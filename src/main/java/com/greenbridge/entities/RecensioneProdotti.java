package com.greenbridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecensioneProdotti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idCliente;
    private Integer idProdotto;
    private String descrizione;
    private Integer voto;

    public RecensioneProdotti(int idCliente,
                              int idProdotto,
                              String descrzione,
                              Integer voto) {
        this.idCliente = idCliente;
        this.idProdotto = idProdotto;
        this.descrizione = descrzione;
        this.voto = voto;
    }
}


