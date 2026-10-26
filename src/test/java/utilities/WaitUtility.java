package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

	private final WebDriver driver;
	private final WebDriverWait wait;

	private static final By LOADER = By.cssSelector(".oxd-form-loader");

	public WaitUtility(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Wait until an element is visible
	public WebElement visibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement visibility(WebElement locator) {
		return wait.until(ExpectedConditions.visibilityOf(locator));
	}

	// Wait until an element disappears
	public boolean invisibility(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Wait until an element is clickable
	public WebElement clickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Wait until an element is present in the DOM
	public WebElement presence(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// Wait until a specific WebElement is clickable
	public WebElement clickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Wait until the OrangeHRM form loader disappears
	public boolean waitForLoaderToDisappear() {
		return new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.invisibilityOfElementLocated(LOADER));
	}
}