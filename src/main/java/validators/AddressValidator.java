package validators;

import org.testng.Assert;

import models.AddressDetails;

public class AddressValidator {

	public void assertAddress(AddressDetails actual, String fName, String lName,
            String company, String address, String address2,
            String country, String state, String city,
            String zipcode, String mobNo, String type)
		{
			Assert.assertEquals(actual.getActualName(), "Mr. " + fName + " " + lName, type + " name mismatch");
			Assert.assertEquals(actual.getActualCompany(), company, type + " company mismatch");
			Assert.assertEquals(actual.getActualAddress1(), address, type + " address1 mismatch");
			Assert.assertEquals(actual.getActualAddress2(), address2, type + " address2 mismatch");
			Assert.assertEquals(actual.getActualCityStateZip(), city + " " + state + " " + zipcode, type + " city/state mismatch");
			Assert.assertEquals(actual.getActualCountry(), country, type + " country mismatch");
			Assert.assertEquals(actual.getActualMobile(), mobNo, type + " mobile mismatch");
		}
}
