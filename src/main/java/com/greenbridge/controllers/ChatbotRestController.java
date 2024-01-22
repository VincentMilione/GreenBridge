package com.greenbridge.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ChatbotRestController {
    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/executeCommand")
    public ResponseEntity<String> executeCommand(@RequestBody String command) {
        System.out.println(command);
        // Controllo sul comando ricevuto
        if ("{\"command\":\"/start\"}".equals(command)) {
            eseguiOperazioneStart();
            return ResponseEntity.ok("Operazione avviata con successo per il comando: " + command);
        } else {
            // Comando non riconosciuto
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comando non riconosciuto: " + command);
        }
    }

    private String eseguiOperazioneStart() {
        // Implementa qui l'operazione specifica per il comando "/start"
        // Ad esempio, puoi avviare un processo, eseguire un'azione, ecc.
        System.out.println("Eseguendo operazione specifica per il comando /start");
        String jsonData = "{\"key\": \"value\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);

        String flaskServerUrl = "http://127.0.0.1:5000/start-module";

        String response = restTemplate.exchange(
                        flaskServerUrl, HttpMethod.POST, entity, String.class).
                getBody();


        return "Request sent to Flask server. Response: " + response;
    }

    @PostMapping ("/sendDataToFlask")
    public String sendDataToFlask() {
        String jsonData = "{\"key\": \"value\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);

        String flaskServerUrl = "http://127.0.0.1:5000/start-module";

        String response = restTemplate.exchange(
                        flaskServerUrl, HttpMethod.POST, entity, String.class).
                getBody();


        return "Request sent to Flask server. Response: " + response;
    }

    @PostMapping("/ricevi-dati")
    public String riceviDatiDaFlask(@RequestBody String dati) {
        // Logica di elaborazione dei dati ricevuti dal server Flask
        System.out.println("Dati ricevuti da Flask: " + dati);

        return "Risposta al server Flask";
    }
}