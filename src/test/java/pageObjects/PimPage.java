package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimPage extends BasePage {

	// =========================
	// LOCATOR
	// =========================
	private static final By ADD_BUTTON = By.xpath("//button[normalize-space()='Add']");

	public PimPage(WebDriver driver) {
		super(driver);
	}

	// =========================
	// ACTION METHOD
	// =========================
	public void clickAdd() {
		waitutil.clickable(ADD_BUTTON).click();
	}
}