package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utility;

public class PaymentDone_Page extends Utility{
	WebDriver driver;
	
	public PaymentDone_Page(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="h2 b")
	WebElement orderStatus;
	
	@FindBy(css="a[href*='/download_invoice']")
	WebElement downloadBtn;
	
	@FindBy(css="a[data-qa='continue-button']")
	WebElement continueBtn;
	
	private String getOrderStatusText() {
	    return waitForVisibilityOfElement(orderStatus).getText().trim();
	}

	public boolean isOrderPlacedSuccessfully() {
	    return getOrderStatusText().equalsIgnoreCase("ORDER PLACED!");
	}
	
	public void downloadInvoice()
	{
		// Download invoice
		downloadBtn.click();
		
	}
	
	public void clickContinue()
	{
		// Continue after order placed
				continueBtn.click();
	}

}
