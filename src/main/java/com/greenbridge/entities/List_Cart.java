package com.greenbridge.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Setter
@Getter

public class List_Cart {

    private Cliente cliente;
    List<CarrelloCliente> list_cart;
    private float totale=0;

    public List_Cart(Cliente cliente, List<CarrelloCliente> list_cart) {
        this.cliente = cliente;
        this.list_cart = list_cart;
        aggiorno_totale();
    }
    public void aggiorno_totale(){
        totale=0;
        for (CarrelloCliente itemCart : list_cart) {
            totale+=(itemCart.getProdotto().getPrezzoKg()* itemCart.getKg_richiesti());

        }
    }
    public List_Cart(Cliente cliente) {
        this.cliente = cliente;
        list_cart= new ArrayList<CarrelloCliente>();
    }
    public void addCart(Prodotto prodotto, int quantita){
        CarrelloCliente itemCart= new CarrelloCliente(cliente,prodotto,quantita);
        list_cart.add(itemCart);
        aggiorno_totale();

    }
    public boolean isPresent(Integer prodotto_id){
        for (CarrelloCliente itemCart : list_cart) {
            if (itemCart.getProdotto().getIdProdotto() == prodotto_id) {
                return true;
            }

        }
    return false;
    }
public float[] delete(Integer prodotto_id){
    Iterator<CarrelloCliente> iterator = list_cart.iterator();

    while (iterator.hasNext()) {
        CarrelloCliente carrelloCliente = iterator.next();
        if (carrelloCliente.getProdotto().getIdProdotto()==prodotto_id) {
            iterator.remove();
        }
    }
    aggiorno_totale();
    float[] response = {-1, totale};
        return response;
}
    public float[] edit_prodotto(Integer prodotto_id, String edit) {
        int quantita = 0;
        for (CarrelloCliente itemCart : list_cart) {

            if (itemCart.getProdotto().getIdProdotto() == prodotto_id) {
                if (edit.equals("add")) {
                    quantita = (itemCart.getKg_richiesti() + 1);
                    if(quantita<=itemCart.getProdotto().getQuantitaDisp()) {
                        itemCart.setKg_richiesti(quantita);

                    }else{
                        quantita--;
                    }
                } else if(edit.equals("sott")) {
                    quantita = itemCart.getKg_richiesti() - 1;
                    if(quantita>=1)
                        itemCart.setKg_richiesti(quantita);
                }else{
                    quantita++;
                }

            }

        }
        aggiorno_totale();
        float[] response = {quantita, totale};
        return response;

    }


    @Override
    public String toString() {
        return "List_Cart{" +
                "cliente=" + cliente +
                ", list_cart=" + list_cart +
                '}';
    }

}
