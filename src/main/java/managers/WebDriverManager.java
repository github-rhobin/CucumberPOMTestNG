package managers;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.WebDriver;

import enums.ConfigProp;
import utilities.ConfigUtil;

public final class WebDriverManager {

	private WebDriverManager() {
	}

	private static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return webdriver.get();
	}
	
	public static void initDriver(String browser) {
		if (Objects.isNull(getDriver())) {
			try {
				webdriver.set(BrowserManager.configBrowser(browser));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(
				Duration.ofSeconds(Integer.parseInt(ConfigUtil.getPropValue(ConfigProp.PAGE_LOAD_TIMEOUT_TIMER))));
	}

	public static void quitDriver() {
		if (Objects.nonNull(getDriver())) {
			getDriver().quit();
			webdriver.remove();
		}
	}
}
