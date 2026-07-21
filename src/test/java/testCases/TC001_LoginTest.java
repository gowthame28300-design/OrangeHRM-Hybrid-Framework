package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC001_LoginTest extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	 
	public void Login_Verify(String username,String password) throws InterruptedException
	{
	try
	{
		logger.info("Starting Login Test");

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.username(username);
		logger.info("Entered Username");

		loginPage.password(password);
		logger.info("Entered Password");
		loginPage.login();

		logger.info("Login Successful");
		Assert.assertEquals(getDriver().getTitle(),"OrangeHRM");
	}
	catch(Exception e)
	{
		Assert.fail();
	}
		
	}}
