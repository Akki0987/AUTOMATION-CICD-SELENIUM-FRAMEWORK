package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utility;

public class Products_Page extends Utility{

		WebDriver driver;
	    WebDriverWait wait;
	    Actions actions;
	    JavascriptExecutor js;

	    public Products_Page(WebDriver driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	

	@FindBy(css="div.modal-body a[href='/view_cart']")
	WebElement viewCart;
	
	@FindBy(css="div.modal-footer button")
	WebElement continueBtn;
	

	private void clickAddToCart(WebElement product)
	{
		 // Click Add to Cart button using JavaScript
        WebElement addToCartBtn = product.findElement(By.cssSelector(".overlay-content a"));
        clickUsingJS(addToCartBtn);
	}
	
	private String getAddToCartMessage() {
	    WebElement modal = waitForVisibilityOfElementLocated(By.id("cartModal"));
	    return modal.findElement(By.tagName("h4")).getText().trim();
	}

	public void addItemToCart(String productName) {
	    WebElement product = driver.findElement(
	            By.xpath("//p[normalize-space()='" + productName + "']/ancestor::div[@class='product-image-wrapper']")
	    );

	    scrollToElement(product);
	    hoverOnElement(product);
	    clickAddToCart(product);
	}

	public void clickContinueShopping() {
	    continueBtn.click();
	}

	public Cart_Page clickViewCart() {
	    viewCart.click();
	    return new Cart_Page(driver);
	}

	public boolean isProductAddedToCart() {
	    return getAddToCartMessage().equalsIgnoreCase("Added!");
	}
		    
}


