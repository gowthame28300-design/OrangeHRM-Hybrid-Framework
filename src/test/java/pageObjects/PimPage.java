package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PimPage extends BasePage{
	
	public PimPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//button[normalize-space()='Add']")
	WebElement addBtn;

	public void clickAdd()
	{
	    addBtn.click();
	}
}
