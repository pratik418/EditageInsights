package com.test.automation.uiAutomation.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.uiAutomation.excelReader.Excel_Reader;
import com.test.automation.uiAutomation.homepage.explicitWait;
import com.test.automation.uiAutomation.listener.Listener;

public class TestBase {

	public static WebDriver driver;
	Excel_Reader excel;
	Listener lis;
	public Properties OR = new Properties();
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest testClass;

	public void loadData() throws IOException {

		File file = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\test\\automation\\uiAutomation\\config\\config.properties");
		FileInputStream f = new FileInputStream(file);
		OR.load(f);
	}

	// To fetch the URL and browser
	public void init() throws IOException {
		loadData();
		extent = new ExtentReports(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\test\\automation\\uiAutomation\\report\\TestHtmlReport.html", false);
	}

	// Select the browser
	public void selectBrowser(String browser) throws IOException {
		loadData();
		if (browser.equalsIgnoreCase("chrome")) {
			// Open the browser
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			// Open the browser
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}

	// To fetch the URL
	public void getUrl(String url) {
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		driver.get(url);
		// Wait
		wait.waitForLoad(driver);
		// Maximize
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	// To get input from excel
	public String[][] getData(String excelName, String sheetName) {
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\test\\automation\\uiAutomation\\data\\"
				+ excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}

	// Capture Screenshot
	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File destFile = null;

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "\\src\\main\\java\\com\\test\\automation\\uiAutomation\\screenshot\\";
			destFile = new File(
					(String) reportDirectory + fileName + "_" + formatter.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// To link the screenshot to TestNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'/> </a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void log(String data) {
		// log.info(data);
		Reporter.log(data);
		test.log(LogStatus.INFO, data);
	}

	// Display Result
	public void getresult(ITestResult result) {
		test = extent.startTest(result.getName());
		testClass.appendChild(test);
		if (result.getStatus() == ITestResult.SUCCESS) {
			// when successful
			test.log(LogStatus.PASS, test.getTest().getName() + "   test is passed");
			String screen = captureScreen("");
			String screenshot = "<a href='" + screen
					+ "' target='_blank' style='font-weight: bold;color:green;text-decoration: underline;'>Passed Test Case Screenshot </a>";
			test.log(LogStatus.PASS, screenshot);
		} else if (result.getStatus() == ITestResult.SKIP) {
			// when skipped
			test.log(LogStatus.SKIP,
					test.getTest().getName() + "   test is skipped and the reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			// when failed
			test.log(LogStatus.ERROR,
					test.getTest().getName() + "   test is failed and the reason is" + result.getThrowable());
			String screen = captureScreen("");
			String screenshot = "<a href='" + screen
					+ "' target='_blank' style='font-weight: bold;color:red;text-decoration: underline;'>Failed Test Case Screenshot </a>";
			test.log(LogStatus.FAIL, screenshot);
		} else if (result.getStatus() == ITestResult.STARTED) {
			// when started
			test.log(LogStatus.INFO, test.getTest().getName() + "   test is started");
		}
	}

	@BeforeMethod()
	public void beforeMethod(ITestResult result) throws IOException {
		selectBrowser(OR.getProperty("browser"));
		// lis = new Listener(driver);
		getUrl(OR.getProperty("url"));

	}

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
		extent.endTest(test);
		driver.quit();

	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	public void closeBrowser() {
		driver.quit();
		extent.endTest(testClass);
		extent.flush();
	}

}
