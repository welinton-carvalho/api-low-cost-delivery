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

import br.com.low.cost.delivery.api.dto.EdgeDTO;
import br.com.low.cost.delivery.api.services.EdgeService;

/**
 * 
 * @author Welinton.Padua
 *
 */
@RestController
public class EdgeController {

	@Autowired
	private EdgeService edgeService;

	public EdgeController() {

	}
	
	@GetMapping("/edges")
	public List<EdgeDTO> retrieveAllEdges() {

		return edgeService.findAll();
	}

	@GetMapping("/edge/id/{id}")
	public ResponseEntity<EdgeDTO> retrieveEdge(@PathVariable final String id) {

		final Optional<EdgeDTO> edge = edgeService.findById(id);

		if (!edge.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(edge.get());
	}

	@PostMapping("/edge")
	public ResponseEntity<EdgeDTO> createEdge(@Valid @RequestBody EdgeDTO edge) {

		final EdgeDTO savedEdge = edgeService.save(edge);

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedEdge.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/edge/delete/{id}")
	public void deleteEdge(@PathVariable final String id) {
		edgeService.deleteById(id);
	}

	@DeleteMapping("/edge/delete/all")
	public void deleteEdge() {
		edgeService.deleteAll();
	}

}
