package com.test.automation.uiAutomation.homepage;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;
import com.test.automation.uiAutomation.uiActions.LoginRegister;
import com.test.automation.uiAutomation.uiActions.Searchpage;

public class TC_005Homepage extends TestBase {

	@BeforeTest
	public void setup() throws IOException {
		init();
		testClass = extent.startTest(getClass().getSimpleName());
	}

	// Verify when we click on "Get Your Copy Now" unauthenticated user is
	// directed to registration page
	@Test
	public void getYourCopy() throws InterruptedException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		LoginRegister loginReg = PageFactory.initElements(driver, LoginRegister.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// CLick on Get your copy image
		homepage.getYourCopy.click();
		// Switch to register Iframe
		driver.switchTo().frame("insights-widget");
		// Wait
		wait.waitForElementFluently(loginReg.registrationForm, 30);
		// Verify final condition
		if (driver.findElement(By.id("txtpwd")).isDisplayed()) {
			assertTrue(true,
					"On clicking Get Your Copy Now link unauthenticated user is directed to registration page");
		} else {
			assertTrue(false,
					"On clicking Get Your Copy Now link unauthenticated user is not directed to registration page");
		}
	}

	// Verify "Updates and Events" shows the latest content
	@Test
	public void updateEventsLatestContent() throws InterruptedException, ParseException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		boolean flag1 = false;
		// Click on Workshop Tab
		homepage.updateEvent.click();
		wait.waitForLoad(driver);
		// Store the date in list
		List<Date> listDates = new ArrayList<Date>();
		List<Date> sortDates = new ArrayList<Date>();
		List<WebElement> elementList = driver.findElements(By.xpath("//span[@class='posted-date']"));
		for (WebElement we : elementList) {
			Date date = format.parse(we.getText());
			listDates.add(date);
			sortDates.add(date);

		}
		// Sort based on collections
		Collections.sort(sortDates);
		Collections.reverse(sortDates);
		// Use iterator for checking values
		Iterator<Date> firstDate = listDates.iterator();
		Iterator<Date> secondDate = sortDates.iterator();
		while (firstDate.hasNext()) {
			Date date = firstDate.next();
			// recreate iterator for second list

			Date sdate = secondDate.next();
			if (date.equals(sdate)) {
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}
		// Final Condition
		if (flag1) {
			assertTrue(true, "Update Events shows the latest content");
		} else {
			assertTrue(false, "Update Events does not show the latest content");
		}
	}

	// Verify "Updates and Events" should contain image and title
	@Test
	public void updateEventsVerifyImgTitleListing() throws InterruptedException, ParseException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		boolean flag1 = false, flag2 = false;
		// Click on Workshop Tab
		homepage.updateEvent.click();
		wait.waitForLoad(driver);
		// Store the date in list
		List<WebElement> noOfColumnsImages;
		noOfColumnsImages = driver.findElements(By.xpath("//span[@class='thumbnail']//img"));
		List<WebElement> noOfColumnsTitle;
		noOfColumnsTitle = driver.findElements(
				By.xpath("//div[@class='views-field views-field-title']//span[@class='field-content']/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsImages) {
			// set flag when title is found
			if (cell.getAttribute("src").contains("/sites/default/files/styles/")) {
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}
		// Verify Presence of Title
		for (WebElement cellTitle : noOfColumnsTitle) {
			// set flag when title is found
			if (cellTitle.getAttribute("href").contains("/insights/")) {
				flag2 = true;
			} else {
				flag2 = false;
				break;
			}
		}
		// Verify Final Condition
		if (flag1 && flag2) {
			assertTrue(true, "Update Events page contains Images and Titles on Listing page");
		} else {
			assertTrue(true, "Update Events page does not contain Images and Titles on Listing page");
		}
	}

	// Verify clicking on content under "Updates and Events" takes to Detailed
	// Article
	@Test
	public void updateEventDetailPageDirect() throws InterruptedException, ParseException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Searchpage search = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Click on Workshop Tab
		homepage.updateEvent.click();
		wait.waitForLoad(driver);
		// Get the First Title in Updates and Events Page
		String title = homepage.updateEventFirst.getText();
		// Click on the first title
		homepage.updateEventFirst.click();
		wait.waitForLoad(driver);
		// Get the Heading Title on Detailed Page
		String mainTitle = search.askCommunityHeading.getText();
		// Verify Final Condition
		if (title.equals(mainTitle)) {
			assertTrue(true, "On clicking content on Updates and Events we are getting directed to Detailed Page");
		} else {
			assertTrue(false, "On clicking content on Updates and Events we are getting directed to Detailed Page");
		}
	}

