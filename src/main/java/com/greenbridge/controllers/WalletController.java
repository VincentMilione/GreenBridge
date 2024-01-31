package com.greenbridge.controllers;
import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Portafoglio;
import com.greenbridge.services.PortafoglioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Classe controller per la gestione della visualizzazione del portafoglio.
 * Autore: Mauro, Salvatore.
 */
@RestController
public class WalletController {

    /**
     * Service per il portafoglio.
     */
    @Autowired
    private PortafoglioService portafoglioService;

    /**
     * Gestisce il recupero del saldo per ogni agricoltore.
     *
     * @param session Oggetto HttpSession per
     *                recuperare l'agricoltore autenticato.
     * @return Il saldo attuale (credito) del
     *                portafoglio dell'agricoltore.
     */
    @PostMapping("/getSaldo")
    public float getSaldo(final HttpSession session) {
        Agricoltore agricoltore = (Agricoltore)
                session.getAttribute("agricoltore");
        if (agricoltore != null) {
            Portafoglio totale =
                    portafoglioService.getPortafoglioById(
                            agricoltore.getPortafoglio().getId());
            return totale.getCredito();
        }
        return 0;
    }
}
