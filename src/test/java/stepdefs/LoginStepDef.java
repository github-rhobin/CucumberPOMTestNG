package stepdefs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import factories.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public final class LoginStepDef {

	// Page Objects
	LoginPage loginPage = new LoginPage();
	HomePage homePage;
	
	// ThreadLocal Scenario Outline Name 
	private String test_name;

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(LoginStepDef.class);

	// Cucumber Step Definitions
	@Given("user launch url {string} on browser {string}")
	public void i_launch_url_on_browser(String url, String browser) {
		test_name = Hooks.scenario_name.get();
		logger.info("INITIALIZE WEBDRIVER [{}]", test_name);
		DriverFactory.initDriver(browser);
		DriverFactory.getDriver().get(url);
	}

	@When("user login using username {string} and password {string}")
	public void i_login_using_username_and_password(String username, String password) {
		test_name = Hooks.scenario_name.get();
		loginPage.enterUsername(test_name, username);
		loginPage.enterPassword(test_name, password);
		homePage = loginPage.clickLogin(test_name);
	}

	@Then("user should see an error message {string}")
	public void i_should_see_an_error_message(String error_message) {
		test_name = Hooks.scenario_name.get();
		Assert.assertTrue(loginPage.isErrorDisplayed(test_name, error_message), "ERROR MESSAGE WAS NOT DISPLAYED!");
	}
}