package runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import enums.ConfigProp;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ConfigUtil;


@CucumberOptions(tags = "@login", 
features = { "./src/test/resources/features" }, 
glue = { "stepdefs" }, 
plugin = {"pretty", 
		"html:cucumber-report.html", 
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestRunner extends AbstractTestNGCucumberTests {
	
	// Logger
	private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@BeforeSuite
	public void beginTestSuite() {
		logger.info("BEGIN TEST SUITE - RUN MODE [{}]", ConfigUtil.getPropValue(ConfigProp.RUN_MODE).toUpperCase());
	}
	
	@AfterSuite
	public void endTestSuite() {
		logger.info("END TEST SUITE - RUN MODE [{}]", ConfigUtil.getPropValue(ConfigProp.RUN_MODE).toUpperCase());
		//sample commit
	}

}
