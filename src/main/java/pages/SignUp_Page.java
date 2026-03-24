package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Utility;

public class SignUp_Page extends Utility{
	
	WebDriver driver;
	
	public SignUp_Page(WebDriver driver)
	{
		super(driver);
//		Initialize
		this.driver = driver;
//		Passing driver to initialize elements 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='login-form']/h2/b")
	WebElement accountInfoBlock;
	
	@FindBy(id="name")
	WebElement nameInput;
	
	@FindBy(id="email")
	WebElement emailInput;
	
	@FindBy(id ="id_gender1")
	WebElement maleGender;
	
	@FindBy(id ="password")
	WebElement passwordInput;
	
	@FindBy(id ="days")
	WebElement dayInput;
	
	@FindBy(id ="months")
	WebElement monthInput;
	
	@FindBy(id ="years")
	WebElement yearInput;
	
	@FindBy(id ="newsletter")
	WebElement newsLetter;
	
	@FindBy(id ="optin")
	WebElement recieveOffers;
	
	@FindBy(id ="first_name")
	WebElement firstNameInput;
	
	@FindBy(id ="last_name")
	WebElement lastNameInput;
	
	@FindBy(id ="company")
	WebElement companyInput;
	
	@FindBy(id ="address1")
	WebElement addressInput;
	
	@FindBy(id ="address2")
	WebElement address2Input;
	
	@FindBy(id ="country")
	WebElement countryInput;
	
	@FindBy(id ="state")
	WebElement stateInput;
	
	@FindBy(id ="city")
	WebElement cityInput;
	
	@FindBy(id ="zipcode")
	WebElement zipCodeInput;
	
	@FindBy(id ="mobile_number")
	WebElement mobileNumberInput;
	
	@FindBy(css="button[data-qa='create-account']")
	WebElement createAccountBtn;
	
	
	
	private String getAccountInfoText() {
	    return waitForVisibilityOfElement(accountInfoBlock).getText().trim();
	}

	public boolean isAccountInfoPageVisible() {
	    return getAccountInfoText().equalsIgnoreCase("ENTER ACCOUNT INFORMATION");
	}
	
	public String getPrefilledName() {
	    return nameInput.getDomAttribute("value").trim();
	}

	public String getPrefilledEmail() {
	    return emailInput.getDomAttribute("value").trim();
	}
	
	public void fillAccountDetails(String password,String fName,String lName,String company,String address,String address2,String country,String state,String city,String zipcode,String mobNo)
	{
		 // Select title
		maleGender.click();
        // Enter password
		passwordInput.sendKeys(password);

        // Select date of birth
        new Select(dayInput).selectByValue("28");
        new Select(monthInput).selectByVisibleText("October");
        new Select(yearInput).selectByValue("2002");

        // Select newsletter and offers checkboxes
        newsLetter.click();
        recieveOffers.click();

        // Fill address details
        firstNameInput.sendKeys(fName);
        lastNameInput.sendKeys(lName);
        companyInput.sendKeys(company);
        addressInput.sendKeys(address);
        address2Input.sendKeys(address2);

        new Select(countryInput).selectByVisibleText(country);

        stateInput.sendKeys(state);
        cityInput.sendKeys(city);
        zipCodeInput.sendKeys(zipcode);
        mobileNumberInput.sendKeys(mobNo);
	}

	public Account_Created_Page clickCreateAccount() {
	      // Create account
		createAccountBtn.click();	
		return new Account_Created_Page(driver);
	}
}
