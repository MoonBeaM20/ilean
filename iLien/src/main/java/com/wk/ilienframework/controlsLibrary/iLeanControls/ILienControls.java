package com.wk.ilienframework.controlsLibrary.iLeanControls;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilienframework.controlsLibrary.utilities.PropFileHandler;
import com.wk.ilienframework.controlsLibrary.webControls.WebControls;
import com.wk.ilienframework.controlsLibrary.webControls.WebControlsLibrary;
import com.wk.ilienframework.drivers.webDriverFactory.WebDriverFactory;
import com.wk.ilienframework.reporting.Reporting;

public class ILienControls {

	private String browserName = System.getProperty("browser");
	private String deviceName = System.getProperty("device");
	public static String startTestTime = "", endTestTime = "", startTestDate = "", endTestDate = "";
	
	//global variables
	public static String requirementName;
	public static String clusterName;
	public static String binderName;
	public static String contextname;
	public static String searchname;
	
	// private String appType = "web";
	private String appType = System.getProperty("appType");
	public static final String ENVIRONMENT = System.getProperty("env");

	public ThreadLocal<WebControlsLibrary> _webcontrols = new ThreadLocal<WebControlsLibrary>();
	
	
	public static String viewPort="yes";
	
	//protected LoginPage loginPage=new LoginPage(_webcontrols);

	String browserValue;

	/*
	 * private ScreenRecorder screenRecorder; VideoReord videoReord;
	 */
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws IOException {

		PropFileHandler pf = new PropFileHandler(new Properties());
		String projectName = pf.readProperty("config", "ProjectName");
		if (projectName.equalsIgnoreCase("Proviso")) {
				startTestDate = getDate();
				startTestTime = getTime();
				setTestTime("startTestTime", startTestTime);
				setTestTime("startTestDate", startTestDate);
		}
	
		try {
		PropFileHandler propFileHandler = new PropFileHandler(new Properties());
		viewPort=propFileHandler.readProperty("config", "ViewPortElement");
		}catch(Exception e) {
			
		}
		System.out.println("View Port Element from config"+viewPort);
		Reporting.createReportDirectory();
		Reporting.moveFile();
		//Temporary commented and need to look into this
		
	

	}

	/**
	 * 
	 * Initialize webcontrols for every web browser method:-
	 * 
	 * @param methodName
	 */
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method methodName) {

		System.out.println("Method Name >>>> " + methodName.getName());
		System.out.println("appType  :" + appType);

		if (deviceName.contains("\"")) {
			deviceName = deviceName.replace("\"", "");
		}

		
			WebControlsLibrary _webControls = null;

			if (this.appType.equalsIgnoreCase("web")) {
				_webControls = new WebControls(new WebDriverFactory().getDriver(this.browserName, this.deviceName));
				System.out.println(
						"Driver initialization before method: get Remote Webdriver************ + method Name ****"
								+ methodName.getName() + "\nThread id is - " + Thread.currentThread().getId());
				this._webcontrols.set(_webControls);
				((WebControlsLibrary) this._webcontrols.get()).getDriver().manage().window().maximize();
				

				Reporting.getLogger(methodName.getName());

				String browserValue = System.getProperty("browser");
				if ((browserValue.equalsIgnoreCase("chromedevice"))
						&& (!this.deviceName.equalsIgnoreCase("Apple iPad"))) {
					Reporting.getLogger().log(LogStatus.INFO, "<b>Mstore_Test_cases</b>",
							"<b>Script is running for MStore </b>");
				} else if ((browserValue.equalsIgnoreCase("chromedevice"))
						&& (this.deviceName.equalsIgnoreCase("Apple iPad"))) {
					Reporting.getLogger().log(LogStatus.INFO, "<b>Tablet_Test_cases</b>",
							"<b>Script is running for Tablet </b>");
				}

			}

	}

	/**
	 * Quit driver after every test:-
	 * 
	 * @param methodName
	 */

//	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method methodName) {
		System.out.println("After Method >>> " + methodName.getName());
		Reporting.generateTestReport(methodName.getName());
			((WebControlsLibrary) this._webcontrols.get()).quitDriver();
		
		System.out.println("After method >>>  Thread ID >>> " + Thread.currentThread().getId());
	
	}


	/**
	 * Generate report:-
	 * 
	 * @throws IOException
	 */
//	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws IOException {
		PropFileHandler pf = new PropFileHandler(new Properties());
		String projectName = pf.readProperty("config", "ProjectName");
		Reporting.generateReport();
		if (projectName.equalsIgnoreCase("Proviso")) {
				setTestTime("endTestTime", "");
				setTestTime("startTestTime", "");
				setTestTime("endTestDate", "");
				setTestTime("startTestDate", "");
		}
	}

	/**
	 * <strong>setTestTime</strong>-
	 * 
	 * @param propertyKey
	 * @param testTime
	 * @throws IOException
	 * @author Samarendra 
	 */
	public static void setTestTime(String propertyKey, String testTime) throws IOException {
		Properties prop = new Properties();
		FileInputStream in = null;
		FileOutputStream out = null;
		String filePath = "";
		/** If executing in remote machine shared location to store time **/
		if (InetAddress.getLocalHost().getHostName().contains("156")
				|| InetAddress.getLocalHost().getHostName().contains("157")
				|| InetAddress.getLocalHost().getHostName().contains("222"))
			filePath = "\\\\c1w29157\\SharePath\\CategoryData.properties";
		else
			filePath = "//data//CategoryData.properties";

		in = new FileInputStream(System.getProperty("user.dir") + filePath);
		prop.load(in);
		in.close();
		out = new FileOutputStream(System.getProperty("user.dir") + filePath);
		prop.setProperty(propertyKey, testTime);
		prop.store(out, null);
		out.close();
	}

	/**
	 * <strong>getTime</strong>-
	 * 
	 * @return String - Time [hh~mm~ss]
	 * @author Samarendra May 24, 2018
	 */
	public static String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("kk:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * <strong>getDate</strong>-
	 * 
	 * @return String- Date [DD-MM-YYYY]
	 * @author Samarendra 
	 */
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
