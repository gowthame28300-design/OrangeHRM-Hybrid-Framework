package utilities;

import org.openqa.selenium.WebDriver;

import pageObjects.DashBoardPage;
import pageObjects.EmployeeListPage;


public class EmployeeHelper {
	
	
	private final WebDriver driver;
	private EmployeeListPage employeelistPage;
	
	
	public EmployeeHelper(WebDriver driver)
	{
	    this.driver = driver;
	    this.employeelistPage = new EmployeeListPage(driver);
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
        
        public String getEmployeeLastName()
    {
        return employeelistPage.getEmployeeLastName();
    }
}