package testCases.AllTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ContactUs_Page;

public class TC06_Contact_Us_Form extends BaseTest{

	String name = "Akki";
	String email = "AkkiPlays@gmail.com";
	String subject = "Add To Cart Is not working";
	String msg = """
			Hi Team,

			The Add To Cart functionality is currently not working. When the user clicks the Add To Cart button, the item is not being added to the cart and no expected action occurs.

			Please check and fix the issue.
			""";
	String localPath = "\\screenshot\\Issue.png";
	
	@Test
	public void contactUs()
	{
	
		Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");
//		4. Click on 'Contact Us' button
		ContactUs_Page contactUsPage = hp.clickContactUs();
//		5. Verify 'GET IN TOUCH' is visible
		Assert.assertTrue(contactUsPage.isGetInTouchVisible());		
//		6. Enter name, email, subject and message
		contactUsPage.enterNameEmailSubjectMsg(name, email, subject, msg);
//		7. Upload file
		contactUsPage.uploadFile(localPath);
		
//		8. Click 'Submit' button
		contactUsPage.submitIssue();
		
//		9. Click OK button
		contactUsPage.AcceptAlert();
		
//		10. Verify success message 'Success! Your details have been submitted successfully.' is visible
		Assert.assertEquals(contactUsPage.getSuccessMessage(), "Success! Your details have been submitted successfully.");
		
//		11. Click 'Home' button and verify that landed to home page successfully
		contactUsPage.clickHome();
		Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");
	}
}
