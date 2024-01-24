package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Certificato;

import java.time.LocalDate;
import java.util.List;

public interface AgricoltoreService {
    List<Agricoltore> getAgricoltori();
    Agricoltore saveAgricoltore(Agricoltore agricoltore);
    void modificaAgricoltore(Agricoltore agricoltore);
    Agricoltore getSingleAgricoltore(int id);
    Agricoltore getAgricoltoreByEmail(String email);

    // Add these methods to AgricoltoreService
    void aggiungiCertificato(int agricoltoreId, String nomeCertificato, LocalDate dataScadenzaCertificato);

    public List<Certificato> getAgricoltoreByCertificatoNome(Agricoltore agricoltore);
}
