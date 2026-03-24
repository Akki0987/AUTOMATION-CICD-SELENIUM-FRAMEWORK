package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import models.AddressDetails;
import utils.Utility;

public class Checkout_Page extends Utility{

	WebDriver driver;

    public Checkout_Page(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(tagName="textarea")
    WebElement commentBox;
    
    @FindBy(css="a[href='/payment']")
    WebElement placeOrderBtn;
    
    public AddressDetails getAddressDetails(String addressType)
    {
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

        return new AddressDetails(
                actualName, actualCompany, actualAddress1,
                actualAddress2, actualCityStateZip,
                actualCountry, actualMobile
        );
    }
    
    public void addOrderComment(String comment)
    {
    	// Add order comment 
    	commentBox.sendKeys(comment);
    }
    
    public Payment_Page clickPlaceOrder()
    {
        // Proceed to payment page
    	placeOrderBtn.click();
    	return new Payment_Page(driver);
    }
}
