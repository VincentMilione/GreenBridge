package com.greenbridge.unit;
import com.greenbridge.GreenBridgeApplication;
import com.greenbridge.controllers.RestUseController;
import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreServiceImpl;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.mapping.Array;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreenBridgeApplication.class)
@AutoConfigureMockMvc
public class TF2 {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgricoltoreServiceImpl agricoltoreService;
    private RestUseController restUseController;

    @Test
    public void testRegistrazione() throws Exception {
        mockMvc.perform(get("/registrazione-Agricoltore"))
                .andExpect(status().isOk())
                .andExpect(view().name("RegistrazioneUtente"));
        verify(agricoltoreService, never()).getAgricoltori();

    }

    @Test
    public void testModificaUtente() throws Exception {
        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;

        when(agricoltoreService.getAgricoltori()).thenReturn(Arrays.asList(new Agricoltore(), new Agricoltore()));

        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();
        // Esegui la richiesta e verifiche
        mockMvc.perform(get("/modifica"))
                .andExpect(status().isOk())
                .andExpect(view().name("modify"))
                .andExpect(model().attributeExists("agricoltori"));

        // Verifica che il metodo getAgricoltori sia stato chiamato esattamente una volta
        verify(agricoltoreService, times(1)).getAgricoltori();

        // Altro stile di asserzioni con AssertJ
        assertThat(mockMvc).isNotNull();
        assertThat(agricoltoreService).isNotNull();
        assertThat(agricoltoreService).isNotNull();
    }
    @Test
    public void testRegistratoConSuccesso() throws Exception {
        mockMvc.perform(get("/registratoConSuccesso"))
                .andExpect(status().isOk())
                .andExpect(view().name("RegistrazioneConSuccesso"));
    }


}