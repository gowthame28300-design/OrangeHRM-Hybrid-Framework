package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement usr;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement pwd;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btn;
	
	
	public void username(String usrname)
	{
		usr.sendKeys(usrname);
	}
	public void password(String passwd)
	{
		pwd.sendKeys(passwd);
	}
	public void login()
	{
		btn.click();
	}
	

}
