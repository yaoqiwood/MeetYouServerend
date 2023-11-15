package com.cx.meetyoubackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cors")
@Data
public class CorsProperties {

	private String allowedOrigin;
	private String allowedMethods;
	private long maxAge;
	private String allowedHeaders;
}