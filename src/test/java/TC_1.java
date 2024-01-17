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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GreenBridgeApplication.class) //
@AutoConfigureMockMvc
public class TC_1 {

    @Autowired
    private MockMvc mockMvc;


    @Mock
    private ProdottoService prodottoService;


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
                        .param("nome", "Fico")
                        .param("origine", "regno uni")
                        .param("formatoVendita", "")
                        .param("prezzoVendita", "5")
                        .param("prezzoKg", "4")
                        .param("quantitaDisp", "5")
                        .param("lotto", "11111")
                        .param("descrizione", "Bel prodottaa")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());

    }
}
