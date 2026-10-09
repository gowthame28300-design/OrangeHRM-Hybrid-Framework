package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;
import utilities.EmployeeHelper;

public class TC003_SearchEmployeeTest extends BaseClass {
	@Test
	
	public void SearchEmployeeVerify()
	{
	    login();

	    EmployeeHelper helper = new EmployeeHelper(getDriver());

	    helper.openEmployee(p.getProperty("name"));

	    Assert.assertEquals(helper.getEmployeeLastName(), "E");
	}

}
