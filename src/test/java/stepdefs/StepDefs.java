package stepdefs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import driver.Driver;
import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public final class StepDefs {

	// Page Objects
	LoginPage loginPage;
	HomePage homePage;
	
	// ThreadLocal Scenario Name
	private static String test_name = Hooks.scenario_name.get();

	// Logger
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StepDefs.class);


	// LoginTest.feature - Step Definitions
	@Given("user launch url {string} on browser {string}")
	public void i_launch_url_on_browser(String url, String browser) {
		Driver.initDriver(browser);
		DriverManager.getDriver().get(url);
		loginPage = new LoginPage();
	}

	@When("user login using username {string} and password {string}")
	public void i_login_using_username_and_password(String username, String password) {
		loginPage.enterUsername(test_name, username);
		loginPage.enterPassword(test_name, password);
		homePage = loginPage.clickLogin(test_name);
	}

	@Then("user should land on Home page")
	public void i_should_land_on_home_page() {
		Assert.assertTrue(homePage.isHomePageLoaded(test_name), "HOME PAGE WAS NOT LOADED!");
	}

	@Then("user should see an error message {string}")
	public void i_should_see_an_error_message(String error_message) {
		Assert.assertTrue(loginPage.isErrorDisplayed(test_name, error_message), "ERROR MESSAGE WAS NOT DISPLAYED!");
	}

}