
package com.greenbridge.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entità che rappresenta un elemento nel carrello di un cliente.
 * @author Salvatore Mattiello
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarrelloCliente {

    /** Identificatore del CarrelloCliente. */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    /** cliente del CarrelloCliente. */
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    /** prodotto del CarrelloCliente. */
    @ManyToOne
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;

    /** kgRichiesti del CarrelloCliente. */
    @Column(name = "kg_richiesti")
     private int kgRichiesti;

    /**
     * Restituisce una rappresentazione in forma di stringa dell'oggetto CarrelloCliente.
     *
     * @return Stringa rappresentante l'oggetto CarrelloCliente.
     */
    @Override
    public String toString() {
        return "Cart{"
                + ", cliente=" + cliente
                + ", prodotto=" + prodotto
                + ", quantita=" + kgRichiesti
                + '}';
    }

    /**
     * Costruttore per creare un'istanza di CarrelloCliente.
     *
     * @param cliente     Cliente associato al carrello.
     * @param prodotto    Prodotto associato al carrello.
     * @param kgRichiesti Quantità del prodotto richiesta nel carrello.
     */
    public CarrelloCliente(Cliente cliente, Prodotto prodotto,
                           int kgRichiesti) {
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.kgRichiesti = kgRichiesti;
    }
}