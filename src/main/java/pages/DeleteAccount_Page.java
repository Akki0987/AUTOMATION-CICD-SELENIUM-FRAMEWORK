package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utility;

public class DeleteAccount_Page extends Utility{

	WebDriver driver;
	
	public DeleteAccount_Page(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="h2[data-qa='account-deleted']")
	WebElement msgBox;
	
	@FindBy(css="a[data-qa='continue-button']")
	WebElement continueBtn;
	
	public String getDeletedMessage()
	{
		waitUntilUrlContains("delete_account");
        String status = msgBox.getText();
        return status;
	}
	
	public boolean isDeleteAccountPageLoaded() {
	    waitUntilUrlContains("delete_account");
	    return driver.getCurrentUrl().contains("delete_account");
	}

	
	public void clickContinue()
	{
        // Click Continue
		continueBtn.click();
	}
}
