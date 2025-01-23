package driver;

import java.time.Duration;
import java.util.Objects;

import enums.ConfigProp;
import factories.DriverFactory;
import utilities.ConfigUtil;

public final class Driver {

	private Driver() {
	}

	public static void initDriver(String browser) {
		if (Objects.isNull(DriverManager.getDriver())) {
			try {
				DriverManager.setDriver(DriverFactory.configDriver(browser));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().pageLoadTimeout(
				Duration.ofSeconds(Integer.parseInt(ConfigUtil.getPropValue(ConfigProp.PAGE_LOAD_TIMEOUT_TIMER))));
	}

	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.removeDriver();
		}
	}
}
