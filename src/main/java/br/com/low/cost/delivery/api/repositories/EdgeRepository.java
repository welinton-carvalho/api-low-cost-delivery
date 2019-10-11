package br.com.low.cost.delivery.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.low.cost.delivery.api.entity.EdgeEntity;

/**
 * 
 * @author Welinton.Padua
 *
 */
@Repository
public interface EdgeRepository extends JpaRepository<EdgeEntity, String> {

}
