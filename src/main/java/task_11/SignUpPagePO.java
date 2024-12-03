package task_11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPagePO {
    private final WebDriver driver;

    private final By signupMenuItem = By.id("signin2");
    private final By usernameField = By.id("sign-username");
    private final By passwordField = By.id("sign-password");
    private final By signupButton = By.xpath("//button[text()='Sign up']");

    public SignUpPagePO(WebDriver driver) {
        this.driver = driver;
    }

    public void openSignUpModal() {
        driver.findElement(signupMenuItem).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignUp() {
        driver.findElement(signupButton).click();
    }

    public void handleAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
