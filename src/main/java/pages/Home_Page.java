package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utility;

public class Home_Page extends Utility{


	WebDriver driver;
	
	public Home_Page(WebDriver driver)
	{
		super(driver);
//		Initialize
		this.driver = driver;
//		Passing driver to initialize elements 
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css="ul a[href='/']")
	WebElement homeBtn;
	
	@FindBy(css = "ul a[href='/login']")
	WebElement loginSignupBtn;

	@FindBy(xpath="//a[contains(text(),'Logged in as')]")
	WebElement loggedInBtn;
	
	@FindBy(css="a[href='/products']")
	WebElement prodBtn;
	
	@FindBy(css="a[href='/delete_account']")
	WebElement delAccountBtn;
	
	
	@FindBy(css="a[href='/logout']")
	WebElement logoutBtn;
	
	@FindBy(css="a[href='/contact_us']")
	WebElement contactUsBtn;
	
	@FindBy(css="ul a[href='/test_cases']")
	WebElement testCasesBtn;
	
	public void goToLandingPage()
	{
		 // Navigate to application URL
        driver.get("http://automationexercise.com");
	}
	

	public boolean isHomePageDisplayed() {
	    return homeBtn.isDisplayed();
	}
	
	public Login_Page clickLoginSignupBtn()
	{
		loginSignupBtn.click();
		return new Login_Page(driver);
	}
	
	private String getLoggedInUserName() {
	    String text = waitForVisibilityOfElement(loggedInBtn).getText().trim();
	    return text.replace("Logged in as ", "");
	}
	
	public boolean isUserLoggedIn(String expectedName) {
	    return getLoggedInUserName().equals(expectedName);
	}
	

	public Products_Page goToProductsPage()
	{
		 // Navigate to Products page
		prodBtn.click();
		return new Products_Page(driver);
	}
	
	public DeleteAccount_Page deleteAccount()
	{
	     // Click Delete Account
		delAccountBtn.click();
		
		return new DeleteAccount_Page(driver);
	}
	
	public ContactUs_Page clickContactUs()
	{
		contactUsBtn.click();
		return new ContactUs_Page(driver);
	}
	
	public void clickTestCases()
	{
		testCasesBtn.click();
	}
	
	public boolean isTestCasesPageOpened()
	{
		waitUntilUrlContains("test_cases");
		return driver.getCurrentUrl().contains("test_cases");
	}
	public void logOut()
	{
		logoutBtn.click();
	}
}
