package com.greenbridge.controllers;

import com.greenbridge.services.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Collections;
import java.util.Map;

@Controller
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @GetMapping("/get_data_from_db")
    public ResponseEntity<List<String>> getDataFromDb() {
        try {
            // Implementa la logica per ottenere i dati dal database
            List<String> dataFromDb = ChatbotService.getDataFromDb();

            return ResponseEntity.ok(dataFromDb);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.getMessage()));
        }
    }

    @PostMapping("/send_command_to_flask")
    public ResponseEntity<String> sendCommandToFlask(@RequestBody Map<String, String> command) {
        try {
            // Invia il comando al modulo Flask attraverso il servizio CommandService
            chatbotService.sendCommandToFlask(command);

            return ResponseEntity.ok("Comando inviato con successo al modulo Flask");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
