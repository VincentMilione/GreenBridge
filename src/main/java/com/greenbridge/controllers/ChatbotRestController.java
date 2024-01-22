package com.greenbridge.controllers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatbotRestController {
    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/executeCommand")
    public ResponseEntity<Map<String, Object>> executeCommand(@RequestBody String command) {
        System.out.println(command);
        // Controllo sul comando ricevuto
        if ("{\"command\":\"/start\"}".equals(command)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            String flaskServerUrl = "http://127.0.0.1:5000/start-module";
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                    flaskServerUrl, HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, Object>>() {
                    });
            return ResponseEntity.ok(responseEntity.getBody());
        } else {
            // Comando non riconosciuto
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}