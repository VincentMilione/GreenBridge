package com.greenbridge.system;

import com.greenbridge.GreenBridgeApplication;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = GreenBridgeApplication.class)
public class TF2 {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        WebDriverManager.chromedriver().setup();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void tC1() {
            driver.get("http://localhost:8080/registrazione-Agricoltore");
            driver.manage().window().setSize(new Dimension(1110, 794));
            driver.findElement(By.id("nomeBottega")).click();
            driver.findElement(By.id("nomeBottega")).sendKeys("zitounielmehdi");
            driver.findElement(By.id("indirizzoBottega")).click();
            driver.findElement(By.id("indirizzoBottega")).sendKeys("via roma");
            driver.findElement(By.id("email")).click();
            driver.findElement(By.id("email")).sendKeys("xhbouchra3@gmail.com");
            driver.findElement(By.id("password")).click();
            driver.findElement(By.id("password")).sendKeys("Mehdi1998");
            driver.findElement(By.id("confirmaPassword")).click();
            driver.findElement(By.id("confirmaPassword")).sendKeys("Mehdi1998");
            driver.findElement(By.id("privacyCheckbox")).click();
            driver.findElement(By.cssSelector("button")).click();
            assertThat(driver.switchTo().alert().getText(), is("nome utente non inserito"));


    }

    @Test
    public void tC2() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(763, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("elmehdizitounizitounielmehdizitouni");
        driver.findElement(By.id("nomeBottega")).click();
        driver.findElement(By.id("nomeBottega")).sendKeys("nomeBottega");
        driver.findElement(By.id("indirizzoBottega")).click();
        driver.findElement(By.id("indirizzoBottega")).sendKeys("via roma87");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3333333333333");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("3333333333333");
        driver.findElement(By.id("privacyCheckbox")).click();
        driver.findElement(By.cssSelector("button:nth-child(8)")).click();
        assertThat(driver.switchTo().alert().getText(), is("nome utente troppo lungo"));
    }
    @Test
    public void tc3() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(761, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("mehdi");
        driver.findElement(By.id("indirizzoBottega")).click();
        driver.findElement(By.id("indirizzoBottega")).sendKeys("via roma 22");
        driver.findElement(By.cssSelector("form")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3333333333333");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("3333333333333");
        driver.findElement(By.cssSelector("label")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("nome Bottega non inserito"));

    }

    @Test
    public void tC4() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(700, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("emlehdi");
        driver.findElement(By.id("nomeBottega")).click();
        driver.findElement(By.id("nomeBottega")).sendKeys("noemBottega");
        driver.findElement(By.id("indirizzoBottega")).click();
        driver.findElement(By.id("indirizzoBottega")).sendKeys("viasanleonardo");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("elmehdi87@email.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("123456789101");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("123456789101");
        driver.findElement(By.id("privacyBox")).click();
        driver.findElement(By.id("privacyCheckbox")).click();
        driver.findElement(By.cssSelector("button:nth-child(8)")).click();
        assertThat(driver.switchTo().alert().getText(), is("email non corretta!"));
    }


    @Test
    public void tC5() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(761, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("hhhhhhhh");
        driver.findElement(By.id("nomeBottega")).click();
        driver.findElement(By.id("nomeBottega")).sendKeys("hhhhhhhhh");
        driver.findElement(By.id("indirizzoBottega")).click();
        driver.findElement(By.id("indirizzoBottega")).sendKeys("hhhhhhhhhhh");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3333333333333");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("33333333333");
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.cssSelector("label")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("conferma password errata. Riprova."));
    }

    @Test
    public void tC6() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(1109, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("hhhhhhhhh");
        driver.findElement(By.id("nomeBottega")).click();
        driver.findElement(By.id("nomeBottega")).sendKeys("hhhhhhhhhh");
        driver.findElement(By.id("indirizzoBottega")).click();
        driver.findElement(By.id("indirizzoBottega")).sendKeys("JDSKJS");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3333333333333");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("3333333333333");
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("Devi accettare la privacy per procedere."));
    }
}
