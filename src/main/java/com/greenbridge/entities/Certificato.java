package com.greenbridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Certificato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeCertificato;
    private LocalDate dataScadenzaCertificato;
    @ManyToOne(fetch = FetchType.LAZY)
    private Agricoltore agricoltore;

    public Certificato(String nomeCertificato, LocalDate dataScadenzaCertificato, Agricoltore agricoltore) {
        this.nomeCertificato = nomeCertificato;
        this.dataScadenzaCertificato = dataScadenzaCertificato;
        this.agricoltore = agricoltore;
    }

    @Override
    public String toString() {
        return "Certificato{" +
                "id=" + id +
                ", nomeCertificato='" + nomeCertificato + '\'' +
                ", dataScadenzaCertificato=" + dataScadenzaCertificato +
                ", agricoltore=" + agricoltore +
                '}';
    }
}
