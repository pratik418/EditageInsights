package com.test.automation.uiAutomation.homepage;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_001VerifyLoginWithInvalidCredentials extends TestBase {


	@BeforeTest
	public void setup() throws IOException {
		init();
	}

	@Test
	public void invalidLoginCredentials() throws InterruptedException {
		
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		// Login
		homePage.loginApplicationInvalid("abc@test.com", "abc");
		// Error message text
		String errorMessage = homePage.errorMessage();
		// Verify condition
		Assert.assertEquals("Error message", errorMessage);
	}

	//@AfterClass
//	public void endTest() {
		// Close the browser
	//	driver.close();
//	}

}
