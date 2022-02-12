package com.microservice.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.webservices.restfulwebservices.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
