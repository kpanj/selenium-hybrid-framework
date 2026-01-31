package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;

public class LoginPage {

    private WebDriver driver;

    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    By dashboardHeader = By.xpath("//h6[text()='Dashboard']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String user) {
        WaitUtil.waitForElementVisible(driver, username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        WaitUtil.waitForElementVisible(driver, password).sendKeys(pass);
    }

    public void clickLogin() {
        WaitUtil.waitForElementVisible(driver, loginBtn).click();
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
    
    
    // Validation / State check
    public boolean isLoginButtonDisplayed() {
        return WaitUtil.waitForElementVisible(driver, loginBtn).isDisplayed();
    }
    
    
    public boolean isDashboardDisplayed() {
        return WaitUtil.waitForElementVisible(driver, dashboardHeader).isDisplayed();
    }


    
}
