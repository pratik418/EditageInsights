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
	
	//First content in Update and Events
	@FindBy(xpath = "//div[@class='view-content']/div[1]//div[@class='views-field views-field-title']//a")
	public WebElement updateEventFirst;

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
