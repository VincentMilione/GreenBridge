package com.greenbridge.controllers;



import com.greenbridge.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProdottoController{
    @Autowired
    private ProdottoService prodottoService;


   @GetMapping("/")
   public String getHome(){
        return "pages/user/home";
    }

}

