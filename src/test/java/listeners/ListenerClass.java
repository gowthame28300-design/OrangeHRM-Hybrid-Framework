package listeners;

import java.awt.Desktop;
import java.io.File;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import testBase.BaseClass;
import utilities.CaptureScreen;
import utilities.ExtentReportManager;

public class ListenerClass implements ITestListener, ISuiteListener {

   

    private static final ThreadLocal<ExtentTest> TEST =
            new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.getExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {

        String browser = result.getTestContext()
                .getCurrentXmlTest()
                .getParameter("browser");

        if (browser == null || browser.isBlank()) {
            browser = "Unknown Browser";
        }

        String testName =
                result.getMethod().getMethodName()
                + " [" + browser + "]";

        System.out.println("Listener: " + testName);

        TEST.set(ExtentReportManager.getExtentReport()
                .createTest(testName));
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest extentTest = TEST.get();

        if (extentTest != null) {
            extentTest.pass("Test Passed");
        }

        TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = TEST.get();

        try {
            if (extentTest == null) {
                return;
            }

            extentTest.fail(result.getThrowable());

            try {
                String path = CaptureScreen.captureScreenshot(
                        BaseClass.getDriver(),
                        result.getMethod().getMethodName()
                );

                extentTest.addScreenCaptureFromPath(path);

            } catch (Exception e) {
                extentTest.warning(
                        "Screenshot capture failed: " + e.getMessage()
                );
            }

        } finally {
            TEST.remove();
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest extentTest = TEST.get();

        if (extentTest != null) {
            extentTest.skip("Test Skipped");
        }

        TEST.remove();
    }

    @Override
    public void onFinish(ISuite suite) {

        ExtentReportManager.getExtentReport().flush();

        try {

            File report =
                    new File(ExtentReportManager.REPORT_PATH);

            if (Desktop.isDesktopSupported()
                    && report.exists()) {

                Desktop.getDesktop().browse(report.toURI());
            }

        } catch (Exception e) {

            System.err.println(
                    "Unable to open Extent Report: "
                    + e.getMessage());
        }
    }
}