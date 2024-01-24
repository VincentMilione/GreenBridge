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


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Prodotto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idProdotto;

    private String nome;
    private String origine;

    @Lob
    private byte[] immagine;

    @Transient
    private MultipartFile immagineFile;

    @Transient
    private String immagineBase64; // Immagine codificata in base64

    private float formatoVendita;
    private float prezzoKg;
    private float prezzoVendita;
    private float quantitaDisp;
    private int lotto;
    private String descrizione;
    private boolean acquistabile;
    @ManyToOne
    @JoinColumn(name = "id_agricoltore")
    private Agricoltore agricoltore;

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

    public void setImmagine(byte[] immagine) {
        this.immagine = immagine;
        if (immagine != null) {
            this.immagineBase64 = Base64.getEncoder().encodeToString(immagine);
        }
    }


    public void setImmagineBase64(String immagineBase64) {
        this.immagineBase64 = immagineBase64;

        if (immagineBase64 != null) {
            this.immagine = Base64.getDecoder().decode(immagineBase64);
        }
    }

}