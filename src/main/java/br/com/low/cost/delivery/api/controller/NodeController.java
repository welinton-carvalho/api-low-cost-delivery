package br.com.low.cost.delivery.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.low.cost.delivery.api.dto.NodeDTO;
import br.com.low.cost.delivery.api.services.NodeService;

/**
 * 
 * @author Welinton.Padua
 *
 */
@RestController
public class NodeController {

	@Autowired
	private NodeService nodeService;

	public NodeController() {

	}
	
	@GetMapping("/nodes")
	public List<NodeDTO> retrieveAllNodes() {

		return nodeService.findAll();
	}

	@GetMapping("/node/id/{id}")
	public ResponseEntity<NodeDTO> retrieveNode(@PathVariable final Long id) {

		final Optional<NodeDTO> node = nodeService.findById(id);

		if (!node.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(node.get());
	}

	@GetMapping("/node/name/{name}")
	public ResponseEntity<NodeDTO> retrieveNode(
			@PathVariable final String name) {

		final Optional<NodeDTO> node = nodeService.findByName(name);

		if (!node.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(node.get());
	}

	@PostMapping("/node")
	public ResponseEntity<NodeDTO> createNode(@Valid @RequestBody NodeDTO node) {

		final NodeDTO savedNode = nodeService.save(node);

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedNode.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/node/delete/{name}")
	public void deleteNode(@PathVariable final String name) {
		nodeService.deleteByName(name);
	}
	
	@DeleteMapping("/node/delete/all")
	public void deleteNode() {
		nodeService.deleteAll();
	}
	
}
