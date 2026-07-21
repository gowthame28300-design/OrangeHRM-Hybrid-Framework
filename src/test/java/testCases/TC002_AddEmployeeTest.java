package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashBoardPage;
import pageObjects.EmployeePage;
import pageObjects.PimPage;
import testBase.BaseClass;

public class TC002_AddEmployeeTest  extends BaseClass{
	@Test
	public void AddEmployeeVerify()
	{
	try
	{
		login();
		logger.info("Login sucessful..");
		logger.info("TC002 starts...");
	DashBoardPage dashBoardPage = new DashBoardPage(getDriver());
	dashBoardPage.clickPim();
	logger.info("Clicked pim button..");
	PimPage pim = new PimPage(getDriver());
	pim.clickAdd();
	logger.info("Employee add starts now..");
	EmployeePage emplyeePage = new EmployeePage(getDriver());
	emplyeePage.enterFirstName("gowtham");
	emplyeePage.enterLastName("E");
	emplyeePage.clickSave();
	logger.info(" save button clicked...");
	
	Assert.assertTrue(true);

}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("TC002 finished successfully");
}
}
