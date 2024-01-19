package com.greenbridge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Setter
@Getter
@NoArgsConstructor
public class ProdottiOrdine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name = "id_ordine")
    private Ordine ordine;


    @Column(name = "kg_acquistati")
    private int kgAcquistati;

    @Column(name = "prezzo_kg")
    private float prezzo_kg;

    public ProdottiOrdine(Prodotto prodotto, Ordine ordine,
                  int kgAcquistati, float prezzo_kg) {
        this.prodotto = prodotto;
        this.ordine = ordine;
        this.kgAcquistati = kgAcquistati;
        this.prezzo_kg = prezzo_kg;
    }

    @Override
    public String toString() {
        return "ProdottiOrdine{"
                + "id=" + id
                + ", prodotto=" + prodotto
                + ", ordine=" + ordine
                + ", kgAcquistati=" + kgAcquistati
                + '}';
    }
// Costruttori, getter e setter
}
