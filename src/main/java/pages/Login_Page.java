package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utility;

public class Login_Page extends Utility{

	WebDriver driver;
	
	public Login_Page(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.signup-form h2")
	WebElement signUpHeader;
	
	@FindBy(css="div.login-form")
	WebElement loginHeader;
	
	@FindBy(css="input[data-qa='login-email']")
	WebElement loginEmail;
	
	@FindBy(css="input[data-qa='login-password']")
	WebElement loginPassword;
	
	@FindBy(css="button[data-qa='login-button']")
	WebElement loginBtn;
	
	@FindBy(name="name")
	WebElement signUpName;
	
	@FindBy(css="form[action='/signup'] input[placeholder='Email Address']")
	WebElement signUpEmail;
	
	@FindBy(css="button[data-qa$='signup-button']")
	WebElement signUpBtn;
	
	@FindBy(css="form[action='/login'] p")
	WebElement loginErrorMsgBox;
	
	@FindBy(css="form[action='/signup'] p")
	WebElement signUpErrorMsgBox;
	
	public boolean isSignUpSectionVisible() {
	    return waitForVisibilityOfElement(signUpHeader).isDisplayed();
	}

	public void enterNameEmail(String name, String email)
	{
        // Enter name and email for registration
		signUpName.sendKeys(name);
		signUpEmail.sendKeys(email);
	}
	
	public SignUp_Page clickSignUp()
	{
		// Click Signup button
		signUpBtn.click();
		return new SignUp_Page(driver);
	}
	

	public boolean isLoginSectionVisible()
	{
		return  waitForVisibilityOfElement(loginHeader).isDisplayed();
	}
	
	public void enterEmailPassword(String email, String password)
	{
        // Enter name and email for registration
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(password);
	}
	
	public SignUp_Page clickLoginBtn()
	{
		// Click Signup button
		loginBtn.click();
		return new SignUp_Page(driver);
	}
	
	public String getCurrentUrl()
	{
		waitUntilUrlContains("login");
		return driver.getCurrentUrl();
	}
	
	public String getLoginErrorMessage()
	{
		waitForVisibilityOfElement(loginErrorMsgBox);
		
		return loginErrorMsgBox.getText().trim();
	}
	
	public String getSignUpErrorMessage()
	{
		waitForVisibilityOfElement(signUpErrorMsgBox);
		
		return signUpErrorMsgBox.getText().trim();
	}
	
}
