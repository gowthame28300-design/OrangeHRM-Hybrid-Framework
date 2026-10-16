package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    
    @FindBy(xpath="(//input[@placeholder='yyyy-dd-mm'])[2]")
    WebElement dob;
    
    
    // ===========================
    // ACTION METHODS
    // ===========================

    public void editMiddleName(String mname) {
    	waitutil.invisibility(By.className("oxd-loading-spinner"));

    	/*WebElement mdl=waitutil.visibility(By.xpath("//input[@name='middleName']"));
    	 
    	waitutil.clickable(mdl);
    	mdl.click(); */
    	
    	mdlname.sendKeys(Keys.CONTROL + "a");
    	mdlname.sendKeys(Keys.DELETE);
    	
    	mdlname.sendKeys(mname);
        
    }
    
    public void enterLicenceDate() {
    	waitutil.invisibility(By.className("oxd-loading-spinner"));
    	/*waitutil.clickable(licenceDate);

    	licenceDate.click();*/
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

       WebElement maritalOption= driver.findElement(
            By.xpath("//div[@role='option'][normalize-space()='" + status + "']"));
    waitutil.clickable(maritalOption);
    maritalOption.click();
    }
    
    public void DateOfBirth()
    {
    	dob.sendKeys("2000-28-03");
    }
    public void clickMaleButton() {

       

       waitutil.invisibility( By.className("oxd-form-loader"));
               

       WebElement mle=waitutil.clickable(By.xpath("//label[normalize-space()='Male']"));

        mle.click();
    }

    public void clickSave() {

       

        waitutil.invisibility( By.className("oxd-form-loader"));       

        waitutil.clickable(savebtn);

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

      

      waitutil.clickable(save2btn); 

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

    public boolean isPersonalDetailsPageDisplayed() {

        return waitutil.visibility(
            By.xpath("//h6[normalize-space()='Personal Details']")
        ).isDisplayed();
    }
    public String getEmployeeName() {
        return waitutil.visibility(
            By.xpath("//h6[normalize-space()='Gowtham Tester']")
        ).getText().trim();
    }
}