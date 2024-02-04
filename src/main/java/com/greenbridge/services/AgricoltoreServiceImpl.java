package com.greenbridge.services;
import com.greenbridge.entities.Agricoltore;

import com.greenbridge.entities.Certificato;
import com.greenbridge.entities.Portafoglio;
import com.greenbridge.repositories.AgricoltoreRepository;

import com.greenbridge.repositories.CertificatoRepository;
import com.greenbridge.repositories.PortafoglioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * Implementazione dell'interfaccia
 * AgricoltoreService per la gestione
 * delle entità di Agricoltore e
 * delle operazioni correlate.
 */
@Service
public class AgricoltoreServiceImpl implements AgricoltoreService {

    /**
     * Repository per gestire
     * le operazioni CRUD sui certificati.
     */
    @Autowired
    private CertificatoRepository certificatoRepository;
    /**
     * Repository per gestire
     * le operazioni CRUD sugli agricoltori.
     */
    @Autowired
    private AgricoltoreRepository agricoltoreRepository;
    /**
     * Repository per gestire le operazioni CRUD sui portafogli.
     */
    @Autowired
    private PortafoglioRepository portafoglioRepository;

    /**
     * Recupera una lista di tutte
     * le entità di Agricoltore.
     *
     * @return Lista delle entità di Agricoltore
     */
    @Override
    public List<Agricoltore> getAgricoltori() {
        return agricoltoreRepository.findAll();
    }

    /**
     * Salva una nuova entità di Agricoltore.
     *
     * @param agricoltore Entità di Agricoltore da salvare
     * @return Entità di Agricoltore appena salvata
     * @throws RuntimeException se esiste già un Agricoltore
     *                           con lo stesso ID
     */
    @Override
    public Agricoltore saveAgricoltore(Agricoltore agricoltore) {
        if (agricoltoreRepository.existsById(agricoltore.getId())) {
            throw new RuntimeException("User already exists");
        }

        Portafoglio portafoglio = new Portafoglio();
        Portafoglio loadedWallet = portafoglioRepository.save(portafoglio);
        agricoltore.setPortafoglio(loadedWallet);

        return agricoltoreRepository.save(agricoltore);
    }

    /**
     * Modifica un'entità di Agricoltore esistente.
     *
     * @param agricoltore Entità di Agricoltore modificata
     * @throws RuntimeException se non esiste un Agricoltore
     * con l'ID specificato
     */
    @Override
    public void modificaAgricoltore(Agricoltore agricoltore) {
        if (!agricoltoreRepository.existsById(agricoltore.getId())) {
            throw new RuntimeException("User does not exist");
        }
        agricoltoreRepository.save(agricoltore);
    }

    /**
     * Recupera una singola entità di Agricoltore tramite ID.
     *
     * @param id ID dell'entità di Agricoltore da recuperare
     * @return Entità di Agricoltore corrispondente all'ID fornito,
     *         o null se non trovato
     */
    @Override
    public Agricoltore getSingleAgricoltore(int id) {
        Optional<Agricoltore> agricoltoreOptional =
                agricoltoreRepository.findById(id);
        // Se l'agricoltore non è presente, restituisci null
        return agricoltoreOptional.orElse(null);
    }

    /**
     * Recupera un'entità di Agricoltore tramite email.
     *
     * @param email Email dell'Agricoltore da recuperare
     * @return Entità di Agricoltore
     * corrispondente all'email fornita
     */
    @Override
    public Agricoltore getAgricoltoreByEmail(String email) {
        return agricoltoreRepository.findByEmail(email);
    }

    /**
     * Aggiunge un Certificato a un'entità di Agricoltore esistente.
     *
     * @param agricoltoreId              ID dell'Agricoltore a cui
     *                                   aggiungere il Certificato
     * @param nomeCertificato            Nome del Certificato
     * @param dataScadenzaCertificato    Data di scadenza del Certificato
     */
    @Override
    public void aggiungiCertificato(int agricoltoreId, String nomeCertificato,
                                    LocalDate dataScadenzaCertificato,
                                    MultipartFile scansione) {
        Agricoltore agricoltore = getSingleAgricoltore(agricoltoreId);

        try {
            Certificato certificato = new Certificato(nomeCertificato,
                    dataScadenzaCertificato, agricoltore, scansione.getBytes());
            certificato.setAgricoltore(agricoltore);
            certificatoRepository.save(certificato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Recupera la lista di Certificati associati
     * a un'entità di Agricoltore esistente.
     *
     * @param agricoltore   Agricoltore per recuperare
     *                      la lista dei certificati
     */
    @Override
    public List<Certificato> getCertificati(Agricoltore agricoltore) {
        return certificatoRepository
                .findByAgricoltore(agricoltore);
    }
}
