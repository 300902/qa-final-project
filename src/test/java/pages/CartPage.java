package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By cartNav = By.xpath("//a[text()='Cart']");
    private By productRow = By.xpath("//tr[contains(.,'Samsung galaxy s6')]");
    private By deleteButton = By.xpath("//tr[contains(.,'Samsung galaxy s6')]//a[text()='Delete']");
    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By cartTable = By.id("cartTable");

    public void clickCartNav() {
        driver.findElement(cartNav).click();
    }

    public boolean isProductInCart() {
        return !driver.findElements(productRow).isEmpty();
    }

    public void deleteProduct() {
        driver.findElement(deleteButton).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(productRow).isEmpty();
    }

    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
    }
}
