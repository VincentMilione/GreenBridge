package com.greenbridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenbridge.entities.Example;

public interface ExampleRepository extends JpaRepository<User, String>{
    
}
