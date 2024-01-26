package com.greenbridge.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Entity class representing a Certificato (certificate)
 * associated with an Agricoltore (farmer).
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Certificato {
    /**
     * Identificatore univoco del certificato.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Nome del certificato.
     */
    @Column(name = "nome")
    private String nomeCertificato;
    /**
     * Data di scadenza del certificato.
     */
    @Column(name = "data_scadenza")
    private LocalDate dataScadenzaCertificato;
    /**
     * Agricoltore a cui è associato il certificato.
     */
    @ManyToOne
    @JoinColumn(name = "id_agricoltore")
    private Agricoltore agricoltore;
    /**
     * Scansione del certificato in formato byte array.
     */
    @Lob
    private byte[] scansione;

    /**
     * Constructor for creating a Certificato with specified attributes.
     *
     * @param nomeCertificatoParam           Name of the certificate
     * @param dataScadenzaCertificatoParam   Expiry date of the certificate
     * @param agricoltoreParam               Associated farmer (Agricoltore)
     * @param scan                 Scan of the certificate
     */
    public Certificato(String nomeCertificatoParam,
                       LocalDate dataScadenzaCertificatoParam,
                       Agricoltore agricoltoreParam, byte[] scan) {
        this.nomeCertificato = nomeCertificatoParam;
        this.dataScadenzaCertificato = dataScadenzaCertificatoParam;
        this.agricoltore = agricoltoreParam;
        this.scansione = scan;
    }
    /**
     * Returns a string representation of the Certificato.
     *
     * @return String representation of the Certificato
     */
    @Override
    public String toString() {
        return "Certificato{"
                + "id=" + id
                + ", nomeCertificato='" + nomeCertificato + '\''
                + ", dataScadenzaCertificato=" + dataScadenzaCertificato
                + ", agricoltore=" + agricoltore
                + '}';
    }
}
