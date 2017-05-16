package com.test.automation.uiAutomation.homepage;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.Homepage;

public class TC_004SearchPage extends TestBase {


	@BeforeTest
	public void setup() throws IOException {
		init();
	}

	@Test
	public void searchValidData() throws InterruptedException {
		
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		boolean flag1=false;
		//Send search text
		homePage.searchBox.sendKeys("question");
		// Click search icon
		homePage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumns;
		noOfColumns = driver.findElements(By.xpath("//h3/a"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumns) {
			// set flag when title is found
			if (cell.getText().contains("question")) {
				flag1 = true;
			}
		}
		//Verify condition
		if(flag1){
			assertTrue(true, "User was able to search using search-box.");
		}else{
			assertTrue(false, "User was not able to search using search-box");
		}
	}
	
	
	@Test
	public void searchFilters() throws InterruptedException {
		
		Homepage homePage = PageFactory.initElements(driver, Homepage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		boolean flag1=false;
		//Send search text
		homePage.searchBox.sendKeys("question");
		// Click search icon
		homePage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumns;
		noOfColumns = driver.findElements(By.xpath("//h3/a"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumns) {
			// set flag when title is found
			if (cell.getText().contains("question")) {
				flag1 = true;
			}
		}
		//Verify condition
		if(flag1){
			assertTrue(true, "User was able to search using search-box.");
		}else{
			assertTrue(false, "User was not able to search using search-box");
		}
	}


}
