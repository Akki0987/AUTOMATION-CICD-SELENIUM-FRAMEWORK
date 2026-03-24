package testCases.Authentication_Login_Register_Logout;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.RetryMechanism;
import pages.Login_Page;

public class TC03_Login_Using_Incorrect_Email_Password extends BaseTest{

	String email = "akkii@gmail.com";
	String password = "Akki@098";
	
	@Test(groups = {"auth"}, retryAnalyzer = RetryMechanism.class)
	public void negativeLogin()
	{
//		2. Navigate to url 'http://automationexercise.com'
//		3. Verify that home page is visible successfully
//		4. Click on 'Signup / Login' button
//		5. Verify 'Login to your account' is visible
		Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");
		Login_Page lp = hp.clickLoginSignupBtn();

		Assert.assertTrue(lp.isLoginSectionVisible(), "Login section not visible");

//		6. Enter incorrect email address and password
		lp.enterEmailPassword(email, password);
		
//		7. Click 'login' button
		lp.clickLoginBtn();
		
//		8. Verify error 'Your email or password is incorrect!' is visible
		Assert.assertTrue(lp.getLoginErrorMessage().equalsIgnoreCase("Your email or password is incorrect!"), "Error Message not showed up after entering incorrect email password");
		
	}
}
