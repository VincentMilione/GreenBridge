package com.greenbridge.controllers;


import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatbotRestController {
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    AgricoltoreServiceImpl agricoltoreServiceImpl;

    @PostMapping("/executeCommand")
    public List<String> executeCommand(@RequestBody String command) {
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
            List<String> agricoltoriList = new ArrayList<>();

            // Verificare se l'oggetto è effettivamente un array
            if (idListObject instanceof List) {
                // Cast dell'oggetto a List
                List<Integer> idList = (List<Integer>) idListObject;

                for (Integer intValue : idList) {
                    System.out.println("Valore intero: " + intValue);
                    agricoltoreServiceImpl.getSingleAgricoltore(intValue);
                    Agricoltore agricoltore = agricoltoreServiceImpl.getSingleAgricoltore(intValue);

                    agricoltoriList.add(agricoltore.getNomeBottega()); // Aggiungi l'agricoltore alla lista

                }
            }
            else {
                // Gestire il caso in cui l'oggetto non è un array
                System.err.println("La chiave 'arrayDiInt' non contiene un array di interi.");
            }


            return agricoltoriList;
        }
         else{
            // Comando non riconosciuto
            List<String> error = new ArrayList<>();
            error.add("Comando non riconosciuto");
            return error;
        }
    }
}

