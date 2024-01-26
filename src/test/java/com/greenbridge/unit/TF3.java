package com.greenbridge.unit;

import com.greenbridge.GreenBridgeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreenBridgeApplication.class) //
@AutoConfigureMockMvc
public class TF3 {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAggiungiCertificatoCorretto() throws Exception{
        //Creare un file scan fittizio
        MockMultipartFile certScan = new MockMultipartFile("certScan", "Certificato.pdf", "application/pdf", "dummy content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/Agricoltori/1/aggiungiCertificato")
                .file(certScan)
                .param("certName", "The Carbon Trust Standard")
                .param("expiryDate", "2025-01-25"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Certificato aggiunto con successo"));

    }

    @Test
    public void testAggiungiCertificatoErratoCertificatoMancante() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/Agricoltori/1/aggiungiCertificato")
                        .param("certName", "The Carbon Trust Standard")
                        .param("expiryDate", "2025-01-25"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAggiungiCertificatoErratoDataNonValida() throws Exception {
        MockMultipartFile certScan = new MockMultipartFile("certScan", "Certificato.pdf", "application/pdf", "dummy content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/Agricoltori/1/aggiungiCertificato")
                        .file(certScan)
                        .param("certName", "The Carbon Trust Standard")
                        .param("expiryDate", "25-01-2025"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAggiungiCertificatoErratoDataMancante() throws Exception {
        MockMultipartFile certScan = new MockMultipartFile("certScan", "Certificato.pdf", "application/pdf", "dummy content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/Agricoltori/1/aggiungiCertificato")
                        .file(certScan)
                        .param("certName", "The Carbon Trust Standard"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAggiungiCertificatoErratoNomeMancante() throws Exception{
        MockMultipartFile certScan = new MockMultipartFile("certScan", "Certificato.pdf", "application/pdf", "dummy content".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/Agricoltori/1/aggiungiCertificato")
                .file(certScan)
                .param("expiryDate", "2025-01-25"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
