package com.msc.route;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact(
			"Rakesh Ramteke", "localhost:8080", "ramteke.rakesh@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"Citi Connected Route API Title", "This api is used to check two cities are connected or not.", "1.0",
			"urn:tos", DEFAULT_CONTACT, 
			"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				 .select()
		            .paths(Predicates.not(PathSelectors.regex("/error"))) // Exclude Spring error controllers
		            .build();
				
				
	}
}
