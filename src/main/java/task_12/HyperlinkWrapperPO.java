package task_12;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HyperlinkWrapperPO {

    private WebDriver driver;
    private By locator;

    public HyperlinkWrapperPO(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
        PageFactory.initElements(driver, this);
    }

    public void click() {
        try {
            driver.findElement(locator).click();
        } catch (Exception e) {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void waitForClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void closeModal() {
        try {
            WebElement closeModalButton = driver.findElement(By.cssSelector("button[aria-label='Close']"));
            if (closeModalButton.isDisplayed()) {
                closeModalButton.click();
            }
        } catch (NoSuchElementException e) {
        }
    }
}
