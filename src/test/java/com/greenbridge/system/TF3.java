package com.greenbridge.system;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TF3 {
    private WebDriver driver;
    JavascriptExecutor js;

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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void tC1() {
        driver.get("http://localhost:8080/home");
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.findElement(By.cssSelector("a > .imgIcon")).click();
        driver.findElement(By.cssSelector("div:nth-child(2) > a > .button")).click();
        driver.findElement(By.id("certName")).click();
        driver.findElement(By.id("certName")).sendKeys("AB");
        driver.findElement(By.id("expiryDate")).click();
        driver.findElement(By.id("expiryDate")).sendKeys("07-12-2024");
        driver.findElement(By.id("certScan")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/Certificato.pdf");
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("nome del certificato è troppo corto"));
    }
    @Test
    public void tC2() {
        driver.get("http://localhost:8080/home");
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.findElement(By.cssSelector("a > .imgIcon")).click();
        driver.findElement(By.cssSelector("div:nth-child(2) > a > .button")).click();
        driver.findElement(By.id("certName")).click();
        driver.findElement(By.id("certName")).sendKeys("The Carbon Trust Standard for Zero Waste to Landfill rilasciato dall’ente internazionale Carbon Trust  ");
        driver.findElement(By.id("expiryDate")).click();
        driver.findElement(By.id("expiryDate")).sendKeys("07-12-2024");
        driver.findElement(By.id("certScan")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/Certificato.pdf");
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("nome del certificato è troppo lungo"));
    }
    @Test
    public void tC3() {
        driver.get("http://localhost:8080/home");
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.findElement(By.cssSelector("a > .imgIcon")).click();
        driver.findElement(By.cssSelector("div:nth-child(2) > a > .button")).click();
        driver.findElement(By.id("certName")).click();
        driver.findElement(By.id("certName")).sendKeys("The Carbon Trust Standard");
        driver.findElement(By.id("expiryDate")).click();
        driver.findElement(By.id("expiryDate")).sendKeys("14-10-2011");
        driver.findElement(By.id("certScan")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/Certificato.pdf");
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("Impossibile procedere poiché la data di scadenza inserita è precedente alla data corrente"));
    }
    @Test
    public void tC4() {
        Date dataCorrente=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormattata = sdf.format(dataCorrente);

        driver.get("http://localhost:8080/home");
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.findElement(By.cssSelector("a > .imgIcon")).click();
        driver.findElement(By.cssSelector("div:nth-child(2) > a > .button")).click();
        driver.findElement(By.id("certName")).click();
        driver.findElement(By.id("certName")).sendKeys("The Carbon Trust Standard");
        driver.findElement(By.id("expiryDate")).click();
        driver.findElement(By.id("expiryDate")).sendKeys(dataFormattata);
        driver.findElement(By.id("certScan")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/Certificato.pdf");
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("Impossibile procedere poiché la data di scadenza inserita è uguale alla data corrente"));
    }
    @Test
    public void tC5() {
        driver.get("http://localhost:8080/home");
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.findElement(By.cssSelector("a > .imgIcon")).click();
        driver.findElement(By.cssSelector("div:nth-child(2) > a > .button")).click();
        driver.findElement(By.id("certName")).click();
        driver.findElement(By.id("certName")).sendKeys("The Carbon Trust Standard");
        driver.findElement(By.id("expiryDate")).click();
        driver.findElement(By.id("expiryDate")).sendKeys("07-12-2024");
        driver.findElement(By.id("certScan")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/CertificatoErrato.docx");
        driver.findElement(By.cssSelector("button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        assertThat(driver.switchTo().alert().getText(), is("Impossibile procedere poiché il formato della scansione certificato non è valido"));
    }
    @Test
    public void tC6() {
        driver.get("http://localhost:8080/home");
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.findElement(By.cssSelector("a > .imgIcon")).click();
        driver.findElement(By.cssSelector("div:nth-child(2) > a > .button")).click();
        driver.findElement(By.id("certName")).click();
        driver.findElement(By.id("certName")).sendKeys("The Carbon Trust Standard");
        driver.findElement(By.id("expiryDate")).click();
        driver.findElement(By.id("expiryDate")).sendKeys("07-12-2024");
        driver.findElement(By.id("certScan")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/Certificato.pdf");
        driver.findElement(By.cssSelector("button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        assertThat(driver.switchTo().alert().getText(), is("Passa al passo successivo"));
    }
}