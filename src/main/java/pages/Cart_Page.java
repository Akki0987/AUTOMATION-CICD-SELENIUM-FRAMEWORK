package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import models.CartItem;
import utils.Utility;

public class Cart_Page extends Utility{

	WebDriver driver;
	
	public Cart_Page(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="table#cart_info_table tbody tr")
	List<WebElement> cartRows;
	
	
	
	public List<CartItem> getCartItems()
	{
	    waitForVisibilityOfElementLocated(By.cssSelector("table#cart_info_table tbody tr"));

	    List<CartItem> items = new ArrayList<>();

	    for (WebElement row : cartRows) {

	        String productName = row.findElement(By.cssSelector(".cart_description a"))
	                                .getText().trim();

	        int price = Integer.parseInt(
	                row.findElement(By.cssSelector("td.cart_price p"))
	                        .getText().replace("Rs.", "").trim()
	        );

	        int quantity = Integer.parseInt(
	                row.findElement(By.cssSelector("td.cart_quantity button"))
	                        .getText().trim()
	        );

	        int totalPrice = Integer.parseInt(
	                row.findElement(By.cssSelector("td.cart_total p"))
	                        .getText().replace("Rs.", "").trim()
	        );

	        items.add(new CartItem(productName, price, quantity, totalPrice));
	    }

	    return items;
	}
	
	public Checkout_Page proceedToCheckout()
	{
		 // Click Proceed to Checkout
        WebElement checkoutBtn =
        		waitForElementToBeClickable(By.cssSelector("a.check_out"));
        clickUsingJS(checkoutBtn);
        
        return new Checkout_Page(driver);
	}
}
