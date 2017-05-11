package com.test.automation.uiAutomation.homepage;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_002VerifyLoginWithValidCredentials extends TestBase {

	public static final Logger log = Logger.getLogger(TC_002VerifyLoginWithValidCredentials.class.getName());

	@BeforeTest
	public void setup() {
		init();
	}

	@Test
	public void validLoginCredentials() throws InterruptedException {
		log.info("**************Starting the Test validLoginCredentials********************");
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		// Login
		homePage.loginApplicationvalid("authenticated-user-test@mailinator.com", "authenticatedusertest");
		// Error message text
		String username = homePage.authenticatedUsername();
		getScreenShot("Username" +username);
		// Verify condition
		if (username.contains("Authenticated")) {
			assertTrue(true, "User successfully logged in");
		} else {
			assertTrue(false, "User login unsuccessful");
		}
		log.info("**************Finishing the Test validLoginCredentials********************");
	}

	@AfterClass
	public void endTest() {
		// Close the browser
		driver.close();
	}
}