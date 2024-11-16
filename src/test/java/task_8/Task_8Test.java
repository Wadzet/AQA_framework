package task_8;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Task_8Test {

    @Test
    @Parameters({"input"})
    public void testWithParameters(@Optional("default_value") String input) {
        System.out.println("Parameter value: " + input);
        Assert.assertNotNull(input, "Input parameter should not be null");
    }

    @Test(dataProvider = "dataProviderMethod1")
    public void testWithDataProvider1(int input, int expected) {
        System.out.println("Testing with input: " + input);
        int actual = calculateSquare(input);
        Assert.assertEquals(actual, expected, "Square calculation mismatch");
    }

    @Test(dataProvider = "dataProviderMethod2")
    public void testWithDataProvider2(int input, boolean expected) {
        System.out.println("Testing even number check for: " + input);
        boolean actual = isEven(input);
        Assert.assertEquals(actual, expected, "Even number check mismatch");
    }

    public int calculateSquare(int n) {
        return n * n;
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    @DataProvider(name = "dataProviderMethod1")
    public Object[][] provideDataForSquares() {
        return new Object[][]{
                {2, 4},
                {3, 9},
                {4, 16}
        };
    }

    @DataProvider(name = "dataProviderMethod2")
    public Object[][] provideDataForEvenCheck() {
        return new Object[][]{
                {2, true},
                {3, false},
                {4, true}
        };
    }
}
