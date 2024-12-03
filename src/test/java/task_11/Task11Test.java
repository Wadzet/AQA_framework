package task_11;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Task11Test {
    private WebDriver driver;
    private HomePageBO homePageBO;

    @BeforeTest
    void setUp() {
        driver = DriverProvider.getDriver();
        SignUpPagePO signUpPagePO = new SignUpPagePO(driver);
        HomePagePO homePagePO = new HomePagePO(driver);
        homePageBO = new HomePageBO(signUpPagePO, homePagePO);
    }

    @Test
    void testUserSignUp() {
        driver.get("https://www.demoblaze.com/");
        boolean isLoggedIn = homePageBO.registerNewUser("testuserVadym", "admin");
        Assert.assertTrue(isLoggedIn, "User was not logged in after sign-up.");
    }

    @AfterTest
    void tearDown() {
        DriverProvider.quitDriver();
    }
}

//General task
//1. Make up one simple UI end-to-end test case for your test page from Task_10 - https://www.demoblaze.com
//2. Automate that scenario using WebDriver
//3. Create PageObject (use Busines object if need)for all pages used in scenario
//
//Scenario example (new account)
//- Navigate to the sign-up page
//- Enter a valid email address, a username, and a strong password
//- Click on the "Sign Up" button
//- Verify that the user is redirected to the home page
//- Verify that the user account is created andlogged in