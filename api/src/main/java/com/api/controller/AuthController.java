package com.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

import com.api.dto.Login;
import com.api.entity.User;
import com.api.service.TokenService;

@RestController
public class AuthController {

  @Autowired 
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public String login(@RequestBody Login login){
    
     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
     new UsernamePasswordAuthenticationToken(login.login(),login.password());

     Authentication authenticate=this.authenticationManager.authenticate(usernamePasswordAuthenticationToken); //autentiva usuario

     var usuario=(User) authenticate.getPrincipal();
     return tokenService.gerarToken(usuario); //passa o usuario valido encontrado e gera o token
  }
  
}
