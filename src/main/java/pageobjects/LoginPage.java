package pageobjects;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import enums.WaitStrategy;
import utilities.ElementUtil;

public final class LoginPage extends ElementUtil{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	
	// Locators
	private final By input_username = By.xpath("//input[@id='user-name']");
	private final By input_password = By.xpath("//input[@id='password']");
	private final By btn_login = By.xpath("//input[@id='login-button']");
	
	
	// Methods
	public LoginPage enterUsername(String test_name, String username) {
		logger.info("{}_____enterUsername_____{}_____{}", test_name, input_username, username);
		sendKeys(input_username, WaitStrategy.VISIBLE, username );
		return this;
	}
	
	public LoginPage enterPassword(String test_name, String password) {
		logger.info("{}_____enterPassword_____{}_____{}", test_name, input_password, password);
		sendKeys(input_password, WaitStrategy.VISIBLE, password );
		return this;
	}
	
	public HomePage clickLogin(String test_name) {
		logger.info("{}_____clickLogin_____{}", test_name, btn_login);
		click(btn_login, WaitStrategy.CLICKABLE);
		return new HomePage();
	}
	
	private By setErrorXpath(String text) {
		return By.xpath("//h3[text()[contains(.,'" +text+ "')]]");
	}
	
	public Boolean isErrorDisplayed(String test_name, String error_message) {
		logger.info("{}_____isErrorDisplayed_____{}", test_name, setErrorXpath(error_message));
		return checkElementCondition(setErrorXpath(error_message), WaitStrategy.VISIBLE);
	}
}
