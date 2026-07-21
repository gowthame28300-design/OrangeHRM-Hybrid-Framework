package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreen {

	public static String captureScreenshot(WebDriver driver, String testName) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		String targetFilePath = System.getProperty("user.dir")
		        + File.separator
		        + "Screenshots"
		        + File.separator
		        + testName + "_" + timeStamp + ".png";

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		File target = new File(targetFilePath);

		FileUtils.copyFile(source, target);

		return targetFilePath;
	}

}