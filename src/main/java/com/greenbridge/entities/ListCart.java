package com.greenbridge.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter

public class ListCart {

    private Cliente cliente;
    List<CarrelloCliente> listCart;
    private float totale = 0;

    public ListCart(Cliente cliente, List<CarrelloCliente> list_cart) {
        this.cliente = cliente;
        this.listCart = list_cart;
        aggiornoTotale();
    }
    public void aggiornoTotale() {
        totale = 0;
        for (CarrelloCliente itemCart : listCart) {
            totale += (itemCart.getProdotto().
                    getPrezzoKg() * itemCart.getKg_richiesti());

        }
    }
    public ListCart(Cliente cliente) {
        this.cliente = cliente;
        listCart = new ArrayList<CarrelloCliente>();
        totale = 0;
    }
    public void addCart(Prodotto prodotto, int quantita) {
        CarrelloCliente itemCart = new CarrelloCliente(cliente,
                prodotto, quantita);
        listCart.add(itemCart);
        aggiornoTotale();

    }
    public void addCart(CarrelloCliente carrelloCliente) {

        listCart.add(carrelloCliente);
        aggiornoTotale();

    }
    public boolean isPresent(Integer idProdotto) {
        for (CarrelloCliente itemCart : listCart) {
            if (itemCart.getProdotto().getIdProdotto() == idProdotto) {
                return true;
            }

        }
    return false;
    }
    public CarrelloCliente getProdottoById(Integer idProdotto) {
        for (CarrelloCliente itemCart : listCart) {
            if (itemCart.getProdotto().getIdProdotto() == idProdotto) {
                return itemCart;
            }

        }
        return null;
    }
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
    public float[] editProdotto(Integer idProdtto, String edit) {
        int quantita = 0;
        for (CarrelloCliente itemCart : listCart) {

            if (itemCart.getProdotto().getIdProdotto() == idProdtto) {
                if (edit.equals("add")) {
                    quantita = (itemCart.getKg_richiesti() + 1);
                    if (quantita <= itemCart.getProdotto().getQuantitaDisp()) {
                        itemCart.setKg_richiesti(quantita);

                    } else {
                        quantita--;
                    }
                } else if (edit.equals("sott")) {
                    quantita = itemCart.getKg_richiesti() - 1;
                    if (quantita >= 1) {
                        itemCart.setKg_richiesti(quantita);
                    }
                    else {
                        quantita++;
                    }
                }
            }

        }
        aggiornoTotale();
        float[] response = {quantita, totale};
        return response;

    }

    public Map<String, List<CarrelloCliente>> getProdottiOrdinati() {
        List<CarrelloCliente> list = listCart;
        list.sort(Comparator.comparing(p -> p.getProdotto().
                getAgricoltore().getNome()));

        Map<String, List<CarrelloCliente>> prodottiByAgricoltore =
                list.stream().collect(Collectors.groupingBy(p -> p.
                        getProdotto().getAgricoltore().getNome()));
        return prodottiByAgricoltore;
    }

    public void riordinaLista() {
        listCart.sort(Comparator.comparing(p -> p.getProdotto().
                getAgricoltore().getId()));

    }


    @Override
    public String toString() {
        return "ListCart{"
                + "cliente=" + cliente
                + ", list_cart=" + listCart
                + '}';
    }

}
