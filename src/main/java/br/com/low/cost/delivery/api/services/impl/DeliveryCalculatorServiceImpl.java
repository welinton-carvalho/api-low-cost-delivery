package br.com.low.cost.delivery.api.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.low.cost.delivery.api.dto.DeliveryDataRequestDTO;
import br.com.low.cost.delivery.api.dto.DeliveryDataResponseDTO;
import br.com.low.cost.delivery.api.dto.ErrorDTO;
import br.com.low.cost.delivery.api.dto.NodeDTO;
import br.com.low.cost.delivery.api.dto.ResponseServiceDTO;
import br.com.low.cost.delivery.api.models.DeliveryResultPath;
import br.com.low.cost.delivery.api.models.Node;
import br.com.low.cost.delivery.api.parse.NodeParseUtil;
import br.com.low.cost.delivery.api.services.DeliveryCalculatorService;
import br.com.low.cost.delivery.api.services.GraphService;
import br.com.low.cost.delivery.api.services.NodeService;
import br.com.low.cost.delivery.api.utils.DijkstraAlgorithmUtils;

/**
 *
 * @author Welinton.Padua
 *
 */
@Service
public class DeliveryCalculatorServiceImpl implements DeliveryCalculatorService {

	private static final int BR_CURRENCY_SCALE = 2;

	@Autowired
	private NodeService nodeService;

	@Autowired
	private GraphService graphService;

	@Override
	public Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> calculateRoute(
			final DeliveryDataRequestDTO deliveryDataRequestDTO) throws Exception {
		try {

			final Optional<NodeDTO> sourceNode = this.nodeService
					.findByName(deliveryDataRequestDTO.getSourceNodeName());

			final Optional<NodeDTO> destinationNode = this.nodeService
					.findByName(deliveryDataRequestDTO.getDestinationNodeName());

			Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> result = this.validateInformedNodes(sourceNode,
					destinationNode);

			if (result.get().hasError()) {
				return result;
			}

			DijkstraAlgorithmUtils dijkstra = new DijkstraAlgorithmUtils(graphService.loadGraph());
			dijkstra.execute(NodeParseUtil.parseToDijkstraNode(sourceNode));

			final DeliveryResultPath resultPath = dijkstra.getPath(NodeParseUtil.parseToDijkstraNode(destinationNode));

			if (resultPath.isRouteEncountered()) {

				DeliveryDataResponseDTO dataResponse = new DeliveryDataResponseDTO();
				dataResponse.setRoute(this.formatRoutePath(resultPath.getPathToTarget()));
				dataResponse.setFuelCost(this.calculateDeliveryCost(resultPath.getTotalDistance(),
						deliveryDataRequestDTO.getVeicleAutonomy(), deliveryDataRequestDTO.getLiterValueOfFuel()));

				result.get().setResult(dataResponse);
			} else {

				result.get().addError(new ErrorDTO("3", "O trajeto desejado não foi encontrado no mapa."));
			}

			return result;

		} catch (Exception e) {
			throw new Exception("Ocorreu um erro ao calcular a rota.", e);
		}
	}

	private String formatRoutePath(final LinkedList<Node> nodes) {
		StringBuilder stb = new StringBuilder();

		nodes.forEach(node -> stb.append(node.getName().concat(" ")));

		return stb.toString().trim();
	}

	private BigDecimal calculateDeliveryCost(final BigDecimal totalDistance, final BigDecimal veicleAutonomy,
			final BigDecimal litleValue) {

		return totalDistance.divide(veicleAutonomy, BR_CURRENCY_SCALE, RoundingMode.HALF_UP).multiply(litleValue)
				.setScale(BR_CURRENCY_SCALE, RoundingMode.HALF_UP);
	}

	private Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> validateInformedNodes(
			final Optional<NodeDTO> sourceNode, final Optional<NodeDTO> destinationNode) {

		Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> result = Optional.of(new ResponseServiceDTO<>());

		if (!sourceNode.isPresent()) {
			result.get().addError(new ErrorDTO("1", "Endereço de origem não encontrado"));
		}

		if (!destinationNode.isPresent()) {
			result.get().addError(new ErrorDTO("2", "Endereço de destino não encontrado"));
		}

		return result;
	}

}
