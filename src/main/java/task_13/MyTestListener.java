package task_13;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class MyTestListener implements ITestListener {
    private static final Logger LOGGER = LogManager.getLogger(MyTestListener.class);

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("onStart: Setting up test data and starting browser.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("onTestStart: Executing test method - " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("onTestSuccess: Test passed - " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error("onTestFailure: Test failed - " + result.getName());

        // Save HTML code to file
        try {
            String fileName = result.getName() + "_" + LocalDateTime.now() + ".html";
            File file = new File("failed_tests/" + fileName);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            writer.write("<html>Mock HTML content</html>");
            writer.close();
        } catch (IOException e) {
            LOGGER.error("Failed to save HTML code", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn("onTestSkipped: Test skipped - " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("onFinish: Closing browser and cleaning up resources.");
    }
}
