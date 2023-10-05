package com.wk.ilien.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ilienframework.controlsLibrary.webControls.WebControlsLibrary;
import com.wk.ilienframework.reporting.Logger;
import com.wk.ilienframework.reporting.Reporting;

/**
 * Generic methods which can be used across all pages
 */

public class PageGenerics extends ILeanControls {

	public ThreadLocal<WebControlsLibrary> _webcontrols;
	private String pageName = "Page Generics";
	protected static Properties pageGenericsOR = null;
	public static int timeOut = 0;
	private static String testDataFile = null;
	public static Map<String, ArrayList<String>> testData = null;
	public String pageNameForExecutionReport = "Page Generics";
	static String firstMapKey = "";
	static String secondMapValue = "";
	static String firstMapValue = "";
	private static UtilLib utilLib = new UtilLib();

	public PageGenerics() {
		if (pageGenericsOR == null) {
			pageGenericsOR = UtilLib.setPropertyObject("PageGenerics");
		}
		if (timeOut == 0) {
			timeOut = UtilLib.getTimeoutVal();
		}
		/*
		 * if (testData == null) { testDataFile =
		 * UtilLib.getTestDataFileNameFromConfig() + ".xlsx"; testData =
		 * UtilLib.readTestDataSheet("sanity", testDataFile, "Proviso_POC"); }
		 */
	}

	/**
	 * Gets test data
	 * 
	 * @return Map Key is string and value is arrayList
	 * @author Samar
	 */
	public Map<String, ArrayList<String>> getTestData() {
		return testData;
	}

	/**
	 * To send pass/fail/exception detail to report based on log status
	 * 
	 * @param status
	 * @param stepDescription
	 * @param expectedResults
	 * @param pageName        To print page name in report
	 * @param logUrl          To capture url based on boolean value
	 * @param isScreenshot    To capture screenshot based on boolean value (true or
	 *                        false)
	 * @param exception
	 * @return void
	 * 
	 */
	public void reporter(LogStatus status, String stepDescription, String expectedResults, String pageName,
			boolean logUrl, boolean isScreenshot, Exception exception) {
		String details = expectedResults;
		if (status != LogStatus.PASS) {
			details = details + "<br/><b> Page  Name : </b> " + pageNameForExecutionReport;
		}
		if (logUrl) {
			details = details + "</br><b> Current URL   :     </b>" + _webcontrols.get().getCurrentUrl(" Current URL");
		}
		if (exception != null) {
			details = details + new Logger().writeExceptionLogs(pageName + ":" + expectedResults,
					_webcontrols.get().getExceptionDetails(exception));
		}
		if (isScreenshot) {
			details = details + Reporting.getLogger()
					.addScreenCapture(Reporting.createScreenshot(_webcontrols.get().getDriver()));
		}
		Reporting.getLogger().log(status, stepDescription, details);
	}

	/**
	 * Sends pass information to report
	 * 
	 * @param stepDescription
	 * @param expectedResults
	 * @param pageName        To print page name in report
	 * @return void
	 */
	public void reportPass(String stepDescription, String expectedResults, String pageName) {
		reporter(LogStatus.PASS, stepDescription, expectedResults, pageName, false, false, null);
	}

	/**
	 * Sends fail information to report
	 * 
	 * @param stepDescription
	 * @param expectedResults
	 * @param pageName        To print page name in report
	 * @return void
	 */
	public void reportFail(String stepDescription, String expectedResults, String pageName) {
		reporter(LogStatus.FAIL, stepDescription, expectedResults, pageName, true, true, null);
	}

	/**
	 * Sends warning information to report
	 * 
	 * @param stepDescription
	 * @param expectedResults
	 * @param pageName
	 * @return void
	 */
	public void reportWarning(String stepDescription, String expectedResults, String pageName) {
		reporter(LogStatus.WARNING, stepDescription, expectedResults, pageName, false, false, null);
	}

