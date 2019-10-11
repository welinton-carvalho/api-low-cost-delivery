package br.com.low.cost.delivery.api.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import br.com.low.cost.delivery.api.component.InitialDataComponent;
import br.com.low.cost.delivery.api.configuration.ApplicationConfig;
import br.com.low.cost.delivery.api.configuration.CacheConfiguration;
import br.com.low.cost.delivery.api.dto.DeliveryDataRequestDTO;
import br.com.low.cost.delivery.api.dto.DeliveryDataResponseDTO;
import br.com.low.cost.delivery.api.dto.ErrorDTO;
import br.com.low.cost.delivery.api.dto.ResponseServiceDTO;
import br.com.low.cost.delivery.api.services.DeliveryCalculatorService;

/**
 * 
 * @author Welinton.Padua
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationConfig.class, CacheConfiguration.class })
public class DeliveryCalculatorServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryCalculatorServiceTest.class);

	@Autowired
	private DeliveryCalculatorService deliveryCalculatorService;

	@Autowired
	private InitialDataComponent initialDataComponent;

	public DeliveryCalculatorServiceTest() {

	}

	@Before
	public void setup() {
		logger.info("realizando load da massa....");
		initialDataComponent.loadInitialData();
	}

	@After
	public void tearDown() {
		logger.info("realizando tearDown da massa....");
		initialDataComponent.loadInitialData();
	}

	@Test
	public void testSuccessCalculateRoute() throws Exception {
		DeliveryDataRequestDTO deliveryDataRequest = new DeliveryDataRequestDTO();
		deliveryDataRequest.setSourceNodeName("A");
		deliveryDataRequest.setDestinationNodeName("D");
		deliveryDataRequest.setVeicleAutonomy(BigDecimal.TEN);
		deliveryDataRequest.setLiterValueOfFuel(BigDecimal.valueOf(2.50));

		final Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> result = deliveryCalculatorService
				.calculateRoute(deliveryDataRequest);

		assertTrue(result.get() != null);
		assertTrue(CollectionUtils.isEmpty(result.get().getErrors()));
		assertNotNull(result.get().getResult());

		final DeliveryDataResponseDTO deliveryDataResponse = result.get().getResult();

		assertEquals("A B D", deliveryDataResponse.getRoute());
		assertEquals(BigDecimal.valueOf(6.25D), deliveryDataResponse.getFuelCost());
	}

	@Test
	public void testFailSourceOrTargetRouteNotExists() throws Exception {
		DeliveryDataRequestDTO deliveryDataRequest = new DeliveryDataRequestDTO();
		deliveryDataRequest.setSourceNodeName("Y");
		deliveryDataRequest.setDestinationNodeName("Z");
		deliveryDataRequest.setVeicleAutonomy(BigDecimal.TEN);
		deliveryDataRequest.setLiterValueOfFuel(BigDecimal.valueOf(2.50));

		final Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> result = deliveryCalculatorService
				.calculateRoute(deliveryDataRequest);

		assertTrue(result.get() != null);
		assertNull(result.get().getResult());
		assertTrue(!CollectionUtils.isEmpty(result.get().getErrors()));

		final List<ErrorDTO> errors = result.get().getErrors();

		assertEquals("1", errors.get(0).getCode());
		assertEquals("2", errors.get(1).getCode());
	}
	
	@Test
	public void testFailNonexistentPath() throws Exception {
		DeliveryDataRequestDTO deliveryDataRequest = new DeliveryDataRequestDTO();
		deliveryDataRequest.setSourceNodeName("D");
		deliveryDataRequest.setDestinationNodeName("A");
		deliveryDataRequest.setVeicleAutonomy(BigDecimal.TEN);
		deliveryDataRequest.setLiterValueOfFuel(BigDecimal.valueOf(2.50));

		final Optional<ResponseServiceDTO<DeliveryDataResponseDTO>> result = deliveryCalculatorService
				.calculateRoute(deliveryDataRequest);

		assertTrue(result.get() != null);
		assertNull(result.get().getResult());
		assertTrue(!CollectionUtils.isEmpty(result.get().getErrors()));

		final List<ErrorDTO> errors = result.get().getErrors();

		assertEquals("3", errors.get(0).getCode());
	}

}
