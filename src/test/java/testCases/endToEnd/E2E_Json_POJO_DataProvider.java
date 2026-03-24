package testCases.endToEnd;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import dataProviders.E2EData;
import models.AddressDetails;
import models.CartItem;
import pages.Account_Created_Page;
import pages.Cart_Page;
import pages.Checkout_Page;
import pages.DeleteAccount_Page;
import pages.Login_Page;
import pages.PaymentDone_Page;
import pages.Payment_Page;
import pages.Products_Page;
import pages.SignUp_Page;
import pojo.UsersData2;
import validators.AddressValidator;

public class E2E_Json_POJO_DataProvider extends base.BaseTest{
	
	@Test(dataProvider = "getJsonDataPOJO", dataProviderClass = E2EData.class)
	public void endToend(UsersData2 user) throws InterruptedException, IOException {

	    // Home Page check
	    Assert.assertTrue(hp.isHomePageDisplayed(), "Home page is not visible");

	    Login_Page lp = hp.clickLoginSignupBtn();
	    Assert.assertTrue(lp.isSignUpSectionVisible(), "Signup section not visible");

	    // Use POJO data
	    lp.enterNameEmail(user.getName(), user.getEmail());
	    SignUp_Page sp = lp.clickSignUp();

	    Assert.assertTrue(sp.isAccountInfoPageVisible(), "Account information page not opened");

	    // Prefilled checks
	    Assert.assertEquals(sp.getPrefilledName(), user.getName(), "Name not prefilled correctly");
	    Assert.assertEquals(sp.getPrefilledEmail(), user.getEmail(), "Email not prefilled correctly");

	    // Fill account details using POJO
	    sp.fillAccountDetails(
	        user.getPassword(),
	        user.getfName(),
	        user.getlName(),
	        user.getCompany(),
	        user.getAddress(),
	        user.getAddress2(),
	        user.getCountry(),
	        user.getState(),
	        user.getCity(),
	        user.getZipcode(),
	        user.getMobNo()
	    );

	    Account_Created_Page acp = sp.clickCreateAccount();
	    Assert.assertTrue(driver.getCurrentUrl().contains("account_created"), "Account was not created");
	    acp.clickContinue();

	    Assert.assertTrue(hp.isUserLoggedIn(user.getName()), "User not logged in correctly");

	    // Products page
	    Products_Page pp = hp.goToProductsPage();
	    List<String> productsToAdd = Arrays.asList(
	        "Men Tshirt",
	        "Premium Polo T-Shirts",
	        "Pure Cotton Neon Green Tshirt",
	        "Soft Stretch Jeans"
	    );

	    Cart_Page cp = null;
	    for (int i = 0; i < productsToAdd.size(); i++) {
	        pp.addItemToCart(productsToAdd.get(i));
	        Assert.assertTrue(pp.isProductAddedToCart(), "Product not added to cart");

	        if (i == productsToAdd.size() - 1) {
	            cp = pp.clickViewCart();
	        } else {
	            pp.clickContinueShopping();
	        }
	    }

	    Assert.assertNotNull(cp, "Cart page was not opened");

	    List<CartItem> cartItems = cp.getCartItems();
	    Assert.assertEquals(cartItems.size(), productsToAdd.size());

	    for (CartItem item : cartItems) {
	        Assert.assertTrue(productsToAdd.contains(item.getProductName()), "Unexpected product: " + item.getProductName());
	        Assert.assertEquals(item.getPrice() * item.getQuantity(), item.getTotalPrice(),
	                "Price mismatch for product: " + item.getProductName());
	    }

	    // Checkout & Address validation
	    Checkout_Page checkOutPage = cp.proceedToCheckout();
	    AddressValidator ac = new AddressValidator();

	    AddressDetails deliveryAddress = checkOutPage.getAddressDetails("delivery");
	    ac.assertAddress(
	        deliveryAddress,
	        user.getfName(),
	        user.getlName(),
	        user.getCompany(),
	        user.getAddress(),
	        user.getAddress2(),
	        user.getCountry(),
	        user.getState(),
	        user.getCity(),
	        user.getZipcode(),
	        user.getMobNo(),
	        "delivery"
	    );

	    AddressDetails billingAddress = checkOutPage.getAddressDetails("billing");
	    ac.assertAddress(
	        billingAddress,
	        user.getfName(),
	        user.getlName(),
	        user.getCompany(),
	        user.getAddress(),
	        user.getAddress2(),
	        user.getCountry(),
	        user.getState(),
	        user.getCity(),
	        user.getZipcode(),
	        user.getMobNo(),
	        "billing"
	    );

	    checkOutPage.addOrderComment("Please pack the items properly and deliver on time.");

	    // Payment
	    Payment_Page paymentPage = checkOutPage.clickPlaceOrder();
	    paymentPage.enterCardDetails(
	        user.getNameOnCard(),
	        user.getCardNumber(),
	        user.getCvc(),
	        user.getExpMonth(),
	        user.getExpYear()
	    );

	    PaymentDone_Page paymentDonePage = paymentPage.clickPlaceOrder();
	    Assert.assertTrue(paymentDonePage.isOrderPlacedSuccessfully(), "Order was not placed successfully");
	    paymentDonePage.downloadInvoice();
	    paymentDonePage.clickContinue();

	    // Delete Account
	    DeleteAccount_Page deleteAccountPage = hp.deleteAccount();
	    Assert.assertTrue(deleteAccountPage.isDeleteAccountPageLoaded(), "Delete account page did not open");

	    String message = deleteAccountPage.getDeletedMessage();
	    Assert.assertEquals(message, "ACCOUNT DELETED!", "Account deletion failed");

	    deleteAccountPage.clickContinue();
	}
	
}

