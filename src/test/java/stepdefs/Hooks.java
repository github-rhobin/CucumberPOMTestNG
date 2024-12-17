package stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import driver.Driver;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public final class Hooks {

	// Thread Local Scenario Name
	public static ThreadLocal<String> scenario_name = new ThreadLocal<>();

	// Cucumber Hooks
	// Before(order=0) -> Before(order=1) -> Scenario -> After(order=1) -> After(order=0)
	@Before(order = 0)
	public void setScenarioName(Scenario scenario) {
		scenario_name.set(scenario.getName());
	}

	@After(order = 1)
	public void takeScreenshotOnTestFailure(Scenario scenario) {
		if (scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
			byte[] ss = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(ss, "image/png", "Screenshot attached");
		} 
	}

	@After(order = 0)
	public void tearDown(Scenario scenario) {
		//Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
		Driver.quitDriver();
	}
}
