package com.test.automation.uiAutomation.uiActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.test.automation.uiAutomation.homepage.explicitWait;

public class HomePage {

	WebDriver driver;
	explicitWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new explicitWait(driver);
	}

	// Get your copy image
	@FindBy(xpath = "//div[@class='regid-generator']//img")
	public WebElement getYourCopy;

	// What's new menu
	@FindBy(xpath = "//a[contains(@href,'whats-new')]")
	public WebElement whatNewMenu;

	// Events Submenu
	@FindBy(xpath = "//a[contains(@href,'categories/events')]")
	public WebElement eventsMenu;

	// Workshop Tab
	@FindBy(xpath = "//a[@class='panels-ajax-tab-tab panels-ajax-tabs-once-processed panels-ajax-tabs-processed panels-ajax-tabs-first-loaded']")
	public WebElement workshopTab;

	// Updates and Events
	@FindBy(xpath = "//a[contains(@href,'updates-and-events')]")
	public WebElement updateEvent;

	// First content in Update and Events
	@FindBy(xpath = "//div[@class='view-content']/div[1]//div[@class='views-field views-field-title']//a")
	public WebElement updateEventFirst;

	// Recent Articles homepage
	@FindBy(xpath = "//a[contains(@href,'recent-article')]")
	public WebElement recentArticlesHomepage;

	// 1st Recent Articles homepage
	@FindBy(xpath = "//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//div[@class='views-row views-row-1 views-row-odd views-row-first']//div[@class='views-field views-field-title']/span/a")
	public WebElement recentArticlesFirstTitle;

	// 1st Read More Link Click
	@FindBy(xpath = "//div[contains(@class, 'view view-article view-id-article view-display-id-panel_pane_3')]//div[@class='views-row views-row-1 views-row-odd views-row-first']//div[@class='views-field views-field-field-synopsis']/span/a")
	public WebElement readMoreFirstLink;

	// Most Popular Section Heading
	@FindBy(xpath = "//div[@class='panel-pane pane-panels-ajax-tab-tabs']/h2/a")
	public WebElement mostPopularSection;

	// Most Popular Section Detail Page Heading
	@FindBy(xpath = "//div[@class='panel-pane pane-panels-ajax-tab-tabs']/h2")
	public WebElement mostPopularDetailPage;
	
	//First Title in Most Popular Section
	@FindBy(xpath = "//div[contains(@class,'view view-most-popular- view-id-most_popular_ view-display-id-panel_pane_2 view-dom-id')]//div[@class='views-row views-row-1']/div[@class='views-field views-field-title']//a")
	public WebElement mostPopularFirstTitle;

	// Login in Application Valid Credentials
	public void clickMenu(WebElement mainMenu, WebElement mainSubMenu) {
		wait.waitForLoad(driver);
		Actions actions = new Actions(driver);
		wait.waitForElementFluently(mainMenu, 30);
		WebElement menu = mainMenu;
		actions.moveToElement(menu);
		wait.waitForElementFluently(mainSubMenu, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement subMenu = mainSubMenu;
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		wait.waitForLoad(driver);
	}

}
