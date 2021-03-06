package com.wcode.api_hateoas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE",
				"CONNECT");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true)
				.useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);

		// configurer.defaultContentType(MediaType.APPLICATION_XML);

		// configurer.favorParameter(false).ignoreAcceptHeader(false)
		// .defaultContentType(MediaType.APPLICATION_JSON)
		// .mediaType("json", MediaType.APPLICATION_JSON)
		// .mediaType("xml", MediaType.APPLICATION_XML);

		// configurer.favorPathExtension(false)
		// .favorParameter(false)
		// .ignoreAcceptHeader(false)
		// .useRegisteredExtensionsOnly(false)
		// .defaultContentType(MediaType.APPLICATION_XML)
		// .mediaType("json", MediaType.APPLICATION_JSON)
		// .mediaType("xml", MediaType.APPLICATION_XML);
	}
}
