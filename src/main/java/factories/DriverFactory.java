package factories;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import enums.ConfigProp;
import utilities.ConfigUtil;

public final class DriverFactory {

	private DriverFactory() {
	}

	public static WebDriver configDriver(String browser) {

		WebDriver driver = null;

		if (ConfigUtil.getPropValue(ConfigProp.RUN_MODE).equalsIgnoreCase("local")) {
			if (browser.equalsIgnoreCase("chrome")) {

				ChromeOptions co = new ChromeOptions();
				co.addArguments("--incognito");
				co.addArguments("--disable-extensions");
				co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				co.setAcceptInsecureCerts(true);

				if (ConfigUtil.getPropValue(ConfigProp.HEADLESS).equalsIgnoreCase("yes")) {
					co.addArguments("--headless=new");
					co.addArguments("--window-size=" + ConfigUtil.getPropValue(ConfigProp.HEADLESS_RESOLUTION));
				}

				driver = new ChromeDriver(co);

			} else if (browser.equalsIgnoreCase("edge")) {

				EdgeOptions eo = new EdgeOptions();
				eo.addArguments("--inprivate");
				eo.addArguments("--disable-extensions");
				eo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				eo.setAcceptInsecureCerts(true);

				if (ConfigUtil.getPropValue(ConfigProp.HEADLESS).equalsIgnoreCase("yes")) {
					eo.addArguments("--headless=new");
					eo.addArguments("--window-size=" + ConfigUtil.getPropValue(ConfigProp.HEADLESS_RESOLUTION));
				}

				driver = new EdgeDriver(eo);

			}
		}
		
		return driver;
	}
}
