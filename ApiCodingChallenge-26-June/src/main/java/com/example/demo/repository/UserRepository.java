package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AppUser;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer>{
	
	AppUser findByUsername(String username);

}
