
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.PersonalDetailsPage;
import testBase.BaseClass;
import utilities.EmployeeHelper;

public class TC004_EditEmployeeTest extends BaseClass {

    @Test
    public void EditEmployeeVerify() {

        login();

        EmployeeHelper employeeHelper = new EmployeeHelper(getDriver());
        employeeHelper.openEmployee(p.getProperty("name"));

        PersonalDetailsPage personalDetailsPage =
                new PersonalDetailsPage(getDriver());

        personalDetailsPage.editMiddleName("Alex");
        personalDetailsPage.enterLicenseExpiryDate("2028-12-31");
        personalDetailsPage.selectNationality("Indian");
        personalDetailsPage.selectMaritalStatus("Single");
        personalDetailsPage.clickMaleButton();
        personalDetailsPage.clickSave();

        Assert.assertEquals(
                personalDetailsPage.getMiddleName(),
                "Alex",
                "Middle name does not match."
        );

        Assert.assertEquals(
                personalDetailsPage.getMaritalStatus(),
                "Single",
                "Marital status does not match."
        );

        Assert.assertEquals(
                personalDetailsPage.getNationality(),
                "Indian",
                "Nationality does not match."
        );
    }
}

