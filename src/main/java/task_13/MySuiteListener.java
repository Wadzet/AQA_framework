package task_13;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class MySuiteListener implements ISuiteListener {
    private static final Logger LOGGER = LogManager.getLogger(MySuiteListener.class);

    @Override
    public void onStart(ISuite suite) {
        LOGGER.info("onStart: Setting up environment for test suite - " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        LOGGER.info("onFinish: Tearing down environment for test suite - " + suite.getName());
    }
}
