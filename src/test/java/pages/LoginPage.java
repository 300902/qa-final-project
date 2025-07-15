package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By loginNav = By.xpath("//a[text()='Log in']");
    private By usernameInput = By.id("loginusername");
    private By passwordInput = By.id("loginpassword");
    private By loginModalButton = By.xpath("//button[text()='Log in']");
    private By welcomeMessage = By.id("nameofuser");
    private By errorAlert = By.xpath("//div[@class='modal fade show']//div[contains(text(),'Wrong password.')] | //div[@class='modal fade show']//div[contains(text(),'User does not exist.')] ");

    // Actions
    public void clickLoginNav() {
        driver.findElement(loginNav).click();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginModalButton() {
        driver.findElement(loginModalButton).click();
    }

    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    public boolean isErrorAlertDisplayed() {
        return !driver.findElements(errorAlert).isEmpty();
    }
}
