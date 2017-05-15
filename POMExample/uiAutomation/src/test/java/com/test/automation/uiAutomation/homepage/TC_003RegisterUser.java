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

	@Test
	public void registerUsername() throws InterruptedException {
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		// Login
		homePage.registerUser("Prst", "Psst", "pasd@mailinator.com", "abcd");
		// Error message text
		String username = homePage.authenticatedUsername();
		// Verify condition
		if (username.contains("Dr")) {
			assertTrue(true, "User successfully registered");
		} else {
			assertTrue(false, "User registration was unsuccessful");
		}
	}

	//@AfterClass
	//public void endTest() {
		// Close the browser
	//	driver.close();
	//}
}
