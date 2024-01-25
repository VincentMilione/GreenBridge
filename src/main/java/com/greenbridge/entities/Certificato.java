package com.greenbridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/**
 * Entity class representing a Certificato (certificate) associated with an Agricoltore (farmer).
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Certificato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeCertificato;
    private LocalDate dataScadenzaCertificato;
    @ManyToOne(fetch = FetchType.LAZY)
    private Agricoltore agricoltore;
    /**
     * Constructor for creating a Certificato with specified attributes.
     *
     * @param nomeCertificato           Name of the certificate
     * @param dataScadenzaCertificato   Expiry date of the certificate
     * @param agricoltore               Associated farmer (Agricoltore)
     */
    public Certificato(String nomeCertificato, LocalDate dataScadenzaCertificato, Agricoltore agricoltore) {
        this.nomeCertificato = nomeCertificato;
        this.dataScadenzaCertificato = dataScadenzaCertificato;
        this.agricoltore = agricoltore;
    }
    /**
     * Returns a string representation of the Certificato.
     *
     * @return String representation of the Certificato
     */
    @Override
    public String toString() {
        return "Certificato{" +
                "id=" + id +
                ", nomeCertificato='" + nomeCertificato + '\'' +
                ", dataScadenzaCertificato=" + dataScadenzaCertificato +
                ", agricoltore=" + agricoltore +
                '}';
    }
}
