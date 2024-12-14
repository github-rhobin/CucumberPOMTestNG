package factories;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverManager;
import enums.ConfigProp;
import enums.WaitStrategy;
import utilities.ConfigUtil;

public final class WaitFactory {

	private WaitFactory() {
	}

	public static WebElement wait(By by, WaitStrategy strategy) {

		WebElement element = null;

		if (strategy == WaitStrategy.VISIBLE) {
			element = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(Long.parseLong(ConfigUtil.getPropValue(ConfigProp.EXPLICIT_WAIT_TIMER))))
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		} else if (strategy == WaitStrategy.PRESENT) {
			element = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(Long.parseLong(ConfigUtil.getPropValue(ConfigProp.EXPLICIT_WAIT_TIMER))))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} else if (strategy == WaitStrategy.CLICKABLE) {
			element = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(Long.parseLong(ConfigUtil.getPropValue(ConfigProp.EXPLICIT_WAIT_TIMER))))
					.until(ExpectedConditions.elementToBeClickable(by));
		} else if (strategy == WaitStrategy.NONE) {
			element = DriverManager.getDriver().findElement(by);
		}

		return element;
	}
}
