package com.greenbridge.controllers;

import com.greenbridge.entities.CarrelloCliente;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.ListCart;
import com.greenbridge.services.CarrelloClienteService;
import com.greenbridge.services.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

/** Questa classe gestisce le operazione di autenticazione nel sistema.*/

@RestController
@RequestMapping("/api")
public class LoginController {
    /**
     * Attributo che permette di utilizzare
     * i servizi disponibili per prodotti legati
     * ad un cliente.
     */
    @Autowired
    private ClienteService clienteService;
    /**
     * Attributo che permette di utilizzare
     * i servizi disponibili per prodotti legati
     * al carrello.
     */
    @Autowired
    private CarrelloClienteService carrelloClienteService;


    /**
     * Questo metodo autentifica un utente cliente.
     * @param cliente cliente di cui si vuole effettuare
     *                l'autenticazione nel sistema
     * @param session gestisce i dati del cliente autenticato
     * @return indica l'esito del processo di login
     * */
    @PostMapping("/loginCliente")
    public ResponseEntity<String> saveCliente(
            @RequestBody Cliente cliente,
            HttpSession session) {
        Cliente c = clienteService.getClienteByEmail(cliente.getEmail());
        System.out.println("sto comparando " + c + "e" + cliente.getPassword());
        if (c != null
                && cliente.getPassword().compareTo(c.getPassword()) == 0) {
            session.setAttribute("cliente", c);
            List<CarrelloCliente> lista =
                    carrelloClienteService.getByClientId(c);
            System.out.println("il suo carrello è :" + lista);
            if (lista == null) {
                lista = new ArrayList<CarrelloCliente>();
            }
            ListCart listCart = new ListCart(c, lista);
            session.setAttribute("list_cart", listCart);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("notok", HttpStatus.FORBIDDEN);
    }

    /**Questo metodo effettua il logout di un utente cliente.
     * @param session contiene il cliente di cui fare il logout
     * @return view verso cui reindirizzare gli utenti dopo il logout
     * */
    @GetMapping("/logoutCliente")
    public RedirectView logoutCliente(HttpSession session) {
        session.removeAttribute("cliente");
        ListCart listCart = (ListCart) session.getAttribute("list_cart");
        for (CarrelloCliente itemCart : listCart.getListCart()) {
            System.out.println(itemCart);
            carrelloClienteService.save(itemCart);
        }
        session.removeAttribute("list_cart");

        return new RedirectView("../");
    }

}
