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
import java.util.Calendar;
import java.util.Date;


/**
 * Entità che rappresenta un ordine effettuato
 * da un cliente.
 * @author Salvatore Mattiello
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Ordine {

    /** Identificatore dell'ordine. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** importo dell'ordine. */
    @Column(name = "importo")
    private float importo;

    /** datadell'ordine. */
    @Column(name = "data_ordine")
    private Date dataOrdine;

    /** Metodo pagamento dell'ordine. */
    @Column(name = "pagamento")
    private String pagamento;

    /** Indirizzo da spedire dell'ordine. */
    @Column(name = "id_indirizzo")
    private int indirizzo;

    /** cliente che ha effettuato l'ordine. */
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    /** Agricoltore proprietario dei prodotti nell'ordine. */
    @ManyToOne
    @JoinColumn(name = "id_agricoltore")
    private Agricoltore agricoltore;

    /** Stato dell'ordine. */
    @Column(name = "stato")
    private int stato;

    /**
     * Costruttore per creare un'istanza di Ordine.
     *
     * @param importo     Importo totale dell'ordine.
     * @param pagamento   Metodo di pagamento utilizzato.
     * @param indirizzo Identificativo dell'indirizzo di spedizione.
     * @param cliente     Cliente che ha effettuato l'ordine.
     * @param agricoltore Agricoltore associato all'ordine.
     */
    public Ordine(float importo,  String pagamento, int indirizzo,
                  Cliente cliente, Agricoltore agricoltore) {
        this.importo = importo;
        Calendar cal = Calendar.getInstance();

// Ottenere la data corrente
        Date dataOrdine = new Date(cal.getTimeInMillis());
        this.dataOrdine = dataOrdine;
        this.pagamento = pagamento;
        this.indirizzo = indirizzo;
        this.cliente = cliente;
        this.agricoltore = agricoltore;
        this.stato = 1;
    }

    @Override
    public String toString() {
        return "Ordine{"
                + "id=" + id
                + ", importo=" + importo
                + ", dataOrdine=" + dataOrdine
                + ", pagamento='" + pagamento + '\''
                + ", cliente=" + cliente
                + ", agricoltore=" + agricoltore
                + '}';
    }
}

