package com.api.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

  @Value("${api.token.secret}")
  private String SECRET_KEY;

  @Value("${api.token.issuer}")
  private String ISSUE;

  @Value("${api.token.expires}")
  private int EXPIRATION;

  public String gerarToken(User usuario){
    return JWT.create()
    .withIssuer(ISSUE)//adiciona um issuer no payload do token
    .withSubject(usuario.getUsername()) 
    .withClaim("id",usuario.getId())
    .withExpiresAt(LocalDateTime.now()//define momento da expiração
    .plusMinutes(EXPIRATION)
    .toInstant(ZoneOffset.of("-03:00"))
    ).sign(Algorithm.HMAC256(SECRET_KEY));// assina o token

  }

  //extrai o username(subject) do token e verifica validade do mesmo
  public String getSubject(String token){

    return JWT.require(Algorithm.HMAC256(SECRET_KEY))
    .withIssuer(ISSUE)
    .build().verify(token).getSubject();

  }

  
}
