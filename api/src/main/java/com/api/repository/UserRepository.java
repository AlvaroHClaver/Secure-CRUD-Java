package com.api.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User,Integer>  {

  User findByLogin(String login);

  
}
