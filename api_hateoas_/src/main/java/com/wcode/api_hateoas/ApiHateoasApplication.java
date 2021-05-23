package com.wcode.api_hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.wcode.api_hateoas.config.FileConfig;

@SpringBootApplication
@EnableConfigurationProperties({ FileConfig.class })
@EnableAutoConfiguration
@ComponentScan
public class ApiHateoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHateoasApplication.class, args);
	}
}
