package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentReportManager {

    private static ExtentReports extent;

    public static final String REPORT_PATH =
            System.getProperty("user.dir")
            + "/test-output/ExtentReport.html";

    private ExtentReportManager() {
        // Prevent object creation
    }

    public static synchronized ExtentReports getExtentReport() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(REPORT_PATH);

            spark.config().setDocumentTitle(
                    "OrangeHRM Automation Report");

            spark.config().setReportName(
                    "Regression Test Report");

            spark.config().setTheme(Theme.DARK);

            spark.config().setTimeStampFormat(
                    "dd-MM-yyyy hh:mm:ss a");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Application", "OrangeHRM");
            extent.setSystemInfo("Automation Tester", "Gowtham");
            extent.setSystemInfo(
                    "Operating System",
                    System.getProperty("os.name"));

            extent.setSystemInfo(
                    "Java Version",
                    System.getProperty("java.version"));
        }

        return extent;
    }
}