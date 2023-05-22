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

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;

@Tag(name="Contas",description="Gerenciamento das Contas Bancárias")
@RestController
@RequestMapping("/api/contas")
public class ContaController {

  @Autowired
  private ContaRepository repository;

  @ApiResponses({
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Conta.class), mediaType = "application/json") }),
    @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
  })

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

  @ApiResponses({
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Conta.class), mediaType = "application/json") }),
    @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
  })

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

  @ApiResponses({
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Conta.class), mediaType = "application/json") }),
    @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
  })

  @PostMapping
  public ResponseEntity<Conta> postConta(@RequestBody Conta conta){
    try{
      return new ResponseEntity<Conta>(repository.save(conta),HttpStatus.OK);
    }catch(Exception ex){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
  }

  @ApiResponses({
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Conta.class), mediaType = "application/json") }),
    @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
  })
  
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable(value="id")Integer id){
    try{
      repository.deleteById(id);
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }catch(Exception ex){
      return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @ApiResponses({
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Conta.class), mediaType = "application/json") }),
    @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
  })

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

  @ApiResponses({
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Conta.class), mediaType = "application/json") }),
    @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema()) }),
    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
  })

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
