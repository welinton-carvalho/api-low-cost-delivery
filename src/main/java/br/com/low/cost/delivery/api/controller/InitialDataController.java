package br.com.low.cost.delivery.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.low.cost.delivery.api.component.InitialDataComponent;

/**
 * 
 * @author Welinton.Padua
 *
 */
@RestController
public class InitialDataController {

	@Autowired
	private InitialDataComponent initialDataComponent;

	public InitialDataController() {

	}
	
	@GetMapping("/initial/data/load")
	public void loadInitialData() {
		initialDataComponent.loadInitialData();
	}
	
	@DeleteMapping("/initial/data/clear")
	public void clearInitialData() {
		initialDataComponent.clearInitialData();
	}

}
