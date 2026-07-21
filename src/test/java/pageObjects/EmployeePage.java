package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		 waitutil.invisibility(By.className("oxd-loading-spinner"));
		wait.until(ExpectedConditions.visibilityOf(saveBtn));
		
	    saveBtn.click();
	}
	
	
	
	
}
