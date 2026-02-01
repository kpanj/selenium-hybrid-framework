package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        test = extent.createTest("Login Test");

        LoginPage login = new LoginPage(DriverFactory.getDriver());
        login.login("Admin", "admin123");

        if (login.isDashboardDisplayed()) {
            test.pass("Dashboard is displayed");
        } else {
            test.fail("Dashboard not displayed");
        }
    }
}
