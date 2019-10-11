package br.com.low.cost.delivery.api.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Welinton.Padua
 *
 */
@Entity
@Table(name = "edge")
public class EdgeEntity {

	@Id
	private String id;

	@OneToOne(cascade = CascadeType.ALL)
	private NodeEntity source;

	@OneToOne(cascade = CascadeType.ALL)
	private NodeEntity destination;

	private BigDecimal distance;

	public EdgeEntity() {

	}

	public EdgeEntity(String id, NodeEntity source, NodeEntity destination, BigDecimal distance) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NodeEntity getSource() {
		return source;
	}

	public void setSource(NodeEntity source) {
		this.source = source;
	}

	public NodeEntity getDestination() {
		return destination;
	}

	public void setDestination(NodeEntity destination) {
		this.destination = destination;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "EdgeEntity [id=" + id + ", source=" + source + ", destination=" + destination + ", distance=" + distance
				+ "]";
	}

}
