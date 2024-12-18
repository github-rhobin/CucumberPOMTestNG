package stepdefs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public final class HomeStepDef {

	// Page Objects
	LoginPage loginPage;
	HomePage homePage = new HomePage();

	// ThreadLocal Scenario Outline Name
	private String test_name;

	// Logger
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HomeStepDef.class);

	// Cucumber Step Definitions
	@Then("user should land on Home page")
	public void i_should_land_on_home_page() {
		test_name = Hooks.scenario_name.get();
		Assert.assertTrue(homePage.isHomePageLoaded(test_name), "HOME PAGE WAS NOT LOADED!");
	}
}
