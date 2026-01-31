package tests;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginTest extends BaseTest {

	@Test
	public void loginTest() {
	    LoginPage login = new LoginPage(DriverFactory.getDriver());
	    login.login("Admin", "admin123");
	    //Assert.assertTrue(login.isLoginButtonDisplayed());
	    Assert.assertTrue(login.isDashboardDisplayed(), "Dashboard not visible after login");


	}

}
