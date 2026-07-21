package utilities;

import org.openqa.selenium.WebDriver;

import pageObjects.DashBoardPage;
import pageObjects.EmployeeListPage;


public class EmployeeHelper {
	
	
	private final WebDriver driver;
	
	
    public EmployeeHelper(WebDriver driver)
    {
        this.driver = driver;
    }

    public void openEmployee(String employeeName)
    {
        DashBoardPage dashboardPage = new DashBoardPage(driver);
        dashboardPage.clickPim();

        EmployeeListPage employeelistPage = new EmployeeListPage(driver);

        employeelistPage.clickEmployeeList();
        employeelistPage.findNameEmployee(employeeName);
        employeelistPage.search();
        employeelistPage.ClickLastNameButton();
    }
}