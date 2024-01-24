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

/**
 * Entità rappresentante la relazione tra i prodotti e gli ordini.
 * @Author Salvatore Mattiello
 */

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
    private float prezzoKg;

    /**
     * Costruttore per creare un'istanza di ProdottiOrdine.
     *
     * @param prodotto      Prodotto associato all'ordine.
     * @param ordine        Ordine a cui il prodotto è associato.
     * @param kgAcquistati  Quantità di prodotto acquistata in kg.
     * @param prezzoKg     Prezzo per kg del prodotto.
     */
    public ProdottiOrdine(Prodotto prodotto, Ordine ordine,
                  int kgAcquistati, float prezzoKg) {
        this.prodotto = prodotto;
        this.ordine = ordine;
        this.kgAcquistati = kgAcquistati;
        this.prezzoKg = prezzoKg;
    }

    /**
     * Restituisce una rappresentazione in forma di stringa dell'oggetto ProdottiOrdine.
     *
     * @return Stringa rappresentante l'oggetto ProdottiOrdine.
     */
    @Override
    public String toString() {
        return "ProdottiOrdine{"
                + "id=" + id
                + ", prodotto=" + prodotto
                + ", ordine=" + ordine
                + ", kgAcquistati=" + kgAcquistati
                + '}';
    }
}
