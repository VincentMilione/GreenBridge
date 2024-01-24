package com.greenbridge.entities;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

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
    @OneToMany(mappedBy = "agricoltore", fetch = FetchType.LAZY)
    private List<Certificato> certificati;


}
