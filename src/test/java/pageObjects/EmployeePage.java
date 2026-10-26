package pageObjects;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeePage extends BasePage {

	public EmployeePage(WebDriver driver) {
		super(driver);
	}

	// =========================
	// LOCATORS
	// =========================

	private static final By SAVE_BUTTON = By.xpath("//button[@type='submit']");

	@FindBy(name = "firstName")
	private WebElement firstNameField;

	@FindBy(name = "lastName")
	private WebElement lastNameField;

	@FindBy(xpath = "//label[normalize-space()='Employee Id']/following::input[1]")
	private WebElement employeeIdField;

	// =========================
	// ACTION METHODS
	// =========================

	// Enter employee first name
	public void enterFirstName(String firstName) {
		waitutil.visibility(By.name("firstName"));
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
	}

	// Enter employee last name
	public void enterLastName(String lastName) {
		waitutil.visibility(By.name("lastName"));
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
	}

	// Enter employee ID
	public void enterEmpId(String employeeId) {
		waitutil.visibility(employeeIdField);

		employeeIdField.clear();
		employeeIdField.sendKeys(employeeId);
	}

	// Generate a random four-digit employee ID
	public String generateEmployeeId() {
		return String.valueOf(ThreadLocalRandom.current().nextInt(1000, 10000));
	}

	// Save employee details
	public void clickSave() {

		waitutil.waitForLoaderToDisappear();

		waitutil.clickable(SAVE_BUTTON).click();
	}
}