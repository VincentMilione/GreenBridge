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
 * Implementation of the AgricoltoreService interface for managing Agricoltore entities and related operations.
 */
@Service
public class AgricoltoreServiceImpl implements AgricoltoreService {

    @Autowired
    private CertificatoRepository certificatoRepository;
    @Autowired
    private AgricoltoreRepository agricoltoreRepository;
    @Autowired
    private PortafoglioRepository portafoglioRepository;

    /**
     * Retrieves a list of all Agricoltore entities.
     *
     * @return List of Agricoltore entities
     */
    @Override
    public List<Agricoltore> getAgricoltori() {
        return agricoltoreRepository.findAll();
    }

    /**
     * Saves a new Agricoltore entity.
     *
     * @param agricoltore Agricoltore entity to be saved
     * @return Saved Agricoltore entity
     * @throws RuntimeException if the Agricoltore with the same ID already exists
     */
    @Override
    public Agricoltore saveAgricoltore(Agricoltore agricoltore) {
        if (agricoltoreRepository.existsById(agricoltore.getId()))
            throw new RuntimeException("User already exists");

        Portafoglio portafoglio = new Portafoglio();
        Portafoglio loadedWallet = portafoglioRepository.save(portafoglio);
        agricoltore.setPortafoglio(loadedWallet);

        return agricoltoreRepository.save(agricoltore);
    }

    /**
     * Modifies an existing Agricoltore entity.
     *
     * @param agricoltore Modified Agricoltore entity
     * @throws RuntimeException if the Agricoltore with the specified ID does not exist
     */
    @Override
    public void modificaAgricoltore(Agricoltore agricoltore) {
        if (!agricoltoreRepository.existsById(agricoltore.getId()))
            throw new RuntimeException("User does not exist");
        agricoltoreRepository.save(agricoltore);
    }

    /**
     * Retrieves a single Agricoltore entity by ID.
     *
     * @param id ID of the Agricoltore entity to retrieve
     * @return Agricoltore entity corresponding to the provided ID, or null if not found
     */
    @Override
    public Agricoltore getSingleAgricoltore(int id) {
        Optional<Agricoltore> agricoltoreOptional = agricoltoreRepository.findById(id);
        // Se l'agricoltore non è presente, restituisci null
        return agricoltoreOptional.orElse(null);
    }

    /**
     * Retrieves an Agricoltore entity by email.
     *
     * @param email Email of the Agricoltore to retrieve
     * @return Agricoltore entity corresponding to the provided email
     */
    @Override
    public Agricoltore getAgricoltoreByEmail(String email) {
        return agricoltoreRepository.findByEmail(email);
    }

    /**
     * Adds a Certificato to an existing Agricoltore entity.
     *
     * @param agricoltoreId              ID of the Agricoltore to add the Certificato to
     * @param nomeCertificato            Name of the Certificato
     * @param dataScadenzaCertificato    Expiry date of the Certificato
     */
    @Override
    public void aggiungiCertificato(int agricoltoreId, String nomeCertificato,
                                    LocalDate dataScadenzaCertificato, MultipartFile scansione) {
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
     * Retrieves the list of Certificato associated to an existing Agricoltore entity.
     *
     * @param agricoltore   Agricoltore to retrieve the list of certificates
     */
    @Override
    public List<Certificato> getCertificati(Agricoltore agricoltore) {
        return certificatoRepository
                .findByAgricoltore(agricoltore);
    }
}
