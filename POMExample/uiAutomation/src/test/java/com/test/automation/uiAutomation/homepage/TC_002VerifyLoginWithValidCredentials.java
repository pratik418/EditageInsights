package com.test.automation.uiAutomation.homepage;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.LoginRegister;

public class TC_002VerifyLoginWithValidCredentials extends TestBase {

	@DataProvider(name = "loginDataEmail")
	public String[][] getTestData() {
		String[][] testRecords = getData("LoginTestData.xlsx", "LoginData");
		return testRecords;
	}

	@BeforeClass
	public void setup() throws IOException {
		init();
	}

	// Verify Admin and Authenticated should be able to login
	//Verify admin and authenticated user should be able to logout
	@Test(dataProvider = "loginDataEmail")
	public void validLoginCredentials(String emailAddress, String password, String runMode)
			throws InterruptedException, IOException {
		LoginRegister homePage = PageFactory.initElements(driver, LoginRegister.class);
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		// Login
		homePage.loginApplicationvalid(emailAddress, password);
		// Error message text
		String username = homePage.authenticatedUsername();
		// Verify condition
		if (username.contains("Hello")) {
			assertTrue(true, "User successfully logged in");
		} else {
			assertTrue(false, "User login unsuccessful");
		}
		// logout
		homePage.logout();
	}

	// Verify Authenticated user should be able to login using Top button,
	// Access denied login block and anynomus user click on "Save to Library"
	// link
	@Test
	public void authenticatedUserSaveToLibrary() throws InterruptedException, IOException {
		LoginRegister homePage = PageFactory.initElements(driver, LoginRegister.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		boolean flag1 = false, flag2 = false;
		// Login
		homePage.loginApplicationvalid("authenticated-user-test@mailinator.com", "authenticatedusertest");
		// Error message text
		String username = homePage.authenticatedUsername();
		// Verify condition
		if (username.contains("Hello")) {
			flag1 = true;
		} else {
			flag1 = false;
		}
		// Manuscipt Preparation Menu Clicked
		homePage.manuscriptMenu.click();
		// Click save later flag
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", homePage.saveLaterFlag);
		// First Title
		String title = homePage.contentTitle();
		driver.get("http://ei.editage.com/insights/user/bookmarked-articles");
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumns;
		noOfColumns = driver.findElements(By.xpath("//div[@class='views-field views-field-title']/span/a"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumns) {
			// set flag when title is found
			if (cell.getText().contains(title)) {
				flag2 = true;
			}
		}
		// Final Condition
		if (flag1 && flag2) {
			assertTrue(true, "User successfully logged in and Content is saved");

		} else {
			assertTrue(false, "User login unsuccessful and content is not saved");
		}

	}

	// @AfterClass()
	// public void endTest() {
	// driver.quit();
	// }
}