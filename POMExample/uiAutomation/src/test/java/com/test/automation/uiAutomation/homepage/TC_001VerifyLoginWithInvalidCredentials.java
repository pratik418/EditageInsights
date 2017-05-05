package com.test.automation.uiAutomation.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_001VerifyLoginWithInvalidCredentials {

	private WebDriver driver;

	@BeforeTest
	public void setup() {
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Open the browser
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://ei.editage.com/insights/");
		// Wait
		wait.waitForLoad(driver);
		// Maximize
		driver.manage().window().maximize();
	}

	@Test
	public void invalidLoginCredentials() throws InterruptedException {
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		// Login
		homePage.loginApplication("authenticated-user-test@mailinator.com", "abc");
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
