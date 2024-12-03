package task_11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePagePO {
    private final WebDriver driver;

    private final By welcomeMessage = By.id("nameofuser");

    public HomePagePO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUserLoggedIn() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }
}
