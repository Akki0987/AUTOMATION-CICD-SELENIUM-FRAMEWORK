package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utility;

public class ContactUs_Page extends Utility{

	WebDriver driver;
	
	public ContactUs_Page(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.contact-form h2")
	WebElement GIT;
	
	@FindBy(css="input[name='name']")
	WebElement nameInput;
	
	@FindBy(css="input[name='email']")
	WebElement emailInput;
	
	@FindBy(css="input[name='subject']")
	WebElement subjectInput;
	
	@FindBy(css="textarea[name='message']")
	WebElement msgInput;
	
	@FindBy(css="input[type='file']")
	WebElement fileInput;
	
	@FindBy(css="input[type='submit']")
	WebElement submitBtn;
	
	@FindBy(css="div.status.alert.alert-success")
	WebElement successMsg;
	
	@FindBy(css="ul a[href='/']")
	WebElement homeBtn;
	
	
	public boolean isGetInTouchVisible()
	{
	    return waitForVisibilityOfElement(GIT).isDisplayed();
	}
	
	public void enterNameEmailSubjectMsg(String name, String email , String subject, String msg)
	{
		nameInput.sendKeys(name);
		emailInput.sendKeys(email);
		subjectInput.sendKeys(subject);
		msgInput.sendKeys(msg);
	}
	
	public void uploadFile(String localPath)
	{
		String filePath = System.getProperty("user.dir") + localPath;
		fileInput.sendKeys(filePath);
	}

	public void submitIssue()
	{
		submitBtn.click();
	}
	
	public void AcceptAlert()
	{
		acceptAlert();
	}
	
	public String getSuccessMessage()
	{
	    return waitForVisibilityOfElement(successMsg).getText().trim();
	}
	
	public void clickHome()
	{
	    waitForVisibilityOfElement(homeBtn).click();
	}
	
}
