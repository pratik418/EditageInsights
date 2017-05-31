package com.test.automation.uiAutomation.uiActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.test.automation.uiAutomation.homepage.explicitWait;

public class LoginRegister {

	WebDriver driver;
	explicitWait wait;

	public LoginRegister(WebDriver driver) {
		this.driver = driver;
		wait = new explicitWait(driver);
	}

	// Login Button
	@FindBy(xpath = "//div[@class='anonymous-login anonymous-text']/a")
	public WebElement logIn;

	// Login Pop-Up
	@FindBy(xpath = "//div[@id='mini-panel-register_popup']")
	public WebElement popUpFrame;

	// Email ID in Login
	@FindBy(id = "edit-name--2")
	public WebElement EmailId;

	// Password in Login
	@FindBy(id = "edit-pass--2")
	public WebElement password;

	// Login Button
	@FindBy(id = "edit-submit--3")
	public WebElement loginButton;

	// Error Message
	@FindBy(xpath = "//div[@class='messages messages--error']/h2")
	public WebElement errorMessage;

	// Register button
	@FindBy(xpath = "//div[@class='anonymous-register anonymous-text']")
	public WebElement registerButton;

	// Register form
	@FindBy(id = "formm-bg")
	public WebElement registrationForm;

	// Username block
	@FindBy(xpath = "//div[@class='user-name']")
	public WebElement username;

	// First Name
	@FindBy(id = "txtfirstname")
	public WebElement firstName;

	// Last name
	@FindBy(id = "txtlastname")
	public WebElement lastName;

	// Email Address
	@FindBy(id = "txtemail")
	public WebElement emailAddressRegister;

	// Password
	@FindBy(id = "txtpwd")
	public WebElement passwordRegister;

	// Confirm Password
	@FindBy(id = "txtcnfpwd")
	public WebElement confirmPassword;

	// Univeristy Name
	@FindBy(id = "txtorg")
	public WebElement universityName;

	// Job Title Select Id
	@FindBy(id = "drpjdesc")
	public WebElement jobTitleId;

	// Captcha Text
	@FindBy(xpath = "//span[@class='captchaText']")
	public WebElement captchaText;

	// Register for free button
	@FindBy(id = "crsubmit")
	public WebElement registerFreeButton;

	// Logout Link
	@FindBy(xpath = "//div[@class='user-menu-authenticated GoogleAnalyticsET-processed']//a[contains(@href,'logout')]")
	public WebElement logoutLink;

	// 1st Menu Manuscript Preparation
	@FindBy(xpath = "//nav[@id='block-system-main-menu']/ul/li[1]")
	public WebElement manuscriptMenu;

	// Save to Read later
	@FindBy(xpath = "//div[@class='views-row views-row-1']//a[contains(@href,'insights/flag')]")
	public WebElement saveLaterFlag;

	// First Content Title in Menu
	@FindBy(xpath = "//div[@class='views-row views-row-1']//div[@class='content-listing-title']/a")
	public WebElement firstContentTitle;

	// First Name Error
	@FindBy(id = "fnameerror")
	public WebElement firstNameError;

	// Last Name Error
	@FindBy(id = "lnameerror")
	public WebElement lastNameError;

	// Email Address Error
	@FindBy(id = "enameerror")
	public WebElement emailAddressError;

	// Password Error
	@FindBy(id = "pwderror")
	public WebElement passwordErrorMessage;

	// Confirm Password Error
	@FindBy(id = "cnfpwderror")
	public WebElement confirmPasswordError;

	// Job Title Error
	@FindBy(id = "jdescerror")
	public WebElement jobtitleError;

	// University/Institute/Company Error
	@FindBy(id = "orgerror")
	public WebElement univErrorMsg;

	// Captcha error
	@FindBy(id = "captchaerror")
	public WebElement captchaErrorMsg;

	// Login in Application Invalid Credentials
	public void loginApplicationInvalid(String emailAddress, String Password) {
		logIn.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + emailAddress + "';", EmailId);
		jse.executeScript("arguments[0].value='" + Password + "';", password);
		loginButton.click();
		// Wait
		wait.waitForElementToBeInvisible(By.xpath("//div[@class='overlay-close']"), 30);
		wait.waitForLoad(driver);
	}

	// Login in Application Valid Credentials
	public void loginApplicationvalid(String emailAddress, String Password) {
		logIn.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + emailAddress + "';", EmailId);
		jse.executeScript("arguments[0].value='" + Password + "';", password);
		loginButton.click();
		// Wait
		wait.waitForElementToBeInvisible(By.xpath("//div[@class='overlay-close']"), 30);
		wait.waitForLoad(driver);

	}

	// New User Registration
	public void registerUser(String firstname, String lastname, String email, String Password) {
		registerButton.click();
		wait.waitForLoad(driver);
		driver.switchTo().frame("insights-widget");
		wait.waitForElementFluently(registrationForm, 30);
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		emailAddressRegister.sendKeys(email);
		passwordRegister.sendKeys(Password);
		confirmPassword.sendKeys(Password);
		Select jobTitle = new Select(jobTitleId);
		jobTitle.selectByIndex(4);
		universityName.sendKeys("test");
		selectAnOption();
		registerFreeButton.click();
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

	// Logout
	public void logout() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", username);
		executor.executeScript("arguments[0].click();", logoutLink);
	}

	// Error message
	public String errorMessage() {
		return errorMessage.getText();
	}

	// Username Text
	public String authenticatedUsername() {
		return username.getText();
	}

	// First Content Title Text
	public String contentTitle() {
		return firstContentTitle.getText();
	}

}
