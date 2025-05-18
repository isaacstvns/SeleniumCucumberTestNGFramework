package StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.ConfirmationPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.OrderHistoryPage;
import Utils.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	LoginPage login;
	DashboardPage dashboardPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmationPage;
	OrderHistoryPage orderHistoryPage;
	public String orderId;

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		launchApplication();
	}

	@Given("^Logged in with (.+) and (.+)$")
	public void logged_in_with_and(String username, String password) {
		login = new LoginPage(driver);
		login.Login(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		dashboardPage = new DashboardPage(driver);
		dashboardPage.addProductToCart(productName);
		dashboardPage.goToCart();
		cartPage = new CartPage(driver);
		Boolean match = cartPage.validateCartProducts(productName);
		Assert.assertTrue(match);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		cartPage.goToCheckoutPage();
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.selectCountry("United");
		checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on Confirmation Page")
	public void message_is_displayed_on_confirmation_page(String expectedMessage) {
		confirmationPage = new ConfirmationPage(driver);
		Assert.assertTrue(confirmationPage.validateConfirmationMessage());
		orderId = confirmationPage.returnOrderId();
		dashboardPage.goToOrders();
		orderHistoryPage = new OrderHistoryPage(driver);
		Assert.assertTrue(orderHistoryPage.validateOrderId(orderId));
		driver.quit();
	}

	@Then("{string} message is displayed")
    public void message_is_displayed(String expectedMessage) {
		Assert.assertTrue(login.errorMessage().equalsIgnoreCase("Incorrect email or password."));
		driver.quit();
    }
}
