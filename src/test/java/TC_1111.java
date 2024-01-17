import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;


import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;

public class TC_1111 {

    private WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    @Test
    public void testNomeErrato() {

        driver.get("http://localhost:8080/formInserimento");


        WebElement nomeInput = driver.findElement(By.id("nome"));
        WebElement origineInput = driver.findElement(By.id("origine"));
        WebElement immagineFileInput = driver.findElement(By.id("immagineFile"));
        WebElement formatoVenditaInput = driver.findElement(By.id("formatoVendita"));
        WebElement prezzoVenditaInput = driver.findElement(By.id("prezzoVendita"));
        WebElement prezzoKgInput = driver.findElement(By.id("prezzoKg"));
        WebElement quantitaDispInput = driver.findElement(By.id("quantitaDisp"));
        WebElement lottoInput = driver.findElement(By.id("lotto"));
        WebElement descrizioneInput = driver.findElement(By.id("descrizione"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


        nomeInput.sendKeys("Fico artacarpus heterophyllus");
        origineInput.sendKeys("Regno Unito");
        immagineFileInput.sendKeys("C:\\Users\\mauro\\Desktop\\fico.jpg");
        formatoVenditaInput.clear();
        formatoVenditaInput.sendKeys("2");
        prezzoVenditaInput.clear();
        prezzoVenditaInput.sendKeys("6");
        prezzoKgInput.clear();
        prezzoKgInput.sendKeys("3");
        quantitaDispInput.clear();
        quantitaDispInput.sendKeys("10");
        lottoInput.clear();
        lottoInput.sendKeys("41241");
        descrizioneInput.sendKeys("Ottime fiche del regno unito");

        submitButton.click();

        Boolean isElementValid = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return document.getElementById('nome').validity.valid");

        assertFalse("Il campo 'nome' è valido, inserimento errato", isElementValid);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}