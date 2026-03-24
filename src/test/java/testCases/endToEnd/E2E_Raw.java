package testCases.endToEnd;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class E2E_Raw extends BaseTest {

    // =========================
    // Test Data
    // =========================
    String name = "Akki";
    String email = "dailyHustlin44@gmail.com";
    String password = "Broken@098";
    String fName = "Abhishek";
    String lName = "Chauhan";
    String company = "Nugats Technologies";
    String address = "Sector 62";
    String address2 = "Azad Vihar Colony, Block A";
    String city = "Noida";
    String state = "UttarPradesh";
    String zipcode = "280001";
    String country = "India";
    String mobNo = "8494045494";

    // =========================
    // Step 1: Register user and login
    // =========================
    public void registerUserAndLogin() throws InterruptedException {

    	
        // Navigate to application URL
        driver.get("http://automationexercise.com");

        // Verify home page is displayed successfully
        String homeColor = driver.findElement(By.cssSelector("ul a[href='/']")).getCssValue("color");
        Assert.assertEquals(homeColor, "rgba(255, 165, 0, 1)", "Home page is not visible");

        // Click on 'Signup / Login'
        driver.findElement(By.cssSelector("ul a[href='/login']")).click();

        // Verify 'New User Signup!' is visible
        String signupHeader = driver.findElement(By.cssSelector("div.signup-form h2")).getText();
        Assert.assertEquals(signupHeader, "New User Signup!", "Signup section not visible");

        // Enter name and email for registration
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.cssSelector("form[action='/signup'] input[placeholder='Email Address']")).sendKeys(email);

        // Click Signup button
        driver.findElement(By.cssSelector("button[data-qa$='signup-button']")).click();

        // Verify account information page
        String accountInfoText = driver.findElement(By.xpath("//div[@class='login-form']/h2/b")).getText();
        Assert.assertEquals(accountInfoText, "ENTER ACCOUNT INFORMATION", "Account information page not opened");

        // Select title
        driver.findElement(By.id("id_gender1")).click();

        // Verify prefilled name and email
        Assert.assertEquals(driver.findElement(By.id("name")).getDomAttribute("value"), name, "Name not prefilled correctly");
        Assert.assertEquals(driver.findElement(By.id("email")).getDomAttribute("value"), email, "Email not prefilled correctly");

        // Enter password
        driver.findElement(By.id("password")).sendKeys(password);

        // Select date of birth
        new Select(driver.findElement(By.id("days"))).selectByValue("28");
        new Select(driver.findElement(By.id("months"))).selectByVisibleText("October");
        new Select(driver.findElement(By.id("years"))).selectByValue("2002");

        // Select newsletter and offers checkboxes
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();

        // Fill address details
        driver.findElement(By.id("first_name")).sendKeys(fName);
        driver.findElement(By.id("last_name")).sendKeys(lName);
        driver.findElement(By.id("company")).sendKeys(company);
        driver.findElement(By.id("address1")).sendKeys(address);
        driver.findElement(By.id("address2")).sendKeys(address2);

        new Select(driver.findElement(By.id("country"))).selectByVisibleText(country);

        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zipcode")).sendKeys(zipcode);
        driver.findElement(By.id("mobile_number")).sendKeys(mobNo);

        // Create account
        driver.findElement(By.cssSelector("button[data-qa='create-account']")).click();

        Thread.sleep(2000);
        
        // Verify account creation success
        Assert.assertTrue(driver.getCurrentUrl().contains("account_created"), "Account was not created");

        // Click Continue
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
    }

    // =========================
    // Step 2: Add a product to cart
    // =========================
    public void addItemToCart(String productName, int index, int totalProducts) {

        // Locate product card using product name
        WebElement product = driver.findElement(
                By.xpath("//p[normalize-space()='" + productName + "']/ancestor::div[@class='product-image-wrapper']")
        );

        // Scroll product into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);

        // Hover over product card to reveal Add to Cart button
        new Actions(driver).moveToElement(product).perform();

        // Click Add to Cart button using JavaScript
        WebElement addToCartBtn = product.findElement(By.cssSelector(".overlay-content a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

        // Wait for confirmation modal
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));

        // Verify product added successfully
        Assert.assertEquals(modal.findElement(By.tagName("h4")).getText().trim(), "Added!", "Product not added to cart");

        // If last product, go to cart; otherwise continue shopping
        if (index == totalProducts - 1) {
            driver.findElement(By.cssSelector("div.modal-body a[href='/view_cart']")).click();
        } else {
            driver.findElement(By.cssSelector("div.modal-footer button")).click();
        }
    }

    // =========================
    // Step 3: Verify delivery/billing address
    // =========================
    public void verifyAddressDetails(String addressType) {

        String baseLocator = addressType.equalsIgnoreCase("delivery")
                ? "ul#address_delivery"
                : "ul#address_invoice";

        String actualName = driver.findElement(
                By.cssSelector(baseLocator + " li.address_firstname.address_lastname"))
                .getText().trim();

        String actualCompany = driver.findElement(
                By.cssSelector(baseLocator + " li:nth-of-type(3)"))
                .getText().trim();

        String actualAddress1 = driver.findElement(
                By.cssSelector(baseLocator + " li:nth-of-type(4)"))
                .getText().trim();

        String actualAddress2 = driver.findElement(
                By.cssSelector(baseLocator + " li:nth-of-type(5)"))
                .getText().trim();

        String actualCityStateZip = driver.findElement(
                By.cssSelector(baseLocator + " li.address_city.address_state_name.address_postcode"))
                .getText().replaceAll("\\s+", " ").trim();

        String actualCountry = driver.findElement(
                By.cssSelector(baseLocator + " li.address_country_name"))
                .getText().trim();

        String actualMobile = driver.findElement(
                By.cssSelector(baseLocator + " li.address_phone"))
                .getText().trim();

        // Verify all address fields
        Assert.assertEquals(actualName, "Mr. " + fName + " " + lName, addressType + " name mismatch");
        Assert.assertEquals(actualCompany, company, addressType + " company mismatch");
        Assert.assertEquals(actualAddress1, address, addressType + " address line 1 mismatch");
        Assert.assertEquals(actualAddress2, address2, addressType + " address line 2 mismatch");
        Assert.assertEquals(actualCityStateZip, city + " " + state + " " + zipcode, addressType + " city/state/zipcode mismatch");
        Assert.assertEquals(actualCountry, country, addressType + " country mismatch");
        Assert.assertEquals(actualMobile, mobNo, addressType + " mobile number mismatch");
    }

    // =========================
    // Step 4: Verify products added in cart
    // =========================
    public void verifyCartProducts(List<String> expectedProducts) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("table#cart_info_table tbody tr")));

        List<WebElement> rows = driver.findElements(By.cssSelector("table#cart_info_table tbody tr"));
        Assert.assertEquals(rows.size(), expectedProducts.size(), "Cart item count mismatch");

        for (int i = 0; i < rows.size(); i++) {

            // Re-fetch row each iteration to avoid stale element issues
            WebElement row = driver.findElements(By.cssSelector("table#cart_info_table tbody tr")).get(i);

            String productName = row.findElement(By.cssSelector(".cart_description a")).getText().trim();
            Assert.assertTrue(expectedProducts.contains(productName), "Unexpected product in cart: " + productName);

            int price = Integer.parseInt(
                    row.findElement(By.cssSelector("td.cart_price p"))
                            .getText()
                            .replace("Rs.", "")
                            .trim()
            );

            int quantity = Integer.parseInt(
                    row.findElement(By.cssSelector("td.cart_quantity button"))
                            .getText()
                            .trim()
            );

            int totalPrice = Integer.parseInt(
                    row.findElement(By.cssSelector("td.cart_total p"))
                            .getText()
                            .replace("Rs.", "")
                            .trim()
            );

            // Verify total = price × quantity
            Assert.assertEquals(price * quantity, totalPrice, "Price mismatch for product: " + productName);
        }
    }

    // =========================
    // Step 5: Delete account after order
    // =========================
    public void deleteAccount() {

        // Click Delete Account
        driver.findElement(By.cssSelector("a[href='/delete_account']")).click();

        // Wait until delete account page loads
        wait.until(ExpectedConditions.urlContains("delete_account"));

        // Verify account deleted message
        Assert.assertTrue(driver.getCurrentUrl().contains("delete_account"), "Delete account page did not open");

        String deletedMessage = driver.findElement(By.cssSelector("h2[data-qa='account-deleted']")).getText();
        Assert.assertEquals(deletedMessage, "ACCOUNT DELETED!", "Account deletion failed");

        // Click Continue
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
    }

    // =========================
    // End-to-End Test: Register -> Add Products -> Checkout -> Order -> Delete Account
    // =========================
    @Test
    public void registerAndOrder() throws InterruptedException {

        // Register user and continue to logged-in session
        registerUserAndLogin();

        // Verify user is logged in
        WebElement loggedInText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(text(),'Logged in as')]"))
        );
        Assert.assertEquals(loggedInText.getText().trim(), "Logged in as " + name, "Logged in username mismatch");

        // Navigate to Products page
        driver.findElement(By.cssSelector("a[href='/products']")).click();

        // Products to be added into cart
        List<String> productsToAdd = Arrays.asList(
                "Men Tshirt",
                "Premium Polo T-Shirts",
                "Pure Cotton Neon Green Tshirt",
                "Soft Stretch Jeans"
        );

        // Add all products to cart
        for (int i = 0; i < productsToAdd.size(); i++) {
            addItemToCart(productsToAdd.get(i), i, productsToAdd.size());
        }

        // Verify products in cart
        verifyCartProducts(productsToAdd);

        // Click Proceed to Checkout
        WebElement checkoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a.check_out"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);

        // Verify delivery and billing address details
        verifyAddressDetails("delivery");
        verifyAddressDetails("billing");

        // Add order comment
        driver.findElement(By.tagName("textarea"))
                .sendKeys("Please pack the items properly and deliver on time.");

        // Proceed to payment page
        driver.findElement(By.cssSelector("a[href='/payment']")).click();

        // Enter payment details
        driver.findElement(By.name("name_on_card")).sendKeys(name);
        driver.findElement(By.name("card_number")).sendKeys("11445566");
        driver.findElement(By.name("cvc")).sendKeys("145");
        driver.findElement(By.name("expiry_month")).sendKeys("10");
        driver.findElement(By.name("expiry_year")).sendKeys("2050");

        // Scroll to Place Order button and click
        WebElement payBtn = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payBtn);
        wait.until(ExpectedConditions.elementToBeClickable(payBtn)).click();

        // Verify order placed successfully
        Assert.assertEquals(driver.findElement(By.cssSelector("h2 b")).getText().trim(),
                "ORDER PLACED!", "Order was not placed successfully");

        // Download invoice
        driver.findElement(By.cssSelector("a[href*='/download_invoice']")).click();

        // Continue after order placed
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();

        // Delete created account
        deleteAccount();
    }
}