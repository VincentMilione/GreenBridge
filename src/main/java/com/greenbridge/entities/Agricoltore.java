package com.greenbridge.entities;

import jakarta.persistence.*;
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
    @Column(name="pwd")
    private String password;

    @OneToMany(mappedBy = "agricoltore")
    List<Prodotto> prodotti;
}
