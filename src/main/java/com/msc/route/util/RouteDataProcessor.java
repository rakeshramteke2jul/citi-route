package com.msc.route.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.msc.route.model.City;

@Component
public class RouteDataProcessor {

	private final Log LOG = LogFactory.getLog(RouteDataProcessor.class);

	private Map<String, City> cityMap = new HashMap<>();

	@Value("${data.file:classpath:city.txt}")
	private String CITIES;

	@Autowired
	private ResourceLoader resourceLoader;

	public Map<String, City> getCityMap() {
		return cityMap;
	}

	@PostConstruct
	private void read() throws IOException {

		LOG.info("Reading data");

		Resource resource = resourceLoader.getResource(CITIES);

		InputStream is;

		if (!resource.exists()) {
			// file on the filesystem path
			is = new FileInputStream(new File(CITIES));
		} else {
			// file is a classpath resource
			is = resource.getInputStream();
		}

		Scanner scanner = new Scanner(is);

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (StringUtils.isEmpty(line))
				continue;

			LOG.info(line);

			String[] split = line.split(",");
			String originKey = split[0].trim().toUpperCase();
			String destiKey = split[1].trim().toUpperCase();

			if (!originKey.equals(destiKey)) {
				City originCity = cityMap.getOrDefault(originKey, City.build(originKey));
				City destinationCity = cityMap.getOrDefault(destiKey, City.build(destiKey));

				originCity.addNearby(destinationCity);
				destinationCity.addNearby(originCity);

				cityMap.put(originCity.getName(), originCity);
				cityMap.put(destinationCity.getName(), destinationCity);
			}
		}

		LOG.info("Map: " + cityMap);
	}

	public City getCity(String name) {
		return cityMap.get(name);
	}

}
