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

import com.api.entity.Cidade;
import com.api.repository.CidadeRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Cidades",description="Gerenciamento das Cidades")
@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

  @Autowired
  private CidadeRepository repository;

  @GetMapping
  public ResponseEntity<List<Cidade>> getAllContas(){
   try{
    List<Cidade> cidades=new ArrayList<>();
    cidades=repository.findAll();
    
    if(!cidades.isEmpty())
      return new ResponseEntity<List<Cidade>>(cidades, HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    
   }catch(Exception ex){
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }
  }


  @GetMapping("/{id}")
  public ResponseEntity<Cidade> getById(@PathVariable(value="id") Integer id){
    try{
      Optional<Cidade> cidades=repository.findById(id);

      if(cidades.isPresent())

        return new ResponseEntity<Cidade>(cidades.get(),HttpStatus.OK);

      else

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }catch(Exception ex){
      return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PostMapping
  public ResponseEntity<Cidade> postConta(@RequestBody Cidade cidade){
    try{
      return new ResponseEntity<Cidade>(repository.save(cidade),HttpStatus.OK);
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
  public ResponseEntity<Cidade> updateSaldo(@PathVariable(value="id")Integer id,@RequestBody Cidade updatedCidade){
    
    try{
      Optional<Cidade> cidade=repository.findById(id);

    if(cidade.isPresent()){
      Cidade aux=cidade.get();
      aux.setPubAlvo(updatedCidade.getPubAlvo());
      return new ResponseEntity<Cidade>(repository.save(aux), HttpStatus.OK);

    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }catch(Exception ex){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/cidades")
  public ResponseEntity<List<Cidade>> getByTitular(@RequestParam(value="nome")String nome){

    try{
      List<Cidade> cidades=repository.findByNome(nome);
      
      if(!cidades.isEmpty())
        return new ResponseEntity<List<Cidade>>(cidades, HttpStatus.OK);
      else
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }catch(Exception ex){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
}
