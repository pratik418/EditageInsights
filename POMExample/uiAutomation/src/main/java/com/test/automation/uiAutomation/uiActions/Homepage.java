package com.test.automation.uiAutomation.uiActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

	// Register button
	@FindBy(xpath = "//div[@class='anonymous-register anonymous-text']")
	WebElement registerButton;

	// Register form
	@FindBy(id = "formm-bg")
	WebElement registrationForm;

	// Username block
	@FindBy(xpath = "//div[@class='user-name']/span")
	WebElement username;

	// First Name
	@FindBy(id = "txtfirstname")
	WebElement firstName;

	// Last name
	@FindBy(id = "txtlastname")
	WebElement lastName;

	// Email Address
	@FindBy(id = "txtemail")
	WebElement emailAddressRegister;

	// Password
	@FindBy(id = "txtpwd")
	WebElement passwordRegister;

	// Confirm Password
	@FindBy(id = "txtcnfpwd")
	WebElement confirmPassword;

	// Univeristy Name
	@FindBy(id = "txtorg")
	WebElement universityName;

	// Job Title Select Id
	@FindBy(id = "drpjdesc")
	WebElement jobTitleId;

	// Captcha Text
	@FindBy(xpath = "//span[@class='captchaText']")
	WebElement captchaText;

	// Register for free button
	@FindBy(id = "crsubmit")
	WebElement registerFreeButton;

	// Login in Application Invalid Credentials
	public void loginApplicationInvalid(String emailAddress, String Password) {
		logIn.click();
		log.info("Clicked on sign in and object is:" + logIn.toString());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + emailAddress + "';", EmailId);
		jse.executeScript("arguments[0].value='" + Password + "';", password);
		loginButton.click();
		log.info("CLick on login button and the object is:" + loginButton.toString());
		// Wait
		wait.waitForElementToBeInvisible(By.xpath("//div[@class='overlay-close']"), 30);
		wait.waitForLoad(driver);
	}

	// Login in Application Valid Credentials
	public void loginApplicationvalid(String emailAddress, String Password) {
		logIn.click();
		log.info("Clicked on sign in and object is:" + logIn.toString());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + emailAddress + "';", EmailId);
		jse.executeScript("arguments[0].value='" + Password + "';", password);
		loginButton.click();
		log.info("CLick on login button and the object is:" + loginButton.toString());
		// Wait
		wait.waitForElementToBeInvisible(By.xpath("//div[@class='overlay-close']"), 30);
		wait.waitForLoad(driver);
	}

	// New User Registration
	public void registerUser(String firstname, String lastname, String email, String Password) {
		registerButton.click();
		log.info("Clicked on sign in and object is:" + registerButton.toString());
		wait.waitForLoad(driver);
		driver.switchTo().frame("insights-widget");
		wait.waitForElementFluently(registrationForm, 30);
		firstName.sendKeys(firstname);
		log.info("First Name object is:" + firstName.toString());
		lastName.sendKeys(lastname);
		log.info("Last Name object is:" + lastName.toString());
		emailAddressRegister.sendKeys(email);
		log.info("Email Address Name object is:" + emailAddressRegister.toString());
		passwordRegister.sendKeys(Password);
		log.info("Password object is:" + passwordRegister.toString());
		confirmPassword.sendKeys(Password);
		log.info("Confirm password object is:" + confirmPassword.toString());
		Select jobTitle = new Select(jobTitleId);
		jobTitle.selectByIndex(4);
		universityName.sendKeys("test");
		log.info("University name object is:" + universityName.toString());
		selectAnOption();
		registerFreeButton.click();
		log.info("Clicked on register free button:" + registerFreeButton.toString());
		wait.waitForElementToBeInvisible(By.id("modalOverlay"), 50);
		driver.switchTo().defaultContent();

	}

	// Select captcha
	public void selectAnOption() {
		List<WebElement> choice = driver.findElements(By.xpath("//div[@class='captchaImages']/img"));
		for (WebElement e : choice) {
			System.out.println(e.getAttribute("src"));
			if (e.getAttribute("src").contains("01.png") && captchaText.getText().contains("House")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("02.png") && captchaText.getText().contains("Key")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("03.png") && captchaText.getText().contains("Flag")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("04.png") && captchaText.getText().contains("Clock")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("05.png") && captchaText.getText().contains("Bug")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("06.png") && captchaText.getText().contains("Pen")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("07.png") && captchaText.getText().contains("Light")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("08.png") && captchaText.getText().contains("Musical")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("09.png") && captchaText.getText().contains("Heart")) {
				e.click();
				break;
			} else if (e.getAttribute("src").contains("10.png") && captchaText.getText().contains("World")) {
				e.click();
				break;
			}
		}
	}

	// Error message
	public String errorMessage() {
		return errorMessage.getText();
	}

	// Username Text
	public String authenticatedUsername() {
		return username.getText();
	}

}
