package com.cx.meetyoubackend.utils;

import com.cx.meetyoubackend.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

	private final JwtProperties jwtProperties;


	public JwtTokenUtil(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
	}

	public String generateToken(String username) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// 使用jwtProperties中的值
		Date expiryDate = new Date(nowMillis + jwtProperties.getExpirationTime());

		// 生成JWT token
		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
			.compact();
	}
}
