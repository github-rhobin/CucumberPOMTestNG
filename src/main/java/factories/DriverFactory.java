package factories;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import enums.ConfigProp;
import utilities.ConfigUtil;

public final class DriverFactory {

	private DriverFactory() {
	}

	private static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return webdriver.get();
	}
	
	public static void initDriver(String browser) {
		if (Objects.isNull(getDriver())) {
			try {
				webdriver.set(BrowserFactory.configBrowser(browser));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Using window().setSize() instead of window().maximize() to fix issue when taking screenshot on headless browser mode
		getDriver().manage().window().setSize(new Dimension((Integer.parseInt(ConfigUtil.getPropValue(ConfigProp.BROWSER_HEIGHT))), 
				(Integer.parseInt(ConfigUtil.getPropValue(ConfigProp.BROWSER_WIDTH)))));
		getDriver().manage().timeouts().pageLoadTimeout(
				Duration.ofSeconds(Integer.parseInt(ConfigUtil.getPropValue(ConfigProp.PAGE_LOAD_TIMEOUT_TIMER))));
		getDriver().manage().deleteAllCookies();
	}

	public static void quitDriver() {
		if (Objects.nonNull(getDriver())) {
			getDriver().quit();
			webdriver.remove();
		}
	}
}
