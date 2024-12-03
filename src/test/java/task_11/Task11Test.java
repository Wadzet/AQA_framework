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
