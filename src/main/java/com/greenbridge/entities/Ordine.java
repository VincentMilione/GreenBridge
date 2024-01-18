package com.greenbridge.entities;


import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "importo")
    private float importo;

    @Column(name = "data_ordine")
    private Date dataOrdine;

    @Column(name = "pagamento")
    private String pagamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_agricoltore")
    private Agricoltore agricoltore;

    public Ordine(float importo,  String pagamento, Cliente cliente, Agricoltore agricoltore) {
        this.importo = importo;
        Calendar cal = Calendar.getInstance();

// Ottenere la data corrente
        Date dataOrdine = new Date(cal.getTimeInMillis());
        this.dataOrdine = dataOrdine;
        this.pagamento = pagamento;
        this.cliente = cliente;
        this.agricoltore = agricoltore;
    }





}