	/**
	 * Sends test case information to report
	 * 
	 * @param stepDescription
	 * @param expectedResults
	 * @return void
	 */
	public void reportInfo(String stepDescription, String expectedResults) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>" + stepDescription + "</b>", "<b>" + expectedResults + "</b>");
	}

	/**
	 * Sends exception information to report
	 * 
	 * @param stepDescription
	 * @param expectedResults
	 * @param pageName        To print page name in report
	 * @param e               Exception
	 * @return void
	 */
	public void reportException(String stepDescription, String expectedResults, String pageName, Exception e) {
		reporter(LogStatus.FAIL, stepDescription, expectedResults, pageName, true, true, e);
	}

	/**
	 * Verifies home page is displayed
	 * 
	 * @param stepDescription
	 * @param expPageName.
	 * @param expectedResult
	 * @return boolean
	 */
	public boolean verifyHomePageIsDisplayed(String stepDescription, String expPageName, String expectedResult) {
		boolean flag = false;
		try {
			String eleXpth = pageGenericsOR.getProperty("lvwSelection");
			if (_webcontrols.get().waitUntilElementIsVisible(stepDescription, eleXpth, timeOut, false)) {
				String actPageName = _webcontrols.get().getText("Getting text of " + pageName, eleXpth);
				if (expPageName.equals(actPageName)) {
					reportPass(stepDescription, "<b>" + expectedResult + "</b>", pageName);
					flag = true;
				} else {
					reportFail(stepDescription, "Expected page name is <b>" + expPageName
							+ "</b> but actual page name is <b>" + actPageName + "</b>", "");
				}
			} else {
				reportFail(stepDescription, "<b>" + expPageName + "</b> page is not opened", pageName);
			}
		} catch (Exception e) {
			reportException(stepDescription, "Exception occured while verifying page " + expPageName, pageName, e);
		}
		return flag;
	}

	/**
	 * Compares data between two maps
	 * 
	 * @param stepDescription
	 * @param expectedResult
	 * @param firstMap        Data from parent case
	 * @param secondMap       Data from child case
	 * @return String
	 */
	public String compareDataBetweenTwoMaps(String stepDescription, String expectedResult,
			HashMap<String, String> firstMap, HashMap<String, String> secondMap) {
		String value = "";
		String notMatches = "";
		try {
			Set<Entry<String, String>> mapOne = firstMap.entrySet();
			for (Entry<String, String> map : mapOne) {
				firstMapKey = map.getKey();
				firstMapValue = map.getValue();
				if (secondMap.containsKey(firstMapKey)) {
					secondMapValue = secondMap.get(firstMapKey);
					if (!secondMapValue.equals(firstMapValue)) {
						notMatches = notMatches + " <br> Expected value of " + firstMapKey + " is " + firstMapValue
								+ " but actual value is " + secondMapValue;
					}
				}
			}
			if (notMatches.isEmpty()) {
				value = utilLib.getHashMapAsString(firstMap, ":");
				reportPass(stepDescription, expectedResult + ":-" + value, "");
			} else {
				reportFail(stepDescription, "User Verified, below fields are not populated " + notMatches, "");
				return value;
			}
		} catch (Exception e) {
			reportException(stepDescription, "Exception occured while comparing Data Between Two Maps", "", e);
		}
		return value;
	}

	/**
	 * Moves to element by using actions class
	 * 
	 * @param locators Xpath of element
	 * @return void
	 */
	public void moveToElementByActionBuilder(String locators) {
		try {
			Actions builder = new Actions(_webcontrols.get().getDriver());
			builder.moveToElement(_webcontrols.get().getDriver().findElement(By.xpath(locators.replace("xpath~", ""))))
					.build().perform();
		} catch (Exception e) {
			// no need
		}
	}

	/**
	 * Switchs to last window
	 * 
	 * @param stepDescription
	 * @return void
	 */
	public Boolean switchToLastWindow(String stepDescription) {
		boolean flag = false;
		List<String> aList = new ArrayList<String>();
		try {
			Set<String> allWH = _webcontrols.get().getDriver().getWindowHandles();
			aList.addAll(allWH);
			if (aList.size() > 1) {
				_webcontrols.get().getDriver().switchTo().window(aList.get(aList.size() - 1));
				_webcontrols.get().getDriver().manage().window().maximize();
				flag = true;
			} else {
				reportFail(stepDescription, "single window is available", pageName);
			}

		} catch (Exception e) {
			reportException(stepDescription, "Exception occured while switching to last window", pageName, e);
		}
		return flag;
	}

	/**
	 * Clicks frame is available and switch to Frame
	 * 
	 * @param stepDescription
	 * @param frameName
	 * @return void
	 */
	public void checkFrameIsAvailableAndswitchToframe(String stepDescription, String frameName) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(_webcontrols.get().getDriver(), timeOut);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
					_webcontrols.get().getDriver().findElement(By.xpath(frameName.replace("xpath~", "")))));
		} catch (Exception e) {
			reportException(stepDescription, "Exception occured while switching to frame", pageName, e);
		}
	}

	public boolean waitForApplicationToBeIdle() {
		boolean appIdle = false;
		StopWatch idleTimer = new StopWatch();
		;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) _webcontrols.get().getDriver();
			long idleTimeOut = 90000;
			idleTimer.start();
			boolean docReadyState = new WebDriverWait(_webcontrols.get().getDriver(), 90)
					.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
							.equals("complete"));
			while (!appIdle) {
				idleTimer.split();
				if (idleTimer.getSplitTime() > idleTimeOut) {
					break;
				} else {
					appIdle = (boolean) jse.executeScript("return window.UCWorkBlockTracker.isAppIdle();");
				}
			}
		} catch (Exception e) {
			reportException("Exception occured  while waiting for application to be idle",
					"Exception occured  while waiting for application to be idle", pageName, e);
			appIdle = false;
		}
		idleTimer.stop();
		return appIdle;
	}

	/**
	 * Switches to particular
	 * 
	 * @param stepDescription
	 * @param windowtitle
	 * @return boolean
	 */
	public boolean switchToParticularWindow(String stepDescription, String windowtitle) {
		boolean execute = true;
		boolean flag = false;
		String title = "";
		try {
			StopWatch waitTimer = new StopWatch();
			waitTimer.start();
			while (execute) {
				Set<String> allWH = _webcontrols.get().getAllWindowHandles("get All Window Handles");
				for (String windowHandle : allWH) {
					_webcontrols.get().switchToWindow("switch to window", windowHandle);
					waitForApplicationToBeIdle();
					title = _webcontrols.get().getPageTitle("page title");
					if (title.contains(windowtitle)) {
						_webcontrols.get().getDriver().manage().window().maximize();
						flag = true;
						execute = false;
						break;
					}
				}
				waitTimer.split();
				if (waitTimer.getSplitTime() > 90000) {
					execute = false;
					break;
				}
			}
		} catch (Exception e) {
			reportException(stepDescription, "Exception occured while switching to particular window " + title, "", e);
		}
		return flag;
	}

	public void reportPass(String stepDescription, String expectedResults, String pageName, boolean screenCapture) {
		reporter(LogStatus.PASS, stepDescription, expectedResults, pageName, true, true, null);
	}

	public boolean verifyButtonExist(String stepDescription, String buttonName, boolean frame, String frameId,
			boolean isPrint) {
		boolean flag = false;
		try {
			_webcontrols.get().deselectIframe("Exit Frame");
			if (frame) {
				_webcontrols.get().getDriver().switchTo().frame(frameId);
			} // else block is not required
			String propVal = pageGenericsOR.getProperty("btnTextName");
			String xpathButton = propVal.replace("buttonName", buttonName);
			if (_webcontrols.get().waitUntilElementIsVisible("Wait for " + buttonName, xpathButton, 4 * timeOut,
					false)) {
				flag = true;
				if (isPrint) {
					reportPass(stepDescription, buttonName + " is available.", pageName);
				}
			} else {
				reportFail(stepDescription, buttonName + " button is not available.", pageName);
			}
		} catch (Exception e) {
			reportException(stepDescription, "Exception occured  while veifying " + buttonName + " is exist or not.",
					pageName, e);
		}
		return flag;
	}
	
	/**
	 * Clears text field value
	 * 
	 * @param locator
	 * @return boolean
	 * @author Samarendra
	 */
	public boolean clearTextFieldByControlA(String locator) {
		boolean flg = false;
		try {
			WebElement ele = _webcontrols.get().getDriver().findElement(By.xpath(locator.replace("xpath~", "")));
			Actions act1 = new Actions(_webcontrols.get().getDriver());
			act1.sendKeys(ele, Keys.CONTROL).sendKeys("a").sendKeys(Keys.DELETE).keyUp(Keys.CONTROL).build().perform();
		} catch (Exception e) {
			reportException("Exception occured while clearing text field value",
					"Exception occured while clearing text field value", pageName, e);
		}
		return flg;
	}

	/**
	 * Scroll to element
	 * 
	 * @param locator
	 * @author samarendra
	 * @since Sep 28, 2020 11:49:29 AM
	 */
	public Object scrollElementIntoView(String locator) {
		return ((JavascriptExecutor) _webcontrols.get().getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				_webcontrols.get().getDriver().findElement(By.xpath(locator.replace("xpath~", ""))));
	}

	public void fileUpload(String fileName) throws IOException {
		String userDir = System.getProperty("user.dir");
		String fileSeparator = File.separator;
		String filePath = userDir + fileSeparator + "FilesUploadAndDownload" + fileSeparator + "UploadFiles"
				+ fileSeparator + fileName;
		System.out.println(filePath);
		Runtime.getRuntime().exec(userDir + fileSeparator + "AutoIT" + fileSeparator + "FileUpload.exe " + filePath);
	}

}