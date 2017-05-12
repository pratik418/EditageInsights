package com.test.automation.uiAutomation.homepage;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_001VerifyLoginWithInvalidCredentials extends TestBase {


	@DataProvider(name = "loginDataEmail")
	public String[][] getTestData() {
		String[][] testRecords = getData("LoginTestData.xlsx", "LoginData");
		return testRecords;
	}

	@BeforeTest
	public void setup() throws IOException {
		init();
	}

	@Test(dataProvider = "loginDataEmail")
	public void invalidLoginCredentials(String emailAddress, String password, String runMode)
			throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		// Login
		homePage.loginApplicationInvalid(emailAddress, password);
		// Error message text
		String errorMessage = homePage.errorMessage();
		// Verify condition
		Assert.assertEquals("Error message", errorMessage);
	}

	@AfterClass
	public void endTest() {
		// Close the browser
		driver.close();
	}

}
