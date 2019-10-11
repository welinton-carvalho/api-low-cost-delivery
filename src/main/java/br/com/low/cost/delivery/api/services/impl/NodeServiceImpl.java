package br.com.low.cost.delivery.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;

import br.com.low.cost.delivery.api.dto.NodeDTO;
import br.com.low.cost.delivery.api.entity.NodeEntity;
import br.com.low.cost.delivery.api.parse.NodeParseUtil;
import br.com.low.cost.delivery.api.repositories.NodeRepository;
import br.com.low.cost.delivery.api.services.NodeService;

/**
 * 
 * @author Welinton.Padua
 *
 */
@Service
public class NodeServiceImpl implements NodeService {

	private static final Logger logger = LoggerFactory.getLogger(NodeServiceImpl.class);

	@Autowired
	private NodeRepository nodeRepository;

	@Cacheable("allNodes")
	@Override
	public List<NodeDTO> findAll() {

		logger.info("init findAll nodes...");

		List<NodeDTO> resultList = new ArrayList<NodeDTO>();

		final List<NodeEntity> entities = this.nodeRepository.findAll();

		if (!CollectionUtils.isEmpty(entities)) {

			resultList.addAll(
					entities.stream().map(entity -> NodeParseUtil.parseToDTO(entity)).collect(Collectors.toList()));
		}

		logger.info("end findAll nodes: {}", new Gson().toJson(resultList));

		return resultList;
	}

	@Override
	public Optional<NodeDTO> findById(final Long id) {
		return NodeParseUtil.parseToDTO(nodeRepository.findById(id));
	}

	@CachePut(value = "nodes", key = "#name", unless="#result==null")
	@Override
	public Optional<NodeDTO> findByName(final String name) {
		return NodeParseUtil.parseToDTO(this.nodeRepository.findByName(name));
	}

	@Caching(put = { @CachePut(value = "nodes", key = "#node.getName()") }, evict = {
			@CacheEvict(value = "allNodes", allEntries = true) })
	@Override
	public NodeDTO save(@Valid final NodeDTO node) {
		final NodeEntity entity = this.nodeRepository.save(NodeParseUtil.parseToEntity(node));
		return NodeParseUtil.parseToDTO(entity);
	}

	@Caching(evict = { @CacheEvict(value = "nodes", key = "#name"),
			@CacheEvict(value = "allNodes", allEntries = true) })
	@Override
	public void deleteByName(final String name) {
		this.nodeRepository.deleteByName(name);
	}

	@CacheEvict(cacheNames = "allNodes", allEntries = true)
	@Override
	public void deleteAll() {
		this.nodeRepository.deleteAll();
	}

}
