package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgricoltoreRepository extends JpaRepository<Agricoltore, Integer>{

    Agricoltore findByEmail(String email);


}
