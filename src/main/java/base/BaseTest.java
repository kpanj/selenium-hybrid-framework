package base;

import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.DriverFactory;
import utils.ExtentManager;
import config.ConfigReader;
import utils.ScreenshotUtil;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;

public class BaseTest {

    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getExtent();
    }

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver(ConfigReader.getProperty("browser"));
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
       
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(DriverFactory.getDriver(), result.getName());
            test.log(Status.FAIL, result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped");
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();

            // Open report automatically in default browser
            try {
                File htmlFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
