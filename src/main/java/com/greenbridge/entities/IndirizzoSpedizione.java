package com.greenbridge.entities;


import jakarta.persistence.Column;
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
public class IndirizzoSpedizione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "civico")
    private Integer civico;

    @Column(name = "via")
    private String via;



    @Column(name = "cap")
    private Integer cap;

    @Column(name = "citta")
    private String citta;

    @Column(name = "provincia")
    private String provincia;

    public IndirizzoSpedizione(Cliente cliente, Integer civico,
               String via, Integer cap, String citta, String provincia) {
        this.cliente = cliente;
        this.civico = civico;
        this.via = via;
        this.cap = cap;
        this.citta = citta;
        this.provincia = provincia;
    }

    public Boolean isEmpty() {
        if (via.isEmpty() || via.equals("")) {
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "IndirizzoSpedizione{"
                + "id=" + id
                + ", cliente=" + cliente
                + ", civico=" + civico
                + ", via='" + via + '\''
                + ", cap=" + cap
                + ", citta='" + citta + '\''
                + ", provincia='" + provincia + '\''
                + '}';
    }
}
