package com.wk.ileanframework.reporting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.wk.ileanframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ileanframework.controlsLibrary.utilities.PropFileHandler;

import io.appium.java_client.windows.WindowsDriver;

public class Reporting {

	public static ExtentReports reporter;
	public static Map<Long, String> threadToTestCaseMap = new HashMap<Long, String>();
	public static Map<String, ExtentTest> nameToExtentTestMap = new HashMap<String, ExtentTest>();
	static FileInputStream in = null;
	static FileOutputStream out = null;
	public static String reportLocation = System.getProperty("user.dir")+ "./reports/";
	public static String filename = "";
	static String reportName = "/TestReports.html";
	static String errorLogsLoc = "";
	static String configfiles = "./data/extent-config.xml";
	static String sharedFolder = "";
	static String propertyFileName = "";

	static String runID = "";
	private static String srcpath = System.getProperty("user.dir") + "/data/ProvisoImg.png";
	private static String destpath = null;
	
	static String consolidateReportName = "/ConsolidateReport.html";
	// static String workflow = System.getProperty("workflow");
	// static String workflowNumber = System.getProperty("workflowNumber");
	static String workflow = "last";
	static String workflowNumber = "Flow1";

	// private static String reportTracker = System.getProperty("reportTracker");

	/*
	 * 
	 * Create Extent Reports Object:-
	 */
	private synchronized static ExtentReports initiateReporting() {
		if (reporter == null) {

			reporter = new ExtentReports(new File(reportLocation + reportName).getAbsolutePath(), true,
					DisplayOrder.NEWEST_FIRST);
			// reporter.addSystemInfo("SuiteName",
			// System.getProperty("suiteName").split("\\.")[0]);
			reporter.addSystemInfo("SuiteName", "Proviso");
			reporter.loadConfig(new File(configfiles).getAbsoluteFile());

		}
		return reporter;
	}

	/*
	 * 
	 * Initiate Extent test logger for test initialization:-
	 */
	public synchronized static ExtentTest initiateTestLogger(String testName) {

		if (!nameToExtentTestMap.containsKey(testName)) {
			Long threadID = Thread.currentThread().getId();
			ExtentTest test = initiateReporting().startTest(testName);
			nameToExtentTestMap.put(testName, test);
			threadToTestCaseMap.put(threadID, testName);

		}
		return nameToExtentTestMap.get(testName);
	}

	/*
	 * Fetch the Extent Test Logger at runtime for the respective test case:-
	 */
	public synchronized static ExtentTest getLogger(String testName) {
		return initiateTestLogger(testName);
	}

	/*
	 * At any given stage we need to fetch the details of Test Method:-
	 *
	 */
	public synchronized static ExtentTest getLogger() {
		Long threadID = Thread.currentThread().getId();

		if (threadToTestCaseMap.containsKey(threadID)) {
			String testName = threadToTestCaseMap.get(threadID);
			return nameToExtentTestMap.get(testName);
		}
		return null;
	}

	/*
	 * Generate the reporting for the test case:-
	 */
	public synchronized static void generateTestReport(String testName) {

		if (!testName.isEmpty()) {
			ExtentTest test = getLogger(testName);
			initiateReporting().endTest(test);
		}
	}

	/*
	 * Generate reports for the logger:-
	 */
	public synchronized static void generateReport() {
		String prePath = "";

		if (reporter != null) {
			reporter.flush();
			reporter.close();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (workflow.equalsIgnoreCase("last") || workflow.equalsIgnoreCase("inprogress")) {
				Properties prop = new Properties();
				try {
					PropFileHandler propFilePRoject = new PropFileHandler(new Properties());
					if (propFilePRoject.readProperty("config", "ProjectName").equalsIgnoreCase("Proviso")) {
						if (InetAddress.getLocalHost().getHostName().contains("156")
								|| InetAddress.getLocalHost().getHostName().contains("157")
								|| InetAddress.getLocalHost().getHostName().contains("222")) {
						} else {
							in = new FileInputStream(System.getProperty("user.dir") + "//data//config.properties");
							//Properties propObj = getPropertyObject(System.getProperty("user.dir") + "//data//config.properties");
							prop.load(in);
							in.close();
							out = new FileOutputStream(System.getProperty("user.dir") + "//data//config.properties");
							prop.setProperty(workflowNumber, (new File(reportLocation + reportName).getAbsolutePath()));
							prop.store(out, null);
							out.close();
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (workflow.equalsIgnoreCase("NA")) {
				// sendEmail();
			}
		}

	}

	public static String createScreenshot(WebDriver driver) {
		Long threadID = Thread.currentThread().getId();
		String testName = threadToTestCaseMap.get(threadID);

		UUID uuid = UUID.randomUUID();

		// generate screenshot as a file object
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// copy file object to designated location
			FileUtils.copyFile(scrFile, new File(reportLocation + "/" + uuid + testName + ".png"));
			System.out.println("Screenshot Capture: " + reportLocation + "/" + uuid + testName + ".png");
		} catch (IOException e) {
			System.out.println("Error while generating screenshot:\n" + e.toString());
		}
		return uuid + testName + ".png";

	}

	/**
	 * Generates a unique folder based upon our run:-
	 */
	public static void createReportDirectory() {

		runID = new Reporting().generateUniqueRunId();
		File file = new File(reportLocation + runID);
		if (!file.exists()) {
			if (!file.exists()) {
				if (file.mkdir()) {
					reportLocation = reportLocation + runID;
					destpath = reportLocation + "/proviso_logo.gif";
					errorLogsLoc = reportLocation + "/errorLogs";
					if (!errorLogsLoc.isEmpty()) {
						File errorLogs = new File(errorLogsLoc);
						if (!errorLogs.exists()) {
							if (!errorLogs.mkdir()) {

								System.out.print(
										"Unable to create error logs directory for reportlocation: " + reportLocation);
							}
						}
					}
				} else {
					System.out.println("Failed to create directory!");
				}
			}

		}

	}

	private String getDate() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date date = new Date();
			return dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * On each call of this function, it randomly generates a unique run id
	 * (combination of 6 digits).
	 * 
	 * @return String runId
	 */
	private String generateUniqueRunId() {
		String runID = "";
		try {
			// Randomly generates 6 digits unique number.
			Random random = new Random();
			int n = 100000 + random.nextInt(900000);
			runID = String.valueOf(n) + "_" + getDate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return runID.trim();
	}

	/**
	 * Get Error Logs location:-
	 */

	public String getErrorLogsLocation() {
		return this.errorLogsLoc;
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			long count = 0;
			long size = source.size();
			while ((count += destination.transferFrom(source, count, size - count)) < size)
				;
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	/**
	 * 
	 * @param source
	 * @param destination
	 */
	public static void moveFile() {
		try {
			File sourceFile = new File(srcpath);
			File destFile = new File(destpath);
			copyFile(sourceFile, destFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
