package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class CaptureScreen {

    private static final String SCREENSHOT_DIRECTORY = "Screenshots";

    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private CaptureScreen() {
        // Prevent object creation
    }

    public static String captureScreenshot(
            WebDriver driver,
            String testName) throws IOException {

        if (driver == null) {
            throw new IllegalArgumentException(
                    "WebDriver must not be null.");
        }

        if (!(driver instanceof TakesScreenshot)) {
            throw new IllegalArgumentException(
                    "This WebDriver does not support screenshots.");
        }

        String timestamp = LocalDateTime.now()
                .format(TIMESTAMP_FORMAT);

        String safeTestName = testName.replaceAll(
                "[^a-zA-Z0-9._-]", "_");

        Path screenshotDirectory = Paths.get(
                System.getProperty("user.dir"),
                SCREENSHOT_DIRECTORY
        );

        Files.createDirectories(screenshotDirectory);

        Path screenshotPath = screenshotDirectory.resolve(
                safeTestName + "_" + timestamp + ".png"
        );

        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File target = screenshotPath.toFile();

        FileUtils.copyFile(source, target);

        return target.getAbsolutePath();
    }
}