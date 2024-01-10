package com.greenbridge.entities;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String cognome;
    @Column(name = "pwd")
    private String password;
    private Date dataDiNascita;
    private String email;

    @Override
    public String toString() {
        return "Cliente{" +

                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                '}';
    }



    public Cliente(String nome, String cognome, String email, String password, Date dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.dataDiNascita = dataDiNascita;
    }

}
