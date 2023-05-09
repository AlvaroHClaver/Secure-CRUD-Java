package com.api.config;


import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.api.filter.FilterToken;


import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity

public class Configs {

  @Autowired
  private FilterToken filtro;
  

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    return config.getAuthenticationManager();
  }

  @Bean 
  //configura as rotas e a segurança
  public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
      
    http.cors(Customizer.withDefaults()).csrf().disable()//passa o método cors para liberar as requisições de aplicações externas
    .authorizeHttpRequests()
    .requestMatchers("/login","/register")//define as rotas que não necessitam de informar o token
    .permitAll()
    .anyRequest()//qualquer outra requisição deve ser autenticada
    .authenticated()
    .and()
    .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//define estado da sessão
         .and().addFilterBefore(filtro,UsernamePasswordAuthenticationFilter.class);
    return http.build();
    
    
  }

  @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));//url da qual as requisições devem ser autorizadas http://localhost:8080
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT","DELETE"));//métdos autorizados
        configuration.applyPermitDefaultValues();//autoriza os métodos
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
   }

  @Bean
  //Encriptador para a senha não ficar vulneravel no BD.
  public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
  
}
