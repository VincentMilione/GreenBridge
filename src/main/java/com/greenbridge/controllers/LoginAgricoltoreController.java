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
/**
 * Controller for handling login operations related to
 * farmers through RESTful APIs.
 */
@RestController
@RequestMapping("/api")
public class LoginAgricoltoreController {

    /**
     * Questa variabile rappresenta un'istanza del servizio
     * per gestire le operazioni relative agli agricoltori.
     */
    private final AgricoltoreServiceImpl agricoltoreService;

    /**
     * Constructor for LoginAgricoltoreController.
     *
     * @param agricoltoreServiceParam AgricoltoreServiceImpl
     *                                instance for handling
     *                                farmer-related operations
     */
    public LoginAgricoltoreController(AgricoltoreServiceImpl
                                              agricoltoreServiceParam) {
        this.agricoltoreService = agricoltoreServiceParam;
    }
    /**
     * Handles the login operation for farmers.
     *
     * @param agricoltore Farmer object containing login credentials
     * @param session      HttpSession for storing farmer
     *                     information after successful login
     * @return ResponseEntity containing a success or error message
     */
    @PostMapping("/loginAgricoltore")
    public ResponseEntity<String> saveAgricoltore(@RequestBody
                  Agricoltore agricoltore, HttpSession session) {
        Agricoltore a = agricoltoreService.
                getAgricoltoreByEmail(agricoltore.getEmail());
        if (a != null && agricoltore.getPassword().
                compareTo(a.getPassword()) == 0) {

            session.setAttribute("agricoltore", a);
               return new ResponseEntity<>("ok", HttpStatus.OK);
        }
         return new ResponseEntity<>("not ok",
                 HttpStatus.FORBIDDEN);

    }

}
