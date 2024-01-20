package com.greenbridge.unit;

import com.github.dockerjava.api.exception.InternalServerErrorException;
import com.greenbridge.GreenBridgeApplication;
import com.greenbridge.controllers.RecensioneController;
import com.greenbridge.entities.*;
import com.greenbridge.services.RecensioneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GreenBridgeApplication.class) //
@AutoConfigureMockMvc
public class TF5 {
    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private RecensioneController recensioneController;

    @Mock
    private RecensioneService recensioneService;
    @Mock
    HttpSession mockSession;

    @Test
    public void testSaveRecensioneCorretto() throws Exception {
    
        mockSession = mock(HttpSession.class);
        Cliente cliente = new Cliente();
        cliente.setId(1);
        when(mockSession.getAttribute("cliente")).thenReturn(cliente);

        List<ProdottiOrdine> prodottiOrdineList = new ArrayList<>();
        ProdottiOrdine p = new ProdottiOrdine();
        p.setId(1);
        p.setOrdine(new Ordine());
        p.setKgAcquistati(1);
        p.setProdotto(new Prodotto());
        p.getProdotto().setAgricoltore(new Agricoltore());
        p.getProdotto().getAgricoltore().setNome("Antonio");
        prodottiOrdineList.add(p);

        RecensioneProdotti recensione = new RecensioneProdotti();
        recensione.setVoto(5);
        recensione.setDescrizione("Bel prodotto!");

        mockMvc.perform(post("/saveRecensione")
                        .sessionAttr("cliente", cliente)
                        .sessionAttr("indiceRecensioni", 0)
                        .sessionAttr("prodottiOrdine", prodottiOrdineList)
                        .flashAttr("rec", recensione))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void testSaveRecensioneSenzaVoto() throws Exception {

        mockSession = mock(HttpSession.class);
        Cliente cliente = new Cliente();
        cliente.setId(1);
        when(mockSession.getAttribute("cliente")).thenReturn(cliente);

        List<ProdottiOrdine> prodottiOrdineList = new ArrayList<>();
        ProdottiOrdine p = new ProdottiOrdine();
        p.setId(1);
        p.setOrdine(new Ordine());
        p.setKgAcquistati(1);
        p.setProdotto(new Prodotto());
        p.getProdotto().setAgricoltore(new Agricoltore());
        p.getProdotto().getAgricoltore().setNome("Antonio");
        prodottiOrdineList.add(p);

        RecensioneProdotti recensione = new RecensioneProdotti();
        recensione.setVoto(0);
        recensione.setDescrizione("Bel prodotto!");

        mockMvc.perform(post("/saveRecensione")
                        .sessionAttr("cliente", cliente)
                        .sessionAttr("indiceRecensioni", 0)
                        .sessionAttr("prodottiOrdine", prodottiOrdineList)
                        .flashAttr("rec", recensione))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testSaveRecensioneDescrizioneLunga() throws Exception {

        mockSession = mock(HttpSession.class);
        Cliente cliente = new Cliente();
        cliente.setId(1);
        when(mockSession.getAttribute("cliente")).thenReturn(cliente);

        List<ProdottiOrdine> prodottiOrdineList = new ArrayList<>();
        ProdottiOrdine p = new ProdottiOrdine();
        p.setId(1);
        p.setOrdine(new Ordine());
        p.setKgAcquistati(1);
        p.setProdotto(new Prodotto());
        p.getProdotto().setAgricoltore(new Agricoltore());
        p.getProdotto().getAgricoltore().setNome("Antonio");
        prodottiOrdineList.add(p);

        RecensioneProdotti recensione = new RecensioneProdotti();
        recensione.setVoto(5);
        recensione.setDescrizione("La banana è un frutto eccezionale che offre non solo un gusto delizioso, dolce e cremoso, ma anche numerosi benefici per la salute. La sua consistenza morbida e la facilità con cui può essere consumata la rendono una scelta ideale per uno spuntino veloce o come ingrediente in varie ricette. Ricca di potassio, vitamine e fibre, la banana favorisce la salute cardiaca, regola la pressione sanguigna e migliora la digestione. La sua versatilità la rende adatta a essere utilizzata in frullati, dessert, pancake e molto altro. Inoltre, il suo involucro naturale la rende una scelta ecologica e pratica. La banana è un frutto irresistibile che soddisfa il palato e promuove il benessere complessivo.");

        mockMvc.perform(post("/saveRecensione")
                        .sessionAttr("cliente", cliente)
                        .sessionAttr("indiceRecensioni", 0)
                        .sessionAttr("prodottiOrdine", prodottiOrdineList)
                        .flashAttr("rec", recensione))
                .andExpect(status().isNotAcceptable());
    }
}