package com.greenbridge.unit;

import com.greenbridge.GreenBridgeApplication;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreService;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GreenBridgeApplication.class) //
@AutoConfigureMockMvc
public class TF6 {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AgricoltoreService agricoltoreService;

    @Test
    public void testNomeErrato() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico eterofillus eucaliptos")
                        .param("origine", "regno uni")
                        .param("formatoVendita", "3")
                        .param("prezzoVendita", "6")
                        .param("prezzoKg", "2")
                        .param("quantitaDisp", "10")
                        .param("lotto", "11111")
                        .param("descrizione", "Bel prodotto")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }

    @Test
    public void testOrigineErrata() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico")
                        .param("origine", "Regno Unito di Gran Bretagna")
                        .param("formatoVendita", "3")
                        .param("prezzoVendita", "6")
                        .param("prezzoKg", "2")
                        .param("quantitaDisp", "10")
                        .param("lotto", "11111")
                        .param("descrizione", "Bel prodottaa")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());

    }

    @Test
    public void testFormatoErrato() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico")
                        .param("origine", "Regno Unito")
                        .param("formatoVendita", "3x")
                        .param("prezzoVendita", "6")
                        .param("prezzoKg", "2")
                        .param("quantitaDisp", "10")
                        .param("lotto", "11111")
                        .param("descrizione", "Bel prodotto")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());

    }

    @Test
    public void testPrezzoVenditaErrato() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico")
                        .param("origine", "Regno Unito")
                        .param("formatoVendita", "3")
                        .param("prezzoVendita", "6x")
                        .param("prezzoKg", "2")
                        .param("quantitaDisp", "10")
                        .param("lotto", "11111")
                        .param("descrizione", "Bel prodotto")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());

    }

    @Test
    public void testPrezzoKgErrato() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico")
                        .param("origine", "Regno Unito")
                        .param("formatoVendita", "3")
                        .param("prezzoVendita", "6")
                        .param("prezzoKg", "2x")
                        .param("quantitaDisp", "10")
                        .param("lotto", "11111")
                        .param("descrizione", "Bel prodotto")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());

    }

    @Test
    public void testQuantitaDispErrata() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico")
                        .param("origine", "Regno Unito")
                        .param("formatoVendita", "3")
                        .param("prezzoVendita", "6")
                        .param("prezzoKg", "2")
                        .param("quantitaDisp", "10x")
                        .param("lotto", "11111")
                        .param("descrizione", "Bel prodotto")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());

    }

    @Test
    public void testLottoErrato() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico")
                        .param("origine", "Regno Unito")
                        .param("formatoVendita", "3")
                        .param("prezzoVendita", "6")
                        .param("prezzoKg", "2")
                        .param("quantitaDisp", "10")
                        .param("lotto", "11111ab")
                        .param("descrizione", "Bel prodotto")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());

    }

    @Test
    public void testDescrizioneErrata() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico")
                        .param("origine", "Regno Unito")
                        .param("formatoVendita", "3")
                        .param("prezzoVendita", "6")
                        .param("prezzoKg", "2")
                        .param("quantitaDisp", "10")
                        .param("lotto", "11111")
                        .param("descrizione", "Bellissima descrizione del prodotto ma purtroppo e' troppo lunga quindi non andrà a buon fine, meglio riprovare con qualcosa di piu corto")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }

    @Test
    public void testInsertCorretta() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        Agricoltore agricoltoreFittizio = new Agricoltore();
        int idAgricoltore=1;
        when(agricoltoreService.getSingleAgricoltore(idAgricoltore)).thenReturn(agricoltoreFittizio);

        // Simula la presenza di Agricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("agricoltore", agricoltoreService.getSingleAgricoltore(idAgricoltore))
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fico")
                        .param("origine", "Regno Uni")
                        .param("formatoVendita", "3")
                        .param("prezzoVendita", "6")
                        .param("prezzoKg", "2")
                        .param("quantitaDisp", "10")
                        .param("lotto", "11111")
                        .param("descrizione", "Bel prodotto")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}