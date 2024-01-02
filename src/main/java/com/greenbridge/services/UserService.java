package com.greenbridge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenbridge.entities.Example;
import com.greenbridge.repositories.ExampleRepository;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository repository;

}
