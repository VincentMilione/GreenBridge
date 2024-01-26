package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Certificato;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing Agricoltore entities and related operations.
 */
public interface AgricoltoreService {

    /**
     * Retrieves a list of all Agricoltore entities.
     *
     * @return List of Agricoltore entities
     */
    List<Agricoltore> getAgricoltori();

    /**
     * Saves a new Agricoltore entity.
     *
     * @param agricoltore Agricoltore entity to be saved
     * @return Saved Agricoltore entity
     */
    Agricoltore saveAgricoltore(Agricoltore agricoltore);

    /**
     * Modifies an existing Agricoltore entity.
     *
     * @param agricoltore Modified Agricoltore entity
     */
    void modificaAgricoltore(Agricoltore agricoltore);

    /**
     * Retrieves a single Agricoltore entity by ID.
     *
     * @param id ID of the Agricoltore entity to retrieve
     * @return Agricoltore entity corresponding to the provided ID
     */
    Agricoltore getSingleAgricoltore(int id);

    /**
     * Retrieves an Agricoltore entity by email.
     *
     * @param email Email of the Agricoltore to retrieve
     * @return Agricoltore entity corresponding to the provided email
     */
    Agricoltore getAgricoltoreByEmail(String email);

    /**
     * Adds a Certificato to an existing Agricoltore entity.
     *
     * @param agricoltoreId              ID of the Agricoltore to
     *                                   add the Certificato to
     * @param nomeCertificato            Name of the Certificato
     * @param dataScadenzaCertificato    Expiry date of the Certificato
     * @param scansione                  Scan of Certificato
     */
    void aggiungiCertificato(int agricoltoreId, String nomeCertificato,
                             LocalDate dataScadenzaCertificato,
                             MultipartFile scansione);

    /**
     * Restituisce una lista di certificati associati
     * all'agricoltore specificato.
     *
     * @param agricoltore L'agricoltore di cui si vogliono
     *                    recuperare i certificati.
     * @return Una lista di certificati associati all'agricoltore
     *         specificato. Se non sono presenti certificati,
     *         viene restituita una lista vuota.
     */
    List<Certificato> getCertificati(Agricoltore agricoltore);
}
