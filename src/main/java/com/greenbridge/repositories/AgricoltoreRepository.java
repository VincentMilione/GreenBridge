package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgricoltoreRepository extends JpaRepository<Agricoltore, Integer>{



}
