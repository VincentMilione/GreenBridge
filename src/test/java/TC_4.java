import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenbridge.GreenBridgeApplication;
import com.greenbridge.controllers.ProdottoController;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GreenBridgeApplication.class) //
@AutoConfigureMockMvc
public class TC_4 {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInsertCorretta() throws Exception {

        // Creare un file immagine fittizio
        MockMultipartFile immagineFile = new MockMultipartFile("immagineFile", "fico.jpg", "image/jpeg", "dummy content".getBytes());

        // Simula la presenza di idAgricoltore nella sessione
        HttpSession session = mockMvc.perform(MockMvcRequestBuilders.get("/dummy-url")
                .sessionAttr("idAgricoltore", 1)
        ).andReturn().getRequest().getSession();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/addProdotto")
                        .file(immagineFile)
                        .param("nome", "Fica")
                        .param("origine", "regno unito")
                        .param("formatoVendita", "5")
                        .param("prezzoVendita", "")
                        .param("prezzoKg", "4")
                        .param("quantitaDisp", "5")
                        .param("lotto", "11111")
                        .param("descrizione", "")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
