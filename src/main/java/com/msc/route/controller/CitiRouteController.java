package com.msc.route.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("connected")
public class CitiRouteController {

	@GetMapping
	public String getRoute(@RequestParam("origin") String origin, @RequestParam("destination") String desti) {
		return origin+" "+desti;
	}

}
