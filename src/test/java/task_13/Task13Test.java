package task_13;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({MyTestListener.class, MySuiteListener.class, MyExecutionListener.class})
public class Task13Test {

    @Test
    public void testSuccess() {
        Assert.assertTrue(true);
    }

    @Test
    public void testFailure() {
        Assert.fail("Intentionally failing this test.");
    }

    @Test
    public void testSkip() {
        throw new SkipException("Skipping this test intentionally.");
    }
}
