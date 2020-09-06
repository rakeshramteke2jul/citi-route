package com.msc.route.service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.msc.route.model.City;
import com.msc.route.service.impl.RouteConnectorServiceImpl;
import com.msc.route.util.RouteDataProcessor;

public class RouteConnectorServiceImplTest {

	
	RouteDataProcessor routeDataProcessor;

	@InjectMocks
	RouteConnectorServiceImpl routeConnectorServiceImpl;

	@Autowired
	City originCity;
	@Autowired
	City destinationCity;
	
	@Autowired
	City destinationCity1;


	@Autowired
	private Set<City> nearby;

	@Before
	public void setUp() throws Exception {
		originCity = City.build("Boston");
		destinationCity = City.build("Philadelphia");
		destinationCity1 = City.build("Albany");
		
		nearby = new HashSet<>();
		nearby.add(originCity);
		originCity.addNearby(City.build("Newark"))
         .addNearby(City.build("Philadelphia"));

		
		routeDataProcessor = new RouteDataProcessor();
		
		Map<String, City> map =routeDataProcessor.getCityMap();
		map.put("Boston", originCity);
		map.put("Newark", destinationCity);
		map.put("Albany", destinationCity1);

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOriginAndDestinationSame() {

		String result = routeConnectorServiceImpl.isConnected(originCity, originCity);
		assertEquals("yes", result);

	}

	@Test
	public void testNeighbourCity() {
		Assert.assertNotNull("Invalid test data. City not found: origin", originCity);
		Assert.assertNotNull("Invalid test data. City not found: destination", destinationCity);
		String result = routeConnectorServiceImpl.isConnected(originCity, destinationCity);

		Assert.assertEquals("yes", result);
	}

	
	@Test
	public void testNeighbourCityNotConnected() {
		City destinationCity = routeDataProcessor.getCity("Albany");
		Assert.assertNotNull("Invalid test data. City not found: origin", originCity);
		Assert.assertNotNull("Invalid test data. City not found: destination", destinationCity);
		String result = routeConnectorServiceImpl.isConnected(originCity, destinationCity1);

		Assert.assertEquals("no", result);
	}
}
