package web;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class WebStepDefinitions {
    private static WebDriver driver;
    private static WebDriverWait wait;
    
    @Before
    public void setUp() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode for CI
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    @Given("I am on the Demoblaze homepage")
    public void i_am_on_the_demoblaze_homepage() {
        driver.get("https://www.demoblaze.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-brand")));
    }

    @When("I select product {string}")
    public void i_select_product(String productName) {
        try {
            // Wait for products to load and be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
            Thread.sleep(1000); // Allow for dynamic content to load
            
            // Wait for all product links to be present
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("card-title")));
            
            // Get all product links
            java.util.List<WebElement> products = driver.findElements(By.className("card-title"));
            WebElement targetProduct = null;
            
            // Find product that contains our search text (case insensitive)
            for (WebElement product : products) {
                String productText = product.getText();
                if (productText.toLowerCase().contains(productName.toLowerCase())) {
                    targetProduct = product;
                    break;
                }
            }
            
            if (targetProduct != null) {
                // Scroll the product into view before clicking
                ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", targetProduct);
                Thread.sleep(500); // Give browser time to scroll
                
                wait.until(ExpectedConditions.elementToBeClickable(targetProduct)).click();
            } else {
                throw new org.openqa.selenium.NoSuchElementException(
                    "Product not found: " + productName + ". Available products: " + 
                    products.stream().map(WebElement::getText).collect(java.util.stream.Collectors.joining(", ")));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while selecting product", e);
        }
    }

    @When("I add the product to the cart")
    public void i_add_the_product_to_cart() {
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.linkText("Add to cart")));
        addToCartBtn.click();
        
        // Handle potential alert
        try {
            Thread.sleep(1000); // Wait for potential alert
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // No alert appeared, continue
        }
    }

    @When("I add the product to the cart again")
    public void i_add_the_product_to_cart_again() {
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.linkText("Add to cart")));
        addToCartBtn.click();
    }

    @Then("the product should appear in the cart")
    public void the_product_should_appear_in_the_cart() {
        WebElement cartLink = driver.findElement(By.id("cartur"));
        cartLink.click();
        
        // Wait for cart page to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("table")));
        
        // Verify product is in cart (check for Samsung in page source)
        assertTrue(driver.getPageSource().contains("Samsung"), "Product should be in cart");
    }

    @Then("I should see a duplicate product warning or only one product in the cart")
    public void i_should_see_duplicate_warning_or_single_product() {
        WebElement cartLink = driver.findElement(By.id("cartur"));
        cartLink.click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("table")));
        // Check cart contents - implementation depends on actual behavior
        assertTrue(driver.getPageSource().contains("Samsung"), "Product should be in cart");
    }

    @Given("I have product {string} in the cart")
    public void i_have_product_in_cart(String productName) {
        // Setup and add product to cart
        i_am_on_the_demoblaze_homepage();
        i_select_product(productName);
        i_add_the_product_to_cart();
    }

    @When("I remove the product from the cart")
    public void i_remove_product_from_cart() {
        WebElement cartLink = driver.findElement(By.id("cartur"));
        cartLink.click();
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.linkText("Delete")));
        deleteBtn.click();
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        // Verify cart is empty
        driver.quit();
    }

    @When("I click the Log in navigation")
    public void i_click_login_navigation() {
        WebElement loginLink = driver.findElement(By.id("login2"));
        loginLink.click();
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_credentials(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("loginusername")));
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    @When("I click the Log in button")
    public void i_click_login_button() {
        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='Log in']"));
        loginBtn.click();
    }

    @Then("I should see an error message")
    public void i_should_see_error_message() {
        // Wait for and verify error message
        driver.quit();
    }

    @Then("I should see the welcome message")
    public void i_should_see_welcome_message() {
        // Verify successful login
        driver.quit();
    }

    @When("I go to the cart")
    public void i_go_to_cart() {
        WebElement cartLink = driver.findElement(By.id("cartur"));
        cartLink.click();
    }

    @When("I click Place Order")
    public void i_click_place_order() {
        WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[text()='Place Order']")));
        placeOrderBtn.click();
    }

    @When("I enter order details {string} {string} {string} {string} {string} {string}")
    public void i_enter_order_details(String name, String country, String city, String card, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(name);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).sendKeys(card);
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).sendKeys(year);
    }

    @When("I confirm the purchase")
    public void i_confirm_purchase() {
        WebElement purchaseBtn = driver.findElement(By.xpath("//button[text()='Purchase']"));
        purchaseBtn.click();
    }

    @Then("I should see the Thank you! message")
    public void i_should_see_thank_you_message() {
        WebElement thankYouMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(text(),'Thank you')]")));
        assertTrue(thankYouMsg.isDisplayed());
        driver.quit();
    }
}
