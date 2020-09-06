package com.msc.route.service.impl;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.msc.route.model.City;
import com.msc.route.service.RouteConnectorService;

@Service
public class RouteConnectorServiceImpl implements RouteConnectorService {

	private static final Log LOG = LogFactory.getLog(RouteConnectorServiceImpl.class);

	public String isConnected(City origin, City destination) {

		LOG.info("Origin: " + origin.getName() + ", destination: " + destination.getName());
		if (origin.equals(destination))
			return "yes";

		if (origin.getNearby().contains(destination))
			return "yes";

		Set<City> visited = new HashSet<>(Collections.singleton(origin));
		
		Deque<City> cityList = new ArrayDeque<>(origin.getNearby());

		while (!cityList.isEmpty()) {

			City city = cityList.getLast();

			if (city.equals(destination))
				return "yes";
			
			if (!visited.contains(city)) {
				visited.add(city);
     			cityList.addAll(city.getNearby());
				cityList.removeAll(visited);

			} else {
				cityList.removeAll(Collections.singleton(city));
			}
		}
		
		return "no";

	}

}
