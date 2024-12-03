package task_11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePagePO {
    private WebDriver driver;
    private WebDriverWait wait;

    private By signUpMenu = By.id("signin2");
    private By loginMenu = By.id("login2");

    public HomePagePO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openSignUpModal() {
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(signUpMenu));
        signUpButton.click();
    }

    public void openLoginModal() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginMenu));
        loginButton.click();
    }
}
