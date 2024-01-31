package com.greenbridge.repositories;

import com.greenbridge.entities.CarrelloCliente;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository per l'entità CarrelloCliente.
 * @author Salvatore Mattiello
 */
public interface CarrelloClienteRepository
        extends JpaRepository<CarrelloCliente, Integer> {

                /**
                 * Trova tutti gli elementi
                 * del carrello associati a un determinato Cliente.
                 *
                 * @param cliente Il cliente associato
                 *               ai carrelli da cercare.
                 * @return Una lista di carrelli
                 * associati al cliente specificato.
                 */
                List<CarrelloCliente> findByCliente(Cliente cliente);

                /**
                 * Elimina gli elementi del carrello
                 * associati a un determinato Prodotto.
                 *
                 * @param prodotto Il prodotto
                 *           associato ai carrelli da eliminare.
                 */
                void deleteByProdotto(Prodotto prodotto);

                /**
                 * Elimina tutti gli elementi
                 * nel carrello associati a un determinato Cliente.
                 *
                 * @param cliente Il cliente associato
                 *                ai carrelli da eliminare.
                 */
                void deleteByCliente(Cliente cliente);



        }
