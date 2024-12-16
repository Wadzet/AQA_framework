package task_20;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class Task20Test {
    public static void main(String[] args) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "samsungS20");
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity", ".Calculator");

        AndroidDriver driver = null;

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

            driver.findElement(By.id("com.android.calculator2:id/digit_1")).click();
            driver.findElement(By.id("com.android.calculator2:id/digit_0")).click();
            captureScreenshot(driver, "step_1_enter_10");

            driver.findElement(By.id("com.android.calculator2:id/op_pct")).click();
            captureScreenshot(driver, "step_2_press_percentage");

            driver.findElement(By.id("com.android.calculator2:id/digit_5")).click();
            driver.findElement(By.id("com.android.calculator2:id/digit_0")).click();
            captureScreenshot(driver, "step_3_enter_50");

            driver.findElement(By.id("com.android.calculator2:id/eq")).click();
            captureScreenshot(driver, "step_4_press_equals");

            WebElement resultElement = driver.findElement(By.id("com.android.calculator2:id/result"));
            String result = resultElement.getText();

            if ("5".equals(result)) {
                System.out.println("Test Passed: Calculation is correct.");
            } else {
                System.out.println("Test Failed: Expected 5 but got " + result);
            }

        } catch (MalformedURLException e) {
            System.out.println("Invalid Appium Server URL");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred during test execution");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void captureScreenshot(AndroidDriver driver, String stepName) {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(stepName + ".png"));
            System.out.println("Screenshot captured for step: " + stepName);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot for step: " + stepName);
            e.printStackTrace();
        }
    }
}
