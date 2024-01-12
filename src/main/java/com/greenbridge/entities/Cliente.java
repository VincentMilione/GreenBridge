package com.greenbridge.entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    /** Nome del cliente. */
    private String nome;
    /** Cognome del cliente. */
    private String cognome;
    /** Email del cliente. */
    private String email;
    /** Password del cliente. */
    private String password;
    /** Data di nascita del cliente. */
    private Date dataDiNascita;

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



    public Cliente(
            String nome,
            String cognome,
            String email,
            String password,
            Date dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.dataDiNascita = dataDiNascita;
    }

}
