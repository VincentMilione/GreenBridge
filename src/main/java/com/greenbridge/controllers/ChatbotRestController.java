package com.greenbridge.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatbotRestController {
    @PostMapping ("/sendDataToFlask")
    public String sendDataToFlask() {
        String jsonData = "{\"key\": \"value\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);

        String flaskServerUrl = "http://127.0.0.1:5000/receive-data";

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.exchange(flaskServerUrl, HttpMethod.POST, entity, String.class).getBody();


        return "Request sent to Flask server. Response: " + response;
    }
}