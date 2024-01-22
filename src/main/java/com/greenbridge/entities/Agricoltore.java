package com.greenbridge.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Agricoltore {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String nomeBottega;
    private String indirizzoBottega;
    private String email;
    @Column(name = "pwd")
    private String password;

    @OneToOne
    @JoinColumn(name = "id_portafoglio")
    private Portafoglio portafoglio;

    @OneToMany(mappedBy = "agricoltore")
    List<Prodotto> prodotti;
}
