package com.greenbridge.system;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TF6 {

  private WebDriver driver;

  @Before
  public void setUp() {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
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
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
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
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito Di Gran Bretagna");
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
            .executeScript("return document.getElementById('origine').validity.valid");

    assertFalse("Il campo 'origine' non è valido, inserimento errato", isElementValid);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testImmagineErrata() {

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


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys("C:\\Users\\mauro\\Desktop\\tabelle.txt");
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
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
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
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys("C:\\Users\\mauro\\Desktop\\fico.jpg");
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
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
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
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys("C:\\Users\\mauro\\Desktop\\fico.jpg");
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
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
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
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys("C:\\Users\\mauro\\Desktop\\fico.jpg");
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
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
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
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


    nomeInput.sendKeys("Fico");
    origineInput.sendKeys("Regno Unito");
    immagineFileInput.sendKeys("C:\\Users\\mauro\\Desktop\\fico.jpg");
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
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
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
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


    nomeInput.sendKeys("Fico");
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
    lottoInput.sendKeys("412411");
    descrizioneInput.sendKeys("Ottime fiche del regno unito");

    submitButton.click();

    Boolean isElementValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return document.getElementById('lotto').validity.valid");

    assertFalse("Il campo 'nome' è valido, inserimento errato", isElementValid);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
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
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


    nomeInput.sendKeys("Fico");
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
    descrizioneInput.sendKeys("Ottime fiche del regno unito UAUUUUUUUUUUdddddddddddddddddddddddddddddddddddddddddddddddddssUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");

    submitButton.click();

    Boolean isElementValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return document.getElementById('descrizione').validity.valid");

    assertFalse("Il campo 'nome' è valido, inserimento errato", isElementValid);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }



  @Test
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
    WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));


    nomeInput.sendKeys("Fico");
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

  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}