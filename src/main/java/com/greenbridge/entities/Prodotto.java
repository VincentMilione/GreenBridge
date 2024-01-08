package com.greenbridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Getter
@Setter
@Entity
public class Prodotto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProdotto;
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
    private int idAgricoltore;


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

