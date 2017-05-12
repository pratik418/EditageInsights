package com.test.automation.uiAutomation.homepage;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_002VerifyLoginWithValidCredentials extends TestBase {

	int i = 0;

	@BeforeClass
	public void setup() throws IOException {
		init();
	}

	@Test(priority = 0, enabled = true, description = "Test Registration with valid data")
	public void validLoginCredentials() throws InterruptedException, IOException {
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		// Login
		homePage.loginApplicationvalid("authenticated-user-test@mailinator.com", "authenticatedusertest");
		// Error message text
		String username = homePage.authenticatedUsername();
		// Verify condition
		if (username.contains("Authenticated")) {
			assertTrue(true, "User successfully logged in");
			updateResult(i, "Login With Valid Data", "Pass", "Registration Test");
		} else {
			assertTrue(false, "User login unsuccessful");
			updateResult(i, "Login With Valid Data", "Fail", "Login Test");
		}
	}

	@AfterClass()
	public void endTest() {
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
}