package com.greenbridge.controllers;
import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Portafoglio;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.PortafoglioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    private PortafoglioService portafoglioService;

    @PostMapping("/getSaldo")
    public float getSaldo(HttpSession session){
        Agricoltore agricoltore= (Agricoltore) session.getAttribute("agricoltore");
       if((agricoltore)!=null){
           Portafoglio totale=portafoglioService.getPortafoglioById(agricoltore.getPortafoglio().getId());
           return totale.getCredito();
       }
       return 0;
    }
}

