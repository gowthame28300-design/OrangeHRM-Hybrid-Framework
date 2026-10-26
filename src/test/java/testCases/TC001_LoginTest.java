package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC001_LoginTest extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void loginVerify(String username, String password) {

        logger.info("Starting login test");

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.enterUsername(username);
        logger.info("Entered username");

        loginPage.enterPassword(password);
        logger.info("Entered password");

        loginPage.clickLogin();
        logger.info("Clicked Login button");

        Assert.assertEquals(
                getDriver().getTitle(),
                "OrangeHRM",
                "Login verification failed: page title did not match."
        );

        logger.info("Login test passed");
    }
}