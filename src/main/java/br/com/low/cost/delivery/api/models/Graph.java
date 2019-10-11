package br.com.low.cost.delivery.api.models;

import java.util.List;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class Graph {

	private final List<Node> Nodees;
	private final List<Edge> edges;

	public Graph(List<Node> Nodees, List<Edge> edges) {
		this.Nodees = Nodees;
		this.edges = edges;
	}

	public List<Node> getNodees() {
		return Nodees;
	}

	public List<Edge> getEdges() {
		return edges;
	}

}