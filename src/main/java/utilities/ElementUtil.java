package utilities;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import enums.WaitStrategy;
import managers.ExplicitWaitManager;

public class ElementUtil {
	
	protected ElementUtil() {
	}
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ElementUtil.class);
	
	// Element Actions
	protected void click(By by, WaitStrategy strategy) {
		try {
			ExplicitWaitManager.wait(by, strategy).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void sendKeys(By by, WaitStrategy strategy, String value) {
		try {
			ExplicitWaitManager.wait(by, strategy).clear();
			ExplicitWaitManager.wait(by, strategy).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected boolean checkElementCondition(By by, WaitStrategy strategy) {
		try {
			ExplicitWaitManager.wait(by, strategy);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
