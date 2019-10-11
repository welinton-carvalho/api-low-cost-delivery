package br.com.low.cost.delivery.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.low.cost.delivery.api.models.Edge;
import br.com.low.cost.delivery.api.models.Graph;
import br.com.low.cost.delivery.api.models.Node;
import br.com.low.cost.delivery.api.parse.EdgeParseUtil;
import br.com.low.cost.delivery.api.parse.NodeParseUtil;
import br.com.low.cost.delivery.api.services.EdgeService;
import br.com.low.cost.delivery.api.services.GraphService;
import br.com.low.cost.delivery.api.services.NodeService;

/**
 * 
 * @author Welinton.Padua
 *
 */
@Service
public class GraphServiceImpl implements GraphService {

	private static final Logger logger = LoggerFactory
			.getLogger(GraphServiceImpl.class);

	@Autowired
	private EdgeService edgeService;

	@Autowired
	private NodeService nodeService;

	@Override
	public Graph loadGraph() {

		logger.debug("init loading graph...");

		final List<Node> nodes = nodeService.findAll().stream()
				.map(dto -> NodeParseUtil.parseToDijkstraNode(dto))
				.collect(Collectors.toList());

		final List<Edge> edges = edgeService.findAll().stream()
				.map(dto -> EdgeParseUtil.parseToDijkstraEdge(dto))
				.collect(Collectors.toList());

		logger.debug("end loading graph...");

		return new Graph(nodes, edges);
	}

}
