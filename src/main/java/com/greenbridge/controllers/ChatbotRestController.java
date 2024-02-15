package com.greenbridge.controllers;


import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Questa classe rappresenta un controller REST
 * che gestisce le richieste API per il chatbot.
 * Le operazioni principali riguardano l'esecuzione di comandi
 * e la gestione delle risposte.
 *
 * @author Davide Califano, Giovanni De Gregorio
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class ChatbotRestController {
    /**
     * Template per le chiamate REST utilizzato
     * per comunicare con il server Flask.
     */
    private RestTemplate restTemplate = new RestTemplate();
    /**
     * Implementazione del servizio AgricoltoreService
     * utilizzata per ottenere informazioni sugli agricoltori.
     */
    @Autowired
    private AgricoltoreServiceImpl agricoltoreServiceImpl;
    /**
     * Metodo che gestisce le richieste POST relative all'esecuzione di comandi.
     *
     * @param command Comando inviato come corpo della richiesta.
     * @return Lista di agricoltori affini al cliente.
     */

    @PostMapping("/executeCommand")
    public List<String> executeCommand(@RequestBody final String command) {
        System.out.println(command);

        // Controllo sul comando ricevuto
        if ("{\"command\":\"/start\"}".equals(command)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            String flaskServerUrl = "http://127.0.0.1:5000/start-module";

            // Effettua una chiamata REST al server Flask per avviare il modulo
            ResponseEntity<Map<String, Object>> responseEntity =
                restTemplate.exchange(flaskServerUrl, HttpMethod.POST, entity,
                new ParameterizedTypeReference<Map<String, Object>>() {
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
                    System.out.println("Valore intero: " + (intValue+1));
                    agricoltoreServiceImpl.getSingleAgricoltore(intValue+1);
                    Agricoltore agricoltore =
                        agricoltoreServiceImpl.getSingleAgricoltore(intValue+1);
                    // Aggiungi l'agricoltore alla lista
                    agricoltoriList.add(agricoltore.getNomeBottega());

                }
            } else {
                // Gestire il caso in cui l'oggetto non è un array
                System.err.println("idList non contiene un array di interi.");
            }

            return agricoltoriList;
        } else {
            // Comando non riconosciuto
            List<String> error = new ArrayList<>();
            error.add("Comando non riconosciuto");
            return error;
        }
    }
}

