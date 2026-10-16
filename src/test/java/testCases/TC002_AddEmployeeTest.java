package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashBoardPage;
import pageObjects.EmployeePage;
import pageObjects.PersonalDetailsPage;
import pageObjects.PimPage;
import testBase.BaseClass;

public class TC002_AddEmployeeTest extends BaseClass {

    @Test
    public void AddEmployeeVerify() {

        login();

        DashBoardPage dashboard = new DashBoardPage(getDriver());
        dashboard.clickPim();

        PimPage pim = new PimPage(getDriver());
        pim.clickAdd();

        EmployeePage employee = new EmployeePage(getDriver());
        
        
       

        employee.enterFirstName("Gowtham");
        employee.enterLastName("Tester");

        String employeeId = employee.generateEmployeeId();

        employee.enterEmpId(employeeId);

        System.out.println("Generated Employee ID: " + employeeId);

        employee.clickSave();
 
       

        PersonalDetailsPage personalDetails =
                new PersonalDetailsPage(getDriver());

        Assert.assertTrue(
                personalDetails.isPersonalDetailsPageDisplayed(),
                "Personal Details page was not displayed after saving the employee."
        );

        System.out.println("PASS: Personal Details page displayed successfully.");
      
        
       System.out.println("Current URL: " + getDriver().getCurrentUrl());
       
       
       Assert.assertEquals(
    		    personalDetails.getEmployeeName(),
    		    "Gowtham Tester",
    		    "Employee name does not match!"
    		);
    }
    
    
}