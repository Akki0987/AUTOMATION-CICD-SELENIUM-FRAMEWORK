package testCases.Authentication_Login_Register_Logout;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.Account_Created_Page;
import pages.DeleteAccount_Page;
import pages.Login_Page;
import pages.SignUp_Page;

public class TC01_Register_User extends BaseTest {
	String name = "Akki";
	String email = "dailyyHustlin44@gmail.com";
	String password = "Broken@098";
	String fName = "Abhishek";
	String lName = "Chauhan";
	String company = "Nugats Technologies";
	String address = "Sector 62";
	String address2 = "Azad Vihar Colony, Block A";
	String city = "Noida";
	String state = "UttarPradesh";
	String zipcode = "280001";
	String country = "India";
	String mobNo = "8494045494";
	String nameOnCard = "Abhishek";
	String cardNumber = "8596454662";
	String cvc = "585";
	String expMonth = "10";
	String expYear = "2050";

	@Test(groups = {"auth"})

	public void RegisterUser() {
		Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");
		Login_Page lp = hp.clickLoginSignupBtn();

		Assert.assertTrue(lp.isSignUpSectionVisible(), "Signup section not visible");

		lp.enterNameEmail(name, email);
		SignUp_Page sp = lp.clickSignUp();

		Assert.assertTrue(sp.isAccountInfoPageVisible(), "Account information page not opened");

		Assert.assertEquals(sp.getPrefilledName(), name, "Name not prefilled correctly");
		Assert.assertEquals(sp.getPrefilledEmail(), email, "Email not prefilled correctly");

		sp.fillAccountDetails(password, fName, lName, company, address, address2, country, state, city, zipcode, mobNo);
		Account_Created_Page acp = sp.clickCreateAccount();

		Assert.assertTrue(driver.getCurrentUrl().contains("account_created"), "Account was not created");
		acp.clickContinue();

		Assert.assertTrue(hp.isUserLoggedIn(name), "User not logged in correctly");

		DeleteAccount_Page deleteAccountPage = hp.deleteAccount();

		Assert.assertTrue(deleteAccountPage.isDeleteAccountPageLoaded(), "Delete account page did not open");

		String message = deleteAccountPage.getDeletedMessage();
		Assert.assertEquals(message, "ACCOUNT DELETED!", "Account deletion failed");

		deleteAccountPage.clickContinue();
	}
}
