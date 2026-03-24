package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utility;

public class Payment_Page extends Utility{

	WebDriver driver;
	WebDriverWait wait;
	
	public Payment_Page(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="name_on_card")
	WebElement nameInput;
	
	@FindBy(name="card_number")
	WebElement cardNumberInput;
	
	@FindBy(name="cvc")
	WebElement cvcInput;
	
	@FindBy(name="expiry_month")
	WebElement expMonthInput;
	
	@FindBy(name="expiry_year")
	WebElement expYearInput;
	
	@FindBy(id="submit")
	WebElement payBtn;
	
	public void enterCardDetails(String name, String cardNumber, String cvc, String expMonth, String expYear)
	{
		// Enter payment details
        nameInput.sendKeys(name);
        cardNumberInput.sendKeys(cardNumber);
        cvcInput.sendKeys(cvc);
        expMonthInput.sendKeys(expMonth);
        expYearInput.sendKeys(expYear);
	}
	
	public PaymentDone_Page clickPlaceOrder()
	{
		// Scroll to Place Order button and click
		scrollToElement(payBtn);
        waitForElementToBeClickable(payBtn).click();
        
        return new PaymentDone_Page(driver);
	}
}
