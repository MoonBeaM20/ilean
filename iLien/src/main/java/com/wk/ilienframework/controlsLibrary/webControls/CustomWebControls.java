package com.wk.ilienframework.controlsLibrary.webControls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilienframework.reporting.Logger;
import com.wk.ilienframework.reporting.Reporting;

public class CustomWebControls {
	
	private WebDriver driver;
	
	private long defaultExplicitWaitTimeout;
	
	private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomWebControls.class.getName());

	public CustomWebControls(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public long getDefaultExplicitWaitTimeout() {
		return defaultExplicitWaitTimeout;
	}

	public void setDefaultExplicitWaitTimeout(long defaultExplicitWaitTimeout) {
		this.defaultExplicitWaitTimeout = defaultExplicitWaitTimeout;
	}
	
	public boolean clearAndSendKeys(String stepDescription, String locatorString, String value) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			return clearAndSendKeys(stepDescription, getLocators(locatorString).get(0), value);
		}
		return false;
	}
	
	public boolean clearAndSendKeys(String stepDescription, By by, String value) {
		if(waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitTimeout)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return clearAndSendKeys(stepDescription, webElement, value);
			}
		}
		return false;
	}
	
	public boolean clearAndSendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitTimeout)) {
			try {
				webElement.clear();
				webElement.sendKeys(value);
				report(LogStatus.PASS, stepDescription, "Send keys successful");
				return true;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while sending keys to element : %s", webElement), e);
			}
		}
		return false;
	}
	
	public boolean sendKeys(String stepDescription, String locatorString, String value) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			return sendKeys(stepDescription, getLocators(locatorString).get(0), value);
		}
		return false;
	}
	
	public boolean sendKeys(String stepDescription, By by, String value) {
		if(waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitTimeout)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return sendKeys(stepDescription, webElement, value);
			}
		}
		return false;
	}
	
	public boolean sendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitTimeout)) {
			try {
				webElement.sendKeys(value);
				report(LogStatus.PASS, stepDescription, "Send keys successful");
				return true;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while sending keys to element : %s", webElement), e);
			}
		}
		return false;
	}
	
	public boolean click(String stepDescription, String locatorString) {
		if(waitForClickabilityOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			return click(stepDescription, getLocators(locatorString).get(0));
		}
		return false;
	}
	
	public boolean click(String stepDescription, By by) {
		if(waitForClickabilityOfElement(stepDescription, by, defaultExplicitWaitTimeout)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return click(stepDescription, webElement);
			}
		}
		return false;
	}
	
	public boolean click(String stepDescription, WebElement webElement) {
		if(webElement != null && waitForClickabilityOfElement(stepDescription, webElement, defaultExplicitWaitTimeout)) {
			try {
				webElement.click();
				report(LogStatus.PASS, stepDescription, "Element click successful");
				return true;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while clicking element : %s", webElement), e);
			}
		}
		return false;
	}
	
	public boolean clickUsingActions(String stepDescription, String locatorString) {
		if(waitForClickabilityOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			return clickUsingActions(stepDescription, getLocators(locatorString).get(0));
		}
		return false;
	}
	
	public boolean clickUsingActions(String stepDescription, By by) {
		if(waitForClickabilityOfElement(stepDescription, by, defaultExplicitWaitTimeout)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return clickUsingActions(stepDescription, webElement);
			}
		}
		return false;
	}
	
	public boolean clickUsingActions(String stepDescription, WebElement webElement) {
		if(webElement != null && waitForClickabilityOfElement(stepDescription, webElement, defaultExplicitWaitTimeout)) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(webElement).click().build().perform();
				report(LogStatus.PASS, stepDescription, "Element click successful");
				return true;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while clicking element : %s", webElement), e);
			}
		}
		return false;
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, String locatorString) {
		if(waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			return clickUsingJSExecutor(stepDescription, getLocators(locatorString).get(0));
		}
		return false;
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, By by) {
		if(waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitTimeout)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return clickUsingJSExecutor(stepDescription, webElement);
			}
		}
		return false;
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].click();", webElement);
				report(LogStatus.PASS, stepDescription, "Element click successful");
				return true;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while clicking element : %s", webElement), e);
			}
		}
		return false;
	}
	
	public boolean selectByVisibleText(String stepDescription, String locatorString, String visibleText) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			return selectByVisibleText(stepDescription, getLocators(locatorString).get(0), visibleText);
		}
		return false;
	}
	
	public boolean selectByVisibleText(String stepDescription, By by, String visibleText) {
		if(waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitTimeout)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return selectByVisibleText(stepDescription, webElement, visibleText);
			}
		}
		return false;
	}
	
	public boolean selectByVisibleText(String stepDescription, WebElement webElement, String visibleText) {
		if(webElement != null && waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitTimeout)) {
			try {
				Select select = new Select(webElement);
				select.selectByVisibleText(visibleText);
				report(LogStatus.PASS, stepDescription, "Selection by visible text successful");
				return true;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, "Exception occured while selecting by visible text", e);
			}
		}
		return false;
	}
	
	public boolean selectByValue(String stepDescription, String locatorString, String value) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			return selectByValue(stepDescription, getLocators(locatorString).get(0), value);
		}
		return false;
	}
	
	public boolean selectByValue(String stepDescription, By by, String value) {
		if(waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitTimeout)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return selectByValue(stepDescription, webElement, value);
			}
		}
		return false;
	}
	
	public boolean selectByValue(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitTimeout)) {
			try {
				Select select = new Select(webElement);
				select.selectByValue(value);
				report(LogStatus.PASS, stepDescription, "Selection by visible text successful");
				return true;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, "Exception occured while selecting by visible text", e);
			}
		}
		return false;
	}
	
	public boolean waitForClickabilityOfElement(String stepDescription, String locatorString, long timeout) {
		return getLocators(locatorString).stream()
				.allMatch(locator -> tryToWaitForClickabilityOfElement(stepDescription, locator, timeout));
	}
	
	private boolean tryToWaitForClickabilityOfElement(String stepDescription, By by, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.elementToBeClickable(by)) != null;
		}catch(Exception e) {
			logger.log(Level.WARNING, "exception {0} while waiting for clickability of element by : {1}", new Object[] {e.toString(), by.toString()});
		}
		return false;
	}
	
	public boolean waitForClickabilityOfElement(String stepDescription, By by, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.elementToBeClickable(by)) != null;
		}catch(Exception e) {
			reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while waiting for clickability of element : %s", by), e);
		}
		return false;
	}
	
	public boolean waitForClickabilityOfElement(String stepDescription, WebElement webElement, long timeout) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				return wait.until(ExpectedConditions.elementToBeClickable(webElement)) != null;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while waiting for clickability of element : %s", webElement), e);
			}
		}
		return false;
	}
	
	public boolean waitForPresenceOfElement(String stepDescription, String locatorString, long timeout) {
		return getLocators(locatorString).stream()
				.allMatch(locator -> tryToWaitForPresenceOfElements(stepDescription, locator, timeout));
	}
	
	private boolean tryToWaitForPresenceOfElements(String stepDescription, By by, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)) != null;
		}catch(Exception e) {
			logger.log(Level.WARNING, "exception {0} while waiting for presenece of element by : {1}", new Object[] {e.toString(), by.toString()});
		}
		return false;
	}
	
	public boolean waitForPresenceOfElement(String stepDescription, By by, long timeout) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				return wait.until(ExpectedConditions.presenceOfElementLocated(by)) != null;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while waiting for presence of element by : %s", by), e);
			}
		}
		return false;
	}
	
	public boolean waitForVisibilityOfElement(String stepDescription, String locatorString, long timeout) {
		return getLocators(locatorString).stream()
				.allMatch(locator -> tryToWaitForVisibilityOfElement(stepDescription, locator, timeout));
	}
	
	private boolean tryToWaitForVisibilityOfElement(String stepDescription, By by, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by)) != null;
		}catch(Exception e) {
			logger.log(Level.WARNING, "exception {0} while waiting for visibility of element by : {1}", new Object[] {e.toString(), by.toString()});
		}
		return false;
	}
	
	public boolean waitForVisibilityOfElement(String stepDescription, By by, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by)) != null;
		}catch(Exception e) {
			reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while waiting for visibility of element by : %s", by), e);
		}
		return false;
	}
	
	public boolean waitForVisibilityOfElement(String stepDescription, WebElement webElement, long timeout) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				return wait.until(ExpectedConditions.visibilityOf(webElement)) != null;
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while waiting for visibility of element : %s", webElement), e);
			}
		}
		return false;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, String locatorString, long timeout) {
		return getLocators(locatorString).stream()
				.allMatch(locator -> tryToWaitForInvisibilityOfElement(stepDescription, locator, timeout));
	}
	
	private boolean tryToWaitForInvisibilityOfElement(String stepDescription, By by, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}catch(Exception e) {
			logger.log(Level.WARNING, "exception {0} while waiting for invisibility of element by : {1}", new Object[] {e.toString(), by.toString()});
		}
		return false;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, By by, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}catch(Exception e) {
			reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while waiting for invisibility of element by : %s", by), e);
		}
		return false;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, WebElement webElement, long timeout) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				return wait.until(ExpectedConditions.invisibilityOf(webElement));
			}catch(Exception e) {
				reportException(LogStatus.FAIL, stepDescription, String.format("Exception occured while waiting for invisibility of element : %s", webElement), e);
			}
		}
		return false;
	}
	
	public boolean scrollToElement(String stepDescription, String locatorString, String scrollOptions) {
		if(waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			return scrollToElement(stepDescription, getLocators(locatorString).get(0), scrollOptions);
		}
		return false;
	}
	
	public boolean scrollToElement(String stepDescription, By by, String scrollOptions) {
		if(by != null && waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitTimeout)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				try {
					JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
					String script = String.format("arguments[0].scrollIntoView(%s);", scrollOptions);
					javascriptExecutor.executeScript(script, webElement);
					return true;
				}catch(Exception e) {
					reportException(LogStatus.FAIL, stepDescription, "Exception occured while scrolling", e);
				}
			}
		}
		return false;
	}
	
	public void acceptAlertIfPresent(String stepDescription) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if(alert != null) {
				alert.accept();
			}
		}catch(Exception e) {
			logger.log(Level.WARNING, "exception {0} while interacting with alert", new Object[] {e.toString()});
			e.printStackTrace();
		}
	}
	
	public void switchToIFrame(String stepDescription, String locatorString) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitTimeout)) {
			try {
				driver.switchTo().frame(driver.findElement(getLocators(locatorString).get(0)));
			}catch(Exception e) {
				logger.log(Level.WARNING, "exception {0} while switching to iframe", new Object[] {e.toString()});
				e.printStackTrace();
			}
		}
	}
	
	public void switchToDefaultContent(String stepDescription) {
		try {
			driver.switchTo().defaultContent();
		}catch(Exception e) {
			logger.log(Level.WARNING, "exception {0} while switching to default content", new Object[] {e.toString()});
			e.printStackTrace();
		}
	}
	
	public WebElement findElement(String locatorString) {
		List<WebElement> webElements = findElements(locatorString);
		return webElements.size() > 0 ? webElements.get(0) : null;
	}
	
	public WebElement findElement(By by) {
		WebElement webElement = null;
		try {
			webElement = driver.findElement(by);
		}catch(Exception e) {
			reportException(LogStatus.FAIL, null, String.format("Exception occured while locating element by : %s", by.toString()), e);
		}
		return webElement;
	}
	
	public List<WebElement> findElements(String locatorString) {
		List<WebElement> webElements = new ArrayList<>();
		List<By> locators = getLocators(locatorString);
		if(locators.size() > 0) {
			try {
				webElements = locators.stream()
						.map(locator -> tryFindingElements(locator))
						.filter(webElementList -> webElementList.size() > 0)
						.findFirst()
						.get();
			}catch(Exception e) {
				String message = String.format("No elements found by locator string : %s", locatorString)
						+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(driver));
				report(LogStatus.FAIL, null, message);
			}
		}
		return webElements;
	}
	
	public List<WebElement> findElements(By by) {
		List<WebElement> webElements = new ArrayList<>();
		try {
			webElements = driver.findElements(by);
		}catch(Exception e) {
			reportException(LogStatus.FAIL, null, String.format("Exception occured while locating elements by : %s", by.toString()), e);
		}
		return webElements;
	}
	
	private List<WebElement> tryFindingElements(By by) {
		List<WebElement> webElements = new ArrayList<>();
		try {
			webElements = driver.findElements(by);
		}catch(Exception e) {
			logger.log(Level.WARNING, "exception {0} while finding elements by : {1}", new Object[] {e.toString(), by.toString()});
		}
		return webElements;
	}
	
	private List<By> getLocators(String locatorString) {
		List<String> locatorPairStrings = Arrays.asList(locatorString.split(";"));
		List<List<String>> locatorPairs = locatorPairStrings.stream()
				.map(locatorPairString -> extractLocatorPair(locatorPairString))
				.filter(locatorPairList -> locatorPairList.size() > 0)
				.collect(Collectors.toList());
		List<By> locators = locatorPairs.stream()
				.map(locatorPair -> getLocator(locatorPair.get(0), locatorPair.get(1)))
				.filter(locator -> locator != null)
				.collect(Collectors.toList());
		if(locators.size() == 0) {
			logger.log(Level.WARNING, "No locators found using locator string : {0}", new Object[] {locatorString});
		}
		return locators;
	}
	
	private List<String> extractLocatorPair(String locatorPairString) {
		try {
//			return List.of(locatorPairString.split("~")[0], locatorPairString.split("~")[1]);
			List<String> locatorPair = new ArrayList<>();
			locatorPair.add(locatorPairString.split("~")[0]);
			locatorPair.add(locatorPairString.split("~")[1]);
			return locatorPair;
		}catch (Exception e) {
			logger.log(Level.WARNING, "exception {0} while finding elements by : {1}", new Object[] {e.toString(), locatorPairString});
		}
		return new ArrayList<>();
	}
	
	private By getLocator(String locatorKey, String locatorValue) {
		By byLocator = null;
		switch (locatorKey) {
		case "xpath":
			byLocator = By.xpath(locatorValue);
			break;
		case "classname":
			byLocator = By.className(locatorValue);
			break;
		case "name":
			byLocator = By.name(locatorValue);
			break;
		case "id":
			byLocator = By.id(locatorValue);
			break;
		case "css":
			byLocator = By.cssSelector(locatorValue);
			break;
		case "tagname":
			byLocator = By.tagName(locatorValue);
			break;
		case "linktext":
			byLocator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			byLocator = By.linkText(locatorValue);
			break;
		default:
			logger.log(Level.WARNING, "invalid locator key : {0}", new Object[] {locatorKey});
			break;
		}
		return byLocator;
	}
	
	public void waitForPageLoad(String stepDescription) {
		try {
			stepDescription = StringUtils.isBlank(stepDescription) ? "wait for page load" : stepDescription;
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			javascriptExecutor.executeScript("return document.readyState").equals("complete");
		}catch(Exception e) {
			reportException(LogStatus.FAIL, stepDescription, "Exception occured while waiting for page load", e);
			e.printStackTrace();
		}
	}
	
	public boolean waitForLoadingToComplete() {
		return waitForInvisibilityOfElement("Wait for invisibility of loading overlay", "xpath~//div[@id='overlay']", defaultExplicitWaitTimeout);
	}
	
	public void report(LogStatus logStatus, String stepDescription, String message) {
		if(StringUtils.isNotBlank(stepDescription)) {
			Reporting.getLogger().log(logStatus, stepDescription, message);
		}else {
			Reporting.getLogger().log(logStatus, message);
		}
	}
	
	public void reportException(LogStatus logStatus, String stepDescription, String message, Exception e) {
		String details = "";
		if(StringUtils.isNotBlank(stepDescription)) {
			message = "Exception occured: " + stepDescription;
			details = "For details refer "
					+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
					+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(driver));
		}else {
			details = "For details refer "
					+ new Logger().writeExceptionLogs(message, getExceptionDetails(e))
					+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(driver));
		}
		
		Reporting.getLogger().log(logStatus, message, details);
	}
	
	private String getExceptionDetails(Exception e) {

		String stackTrace = "";
		for (StackTraceElement str : e.getStackTrace()) {
			stackTrace = stackTrace + str.toString() + "\n";
		}
		return e.fillInStackTrace().toString() + stackTrace;
	}
	
}
