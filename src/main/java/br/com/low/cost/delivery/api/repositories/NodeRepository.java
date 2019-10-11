package br.com.low.cost.delivery.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.low.cost.delivery.api.entity.NodeEntity;

/**
 * 
 * @author Welinton.Padua
 *
 */
@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, Long> {

	Optional<NodeEntity> findByName(final String name);

	@Transactional
	Long deleteByName(final String name);

}
