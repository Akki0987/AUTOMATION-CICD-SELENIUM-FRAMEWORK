package testCases.AllTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Login_Page;

public class TC05_Register_User_With_Existing_Email extends BaseTest{
	
	String name = "Akki";
	String email = "akkii@gmail.com";
	@Test
	public void registerExistingEmail()
	{
//		1. Launch browser
//		2. Navigate to url 'http://automationexercise.com'
//		3. Verify that home page is visible successfully
		Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");

//		4. Click on 'Signup / Login' button
		Login_Page lp = hp.clickLoginSignupBtn();

//		5. Verify 'New User Signup!' is visible
		Assert.assertTrue(lp.isSignUpSectionVisible(), "Signup section not visible");
		
//		6. Enter name and already registered email address
		lp.enterNameEmail(name, email);
		
//		7. Click 'Signup' button
		lp.clickSignUp();

//		8. Verify error 'Email Address already exist!' is visible
		Assert.assertTrue(lp.getSignUpErrorMessage().equalsIgnoreCase("Email Address already exist!"), "Error msg didnt shoed after already registered email login");
		
	}
}
