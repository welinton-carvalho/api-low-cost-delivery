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

import br.com.low.cost.delivery.api.dto.EdgeDTO;
import br.com.low.cost.delivery.api.entity.EdgeEntity;
import br.com.low.cost.delivery.api.parse.EdgeParseUtil;
import br.com.low.cost.delivery.api.repositories.EdgeRepository;
import br.com.low.cost.delivery.api.services.EdgeService;

/**
 * 
 * @author Welinton.Padua
 *
 */
@Service
public class EdgeServiceImpl implements EdgeService {

	private static final Logger logger = LoggerFactory.getLogger(EdgeServiceImpl.class);

	@Autowired
	private EdgeRepository edgeRepository;

	@Cacheable("allEdges")
	@Override
	public List<EdgeDTO> findAll() {

		logger.info("init findAll edges...");

		List<EdgeDTO> resultList = new ArrayList<EdgeDTO>();

		final List<EdgeEntity> entities = this.edgeRepository.findAll();

		if (!CollectionUtils.isEmpty(entities)) {

			resultList.addAll(
					entities.stream().map(entity -> EdgeParseUtil.parseToDTO(entity)).collect(Collectors.toList()));
		}

		logger.info("end findAll edges: {}", new Gson().toJson(resultList));

		return resultList;
	}

	@CachePut(value = "edges", key = "#id", unless="#result==null")
	@Override
	public Optional<EdgeDTO> findById(final String id) {
		return EdgeParseUtil.parseToDTO(edgeRepository.findById(id));
	}

	@Caching(put = { @CachePut(value = "edges", key = "#edge.getId()") }, evict = {
			@CacheEvict(value = "allEdges", allEntries = true),
			@CacheEvict(cacheNames = "allNodes", allEntries = true) })
	@Override
	public EdgeDTO save(@Valid final EdgeDTO edge) {
		final EdgeEntity entity = this.edgeRepository.save(EdgeParseUtil.parseToEntity(edge));
		return EdgeParseUtil.parseToDTO(entity);
	}

	@Caching(evict = { @CacheEvict(value = "edges", key = "#id"), @CacheEvict(value = "allEdges", allEntries = true) })
	@Override
	public void deleteById(final String id) {
		this.edgeRepository.deleteById(id);
	}

	@CacheEvict(cacheNames = "edges", allEntries = true)
	@Override
	public void deleteAll() {
		this.edgeRepository.deleteAll();
	}

}
