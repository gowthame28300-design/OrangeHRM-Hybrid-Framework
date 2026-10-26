package utilities;

import org.openqa.selenium.WebDriver;

import pageObjects.DashBoardPage;
import pageObjects.EmployeeListPage;

public class EmployeeHelper {


private final WebDriver driver;

public EmployeeHelper(WebDriver driver) {
    this.driver = driver;
}

public void openEmployee(String employeeName) {

    DashBoardPage dashboardPage = new DashBoardPage(driver);
    dashboardPage.clickPim();

    EmployeeListPage employeeListPage =
            new EmployeeListPage(driver);

    employeeListPage.clickEmployeeList();
    employeeListPage.findNameEmployee(employeeName);
    employeeListPage.search();
    employeeListPage.EmployeeClick();
}


}
