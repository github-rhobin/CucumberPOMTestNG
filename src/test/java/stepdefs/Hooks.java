package stepdefs;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.Uninterruptibles;

import factories.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public final class Hooks {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
	
	// Thread Local Scenario Outline Name
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
			TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
			byte[] ss = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(ss, "image/png", "Screenshot attached");
		} // copy the same code on else block {} to take screenshot for passed test/scenario
	}

	@After(order = 0)
	public void tearDown(Scenario scenario) {
		logger.info("TEAR DOWN WEBDRIVER [{}]", scenario_name.get());
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
		DriverFactory.quitDriver();
	}
}
