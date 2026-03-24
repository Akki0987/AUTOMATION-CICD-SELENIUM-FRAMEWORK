package testCases.Product_Search;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TC08_Verify_All_Products_And_Product_Detail_Page extends BaseTest{

	@Test
	public void verifyAllProducts()
	{
//		1. Launch browser
//		2. Navigate to url 'http://automationexercise.com'
		driver.get("http://automationexercise.com");

//		3. Verify that home page is visible successfully
		String color = driver.findElement(By.cssSelector("ul a[href='/']")).getCssValue("color");
		Assert.assertEquals(color, "rgba(255, 165, 0, 1)");
		
//		4. Click on 'Products' button
		driver.findElement(By.cssSelector("a[href='/products']")).click();
		
//		Handle google ad 
		driver.switchTo().frame("google_esf");
		driver.switchTo().frame("aswift_3");
		driver.findElement(By.cssSelector("div#dismiss-button div")).click();
		driver.switchTo().defaultContent();
		
//		5. Verify user is navigated to ALL PRODUCTS page successfully
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("products"));
		
		Assert.assertTrue(driver.getCurrentUrl().contains("products"), "Unable to redirect to products page");

//		6. The products list is visible
		WebElement prodSection = driver.findElement(By.cssSelector("div.features_items"));
		Assert.assertTrue(prodSection.isDisplayed());
		
		List<WebElement> allProds = driver.findElements(By.cssSelector("div.product-image-wrapper"));
		
		Assert.assertTrue(allProds.size()>0, "Products are not displayed");
		
//		7. Click on 'View Product' of first product
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement e = driver.findElement(By.cssSelector("a[href='/product_details/1']"));
		js.executeScript("arguments[0].scrollIntoView(true)", e);
		driver.findElement(By.cssSelector("a[href='/product_details/1']")).click();
		
//		8. User is landed to product detail page
		wait.until(ExpectedConditions.urlContains("product_details"));
		
		Assert.assertTrue(driver.getCurrentUrl().contains("product_details"), "Unable to redirect to product detail page");
		
//		9. Verify that detail detail is visible: product name, category, price, availability, condition, brand
		WebElement productInfo = driver.findElement(By.className("product-information"));
		
		Assert.assertTrue(productInfo.findElement(By.tagName("h2")).isDisplayed());
		Assert.assertTrue(productInfo.getText().contains("Category"));
		Assert.assertTrue(productInfo.getText().contains("Rs."));
		Assert.assertTrue(productInfo.getText().contains("Availability"));
		Assert.assertTrue(productInfo.getText().contains("Condition"));
		Assert.assertTrue(productInfo.getText().contains("Brand"));
		
	}
}
