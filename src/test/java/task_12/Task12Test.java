package task_12;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task12Test {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
    }

    @Test
    public void testFullScenario() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        String uniqueUsername = "testuser" + System.currentTimeMillis();
        driver.findElement(By.id("sign-username")).sendKeys(uniqueUsername);
        driver.findElement(By.id("sign-password")).sendKeys("password123");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert present.");
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).sendKeys(uniqueUsername);
        driver.findElement(By.id("loginpassword")).sendKeys("password123");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        HyperlinkWrapperPO contactLink = new HyperlinkWrapperPO(driver, By.linkText("Contact"));
        contactLink.waitForClickable();
        contactLink.click();
        contactLink.closeModal();

        HyperlinkWrapperPO aboutUsLink = new HyperlinkWrapperPO(driver, By.linkText("About us"));
        aboutUsLink.waitForClickable();
        aboutUsLink.click();
        aboutUsLink.closeModal();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

//General
//Implement PageFactory for a few pages.
//Implement Wrapper for common WebElements (choose your variant).
//Implement methods for your web element with console logging. (Choose your variant with specific methods)
//Use those methods in simple TC scenario

//V7 - Hyperlinks:
//click - clicks on a hyperlink
//waitForClickable - waits for the hyperlink to become clickable
//getUrl - retrieves the URL of the hyperlink
