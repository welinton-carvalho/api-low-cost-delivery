package br.com.low.cost.delivery.api.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.low.cost.delivery.api.dto.NodeDTO;

/**
 * 
 * @author Welinton.Padua
 *
 */
public interface NodeService {

	List<NodeDTO> findAll();

	Optional<NodeDTO> findById(final Long id);

	Optional<NodeDTO> findByName(final String name);

	NodeDTO save(@Valid final NodeDTO node);

	void deleteByName(final String name);

	void deleteAll();

}
