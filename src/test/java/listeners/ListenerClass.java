package listeners;

import java.awt.Desktop;
import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import testBase.BaseClass;
import utilities.CaptureScreen;
import utilities.ExtentReportManager;

public class ListenerClass implements ITestListener {

    private ExtentReports extent;

    // Thread-safe ExtentTest
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

   

    @Override
    public void onStart(ITestContext context) {

    	extent = ExtentReportManager.getExtentReport();

    }

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("Listener: " + result.getMethod().getMethodName());

        String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");

        String testName = result.getMethod().getMethodName() + " [" + browser + "]";

        test.set(extent.createTest(testName));
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");

        test.remove();

    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        try {

            String path = CaptureScreen.captureScreenshot(
                    BaseClass.getDriver(),
                    result.getMethod().getMethodName());

            test.get().addScreenCaptureFromPath(path);

        } catch (Exception e) {

            e.printStackTrace();

        }

        test.remove();

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");

        test.remove();

    }

   
    @Override
    public void onFinish(ITestContext context) {

        synchronized (this) {
            extent.flush();
        }

        try {

            Thread.sleep(1000);

            File report = new File(
                    System.getProperty("user.dir")
                    + "/test-output/ExtentReport.html");

            Desktop.getDesktop().browse(report.toURI());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }

