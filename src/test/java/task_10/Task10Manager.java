package task_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task10Manager {
    WebDriver driver;

    @BeforeTest
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void Task10Manager() {
        driver.get("https://www.demoblaze.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signupMenuItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='signin2']")));
        signupMenuItem.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sign-username']")))
                .sendKeys("admin");
        driver.findElement(By.xpath("//*[@id='sign-password']")).sendKeys("password");

        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        System.out.println("Alert message: " + alertText);
        driver.switchTo().alert().accept();
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }
}
