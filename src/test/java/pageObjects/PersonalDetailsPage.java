package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalDetailsPage extends BasePage {

    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
       
    }

    // Middle Name
    @FindBy(xpath = "//input[@name='middleName']")
    WebElement mdlname;
    
    @FindBy(xpath="(//div[@class='oxd-select-text-input'])[1]")
    WebElement nationalityValue;
    
    @FindBy(xpath="//label[normalize-space()='Marital Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]")
    WebElement statusbtn;
    
   

    
    @FindBy(xpath="(//input[@placeholder='yyyy-dd-mm'])[1]")
    WebElement licenceDate;

   
    

    // Male Radio Button
    @FindBy(xpath = "//label[normalize-space()='Male']")
    WebElement malebtn;

    // First Save Button
    @FindBy(xpath = "//button[@type='submit'][normalize-space()='Save']")
    WebElement savebtn;

    // Blood Type Dropdown
    @FindBy(xpath = "//label[normalize-space()='Blood Type']/following::div[contains(@class,'oxd-select-text')][1]")
    WebElement bloodTypeDropdown;
    

    // Second Save Button (Custom Fields)
    @FindBy(xpath = "//div[@class='orangehrm-custom-fields']//button[@type='submit']")
    WebElement save2btn;

    @FindBy(xpath = "//img[@class='oxd-userdropdown-img']")
    WebElement clkmenubtn;
    
    @FindBy(xpath="//a[normalize-space()='Logout']")
    WebElement clklogout;
    // ===========================
    // ACTION METHODS
    // ===========================

    public void editMiddleName(String mname) {

    	waitutil.visibility(By.xpath("//input[@name='middleName']"));
    	 waitutil.invisibility(By.className("oxd-loading-spinner"));
    	mdlname.click();
    	
    	mdlname.sendKeys(Keys.CONTROL + "a");
    	mdlname.sendKeys(Keys.DELETE);
    	
    	mdlname.sendKeys(mname);
        
    }
    
    public void enterLicenceDate() {
    	waitutil.invisibility(By.className("oxd-loading-spinner"));
    	waitutil.clickable(licenceDate);

    	licenceDate.click();
    	licenceDate.sendKeys(Keys.CONTROL + "a");
    	licenceDate.sendKeys(Keys.DELETE);
    	licenceDate.sendKeys("2026-03-28");
    }
    
    public void clickNationalityButton(String nationality) {

        waitutil.invisibility(By.className("oxd-loading-spinner"));

        nationalityValue.click();

        driver.findElement(
            By.xpath("//span[normalize-space()='" + nationality + "']")
        ).click();
    }

    public void selectMaritalStatus(String status) {
    	waitutil.invisibility(By.className("oxd-loading-spinner"));

        statusbtn.click();

        driver.findElement(
            By.xpath("//div[@role='option'][normalize-space()='" + status + "']")
        ).click();
    }
    public void clickMaleButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-form-loader")));

        wait.until(ExpectedConditions.elementToBeClickable(malebtn));

        malebtn.click();
    }

    public void clickSave() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-loading-spinner")));

        wait.until(ExpectedConditions.elementToBeClickable(savebtn));

        savebtn.click();
    }

    public void selectBloodType(String bloodType) {

       waitutil.invisibility(By.className("oxd-loading-spinner"));
                

        bloodTypeDropdown.click();

        driver.findElement(
                By.xpath("//span[normalize-space()='" + bloodType + "']"))
                .click();
    }

    public void clickFinalSaveButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(save2btn));

        save2btn.click();
    }

    // ===========================
    // VERIFICATION METHODS
    // ===========================

    public String getMiddleName() {
        return mdlname.getAttribute("value");
    }

    public String getBloodType() {
        return bloodTypeDropdown.getText();
    }
    
    public String getNationalityOption() {
        return nationalityValue.getText().trim();
    }
    
    public String getMaritualStatus() {
        return statusbtn.getText().trim();
    }
    public void ClickmenuButton()
    {
    	clkmenubtn.click();
    }
    
    public void ClickLogout()
    {
    	clklogout.click();
    }

  
}