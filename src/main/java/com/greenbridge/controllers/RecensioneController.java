package com.greenbridge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecensioneController {

    @GetMapping("/recensione")
    String recensione(){
        return "recensioneProdotto";
    }
}
