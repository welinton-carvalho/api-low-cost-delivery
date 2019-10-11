package br.com.low.cost.delivery.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class DeliveryDataResponseDTO {

	@JsonProperty("rota")
	private String route;

	@JsonProperty("custo")
	private BigDecimal fuelCost;

	public DeliveryDataResponseDTO() {

	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public BigDecimal getFuelCost() {
		return fuelCost;
	}

	public void setFuelCost(BigDecimal fuelCost) {
		this.fuelCost = fuelCost;
	}

	@Override
	public String toString() {
		return "DeliveryDataResponseDTO [route=" + route + ", fuelCost="
				+ fuelCost + "]";
	}

}
