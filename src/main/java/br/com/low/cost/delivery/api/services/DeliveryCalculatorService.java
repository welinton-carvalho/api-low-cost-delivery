package br.com.low.cost.delivery.api.services;

import java.util.Optional;

import br.com.low.cost.delivery.api.dto.DeliveryDataRequestDTO;
import br.com.low.cost.delivery.api.dto.DeliveryDataResponseDTO;
import br.com.low.cost.delivery.api.dto.ResponseServiceDTO;

/**
 * 
 * @author Welinton.Padua
 *
 */
public interface DeliveryCalculatorService {

	Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> calculateRoute(
			final DeliveryDataRequestDTO deliveryDataRequestDTO) throws Exception;

}
