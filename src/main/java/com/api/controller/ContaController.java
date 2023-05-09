package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Conta;
import com.api.repository.ContaRepository;

import java.util.*;


@RestController
@RequestMapping("/api/contas")
public class ContaController {

  @Autowired
  private ContaRepository repository;

  @GetMapping
  public ResponseEntity<List<Conta>> getAllContas(){
   try{
    List<Conta> contas=new ArrayList<>();
    contas=repository.findAll();
    
    if(!contas.isEmpty())
      return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    
   }catch(Exception ex){
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }

  }

  @GetMapping("/{id}")
  public ResponseEntity<Conta> getById(@PathVariable(value="id") Integer id){
    try{
      Optional<Conta> conta=repository.findById(id);

      if(conta.isPresent())

        return new ResponseEntity<Conta>(conta.get(),HttpStatus.OK);

      else

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }catch(Exception ex){
      return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PostMapping
  public ResponseEntity<Conta> postConta(@RequestBody Conta conta){
    try{
      return new ResponseEntity<Conta>(repository.save(conta),HttpStatus.OK);
    }catch(Exception ex){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable(value="id")Integer id){
    try{
      repository.deleteById(id);
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }catch(Exception ex){
      return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PutMapping("/{id}")
  public ResponseEntity<Conta> updateSaldo(@PathVariable(value="id")Integer id,@RequestBody Conta updatedConta){
    
    try{
      Optional<Conta> conta=repository.findById(id);

    if(conta.isPresent()){
      Conta aux=conta.get();
      aux.setSaldo(updatedConta.getSaldo());
      return new ResponseEntity<Conta>(repository.save(aux), HttpStatus.OK);

    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }catch(Exception ex){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/titulares")
  public ResponseEntity<List<Conta>> getByTitular(@RequestParam(value="titular")String titular){

    try{
      List<Conta> contas=repository.findByTitular(titular);
      
      if(!contas.isEmpty())
        return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);
      else
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }catch(Exception ex){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
}
