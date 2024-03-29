/**
 * Classe di test per il form di inserimento nel sistema.
 * Autore: Mauro
 */
package com.greenbridge.system;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static java.lang.Thread.sleep;
import static org.springframework.test.util.AssertionErrors.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TF6 {
  private WebDriver driver;

  /**
   * Metodo di setup eseguito prima di ogni test.
   * @throws InterruptedException se il thread viene interrotto
   */
  @Before
  public void setUp() throws InterruptedException {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("http://localhost:8080/loginAgricoltore");
    driver.manage().window().setSize(new Dimension(1430, 816));
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("costantinocilea@example.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("NhNvJYfA#92HZ@zX");
    driver.findElement(By.cssSelector("button:nth-child(3)")).click();
    sleep(2000);
  }

  /**
   * Test per il campo 'nome' con inserimento errato.
   */
 @org.junit.Test
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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico artacarpus heterophyllus");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
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
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test per il campo 'origine' con inserimento errato.
   */
 @org.junit.Test
  public void testOrigineErrato() {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito Di Gran Bretagna");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
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
            .executeScript("return document.getElementById('origine').validity.valid");

    assertFalse("Il campo 'origine' non è valido, inserimento errato", isElementValid);
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test per il campo 'immagine' con inserimento errato.
   * @throws InterruptedException se il thread viene interrotto
   */
 @org.junit.Test
  public void testImmagineErrata() throws InterruptedException {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
   immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/prova.docx");
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
            .executeScript("return document.getElementById('immagineFile').validity.valid");

    assertFalse("Il campo 'immagine' non è valido, inserimento errato", isElementValid);
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  /**
   * Test per il campo 'formatoVendita' con inserimento errato.
   */
 @org.junit.Test
  public void testFormatoErrato() {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
    formatoVenditaInput.clear();
    formatoVenditaInput.sendKeys("222");
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
            .executeScript("return document.getElementById('formatoVendita').validity.valid");

    assertFalse("Il campo 'formatoVendita' è valido, inserimento errato", isElementValid);
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  /**
   * Test per il campo 'prezzoVendita' con inserimento errato.
   */
 @org.junit.Test
  public void testPrezzoErrato() {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
    formatoVenditaInput.clear();
    formatoVenditaInput.sendKeys("2");
    prezzoVenditaInput.clear();
    prezzoVenditaInput.sendKeys("666");
    prezzoKgInput.clear();
    prezzoKgInput.sendKeys("3");
    quantitaDispInput.clear();
    quantitaDispInput.sendKeys("10");
    lottoInput.clear();
    lottoInput.sendKeys("41241");
    descrizioneInput.sendKeys("Ottime fiche del regno unito");

    submitButton.click();

    Boolean isElementValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return document.getElementById('prezzoVendita').validity.valid");

    assertFalse("Il campo 'nome' è valido, inserimento errato", isElementValid);
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test per il campo 'prezzoKg' con inserimento errato.
   */
 @org.junit.Test
  public void testPrezzoKgErrato() {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
    formatoVenditaInput.clear();
    formatoVenditaInput.sendKeys("2");
    prezzoVenditaInput.clear();
    prezzoVenditaInput.sendKeys("6");
    prezzoKgInput.clear();
    prezzoKgInput.sendKeys("333");
    quantitaDispInput.clear();
    quantitaDispInput.sendKeys("10");
    lottoInput.clear();
    lottoInput.sendKeys("41241");
    descrizioneInput.sendKeys("Ottime fiche del regno unito");

    submitButton.click();

    Boolean isElementValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return document.getElementById('prezzoKg').validity.valid");

    assertFalse("Il campo 'nome' è valido, inserimento errato", isElementValid);
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test per il campo 'quantitaDisp' con inserimento errato.
   */
 @org.junit.Test
  public void testQuantitaDispErrato() {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
    formatoVenditaInput.clear();
    formatoVenditaInput.sendKeys("2");
    prezzoVenditaInput.clear();
    prezzoVenditaInput.sendKeys("6");
    prezzoKgInput.clear();
    prezzoKgInput.sendKeys("3");
    quantitaDispInput.clear();
    quantitaDispInput.sendKeys("1000");
    lottoInput.clear();
    lottoInput.sendKeys("41241");
    descrizioneInput.sendKeys("Ottime fiche del regno unito");

    submitButton.click();

    Boolean isElementValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return document.getElementById('quantitaDisp').validity.valid");

    assertFalse("Il campo 'nome' è valido, inserimento errato", isElementValid);
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test per il campo 'lotto' con inserimento errato.
   */
 @org.junit.Test
  public void testLottoErrato() {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
    formatoVenditaInput.clear();
    formatoVenditaInput.sendKeys("2");
    prezzoVenditaInput.clear();
    prezzoVenditaInput.sendKeys("6");
    prezzoKgInput.clear();
    prezzoKgInput.sendKeys("3");
    quantitaDispInput.clear();
    quantitaDispInput.sendKeys("10");
    lottoInput.clear();
    lottoInput.sendKeys("412411");
    descrizioneInput.sendKeys("Ottime fiche del regno unito");

    submitButton.click();

    Boolean isElementValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return document.getElementById('lotto').validity.valid");

    assertFalse("Il campo 'nome' è valido, inserimento errato", isElementValid);
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test per il campo 'descrizione' con inserimento errato.
   */
 @org.junit.Test
  public void testDescrizioneErrato() {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
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
    descrizioneInput.sendKeys("Ottime fiche del regno unito UAUUUUUUUUUUdddddddddddddddddddddddddddddddddddddddddddddddddssUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");

    submitButton.click();

    Boolean isElementValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return document.getElementById('descrizione').validity.valid");

    assertFalse("Il campo 'nome' è valido, inserimento errato", isElementValid);
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test per un inserimento corretto nel form.
   */
 @org.junit.Test
  public void testInsertCorretta() {

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
    WebElement submitButton =  driver.findElement(By.xpath("/html/body/div[2]/form/button"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys(System.getProperty("user.dir") + "/src/test/resources/fico.jpg");
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

  }

  /**
   * Metodo di tearDown eseguito dopo ogni test.
   */
  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}