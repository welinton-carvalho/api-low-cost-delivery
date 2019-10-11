package br.com.low.cost.delivery.api.parse;

import java.util.Optional;

import br.com.low.cost.delivery.api.dto.NodeDTO;
import br.com.low.cost.delivery.api.entity.NodeEntity;
import br.com.low.cost.delivery.api.models.Node;

/**
 * 
 * @author Welinton.Padua
 *
 */
public final class NodeParseUtil {

	private NodeParseUtil() {

	}

	public static final Optional<NodeDTO> parseToDTO(
			final Optional<NodeEntity> entity) {
		Optional<NodeDTO> nodeDTO = Optional.empty();
		if (entity.isPresent()) {
			nodeDTO = Optional.of(new NodeDTO(entity.get().getId(), entity
					.get().getName()));
		}
		return nodeDTO;
	}

	public static final NodeDTO parseToDTO(final NodeEntity entity) {
		return new NodeDTO(entity.getId(), entity.getName());
	}

	public static final NodeEntity parseToEntity(final NodeDTO dto) {
		return new NodeEntity(dto.getId(), dto.getName());
	}

	public static final Node parseToDijkstraNode(final Optional<NodeDTO> dto) {
		return new Node(dto.get().getId(), dto.get().getName());
	}

	public static final Node parseToDijkstraNode(final NodeDTO dto) {
		return new Node(dto.getId(), dto.getName());
	}

}
