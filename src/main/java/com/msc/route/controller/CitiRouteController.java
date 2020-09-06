package com.msc.route.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msc.route.model.City;
import com.msc.route.service.impl.RouteConnectorServiceImpl;
import com.msc.route.util.RouteDataProcessor;

@RestController
public class CitiRouteController {

	@Autowired
	RouteConnectorServiceImpl routeConnector;

	@Autowired
	RouteDataProcessor dataProcessor;

	@GetMapping(value = "/connected", produces = "text/plain")
	public String checkConnectedRoute(@RequestParam(value = "origin", required = true) String origin,
			@RequestParam(value = "destination", required = true) String desti) {

		City originCity = dataProcessor.getCity(origin.toUpperCase());
		City destCity = dataProcessor.getCity(desti.toUpperCase());

		return routeConnector.isConnected(originCity, destCity);

	}
	
	@GetMapping(value = "/", produces = "text/html")
    public String info() {

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html><head><meta charset=\"utf-8\"><title>City Data</title></head><body>")
                .append("<h2>City List</h2>")
                .append("<ul>");

        Collection<City> cities = dataProcessor.getCityMap().values();
        for (City city : cities) {
            html.append("<li>")
                    .append(city.getName())
                    .append(" &rarr; ")
                    .append(city.printNearByCity())
                    .append("</li>");
        }
        html.append("</ul>");
        html.append("</body></html>");
        return html.toString();
    }


}
