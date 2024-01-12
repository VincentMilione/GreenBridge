import com.greenbridge.controllers.RestUseController;
import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
class RestUseControllerTest {
    @Mock
    private AgricoltoreServiceImpl agricoltoreService;
    @InjectMocks
    private RestUseController restUseController;



    @Test
    void getAllAgricoltoriTest() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        List<Agricoltore> mockAgricoltoriList = Arrays.asList(
                new Agricoltore("mehdi", "mehdiBottega","via roma 23","xhbouchra3@gmailcom","mehdi323"),
                new Agricoltore("vincent", "vincentBottega","via napoli2","vincent2@info.it","vinc432"),
                new Agricoltore("daniele", "danieleBottega","via salerno22","vincent2@info.it","vinc432"),
                new Agricoltore("mauro", "mauroBottega","via giuseppe 22","vincent2@info.it","vinc432"),
                new Agricoltore("michele", "micheleBottega","via roma22","vincent2@info.it","vinc432")
        );
        //Quando viene chiamata il metodo getAgricoltori() sul nostro oggetto agricoltoreService
        // allora viene ristituito mockAgricoltoriList

        when(agricoltoreService.getAgricoltori()).thenReturn(mockAgricoltoriList);
        // Invoca il metodo getALLAgricoltori sul nostro oggetto restUseController

        //e assegna il risultato ad una variabile di tipo List
        List<Agricoltore> result = restUseController.getAllAgricoltori();

        // verifica se è stato chiamato il metodo getAgricoltori sull'oggetto agricoltoreService
        verify(agricoltoreService).getAgricoltori();

        // verifica se la lista di risultati ottenuta dalla chiamata del metodo getAllAgricoltori
        //ha una dimensione di 5
        assertEquals(5, result.size());
    }

}
