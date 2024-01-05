package com.greenbridge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@Entity
public class Prodotto {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private int kgDisponibili;
    private String origine;
    private int lotto;
    private String descrizione;
    private float prezzo;

    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", kgDisponibili=" + kgDisponibili +
                ", origine='" + origine + '\'' +
                ", lotto=" + lotto +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }

    public Prodotto(String nome, int kg_disponibili, String descrizione, int lotto, String origine, float prezzo) {
        this.nome = nome;
        this.kgDisponibili = kg_disponibili;
        this.descrizione = descrizione;
        this.lotto = lotto;
        this.origine = origine;
        this.prezzo = prezzo;
    }
}