	// Verify "Updates and Events" should contain image and title
	@Test
	public void updateEventsVerifyImgTitleHomePage() throws InterruptedException, ParseException {
		boolean flag1 = false, flag2 = false;
		List<WebElement> noOfColumnsImages;
		noOfColumnsImages = driver.findElements(By.xpath(
				"//div[@class='panel-pane pane-views-panes pane-update-and-events-panel-pane-1']//span[@class='thumbnail']/a/img"));
		List<WebElement> noOfColumnsTitle;
		noOfColumnsTitle = driver.findElements(By.xpath(
				"//div[@class='panel-pane pane-views-panes pane-update-and-events-panel-pane-1']//span[@class='field-content']/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsImages) {
			// set flag when title is found
			if (cell.getAttribute("src").contains("/sites/default/files/styles/")) {
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}
		// Verify Presence of Title
		for (WebElement cellTitle : noOfColumnsTitle) {
			// set flag when title is found
			if (cellTitle.getAttribute("href").contains("/insights/")) {
				flag2 = true;
			} else {
				flag2 = false;
				break;
			}
		}
		// Verify Final Condition
		if (flag1 && flag2) {
			assertTrue(true, "Update Events page contains Images and Titles on Homepage");
		} else {
			assertTrue(true, "Update Events page does not contain Images and Titles on Homepage");
		}
	}

	// Verify "Recent Article" shows the latest articles published
	@Test
	public void recentArticlesPublished() throws InterruptedException, ParseException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		boolean flag1 = false;
		// Click on Workshop Tab
		homepage.recentArticlesHomepage.click();
		// wait
		wait.waitForLoad(driver);
		// Store the date in list
		List<Date> listDates = new ArrayList<Date>();
		List<Date> sortDates = new ArrayList<Date>();
		List<WebElement> elementList = driver.findElements(By.xpath("//span[@class='posted-date']"));
		for (WebElement we : elementList) {
			Date date = format.parse(we.getText());
			listDates.add(date);
			sortDates.add(date);

		}
		// Sort based on collections
		Collections.sort(sortDates);
		Collections.reverse(sortDates);
		// Use iterator for checking values
		Iterator<Date> firstDate = listDates.iterator();
		Iterator<Date> secondDate = sortDates.iterator();
		while (firstDate.hasNext()) {
			Date date = firstDate.next();
			// recreate iterator for second list

			Date sdate = secondDate.next();
			if (date.equals(sdate)) {
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}
		// Final Condition
		if (flag1) {
			assertTrue(true, "Recent Articles shows the latest content");
		} else {
			assertTrue(false, "Recent Articles does not show the latest content");
		}
	}

	// Verify "Recent Article" should contain Title, under, By, Views, Add a
	// comment, rating, social icons, Image and teaser with read more link
	@Test
	public void verifyRecentArticlesTab() throws InterruptedException, ParseException {
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false,
				flag8 = false;
		// Verify presence of Title
		List<WebElement> noOfColumnsTitle;
		noOfColumnsTitle = driver.findElements(By.xpath(
				"//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//div[@class='views-field views-field-title']/span/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsTitle) {
			// set flag when title is found
			if (cell.getAttribute("href").contains("/insights/")) {
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}

		// Verify presence of Under, By, Views
		List<WebElement> noOfColumnsFeatures;
		noOfColumnsFeatures = driver.findElements(By.xpath(
				"//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//div[@class='views-field views-field-nothing-1']/span"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsFeatures) {
			// set flag when title is found
			if (cell.getText().contains("Views") && cell.getText().contains("By") && cell.getText().contains("Under")) {
				flag2 = true;
			} else {
				flag2 = false;
				break;
			}
		}

		// Verify presence of Comments
		List<WebElement> noOfColumnsComment;
		noOfColumnsComment = driver.findElements(By.xpath(
				"//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//span[@class='comment-counting']/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsComment) {
			// set flag when title is found
			if (cell.getText().contains("Add a comment")) {
				flag3 = true;
			} else {
				flag3 = false;
				break;
			}
		}

		// Verify presence of Average
		List<WebElement> noOfColumnsAverage;
		noOfColumnsAverage = driver.findElements(By.xpath(
				"//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//div[@class='fivestar-summary fivestar-summary-average-count']/span"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsAverage) {
			// set flag when title is found
			if (cell.getText().contains("Average")) {
				flag4 = true;
			} else {
				flag4 = false;
				break;
			}
		}
		// Verify Presence of rating
		List<WebElement> noOfColumnsStars;
		noOfColumnsStars = driver.findElements(By.xpath(
				"//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//div[contains(@class, 'star star-')]/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsStars) {
			// set flag when title is found
			if (cell.getText().contains("Give it")) {
				flag5 = true;
			} else {
				flag5 = false;
				break;
			}
		}
		// Verify Presence of Images
		List<WebElement> noOfColumnsImages;
		noOfColumnsImages = driver.findElements(By.xpath(
				"//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//span[@class='thumbnail']//img"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsImages) {
			// set flag when title is found
			if (cell.getAttribute("src").contains("/sites/default/files/styles/")) {
				flag6 = true;
			} else {
				flag6 = false;
				break;
			}
		}
		// Verify presence of Read More link
		List<WebElement> noOfColumnsReadMore;
		noOfColumnsReadMore = driver.findElements(By.xpath(
				"//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//div[@class='views-field views-field-field-synopsis']/span/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsReadMore) {
			// set flag when title is found
			if (cell.getText().contains("Read More")) {
				flag7 = true;
			} else {
				flag7 = false;
				break;
			}
		}

		// Verify Presence of Images
		List<WebElement> noOfColumnsSocialMediaIcons;
		noOfColumnsSocialMediaIcons = driver.findElements(By.xpath(
				"//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//div[@class='addthis_toolbox addthis_default_style']/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsSocialMediaIcons) {
			// set flag when title is found
			if (cell.getAttribute("href").contains("www.addthis.com/bookmark.php?v=")) {
				flag8 = true;
			} else {
				flag8 = false;
				break;
			}
		}
		// Final Condition
		if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8) {
			assertTrue(true,
					"Recent Article contains Title, under, By, Views, Add a comment, rating, social icons, Image and teaser with read more link");
		} else {
			assertTrue(false,
					"Recent Article does not contains Title, under, By, Views, Add a comment, rating, social icons, Image and teaser with read more link");
		}
	}

	// Verify click on Read More link in Recent Articles user is directed to
	// Detailed page
	@Test
	public void verifyReadMoreLinkClickRecentArticles() throws InterruptedException, ParseException {
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// First Title in the Recent Articles Section Homepage
		String title = homepage.recentArticlesFirstTitle.getText();
		// Click on the First Read More link
		homepage.readMoreFirstLink.click();
		// wait
		wait.waitForLoad(driver);
		// Verify title on the Detailed page
		String mainTitle = searchpage.askCommunityHeading.getText();
		// Verify Final Condition
		if (title.equalsIgnoreCase(mainTitle)) {
			assertTrue(true, "Read More link in Recent Articles user is directed to the Detailed page");
		} else {
			assertTrue(false, "Read More link in Recent Articles user is not directed to the Detailed page");
		}
	}

	// Verify clicking on "Recent Article" should take to the detailed "Recent
	// Article' page
	@Test
	public void recentArticleHeadingRedirection() throws InterruptedException, ParseException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Store the title in String
		String title = homepage.recentArticlesHomepage.getText();
		// Click on Workshop Tab
		homepage.recentArticlesHomepage.click();
		// wait
		wait.waitForLoad(driver);
		// Store the main title in String
		String mainTitle = searchpage.askCommunityHeading.getText();
		// Verify Final Condition
		if (title.equalsIgnoreCase(mainTitle)) {
			assertTrue(true, "On clicking on Recent Article we are directed to the  detailed Recent Article page");
		} else {
			assertTrue(false, "On clicking on Recent Article we are not directed to the  detailed Recent Article page");
		}
	}

	// Verify clicking on "Article" under "Recent Article" takes to Detailed
	// Article page
	@Test
	public void recentArticleTitleRedirection() throws InterruptedException, ParseException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Store the title in String
		String title = homepage.recentArticlesFirstTitle.getText();
		// Click on Workshop Tab
		homepage.recentArticlesFirstTitle.click();
		// wait
		wait.waitForLoad(driver);
		// Store the main title in String
		String mainTitle = searchpage.askCommunityHeading.getText();
		// Verify Final Condition
		if (title.equalsIgnoreCase(mainTitle)) {
			assertTrue(true, "On clicking  Recent Article Content titl we are directed to the  Detailed Article page");
		} else {
			assertTrue(false,
					"On clicking  Recent Article Content titl we are not directed to the  Detailed Article page");
		}
	}

	// Verify "Most Popular" section should have 3 tabs "This Week", "This
	// Month" and "All Time"
	@Test
	public void verifyMostpopularSectionTabs() throws InterruptedException, ParseException {
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		boolean flag1 = false, flag2 = false, flag3 = false;
		// Store the Title
		String title = homepage.mostPopularSection.getText();
		// Verify Condition
		if (title.equalsIgnoreCase("most popular")) {
			flag1 = true;
		} else {
			flag1 = false;
		}
		// Verify number of tabs
		List<WebElement> noOfTabs;
		noOfTabs = driver.findElements(By.xpath("//div[@class='panel-pane pane-panels-ajax-tab-tabs']//ul/li"));
		int size = noOfTabs.size();
		if (size == 3) {
			flag2 = true;
		} else {
			flag2 = false;
		}
		// Verify tab Names
		for (WebElement cell : noOfTabs) {
			// set flag when title is found
			if (cell.getText().equalsIgnoreCase("This Week") || cell.getText().equalsIgnoreCase("This Month")
					|| cell.getText().equalsIgnoreCase("All Time")) {
				flag3 = true;
			} else {
				flag3 = false;
				break;
			}
		}
		// Final Condition
		if (flag1 && flag2 && flag3) {
			assertTrue(true, "Most Popular section contains 3 tabs This Week, This Month and All Time");
		} else {
			assertTrue(false, "Most Popular section does not contain 3 tabs This Week, This Month and All Time");
		}

	}

	// Verify clicking on "Most Popular" should take user to detailed page
	@Test
	public void mostPopularHeaderRedirection() throws InterruptedException, ParseException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Click on Most Popular Tab
		homepage.mostPopularSection.click();
		// wait
		wait.waitForLoad(driver);
		// Store the main title in String
		String mainTitle = homepage.mostPopularDetailPage.getText();
		// Verify Final Condition
		if (mainTitle.equalsIgnoreCase("popular articles")) {
			assertTrue(true,
					"On clicking on Most Popular section we are directed to the  detailed Most Popular Articles page");
		} else {
			assertTrue(false,
					"On clicking on Most Popular section we are not getting directed to the  detailed Most Popular Articles page");
		}
	}

	// Verify "This Week tab" in Most Popular Section contains latest 4 contents
	// with Title,
	// under, By, Views, Add a comment, rating, social icons, Image and teaser
	// with read more link, should display according to the global views
	@Test
	public void verifyThisWeekContents() throws InterruptedException, ParseException {
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false,
				flag8 = false, flag9 = false;
		// Verify number of tabs
		List<WebElement> noOfTabs;
		noOfTabs = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-weekly-')]//div[@class='views-field views-field-title']//a"));
		int size = noOfTabs.size();
		if (size == 4) {
			flag1 = true;
		} else {
			flag1 = false;
		}
		// Verify tab Names
		for (WebElement cell : noOfTabs) {
			// set flag when title is found
			if (cell.getAttribute("href").contains("/insights/")) {
				flag2 = true;
			} else {
				flag2 = false;
				break;
			}
		}
		// Verify number of Under and Views
		List<WebElement> noOfContentUnder;
		noOfContentUnder = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-weekly-')]//div[@class='views-field views-field-nothing']/span"));
		// Verify tab Names
		for (WebElement cell : noOfContentUnder) {
			// set flag when title is found
			if (cell.getText().contains("Under") && cell.getText().contains("Views")) {
				flag3 = true;
			} else {
				flag3 = false;
				break;
			}
		}
		// Verify number of Under and Views
		List<WebElement> noOfContentBy;
		noOfContentBy = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-weekly-')]//div[@class='views-field views-field-nothing']/span[1]/span[1]"));
		// Verify tab Names
		for (WebElement cell : noOfContentBy) {
			// set flag when title is found
			if (cell.getText().contains("By")) {
				flag4 = true;
			} else {
				flag4 = false;
				break;
			}
		}

		// Verify number of Add comments
		List<WebElement> noOfComments;
		noOfComments = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-weekly-')]//div[@class='views-field views-field-nothing']/span[1]/span[2]/a"));
		// Verify tab Names
		for (WebElement cell : noOfComments) {
			// set flag when title is found
			if (cell.getText().contains("Add a comment")) {
				flag5 = true;
			} else {
				flag5 = false;
				break;
			}
		}

		// Verify Presence of rating
		List<WebElement> noOfColumnsStars;
		noOfColumnsStars = driver.findElements(
				By.xpath("//div[contains(@class,'most-popular-weekly-')]//div[contains(@class, 'star star-')]/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsStars) {
			// set flag when title is found
			if (cell.getText().contains("Give it")) {
				flag6 = true;
			} else {
				flag6 = false;
				break;
			}
		}

		// Verify presence of Average
		List<WebElement> noOfColumnsAverage;
		noOfColumnsAverage = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-weekly-')]//div[@class='fivestar-summary fivestar-summary-average-count']/span"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsAverage) {
			// set flag when title is found
			if (cell.getText().contains("Average")) {
				flag7 = true;
			} else {
				flag7 = false;
				break;
			}
		}

		// Verify Presence of Images
		List<WebElement> noOfColumnsImages;
		noOfColumnsImages = driver.findElements(
				By.xpath("//div[contains(@class,'most-popular-weekly-')]//span[@class='thumbnail']//img"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsImages) {
			// set flag when title is found
			if (cell.getAttribute("src").contains("/sites/default/files/styles/")) {
				flag8 = true;
			} else {
				flag8 = false;
				break;
			}
		}

		// Verify Presence of Images
		List<WebElement> noOfColumnsSocialMediaIcons;
		noOfColumnsSocialMediaIcons = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-weekly-')]//div[@class='views-field views-field-field-social-sharing']//a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsSocialMediaIcons) {
			// set flag when title is found
			if (cell.getAttribute("href").contains("www.addthis.com/bookmark.php?v=")) {
				flag9 = true;
			} else {
				flag9 = false;
				break;
			}
		}
		// Final Condition
		if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8 && flag9) {
			assertTrue(true,
					"Most Popular-This Week contains Title, under, By, Views, Add a comment, rating, social icons, and Images");
		} else {
			assertTrue(false,
					"Most Popular-This Week does not contains Title, under, By, Views, Add a comment, rating, social icons, and Images");
		}
	}

	// Verify "This Month tab" in Most Popular Section contains latest 4
	// contents
	// with Title,
	// under, By, Views, Add a comment, rating, social icons, Image and teaser
	// with read more link, should display according to the global views
	@Test
	public void verifyThisMonthContents() throws InterruptedException, ParseException {
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false,
				flag8 = false, flag9 = false;
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Click on Most Popular Tab
		homepage.mostPopularThisMonthTab.click();
		// wait
		wait.waitForElementToBeInvisible(By.xpath("//li[@class='list-2']"), 30);
		// Verify number of tabs
		List<WebElement> noOfTabs;
		noOfTabs = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-monthly-')]//div[@class='views-field views-field-title']//a"));
		int size = noOfTabs.size();
		if (size == 4) {
			flag1 = true;
		} else {
			flag1 = false;
		}
		// Verify tab Names
		for (WebElement cell : noOfTabs) {
			// set flag when title is found
			if (cell.getAttribute("href").contains("/insights/")) {
				flag2 = true;
			} else {
				flag2 = false;
				break;
			}
		}
		// Verify number of Under and Views
		List<WebElement> noOfContentUnder;
		noOfContentUnder = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-monthly-')]//div[@class='views-field views-field-nothing']/span"));
		// Verify tab Names
		for (WebElement cell : noOfContentUnder) {
			// set flag when title is found
			if (cell.getText().contains("Under") && cell.getText().contains("Views")) {
				flag3 = true;
			} else {
				flag3 = false;
				break;
			}
		}
		// Verify number of Under and Views
		List<WebElement> noOfContentBy;
		noOfContentBy = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-monthly-')]//div[@class='views-field views-field-nothing']/span[1]/span[1]"));
		// Verify tab Names
		for (WebElement cell : noOfContentBy) {
			// set flag when title is found
			if (cell.getText().contains("By")) {
				flag4 = true;
			} else {
				flag4 = false;
				break;
			}
		}

		// Verify number of Add comments
		List<WebElement> noOfComments;
		noOfComments = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-monthly-')]//div[@class='views-field views-field-nothing']/span[1]/span[2]/a"));
		// Verify tab Names
		for (WebElement cell : noOfComments) {
			// set flag when title is found
			if (cell.getText().contains("Add a comment")) {
				flag5 = true;
			} else {
				flag5 = false;
				break;
			}
		}

		// Verify Presence of rating
		List<WebElement> noOfColumnsStars;
		noOfColumnsStars = driver.findElements(
				By.xpath("//div[contains(@class,'most-popular-monthly-')]//div[contains(@class, 'star star-')]/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsStars) {
			// set flag when title is found
			if (cell.getText().contains("Give it")) {
				flag6 = true;
			} else {
				flag6 = false;
				break;
			}
		}

		// Verify presence of Average
		List<WebElement> noOfColumnsAverage;
		noOfColumnsAverage = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-monthly-')]//div[@class='fivestar-summary fivestar-summary-combo']/span"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsAverage) {
			// set flag when title is found
			if (cell.getText().contains("Average")) {
				flag7 = true;
			} else {
				flag7 = false;
				break;
			}
		}

		// Verify Presence of Images
		List<WebElement> noOfColumnsImages;
		noOfColumnsImages = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-monthly-')]//div[@class='views-field views-field-field-thumbnail']//img"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsImages) {
			// set flag when title is found
			if (cell.getAttribute("src").contains("/sites/default/files/styles/")) {
				flag8 = true;
			} else {
				flag8 = false;
				break;
			}
		}

		// Verify Presence of Images
		List<WebElement> noOfColumnsSocialMediaIcons;
		noOfColumnsSocialMediaIcons = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-monthly-')]//div[@class='views-field views-field-field-social-sharing']//a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsSocialMediaIcons) {
			// set flag when title is found
			if (cell.getAttribute("href").contains("www.addthis.com/bookmark.php?v=")) {
				flag9 = true;
			} else {
				flag9 = false;
				break;
			}
		}
		// Final Condition
		if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8 && flag9) {
			assertTrue(true,
					"Most Popular-This Month contains Title, under, By, Views, Add a comment, rating, social icons, and Images");
		} else {
			assertTrue(false,
					"Most Popular-This Month does not contains Title, under, By, Views, Add a comment, rating, social icons, and Images");
		}
	}

	// Verify "All Time tab" in Most Popular Section contains latest 4 contents
	// with Title,
	// under, By, Views, Add a comment, rating, social icons, Image and teaser
	// with read more link, should display according to the global views
	@Test
	public void verifyAllTimeContents() throws InterruptedException, ParseException {
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false,
				flag8 = false, flag9 = false;

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Click on Most Popular All Time Tab
		homepage.mostPopularAllTimeTab.click();
		// wait
		wait.waitForElementToBeInvisible(By.xpath("//li[@class='list-3']"), 30);
		// Verify number of tabs
		List<WebElement> noOfTabs;
		noOfTabs = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-alltime-')]//div[@class='views-field views-field-title']//a"));
		int size = noOfTabs.size();
		if (size == 4) {
			flag1 = true;
		} else {
			flag1 = false;
		}
		// Verify tab Names
		for (WebElement cell : noOfTabs) {
			// set flag when title is found
			if (cell.getAttribute("href").contains("/insights/")) {
				flag2 = true;
			} else {
				flag2 = false;
				break;
			}
		}
		// Verify number of Under and Views
		List<WebElement> noOfContentUnder;
		noOfContentUnder = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-alltime-')]//div[@class='views-field views-field-nothing']/span"));
		// Verify tab Names
		for (WebElement cell : noOfContentUnder) {
			// set flag when title is found
			if (cell.getText().contains("Under") && cell.getText().contains("Views")) {
				flag3 = true;
			} else {
				flag3 = false;
				break;
			}
		}
		// Verify number of Under and Views
		List<WebElement> noOfContentBy;
		noOfContentBy = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-alltime-')]//div[@class='views-field views-field-nothing']/span[1]/span[1]"));
		// Verify tab Names
		for (WebElement cell : noOfContentBy) {
			// set flag when title is found
			if (cell.getText().contains("By")) {
				flag4 = true;
			} else {
				flag4 = false;
				break;
			}
		}

		// Verify number of Add comments
		List<WebElement> noOfComments;
		noOfComments = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-alltime-')]//div[@class='views-field views-field-nothing']/span[1]/span[2]/a"));
		// Verify tab Names
		for (WebElement cell : noOfComments) {
			// set flag when title is found
			if (cell.getText().contains("Add a comment")) {
				flag5 = true;
			} else {
				flag5 = false;
				break;
			}
		}

		// Verify Presence of rating
		List<WebElement> noOfColumnsStars;
		noOfColumnsStars = driver.findElements(
				By.xpath("//div[contains(@class,'most-popular-alltime-')]//div[contains(@class, 'star star-')]/a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsStars) {
			// set flag when title is found
			if (cell.getText().contains("Give it")) {
				flag6 = true;
			} else {
				flag6 = false;
				break;
			}
		}

		// Verify presence of Average
		List<WebElement> noOfColumnsAverage;
		noOfColumnsAverage = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-alltime-')]//div[@class='fivestar-summary fivestar-summary-average-count']/span"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsAverage) {
			// set flag when title is found
			if (cell.getText().contains("Average")) {
				flag7 = true;
			} else {
				flag7 = false;
				break;
			}
		}

		// Verify Presence of Images
		List<WebElement> noOfColumnsImages;
		noOfColumnsImages = driver.findElements(
				By.xpath("//div[contains(@class,'most-popular-alltime-')]//span[@class='thumbnail']//img"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsImages) {
			// set flag when title is found
			if (cell.getAttribute("src").contains("/sites/default/files/styles/")) {
				flag8 = true;
			} else {
				flag8 = false;
				break;
			}
		}

		// Verify Presence of Images
		List<WebElement> noOfColumnsSocialMediaIcons;
		noOfColumnsSocialMediaIcons = driver.findElements(By.xpath(
				"//div[contains(@class,'most-popular-alltime-')]//div[@class='views-field views-field-field-social-sharing']//a"));
		// Verify Presence of Images
		for (WebElement cell : noOfColumnsSocialMediaIcons) {
			// set flag when title is found
			if (cell.getAttribute("href").contains("www.addthis.com/bookmark.php?v=")) {
				flag9 = true;
			} else {
				flag9 = false;
				break;
			}
		}
		// Final Condition
		if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8 && flag9) {
			assertTrue(true,
					"Most Popular-All Time contains Title, under, By, Views, Add a comment, rating, social icons, and Images");
		} else {
			assertTrue(false,
					"Most Popular-All Time does not contains Title, under, By, Views, Add a comment, rating, social icons, and Images");
		}
	}

	// Verify clicking on contents in "Most Popular" should open detailed page
	@Test
	public void mostPopularSectionContentRedirection() throws InterruptedException, ParseException {

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		// Most Popular This Week Tab Content Title
		String titleWeek = homepage.firstTitleThisWeek.getText();
		// Parent Window
		String parentHandle = driver.getWindowHandle();
		// Click on title
		homepage.firstTitleThisWeek.click();
		// Switch to new tab
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		// wait
		wait.waitForLoad(driver);
		// Detailed Page Title This Week
		String maintitleWeek = searchpage.askCommunityHeading.getText();
		// Close the tab
		driver.close();
		// Switch to old tab
		driver.switchTo().window(parentHandle);
		// Click on Most Popular Tab This Month
		homepage.mostPopularThisMonthTab.click();
		// wait
		wait.waitForElementToBeInvisible(By.xpath("//li[@class='list-2']"), 30);
		// Most Popular This Month Tab Content Title
		String titleMonth = homepage.firstTitleThisMonth.getText();
		// Click on title
		homepage.firstTitleThisMonth.click();
		// Switch to new tab
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		// wait
		wait.waitForLoad(driver);
		// Detailed Page Title This Month
		String maintitleMonth = searchpage.askCommunityHeading.getText();
		// Close the tab
		driver.close();
		// Switch to old tab
		driver.switchTo().window(parentHandle);
		// Click on Most Popular Tab All Time
		homepage.mostPopularAllTimeTab.click();
		// wait
		wait.waitForElementToBeInvisible(By.xpath("//li[@class='list-3']"), 30);
		// Most Popular This Month Tab Content Title
		String titleAllTime = homepage.firstTitleAllTime.getText();
		// Click on title
		homepage.firstTitleAllTime.click();
		// Switch to new tab
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		// wait
		wait.waitForLoad(driver);
		// Detailed Page Title This Month All Time
		String maintitleAllTime = searchpage.askCommunityHeading.getText();
		// Close the tab
		driver.close();
		// Switch to old tab
		driver.switchTo().window(parentHandle);
		// Verify Final Condition
		if (titleWeek.equalsIgnoreCase(maintitleWeek) && titleMonth.equalsIgnoreCase(maintitleMonth)
				&& titleAllTime.equalsIgnoreCase(maintitleAllTime)) {
			assertTrue(true,
					"On clicking on Most Popular Content  we are directed to the  detailed Most Popular Articles Detailed page");
		} else {
			assertTrue(false,
					"On clicking on Most Popular Content  we are not directed to the  detailed Most Popular Articles Detailed page");
		}
	}
	
	// Verify featured Interview block should be shown
		@Test
		public void verifyFeaturedInterviewBlock() throws InterruptedException, ParseException {

			HomePage homepage = PageFactory.initElements(driver, HomePage.class);
			Searchpage searchpage = PageFactory.initElements(driver, Searchpage.class);
			explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
			// Interview Title
			String title = homepage.featuredInterviewTitle.getText();
			//Content Title in interview Block
			String contentTitle = homepage.featuredInterviewContentTitle.getText();
			homepage.featuredInterviewTitle.click();
			String mainTitle = searchpage.askCommunityHeading.getText();
		}

}
