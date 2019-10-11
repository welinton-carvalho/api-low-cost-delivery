package br.com.low.cost.delivery.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class DeliveryDataRequestDTO {

	@NotNull
	@JsonProperty("origem")
	private String sourceNodeName;

	@NotNull
	@JsonProperty("destino")
	private String destinationNodeName;

	@NotNull
	@JsonProperty("autonomia")
	private BigDecimal veicleAutonomy;

	@NotNull
	@JsonProperty("valorDoLitro")
	private BigDecimal literValueOfFuel;

	public DeliveryDataRequestDTO() {

	}

	public String getSourceNodeName() {
		return sourceNodeName;
	}

	public void setSourceNodeName(String sourceNodeName) {
		this.sourceNodeName = sourceNodeName;
	}

	public String getDestinationNodeName() {
		return destinationNodeName;
	}

	public void setDestinationNodeName(String destinationNodeName) {
		this.destinationNodeName = destinationNodeName;
	}

	public BigDecimal getVeicleAutonomy() {
		return veicleAutonomy;
	}

	public void setVeicleAutonomy(BigDecimal veicleAutonomy) {
		this.veicleAutonomy = veicleAutonomy;
	}

	public BigDecimal getLiterValueOfFuel() {
		return literValueOfFuel;
	}

	public void setLiterValueOfFuel(BigDecimal literValueOfFuel) {
		this.literValueOfFuel = literValueOfFuel;
	}

	@Override
	public String toString() {
		return "DeliveryDataRequestDTO [sourceNodeName=" + sourceNodeName
				+ ", destinationNodeName=" + destinationNodeName
				+ ", veicleAutonomy=" + veicleAutonomy + ", literValueOfFuel="
				+ literValueOfFuel + "]";
	}

}
