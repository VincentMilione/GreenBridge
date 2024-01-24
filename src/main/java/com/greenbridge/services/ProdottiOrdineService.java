package com.greenbridge.services;

import com.greenbridge.entities.CarrelloCliente;
import com.greenbridge.entities.ListCart;
import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.ProdottiOrdine;
import com.greenbridge.repositories.ProdottiOrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottiOrdineService {

    @Autowired
    private ProdottiOrdineRepository prodottiOrdineRepository;




    public List<ProdottiOrdine> findAllProdottiOrdine() {
        return prodottiOrdineRepository.findAll();
    }
    public List<ProdottiOrdine> findAllProdottiOrdineByOrdine(Ordine ordine) {
        return prodottiOrdineRepository.findProdottiOrdineByOrdine(ordine);
    }
    public ProdottiOrdine saveProdottiOrdine(ProdottiOrdine prodottiOrdine) {
        return prodottiOrdineRepository.save(prodottiOrdine);
    }

    public void deleteProdottiOrdineById(int id) {
        prodottiOrdineRepository.deleteById(id);
    }

    public void saveAllProdottiPerOrdine(ListCart listCart, Ordine ordine) {
        for (CarrelloCliente carrelloCliente : listCart.getListCart()) {
            ProdottiOrdine prodottiOrdine = new ProdottiOrdine(carrelloCliente.
                    getProdotto(), ordine, carrelloCliente.getKg_richiesti(),
                    carrelloCliente.getProdotto().getPrezzoKg());
            saveProdottiOrdine(prodottiOrdine);
        }
    }



    public List<ProdottiOrdine> findAllProdottiOrdineByOrdineId(Ordine ordine) {
        return prodottiOrdineRepository.findProdottiOrdineByOrdine(ordine);
    }
}
