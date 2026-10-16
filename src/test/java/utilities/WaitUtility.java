package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;

public class WaitUtility {

	@SuppressWarnings("unused")
	private final WebDriver driver;
	private final WebDriverWait wait;

    public WaitUtility(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement visibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void invisibility(By locator) {
         wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public WebElement presence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void clickable(WebElement element)
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitForLoaderToDisappear() {

        WebDriverWait wait = new WebDriverWait(
            BaseClass.getDriver(),
            Duration.ofSeconds(15)
        );

        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".oxd-form-loader")
            )
        );
    }
}