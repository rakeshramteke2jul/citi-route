package com.msc.route.service;

import com.msc.route.model.City;

public interface RouteConnectorService {
	
	public String isConnected(City origin,City destination);

}
