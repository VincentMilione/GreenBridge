package com.greenbridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    public Portafoglio(float credito) {
        this.credito = credito;
    }

    public void editCredito(String edit, float totale) {
        if (edit.equals("add")) {
            credito += totale;
        }

    }

    @Override
    public String toString() {
        return "Portafoglio{"
                + "id=" + id
                + ", credito=" + credito
                + '}';
    }
}
