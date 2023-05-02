package com.api.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.repository.UserRepository;
import com.api.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//Estabelece que antes de entrar na rota toda requisição passa pelo filtro

public class FilterToken extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserRepository repository;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain)
      throws ServletException, IOException {

        String token;
        var authorizarion=request.getHeader("Authorization");

        if(authorizarion!=null){
          token=authorizarion.replace("Bearer ", "");//remove berare do token retornado

          var subject=this.tokenService.getSubject(token);//extrai o username do token

          var usuario=this.repository.findByLogin(subject);//busca o usurio extraido do token e verifica se o mesmo existe no banco

          var autenticação=new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities()); //autentiva o usuario

          SecurityContextHolder.getContext().setAuthentication(autenticação);// altera o estado do security context holder
        }
        //passa para o proximo filtro
        filterChain.doFilter(request, response);
    
  }

  
}
