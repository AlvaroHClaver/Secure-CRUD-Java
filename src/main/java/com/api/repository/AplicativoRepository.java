package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Aplicativo;

public interface AplicativoRepository extends JpaRepository<Aplicativo,Integer>  {

  List<Aplicativo> findByNome(String nome);
  
}
