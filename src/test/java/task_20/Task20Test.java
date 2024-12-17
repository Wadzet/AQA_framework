package task_20;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Task20Test {
    public static void main(String[] args) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", "RF8W80XNZLN");
        caps.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        caps.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

        AndroidDriver driver = null;

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_01")).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_00")).click();
            captureScreenshot(driver, "step_1_enter_10");

            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_percentage")).click();
            captureScreenshot(driver, "step_2_press_percentage");

            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_05")).click();
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_00")).click();
            captureScreenshot(driver, "step_3_enter_50");

            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal")).click();
            captureScreenshot(driver, "step_4_press_equals");

            WebElement resultField = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula"));
            String result = resultField.getText().replaceAll("[^0-9]", "");

            if ("5".equals(result)) {
                System.out.println("Test Passed: Calculation is correct.");
            } else {
                System.out.println("Test Failed: Expected 5 but got " + result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void captureScreenshot(AndroidDriver driver, String stepName) {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String directory = "screenshots";
        File targetDir = new File(directory);

        if (!targetDir.exists()) {
            targetDir.mkdir();
        }

        try {
            FileUtils.copyFile(srcFile, new File(directory + "/" + stepName + ".png"));
            System.out.println("Screenshot captured for step: " + stepName);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot for step: " + stepName);
            e.printStackTrace();
        }
    }
}
