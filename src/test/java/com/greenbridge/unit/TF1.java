package com.greenbridge.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenbridge.GreenBridgeApplication;
import com.greenbridge.entities.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreenBridgeApplication.class) //
@AutoConfigureMockMvc
public class TF1 {
    @Autowired
    MockMvc mockMvc;


     @org.junit.Test
    public void testCheckFormCorretto() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        IndirizzoSpedizione indirizzoSpedizione = new IndirizzoSpedizione(cliente,37,"Croce Rossa",80047,"San Giuseppe Vesuviano","NA");
       ListCart listCart = new ListCart(cliente);
        Portafoglio portafoglio= new Portafoglio();
        portafoglio.setId(1);

       Agricoltore agricoltore = new Agricoltore();
       agricoltore.setId(1);
       agricoltore.setNome("Mario Rossi");
       agricoltore.setPortafoglio(portafoglio);

        Prodotto prodotto = new Prodotto();
       prodotto.setIdProdotto(1);
       prodotto.setAgricoltore(agricoltore);
        ObjectMapper objectMapper = new ObjectMapper();
        String indirizzoSpedizioneJson = objectMapper.writeValueAsString(indirizzoSpedizione);

        CarrelloCliente carrelloCliente = new CarrelloCliente(cliente,prodotto,2);
        listCart.addCart(carrelloCliente);


        mockMvc.perform(post("/checkForm")
                        .sessionAttr("cliente", cliente)
                        .sessionAttr("checkout", listCart)
                        .sessionAttr("list_cart",listCart)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(indirizzoSpedizioneJson))
                .andExpect(status().isOk());
    }

     @org.junit.Test
    public void testCheckFormCheckoutNull() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        IndirizzoSpedizione indirizzoSpedizione = new IndirizzoSpedizione(cliente,37,"Croce Rossa",75,"San Giuseppe Vesuviano","NA");
        ListCart listCart = new ListCart(cliente);
        Portafoglio portafoglio= new Portafoglio();
        portafoglio.setId(1);

        Agricoltore agricoltore = new Agricoltore();
        agricoltore.setId(1);
        agricoltore.setNome("Mario Rossi");
        agricoltore.setPortafoglio(portafoglio);

        Prodotto prodotto = new Prodotto();
        prodotto.setIdProdotto(1);
        prodotto.setAgricoltore(agricoltore);
        ObjectMapper objectMapper = new ObjectMapper();
        String indirizzoSpedizioneJson = objectMapper.writeValueAsString(indirizzoSpedizione);

        CarrelloCliente carrelloCliente = new CarrelloCliente(cliente,prodotto,2);
        listCart.addCart(carrelloCliente);


        mockMvc.perform(post("/checkForm")
                        .sessionAttr("cliente", cliente)
                        .sessionAttr("list_cart",listCart)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(indirizzoSpedizioneJson))
                .andExpect(status().isNotAcceptable());
    }

     @org.junit.Test
    public void testCheckFormCarrelloNull() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        IndirizzoSpedizione indirizzoSpedizione = new IndirizzoSpedizione(cliente,37,"Croce Rossa",75,"San Giuseppe Vesuviano","NA");
        ListCart listCart = new ListCart(cliente);
        Portafoglio portafoglio= new Portafoglio();
        portafoglio.setId(1);

        Agricoltore agricoltore = new Agricoltore();
        agricoltore.setId(1);
        agricoltore.setNome("Mario Rossi");
        agricoltore.setPortafoglio(portafoglio);

        Prodotto prodotto = new Prodotto();
        prodotto.setIdProdotto(1);
        prodotto.setAgricoltore(agricoltore);
        ObjectMapper objectMapper = new ObjectMapper();
        String indirizzoSpedizioneJson = objectMapper.writeValueAsString(indirizzoSpedizione);

        CarrelloCliente carrelloCliente = new CarrelloCliente(cliente,prodotto,2);
        listCart.addCart(carrelloCliente);


        mockMvc.perform(post("/checkForm")
                        .sessionAttr("cliente", cliente)
                        .sessionAttr("checkout",listCart)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(indirizzoSpedizioneJson))
                .andExpect(status().isNotAcceptable());
    }

     @org.junit.Test
    public void testCheckFormClienteNull() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        IndirizzoSpedizione indirizzoSpedizione = new IndirizzoSpedizione(cliente,37,"Croce Rossa",75,"San Giuseppe Vesuviano","NA");
        ListCart listCart = new ListCart(cliente);
        Portafoglio portafoglio= new Portafoglio();
        portafoglio.setId(1);

        Agricoltore agricoltore = new Agricoltore();
        agricoltore.setId(1);
        agricoltore.setNome("Mario Rossi");
        agricoltore.setPortafoglio(portafoglio);

        Prodotto prodotto = new Prodotto();
        prodotto.setIdProdotto(1);
        prodotto.setAgricoltore(agricoltore);
        ObjectMapper objectMapper = new ObjectMapper();
        String indirizzoSpedizioneJson = objectMapper.writeValueAsString(indirizzoSpedizione);

        CarrelloCliente carrelloCliente = new CarrelloCliente(cliente,prodotto,2);
        listCart.addCart(carrelloCliente);


        mockMvc.perform(post("/checkForm")
                        .sessionAttr("list_cart", listCart)
                        .sessionAttr("checkout",listCart)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(indirizzoSpedizioneJson))
                .andExpect(status().isNotAcceptable());
    }

     @org.junit.Test
    public void testCheckFormIndirizzoSpedizioneNull() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        IndirizzoSpedizione indirizzoSpedizione = new IndirizzoSpedizione(cliente,37,"Croce Rossa",75,"San Giuseppe Vesuviano","NA");
        ListCart listCart = new ListCart(cliente);
        Portafoglio portafoglio= new Portafoglio();
        portafoglio.setId(1);

        Agricoltore agricoltore = new Agricoltore();
        agricoltore.setId(1);
        agricoltore.setNome("Mario Rossi");
        agricoltore.setPortafoglio(portafoglio);

        Prodotto prodotto = new Prodotto();
        prodotto.setIdProdotto(1);
        prodotto.setAgricoltore(agricoltore);
        ObjectMapper objectMapper = new ObjectMapper();
        String indirizzoSpedizioneJson = objectMapper.writeValueAsString(indirizzoSpedizione);

        CarrelloCliente carrelloCliente = new CarrelloCliente(cliente,prodotto,2);
        listCart.addCart(carrelloCliente);


        mockMvc.perform(post("/checkForm")
                        .sessionAttr("cliente", cliente)
                        .sessionAttr("list_cart", listCart)
                        .sessionAttr("checkout",listCart))
                .andExpect(status().isNotAcceptable());
    }


}