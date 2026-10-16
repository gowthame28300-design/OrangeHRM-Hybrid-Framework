package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeeListPage extends BasePage {
	
	public EmployeeListPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[text()='Employee List']")
	WebElement empList;
	 
	@FindBy(xpath="//input[@placeholder='Type for hints...']")
	WebElement searchName;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement searchBtn;
	
	@FindBy(xpath="//div[normalize-space()='Gowtham']")
	WebElement firstName;

	@FindBy(xpath="//div[normalize-space()='Tester']")
	WebElement lastName;
	
	
	
	
	
	
	public void clickEmployeeList()
	{
	    empList.click();
	}

	public void findNameEmployee(String name)
	{
	    searchName.sendKeys(name);
	}

	public void search()
	{
	    searchBtn.click();
	}

	public String getEmployeeFirstName() 
	{
	    return firstName.getText();
	}
	
	public String getEmployeeLastName() 
	{
	    return lastName.getText();
	}

	public void EmployeeClick() 
	{
	     firstName.click();
	}
	
	 
}
