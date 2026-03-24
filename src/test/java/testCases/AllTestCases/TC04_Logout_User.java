package testCases.AllTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Login_Page;

public class TC04_Logout_User extends BaseTest{

	
	String email = "akkii@gmail.com";
	String password = "Akki@123";
	String name = "Abhishek Kholiya";
	
	@Test
	public void logOutUser()
	{
//		1. Launch browser
//		2. Navigate to url 'http://automationexercise.com'
//		3. Verify that home page is visible successfully
		Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");
		
//		4. Click on 'Signup / Login' button
		Login_Page lp = hp.clickLoginSignupBtn();

//		5. Verify 'Login to your account' is visible
		Assert.assertTrue(lp.isLoginSectionVisible(), "Login section not visible");

//		6. Enter incorrect email address and password
		lp.enterEmailPassword(email, password);
		
//		7. Click 'login' button
		lp.clickLoginBtn();
		
//		8. Verify that 'Logged in as username' is visible
		Assert.assertTrue(hp.isUserLoggedIn(name), "User not logged in correctly");
		
//		9. Click 'Logout' button
		hp.logOut();
		
//		10. Verify that user is navigated to login page
		Assert.assertTrue(lp.getCurrentUrl().contains("login"), "Still Logged In");
	}
}
