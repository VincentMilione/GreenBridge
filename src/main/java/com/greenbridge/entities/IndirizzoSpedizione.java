package com.greenbridge.entities;

import javax.persistence.*;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.sql.Date;

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

    @Column(name = "paese")
    private String paese;

    public IndirizzoSpedizione(Cliente cliente, Integer civico, String via, Integer cap, String citta, String paese) {
        this.cliente = cliente;
        this.civico = civico;
        this.via = via;
        this.cap = cap;
        this.citta = citta;
        this.paese = paese;
    }

    public Boolean isEmpty(){
        if(via.isEmpty() || via.equals(""))
            return true;
        return false;
    }
    @Override
    public String toString() {
        return "IndirizzoSpedizione{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", civico=" + civico +
                ", via='" + via + '\'' +
                ", cap=" + cap +
                ", citta='" + citta + '\'' +
                ", paese='" + paese + '\'' +
                '}';
    }
}
