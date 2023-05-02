package com.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teste {

  @GetMapping("/demo")
  public ResponseEntity<String> teste(){
    return ResponseEntity.ok("Demo");
  }
  
}
