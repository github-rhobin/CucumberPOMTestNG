package factories;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.util.concurrent.Uninterruptibles;

import enums.ConfigProp;
import utilities.ConfigUtil;

public final class BrowserFactory {

	private BrowserFactory() {
	}

	public static WebDriver configBrowser(String browser) throws MalformedURLException, URISyntaxException {

		WebDriver webdriver = null;

		if (ConfigUtil.getPropValue(ConfigProp.RUN_MODE).equalsIgnoreCase("local")) {
			if (browser.equalsIgnoreCase("chrome")) {

				// Assign current thread identifier
				long thread_id = Thread.currentThread().threadId();
				
		        // Store profile path
		        String profile_path = System.getProperty("user.dir") + "/user-data-dir/chrome_profile_" + thread_id;
				
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--user-data-dir=" + profile_path);
				co.addArguments("--disable-extensions");
				co.addArguments("--incognito");
				co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				co.setAcceptInsecureCerts(true);

				if (ConfigUtil.getPropValue(ConfigProp.BROWSER_HEADLESS).equalsIgnoreCase("yes")) {
					co.addArguments("--headless=new");
				} 

				webdriver = new ChromeDriver(co);

			} else if (browser.equalsIgnoreCase("edge")) {

				EdgeOptions eo = new EdgeOptions();
				eo.addArguments("--disable-extensions");
				eo.addArguments("--inprivate");
				eo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				eo.setAcceptInsecureCerts(true);

				if (ConfigUtil.getPropValue(ConfigProp.BROWSER_HEADLESS).equalsIgnoreCase("yes")) {
					eo.addArguments("--headless=new");
				} 

				webdriver = new EdgeDriver(eo);

			} else if (browser.equalsIgnoreCase("firefox")) {

				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--disable-extensions");
				fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				fo.setAcceptInsecureCerts(true);

				if (ConfigUtil.getPropValue(ConfigProp.BROWSER_HEADLESS).equalsIgnoreCase("yes")) {
					fo.addArguments("--headless");
				} 

				webdriver = new FirefoxDriver(fo);

			}

		} else if (ConfigUtil.getPropValue(ConfigProp.RUN_MODE).equalsIgnoreCase("selenium_grid")) {

			URL local_server_url = new URI("http://localhost:4444").toURL();

			if (browser.equalsIgnoreCase("chrome")) {

				// Assign current thread identifier
				long thread_id = Thread.currentThread().threadId();
				
		        // Store profile path
		        String profile_path = System.getProperty("user.dir") + "/user-data-dir/chrome_profile_" + thread_id;
		        
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--user-data-dir=" + profile_path);
				co.addArguments("--disable-extensions");
				co.addArguments("--incognito");
				co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				co.setAcceptInsecureCerts(true);

				if (ConfigUtil.getPropValue(ConfigProp.BROWSER_HEADLESS).equalsIgnoreCase("yes")) {
					co.addArguments("--headless=new");
				} 

				webdriver = new RemoteWebDriver(local_server_url, co);

			} else if (browser.equalsIgnoreCase("edge")) {

				EdgeOptions eo = new EdgeOptions();
				eo.addArguments("--disable-extensions");
				eo.addArguments("--inprivate");
				eo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				eo.setAcceptInsecureCerts(true);

				if (ConfigUtil.getPropValue(ConfigProp.BROWSER_HEADLESS).equalsIgnoreCase("yes")) {
					eo.addArguments("--headless=new");
				} 

				webdriver = new RemoteWebDriver(local_server_url, eo);

			} else if (browser.equalsIgnoreCase("firefox")) {

				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--disable-extensions");
				fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				fo.setAcceptInsecureCerts(true);

				if (ConfigUtil.getPropValue(ConfigProp.BROWSER_HEADLESS).equalsIgnoreCase("yes")) {
					fo.addArguments("--headless");
				} 
				
				webdriver = new RemoteWebDriver(local_server_url, fo);
			}

		}

		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
		return webdriver;
	}
}
