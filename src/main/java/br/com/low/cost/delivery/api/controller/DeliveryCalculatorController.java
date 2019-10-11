package br.com.low.cost.delivery.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.low.cost.delivery.api.dto.DeliveryDataRequestDTO;
import br.com.low.cost.delivery.api.dto.DeliveryDataResponseDTO;
import br.com.low.cost.delivery.api.dto.ResponseServiceDTO;
import br.com.low.cost.delivery.api.services.DeliveryCalculatorService;

/**
 * 
 * @author Welinton.Padua
 *
 */
@RestController
public class DeliveryCalculatorController {

	@Autowired
	private DeliveryCalculatorService deliveryCalculatorService;

	public DeliveryCalculatorController() {

	}

	@PostMapping("/delivery/calculateRoute/")
	public ResponseEntity<ResponseServiceDTO<DeliveryDataResponseDTO>> calculateRoute(
			@Valid @RequestBody DeliveryDataRequestDTO deliveryDataRequestDTO) throws Exception {

		final Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> result = deliveryCalculatorService
				.calculateRoute(deliveryDataRequestDTO);

		if (!result.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(result.get());
	}

}
