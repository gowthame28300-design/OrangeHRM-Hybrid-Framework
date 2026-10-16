package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardPage extends BasePage {
	
	public DashBoardPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//span[text()='PIM']")
	WebElement pim;

	
	public void clickPim()
	{
	    waitutil.clickable(By.xpath("//span[text()='PIM']")).click();
	}
	

}