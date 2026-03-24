package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utility;

public class Account_Created_Page extends Utility{
	
	WebDriver driver;
	
	public Account_Created_Page(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a[data-qa='continue-button']")
	WebElement continueBtn;
	
	
	public void clickContinue()
	{
		continueBtn.click();
	}
	
}
