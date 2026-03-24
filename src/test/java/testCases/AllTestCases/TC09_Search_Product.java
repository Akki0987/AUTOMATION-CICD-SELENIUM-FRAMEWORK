package testCases.AllTestCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TC09_Search_Product extends BaseTest{

	@Test
	public void searchPrroduct()
	{
//		2. Navigate to url 'http://automationexercise.com'
		driver.get("http://automationexercise.com");

//		3. Verify that home page is visible successfully
		String color = driver.findElement(By.cssSelector("ul a[href='/']")).getCssValue("color");
		Assert.assertEquals(color, "rgba(255, 165, 0, 1)");
		
//		4. Click on 'Products' button
		driver.findElement(By.cssSelector("a[href='/products']")).click();

//		5. Verify user is navigated to ALL PRODUCTS page successfully
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("products"));
		
		Assert.assertTrue(driver.getCurrentUrl().contains("products"), "Unable to redirect to products page");
		
//		6. Enter product name in search input and click search button
		driver.findElement(By.cssSelector("input#search_product")).sendKeys("Regular Fit");
		driver.findElement(By.cssSelector("button#submit_search")).click();
		
//		7. Verify 'SEARCHED PRODUCTS' is visible
//		8. Verify all the products related to search are visible
		
		List <WebElement> results = driver.findElements(By.cssSelector("div.product-image-wrapper .productinfo p"));
		Assert.assertTrue(results.size() > 0, "No products found for the searched keyword.");
		
		for(WebElement res : results)
		{
			Assert.assertTrue(res.getText().toLowerCase().contains("regular fit"));
		}
	}
}
