package runners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import enums.ConfigProp;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ConfigUtil;


@CucumberOptions(features = { "@failed_tests_rerun.txt" }, 
glue = { "stepdefs" }, 
plugin = { "pretty", 
		"html:cucumber-report.html", 
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class FailedTestRunner extends AbstractTestNGCucumberTests {
	
	// Logger
	private static final Logger logger = LoggerFactory.getLogger(FailedTestRunner.class);
	
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
		
		// Deleting User Data Directory
        String profile_path = System.getProperty("user.dir") + "/user-data-dir";
        File file = new File(profile_path);
 
        // Call deleteDirectory method to delete directory
        // Recursively
        try {
			FileUtils.deleteDirectory(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
 
        // Delete file object
        file.delete();
	}

}
