package task_13;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;

public class MyExecutionListener implements IExecutionListener {
    private static final Logger LOGGER = LogManager.getLogger(MyExecutionListener.class);

    @Override
    public void onExecutionStart() {
        LOGGER.info("onExecutionStart: Starting execution and initializing resources.");
    }

    @Override
    public void onExecutionFinish() {
        LOGGER.info("onExecutionFinish: Execution finished. Closing resources.");
    }
}
