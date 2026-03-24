package testCases.AllTestCases;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TC20_Proceed_To_Checkout extends BaseTest{

	public void removeAds()
	{
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    js.executeScript(
	        "document.querySelectorAll('.adsbygoogle').forEach(el => el.remove());" +
	        "document.querySelectorAll('iframe[id^=\"aswift_\"]').forEach(el => el.remove());" +
	        "document.querySelectorAll('iframe[src*=\"doubleclick\"]').forEach(el => el.remove());"
	    );
	}
	
	public void addItemToCart(WebDriverWait wait, String text, int index, int total)
	{
	    // Locate product card
	    WebElement product = driver.findElement(
	            By.xpath("//p[normalize-space()='"+text+"']/ancestor::div[@class='product-image-wrapper']")
	    );

	    // Scroll to product
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", product);

	    // Hover product card
	    Actions a = new Actions(driver);
	    a.moveToElement(product).perform();

	    WebElement addBtn = product.findElement(By.cssSelector(".overlay-content a"));

	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", addBtn);

	    // Wait for modal
	    WebElement modal = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.id("cartModal"))
	    );

	    Assert.assertTrue(
	            modal.findElement(By.tagName("h4")).getText().equalsIgnoreCase("Added!"),
	            "Product not added to cart"
	    );

	    // Last product -> View Cart
	    if(index == total - 1)
	    {
	        driver.findElement(By.cssSelector("div.modal-body a[href='/view_cart']")).click();
	    }
	    else
	    {
	        driver.findElement(By.cssSelector("div.modal-footer button")).click();
	    }
	}
	
//	public void verifyAddressBillingDetails()
//	{
//		String firstLastName = driver.findElement(By.cssSelector("ul#address_delivery li.address_firstname.address_lastname")).getText();
//		String company = driver.findElement(By.cssSelector("ul#address_delivery li:nth-of-type(3)")).getText();
//		String address = driver.findElement(By.cssSelector("ul#address_delivery li:nth-of-type(4)")).getText();
//		String address2 = driver.findElement(By.cssSelector("ul#address_delivery li:nth-of-type(5)")).getText();
//		String cityStateZipcode = driver.findElement(By.cssSelector("ul#address_delivery li:nth-of-type(6)")).getText();
//		String country = driver.findElement(By.cssSelector("ul#address_delivery li:nth-of-type(7)")).getText();
//		String mobNo = driver.findElement(By.cssSelector("ul#address_delivery li:nth-of-type(8)")).getText();
//		
//		Assert.assertTrue(false);
//	}
	@Test
	public void proceedToCheckout()
	{
			driver.get("http://automationexercise.com");
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // Verify homepage
		    String color = driver.findElement(By.cssSelector("ul a[href='/']")).getCssValue("color");
		    Assert.assertEquals(color, "rgba(255, 165, 0, 1)");
			
//			4. Click on 'Signup / Login' button
			driver.findElement(By.cssSelector("ul a[href='/login']")).click();

//			5. Verify 'Login to your account' is visible
			String nUser = driver.findElement(By.cssSelector("div.login-form h2")).getText();
			Assert.assertEquals(nUser, "Login to your account");
			
//			6. Enter correct email address and password
			driver.findElement(By.cssSelector("form[action='/login'] input[type='email']")).sendKeys("akkii@gmail.com");
			driver.findElement(By.cssSelector("form[action='/login'] input[type='password']")).sendKeys(Keys.chord("Akki@123", Keys.ENTER));

////			7. Click 'login' button
//			WebElement loginBtn = wait.until(
//				    ExpectedConditions.elementToBeClickable(
//				        By.cssSelector("form[action='/login'] button[data-qa='login-button']")
//				    )
//				);
//			
//			Actions a = new Actions(driver);
//			a.moveToElement(loginBtn).click().build().perform();
			

			// Wait until logged in user label is visible
			WebElement loggedInText = wait.until(
			        ExpectedConditions.visibilityOfElementLocated(
			                By.xpath("//a[contains(text(),'Logged in as')]")
			        )
			);

			// Verify text
			Assert.assertEquals(loggedInText.getText().trim(), "Logged in as Abhishek Kholiya");
			
		    // Click Products
		    driver.findElement(By.cssSelector("a[href='/products']")).click();

		    // Remove ads
		    removeAds();

		    List<String> prodsToAdd = Arrays.asList(
		            "Men Tshirt",
		            "Premium Polo T-Shirts",
		            "Pure Cotton Neon Green Tshirt",
		            "Soft Stretch Jeans"
		    );

//		    Add items to cart 
		    for(int i=0;i<prodsToAdd.size();i++)
		    {
		        addItemToCart(wait, prodsToAdd.get(i), i, prodsToAdd.size());
		    }
		    
//		    9. Verify all products are added to Cart
		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.cssSelector("table#cart_info_table tbody tr")
		    ));

		    List<WebElement> rows = driver.findElements(By.cssSelector("table#cart_info_table tbody tr"));
		    Assert.assertEquals(rows.size(), prodsToAdd.size(), "Cart item count mismatch");

		    for (int i = 0; i < rows.size(); i++) {

		        WebElement row = driver.findElements(By.cssSelector("table#cart_info_table tbody tr")).get(i);

		        String productName = row.findElement(By.cssSelector(".cart_description a")).getText().trim();
		        Assert.assertTrue(prodsToAdd.contains(productName), "Product Not Added : " + productName);

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

		        Assert.assertEquals(price * quantity, totalPrice, "Price mismatch for: " + productName);
		    }

		    // click checkout only once, after loop
		    WebElement checkoutBtn = wait.until(
		            ExpectedConditions.elementToBeClickable(By.cssSelector("a.check_out"))
		    );
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
			    
//		    Verify checkout page is opened 
		    wait.until(ExpectedConditions.urlContains("checkout"));
		    
//			    11. Verify Address Details and Review Your Order
		    
//			    12. Enter description in comment text area and click 'Place Order'
//			    13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
//			    14. Click 'Pay and Confirm Order' button
//			    15. Verify success message 'Your order has been placed successfully!'
//			    16. Click 'Delete Account' button
//			    17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		    }
		    
	}

