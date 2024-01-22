
package com.greenbridge.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarrelloCliente {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;


     private int kg_richiesti;

    @Override
    public String toString() {
        return "Cart{"
                + ", cliente=" + cliente
                + ", prodotto=" + prodotto
                + ", quantita=" + kg_richiesti
                + '}';
    }

    public CarrelloCliente(Cliente cliente, Prodotto prodotto,
                           int kg_richiesti) {
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.kg_richiesti = kg_richiesti;
    }
}