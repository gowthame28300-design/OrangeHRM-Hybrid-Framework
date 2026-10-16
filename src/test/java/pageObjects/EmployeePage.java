package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeePage extends BasePage {

    public EmployeePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "firstName")
    WebElement fstname;

    @FindBy(name = "lastName")
    WebElement lstname;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    WebElement empid;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveBtn;

  
    public void enterFirstName(String name) {
        fstname.sendKeys(name);
    }

    public void enterLastName(String name) {
        lstname.sendKeys(name);
    }

    public void enterEmpId(String id) {
        empid.clear();
        empid.sendKeys(id);
    }
    
    public String generateEmployeeId() {
        return String.valueOf(
            1000 + new java.util.Random().nextInt(9000) );
       
    }
    public void clickSave() {

        waitutil.waitForLoaderToDisappear();

        WebElement savebtn = waitutil.clickable(
            By.xpath("//button[@type='submit']")
        );

        savebtn.click();
    }
            
       
    }

    


	
