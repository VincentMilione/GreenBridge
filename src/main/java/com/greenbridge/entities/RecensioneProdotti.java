package com.greenbridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Questa classe rappresenta la recensione di un prodotto.
 * @author Michele Martino
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecensioneProdotti {
    /**
     * Identificativo univoco per una recensione prodotto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Identificativo del cliente che ha fatto la recensione.
     */
    private Integer idCliente;
    /**
     * Identificativo del prodotto a cui è associata la recensione.
     */
    private Integer idProdotto;
    /**
     * Descrizione della recensione prodotto.
     */
    private String descrizione;
    /**
     * Voto della recensione prodotto.
     */
    private Integer voto;

    /**
     * Metodo costruttore della recensione
     * che esclude l'id.
     * @param idCliente identifica il cliente che sottoscrive la recensione
     * @param idProdotto identifica il prodotto a cui
     *                   è destinata la recensione
     * @param descrizione descrizione della recensione
     * @param voto voto della recensione
     */
    public RecensioneProdotti(int idCliente,
                              int idProdotto,
                              String descrizione,
                              Integer voto) {
        this.idCliente = idCliente;
        this.idProdotto = idProdotto;
        this.descrizione = descrizione;
        this.voto = voto;
    }
}


