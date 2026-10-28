package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.DashBoardPage;
import pageObjects.EmployeePage;
import pageObjects.PersonalDetailsPage;
import pageObjects.PimPage;
import testBase.BaseClass;

public class TC002_AddEmployeeTest extends BaseClass {


@Test
public void addEmployeeVerify() {

    // Step 1: Login to OrangeHRM
    LoginPage loginPage = new LoginPage(getDriver());

    loginPage.enterUsername(p.getProperty("username"));
    loginPage.enterPassword(p.getProperty("password"));
    loginPage.clickLogin();

    logger.info("Login button clicked");

    // Step 2: Verify dashboard is displayed
    DashBoardPage dashboard = new DashBoardPage(getDriver());

   
    

    // Step 3: Navigate to PIM
    dashboard.clickPim();

    // Step 4: Open Add Employee form
    PimPage pim = new PimPage(getDriver());
    pim.clickAdd();

    // Step 5: Enter employee details
    EmployeePage employee = new EmployeePage(getDriver());

    employee.enterFirstName("Gowtham");
    employee.enterLastName("Tester");

    String employeeId = employee.generateEmployeeId();

    employee.enterEmpId(employeeId);

    System.out.println("Generated Employee ID: " + employeeId);

    // Step 6: Save employee
    employee.clickSave();

 // Step 7: Verify Personal Details page
    PersonalDetailsPage personalDetails =
            new PersonalDetailsPage(getDriver());

    boolean isPageDisplayed =
            personalDetails.isPersonalDetailsPageDisplayed();

    if (!isPageDisplayed) {

        logger.error("Personal Details page was not detected.");

        logger.error("Current URL: " + getDriver().getCurrentUrl());

        logger.error("Current page title: " + getDriver().getTitle());
    }

    Assert.assertTrue(
            isPageDisplayed,
            "Personal Details page was not displayed after saving."
    );


}
}
