package testCases;

import org.testng.annotations.Test;

import pageObjects.PersonalDetailsPage;
import testBase.BaseClass;

public class TC005_LogoutTest extends BaseClass{
	@Test
	public void LogoutVerify()
	{
		login();
		PersonalDetailsPage PersonalDetailsPage=new PersonalDetailsPage(getDriver());
		PersonalDetailsPage.ClickmenuButton();
		PersonalDetailsPage.ClickLogout();
	}

}
