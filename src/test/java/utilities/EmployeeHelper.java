package utilities;

import org.openqa.selenium.WebDriver;

import pageObjects.DashBoardPage;
import pageObjects.EmployeeListPage;

public class EmployeeHelper {

    private final WebDriver driver;
    private final EmployeeListPage employeeListPage;

    public EmployeeHelper(WebDriver driver) {
        this.driver = driver;
        this.employeeListPage = new EmployeeListPage(driver);
    }

    public void openEmployee(String employeeName) {
        DashBoardPage dashboardPage = new DashBoardPage(driver);

        dashboardPage.clickPim();
        employeeListPage.clickEmployeeList();
        employeeListPage.findNameEmployee(employeeName);
        employeeListPage.search();
        employeeListPage.EmployeeClick();
    }
    public String getEmployeeLastName() {
        return employeeListPage.getEmployeeLastName();
    }
    
    
    
  
}