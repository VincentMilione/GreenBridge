package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.ListCart;
import com.greenbridge.entities.Portafoglio;
import com.greenbridge.entities.IndirizzoSpedizione;
import com.greenbridge.services.CarrelloClienteService;
import com.greenbridge.services.IndirizzoSpedizioneService;
import com.greenbridge.services.OrdineService;
import com.greenbridge.services.ProdottiOrdineService;
import com.greenbridge.services.PortafoglioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CheckoutController {
    @Autowired
    private ProdottiOrdineService prodottiOrdineService;
    @Autowired
    private OrdineService ordineService;
    @Autowired
    private CarrelloClienteService carrelloClienteService;

    @Autowired
    private IndirizzoSpedizioneService indirizzoSpedizioneService;

    @Autowired
    private PortafoglioService portafoglioService;
    @PostMapping("/checkout")
    String getCheckout(@RequestParam int id, Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        ListCart carrello = (ListCart) session.getAttribute("list_cart");
        //il carrello è vuoto
        if (carrello.getListCart().isEmpty()) {
            model.addAttribute("cart", carrello.getProdottiOrdinati());
            model.addAttribute("totale", carrello.getTotale());
            return "/carrello";
        }
        //checkout totale
        if (id == 0) {
                session.setAttribute("checkout", carrello);
        } else { //checkout singolo
            ListCart listaCheckout = new ListCart(cliente);
            listaCheckout.addCart(carrello.getProdottoById(id));
            session.setAttribute("checkout", listaCheckout);
        }
        model.addAttribute("nome", cliente.getNome());
        model.addAttribute("cognome", cliente.getCognome());

        return "checkout";
    }
    public void aggiornaCarrello(HttpSession session,
                                  ListCart listaCheckout) {
        ListCart listCart = null;
        if (listaCheckout.getListCart().size() == 1) {
            listCart = (ListCart) session.getAttribute("list_cart");
            carrelloClienteService.deleteByProdotto(listaCheckout.
                    getListCart().
                    get(0).getProdotto());
            listCart.delete(
                    listaCheckout.getListCart().get(0).
                            getProdotto().getIdProdotto());
        } else {
            listCart = new ListCart(listaCheckout.getCliente());
            session.removeAttribute("list_cart");
            session.setAttribute("list_cart", listCart);
            carrelloClienteService.deleteAllByCliente(listaCheckout.
                    getCliente());
        }


    }

    void aggiornaPortafoglio(float totale, Agricoltore agricoltore) {
        int id = agricoltore.getPortafoglio().getId();
        Portafoglio portafoglio = portafoglioService.getPortafoglioById(id);
        portafoglio.editCredito("add", totale);
        portafoglioService.salvaPortafoglio(portafoglio);

    }
    @PostMapping ("/checkForm")
    ResponseEntity<String> checkForm(@RequestBody
                                     IndirizzoSpedizione indirizzoSpedizione,
                                     Model model, HttpSession session) {
        int idSpedizione = 0;
        session.setAttribute("counter", 2);
        indirizzoSpedizione.setCliente((Cliente)
                session.getAttribute("cliente"));
        IndirizzoSpedizione indirizzoSpedizione1=null;

        if (!indirizzoSpedizione.isEmpty()) {

            indirizzoSpedizione1 = indirizzoSpedizioneService.
                    saveIndirizzoSpedizione(indirizzoSpedizione);
            idSpedizione = indirizzoSpedizione1.getId();
        }
        ListCart listaCheckout = (ListCart) session.getAttribute("checkout");
        aggiornaCarrello(session, listaCheckout);

        if (listaCheckout.getListCart().size() == 1) {
            //accredito la somma
            Agricoltore agricoltore = listaCheckout.getListCart().get(0).
                    getProdotto().getAgricoltore();
            aggiornaPortafoglio(listaCheckout.getTotale(), agricoltore);

            //creo nuovo ordine
            Ordine ordine = new Ordine(listaCheckout.getTotale(),
                    "mastercard", idSpedizione, listaCheckout.getCliente(),
                    listaCheckout.getListCart().get(0).
                            getProdotto().getAgricoltore());
            Ordine newOrdine = ordineService.salvaOrdine(ordine);
            prodottiOrdineService.
                    saveAllProdottiPerOrdine(listaCheckout, newOrdine);
            listaCheckout.getListCart().remove(0);


        } else  if (listaCheckout.getListCart().size() != 0) {
                    ListCart listaAgricoltore = new ListCart(
                            listaCheckout.getCliente());
                    Agricoltore agricoltore = listaCheckout.
                            getListCart().get(0).
                            getProdotto().getAgricoltore();
                    int idAgricoltore = agricoltore.getId();
                    while (listaCheckout.getListCart().get(0).
                            getProdotto().getAgricoltore().
                            getId() == idAgricoltore) {
                        listaAgricoltore.addCart(listaCheckout.
                                getListCart().get(0));
                        listaCheckout.getListCart().remove(0);
                            if (listaCheckout.getListCart().size() == 0) {
                                break;
                            }

                    }
                    //aggiorno il portafoglio agricoltore
                    aggiornaPortafoglio(listaAgricoltore.
                            getTotale(), agricoltore);

                    //creo il nuovo ordine
                    Ordine ordine = new Ordine(listaAgricoltore.getTotale(),
                            "mastercard", idSpedizione,
                            listaAgricoltore.getCliente(),
                            listaAgricoltore.getListCart().get(0).getProdotto().
                                    getAgricoltore());
                    Ordine newOrdine = ordineService.salvaOrdine(ordine);
                    //aggiungo tutto in prodotti_ordine
                    prodottiOrdineService.
                            saveAllProdottiPerOrdine(listaAgricoltore, newOrdine);
        }
        if (listaCheckout.getListCart().size() == 0) {
            return new ResponseEntity<>("payment", HttpStatusCode.valueOf(200));

        } else {
            session.removeAttribute("checkout");
            session.setAttribute("checkout", listaCheckout);
            int i = (Integer) session.getAttribute("counter");
            session.setAttribute("counter", i--);
            return new ResponseEntity<>("checkout",
                    HttpStatusCode.valueOf(200));


        }
    }

    @GetMapping("/paymentSuccess")
    public String paymentSuccess() {
        return "paymentSuccess";
    }
}
