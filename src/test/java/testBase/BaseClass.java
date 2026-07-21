package testBase;

import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pageObjects.LoginPage;
import utilities.WaitUtility;


public class BaseClass {

    //public static WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public  WaitUtility waitutil;
    public Properties p;
    public Logger logger;
    
    public static void setDriver(WebDriver driverInstance)
    {
        driver.set(driverInstance);
    }
    
    public static WebDriver getDriver()
    {
        return driver.get();
    }

   
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws IOException {
    	
    	
    	 p = new Properties();

    	 FileInputStream file =
    		        new FileInputStream(System.getProperty("user.dir")
    		        + "/src/test/resources/config.properties");
    	 p.load(file);
    	
    	 logger = LogManager.getLogger(this.getClass());

    	 if(browser.equalsIgnoreCase("chrome"))
    	 {
    		 setDriver(new ChromeDriver());
    	 }
    	 else if(browser.equalsIgnoreCase("edge"))
    	 {
    		 setDriver(new EdgeDriver());
    	 }
    	 else if(browser.equalsIgnoreCase("firefox"))
    	 {
    		 setDriver(new FirefoxDriver());
    	 }
    	 else
    	 {
    	     throw new IllegalArgumentException("Invalid browser: " + browser);
    	 }
    	 waitutil = new WaitUtility(getDriver());
        
    	 getDriver().get(p.getProperty("appURL"));

    	 getDriver().manage().window().maximize();

    	 getDriver().manage().timeouts()
    	            .implicitlyWait(Duration.ofSeconds(10));
        
        logger.info("Browser launched successfully");
        p.load(file);
        file.close();
          }
    
    public void login() {
    	LoginPage  loginPage = new LoginPage(getDriver());
        loginPage.username(p.getProperty("username"));
        loginPage.password(p.getProperty("password"));
        loginPage.login();
        logger.info("Logging into OrangeHRM");
    }

    @AfterClass
    public void teardown() {
    	if (getDriver() != null) {
    	    getDriver().quit();
    	    driver.remove();
    	}
        logger.info("Browser closed");
    }
}