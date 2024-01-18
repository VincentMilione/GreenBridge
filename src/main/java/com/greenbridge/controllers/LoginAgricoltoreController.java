package com.greenbridge.controllers;
import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class LoginAgricoltoreController {

    private final AgricoltoreServiceImpl agricoltoreService;
    public LoginAgricoltoreController(AgricoltoreServiceImpl agricoltoreService) {
        this.agricoltoreService = agricoltoreService;
    }
    @PostMapping("/loginAgricoltore")
    public ResponseEntity<String> saveAgricoltore(@RequestBody Agricoltore agricoltore, HttpSession session){

        Agricoltore a = agricoltoreService.getAgricoltoreByEmail(agricoltore.getEmail());

            if(a!=null && agricoltore.getPassword().compareTo(a.getPassword()) ==0)
               {
                    session.setAttribute("agricoltore",a);
                    return new ResponseEntity<>("ok", HttpStatus.OK);
               }

            return new ResponseEntity<>("not ok",HttpStatus.FORBIDDEN);

    }

}
