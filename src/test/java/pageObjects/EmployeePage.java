package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeePage extends BasePage{
	
	public EmployeePage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(name="firstName")
	WebElement fstname;

	@FindBy(name="lastName")
	WebElement lstname;

	@FindBy(xpath="(//input[contains(@class,'oxd-input')])[3]")
	WebElement empid;

	@FindBy(xpath="//button[@type='submit']")
	WebElement saveBtn;
	
	public void enterFirstName(String name)
	{
	    fstname.sendKeys(name);
	}

	public void enterLastName(String name)
	{
	    lstname.sendKeys(name);
	}

	public void enterEmpId(String id)
	{
	    empid.clear();
	    empid.sendKeys(id);
	}

	public void clickSave()
	{
		
		 waitutil.invisibility(By.className("oxd-loading-spinner"));
		waitutil.visibility(By.xpath("//button[@type='submit']"));
	    saveBtn.click();
	}
	
	
	
	
}
