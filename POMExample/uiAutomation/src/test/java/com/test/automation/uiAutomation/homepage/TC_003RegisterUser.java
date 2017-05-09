package com.test.automation.uiAutomation.homepage;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_003RegisterUser extends TestBase {

	public static final Logger log = Logger.getLogger(TC_002VerifyLoginWithValidCredentials.class.getName());

	@BeforeTest
	public void setup() {
		init();
	}

	@Test
	public void registerUsername() throws InterruptedException {
		log.info("**************Starting the Test registerUsername********************");
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
		log.info("**************Finishing the Test registerUsername********************");
	}

	@AfterClass
	public void endTest() {
		// Close the browser
		driver.close();
	}
}
