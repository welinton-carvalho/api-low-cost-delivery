package br.com.low.cost.delivery.api.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.low.cost.delivery.api.models.DeliveryResultPath;
import br.com.low.cost.delivery.api.models.Edge;
import br.com.low.cost.delivery.api.models.Graph;
import br.com.low.cost.delivery.api.models.Node;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class DijkstraAlgorithmUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(DijkstraAlgorithmUtilsTest.class);

	private List<Node> nodes;
	private List<Edge> edges;

	public DijkstraAlgorithmUtilsTest() {

	}
	
	@Before
	public void setup() {
		nodes = new ArrayList<>();
		nodes.addAll(Arrays.asList(
				new Node(0L, "A"), 
				new Node(1L, "B"), 
				new Node(2L, "C"),
				new Node(3L, "D"), 
				new Node(4L, "E")));
		
		// AB 10
		// BD 15
		// AC 20
		// CD 30
		// BE 50
		// DE 30
		
		edges = new ArrayList<Edge>();
		addLane("AB", 0, 1, BigDecimal.valueOf(10));
		addLane("BD", 1, 3, BigDecimal.valueOf(15));
		addLane("AC", 0, 2, BigDecimal.valueOf(20));
		addLane("CD", 2, 3, BigDecimal.valueOf(30));
		addLane("BE", 1, 4, BigDecimal.valueOf(50));
		addLane("DE", 3, 4, BigDecimal.valueOf(50));
	}

	@Test
	public void testSuccessGetRouteNodeAToNodeD() {

		final Graph graph = new Graph(nodes, edges);
		
		final DijkstraAlgorithmUtils dijkstra = new DijkstraAlgorithmUtils(graph);
		dijkstra.execute(nodes.get(0)); // nó A
		
		final DeliveryResultPath resultPath = dijkstra.getPath(nodes.get(3)); // nó D

		Assert.assertNotNull(resultPath);
		Assert.assertNotNull(resultPath.getPathToTarget());
		Assert.assertTrue(resultPath.getPathToTarget().size() == 3);
		Assert.assertEquals(resultPath.getTotalDistance(), BigDecimal.valueOf(25L));
		
		for (final Node node : resultPath.getPathToTarget()) {
			logger.info("Nó do trajeto: {}", node);
		}
		
		logger.info("Distancia total: {}", resultPath.getTotalDistance());
	}
	
	@Test
	public void testFailGetRouteNodeAToNodeZ() {

		final Graph graph = new Graph(nodes, edges);
		
		final DijkstraAlgorithmUtils dijkstra = new DijkstraAlgorithmUtils(graph);
		dijkstra.execute(nodes.get(0)); // nó A
		
		final DeliveryResultPath resultPath = dijkstra.getPath(new Node(30L, "Z")); // nó Z inexistente

		Assert.assertNotNull(resultPath);
		Assert.assertNull(resultPath.getPathToTarget());
		Assert.assertNull(resultPath.getTotalDistance());
		
		logger.info("Trajeto não encontrato: {}", resultPath);
	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo, BigDecimal distance) {
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), distance);
		edges.add(lane);
	}

}
