package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.entity.Conta;
import java.util.*;


public interface ContaRepository extends JpaRepository<Conta,Integer> {

  List<Conta> findByTitular(String titular);
  
}
