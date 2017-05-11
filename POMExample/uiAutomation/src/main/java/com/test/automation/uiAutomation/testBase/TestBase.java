package com.test.automation.uiAutomation.testBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.test.automation.uiAutomation.excelReader.Excel_Reader;
import com.test.automation.uiAutomation.homepage.explicitWait;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public WebDriver driver;
	String url = "http://ei.editage.com/insights/";
	String browser = "chrome";
	Excel_Reader excel;

	// To fetch the URL and browser
	public void init() {
		selectBrowser(browser);
		getUrl(url);
		String log4jConfpath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfpath);
	}

	// Select the browser
	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			// Open the browser
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			log.info("creating object of" + browser);
			driver = new ChromeDriver();
		}
	}

	// To fetch the URL
	public void getUrl(String url) {
		log.info("navigating to:" + url);
		explicitWait wait = PageFactory.initElements(driver, explicitWait.class);
		driver.get(url);
		// Wait
		wait.waitForLoad(driver);
		// Maximize
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public String[][] getData(String excelName, String sheetName){		
		String path = System.getProperty("user.dir") +"\\src\\main\\java\\com\\test\\automation\\uiAutomation\\data\\" +excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}
	
	//Capture Screenshot
	public void getScreenShot(String name){
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try{
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "\\src\\main\\java\\com\\test\\automation\\uiAutomation\\screenshot\\";
			File destFile = new File((String) reportDirectory + name + "_" + formatter.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			//To link the screenshot to TestNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() +"'> <img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/> </a>");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
