package br.com.low.cost.delivery.api.models;

import java.math.BigDecimal;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class Edge {
	
	private final String id;
	private final Node source;
	private final Node destination;
	private final BigDecimal distance;

	public Edge(String id, Node source, Node destination, BigDecimal distance) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public String getId() {
		return id;
	}

	public Node getDestination() {
		return destination;
	}

	public Node getSource() {
		return source;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}

}
