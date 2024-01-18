package com.greenbridge.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ChatbotService {

    // Implementa la logica per ottenere i dati dal database
    public static List<String> getDataFromDb() {
        // ...

        // Restituisci i dati dal database
        return Arrays.asList("Dato1", "Dato2", "Dato3");
    }

    public void sendCommandToFlask(Map<String, String> command) {
        // Implementa la logica per inviare il comando al modulo Flask
        // Puoi utilizzare RestTemplate, Feign, o qualsiasi altra libreria per
        // effettuare richieste HTTP al modulo Flask
    }
}
