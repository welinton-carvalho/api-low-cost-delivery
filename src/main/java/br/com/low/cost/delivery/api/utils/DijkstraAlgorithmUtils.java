package br.com.low.cost.delivery.api.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.low.cost.delivery.api.models.Edge;
import br.com.low.cost.delivery.api.models.Graph;
import br.com.low.cost.delivery.api.models.Node;
import br.com.low.cost.delivery.api.models.DeliveryResultPath;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class DijkstraAlgorithmUtils {

	private final List<Edge> edges;
	private Set<Node> settledNodes;
	private Set<Node> unSettledNodes;
	private Map<Node, Node> predecessors;
	private Map<Node, BigDecimal> distance;

	public DijkstraAlgorithmUtils(final Graph graph) {
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Node source) {
		settledNodes = new HashSet<Node>();
		unSettledNodes = new HashSet<Node>();
		distance = new HashMap<Node, BigDecimal>();
		predecessors = new HashMap<Node, Node>();
		distance.put(source, BigDecimal.ZERO);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Node node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Node node) {
		List<Node> adjacentNodes = getNeighbors(node);
		for (Node target : adjacentNodes) {
			if (getShortestDistance(target).compareTo(getShortestDistance(node).add(getDistance(node, target))) > 0) {
				distance.put(target, getShortestDistance(node).add(getDistance(node, target)));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private BigDecimal getDistance(Node node, Node target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getDistance();
			}
		}
		throw new RuntimeException("Nenhum trajeto encontrado");
	}

	private List<Node> getNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	private Node getMinimum(Set<Node> Nodees) {
		Node minimum = null;
		for (Node Node : Nodees) {
			if (minimum == null) {
				minimum = Node;
			} else {
				if (getShortestDistance(Node).compareTo(getShortestDistance(minimum)) < 0) {
					minimum = Node;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Node Node) {
		return settledNodes.contains(Node);
	}

	private BigDecimal getShortestDistance(Node destination) {
		BigDecimal d = distance.get(destination);
		if (d == null) {
			return BigDecimal.valueOf(Double.MAX_VALUE);
		} else {
			return d;
		}
	}

	public DeliveryResultPath getPath(Node target) {
		DeliveryResultPath resultPath = new DeliveryResultPath();
		LinkedList<Node> pathToTarget = new LinkedList<Node>();
		Node step = target;

		if (predecessors.get(step) == null) {
			return resultPath;
		}

		pathToTarget.add(step);

		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			pathToTarget.add(step);
		}

		Collections.reverse(pathToTarget);

		resultPath.setPathToTarget(pathToTarget);
		resultPath.setTotalDistance(distance.get(target));

		return resultPath;
	}

}
