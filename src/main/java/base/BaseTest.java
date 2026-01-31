package base;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import config.ConfigReader;
import utils.DriverFactory;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManager.getExtent();
    }

    @BeforeMethod
    public void setUp(Method method) {
        DriverFactory.initDriver(ConfigReader.getProperty("browser"));
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));

        // Create test entry in Extent
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } 
        else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } 
        else {
            test.skip("Test Skipped");
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }
}
