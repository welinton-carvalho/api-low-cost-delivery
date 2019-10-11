package br.com.low.cost.delivery.api.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.low.cost.delivery.api.dto.EdgeDTO;

/**
 * 
 * @author Welinton.Padua
 *
 */
public interface EdgeService {

	List<EdgeDTO> findAll();

	Optional<EdgeDTO> findById(final String id);

	EdgeDTO save(@Valid final EdgeDTO node);

	void deleteById(final String id);

	void deleteAll();

}
