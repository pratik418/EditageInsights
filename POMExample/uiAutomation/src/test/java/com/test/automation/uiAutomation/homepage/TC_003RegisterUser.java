package com.test.automation.uiAutomation.homepage;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_003RegisterUser extends TestBase {

	@BeforeTest
	public void setup() throws IOException {
		init();
	}
	
	//Verify anonymous user should be able to register
	//Verify registered user should be able to login
	//Verify correct details of registered user is shown
	@Test
	public void registerUsername() throws InterruptedException {
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		boolean flag1 = false;
		// Register
		homePage.registerUser("Ptesst", "SStest", "pgfd@mailinator.com", "abcd");
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
		homePage.loginApplicationvalid("pgfd@mailinator.com", "abcd");
		// Error message text
		String loginUsername = homePage.authenticatedUsername();
		// Verify condition
		if (loginUsername.contains("Hello") && flag1) {
			assertTrue(true, "User registered and logged in successfully");
		} else {
			assertTrue(false, "User registration was unsuccessful");
		}
	}

	// @AfterClass
	// public void endTest() {
	// Close the browser
	// driver.close();
	// }
}
