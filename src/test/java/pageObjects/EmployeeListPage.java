
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmployeeListPage extends BasePage {

	// =========================
	// LOCATORS
	// =========================

	private static final By EMPLOYEE_LIST_LINK = By.xpath("//a[normalize-space()='Employee List']");

	private static final By SEARCH_NAME_INPUT = By.xpath("//input[@placeholder='Type for hints...']");

	private static final By SEARCH_BUTTON = By.xpath("//button[@type='submit']");

	private static final By FIRST_NAME = By.xpath("//div[normalize-space()='Gowtham']");

	private static final By LAST_NAME = By.xpath("//div[normalize-space()='Tester']");

	private String employeeName;

	// =========================
	// CONSTRUCTOR
	// =========================

	public EmployeeListPage(WebDriver driver) {
		super(driver);
	}

	// =========================
	// ACTION METHODS
	// =========================

	// Open Employee List
	public void clickEmployeeList() {

		waitutil.clickable(EMPLOYEE_LIST_LINK).click();
	}

	// Enter employee name in the search field
	public void findNameEmployee(String name) {

		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Employee name cannot be empty.");
		}

		employeeName = name.trim();

		WebElement searchField = waitutil.visibility(SEARCH_NAME_INPUT);

		searchField.clear();
		searchField.sendKeys(employeeName);
	}

	// Click Search
	public void search() {

		waitutil.clickable(SEARCH_BUTTON).click();
	}

	// Select the employee from search results

	public void EmployeeClick() {

		if (employeeName == null || employeeName.isBlank()) {
			throw new IllegalStateException("Search for an employee before clicking a result.");
		}

		String[] names = employeeName.trim().split("\\s+");
		String lastName = names[names.length - 1];

		By employeeResult = By.xpath("//div[@role='row' and contains(@class,'oxd-table-row')]"
				+ "[.//div[normalize-space()='" + lastName + "']]");

		waitutil.clickable(employeeResult).click();
	}

	// =========================
	// VERIFICATION METHODS
	// =========================

	// Read employee first name
	public String getEmployeeFirstName() {

		return waitutil.visibility(FIRST_NAME).getText().trim();
	}

	// Read employee last name
	public String getEmployeeLastName() {

		return waitutil.visibility(LAST_NAME).getText().trim();
	}
}
