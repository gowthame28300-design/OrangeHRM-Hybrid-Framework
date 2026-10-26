package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends BasePage {

	// =========================
	// LOCATORS
	// =========================
	private static final By DASHBOARD_HEADING = By.xpath("//h6[normalize-space()='Dashboard']");

	private static final By PIM_MENU = By.xpath("//span[normalize-space()='PIM']");

// Constructor
	public DashBoardPage(WebDriver driver) {
		super(driver);
	}

	// =========================
	// ACTION METHOD
	// =========================

	public void clickPim() {

		// Wait until the Dashboard is visible
		waitutil.visibility(DASHBOARD_HEADING);

		// Wait until PIM is clickable and click it
		waitutil.clickable(PIM_MENU).click();
	}

}
