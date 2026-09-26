package com.dcl.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
public class AppConfig {
	@Bean
	public ModelMapper mapper() {
		
		return new ModelMapper();   
	}
	
  
}
