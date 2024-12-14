package driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

	private DriverManager() {
	}

	private static ThreadLocal<WebDriver> tl_driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return tl_driver.get();
	}

	public static void setDriver(WebDriver driver) {
		tl_driver.set(driver);
	}

	public static void removeDriver() {
		tl_driver.remove();
	}
}
