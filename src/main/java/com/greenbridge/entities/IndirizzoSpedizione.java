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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Entità che rappresenta un indirizzo di spedizione associato a un cliente.
 * @author Salvatore Mattiello
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class IndirizzoSpedizione {


    /** Identificatore dell'indirizzo di spedizione. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** cliente dell'indirizzo di spedizione. */
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    /** civico dell'indirizzo di spedizione. */
    @Column(name = "civico")
    private Integer civico;

    /** via dell'indirizzo di spedizione. */
    @Column(name = "via")
    private String via;

    /** cap dell'indirizzo di spedizione. */


    @Column(name = "cap")
    private Integer cap;

    /** Identificatore dell'indirizzo di sepdizione. */
    @Column(name = "citta")
    private String citta;

    /** Identificatore dell'indirizzo di sepdizione. */
    @Column(name = "provincia")
    private String provincia;

    /**
     * Costruttore per creare un'istanza di IndirizzoSpedizione.
     *
     * @param cliente    Cliente associato all'indirizzo di spedizione.
     * @param civico     Numero civico.
     * @param via        Nome della via.
     * @param cap        Codice di avviamento postale.
     * @param citta      Nome della città.
     * @param provincia  Nome della provincia.
     */
    public IndirizzoSpedizione(Cliente cliente, Integer civico,
               String via, Integer cap, String citta, String provincia) {
        this.cliente = cliente;
        this.civico = civico;
        this.via = via;
        this.cap = cap;
        this.citta = citta;
        this.provincia = provincia;
    }

    /**
     * Verifica se l'indirizzo di spedizione è vuoto.
     *
     * @return True se l'indirizzo è vuoto, altrimenti False.
     */
    public Boolean isEmpty() {
        if (via.isEmpty() || via.equals("")) {
            return true;
        }
        if (citta.isEmpty() || citta.equals("")) {
            return true;
        }
        if (provincia.isEmpty() || provincia.equals("")) {
            return true;
        }
        if (civico == 0) {
            return true;
        }
        if (cap == 0) {
            return true;
        }

        return false;
    }

    /**
     * Match tra regex e stringa.
     * @param regex validità stringa
     * @param string stringa da controllare
     * @return True se l'indirizzo  è matchato, altrimenti False.
     */
    public Boolean isMatchRegexString(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }



    /**
     * Verifica se l'indirizzo di spedizione è corretto.
     *
     * @return True se l'indirizzo non è corretto, altrimenti False.
     */
    public Boolean isNotCoorect() {
        if (!isEmpty()) {
            if (!isMatchRegexString("^[A-Za-zÀ-ù' ‘-]{1,50}$", via)) {
                System.out.println("via sbagliato");
                    return true;
            }
            if (!isMatchRegexString("^\\d{1,10}$",
                    String.valueOf(civico))) {
                System.out.println("civico sbagliato");
                return true;
            }
            if (!isMatchRegexString("^\\d{5}$",
                    String.valueOf(cap))) {
                System.out.println("cap sbagliato");
                return true;
            }
            if (!isMatchRegexString("^[A-Za-zÀ-ù'\\s-]{1,30}$", citta)) {
                System.out.println("citta sbagliato");
                return true;
            }
            if (!isMatchRegexString("^[A-Za-zÀ-ù'’-]{2}$", provincia)) {
                System.out.println("provincia sbagliato");
                return true;
            }
        }

        return false;
    }

    /**
     * Restituisce una rappresentazione in forma di
     * stringa dell'oggetto IndirizzoSpedizione.
     *
     * @return Stringa rappresentante l'oggetto IndirizzoSpedizione.
     */
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
