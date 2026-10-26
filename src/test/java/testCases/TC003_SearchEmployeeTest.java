package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashBoardPage;
import pageObjects.EmployeeListPage;
import testBase.BaseClass;

public class TC003_SearchEmployeeTest extends BaseClass {

	@Test
	public void SearchEmployeeVerify() {
		login();

		DashBoardPage dashboardPage = new DashBoardPage(getDriver());

		dashboardPage.clickPim();

		EmployeeListPage employeeListPage = new EmployeeListPage(getDriver());

		employeeListPage.findNameEmployee(p.getProperty("name"));
		employeeListPage.search();

		Assert.assertEquals(employeeListPage.getEmployeeLastName(), "Tester");
		System.out.println(employeeListPage.getEmployeeLastName());
	}
}