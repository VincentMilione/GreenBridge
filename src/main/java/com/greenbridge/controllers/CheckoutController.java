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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller per gestire il processo di checkout.
 * @author salvatore mattiello
 */
@Controller
public class CheckoutController {
    /**
     * Servizio per la gestione delle.
     * operazioni legate ai prodotti ordinati.
     */
    @Autowired
    private ProdottiOrdineService prodottiOrdineService;
    /**
     * magic number STATUS GIUST0.
     */
    private static final int STATUS_OK = 200;
    /**
     * Servizio per la gestione delle
     * operazioni legate agli ordini.
     */
    @Autowired
    private OrdineService ordineService;
    /**
     * Servizio per la gestione delle
     * operazioni legate al carrello.
     */
    @Autowired
    private CarrelloClienteService carrelloClienteService;

    /**
     * Servizio per la gestione delle
     * operazioni legate agli indirizzi.
     */
    @Autowired
    private IndirizzoSpedizioneService indirizzoSpedizioneService;

    /**
     * Servizio per la gestione delle
     * operazioni legate ai portafogli
     * degli agricoltori.
     */
    @Autowired
    private PortafoglioService portafoglioService;

    /**
     * Metodo per gestire la richiesta POST a "/checkout".
     *
     * @param id      Identificativo del prodotto per il checkout singolo.
     * @param model   Modello per aggiungere attributi per la visualizzazione.
     * @param session Sessione HTTP per mantenere lo stato del checkout.
     * @return La vista associata al checkout.
     */
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

    /**
     * Metodo per aggiornare il carrello dopo il checkout.
     *
     * @param session       Sessione HTTP per mantenere lo stato del carrello.
     * @param listaCheckout Lista di prodotti per il checkout.
     */
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

    /**
     * Metodo per aggiornare il portafoglio dell'agricoltore dopo il checkout.
     *
     * @param totale       Totale del checkout.
     * @param agricoltore  Agricoltore associato al prodotto.
     */
    void aggiornaPortafoglio(float totale, Agricoltore agricoltore) {
        int id = agricoltore.getPortafoglio().getId();
        Portafoglio portafoglio = portafoglioService.getPortafoglioById(id);
        portafoglio.editCredito("add", totale);
        portafoglioService.salvaPortafoglio(portafoglio);

    }

    /**
     * permette di associare alle eccezioni
     * degli HttpStatus.
     * @param e eccezione generata
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        System.out.println("Handling DataIntegrityViolationException: "
                + e.getMessage());
    }

    /**
     * Metodo per gestire la richiesta POST
     * a "/checkForm" per verificare i dati
     * del modulo di spedizione.
     * Gestisce anche la creazione di ordine e prodotti_ordine.
     *
     * @param indirizzoSpedizione Dati del modulo di spedizione.
     * @param model               Modello per aggiungere attributi
     *                           per la visualizzazione.
     * @param session             Sessione HTTP per mantenere
     *                           lo stato del checkout.
     * @return Risposta HTTP con esito del controllo.
     */
    @PostMapping ("/checkForm")
    ResponseEntity<String> checkForm(@RequestBody
                     IndirizzoSpedizione indirizzoSpedizione,
                     Model model, HttpSession session) throws Exception {
        if (indirizzoSpedizione == null) {
            throw new Exception("indirizzoSpedizione non inviato");
        }
        if (indirizzoSpedizione.isNotCoorect()) {
            throw new Exception("indirizzoSpedizione non corretto");
        }
        if (session.getAttribute("checkout") == null) {
            throw new Exception("nessun checkout da fare");
        }
        if (session.getAttribute("list_cart") == null) {
            throw new Exception("nessun elemento nel carello");
        }
        if (session.getAttribute("cliente") == null) {
            throw new Exception("cliente non loggato");
        }


        int idSpedizione = 0;
        session.setAttribute("counter", 2);
        indirizzoSpedizione.setCliente((Cliente)
                session.getAttribute("cliente"));
        IndirizzoSpedizione indirizzoSpedizione1 = null;

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
            return new ResponseEntity<>("payment",
                    HttpStatusCode.valueOf(STATUS_OK));

        } else {
            session.removeAttribute("checkout");
            session.setAttribute("checkout", listaCheckout);
            int i = (Integer) session.getAttribute("counter");
            session.setAttribute("counter", i--);
            return new ResponseEntity<>("checkout",
                    HttpStatusCode.valueOf(STATUS_OK));


        }
    }

    /**
     * Metodo per gestire la richiesta GET a "/paymentSuccess".
     *
     * @return La vista di conferma del pagamento.
     */
    @GetMapping("/paymentSuccess")
    public String paymentSuccess() {
        return "paymentSuccess";
    }
}