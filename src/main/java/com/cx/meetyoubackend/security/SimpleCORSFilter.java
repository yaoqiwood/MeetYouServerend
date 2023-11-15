package com.cx.meetyoubackend.security;

import com.cx.meetyoubackend.config.CorsProperties;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {

	private final CorsProperties corsProperties;

	@Autowired
	public SimpleCORSFilter(CorsProperties corsProperties) {
		this.corsProperties = corsProperties;
	}


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
		FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		response.setHeader("Access-Control-Allow-Origin", corsProperties.getAllowedOrigin());
		response.setHeader("Access-Control-Allow-Methods", corsProperties.getAllowedMethods());
		response.setHeader("Access-Control-Max-Age", String.valueOf(corsProperties.getMaxAge()));
		response.setHeader("Access-Control-Allow-Headers", corsProperties.getAllowedHeaders());

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}
}