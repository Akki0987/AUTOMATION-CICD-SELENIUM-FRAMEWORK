package testCases.Cart;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TC12_Add_To_Cart extends BaseTest{

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
	
		@Test
		public void addToCart()
		{
		    driver.get("http://automationexercise.com");

		    // Verify homepage
		    String color = driver.findElement(By.cssSelector("ul a[href='/']")).getCssValue("color");
		    Assert.assertEquals(color, "rgba(255, 165, 0, 1)");

		    // Click Products
		    driver.findElement(By.cssSelector("a[href='/products']")).click();

		    // Remove ads
		    removeAds();

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    List<String> prodsToAdd = Arrays.asList(
		            "Men Tshirt",
		            "Premium Polo T-Shirts",
		            "Pure Cotton Neon Green Tshirt",
		            "Soft Stretch Jeans"
		    );

		    for(int i=0;i<prodsToAdd.size();i++)
		    {
		        addItemToCart(wait, prodsToAdd.get(i), i, prodsToAdd.size());
		    }
		    
//		    9. Verify all products are added to Cart
		    List<WebElement> cartItems = driver.findElements(By.cssSelector("table#cart_info_table tbody tr"));
		    for(WebElement e : cartItems)
		    {
			    Assert.assertTrue(prodsToAdd.contains(e.findElement(By.cssSelector(".cart_description a")).getText()), "Product Not Adde : "+ e.getText());
				
			    int price = Integer.parseInt(e.findElement(By.cssSelector("td.cart_price p")).getText().split(" ")[1].trim());
			    	
			    int quantity = Integer.parseInt( e.findElement(By.cssSelector("td.cart_quantity button")).getText());
			    	
			    int totalPrice = Integer.parseInt(e.findElement(By.cssSelector("td.cart_total p")).getText().split(" ")[1].trim());
			    Assert.assertEquals(price*quantity, totalPrice);     
		    }
		    
		}
	}
