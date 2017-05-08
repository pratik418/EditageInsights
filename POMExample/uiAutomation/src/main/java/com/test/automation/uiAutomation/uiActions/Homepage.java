package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.test.automation.uiAutomation.homepage.TC_001VerifyLoginWithInvalidCredentials;
import com.test.automation.uiAutomation.homepage.explicitWait;

public class Homepage {

	public static final Logger log = Logger.getLogger(Homepage.class.getName());

	private WebDriver driver;
	explicitWait wait;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		wait = new explicitWait(driver);
	}

	// Login Button
	@FindBy(xpath = "//div[@class='anonymous-login anonymous-text']/a")
	WebElement logIn;

	// Login Pop-Up
	@FindBy(xpath = "//div[@id='mini-panel-register_popup']")
	WebElement popUpFrame;

	// Email ID in Login
	@FindBy(id = "edit-name--2")
	WebElement EmailId;

	// Password in Login
	@FindBy(id = "edit-pass--2")
	WebElement password;

	// Login Button
	@FindBy(id = "edit-submit--3")
	WebElement loginButton;

	// Error Message
	@FindBy(xpath = "//div[@class='messages messages--error']/h2")
	WebElement errorMessage;

	// Login in Application
	public void loginApplication(String emailAddress, String Password) {
		logIn.click();
		log.info("Clicked on sign in and object is:" + logIn.toString());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='authenticated-user-test@mailinator.com';", EmailId);
		jse.executeScript("arguments[0].value='asd';", password);
		loginButton.click();
		log.info("CLick on login button and the object is:" + loginButton.toString());
		// Wait
		wait.waitForElementToBeInvisible(By.xpath("//div[@class='overlay-close']"), 30);
		wait.waitForLoad(driver);
	}

	// Error message
	public String errorMessage() {
		return errorMessage.getText();
	}

}
