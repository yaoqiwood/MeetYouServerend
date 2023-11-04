package com.cx.meetyoubackend.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private static final String SECRET_KEY = "yourSecretKey";
  private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("sub", username);
    claims.put("created", new Date());
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .compact();
  }

}
