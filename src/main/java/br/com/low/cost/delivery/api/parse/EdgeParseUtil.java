package br.com.low.cost.delivery.api.parse;

import java.util.Optional;

import br.com.low.cost.delivery.api.dto.EdgeDTO;
import br.com.low.cost.delivery.api.entity.EdgeEntity;
import br.com.low.cost.delivery.api.models.Edge;

/**
 * 
 * @author Welinton.Padua
 *
 */
public final class EdgeParseUtil {

	private EdgeParseUtil() {

	}

	public static final Optional<EdgeDTO> parseToDTO(
			final Optional<EdgeEntity> entity) {
		Optional<EdgeDTO> EdgeDTO = Optional.empty();
		if (entity.isPresent()) {
			EdgeDTO = Optional.of(new EdgeDTO(entity.get().getId(),
					NodeParseUtil.parseToDTO(entity.get().getSource()),
					NodeParseUtil.parseToDTO(entity.get().getDestination()),
					entity.get().getDistance()));
		}
		return EdgeDTO;
	}

	public static final EdgeDTO parseToDTO(final EdgeEntity entity) {
		return new EdgeDTO(entity.getId(), NodeParseUtil.parseToDTO(entity
				.getSource()),
				NodeParseUtil.parseToDTO(entity.getDestination()),
				entity.getDistance());
	}

	public static final EdgeEntity parseToEntity(final EdgeDTO dto) {
		return new EdgeEntity(dto.getId(), NodeParseUtil.parseToEntity(dto
				.getSource()),
				NodeParseUtil.parseToEntity(dto.getDestination()),
				dto.getDistance());
	}

	public static final Edge parseToDijkstraEdge(final EdgeDTO dto) {
		return new Edge(dto.getId(), NodeParseUtil.parseToDijkstraNode(dto
				.getSource()), NodeParseUtil.parseToDijkstraNode(dto
				.getDestination()), dto.getDistance());
	}

}
