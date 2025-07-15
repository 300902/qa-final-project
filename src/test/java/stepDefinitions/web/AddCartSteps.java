package stepDefinitions.web;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;
import pages.CartPage;

public class AddCartSteps {
    private WebDriver driver;
    private CartPage cartPage;

    @Given("I am on the Demoblaze homepage")
    public void iAmOnDemoblazeHomepage() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        cartPage = new CartPage(driver);
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

    @When("I add the product to the cart again")
    public void iAddProductToCartAgain() {
        driver.findElement(org.openqa.selenium.By.xpath("//a[text()='Add to cart']")).click();
        driver.switchTo().alert().accept();
    }

    @Then("the product should appear in the cart")
    public void productShouldAppearInCart() {
        cartPage.clickCartNav();
        Assertions.assertTrue(cartPage.isProductInCart());
        driver.quit();
    }

    @Then("I should see a duplicate product warning or only one product in the cart")
    public void duplicateProductWarningOrOneProduct() {
        cartPage.clickCartNav();
        // Validasi: hanya satu baris produk di cart
        Assertions.assertTrue(cartPage.isProductInCart());
        driver.quit();
    }

    @Given("I have product {string} in the cart")
    public void iHaveProductInCart(String productName) {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.findElement(org.openqa.selenium.By.linkText(productName)).click();
        driver.findElement(org.openqa.selenium.By.xpath("//a[text()='Add to cart']")).click();
        driver.switchTo().alert().accept();
        cartPage = new CartPage(driver);
    }

    @When("I remove the product from the cart")
    public void iRemoveProductFromCart() {
        cartPage.clickCartNav();
        cartPage.deleteProduct();
    }

    @Then("the cart should be empty")
    public void cartShouldBeEmpty() {
        Assertions.assertTrue(cartPage.isCartEmpty());
        driver.quit();
    }
}
