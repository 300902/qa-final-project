package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By nameInput = By.id("name");
    private By countryInput = By.id("country");
    private By cityInput = By.id("city");
    private By creditCardInput = By.id("card");
    private By monthInput = By.id("month");
    private By yearInput = By.id("year");
    private By purchaseButton = By.xpath("//button[text()='Purchase']");
    private By thankYouMessage = By.xpath("//h2[text()='Thank you!']");

    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
    }

    public void enterOrderDetails(String name, String email, String phone, String address, String city, String country) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(countryInput).sendKeys(country);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(creditCardInput).sendKeys(phone);
        driver.findElement(monthInput).sendKeys("01");
        driver.findElement(yearInput).sendKeys("2025");
    }

    public void clickPurchase() {
        driver.findElement(purchaseButton).click();
    }

    public boolean isThankYouMessageDisplayed() {
        return driver.findElement(thankYouMessage).isDisplayed();
    }
}
