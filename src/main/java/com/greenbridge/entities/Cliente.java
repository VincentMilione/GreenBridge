package com.greenbridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;


import java.sql.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



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
    @Column(name = "pwd")
    private String password;
    /** Data di nascita del cliente. */
    private Date dataNascita;

    @Override
    public String toString() {
        return "Cliente{"
                + ", nome='" + nome + '\''
                + ", cognome='" + cognome + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", dataNascita=" + dataNascita
                + '}';
    }



    public Cliente(
            String nome,
            String cognome,
            String email,
            String password,
            Date dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.dataNascita = dataNascita;
    }

}
