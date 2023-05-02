package com.api.controller;

import java.util.*;

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

import com.api.entity.Aplicativo;
import com.api.repository.AplicativoRepository;

@RestController
@RequestMapping("/api/apps")
public class AplicativoController {

  @Autowired
  private AplicativoRepository repository;


  @GetMapping
  public ResponseEntity<List<Aplicativo>> getAllContas(){
   try{
    List<Aplicativo> apps=new ArrayList<>();
    apps=repository.findAll();
    
    if(!apps.isEmpty())
      return new ResponseEntity<List<Aplicativo>>(apps, HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    
   }catch(Exception ex){
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }

  }

  @GetMapping("/{id}")
  public ResponseEntity<Aplicativo> getById(@PathVariable(value="id") Integer id){
    try{
      Optional<Aplicativo> apps=repository.findById(id);

      if(apps.isPresent())

        return new ResponseEntity<Aplicativo>(apps.get(),HttpStatus.OK);

      else

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }catch(Exception ex){
      return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PostMapping
  public ResponseEntity<Aplicativo> postConta(@RequestBody Aplicativo app){
    try{
      return new ResponseEntity<Aplicativo>(repository.save(app),HttpStatus.OK);
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
  public ResponseEntity<Aplicativo> updateSaldo(@PathVariable(value="id")Integer id,@RequestParam(value="downloads")int downloads){
    
    try{
      Optional<Aplicativo> conta=repository.findById(id);

    if(conta.isPresent()){
      Aplicativo aux=conta.get();
      aux.setNroDownloads(downloads);
      return new ResponseEntity<Aplicativo>(repository.save(aux), HttpStatus.OK);

    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }catch(Exception ex){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/aplicativos")
  public ResponseEntity<List<Aplicativo>> getByTitular(@RequestParam(value="nome")String nome){

    try{
      List<Aplicativo> apps=repository.findByNome(nome);
      
      if(!apps.isEmpty())
        return new ResponseEntity<List<Aplicativo>>(apps, HttpStatus.OK);
      else
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }catch(Exception ex){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
}
