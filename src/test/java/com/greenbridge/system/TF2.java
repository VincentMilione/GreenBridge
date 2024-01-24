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
        driver.manage().window().setSize(new Dimension(1094, 794));
        driver.findElement(By.id("nomeBottega")).click();
        driver.findElement(By.id("nomeBottega")).sendKeys("nomeBottega");
        driver.findElement(By.id("indirizzoBottega")).click();
        driver.findElement(By.id("indirizzoBottega")).sendKeys("via fratelli napoli");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3333333333333");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("3333333333333");
        driver.findElement(By.id("privacyCheckbox")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("nome utente non inserito"));
        driver.close();
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
    public void tC3() {
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
    public void tC4() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(700, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("ZITOUNI EL MEHDI");
        driver.findElement(By.id("nomeBottega")).sendKeys("ZITOUNI EL MEHDI");
        driver.findElement(By.id("indirizzoBottega")).sendKeys("Hay Lalla Meriem");
        driver.findElement(By.id("email")).sendKeys("xhbouchra3@gmail.com");
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3333333333333");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("3333333333333");
        driver.findElement(By.id("privacyCheckbox")).click();
        driver.findElement(By.cssSelector("button:nth-child(8)")).click();
    }

    @Test
    public void tC5() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(700, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("ZITOUNI EL MEHDI");
        driver.findElement(By.id("nomeBottega")).sendKeys("ZITOUNI EL MEHDI");
        driver.findElement(By.id("indirizzoBottega")).sendKeys("Hay Lalla Meriem");
        driver.findElement(By.id("email")).sendKeys("xhbouchra3@gmail.com");
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("password");
        driver.findElement(By.id("privacyCheckbox")).click();
        driver.findElement(By.cssSelector("button:nth-child(8)")).click();
        assertThat(driver.switchTo().alert().getText(), is("password non deve contenere meno di 9 carattere"));
    }

    @Test
    public void tC6() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(700, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("ZITOUNI EL MEHDI");
        driver.findElement(By.id("nomeBottega")).sendKeys("ZITOUNI EL MEHDI");
        driver.findElement(By.id("indirizzoBottega")).sendKeys("Hay Lalla Meriem");
        driver.findElement(By.id("email")).sendKeys("xhbouchra3@gmail.com");
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3333333333333");
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).sendKeys("3333333333333");
        driver.findElement(By.cssSelector("label")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("Hai accettato la privacy. vuoi continuare la registrazione "));
    }

    @Test
    public void tC7() {
        driver.get("http://localhost:8080/registrazione-Agricoltore");
        driver.manage().window().setSize(new Dimension(700, 794));
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).sendKeys("ZITOUNI EL MEHDI");
        driver.findElement(By.id("nomeBottega")).sendKeys("ZITOUNI EL MEHDI");
        driver.findElement(By.id("indirizzoBottega")).sendKeys("Hay Lalla Meriem");
        driver.findElement(By.id("email")).sendKeys("xhbouchra3@gmail.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).click();
        {
            WebElement element = driver.findElement(By.id("email"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("johndoe+test@email.us");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3333333333333");
        driver.findElement(By.cssSelector("form")).click();
        driver.findElement(By.id("confirmaPassword")).click();
        driver.findElement(By.id("confirmaPassword")).click();
        {
            WebElement element = driver.findElement(By.id("confirmaPassword"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.id("confirmaPassword")).sendKeys("3333333333333");
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.switchTo().alert().getText(), is("Devi accettare la privacy per procedere."));
    }
}
