package com.greenbridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private String password;

    public Agricoltore(String nome,
                       String nomeBottega,
                       String indirizzoBottega,
                       String email,
                       String password) {
        this.nome = nome;
        this.nomeBottega = nomeBottega;
        this.indirizzoBottega = indirizzoBottega;
        this.email = email;
        this.password = password;
    }


}
