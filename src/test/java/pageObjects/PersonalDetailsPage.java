
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PersonalDetailsPage extends BasePage {

	public PersonalDetailsPage(WebDriver driver) {
		super(driver);
	}

	// =========================
	// LOCATORS
	// =========================

	private static final By LOADING_SPINNER = By.className("oxd-loading-spinner");

	private static final By FORM_LOADER = By.className("oxd-form-loader");

	private static final By PERSONAL_DETAILS_HEADING = By.xpath("//h6[normalize-space()='Personal Details']");

	private static final By MALE_RADIO_BUTTON = By.xpath("//label[normalize-space()='Male']");

	private static final By MIDDLE_NAME_FIELD = By.xpath("//input[@name='middleName']");

	private static final By SAVE_BUTTON = By.xpath("//button[@type='submit'][normalize-space()='Save']");

	private static final By NATIONALITY_DROPDOWN = By.xpath("(//div[@class='oxd-select-text-input'])[1]");

	private static final By MARITAL_STATUS_DROPDOWN = By.xpath("//label[normalize-space()='Marital Status']"
			+ "/ancestor::div[contains(@class,'oxd-input-group')]" + "//div[contains(@class,'oxd-select-text')]");

	private static final By LICENSE_EXPIRY_DATE_FIELD = By.xpath("//label[normalize-space()='License Expiry Date']"
			+ "/ancestor::div[contains(@class,'oxd-input-group')]//input");

	private static final By BLOOD_TYPE_DROPDOWN = By.xpath(
			"//label[normalize-space()='Blood Type']" + "/following::div[contains(@class,'oxd-select-text')][1]");

	private static final By FINAL_SAVE_BUTTON = By
			.xpath("//div[@class='orangehrm-custom-fields']" + "//button[@type='submit']");

	private static final By USER_MENU_BUTTON = By.xpath("//img[@class='oxd-userdropdown-img']");

	private static final By LOGOUT_BUTTON = By.xpath("//a[normalize-space()='Logout']");

	private static final By DATE_OF_BIRTH_FIELD = By.xpath(
			"//label[normalize-space()='Date of Birth']" + "/ancestor::div[contains(@class,'oxd-input-group')]//input");

	private static final By EMPLOYEE_NAME = By.xpath("//div[contains(@class,'orangehrm-edit-employee-name')]//h6");

	// =========================
	// ACTION METHODS
	// =========================

	// Edit middle name
	public void editMiddleName(String middleName) {

		waitutil.visibility(MIDDLE_NAME_FIELD);
		waitutil.invisibility(FORM_LOADER);

		WebElement field = waitutil.clickable(MIDDLE_NAME_FIELD);

		field.sendKeys(Keys.CONTROL, "a");
		field.sendKeys(Keys.BACK_SPACE);
		field.sendKeys(middleName);

		Assert.assertEquals(field.getAttribute("value"), middleName, "Middle name was not entered correctly.");
	}

	// Enter license expiry date
	public void enterLicenseExpiryDate(String date) {

		waitutil.invisibility(LOADING_SPINNER);

		WebElement field = waitutil.clickable(LICENSE_EXPIRY_DATE_FIELD);

		field.sendKeys(Keys.CONTROL, "a");
		field.sendKeys(Keys.BACK_SPACE);
		field.sendKeys(date);
	}

	// Select nationality
	public void selectNationality(String nationality) {

		waitutil.invisibility(LOADING_SPINNER);
		waitutil.clickable(NATIONALITY_DROPDOWN).click();

		By option = By.xpath("//span[normalize-space()='" + nationality + "']");

		waitutil.clickable(option).click();
	}

	// Select marital status
	public void selectMaritalStatus(String status) {

		waitutil.invisibility(LOADING_SPINNER);
		waitutil.clickable(MARITAL_STATUS_DROPDOWN).click();

		By option = By.xpath("//div[@role='option'][normalize-space()='" + status + "']");

		waitutil.clickable(option).click();
	}

	// Enter date of birth
	public void enterDateOfBirth(String date) {

		waitutil.invisibility(LOADING_SPINNER);

		WebElement field = waitutil.clickable(DATE_OF_BIRTH_FIELD);

		field.sendKeys(Keys.CONTROL, "a");
		field.sendKeys(Keys.BACK_SPACE);
		field.sendKeys(date);
	}

	// Select Male
	public void clickMaleButton() {

		waitutil.invisibility(FORM_LOADER);
		waitutil.clickable(MALE_RADIO_BUTTON).click();
	}

	// Click Save
	public void clickSave() {

		waitutil.invisibility(FORM_LOADER);
		waitutil.clickable(SAVE_BUTTON).click();
	}

	// Select blood type
	public void selectBloodType(String bloodType) {

		waitutil.invisibility(LOADING_SPINNER);
		waitutil.clickable(BLOOD_TYPE_DROPDOWN).click();

		By option = By.xpath("//span[normalize-space()='" + bloodType + "']");

		waitutil.clickable(option).click();
	}

	// Click custom-fields Save button
	public void clickFinalSaveButton() {

		waitutil.invisibility(FORM_LOADER);
		waitutil.clickable(FINAL_SAVE_BUTTON).click();
	}

	// Open user menu
	public void clickMenuButton() {

		waitutil.clickable(USER_MENU_BUTTON).click();
	}

	// Click Logout
	public void clickLogout() {

		waitutil.clickable(LOGOUT_BUTTON).click();
	}

	// Refresh page and wait for it to load
	public void refreshPage() {

		driver.navigate().refresh();

		waitutil.visibility(PERSONAL_DETAILS_HEADING);
		waitutil.invisibility(LOADING_SPINNER);
		waitutil.invisibility(FORM_LOADER);
	}

	// =========================
	// VERIFICATION METHODS
	// =========================

	// Read middle name
	public String getMiddleName() {

		return waitutil.visibility(MIDDLE_NAME_FIELD).getAttribute("value").trim();
	}

	// Check whether middle-name field is displayed
	public boolean isMiddleNameFieldDisplayed() {

		try {
			return waitutil.visibility(MIDDLE_NAME_FIELD).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	// Check whether Save button is displayed
	public boolean isSaveButtonDisplayed() {

		try {
			return waitutil.visibility(SAVE_BUTTON).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	// Check whether Personal Details page is displayed
	public boolean isPersonalDetailsPageDisplayed() {

		try {
			return waitutil.visibility(PERSONAL_DETAILS_HEADING).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	// Read blood type
	public String getBloodType() {

		return waitutil.visibility(BLOOD_TYPE_DROPDOWN).getText().trim();
	}

	// Read nationality
	public String getNationality() {

		return waitutil.visibility(NATIONALITY_DROPDOWN).getText().trim();
	}

	// Read marital status
	public String getMaritalStatus() {

		return waitutil.visibility(MARITAL_STATUS_DROPDOWN).getText().trim();
	}

	// Read employee name
	public String getEmployeeName() {

		waitutil.invisibility(LOADING_SPINNER);

		return waitutil.visibility(EMPLOYEE_NAME).getText().trim();
	}
}
