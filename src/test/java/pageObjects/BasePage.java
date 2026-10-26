package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtility;

public class BasePage {

    protected final WebDriver driver;
    protected final WaitUtility waitutil;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitutil = new WaitUtility(driver);

        PageFactory.initElements(driver, this);
    }
}