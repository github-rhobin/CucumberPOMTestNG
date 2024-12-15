package factories;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import enums.ConfigProp;
import utilities.ConfigUtil;

public final class DriverFactory {

	private DriverFactory() {
	}

	public static WebDriver configDriver(String browser) throws MalformedURLException, URISyntaxException {

		WebDriver driver = null;

		if (ConfigUtil.getPropValue(ConfigProp.RUN_MODE).equalsIgnoreCase("local")) {
			if (browser.equalsIgnoreCase("chrome")) {

				ChromeOptions co = new ChromeOptions();
				co.addArguments("--incognito");
				co.addArguments("--disable-extensions");
				co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				co.setAcceptInsecureCerts(true);
				//co.setCapability("webSocketUrl", true); // use WebDriver BiDi protocol

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
				//eo.setCapability("webSocketUrl", true); // use WebDriver BiDi protocol

				if (ConfigUtil.getPropValue(ConfigProp.HEADLESS).equalsIgnoreCase("yes")) {
					eo.addArguments("--headless=new");
					eo.addArguments("--window-size=" + ConfigUtil.getPropValue(ConfigProp.HEADLESS_RESOLUTION));
				}

				driver = new EdgeDriver(eo);

			} else if (browser.equalsIgnoreCase("firefox")) {

				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--incognito");
				fo.addArguments("--disable-extensions");
				fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				fo.setAcceptInsecureCerts(true);
				//fo.setCapability("webSocketUrl", true); // use WebDriver BiDi protocol

				if (ConfigUtil.getPropValue(ConfigProp.HEADLESS).equalsIgnoreCase("yes")) {
					fo.addArguments("--headless");
					fo.addArguments("--window-size=" + ConfigUtil.getPropValue(ConfigProp.HEADLESS_RESOLUTION));
				}

				driver = new FirefoxDriver(fo);

			}
			
		} else if (ConfigUtil.getPropValue(ConfigProp.RUN_MODE).equalsIgnoreCase("selenium_grid")) {
			
			URL local_server_url = new URI("http://localhost:4444").toURL();
			
			if (browser.equalsIgnoreCase("chrome")) {

				ChromeOptions co = new ChromeOptions();
				co.addArguments("--incognito");
				co.addArguments("--disable-extensions");
				co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				co.setAcceptInsecureCerts(true);
				//co.setCapability("webSocketUrl", true); // use WebDriver BiDi protocol

				if (ConfigUtil.getPropValue(ConfigProp.HEADLESS).equalsIgnoreCase("yes")) {
					co.addArguments("--headless=new");
					co.addArguments("--window-size=" + ConfigUtil.getPropValue(ConfigProp.HEADLESS_RESOLUTION));
				}

				driver = new RemoteWebDriver(local_server_url, co);

			} else if (browser.equalsIgnoreCase("edge")) {

				EdgeOptions eo = new EdgeOptions();
				eo.addArguments("--inprivate");
				eo.addArguments("--disable-extensions");
				eo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				eo.setAcceptInsecureCerts(true);
				//eo.setCapability("webSocketUrl", true); // use WebDriver BiDi protocol

				if (ConfigUtil.getPropValue(ConfigProp.HEADLESS).equalsIgnoreCase("yes")) {
					eo.addArguments("--headless=new");
					eo.addArguments("--window-size=" + ConfigUtil.getPropValue(ConfigProp.HEADLESS_RESOLUTION));
				}

				driver = new RemoteWebDriver(local_server_url, eo);
				
			} else if (browser.equalsIgnoreCase("firefox")) {

				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--incognito");
				fo.addArguments("--disable-extensions");
				fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				fo.setAcceptInsecureCerts(true);
				//fo.setCapability("webSocketUrl", true); // use WebDriver BiDi protocol

				if (ConfigUtil.getPropValue(ConfigProp.HEADLESS).equalsIgnoreCase("yes")) {
					fo.addArguments("--headless");
					fo.addArguments("--window-size=" + ConfigUtil.getPropValue(ConfigProp.HEADLESS_RESOLUTION));
				}

				driver = new RemoteWebDriver(local_server_url, fo);
			}
			
		}
		
		return driver;
	}
}
