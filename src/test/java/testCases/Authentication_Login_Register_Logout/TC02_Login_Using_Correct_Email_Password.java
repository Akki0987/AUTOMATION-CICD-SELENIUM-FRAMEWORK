package testCases.Authentication_Login_Register_Logout;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.Login_Page;

public class TC02_Login_Using_Correct_Email_Password extends BaseTest{

	String email = "akkii@gmail.com";
	String password = "Akki@123";
	String name = "Abhishek Kholiya";
	
	@Test(groups = {"auth"})
	public void PositiveLogin()
	{
		Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");
		Login_Page lp = hp.clickLoginSignupBtn();

		Assert.assertTrue(lp.isLoginSectionVisible(), "Login section not visible");

		lp.enterEmailPassword(email, password);
		
		lp.clickLoginBtn();
		
        Assert.assertTrue(hp.isUserLoggedIn(name), "User not logged in correctly");
		
	}
}
