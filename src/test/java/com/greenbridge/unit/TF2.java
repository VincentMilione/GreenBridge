package com.greenbridge.unit;
import com.greenbridge.GreenBridgeApplication;
import com.greenbridge.controllers.RestUseController;
import com.greenbridge.entities.Agricoltore;
import com.greenbridge.repositories.AgricoltoreRepository;
import com.greenbridge.services.AgricoltoreServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void testRegistraAgricoltoreNomeMancante() throws Exception {
        Agricoltore agricoltore= new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltore);
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/RegistrazioneUtente")
                        .param("nomeBottega", "mehdibottega")
                        .param("indirizzoBottega", "via roma 3")
                        .param("email", "jhondoe+test@gmail.us")
                        .param("password", "3333333333333")
                        .param("confermaPassword", "3333333333333")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)

                );


    }



}