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
        // Data to be sent in JSON format
        String jsonData = "{\"key\": \"value\"}";

        // Set up the headers for the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create an HttpEntity with the JSON data and headers
        HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);

        // URL of the Python Flask server endpoint
        String flaskServerUrl = "http://127.0.0.1:5000/receive-data";

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Make the HTTP POST request to the Flask server
        String response = restTemplate.exchange(flaskServerUrl, HttpMethod.POST, entity, String.class).getBody();

        // Process the response from the Flask server
        // ...

        return "Request sent to Flask server. Response: " + response;
    }
}