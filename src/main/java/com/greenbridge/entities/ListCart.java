package com.greenbridge.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe che rappresenta il carrello di un cliente.
 * @author Salvatore Mattiello
 */
@Setter
@Getter
public class ListCart {

    /** Cliente proprietario del carrello . */
    private Cliente cliente;
    /** lista dei prodotti del carrello . */
    private List<CarrelloCliente> listCart;
    /** totale del carrello . */
    private float totale = 0;

    /**
     * Costruttore per creare un'istanza di ListCart con
     * un cliente e una lista di CarrelloCliente.
     *
     * @param cliente     Cliente associato al carrello.
     * @param list_cart   Lista di prodotti nel carrello.
     */
    public ListCart(Cliente cliente,
                    List<CarrelloCliente> list_cart) {
        this.cliente = cliente;
        this.listCart = list_cart;
        aggiornoTotale();
    }

    /**
     * Metodo per aggiornare il totale del carrello.
     */
    public void aggiornoTotale() {
        totale = 0;
        for (CarrelloCliente itemCart : listCart) {
            totale += (itemCart.getProdotto().
                    getPrezzoKg() * itemCart.getKgRichiesti());

        }
    }

    /**
     * Costruttore per creare un'istanza di ListCart con
     * un cliente e una lista vuota di CarrelloCliente.
     *
     * @param cliente Cliente associato al carrello.
     */
    public ListCart(Cliente cliente) {
        this.cliente = cliente;
        listCart = new ArrayList<CarrelloCliente>();
        totale = 0;
    }

    /**
     * Metodo per aggiungere un prodotto al carrello.
     *
     * @param prodotto Prodotto da aggiungere.
     * @param quantita Quantità del prodotto da aggiungere.
     */
    public void addCart(Prodotto prodotto, int quantita) {
        CarrelloCliente itemCart = new CarrelloCliente(cliente,
                prodotto, quantita);
        listCart.add(itemCart);
        aggiornoTotale();

    }

    /**
     * Metodo per aggiungere un oggetto CarrelloCliente al carrello.
     *
     * @param carrelloCliente Oggetto CarrelloCliente da aggiungere.
     */
    public void addCart(CarrelloCliente carrelloCliente) {

        listCart.add(carrelloCliente);
        aggiornoTotale();

    }

    /**
     * Metodo per verificare se un prodotto è presente nel carrello.
     *
     * @param idProdotto Identificativo del prodotto.
     * @return True se il prodotto è presente, altrimenti False.
     */
    public boolean isPresent(Integer idProdotto) {
        for (CarrelloCliente itemCart : listCart) {
            if (itemCart.getProdotto().getIdProdotto() == idProdotto) {
                return true;
            }

        }
    return false;
    }

    /**
     * Metodo per ottenere un oggetto CarrelloCliente
     * dato l'identificativo del prodotto.
     *
     * @param idProdotto Identificativo del prodotto.
     * @return Oggetto CarrelloCliente associato al
     * prodotto, o null se non trovato.
     */
    public CarrelloCliente getProdottoById(Integer idProdotto) {
        for (CarrelloCliente itemCart : listCart) {
            if (itemCart.getProdotto().getIdProdotto() == idProdotto) {
                return itemCart;
            }

        }
        return null;
    }

    /**
     * Metodo per eliminare un prodotto dal carrello.
     *
     * @param idProdotto Identificativo del prodotto da eliminare.
     * @return Un array di float con il primo elemento -1 e
     * il secondo elemento rappresentante il totale aggiornato.
     */
public float[] delete(Integer idProdotto) {
    Iterator<CarrelloCliente> iterator = listCart.iterator();
    while (iterator.hasNext()) {
        CarrelloCliente carrelloCliente = iterator.next();
        if (carrelloCliente.getProdotto().getIdProdotto() == idProdotto) {
            iterator.remove();
        }
    }
    aggiornoTotale();
    float[] response = {-1, totale};
        return response;
}

    /**
     * Metodo per modificare la quantità di un prodotto nel carrello.
     *
     * @param idProdtto Identificativo del prodotto da modificare.
     * @param edit       Tipo di modifica ("add" o "sott").
     * @return Un array di float con il primo elemento rappresentante
     * la nuova quantità e il secondo elemento il totale aggiornato.
     */
    public float[] editProdotto(Integer idProdtto, String edit) {
        int quantita = 0;
        for (CarrelloCliente itemCart : listCart) {

            if (itemCart.getProdotto().getIdProdotto() == idProdtto) {
                if (edit.equals("add")) {
                    quantita = (itemCart.getKgRichiesti() + 1);
                    if (quantita <= itemCart.getProdotto().
                            getQuantitaDisp()) {
                        itemCart.setKgRichiesti(quantita);

                    } else {
                        quantita--;
                    }
                } else if (edit.equals("sott")) {
                    quantita = itemCart.getKgRichiesti() - 1;
                    if (quantita >= 1) {
                        itemCart.setKgRichiesti(quantita);
                    } else {
                        quantita++;
                    }
                }
            }

        }
        aggiornoTotale();
        float[] response = {quantita, totale};
        return response;

    }

    /**
     * Metodo per ottenere una mappa di prodotti ordinati
     * per nome dell'agricoltore.
     *
     * @return Mappa di prodotti ordinati per nome dell'agricoltore.
     */
    public Map<String, List<CarrelloCliente>> getProdottiOrdinati() {
        List<CarrelloCliente> list = listCart;
        list.sort(Comparator.comparing(p -> p.getProdotto().
                getAgricoltore().getNome()));

        Map<String, List<CarrelloCliente>> prodottiByAgricoltore =
                list.stream().collect(Collectors.groupingBy(p -> p.
                        getProdotto().getAgricoltore().getNome()));
        return prodottiByAgricoltore;
    }


    /**
     * Metodo per riordinare la lista di prodotti nel carrello
     * per identificativo dell'agricoltore.
     */
    public void riordinaLista() {
        listCart.sort(Comparator.comparing(p -> p.getProdotto().
                getAgricoltore().getId()));

    }


    /**
     * Restituisce una rappresentazione in forma di
     * stringa dell'oggetto ListCart.
     *
     * @return Stringa rappresentante l'oggetto ListCart.
     */
    @Override
    public String toString() {
        return "ListCart{"
                + "cliente=" + cliente
                + ", list_cart=" + listCart
                + '}';
    }

}
