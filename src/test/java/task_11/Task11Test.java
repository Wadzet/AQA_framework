package task_11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task11Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://www.demoblaze.com";
    private final String username = "test_user_" + System.currentTimeMillis();
    private final String password = "test_password";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    @Test
    public void testSignUpAndLogin() {
        driver.get(baseUrl);

        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("signin2")));
        signUpButton.click();

        WebElement signUpUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));
        signUpUsername.sendKeys(username);
        WebElement signUpPassword = driver.findElement(By.id("sign-password"));
        signUpPassword.sendKeys(password);

        WebElement signUpConfirmButton = driver.findElement(By.xpath("//button[text()='Sign up']"));
        signUpConfirmButton.click();

        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertText, "Sign up successful.", "Sign up was not successful.");

        WebElement logInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
        logInButton.click();
        WebElement logInUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        logInUsername.sendKeys(username);
        WebElement logInPassword = driver.findElement(By.id("loginpassword"));
        logInPassword.sendKeys(password);

        WebElement logInConfirmButton = driver.findElement(By.xpath("//button[text()='Log in']"));
        logInConfirmButton.click();
        WebElement logOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        Assert.assertTrue(logOutButton.isDisplayed(), "Log out button is not displayed. Login might have failed.");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

//Scenario (new account)
//- Navigate to the sign-up page
//- Enter a valid email address, a username, and a strong password
//- Click on the "Sign Up" button
//- Verify that the user account is created
//- Click on the "Log In" button
//- Verify that we successfully logged
