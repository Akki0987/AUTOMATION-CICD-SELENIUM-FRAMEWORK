package testCases.Subscription;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TC10_Verify_Subscription_InHomepage extends BaseTest{

	@Test
	public void verifySubscription()
	{
//		1. Launch browser
//		2. Navigate to url 'http://automationexercise.com'
		driver.get("http://automationexercise.com");

//		3. Verify that home page is visible successfully
		String color = driver.findElement(By.cssSelector("ul a[href='/']")).getCssValue("color");
		Assert.assertEquals(color, "rgba(255, 165, 0, 1)");
		
//		4. Scroll down to footer
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
//		5. Verify text 'SUBSCRIPTION'
		WebElement subscription = driver.findElement(By.cssSelector("div.single-widget h2"));
		Assert.assertTrue(subscription.getText().equalsIgnoreCase("Subscription"));
		
//		6. Enter email address in input and click arrow button
		driver.findElement(By.cssSelector("input#susbscribe_email")).sendKeys("AkkiPlays@gmail.com");
		driver.findElement(By.cssSelector("button#subscribe")).click();
		
//		7. Verify success message 'You have been successfully subscribed!' is visible
		WebElement msg = driver.findElement(By.cssSelector("div.alert-success.alert"));
		Assert.assertTrue(msg.isDisplayed());
	}
}
