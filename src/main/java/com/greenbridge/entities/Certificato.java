package com.greenbridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
/**
 * Entity class representing a Certificato (certificate) associated with an Agricoltore (farmer).
 */
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Certificato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nome")
    private String nomeCertificato;
    @Column(name="data_scadenza")
    private LocalDate dataScadenzaCertificato;
    @ManyToOne
    @JoinColumn(name="id_agricoltore")
    private Agricoltore agricoltore;
    @Lob
    private byte[] scansione;

    /**
     * Constructor for creating a Certificato with specified attributes.
     *
     * @param nomeCertificato           Name of the certificate
     * @param dataScadenzaCertificato   Expiry date of the certificate
     * @param agricoltore               Associated farmer (Agricoltore)
     */
    public Certificato(String nomeCertificato, LocalDate dataScadenzaCertificato, Agricoltore agricoltore, byte[] scansione) {
        this.nomeCertificato = nomeCertificato;
        this.dataScadenzaCertificato=dataScadenzaCertificato;
        this.agricoltore = agricoltore;
        this.scansione = scansione;
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
