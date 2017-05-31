package com.test.automation.uiAutomation.homepage;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.LoginRegister;

public class TC_003RegisterUser extends TestBase {

	@BeforeTest
	public void setup() throws IOException {
		init();
	}

	// Verify anonymous user should be able to register
	// Verify registered user should be able to login
	// Verify correct details of registered user is shown
	@Test
	public void registerUsername() throws InterruptedException {
		LoginRegister homePage = PageFactory.initElements(driver, LoginRegister.class);
		boolean flag1 = false;
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String Email = "p" +formatter.format(date)+ "@mailinator.com";  
		// Register
		homePage.registerUser("Ptesst", "SStest", Email, "abcd");
		// Error message text
		String username = homePage.authenticatedUsername();
		// Verify condition
		if (username.contains("Hello")) {
			flag1 = true;
		} else {
			flag1 = false;
		}
		// logout
		homePage.logout();
		// Login
		homePage.loginApplicationvalid(Email, "abcd");
		// Error message text
		String loginUsername = homePage.authenticatedUsername();
		// Verify condition
		if (loginUsername.contains("Hello") && flag1) {
			assertTrue(true, "User registered and logged in successfully");
		} else {
			assertTrue(false, "User registration was unsuccessful");
		}
	}

	// Verify error messages are displayed for wrong and blank registration
	// field
	@Test
	public void registerErrorMessages() {
		LoginRegister homePage = PageFactory.initElements(driver, LoginRegister.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Register Button Click
		homePage.registerButton.click();
		// Wait for page to Load
		wait.waitForLoad(driver);
		// Switch to iframe
		driver.switchTo().frame("insights-widget");
		// Click on the register button to register
		homePage.registerFreeButton.click();
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false,
				flag8 = false;
		// Verify all the Error Messages
		if (homePage.firstNameError.getText().contains("Re-enter your name")) {
			flag1 = true;
		}
		if (homePage.lastNameError.getText().contains("Re-enter your last name")) {
			flag2 = true;
		}
		if (homePage.emailAddressError.getText().contains("Enter your email address")) {
			flag3 = true;
		}
		if (homePage.passwordErrorMessage.getText().contains("Set your account password")) {
			flag4 = true;
		}
		if (homePage.confirmPasswordError.getText().contains("Please enter your password again for confirmation")) {
			flag5 = true;
		}
		if (homePage.jobtitleError.getText().contains("Select a job description from the dropdown list")) {
			flag6 = true;
		}
		if (homePage.univErrorMsg.getText().contains("Enter the name of the organization")) {
			flag7 = true;
		}
		if (homePage.captchaErrorMsg.getText().contains("Please select a captcha")) {
			flag8 = true;
		}
		// Verify the Final condition
		if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8) {
			assertTrue(true, "Registration Error Messages are shown");
		} else {
			assertTrue(false, "Registration Error Messages are nto shown");
		}

	}

	// @AfterClass
	// public void endTest() {
	// Close the browser
	// driver.close();
	// }
}
