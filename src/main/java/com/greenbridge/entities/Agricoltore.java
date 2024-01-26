package com.greenbridge.entities;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity class representing an Agricultore (farmer) in the system.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Agricoltore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** Identificatore univoco dell'agricoltore. */
    private int id;

    /** Nome dell'agricoltore. */
    private String nome;

    /** Nome della bottega dell'agricoltore. */
    private String nomeBottega;

    /** Indirizzo della bottega dell'agricoltore. */
    private String indirizzoBottega;

    /** Indirizzo email dell'agricoltore. */
    private String email;

    /** Password dell'agricoltore. */
    @Column(name = "pwd")
    private String password;

    /** Portafoglio associato all'agricoltore. */
    @OneToOne
    @JoinColumn(name = "id_portafoglio")
    private Portafoglio portafoglio;

    /** Lista di prodotti dell'agricoltore. */
    @OneToMany(mappedBy = "agricoltore")
    private List<Prodotto> prodotti;

    /** Lista di certificati dell'agricoltore con persistenza automatica. */
    @OneToMany(mappedBy = "agricoltore", cascade = CascadeType.PERSIST)
    private List<Certificato> certificati;

}
