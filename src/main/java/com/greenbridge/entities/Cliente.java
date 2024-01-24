package com.greenbridge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

/**
 * Questa classe rappresenta il cliente.
 * @author Michele Martino
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    /** Identificativo univoco del cliente. */
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

    /**
     * Il metodo consente di visualizzare un cliente come stringa.
     * @return stringa con i valori degli attributi del cliente
     */
    @Override
    public String toString() {
        return "Cliente{"
            + ", id='" + id + '\''
            + ", nome='" + nome + '\''
            + ", cognome='" + cognome + '\''
            + ", email='" + email + '\''
            + ", password='" + password + '\''
            + ", dataNascita=" + dataNascita
            + '}';
    }

    /**
     * Costruttore di Cliente che esclude l'id.
     * @param nome nome del cliente
     * @param cognome cognome del cliente
     * @param email email del cliente
     * @param password password del cliente
     * @param dataNascita data di nascita del cliente
     */
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
