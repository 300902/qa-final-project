package stepDefinitions.web;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;
import pages.OrderPage;

public class PlaceOrderSteps {
    private WebDriver driver;
    private OrderPage orderPage;

    @Given("I am on the Demoblaze homepage")
    public void iAmOnDemoblazeHomepage() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        orderPage = new OrderPage(driver);
    }

    @When("I select product {string}")
    public void iSelectProduct(String productName) {
        driver.findElement(org.openqa.selenium.By.linkText(productName)).click();
    }

    @When("I add the product to the cart")
    public void iAddProductToCart() {
        driver.findElement(org.openqa.selenium.By.xpath("//a[text()='Add to cart']")).click();
        driver.switchTo().alert().accept();
    }

    @When("I go to the cart")
    public void iGoToTheCart() {
        driver.findElement(org.openqa.selenium.By.xpath("//a[text()='Cart']")).click();
    }

    @When("I click Place Order")
    public void iClickPlaceOrder() {
        orderPage.clickPlaceOrder();
    }

    @When("I enter order details {string} {string} {string} {string} {string} {string}")
    public void iEnterOrderDetails(String name, String email, String phone, String address, String city, String country) {
        orderPage.enterOrderDetails(name, email, phone, address, city, country);
    }

    @When("I confirm the purchase")
    public void iConfirmThePurchase() {
        orderPage.clickPurchase();
    }

    @Then("I should see the Thank you! message")
    public void iShouldSeeThankYouMessage() {
        Assertions.assertTrue(orderPage.isThankYouMessageDisplayed());
        driver.quit();
    }
}
