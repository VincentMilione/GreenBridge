package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.greenbridge.services.AgricoltoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author  elmehdi zitouni
 * Controller che gestisce le operazioni
 * relative agli agricoltori in un'applicazione web.
 */
@Controller
public class AgricoltoreController {

    /**
     * Servizio per la gestione delle
     * operazioni legate agli agricoltori.
     */

    private final AgricoltoreServiceImpl agricoltoreService;

    /**
     * Costruttore del controller.
     * @param agricoltoreServiceParam Servizio
     * per la gestione degli agricoltori.
     */
    public AgricoltoreController(AgricoltoreServiceImpl agricoltoreServiceParam) {
        this.agricoltoreService = agricoltoreServiceParam;
    }


    /**
     * Gestisce la richiesta per la visualizzazione
     * della pagina principale degli agricoltori.
     * @param model Modello per passare dati alla vista.
     * @return La vista associata alla pagina
     * principale degli agricoltori.
     */
    @GetMapping("/pageAgricoltore")
    public String homeViewAgricoltore(Model model) {
        model.addAttribute("",
                agricoltoreService.getAgricoltori());
        return "/page";
    }

    /**
     * Gestisce la richiesta per
     * la registrazione di un agricoltore.
     * @return La vista per
     * la registrazione dell'utente agricoltore.
     */
    @GetMapping("/registrazione-Agricoltore")
    public String registrazione() {
        return "RegistrazioneUtente";
    }

    /**
     * Gestisce la richiesta per
     * l'inserimento di un certificato.
     * @return La vista per l'inserimento del certificato.
     */
    @GetMapping("/inserimento-certificato")
    public String inserimentoCertificato() {
        return "InserimentoCertificato";
    }

    /**
     * Gestisce la richiesta per la visualizzazione della pagina dei certificati degli agricoltori.
     *
     * @param model Modello per passare dati alla vista.
     * @return La vista associata alla pagina principale degli agricoltori.
     */
    @GetMapping("/visualizzazione-certificati")
    public String visualizzazioneCertificati(HttpSession session, Model model) {
        Agricoltore user = (Agricoltore) session
                .getAttribute("agricoltore");
        if (user == null) {
            return "error";
        }

        model.addAttribute("certificati",
                agricoltoreService.getCertificati(user));
        return "certificati";
    }

    /**
     * Gestisce la richiesta per la modifica
     * di un utente agricoltore.
     * @param model Modello per passare dati alla vista.
     * @return La vista per la modifica dell'utente agricoltore.
     */
    @GetMapping("/modifica")
    public String modificaUtente(Model model) {
        model.addAttribute("agricoltori",
                agricoltoreService.getAgricoltori());
        return "modify";
    }

    /**
     * Gestisce la richiesta per
     * la visualizzazione della home dell'agricoltore.
     *@param session Sessione HTTP
     * per gestire lo stato dell'utente.
     * @return La vista per la home
     * dell'agricoltore o la vista di
     * login se l'agricoltore non è autenticato.
     */
    @GetMapping("/homeAgricoltore")
    public String home(HttpSession session) {
        if (session.getAttribute("agricoltore") == null) {
            return "loginAgricoltore";
        }
        return "homeAgricoltore";
    }

    /**
     * Gestisce la conferma di
     * registrazione con successo.
     * @return La vista di conferma
     * di registrazione con successo.
     */
    @GetMapping("/registratoConSuccesso")
    public String registratoConSuccesso() {
        return "RegistrazioneConSuccesso";
    }

    /**
     * Gestisce la richiesta per il
     * login dell'agricoltore.
     * @return La vista per il
     * login dell'agricoltore.
     */
    @GetMapping("/loginAgricoltore")
    public String loginAgricoltore() {
        return "loginAgricoltore";
    }

    /**
     * Gestisce la visualizzazione dei
     * dettagli dell'agricoltore.
     * @param model Modello per
     * passare dati alla vista.
     * @param session Sessione
     * HTTP per gestire lo stato dell'utente.
     * @return La vista per
     * la visualizzazione dei dettagli dell'agricoltore.
     */
    @GetMapping("/detailAgricoltore")
    public String detailAgricoltore(Model model, HttpSession session) {
        model.addAttribute("agricoltore",
                session.getAttribute("agricoltore"));
        return "homeAgricoltore";
    }
}
