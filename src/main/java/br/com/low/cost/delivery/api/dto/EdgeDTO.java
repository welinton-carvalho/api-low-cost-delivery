package br.com.low.cost.delivery.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class EdgeDTO {

	@NotEmpty
	private String id;
	
	@NotNull
	private NodeDTO source;
	
	@NotNull
	private NodeDTO destination;

	@NotNull
	private BigDecimal distance;

	public EdgeDTO() {

	}

	public EdgeDTO(String id, NodeDTO source, NodeDTO destination, BigDecimal distance) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public String getId() {
		return id;
	}

	public NodeDTO getDestination() {
		return destination;
	}

	public NodeDTO getSource() {
		return source;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "EdgeDTO [id=" + id + ", source=" + source + ", destination="
				+ destination + ", distance=" + distance + "]";
	}

}
