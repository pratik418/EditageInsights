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
import com.test.automation.uiAutomation.uiActions.LoginRegister;
import com.test.automation.uiAutomation.uiActions.Searchpage;

public class TC_004SearchPage extends TestBase {

	@BeforeTest
	public void setup() throws IOException {
		init();
	}

	@Test
	public void searchValidData() throws InterruptedException {

		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		boolean flag1 = false;
		// Send search text
		searchpage.searchBox.sendKeys("question");
		// Click search icon
		searchpage.searchIcon.click();
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
		// Verify condition
		if (flag1) {
			assertTrue(true, "User was able to search using search-box.");
		} else {
			assertTrue(false, "User was not able to search using search-box");
		}
	}

	// Verify Search Filter on basis of Filter By Format
	@Test
	public void searchFilterByFormat() throws InterruptedException {

		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false,
				flag8 = false;
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// click article filter
		searchpage.articleFilter.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumnsArticle;
		noOfColumnsArticle = driver.findElements(By.xpath("//article[@class='search-result']/span"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumnsArticle) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("article")) {
				flag1 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// click Q&A filter
		searchpage.QAFilter.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumnsQA;
		noOfColumnsQA = driver.findElements(By.xpath("//article[@class='search-result']/span"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumnsQA) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("Q&A FORUM")) {
				flag2 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// click research filter
		searchpage.researchFilter.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumnsResearch;
		noOfColumnsResearch = driver.findElements(By.xpath("//article[@class='search-result']/span"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumnsResearch) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("research")) {
				flag3 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// click interview filter
		searchpage.interviewFilter.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumnsInterview;
		noOfColumnsInterview = driver.findElements(By.xpath("//article[@class='search-result']/span"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumnsInterview) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("interview")) {
				flag4 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// click video filter
		searchpage.videoFilter.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumnsVideos;
		noOfColumnsVideos = driver.findElements(By.xpath("//article[@class='search-result']/span"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumnsVideos) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("video")) {
				flag5 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// click event filter
		searchpage.eventFilter.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumnsEvent;
		noOfColumnsEvent = driver.findElements(By.xpath("//article[@class='search-result']/span"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumnsEvent) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("event")) {
				flag6 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// click slideshare filter
		searchpage.slideshareFilter.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumnsSlideshare;
		noOfColumnsSlideshare = driver.findElements(By.xpath("//article[@class='search-result']/span"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumnsSlideshare) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("slideshare")) {
				flag7 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// click interactive filter
		searchpage.interactiveFilter.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumnsInteractive;
		noOfColumnsInteractive = driver.findElements(By.xpath("//article[@class='search-result']/span"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumnsInteractive) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("interactive")) {
				flag8 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// Verify condition
		if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8) {
			assertTrue(true, "Search By Filter Format Passed.");
		} else {
			assertTrue(false, "Search By Filter Format Failed");
		}
	}

	// Verify Search Filter on basis of Filter By Date
	@Test
	public void searchFilterByDate() throws InterruptedException {

		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false;
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// Click date filter 2013
		searchpage.date2013.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumns2013;
		noOfColumns2013 = driver.findElements(By.xpath("//footer[@class='search-result__info']"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumns2013) {
			// set flag when title is found
			if (cell.getText().contains("2013")) {
				flag1 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// Click date filter 2014
		searchpage.date2014.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumns2014;
		noOfColumns2014 = driver.findElements(By.xpath("//footer[@class='search-result__info']"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumns2014) {
			// set flag when title is found
			if (cell.getText().contains("2014")) {
				flag2 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// Click date filter 2015
		searchpage.date2015.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumns2015;
		noOfColumns2015 = driver.findElements(By.xpath("//footer[@class='search-result__info']"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumns2015) {
			// set flag when title is found
			if (cell.getText().contains("2015")) {
				flag3 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// Click date filter 2016
		searchpage.date2016.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumns2016;
		noOfColumns2016 = driver.findElements(By.xpath("//footer[@class='search-result__info']"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumns2016) {
			// set flag when title is found
			if (cell.getText().contains("2016")) {
				flag4 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// Click date filter 2017
		searchpage.date2017.click();
		// Wait
		wait.waitForLoad(driver);
		List<WebElement> noOfColumns2017;
		noOfColumns2017 = driver.findElements(By.xpath("//footer[@class='search-result__info']"));

		// Loop will rotate till expected title is found.
		for (WebElement cell : noOfColumns2017) {
			// set flag when title is found
			if (cell.getText().contains("2017")) {
				flag5 = true;
			}
		}
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);

		// Verify condition
		if (flag1 && flag2 && flag3 && flag4 && flag5) {
			assertTrue(true, "Search By Filter Date Passed.");
		} else {
			assertTrue(false, "Search By Filter Date Failed");
		}

	}

	// Verify that when clicked on any search results we are redirected to
	// appropriate detailed page
	@Test
	public void searchRedirectDetailPage() throws InterruptedException {

		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// Store the 1st string result
		String searchResult = searchpage.firstSearchResults.getText();
		// Click on the First Search result
		searchpage.firstSearchResults.click();
		// Wait
		wait.waitForLoad(driver);
		// Store the Content Title on the Detailed page
		String mainTitle = searchpage.titleDetailPage.getText();
		// Verify final condition
		if (searchResult.equalsIgnoreCase(mainTitle)) {
			assertTrue(true, "On clicking search result we are directed to Detailed page.");
		} else {
			assertTrue(false, "On clicking search result we are not directed to Detailed page.");
		}

	}

	// Verify when we click on Ask community link we are redirected to Ask A
	// Question page
	@Test
	public void askCommunityLink() throws InterruptedException {

		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// CLick ask the community link
		searchpage.askCommunityLink.click();
		// Wait
		wait.waitForLoad(driver);
		// Store the Ask community Heading
		String heading = searchpage.askCommunityHeading.getText();
		if (heading.equalsIgnoreCase("Ask a question")) {
			assertTrue(true, "On clicking ask the Community link  user is directed to Q&A forum page");
		} else {
			assertTrue(false, "On clicking ask the Community link  user is not  directed to Q&A forum page");
		}

	}

	// Verify proper message is shown when no results are found.
	@Test
	public void noSearchResultsFound() throws InterruptedException {

		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Send search text
		searchpage.searchBox.sendKeys("asdadsasdsadasdasdasd");
		// Click search icon
		searchpage.searchIcon.click();
		// Wait
		wait.waitForLoad(driver);
		// Store the no search result title
		String noSearchResult = searchpage.noResultTitle.getText();
		if (noSearchResult.contains("no results")) {
			assertTrue(true, "Proper Message is shown when no results are found");
		} else {
			assertTrue(false, "Proper Message is not shown when no results are found");
		}

	}

}
