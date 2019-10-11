package br.com.low.cost.delivery.api.component.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.low.cost.delivery.api.component.InitialDataComponent;
import br.com.low.cost.delivery.api.dto.EdgeDTO;
import br.com.low.cost.delivery.api.dto.NodeDTO;
import br.com.low.cost.delivery.api.services.EdgeService;
import br.com.low.cost.delivery.api.services.NodeService;

@Component
public class InitialDataComponentImpl implements InitialDataComponent {

	@Autowired
	private NodeService nodeService;

	@Autowired
	private EdgeService edgeService;

	public InitialDataComponentImpl() {
	
	}

	@Override
	public void loadInitialData() {
		
		this.clearInitialData();

		List<NodeDTO> nodesAfterSave = new ArrayList<>();
		
		final List<NodeDTO> nodes = Arrays.asList(
				new NodeDTO("A"), 
				new NodeDTO("B"), 
				new NodeDTO("C"),
				new NodeDTO("D"), 
				new NodeDTO("E"));
		
		nodes.forEach(node -> {
			nodesAfterSave.add(nodeService.save(node));
		});

		// AB 10
		// BD 15
		// AC 20
		// CD 30
		// BE 50
		// DE 30

		final List<EdgeDTO> edges = Arrays.asList(
				new EdgeDTO("AB", this.findSavedNode(nodesAfterSave, "A"), this.findSavedNode(nodesAfterSave, "B"), BigDecimal.TEN),
				new EdgeDTO("BD", this.findSavedNode(nodesAfterSave, "B"), this.findSavedNode(nodesAfterSave, "D"), BigDecimal.valueOf(15D)),
				new EdgeDTO("AC", this.findSavedNode(nodesAfterSave, "A"), this.findSavedNode(nodesAfterSave, "C"), BigDecimal.valueOf(20D)),
				new EdgeDTO("CD", this.findSavedNode(nodesAfterSave, "C"), this.findSavedNode(nodesAfterSave, "D"), BigDecimal.valueOf(30D)),
				new EdgeDTO("BE", this.findSavedNode(nodesAfterSave, "B"), this.findSavedNode(nodesAfterSave, "E"), BigDecimal.valueOf(50D)),
				new EdgeDTO("DE", this.findSavedNode(nodesAfterSave, "D"), this.findSavedNode(nodesAfterSave, "E"), BigDecimal.valueOf(30D)));
		
		edges.forEach(edge -> {
			edgeService.save(edge);
		});
		
	}

	/**
	 * Metodo utilizado para pegaro resgistro salvo por conta do Id auto generated do jpa.
	 * 
	 * @param nodesAfterSave
	 * @param nodeName
	 * @return
	 */
	private NodeDTO findSavedNode(final List<NodeDTO> nodesAfterSave, final String nodeName) {

		return nodesAfterSave.stream().filter(node -> node.getName().equalsIgnoreCase(nodeName)).findFirst()
				.orElse(null);
	}

	@Override
	public void clearInitialData(){
		edgeService.deleteAll();
		nodeService.deleteAll();
	}
	
}
