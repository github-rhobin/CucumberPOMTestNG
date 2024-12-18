package pageobjects;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import enums.WaitStrategy;
import utilities.ElementUtil;

public final class HomePage extends ElementUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
	
	// Locators
	private final By menu_hamburger = By.xpath("//div[@class='bm-burger-button']");
	
	// Methods
	public boolean isHomePageLoaded(String test_name) {
		logger.info("{}_____isHomePageLoaded_____{}", test_name, menu_hamburger);
		return checkElementCondition(menu_hamburger, WaitStrategy.VISIBLE);
		
	}
}
