package com.greenbridge.controllers;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
            Map<String, Object> responseBody = responseEntity.getBody();

            // Ottenere l'oggetto associato alla chiave "id_list"
            Object idListObject = responseBody.get("id_list");

            // Verificare se l'oggetto è effettivamente un array
            if (idListObject instanceof List) {
                // Cast dell'oggetto a List
                List<Integer> idList = (List<Integer>) idListObject;

                for (Integer intValue : idList) {
                    System.out.println("Valore intero: " + intValue);

                }
            } else {
                // Gestire il caso in cui l'oggetto non è un array
                System.err.println("La chiave 'arrayDiInt' non contiene un array di interi.");
            }


        return ResponseEntity.ok(responseEntity.getBody());
        }
         else{
            // Comando non riconosciuto
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}

