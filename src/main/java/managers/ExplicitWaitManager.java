package managers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.ConfigProp;
import enums.WaitStrategy;
import utilities.ConfigUtil;

public final class ExplicitWaitManager {

	private ExplicitWaitManager() {
	}

	public static WebElement wait(By by, WaitStrategy strategy) {

		WebElement element = null;

		if (strategy == WaitStrategy.VISIBLE) {
			element = new WebDriverWait(WebDriverManager.getDriver(),
					Duration.ofSeconds(Integer.parseInt(ConfigUtil.getPropValue(ConfigProp.EXPLICIT_WAIT_TIMER))))
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		} else if (strategy == WaitStrategy.PRESENT) {
			element = new WebDriverWait(WebDriverManager.getDriver(),
					Duration.ofSeconds(Integer.parseInt(ConfigUtil.getPropValue(ConfigProp.EXPLICIT_WAIT_TIMER))))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} else if (strategy == WaitStrategy.CLICKABLE) {
			element = new WebDriverWait(WebDriverManager.getDriver(),
					Duration.ofSeconds(Integer.parseInt(ConfigUtil.getPropValue(ConfigProp.EXPLICIT_WAIT_TIMER))))
					.until(ExpectedConditions.elementToBeClickable(by));
		} else if (strategy == WaitStrategy.NONE) {
			element = WebDriverManager.getDriver().findElement(by);
		}

		return element;
	}
}
