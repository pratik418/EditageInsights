package com.test.automation.uiAutomation.homepage;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_001VerifyLoginWithInvalidCredentials extends TestBase {

	public static final Logger log = Logger.getLogger(TC_001VerifyLoginWithInvalidCredentials.class.getName());

	@BeforeTest
	public void setup() {
		init();
	}

	@Test
	public void invalidLoginCredentials() throws InterruptedException {
		log.info("**************Starting the Test invalidLoginCredentials********************");
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		// Login
		homePage.loginApplication("authenticated-user-test@mailinator.com", "abc");
		// Error message text
		String errorMessage = homePage.errorMessage();
		// Verify condition
		Assert.assertEquals("Error message", errorMessage);
		log.info("**************Finishing the Test invalidLoginCredentials********************");
	}

	@AfterClass
	public void endTest() {
		// Close the browser
		driver.close();
	}

}
