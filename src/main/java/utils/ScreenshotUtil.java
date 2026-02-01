package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + ".png";

        try {
            File target = new File(dest);
            target.getParentFile().mkdirs(); // create folder if not exists
            Files.copy(src.toPath(), target.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dest;
    }
}
