package utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	WebDriver driver;
	WebDriverWait wait;
	
	public Utility(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
		}
	
	public WebElement waitForVisibilityOfElement(WebElement element)
	{
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitForVisibilityOfElementLocated(By selector)
	{
		return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}
	
	public WebElement waitForElementToBeClickable(By selector)
	{
		return wait.until(
                ExpectedConditions.elementToBeClickable(selector)
        );
	}
	
	public WebElement waitForElementToBeClickable(WebElement element)
	{
	    return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitUntilUrlContains(String text)
	{
		wait.until(ExpectedConditions.urlContains(text));
	}
	
	public void clickUsingJS(WebElement element)
	{
		waitForVisibilityOfElement(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);	
	}


	public void scrollToElement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void hoverOnElement(WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public void acceptAlert()
	{
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void dismissAlert()
	{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public String getAlertText()
	{
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

}