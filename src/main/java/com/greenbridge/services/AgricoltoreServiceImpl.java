package com.greenbridge.services;
import com.greenbridge.entities.Agricoltore;

import com.greenbridge.entities.Certificato;
import com.greenbridge.repositories.AgricoltoreRepository;

import com.greenbridge.repositories.CertificatoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * Implementation of the AgricoltoreService interface for managing Agricoltore entities and related operations.
 */
@Service
public class AgricoltoreServiceImpl implements AgricoltoreService {

    private final AgricoltoreRepository agricoltoreRepository;
    private final CertificatoRepository certificatoRepository;
    /**
     * Constructor for AgricoltoreServiceImpl.
     *
     * @param agricoltoreRepository   Repository for handling Agricoltore entities
     * @param certificatoRepository   Repository for handling Certificato entities
     */
    public AgricoltoreServiceImpl(AgricoltoreRepository agricoltoreRepository, CertificatoRepository certificatoRepository, CertificatoRepository certificatoRepository1) {
        this.agricoltoreRepository = agricoltoreRepository;
        this.certificatoRepository = certificatoRepository1;
    }

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
    public void aggiungiCertificato(int agricoltoreId, String nomeCertificato, LocalDate dataScadenzaCertificato) {
        Agricoltore agricoltore = getSingleAgricoltore(agricoltoreId);
        agricoltoreRepository.save(agricoltore);
    }

    /**
     * Retrieves a list of Certificato entities associated with a specific Agricoltore.
     *
     * @param agricoltore Agricoltore entity for which to retrieve Certificato entities
     * @return List of Certificato entities associated with the provided Agricoltore
     */
    @Override
    public List<Certificato> getAgricoltoreByCertificatoNome(Agricoltore agricoltore) {
        return certificatoRepository.findByAgricoltore(agricoltore);
    }



}









