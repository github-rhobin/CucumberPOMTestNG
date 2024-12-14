package stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import driver.Driver;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public final class StepDefs {

	// Page Objects
	LoginPage loginPage;
	HomePage homePage;

	// Logger
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StepDefs.class);

	// Thread Local Scenario Name
	private static ThreadLocal<String> scenario_name = new ThreadLocal<>();

	// Cucumber Hooks
	// Before(order=0) -> Before(order=1) -> Scenario -> After(order=1) -> After(order=0)
	@Before(order = 0)
	public void getScenarioName(Scenario scenario) {
		scenario_name.set(scenario.getName());
	}

	@After(order = 1)
	public void takeScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
			byte[] ss = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(ss, "image/png", "Screenshot attached");
		} else {
			TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
			byte[] ss = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(ss, "image/png", "Screenshot attached");
		}
	}

	@After(order = 0)
	public void tearDown(Scenario scenario) {
		// Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
		Driver.quitDriver();
	}

	// LoginTest.feature - Step Definitions
	@Given("user launch url {string} on browser {string}")
	public void i_launch_url_on_browser(String url, String browser) {
		Driver.initDriver(browser);
		DriverManager.getDriver().get(url);
		loginPage = new LoginPage();
	}

	@When("user login using username {string} and password {string}")
	public void i_login_using_username_and_password(String username, String password) {
		String test_name = scenario_name.get();
		loginPage.enterUsername(test_name, username);
		loginPage.enterPassword(test_name, password);
		homePage = loginPage.clickLogin(test_name);
	}

	@Then("user should land on Home page")
	public void i_should_land_on_home_page() {
		String test_name = scenario_name.get();
		Assert.assertTrue(homePage.isHomePageLoaded(test_name), "HOME PAGE WAS NOT LOADED!");
	}

	@Then("user should see an error message {string}")
	public void i_should_see_an_error_message(String error_message) {
		String test_name = scenario_name.get();
		Assert.assertTrue(loginPage.isErrorDisplayed(test_name, error_message), "ERROR MESSAGE WAS NOT DISPLAYED!");
	}

}