package com.greenbridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Base64;

/**
 * Classe che rappresenta un prodotto nel sistema.
 * Autore: Mauro
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Prodotto {

    /**
     * Identificatore unico del prodotto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idProdotto;

    /**
     * Nome del prodotto.
     */
    private String nome;

    /**
     * Origine del prodotto.
     */
    private String origine;

    /**
     * Immagine del prodotto in formato byte[].
     */
    @Lob
    private byte[] immagine;

    /**
     * Immagine del prodotto come file MultipartFile (transiente).
     */
    @Transient
    private MultipartFile immagineFile;

    /**
     * Immagine del prodotto in formato Base64 (transiente).
     */
    @Transient
    private String immagineBase64; // Immagine codificata in base64

    /**
     * Formato di vendita del prodotto.
     */
    private float formatoVendita;

    /**
     * Prezzo al kg del prodotto.
     */
    private float prezzoKg;

    /**
     * Prezzo di vendita del prodotto.
     */
    private float prezzoVendita;

    /**
     * Quantità disponibile del prodotto.
     */
    private float quantitaDisp;

    /**
     * Lotto del prodotto.
     */
    private int lotto;

    /**
     * Descrizione del prodotto.
     */
    private String descrizione;

    /**
     * Indica se il prodotto è acquistabile.
     */
    private boolean acquistabile;

    /**
     * Agricoltore associato al prodotto.
     */
    @ManyToOne
    @JoinColumn(name = "id_agricoltore")
    private Agricoltore agricoltore;

    /**
     * Override del metodo toString.
     *
     * @return una stringa rappresentante l'oggetto Prodotto
     */
    @Override
    public String toString() {
        return "Prodotto{"
                + "idProdotto=" + idProdotto
                + ", nome='" + nome + '\''
                + ", origine='" + origine + '\''
                + ", immagine=" + Arrays.toString(immagine)
                + ", immagineFile=" + immagineFile
                + ", immagineBase64='" + immagineBase64 + '\''
                + ", formatoVendita=" + formatoVendita
                + ", prezzoKg=" + prezzoKg
                + ", prezzoVendita=" + prezzoVendita
                + ", quantitaDisp=" + quantitaDisp
                + ", lotto=" + lotto
                + ", descrizione='" + descrizione + '\''
                + ", acquistabile=" + acquistabile
                + ", agricoltore=" + agricoltore.getNome()
                + '}';
    }

    /**
     * Metodo setter per l'immagine in formato byte[].
     *
     * @param immagine l'immagine da impostare
     */
    public void setImmagine(final byte[] immagine) {
        this.immagine = immagine;
        if (immagine != null) {
            this.immagineBase64 = Base64.getEncoder().encodeToString(immagine);
        }
    }

    /**
     * Metodo setter per l'immagine in formato Base64.
     *
     * @param immagineBase64 l'immagine codificata in Base64 da impostare
     */
    public void setImmagineBase64(final String immagineBase64) {
        this.immagineBase64 = immagineBase64;

        if (immagineBase64 != null) {
            this.immagine = Base64.getDecoder().decode(immagineBase64);
        }
    }
}
