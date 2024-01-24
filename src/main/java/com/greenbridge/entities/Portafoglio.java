package com.greenbridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entità che rappresenta il portafoglio associato a un cliente.
 * @Author Salvatore Mattiello.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Portafoglio {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    /** Nome del cliente. */
    float credito;

    /**
     * Costruttore per creare un'istanza di Portafoglio con il credito iniziale.
     *
     * @param credito Credito iniziale nel portafoglio.
     */
    public Portafoglio(float credito) {
        this.credito = credito;
    }

    /**
     * Metodo per modificare il credito nel portafoglio.
     *
     * @param edit   Tipo di modifica ("add" per aggiungere).
     * @param totale Importo da aggiungere al credito.
     */
    public void editCredito(String edit, float totale) {
        if (edit.equals("add")) {
            credito += totale;
        }

    }

    /**
     * Restituisce una rappresentazione in forma di stringa dell'oggetto Portafoglio.
     *
     * @return Stringa rappresentante l'oggetto Portafoglio.
     */
    @Override
    public String toString() {
        return "Portafoglio{"
                + "id=" + id
                + ", credito=" + credito
                + '}';
    }
}
