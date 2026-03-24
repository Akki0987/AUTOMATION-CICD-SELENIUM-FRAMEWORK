package testCases.Contact_StaticPages;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TC07_Verify_TestCasesPage extends BaseTest{

	
	@Test
	public void veridyTestCasePage()
	{
//		1. Launch browser
//		2. Navigate to url 'http://automationexercise.com'
//		3. Verify that home page is visible successfully
		
		Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");
		
//		4. Click on 'Test Cases' button
		hp.clickTestCases();
		
//		5. Verify user is navigated to test cases page successfully
		Assert.assertTrue(hp.isTestCasesPageOpened(), "Testcases page not opened");
		
	}
}
