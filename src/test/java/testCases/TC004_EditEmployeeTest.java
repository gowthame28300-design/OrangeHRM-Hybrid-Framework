package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.PersonalDetailsPage;
import testBase.BaseClass;
import utilities.EmployeeHelper;

public class TC004_EditEmployeeTest extends BaseClass{
	@Test
	public void EditEmployeeVerify()
	{
	    login();

	    EmployeeHelper employeeHelper = new EmployeeHelper(getDriver());

	    employeeHelper.openEmployee(p.getProperty("name"));

	    PersonalDetailsPage PersonalDetailsPage = new PersonalDetailsPage(getDriver());

	    PersonalDetailsPage.editMiddleName("Alex");
	    PersonalDetailsPage.enterLicenceDate();
	    PersonalDetailsPage.clickNationalityButton("Indian");
	    PersonalDetailsPage.selectMaritalStatus("Single");
	    PersonalDetailsPage.DateOfBirth();
	    PersonalDetailsPage.clickMaleButton();
	    PersonalDetailsPage.clickSave();

	    Assert.assertEquals(PersonalDetailsPage.getMiddleName(), "Alex");
	    Assert.assertEquals(PersonalDetailsPage.getMaritualStatus(), "Single");
	    Assert.assertEquals(PersonalDetailsPage.getNationalityOption(), "Indian");
	}
			
	}


