package stepDefinitions.web;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("I am on the Demoblaze homepage")
    public void iAmOnDemoblazeHomepage() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        loginPage = new LoginPage(driver);
    }

    @When("I click the Log in navigation")
    public void iClickLoginNav() {
        loginPage.clickLoginNav();
    }

    @When("I enter username {string} and password {string}")
    public void iEnterCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click the Log in button")
    public void iClickLoginButton() {
        loginPage.clickLoginModalButton();
    }

    @Then("I should see the welcome message")
    public void iShouldSeeWelcomeMessage() {
        Assertions.assertTrue(loginPage.isWelcomeMessageDisplayed());
        driver.quit();
    }

    @Then("I should see an error message")
    public void iShouldSeeErrorMessage() {
        Assertions.assertTrue(loginPage.isErrorAlertDisplayed());
        driver.quit();
    }
}
