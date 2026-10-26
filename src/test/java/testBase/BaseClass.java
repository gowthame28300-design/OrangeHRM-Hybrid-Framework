
package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Locale;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pageObjects.LoginPage;
import utilities.WaitUtility;

public class BaseClass {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    protected WaitUtility waitutil;
    protected Properties p;
    protected Logger logger;

    // Store the WebDriver for the current thread
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Retrieve the WebDriver for the current thread
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Initialize browser and application
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws IOException {

        logger = LogManager.getLogger(getClass());

        loadProperties();
        initializeBrowser(browser);

        WebDriver webDriver = getDriver();

        webDriver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));

        webDriver.manage().timeouts()
                .implicitlyWait(Duration.ZERO);

        webDriver.manage().window().maximize();

        try {
            webDriver.get(p.getProperty("appURL"));
        } catch (TimeoutException e) {
            logger.warn("Page load timed out. Current URL: {}",
                    webDriver.getCurrentUrl());
        }

        waitutil = new WaitUtility(webDriver);

        waitutil.visibility(By.name("username"));

        logger.info("OrangeHRM application opened successfully.");
    }

    // Load application configuration
    private void loadProperties() throws IOException {

        p = new Properties();

        String configPath = System.getProperty("user.dir")
                + "/src/test/resources/config.properties";

        try (FileInputStream file = new FileInputStream(configPath)) {
            p.load(file);
        }

        if (p.getProperty("appURL") == null
                || p.getProperty("appURL").isBlank()) {
            throw new IllegalStateException(
                    "appURL is missing from config.properties");
        }
    }

    // Initialize the selected browser
    private void initializeBrowser(String browser) {

        WebDriver webDriver;

        switch (browser.toLowerCase(Locale.ROOT)) {

        
        case "chrome":

            ChromeOptions chromeOptions = new ChromeOptions();

            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            chromeOptions.addArguments("--lang=en-US");

            webDriver = new ChromeDriver(chromeOptions);

            break;
      

        case "firefox":
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            webDriver = new FirefoxDriver(firefoxOptions);
            break;

        case "edge":
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            webDriver = new EdgeDriver(edgeOptions);
            break;

        default:
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }

        setDriver(webDriver);
        logger.info("{} browser initialized successfully.", browser);
    }

    // Login to OrangeHRM
    public void login() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.enterUsername(p.getProperty("username"));
        loginPage.enterPassword(p.getProperty("password"));
        loginPage.clickLogin();

        logger.info("Login steps completed.");
    }

    // Close browser and clean up the current thread
    @AfterClass(alwaysRun = true)
    public void teardown() {

        WebDriver webDriver = getDriver();

        try {
            if (webDriver != null) {
                webDriver.quit();
                logger.info("Browser closed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error while closing browser.", e);
        } finally {
            driver.remove();
        }
    }
}

