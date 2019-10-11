package br.com.low.cost.delivery.api.models;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.springframework.util.CollectionUtils;

/**
 * 
 * @author Welinton.Padua
 *
 */
public class DeliveryResultPath {

	private LinkedList<Node> pathToTarget;

	private BigDecimal totalDistance;

	public DeliveryResultPath() {

	}

	public LinkedList<Node> getPathToTarget() {
		return pathToTarget;
	}

	public void setPathToTarget(LinkedList<Node> pathToTarget) {
		this.pathToTarget = pathToTarget;
	}

	public BigDecimal getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(BigDecimal totalDistance) {
		this.totalDistance = totalDistance;
	}

	public boolean isRouteEncountered() {
		return !CollectionUtils.isEmpty(this.pathToTarget);
	}

	@Override
	public String toString() {
		return "DeliveryResultPath [pathToTarget=" + pathToTarget + ", totalDistance=" + totalDistance + "]";
	}

}
