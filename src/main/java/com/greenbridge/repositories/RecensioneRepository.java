package com.greenbridge.repositories;

import com.greenbridge.entities.RecensioneProdotti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/** Questa classe contente l'accesso al database ai dati relativi
 * alla recensione di un prodotto.
 * @author Michele Martino
 */
public interface RecensioneRepository extends
        JpaRepository<RecensioneProdotti, Integer> {
    /**
     * l'interfaccia consente di trovare tutte
     * le recensione legate a un prodotto
     * @param id identificativo del prodotto
     * @return Lista di recensioni associate al prodotto
     */
    List<RecensioneProdotti> findAllByIdProdotto(int id);
}
