package utilities;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import enums.WaitStrategy;
import factories.WaitFactory;

public class ElementUtil {
	
	protected ElementUtil() {
	}
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ElementUtil.class);
	
	// Element Actions
	protected void click(By by, WaitStrategy strategy, String element_name) {
		try {
			WaitFactory.wait(by, strategy).click();
			//System.out.println("Click " +element_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void sendKeys(By by, WaitStrategy strategy, String element_name, String value) {
		try {
			WaitFactory.wait(by, strategy).clear();
			WaitFactory.wait(by, strategy).sendKeys(value);
			//System.out.println("Enter " +value+ "on" +element_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Element Status
	protected Boolean isDisplayed(By by, WaitStrategy strategy) {
		try {
			WaitFactory.wait(by, strategy);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
