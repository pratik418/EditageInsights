package com.test.automation.uiAutomation.homepage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class explicitWait {

	private WebDriver driver = null;

	public explicitWait(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementFluently(WebElement identifyBy, int seconds) {
		int retry = 0;
		int retryCount = seconds < 25 ? 2 : 1;
		while (retry < retryCount) {
			try {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS)
						.pollingEvery(200, TimeUnit.MILLISECONDS);
				wait.until(ExpectedConditions.visibilityOf((identifyBy)));
			} catch (Exception ex) {
			}
			retry++;
		}
	}

	public void waitForElementsFluently(List<WebElement> identifyBy, int seconds) {
		int retry = 0;
		int retryCount = seconds < 25 ? 2 : 1;
		while (retry < retryCount) {
			try {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS)
						.pollingEvery(200, TimeUnit.MILLISECONDS);
				wait.until(ExpectedConditions.visibilityOfAllElements(identifyBy));
			} catch (Exception ex) {
			}
			retry++;
		}
	}

	public void waitForElementToBeInvisible(By locator, int seconds) {
		int retry = 0;
		int retryCount = 1;
		while (retry < retryCount) {
			try {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS)
						.pollingEvery(200, TimeUnit.MILLISECONDS);
				wait.until(ExpectedConditions.invisibilityOfElementLocated((locator)));
			} catch (Exception ex) {
			}
			retry++;
		}
	}

	public void waitForElementToBeClickable(WebElement element, int seconds) {
		int retry = 0;
		int retryCount = 1;
		while (retry < retryCount) {
			try {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS)
						.pollingEvery(200, TimeUnit.MILLISECONDS);
				wait.until(ExpectedConditions.elementToBeClickable(element));
			} catch (Exception ex) {
			}
			retry++;
		}
	}

	public void waitForElementAttributeToContainValue(WebElement element, String attribute, String value, int seconds) {
		int retry = 0;
		int retryCount = 1;
		while (retry < retryCount) {
			try {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS)
						.pollingEvery(200, TimeUnit.MILLISECONDS);
				wait.until(ExpectedConditions.attributeContains(element, attribute, value));
			} catch (Exception ex) {
			}
			retry++;
		}
	}

	// Method to check if an element is preesnt on the page
	public Boolean checkElementIsPresent(By locator) {
		this.waitForElementsFluently(driver.findElements(locator), 10);
		List<WebElement> result = driver.findElements(locator);
		if (result.size() > 0) {
			return true;
		} else
			return false;
	}

	// Method to check if text is present in an element
	public Boolean checkTextIsPresent(By locator, String text) {
		this.waitForElementsFluently(driver.findElements(locator), 10);
		if (driver.findElement(locator).getText().contains(text)) {
			return true;
		} else
			return false;
	}

	public void scrollPageUp() {
		Actions clicker = new Actions(driver);
		clicker.sendKeys(Keys.PAGE_UP);
	}

	public void scrollPageDown() {
		Actions clicker = new Actions(driver);
		clicker.sendKeys(Keys.PAGE_DOWN);
	}

	public void scrollPagetoPoint(String xvalue, String yvalue) {
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(" + xvalue + "," + yvalue + ")", "");
	}

	// Get current date time value for test data
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("HHmmssddMM");
		Date date = new Date();
		return dateFormat.format(date);
	}

	// Scroll to top of page by pressing "Up" arrow key for 5 seconds
	public void scrollToTop() {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).build().perform();
		action.keyUp(Keys.CONTROL).build().perform();
	}

	// Function to access shadow root elements
	public WebElement accessShadowRootElement(WebElement parentElement) {
		WebElement rootElement = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot", parentElement);

		return rootElement;
	}

	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}
}
