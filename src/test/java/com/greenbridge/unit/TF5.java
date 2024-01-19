package com.greenbridge.unit;

import com.greenbridge.GreenBridgeApplication;
import com.greenbridge.services.ProdottoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreenBridgeApplication.class)
@AutoConfigureMockMvc
public class TF5 {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdottoService prodottoService;

    @Test
    public  void testGetProductWithNameEmpty() throws Exception {
        mockMvc.perform(post("/ricerca").param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("ricerca"));
        verify(prodottoService, never()).getResult(anyString());
    }

    @Test
    public void testTextLenght() throws Exception {
        String longName = "Ciaoragazziquestoèuntestpercapiresefunzionaesperocheciochestoscrivendosupericaratteri";

        mockMvc.perform(post("/ricerca").param("name", longName))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("ricerca"));
        verify(prodottoService, never()).getResult(anyString());
    }

    @Test
    public void testInvalidName() throws Exception {
        String invalidName = "@ciao!";

        mockMvc.perform(post("/ricerca").param("name", invalidName))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("ricerca"));
        verify(prodottoService, never()).getResult(anyString());
    }

    @Test
    public void testValidName() throws Exception {
        String validName = "Apple";

        mockMvc.perform(post("/ricerca").param("name", validName))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("ricerca"));
        verify(prodottoService).getResult(anyString());
    }
}
