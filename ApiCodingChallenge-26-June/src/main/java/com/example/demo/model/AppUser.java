package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AppUser {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
	
}
