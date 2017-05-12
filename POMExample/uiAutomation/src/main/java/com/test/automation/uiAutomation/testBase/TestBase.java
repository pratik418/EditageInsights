package com.test.automation.uiAutomation.testBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
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

	public void loadData() throws IOException {

		File file = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\test\\automation\\uiAutomation\\config\\config.properties");
		FileInputStream f = new FileInputStream(file);
		OR.load(f);
	}

	// To fetch the URL and browser
	public void init() throws IOException {
		loadData();
		selectBrowser(OR.getProperty("browser"));
		// lis = new Listener(driver);
		getUrl(OR.getProperty("url"));
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
	
	
	public static void updateResult(int indexSI, String testCaseName, String testCaseStatus, String scriptName)
			throws IOException {

		String startDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());

		String userDirector = System.getProperty("user.dir");

		String resultFile = userDirector
				+ "\\src\\main\\java\\com\\test\\automation\\uiAutomation\\report\\TestHtmlReport.html";

		File file = new File(resultFile);
		System.out.println(file.exists());

		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>" + "\n");
			bw.write("<head><title>" + "Test execution report" + "</title>" + "\n");
			bw.write("</head>" + "\n");
			bw.write("<body>");
			bw.write("<font face='Tahoma'size='2'>" + "\n");
			bw.write("<u><h1 align='center'>" + "Test execution report" + "</h1></u>" + "\n");
			bw.flush();
			bw.close();
		}
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
		if (indexSI == 1) {
			bw1.write("<table align='center' border='0' width='70%' height='10'>");
			bw1.write("<tr><td width='70%' </td></tr>");
			bw1.write("<table align='center' border='1' width='70%' height='47'>");
			bw1.write("<tr>");
			bw1.write(
					"<td colspan='2' align='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF'' face='Tahoma' size='2'>"
							+ scriptName + " </font></b></td>");
			bw1.write(
					"<td colspan='1' align='left'><b><font color='#000000' face='Tahoma' size='1'>Start Time :&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='1'>"
							+ startDateTime + " </font></td>");
			bw1.write("</tr>");
			bw1.write("</tr>");
			bw1.write(
					"<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
			bw1.write(
					"<td  bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Test case ID : Test case Description </font></b></td>");

			bw1.write(
					"<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Result </font></b></td>");
			bw1.write("</tr>");
		}
		bw1.write("<tr>" + "\n");
		bw1.write("<td bgcolor='#FFFFDC'align='Center'><font color='#000000' face='Tahoma' size='2'>" + indexSI
				+ "</font></td>" + "\n");
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>"
				+ testCaseName + "</font></b></td>" + "\n");
		if (testCaseStatus == "Pass") {
			bw1.write(
					"<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='Green' face='Tahoma' size='2'>"
							+ testCaseStatus + "</font></b></td>" + "\n");
		} else {
			bw1.write(
					"<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='red' face='Tahoma' size='2'>"
							+ testCaseStatus + "</font></b></td>" + "\n");
		}
		bw1.write("</tr>" + "\n");
		bw1.write("</body>" + "\n");
		bw1.write("</html>");
		bw1.flush();
		bw1.close();

	}

}
