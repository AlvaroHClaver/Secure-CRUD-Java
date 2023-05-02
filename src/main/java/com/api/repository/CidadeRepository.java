package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Cidade;
import java.util.*;

public interface CidadeRepository extends JpaRepository<Cidade,Integer> {

  List<Cidade> findByNome(String nome);
  
}
