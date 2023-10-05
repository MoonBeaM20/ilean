package com.wk.ileanframework.controlsLibrary.webControls;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import com.relevantcodes.extentreports.LogStatus;
import com.wk.ileanframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ileanframework.controlsLibrary.utilities.PropFileHandler;
import com.wk.ileanframework.reporting.Logger;
import com.wk.ileanframework.reporting.Reporting;

public class WebControls extends WebControlsLibrary {

	// Initialize driver:-
	private WebDriver driver = null;

	public WebControls(WebDriver driver) {

		this.driver = driver;
		// resetImplicitWaitToDefault();
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * Launch URL in browser:
	 * 
	 * @param StepDescription
	 * @param url
	 * @return true/false
	 */
	@Override
	public void launchUrl(String stepDescription, String url) {
		// TODO Auto-generated method stub
		try {

			getDriver().get(url);
			waitForPageLoad(stepDescription);

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger().log(LogStatus.INFO, stepDescription, "Successfully launched URL: " + url);

		} catch (TimeoutException tx) {
		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else

				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while launching URL",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while launching URL",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

	}

	/**
	 * Enter text in a TextBox:
	 * 
	 * @param StepDescription
	 * @param properties
	 * @param textToEnter
	 * @return true/false
	 */

	@Override
	public boolean enterText(String stepDescription, String properties, String textToEnter) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					element.clear();
					element.sendKeys(textToEnter);
					if (!(stepDescription == null || stepDescription.isEmpty()))
						Reporting.getLogger().log(LogStatus.PASS, stepDescription, "Successfully entered text");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Unable to enter text: " + textToEnter
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Enter text on a page",
							"Unable to enter text on a page, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FATAL, "Exception occured while entering text of page:",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while entering text of page:",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return bFlag;

	}

	/**
	 * Clear text box:-
	 * 
	 * @param StepDescription
	 * @param properties
	 * @return true/false
	 * 
	 */
	@Override
	public boolean clearTextBox(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {

			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					element.clear();
					// waitForPageLoad();
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription, "Successfully cleared the textBox ");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to clear the textBox, For details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Clear the textBox :: ",
							"Unable to clear the textBox, For details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while clearing the textBox",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while clearing the textBox",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return bFlag;
	}

	/**
	 * Click on element:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public boolean click(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				WebElement ele = verifyElementClickable(element);
				if (ele != null) {
					ele.click();
					// waitForPageLoad();
					bFlag = true;
					if (!(stepDescription == null || stepDescription.isEmpty()))
						Reporting.getLogger().log(LogStatus.PASS, stepDescription, "Element clicked successfully ");
					break;
				}

			}
			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Unable to Click : " + stepDescription
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "click element on a page",
							"Unable to click element on a page, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException e) {
		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while clicking the element",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while clicking the element",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return bFlag;
	}

	/**
	 * Double click on element:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public boolean doubleClick(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				WebElement ele = verifyElementClickable(element);
				if (ele != null) {

					Actions action = new Actions(getDriver());
					action.doubleClick(ele).build().perform();
					// waitForPageLoad();
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription,
					// "Able to double click element successfully");
					bFlag = true;
					break;
				}

			}
			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to double click : " + stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "double click element",
							"Unable to double click element, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while double clicking the element",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while double clicking the element",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return bFlag;
	}

	/**
	 * Right click/ Context click on element:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public boolean rightClick(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				WebElement ele = verifyElementClickable(element);
				if (ele != null) {

					Actions action = new Actions(getDriver());
					action.contextClick(ele).build().perform();
					waitForPageLoad(stepDescription);
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription,
					// "Able to right click element successfully");
					bFlag = true;
					break;
				}

			}
			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to right click : " + stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "right click element",
							"Unable to right click element, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while performig right click on an element",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while performig right click on an element", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
		return bFlag;

	}

	/**
	 * Press enter:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public boolean pressEnter(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					element.sendKeys(Keys.ENTER);
					waitForPageLoad(stepDescription);
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription, " Press entered sucessfully");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to press enter, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "enter text on a page",
							"Unable to press enter, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception while performing press enter operation",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception while performing press enter operation",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return bFlag;

	}

	/**
	 * Press Esc:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public boolean pressEsc(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					element.sendKeys(Keys.ESCAPE);
					waitForPageLoad(stepDescription);
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription, " Press entered sucessfully");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to press Esc, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "enter text on a page",
							"Unable to press Esc, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception while performing press Esc operation",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception while performing press Esc operation",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return bFlag;

	}
	
	/**
	 * Press tab key:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public boolean pressTabKey(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					element.sendKeys(Keys.TAB);
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription,
					// " Tab operation successfully performed ");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to perform tab operation, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "perform tab operation",
							"Unable to perform tab operation, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception while performing tab operation",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception while performing tab operation",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return bFlag;

	}

	/**
	 * Select dropdown value based upon visible text:
	 * 
	 * @param stepDescription:-
	 * @param properties
	 * @param visibleText
	 * @return
	 */
	@Override
	public boolean selectDropDown(String stepDescription, String properties, String visibleText) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {

					Select dropDown = new Select(element);
					dropDown.selectByVisibleText(visibleText);
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription,
					// " Successfully selected dropdown value by visible text:
					// ");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to select the dropdown value by visible text: " + visibleText
									+ " , for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Select the dropdown value by visible text",
							"Unable to select the dropdown value by visible text, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception while selecting the dropdown value by visible text",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception while selecting the dropdown value by visible text", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return bFlag;

	}

	/**
	 * Select dropdown value by Index:
	 * 
	 * @param stepDescription:
	 * @param properties
	 * @param index
	 * @return
	 */
	@Override
	public boolean selectDropDownValueByIndex(String stepDescription, String properties, int index) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {

					Select dropDown = new Select(element);
					dropDown.selectByIndex(index);
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription,
					// " Able to select the dropdown value by index");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to select the dropdown value by index: "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "select the dropdown value by index",
							"Unable to select the dropdown value by index,for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception while selecting the dropdown value by index",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception while selecting the dropdown value by index", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return bFlag;

	}

	/**
	 * Get text of element:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public String getText(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		String text = null;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				text = element.getText();
				if (text != null) {
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription, "Text of element :" + text);
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to read text from field : " + stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Unable to get text of an element",
							"Unable to read text from field , for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			return text;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting text of an element",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while getting text of an element",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return null;

	}

	/**
	 * Get text value from Text Box or Dropbox:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public String getTextValueFromTextBoxOrDropBox(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		String text = null;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					text = element.getAttribute("value");
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// " ABle to get text value of Element :: properties: " +
					// properties);
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL,
							"Unable to get text value of an element, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Get text value of an element",
							"Unable to get text value of an element, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			return text;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception while getting text value of an element",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception while getting text value of an element",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return null;

	}

	/**
	 * click using action builder:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return
	 */
	@Override
	public boolean clickUsingActionBuilder(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				WebElement ele = verifyElementClickable(element);
				if (ele != null) {

					Actions action = new Actions(getDriver());
					action.click(ele).build().perform();
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription, "Successfully able to click");
					bFlag = true;
					break;
				}

			}
			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Unable to Click : " + stepDescription
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Click element using action builder",
							"Unable to click element using action builder, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else

				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception occured while performing click element using action builder",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while performing click element using action builder",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return bFlag;

	}

	/**
	 * Get Value of attribute
	 * 
	 * @param stepDescription
	 * @param properties
	 * @param attribute
	 * @return
	 */
	@Override
	public String getValueOfAttribute(String stepDescription, String properties, String attribute) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		String text = null;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					text = element.getAttribute(attribute);
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription,
					// " Successfully able to get the step description");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to get text value of attribute: " + attribute
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Get text value of attribute" + attribute,
							"Unable to get text value of attribute, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			return text;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting text value of attribute:",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while getting text value of attribute:", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return null;

	}

	/**
	 * Verify Text visibility:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @param text
	 * @return
	 */
	@Override
	public boolean isTextPresent(String stepDescription, String properties, String text) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					String elemntText = element.getText();
					if (elemntText.equals(text)) {
						// if (!(stepDescription == null ||
						// stepDescription.isEmpty()))
						// Reporting.getLogger().log(LogStatus.PASS,
						// stepDescription,
						// " Expected text of an element is present");
						bFlag = true;
						break;
					}

					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger()
							.log(LogStatus.FAIL, stepDescription, "Unable to Verify text from field : "
									+ stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Verify text present",
							"Unable to verify text present for an element, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			return bFlag;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception occured while verifying text present on an element",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while verifying text present on an element", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return false;
	}

	private List<WebElement> getWebElementList(String stepDescription, String properties) {
		// TODO Auto-generated method stub

		try {
			Map<String, List<String>> locatorsMapValues = getLocatorsMap(properties);
			List<WebElement> webElements = new ArrayList<WebElement>();

			for (String locator : locatorsMapValues.keySet()) {

				List<String> locatorVal = locatorsMapValues.get(locator);
				for (String locValue : locatorVal) {
					WebElement element = getWebelement(locator, locValue);
					if (element != null)
						webElements.add(element);

				}

			}

			return webElements;

		} catch (TimeoutException t) {

		} catch (Exception e) {

			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting webelement list:",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while getting webelement list:",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return null;
	}

	public List<WebElement> getWebElementList(String properties, long timeOutInSeconds) {
		// TODO Auto-generated method stub

		try {

			Map<String, List<String>> locatorsMapValues = getLocatorsMap(properties);

			List<WebElement> webElements = new ArrayList<WebElement>();

			for (String locator : locatorsMapValues.keySet()) {

				List<String> locatorVal = locatorsMapValues.get(locator);
				for (String locValue : locatorVal) {
					WebElement element = getWebelement(locator, locValue, timeOutInSeconds);
					if (element != null)
						webElements.add(element);

				}

			}

			if (webElements.isEmpty()) {

			}
			return webElements;

		} catch (TimeoutException t) {

		} catch (Exception e) {

			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting webelement list",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while getting webelement list",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return null;
	}

	public List<WebElement> getWebElementListWithWait(String properties, long timeOutInSeconds) {
		// TODO Auto-generated method stub

		try {

			Map<String, List<String>> locatorsMapValues = getLocatorsMap(properties);

			List<WebElement> webElements = new ArrayList<WebElement>();

			for (String locator : locatorsMapValues.keySet()) {

				List<String> locatorVal = locatorsMapValues.get(locator);
				for (String locValue : locatorVal) {
					WebElement element = getWebelementWithWait(locator, locValue, timeOutInSeconds);
					if (element != null)
						webElements.add(element);

				}

			}

			if (webElements.isEmpty()) {

			}
			return webElements;

		} catch (TimeoutException t) {

		} catch (Exception e) {

			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting webelement list",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while getting webelement list",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return null;
	}

	/**
	 * Get text from WebElements List.
	 * 
	 * @param stepDescription
	 * @param properties
	 * @return List<String>
	 */

	@Override
	public List<String> getTextFromWebElementList(String stepDesription, String properties) {
		// TODO Auto-generated method stub

		try {
			List<WebElement> elements = getWebElementsList(stepDesription, properties);
			List<String> elementText = new ArrayList<String>();

			for (WebElement element : elements) {

				elementText.add(element.getText());
			}
			return elementText;
		} catch (TimeoutException t) {

		} catch (Exception e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting text of an Webelement ",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while getting text of an Webelement ",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return null;
	}

	/**
	 * Returns the title of current opened page.
	 * 
	 * @return pageTitle
	 */
	@Override
	public String getPageTitle(String stepDescription) {
		// TODO Auto-generated method stub

		try {

			String pageTitle = getDriver().getTitle();

			// if (!(stepDescription == null || stepDescription.isEmpty()))
			// Reporting.getLogger().log(LogStatus.PASS, stepDescription, "Title
			// of Page: " + pageTitle);

			return pageTitle;
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else

				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting page title:",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while getting page title:",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return null;
	}

	/**
	 * Verify whether the element exists on page: Reporting will add stepDescription
	 * if toReport flag is true:
	 * 
	 * @param stepDescription
	 * @param properties
	 * @param toReport
	 * @return
	 */
	@Override
	public boolean isElementExists(String stepDescription, String properties, boolean toReport) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					if (element.isEnabled()) {

						// if (toReport)
						// if (!(stepDescription == null ||
						// stepDescription.isEmpty()))
						// Reporting.getLogger().log(LogStatus.PASS,
						// stepDescription,
						// " Element exists on the page");
						bFlag = true;
						break;
					}

				}

			}

			if (!bFlag)
				if (toReport)
					if (!(stepDescription == null || stepDescription.isEmpty()))
						Reporting.getLogger().log(LogStatus.FAIL, stepDescription, " Unable to identify the field : "
								+ stepDescription
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
					else
						Reporting.getLogger().log(LogStatus.FAIL, "Verify element exists on page",
								" Unable to identify the field, for details refer " + Reporting.getLogger()
										.addScreenCapture(Reporting.createScreenshot(getDriver())));

			return bFlag;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (toReport)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured: " + stepDescription,
							"For details refer "
									+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL,
							"Exception occured while verifying the visibility of element",
							"For details refer "
									+ new Logger().writeExceptionLogs(
											"Exception occured while verifying the visibility of element",
											getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return false;

	}

	/**
	 * 
	 * Switch and accept Alert. Comments will be visible if 'toReport' is set as
	 * True
	 * 
	 * @param stepDescription
	 * @param toReport
	 */
	@Override
	public boolean switchAndAcceptAlert(String stepDescription, boolean toReport) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {

			WebDriverWait wait = new WebDriverWait(getDriver(), 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());

			if (alert != null) {
				alert.accept();
				bFlag = true;
				// if (toReport)
				// if (!(stepDescription == null || stepDescription.isEmpty()))
				// Reporting.getLogger().log(LogStatus.PASS, stepDescription,
				// "Successfully accepted the alert");

			} else {
				if (toReport)
					if (!(stepDescription == null || stepDescription.isEmpty()))
						Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Alert not present"
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
					else
						Reporting.getLogger().log(LogStatus.FAIL, "Switch and accept alert",
								"Unable to switch and accept alert" + Reporting.getLogger()
										.addScreenCapture(Reporting.createScreenshot(getDriver())));

			}
			return bFlag;
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (toReport)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured: " + stepDescription,
							"For details refer "
									+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while switch and accept alert:",
							"For details refer "
									+ new Logger().writeExceptionLogs(
											"Exception occured while switch and accept alert:", getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
		return false;
	}

	/**
	 * Switch to alert and cancel alert :
	 * 
	 * 
	 * @param stepDescription
	 * @param toReport
	 * @return
	 */
	@Override
	public boolean switchAndCancelAlert(String stepDescription, boolean toReport) {
		// TODO Auto-generated method stub
		boolean bFlag = false;

		try {

			WebDriverWait wait = new WebDriverWait(getDriver(), 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());

			if (alert != null) {
				alert.dismiss();
				bFlag = true;
				// if (toReport)
				// if (!(stepDescription == null || stepDescription.isEmpty()))
				// Reporting.getLogger().log(LogStatus.PASS, stepDescription,
				// "Successfully dismissed the alert");
			} else {
				if (toReport)
					if (!(stepDescription == null || stepDescription.isEmpty()))
						Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Alert not present"
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
					else
						Reporting.getLogger().log(LogStatus.FAIL, "Switch and cancel alert",
								"Unable to switch and accept alert,for details" + Reporting.getLogger()
										.addScreenCapture(Reporting.createScreenshot(getDriver())));

			}
			return bFlag;
		} catch (TimeoutException t) {

		} catch (Exception e) {

			if (toReport)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured: " + stepDescription,
							"For details refer "
									+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while switch and cancel alert:",
							"For details refer "
									+ new Logger().writeExceptionLogs(
											"Exception occured while switch and cancel alert:", getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
		return false;
	}

	/**
	 * Switch to alert & get text from alert:
	 * 
	 * 
	 * @param stepDescription
	 * @return
	 */
	@Override
	public String switchAndGetTextFromAlert(String stepDescription) {
		// TODO Auto-generated method stub
		String text = null;
		try {

			WebDriverWait wait = new WebDriverWait(getDriver(), 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());

			if (alert != null) {
				text = alert.getText();
				// if (!(stepDescription == null || stepDescription.isEmpty()))
				// Reporting.getLogger().log(LogStatus.PASS, stepDescription,
				// "Text message of the Alert: " + text);

			} else {
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Alert not present"
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Switch and get tet of an alert",
							"Unable to Switch and get tet of an alert"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			}
			return text;
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception occured while switching and getting text from an alert",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while switching and getting text from an alert",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
		return null;
	}

	/**
	 * Explicit wait for the visibility of given web element with user-defined wait
	 * time.
	 * 
	 * @param stepDescription
	 * @param properties
	 * @param timeOutInSeconds
	 * @return
	 */
	@Override
	public boolean waitUntilElementIsVisible(String stepDescription, String properties, long timeOutInSeconds) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementListWithWait(properties, timeOutInSeconds);

			for (WebElement element : elements) {

				if (existenceofWebelement(element, timeOutInSeconds)) {
					bFlag = true;
					break;
				}

			}
			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							" Unable to identify the field : " + stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Wait until element is visible",
							"Unable to identify the field, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			return bFlag;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while waiting for element visibility",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while waiting for element visibility", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return false;

	}

	/**
	 * Explicit wait for the visibility of given web element with user-defined wait
	 * time.
	 * 
	 * @param stepDescription
	 * @param properties
	 * @param timeOutInSeconds
	 * @return
	 */
	@Override
	public boolean waitUntilElementIsVisible(String stepDescription, String properties, long timeOutInSeconds,
			boolean toReport) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementListWithWait(properties, timeOutInSeconds);

			for (WebElement element : elements) {

				if (existenceofWebelement(element, timeOutInSeconds)) {
					bFlag = true;
					break;
				}

			}
			if (!bFlag)
				if (toReport) {
					if (!(stepDescription == null || stepDescription.isEmpty()))
						Reporting.getLogger().log(LogStatus.FAIL, stepDescription, " Unable to identify the field : "
								+ stepDescription
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
					else
						Reporting.getLogger().log(LogStatus.FAIL, "Wait until element is visible",
								"Unable to identify the field, for details refer " + Reporting.getLogger()
										.addScreenCapture(Reporting.createScreenshot(getDriver())));
				}
			return bFlag;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (toReport)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured: " + stepDescription,
							"For details refer "
									+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while waiting for element visibility",
							"For details refer " + new Logger().writeExceptionLogs(
									"Exception occured while waiting for element visibility", getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return false;

	}

	/**
	 * Explicit wait for the visibility of given web element with user-defined wait
	 * time.
	 * 
	 * 
	 * @param properties
	 * @param timeOutInSeconds
	 * @return
	 *//*
		 * @Override public boolean waitUntilElementIsVisibleWithOutReporting(String
		 * properties, long timeOutInSeconds) { // TODO Auto-generated method stub
		 * boolean bFlag = false; try { List<WebElement> elements =
		 * getWebElementList(properties, timeOutInSeconds);
		 * 
		 * for (WebElement element : elements) {
		 * 
		 * if (existenceofWebelement(element, timeOutInSeconds)) { bFlag = true; break;
		 * }
		 * 
		 * }
		 * 
		 * return bFlag;
		 * 
		 * } catch (TimeoutException t) {
		 * 
		 * } catch (Exception e) {
		 * 
		 * Reporting.getLogger().log(LogStatus.FAIL,
		 * "Exception occured while waiting for element visibility",
		 * "For details refer " + new Logger().writeExceptionLogs(
		 * "Exception occured while waiting for element visibility",
		 * getExceptionDetails(e)) +
		 * Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver()
		 * ))); }
		 * 
		 * return false;
		 * 
		 * }
		 */

	/**
	 * Explicit wait for invisibility of web element with user-defined wait time.
	 * 
	 * @param properties
	 * @param timeOutInSeconds
	 * @return
	 */

	@Override
	public boolean waitUntilElementIsInVisible(String stepDescription, String properties, long timeOutInSeconds) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (verifyElementInvisibility(properties, timeOutInSeconds)) {
			flag = true;
		} else {

			if (!(stepDescription == null || stepDescription.isEmpty())) {

				Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Element is visible on page"
						+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			} else {

				Reporting.getLogger().log(LogStatus.FAIL, "Element is visible on page",
						"For details refer " + new Logger().writeExceptionLogs("Waiting for element Invisibility",
								"Element is still visible on page" + Reporting.getLogger()
										.addScreenCapture(Reporting.createScreenshot(getDriver()))));
			}
		}
		return flag;

	}

	/**
	 * Explicit wait until given web element is clickable with user-defined wait
	 * time.
	 * 
	 * @param properties
	 * @return
	 */

	@Override
	public boolean waitUntilElementToBeClickable(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				WebElement ele = verifyElementClickable(element);
				if (ele != null) {
					bFlag = true;
					break;
				}

			}
			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to identify field : " + stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Wait for an element to be clickable",
							"Unable to identify field : "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while waiting for clickable element ",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while waiting for clickable element ", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
		return bFlag;
	}

	/**
	 * Checks if the List<WebElement> are in the DOM, regardless of being displayed
	 * or not.
	 * 
	 * @param stepDescription
	 * @param properties
	 * 
	 * @return boolean
	 */

	@Override
	public boolean areElementsPresent(String stepDescription, String properties) {
		// TODO Auto-generated method stub

		if (getWebElementsList(stepDescription, properties).size() != 0) {

			// Reporting.getLogger().log(LogStatus.PASS, stepDescription,
			// "Elements are present on the page");
		} else {

			Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Elements are not present on the page"
					+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return false;
	}

	/**
	 * Get current window handle
	 *
	 */

	@Override
	public String getCurrentWindowHandle(String stepDescription) {
		// TODO Auto-generated method stub

		try {
			String windowHandle = null;

			windowHandle = getDriver().getWindowHandle();
			return windowHandle;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting current window handle",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while getting current window handle", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return null;
	}

	/**
	 * Get all window handles
	 */
	@Override
	public Set<String> getAllWindowHandles(String stepDescription) {
		// TODO Auto-generated method stub

		try {
			Set<String> windowHandles = null;

			windowHandles = getDriver().getWindowHandles();
			return windowHandles;

		} catch (TimeoutException t) {

		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting all window handles",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while getting all window handles",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return null;
	}

	/**
	 * Switch to a window based upon window handle:-
	 * 
	 * @param stepDescription
	 * @param previousWindowHandel
	 */
	@Override
	public boolean switchToWindow(String stepDescription, String previousWindowHandle) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {

			WebDriver driver = getDriver().switchTo().window(previousWindowHandle);
			if (driver != null && (getCurrentWindowHandle(stepDescription).equals(previousWindowHandle))) {
				bFlag = true;
				// if (!(stepDescription == null || stepDescription.isEmpty()))
				// Reporting.getLogger().log(LogStatus.PASS, stepDescription,
				// "Successfully switched to window:-");
			} else {
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Unable to switch window:-"
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Switch to a new window", "Unable to switch window:-"
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			}

			return bFlag;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while switching to a new window",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while switching to a new window",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return false;
	}

	/**
	 * Switch to a window based upon index of windows
	 * 
	 * @param stepDescription
	 */
	@Override
	public void switchToNewWindow(String stepDescription, int i) {
		// TODO Auto-generated method stub
		try {

			Set<String> windows = getAllWindowHandles(stepDescription);
			getDriver().switchTo().window(windows.toArray(new String[windows.size()])[i]);
			// if (!(stepDescription == null || stepDescription.isEmpty()))
			// Reporting.getLogger().log(LogStatus.PASS, stepDescription,
			// "Successfully switched to window");

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while switching to a new window",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while switching to a new window",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
	}

	/**
	 * 
	 * Close windows based upon window index:-
	 * 
	 * @param stepDescription
	 * @param windowNumber
	 * @param toReport:       False: No reporting will be done for this method True:
	 *                        default reporting will be added for method
	 */
	@Override
	public void closeWindow(String stepDescription, int i, boolean toReport) {
		// TODO Auto-generated method stub

		try {
			Set<String> windows = getDriver().getWindowHandles();
			getDriver().switchTo().window(windows.toArray(new String[windows.size()])[i]).close();
			// if (toReport)
			// if (!(stepDescription == null || stepDescription.isEmpty()))
			// Reporting.getLogger().log(LogStatus.PASS, stepDescription,
			// "Successfully closed the window");
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (toReport)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured: " + stepDescription,
							"For details refer "
									+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while closing window",
							"For details refer "
									+ new Logger().writeExceptionLogs("Exception occured while closing window",
											getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	@Override
	public void refreshPage() {
		// TODO Auto-generated method stub

		try {

			getDriver().navigate().refresh();

		} catch (TimeoutException t) {

		} catch (Exception e) {

			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while refreshing the page ",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while refreshing the page ",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	@Override
	public void navigateToPreviousPage(String stepDescription) {
		// TODO Auto-generated method stub

		try {
			getDriver().navigate().back();

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while navigating to back page",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while navigating to back page",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
	}

	/**
	 * Mouse hover an element in order to see the subesquent pop-ups/messages
	 * 
	 * @param stepDescription
	 * @param properties
	 */
	@Override
	public void mouseHover(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {

					Actions action = new Actions(getDriver());
					action.moveToElement(element).build().perform();
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription,
					// "Able to mouseHover over an element");
					bFlag = true;
					break;
				}

			}
			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger()
							.log(LogStatus.FAIL, stepDescription, "Unable to mouseHover over an field : "
									+ stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Mouse Hover over an element",
							"Unable to mouseHover over an element, "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception occured while mouse hover operation over an element:",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while mouse hover operation over an element:",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

	}

	/**
	 * Mouse hover over an element and click on child element:-
	 * 
	 * @param stepDescription
	 * @param parentProperties
	 * @param childProperties
	 */
	@Override
	public void mouseHoverClickChild(String stepDescription, String parentProperties, String childProperties) {
		// TODO Auto-generated method stub

		try {
			boolean bFlag = false;
			List<WebElement> parentElements = getWebElementList(stepDescription, parentProperties);
			List<WebElement> childElements = getWebElementList(stepDescription, childProperties);

			for (WebElement element : parentElements) {

				if (visibilityofWebelement(element)) {
					for (WebElement childElement : childElements) {
						if (visibilityofWebelement(childElement)) {

							Actions action = new Actions(getDriver());
							action.moveToElement(element).moveToElement(childElement).click().build().perform();
							// if (!(stepDescription == null ||
							// stepDescription.isEmpty()))
							// Reporting.getLogger().log(LogStatus.PASS,
							// stepDescription,
							// "Successfully clicked the child element
							// specified");
							bFlag = true;
						}

					}

				}

			}

			if (!bFlag) {
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to identify the elements specified"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Mouse over and click child node",
							"Unable to identify the elements and click child nodes"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			}

		} catch (TimeoutException t) {

		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while clicking child element:",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while clicking child element:",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * 
	 * 
	 * Gets current page source:-
	 * 
	 * @param boolean toReport
	 * @return
	 */
	@Override
	public String getPageSource(String stepDescription, boolean toReport) {
		// TODO Auto-generated method stub
		String pageSource = null;
		try {

			pageSource = getDriver().getPageSource();
			// if (toReport)
			// Reporting.getLogger().log(LogStatus.PASS, "Fetch current page
			// source:- ",
			// "Succesfully able to get the page source of url: " +
			// getDriver().getCurrentUrl());

			return pageSource;
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (toReport)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured: " + stepDescription,
							"For details refer "
									+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while fetching page source: ",
							"For details refer "
									+ new Logger().writeExceptionLogs("Exception occured while fetching page source: ",
											getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return null;
	}

	/**
	 * Delete all cookies of current page:-
	 * 
	 */
	@Override
	public void deleteAllCookies(String stepDescription) {
		// TODO Auto-generated method stub
		try {

			getDriver().manage().deleteAllCookies();
			Thread.sleep(3500);

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while deleting page cookies: ",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while deleting page cookies: ",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * 
	 * Get current page url:-
	 */
	@Override
	public String getCurrentUrl(String stepDescription) {
		// TODO Auto-generated method stub
		try {

			String currenturl = getDriver().getCurrentUrl();
			return currenturl;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting url of page: ",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while getting url of page: ",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return null;
	}

	/**
	 * Reset ImplicitWait to default. <br>
	 * Default wait time for a page to be displayed is 40 seconds. Based on your
	 * test you can set this value. <br>
	 * <br>
	 * To reset ImplicitWait time, first it automatically set the value as '0' and
	 * then set it with a new default time value.
	 * 
	 * @param driver
	 */
	@Override
	public void resetImplicitWaitToDefault() {
		// TODO Auto-generated method stub
		try {
			nullifyImplicitWait();
			setImplicitWaitToDefault();
		} catch (TimeoutException t) {

		} catch (Exception e) {

			Reporting.getLogger().log(LogStatus.FAIL,
					"Exception occured while resetting the implicit wait for a page: ",
					"For details refer " + new Logger().writeExceptionLogs(
							"Exception occured while resetting the implicit wait for a page: ", getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	@Override
	public void resetImplicitWait(String stepDescription, int newWaitTime_InSeconds) {
		// TODO Auto-generated method stub
		try {
			nullifyImplicitWait();
			setImplicitWait(newWaitTime_InSeconds);
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while resetting the implicit wait for ",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while resetting the implicit wait for ", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	@Override
	public void switchToIframe(String stepDescription) {
		// TODO Auto-generated method stub
		try {
			new WebDriverWait(getDriver(), 15)
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while switchin to iframe by tagname",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while switchin to iframe by tagname", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * 
	 * Switch to an iFrame based upon iFrameID
	 * 
	 * @param iFrameId
	 */
	@Override
	public void switchToIframeById(String stepDescription, String iFrameId) {
		// TODO Auto-generated method stub
		try {
			new WebDriverWait(getDriver(), 15)
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(iFrameId)));
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while switching to iframe by Iframeid ",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while switching to iframe by Iframeid ", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * Switch to an iFrame based upon iFrame Index:-
	 * 
	 * @param iFrame index
	 */
	@Override
	public void switchToIframeByIndex(String stepDescription, int iframeIndex) {
		// TODO Auto-generated method stub
		try {
			this.driver = new WebDriverWait(getDriver(), 15)
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeIndex));

		} catch (TimeoutException t) {

		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else

				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while switching to iframe by index ",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while switching to iframe by index ", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * 
	 * Switch to an iFrame based upon iFrame properties:-
	 * 
	 * @param properties
	 * 
	 */
	@Override
	public boolean switchToIframe(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		try {

			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					getDriver().switchTo().frame(element);
					bFlag = true;
				}

			}

		} catch (TimeoutException t) {

		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

			else

				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception occured while switching to iframe by Iframe properties ",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while switching to iframe by Iframe properties ",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return bFlag;
	}

	/**
	 * Deselect iFrame:
	 * 
	 */
	@Override
	public void deselectIframe(String stepDescription) {
		// TODO Auto-generated method stub
		try {
			getDriver().switchTo().defaultContent();
		} catch (TimeoutException t) {

		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else

				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while deselecting iFrame",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while deselecting iFrame",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * 
	 * Execute javascript:-
	 * 
	 */
	@Override
	public void executeJavaScript(String stepDescription, String jScript) {
		// TODO Auto-generated method stub
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript(jScript);
		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while excecuting Javascript on Browser",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while excecuting Javascript on Browser", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * 
	 * Enter text using Javascript:-
	 * 
	 * @param StepDescription
	 * @param properties
	 * @param textToEnter
	 * 
	 * 
	 * @return true/false
	 */
	@Override
	public boolean enterTextUsingJSExecutor(String stepDescription, String properties, String textToEnter) {
		// TODO Auto-generated method stub

		boolean bFlag = false;

		try {

			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					((JavascriptExecutor) getDriver()).executeScript("arguments[0].value ='';", element);
					((JavascriptExecutor) getDriver()).executeScript("arguments[0].value ='" + textToEnter + "';",
							element);
					bFlag = true;
					break;
				}

			}

			if (!bFlag) {
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Unable to enter text: " + textToEnter
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Enter text using Javascript",
							"Unable to enter text using Javascript for a page, For details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			}
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception occured while entering text using Javascript for a page:",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while entering text using Javascript for text field",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return bFlag;

	}

	/**
	 * 
	 * Click using Javascript:-
	 * 
	 * @param StepDescription
	 * @param properties
	 * @return true/false
	 */
	@Override
	public boolean clickUsingJSExecutor(String stepDescription, String properties) {
		// TODO Auto-generated method stub

		boolean bFlag = false;

		try {

			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
					bFlag = true;
					break;
				}

			}

			if (!bFlag) {
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Unable to Click : " + stepDescription
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Click using Javascript",
							"Unable to click using Javascript for a page, For details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			}
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception occured while clicking using Javascript for a page:",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while clicking using Javascript for text field",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return bFlag;

	}

	/**
	 * double Click using Javascript:-
	 * 
	 * @param StepDescription
	 * @param properties
	 * @return true/false
	 */
	@Override
	public boolean doubleClickUsingJSExecutor(String stepDescription, String properties) {
		// TODO Auto-generated method stub

		boolean bFlag = false;

		try {

			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {

				if (visibilityofWebelement(element)) {
					// ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					// element);
					((JavascriptExecutor) getDriver()).executeScript("var evt = document.createEvent('MouseEvents');"
							+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
							+ "arguments[0].dispatchEvent(evt);", element);

					bFlag = true;
					break;
				}

			}

			if (!bFlag) {
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to double click : " + stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Click using Javascript",
							"Unable to double click " + stepDescription
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			}
		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception occured while clicking using Javascript for a page:",
						"For details refer "
								+ new Logger().writeExceptionLogs(
										"Exception occured while clicking using Javascript for text field",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return bFlag;

	}

	/**
	 * 
	 * Wait for an element for specified milliseconds:-
	 */
	@Override
	public void waitFor(int enterMiliSeconds) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(enterMiliSeconds);
		} catch (InterruptedException e) {

			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while waiting for " + enterMiliSeconds,
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while clicking the element",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

	}

	/**
	 * 
	 * Wait for page load:-
	 */
	@Override
	public void waitForPageLoad(String stepDescription) {
		// TODO Auto-generated method stub

		try {

			getDriver().manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
			new WebDriverWait(getDriver(), 300).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
					.executeScript("return document.readyState").equals("complete"));

		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while waiting for page load",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while waiting for page load",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * Quit Driver
	 */
	@Override
	public void quitDriver() {
		// TODO Auto-generated method stub

		try {

			if (getDriver().toString() == null || getDriver() == null) {
				Reporting.getLogger().log(LogStatus.FAIL, "NoSuchSession Exception",
						"Session is not created or session value is null");

				System.out.println(
						"in the time of execution session value is null Nosuchsession exception driver become null");
			}

			getDriver().quit();
			Reporting.getLogger().log(LogStatus.PASS, "Quit driver", "Driver quit successful");
		} catch (Exception e) {

			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while quitting driver",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while quitting driver",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
	}

	/**
	 * Close current window:-
	 */
	@Override
	public void closeCurrentWindow(String stepDescription) {

		// TODO Auto-generated method stub

		try {

			getDriver().close();
		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while closing browser",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while closing browser",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
	}

	/*
	 * return webelement of locator:-
	 */
	private WebElement getWebelement(String locator, String locatorValue) {

		try {
			FluentWait<WebDriver> driverWait = new FluentWait<WebDriver>(getDriver());
			driverWait.pollingEvery(40, TimeUnit.MILLISECONDS);
			driverWait.withTimeout(1, TimeUnit.SECONDS);
			driverWait.ignoring(NoSuchElementException.class);
			driverWait.ignoring(StaleElementReferenceException.class);
			driverWait.ignoring(ElementNotFoundException.class);
			driverWait.ignoring(ElementNotVisibleException.class);

			return findElement(locator, locatorValue, driverWait);

		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}

	}

	private WebElement getWebelement(String locator, String locatorValue, long timeOutInSeconds) {

		FluentWait<WebDriver> driverWait = new FluentWait<WebDriver>(getDriver());
		driverWait.pollingEvery(250, TimeUnit.MILLISECONDS);
		driverWait.withTimeout(60, TimeUnit.SECONDS);
		driverWait.ignoring(NoSuchElementException.class);
		driverWait.ignoring(StaleElementReferenceException.class);
		driverWait.ignoring(ElementNotVisibleException.class);
		driverWait.ignoring(ElementNotFoundException.class);

		return findElement(locator, locatorValue, driverWait);
	}

	private WebElement getWebelementWithWait(String locator, String locatorValue, long timeOutInSeconds) {

		FluentWait<WebDriver> driverWait = new FluentWait<WebDriver>(getDriver());
		driverWait.pollingEvery(250, TimeUnit.MILLISECONDS);
		driverWait.withTimeout(timeOutInSeconds, TimeUnit.SECONDS);
		driverWait.ignoring(NoSuchElementException.class);
		driverWait.ignoring(StaleElementReferenceException.class);
		driverWait.ignoring(ElementNotVisibleException.class);
		driverWait.ignoring(ElementNotFoundException.class);

		return findElement(locator, locatorValue, driverWait);
	}

	/**
	 * 
	 * @param element
	 */
	private void viewPortElement(WebElement element) {
		try {
			// Coordinates coordinate = (((Locatable) element).getCoordinates());
			// coordinate.onPage();
			// coordinate.inViewPort();
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getCSSProperty(String stepDescription, String properties, String cssValue) {
		boolean bFlag = false;
		String text = null;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);
			for (WebElement element : elements) {

				text = element.getCssValue(cssValue);
				if (text != null) {
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to get CSS property of an element, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Unable to get CSS property of an element",
							"Unable to get text of an element, for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	private WebElement findElement(String locator, String locatorValue, FluentWait<WebDriver> driverWait) {

		WebElement element = null;
		final String locVal = locatorValue;

		try {
			if (locator.equalsIgnoreCase("id")) {

				Function<WebDriver, WebElement> waitForElement = new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(WebDriver driverWait) {
						// WebDriver driver = getDriver();
						// TODO Auto-generated method stub
						return getDriver().findElement(By.id(locVal));
					}

				};
				element = driverWait.until(waitForElement);
			}
			if (locator.equalsIgnoreCase("xpath")) {

				Function<WebDriver, WebElement> waitForElement = new Function<WebDriver, WebElement>() {

					@Override
					public WebElement apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElement(By.xpath(locVal));
					}

				};
				element = driverWait.until(waitForElement);

			}
			if (locator.equalsIgnoreCase("className")) {

				Function<WebDriver, WebElement> waitForElement = new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElement(By.className(locVal));
					}

				};
				element = driverWait.until(waitForElement);

			}
			if (locator.equalsIgnoreCase("name")) {
				Function<WebDriver, WebElement> waitForElement = new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElement(By.name(locVal));
					}

				};
				element = driverWait.until(waitForElement);
			}
			if (locator.equalsIgnoreCase("tagName")) {

				Function<WebDriver, WebElement> waitForElement = new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElement(By.tagName(locVal));
					}

				};
				element = driverWait.until(waitForElement);

			}
			if (locator.equalsIgnoreCase("css")) {

				Function<WebDriver, WebElement> waitForElement = new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElement(By.cssSelector(locVal));
					}

				};
				element = driverWait.until(waitForElement);

			}

			if (locator.equalsIgnoreCase("linkText")) {

				Function<WebDriver, WebElement> waitForElement = new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElement(By.linkText(locVal));
					}

				};
				element = driverWait.until(waitForElement);

			}
			if (ILeanControls.viewPort.toLowerCase().equalsIgnoreCase("yes")) {
				viewPortElement(element);
			}
			return element;

		} catch (Exception e) {

			// e.printStackTrace();
			// Reporting.getLogger().log(LogStatus.WARNING,
			// " Locator " + locator + ":" + locatorValue + " not found :-" +
			// getExceptionDetails(e));
		}

		return null;
	}

	/**
	 * 
	 * verify whether the Webelement is displayed on the page or not:-
	 */

	private boolean visibilityofWebelement(WebElement element) {

		try {
			FluentWait<WebElement> _waitForElement = new FluentWait<WebElement>(element);
			_waitForElement.pollingEvery(10, TimeUnit.SECONDS);
			_waitForElement.withTimeout(60, TimeUnit.SECONDS);
			_waitForElement.ignoring(NoSuchElementException.class);
			_waitForElement.ignoring(StaleElementReferenceException.class);
			_waitForElement.ignoring(ElementNotVisibleException.class);
			_waitForElement.ignoring(ElementNotFoundException.class);

			Function<WebElement, Boolean> elementVisibility = new Function<WebElement, Boolean>() {

				@Override
				public Boolean apply(WebElement element) {
					// TODO Auto-generated method stub

					return element.isDisplayed();
				}

			};

			return _waitForElement.until(elementVisibility);

		} catch (Exception e) {
			// e.printStackTrace();
			// Reporting.getLogger().log(LogStatus.FAIL, "Exception occured
			// while verifying the visibility of an element");
		}

		return false;
	}

	private boolean visibilityofWebelement(WebElement element, long timeOutInSeconds) {

		try {
			FluentWait<WebElement> _waitForElement = new FluentWait<WebElement>(element);
			_waitForElement.pollingEvery(500, TimeUnit.MILLISECONDS);
			_waitForElement.withTimeout(timeOutInSeconds, TimeUnit.SECONDS);
			_waitForElement.ignoring(NoSuchElementException.class);
			_waitForElement.ignoring(StaleElementReferenceException.class);
			_waitForElement.ignoring(ElementNotVisibleException.class);
			_waitForElement.ignoring(ElementNotFoundException.class);

			Function<WebElement, Boolean> elementVisibility = new Function<WebElement, Boolean>() {

				@Override
				public Boolean apply(WebElement element) {
					// TODO Auto-generated method stub

					return element.isDisplayed();
				}

			};

			return _waitForElement.until(elementVisibility);

		} catch (Exception e) {
			// e.printStackTrace();
			// Reporting.getLogger().log(LogStatus.FAIL, "Exception occured
			// while verifying the visibility of an element");
		}

		return false;
	}

	private boolean existenceofWebelement(WebElement element, long timeOutInSeconds) {

		try {

			FluentWait<WebElement> _waitForElement = new FluentWait<WebElement>(element);
			_waitForElement.pollingEvery(500, TimeUnit.MILLISECONDS);
			_waitForElement.withTimeout(timeOutInSeconds, TimeUnit.SECONDS);
			_waitForElement.ignoring(NoSuchElementException.class);
			_waitForElement.ignoring(StaleElementReferenceException.class);
			_waitForElement.ignoring(ElementNotVisibleException.class);
			_waitForElement.ignoring(ElementNotFoundException.class);

			Function<WebElement, Boolean> elementVisibility = new Function<WebElement, Boolean>() {

				@Override
				public Boolean apply(WebElement element) {
					// TODO Auto-generated method stub

					return element.isEnabled();
				}

			};

			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.visibilityOf(element));

			return _waitForElement.until(elementVisibility);

		} catch (Exception e) {
			// e.printStackTrace();
			// Reporting.getLogger().log(LogStatus.FAIL, "Exception occured
			// while verifying the visibility of an element");
		}

		return false;
	}

	/**
	 * 
	 * verify whether the element is clickable:-
	 */
	private WebElement verifyElementClickable(WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 40);
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			// e.printStackTrace();
			// Reporting.getLogger().log(LogStatus.FAIL, "Exception occured
			// while verifying the visibility of an element");
		}

		return null;
	}

	/**
	 * 
	 * verify whether the element is invisible:-
	 */
	private boolean verifyElementInvisibility(String properties, long timeOutInSeconds) {
		boolean elem = false;
		// boolean bFlag = false;
		try {

			Map<String, String> locatorsMap = new HashMap<String, String>();

			String[] locatorValues = properties.split("\\|");

			for (String locator : locatorValues) {

				locatorsMap.put(locator.split("~")[0].trim(), locator.split("~")[1].trim());

			}

			// Fetch webelement of all locators:-
			for (String locator : locatorsMap.keySet()) {

				WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);

				if (locator.equalsIgnoreCase("xpath")) {

					elem = wait
							.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locatorsMap.get(locator))));
				} else if (locator.equalsIgnoreCase("classname")) {
					elem = wait.until(
							ExpectedConditions.invisibilityOfElementLocated(By.className(locatorsMap.get(locator))));

				} else if (locator.equalsIgnoreCase("name")) {
					elem = wait
							.until(ExpectedConditions.invisibilityOfElementLocated(By.name(locatorsMap.get(locator))));

				} else if (locator.equalsIgnoreCase("id")) {
					elem = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locatorsMap.get(locator))));

				} else if (locator.equalsIgnoreCase("css")) {
					elem = wait.until(
							ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locatorsMap.get(locator))));

				} else if (locator.equalsIgnoreCase("tagname")) {
					elem = wait.until(
							ExpectedConditions.invisibilityOfElementLocated(By.tagName(locatorsMap.get(locator))));

				} else if (locator.equalsIgnoreCase("linkText")) {
					elem = wait.until(
							ExpectedConditions.invisibilityOfElementLocated(By.linkText(locatorsMap.get(locator))));

				} else
					break;
			}
		} catch (Exception e) {

		}

		return elem;
	}

	/**
	 * Set implicit wait to zero explicitly to nullify it.
	 */
	private void nullifyImplicitWait() {
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/**
	 * Set driver implicitlyWait() time.
	 */
	private void setImplicitWait(int waitTime_InSeconds) {
		getDriver().manage().timeouts().implicitlyWait(waitTime_InSeconds, TimeUnit.SECONDS);
	}

	/**
	 * Set implicit wait to default. <br>
	 * Default wait time for a page to be displayed is 40 seconds. Based on your
	 * tests, please set this value. <br>
	 * <br>
	 * "0" will nullify implicitlyWait and speed up a test.
	 */
	private void setImplicitWaitToDefault() {
		final int DEFAULT_WAIT_4_PAGE = 40;
		getDriver().manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS);
	}

	/**
	 * Get the webelements of the element:-
	 * 
	 * 
	 * @param locator
	 * @param locatorValue
	 * @return
	 */
	private List<WebElement> getWebElementsOfElement(String locator, String locatorValue) {

		List<WebElement> elements = null;
		final String locVal = locatorValue;
		FluentWait<WebDriver> driverWait = new FluentWait<WebDriver>(getDriver());
		driverWait.pollingEvery(250, TimeUnit.MILLISECONDS);
		driverWait.withTimeout(2, TimeUnit.SECONDS);
		driverWait.ignoring(NoSuchElementException.class);
		driverWait.ignoring(StaleElementReferenceException.class);

		try {
			if (locator.equalsIgnoreCase("id")) {

				Function<WebDriver, List<WebElement>> waitForElement = new Function<WebDriver, List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElements(By.id(locVal));
					}

				};
				elements = driverWait.until(waitForElement);
			}
			if (locator.equalsIgnoreCase("xpath")) {

				Function<WebDriver, List<WebElement>> waitForElement = new Function<WebDriver, List<WebElement>>() {

					@Override
					public List<WebElement> apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElements(By.xpath(locVal));
					}

				};
				elements = driverWait.until(waitForElement);

			}
			if (locator.equalsIgnoreCase("className")) {

				Function<WebDriver, List<WebElement>> waitForElement = new Function<WebDriver, List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElements(By.className(locVal));
					}

				};
				elements = driverWait.until(waitForElement);

			}
			if (locator.equalsIgnoreCase("name")) {
				Function<WebDriver, List<WebElement>> waitForElement = new Function<WebDriver, List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElements(By.name(locVal));
					}

				};
				elements = driverWait.until(waitForElement);
			}
			if (locator.equalsIgnoreCase("tagName")) {

				Function<WebDriver, List<WebElement>> waitForElement = new Function<WebDriver, List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElements(By.tagName(locVal));
					}

				};
				elements = driverWait.until(waitForElement);

			}
			if (locator.equalsIgnoreCase("css")) {

				Function<WebDriver, List<WebElement>> waitForElement = new Function<WebDriver, List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElements(By.cssSelector(locVal));
					}

				};
				elements = driverWait.until(waitForElement);

			}

			if (locator.equalsIgnoreCase("linkText")) {

				Function<WebDriver, List<WebElement>> waitForElement = new Function<WebDriver, List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver driverWait) {
						// TODO Auto-generated method stub
						return getDriver().findElements(By.linkText(locVal));
					}

				};
				elements = driverWait.until(waitForElement);

			}

			return elements;

		} catch (Exception e) {

			// e.printStackTrace();

			// Reporting.getLogger().log(LogStatus.WARNING,
			// " Locator " + locator + ":" + locatorValue + " not found :-" +
			// getExceptionDetails(e));
		}

		return null;
	}

	/**
	 * 
	 * Get the list of webElements for the generic locator:-
	 * 
	 * @param properties
	 * @return
	 */
	@Override
	public List<WebElement> getWebElementsList(String stepDescription, String properties) {

		try {
			List<WebElement> elements = new ArrayList<WebElement>();

			// Map<String, String> locatorsMap = new HashMap<String, String>();
			//
			// String[] locatorValues = properties.split("\\|");
			//
			// for (String locator : locatorValues) {
			//
			// locatorsMap.put(locator.split("~")[0].trim(),
			// locator.split("~")[1].trim());
			// }

			Map<String, List<String>> locatorsMapValues = getLocatorsMap(properties);

			for (String locator : locatorsMapValues.keySet()) {

				List<String> locatorVal = locatorsMapValues.get(locator);
				for (String locValue : locatorVal) {
					WebElement element = getWebelement(locator, locValue);
					if (element != null) {
						elements = getWebElementsOfElement(locator, locValue);
						break;
					}
				}
			}
			return elements;

		} catch (Exception e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting webelements List",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while getting webelements List",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return null;
	}

	/**
	 * Verify whether the page title contains the expected title:-
	 * 
	 */
	@Override
	public boolean verifyTitle(String stepDescription, String title) {
		// TODO Auto-generated method stub

		return new WebDriverWait(getDriver(), 40).until(ExpectedConditions.titleContains(title));
	}

	private Map<String, List<String>> getLocatorsMap(String properties) {
		Map<String, List<String>> locatorsMapValues = new HashMap<String, List<String>>();
		try {

			// Fetch the different locators:-
			// Map<String, String> locatorsMap = new HashMap<String, String>();

			// Split the different locators based upon Pipe symbols:-
			String[] locatorValues = properties.split("\\|");

			// for (String locator : locatorValues) {
			//
			// locatorsMap.put(locator.split("~")[0].trim(),
			// locator.split("~")[1].trim());
			// }

			for (String locator : locatorValues) {

				String locatorName = locator.split("~")[0].trim();
				String locatorValue = locator.split("~")[1].trim();
				if (locatorsMapValues.containsKey(locatorName)) {
					List<String> locatorNameProperties = locatorsMapValues.get(locatorName);
					locatorNameProperties.add(locatorValue);
				} else {
					List<String> locatorVal = new ArrayList<String>();
					locatorVal.add(locatorValue);
					locatorsMapValues.put(locatorName, locatorVal);

				}

			}

		} catch (Exception e) {

			// e.printStackTrace();
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting locatos from properties",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception occured while getting locatos from properties",
									getExceptionDetails(e))
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return locatorsMapValues;
	}

	/**
	 * 
	 * @param stepDescription
	 * @param parentProperties
	 * @param childProperties
	 */
	public void contextMenuClick(String stepDescription, String parentProperties, String childProperties) {
		// TODO Auto-generated method stub
		Actions action = new Actions(getDriver());
		try {
			boolean bFlag = false;
			List<WebElement> parentElements = getWebElementList(stepDescription, parentProperties);
			for (WebElement element : parentElements) {
				if (visibilityofWebelement(element)) {
					action.moveToElement(element).click(element).build().perform();
					List<WebElement> childElements = getWebElementList(stepDescription, childProperties);
					for (WebElement childElement : childElements) {
						if (visibilityofWebelement(childElement)) {
							action.moveToElement(childElement).click().build().perform();
							bFlag = true;
							break;
						}

					}
					if (bFlag == true) {
						break;
					}
				}

			}

			if (!bFlag) {
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to identify the elements specified"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Mouse over and click child node",
							"Unable to identify the elements and click child nodes"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			}

		} catch (TimeoutException t) {

		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while clicking child element:",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while clicking child element:",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
	}

	/**
	 * 
	 */

	public boolean customDropDownselect(String stepDescription, String txtboxprop, String optionProp, String optValue) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		Actions action = new Actions(getDriver());
		try {
			List<WebElement> elements = getWebElementList(stepDescription, txtboxprop);
			for (WebElement element : elements) {
				if (visibilityofWebelement(element)) {
					action.click(element).build().perform();
					waitFor(2000);
					element.clear();
					action.sendKeys(element, optValue).build().perform();
					waitFor(1000);
					List<WebElement> childelements = getWebElementList(stepDescription, optionProp);
					System.out.println("Size child :" + childelements.size());
					for (WebElement chldelmt : childelements) {
						if (visibilityofWebelement(chldelmt)) {
							System.out.println("inside child :" + chldelmt.getText());
							action.moveToElement(chldelmt).click(chldelmt).build().perform();
							bFlag = true;
							break;
						}
					}

					if (bFlag == true) {
						break;
					}
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger()
							.log(LogStatus.FAIL, stepDescription, "Unable to select the dropdown value : "
									+ " , for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Select the dropdown value ",
							"Unable to select the dropdown valuet, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception while selecting the dropdown value by visible text",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception while selecting the dropdown value by visible text", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return bFlag;

	}

	public boolean customDropDownselectWitoutEdit(String stepDescription, String txtboxprop, String optionProp) {
		// TODO Auto-generated method stub
		boolean bFlag = false;
		Actions action = new Actions(getDriver());
		try {
			List<WebElement> elements = getWebElementList(stepDescription, txtboxprop);
			for (WebElement element : elements) {
				if (visibilityofWebelement(element)) {
					action.click(element).build().perform();
					waitFor(2000);
					List<WebElement> childelements = getWebElementList(stepDescription, optionProp);
					System.out.println("Size child :" + childelements.size());
					for (WebElement chldelmt : childelements) {
						if (visibilityofWebelement(chldelmt)) {
							System.out.println("inside child :" + chldelmt.getText());
							action.moveToElement(chldelmt).click(chldelmt).build().perform();
							bFlag = true;
							break;
						}
					}

					if (bFlag == true) {
						break;
					}
				}

			}

			if (!bFlag)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger()
							.log(LogStatus.FAIL, stepDescription, "Unable to select the dropdown value : "
									+ " , for details refer "
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Select the dropdown value ",
							"Unable to select the dropdown valuet, for details refer"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL,
						"Exception while selecting the dropdown value by visible text",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception while selecting the dropdown value by visible text", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return bFlag;

	}

	/**
	 * 
	 * @param stepDescription
	 * @param properties
	 */
	public void scrollPagedown(String stepDescription, String properties) {
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);
			for (WebElement element : elements) {
				if (visibilityofWebelement(element)) {
					element.sendKeys(Keys.PAGE_DOWN);
					break;
				}
			}
		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception while scrolling down page",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception while selecting the dropdown value by visible text", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
	}

	/**
	 * 
	 */
	public String getCurrentMonth(String stepDescription) {
		String currentMon = "";
		Calendar cal = null;
		try {
			cal = Calendar.getInstance();
			currentMon = new SimpleDateFormat("MMMM").format(cal.getTime());
			if (!(currentMon == null)) {
				return currentMon;
			}
		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception : for getting current month",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception while selecting the dropdown value by visible text", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return null;
	}

	public String getCurrentMonthNumber(String stepDescription) {
		String currentMon = "";
		Calendar cal = null;
		try {
			cal = Calendar.getInstance();
			currentMon = new SimpleDateFormat("MM").format(cal.getTime());
			if (!(currentMon == null)) {
				return currentMon;
			}
		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception : for getting current month",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception while selecting the dropdown value by visible text", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return null;
	}

	/**
	 * 
	 */
	public String getCurrentYear(String stepDescription) {
		String currentYear = "";
		Calendar cal = null;
		try {
			cal = Calendar.getInstance();
			currentYear = new SimpleDateFormat("YYYY").format(cal.getTime());
			if (!(currentYear == null)) {
				return currentYear;
			}
		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception : for getting current month",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception while selecting the dropdown value by visible text", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}

		return null;
	}

	/*
	 * 
	 */
	public void scrollObjectIntoView(String stepDescription) {
		try {

		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception : for getting current month",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception while selecting the dropdown value by visible text", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));

		}
	}

	/*
	 * 
	 */
	public boolean childObjectClick(String stepDescription, String parentProperties, String childProperties) {
		// TODO Auto-generated method stub
		Actions action = new Actions(getDriver());
		boolean bFlag = false;
		try {

			List<WebElement> parentElements = getWebElementList(stepDescription, parentProperties);
			for (WebElement element : parentElements) {
				if (visibilityofWebelement(element)) {
					action.moveToElement(element).click(element).build().perform();
					List<WebElement> childElements = getWebElementList(stepDescription, childProperties);
					for (WebElement childElement : childElements) {
						if (visibilityofWebelement(childElement)) {
							action.moveToElement(childElement).click().build().perform();
							bFlag = true;
							break;
						}

					}
					if (bFlag == true) {
						break;
					}
				}

			}

			if (!bFlag) {
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, stepDescription,
							"Unable to identify the elements specified"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Mouse over and click child node",
							"Unable to identify the elements and click child nodes"
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			}

		} catch (TimeoutException t) {

		} catch (Exception e) {

			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while clicking child element:",
						"For details refer "
								+ new Logger().writeExceptionLogs("Exception occured while clicking child element:",
										getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return bFlag;
	}

	/*
	 * 
	 */
	public int getIndexFromArray(String stepDescription, String[] arry, String matctxt) {
		int pos = -1;
		try {
			for (int i = 0; i < arry.length; i++) {
				if (arry[i].equals(matctxt)) {
					pos = i + 1;
				}
			}
		} catch (Exception e) {
			if (!(stepDescription == null || stepDescription.isEmpty()))
				Reporting.getLogger()
						.log(LogStatus.FAIL, "Exception occured: " + stepDescription, "For details refer "
								+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
			else
				Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while getting Index from given array:",
						"For details refer " + new Logger().writeExceptionLogs(
								"Exception occured while getting Index from given array:", getExceptionDetails(e))
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}
		return pos;
	}

	@Override
	public boolean waitUntilElementIsInVisible(String stepDescription, String properties, long timeOutInSeconds,
			boolean toReport) {
		boolean flag = false;
		if (verifyElementInvisibility(properties, timeOutInSeconds)) {
			flag = true;
		}

		else {
			if (toReport) {
				if (!(stepDescription == null || stepDescription.isEmpty())) {

					Reporting.getLogger().log(LogStatus.FAIL, stepDescription, "Element is visible on page"
							+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				} else {
					Reporting.getLogger().log(LogStatus.FAIL, "Element is visible on page",
							"For details refer " + new Logger().writeExceptionLogs("Waiting for element Invisibility",
									"Element is still visible on page" + Reporting.getLogger()
											.addScreenCapture(Reporting.createScreenshot(getDriver()))));
				}
			}
		}
		return flag;

	}

	@Override
	public boolean moveToElement(String stepDescription, String properties, boolean toReport) {
		boolean bFlag = false;
		try {
			List<WebElement> elements = getWebElementList(stepDescription, properties);

			for (WebElement element : elements) {
				viewPortElement(element);
				if (visibilityofWebelement(element)) {
					// if (toReport)
					// if (!(stepDescription == null ||
					// stepDescription.isEmpty()))
					// Reporting.getLogger().log(LogStatus.PASS,
					// stepDescription,
					// " Element exists on the page");
					bFlag = true;
					break;
				}

			}

			if (!bFlag)
				if (toReport)
					if (!(stepDescription == null || stepDescription.isEmpty()))
						Reporting.getLogger().log(LogStatus.FAIL, stepDescription, " Unable to identify the field : "
								+ stepDescription
								+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
					else
						Reporting.getLogger().log(LogStatus.FAIL, "Move to element on page",
								" Unable to identify the field, for details refer " + Reporting.getLogger()
										.addScreenCapture(Reporting.createScreenshot(getDriver())));

			return bFlag;

		} catch (TimeoutException t) {

		} catch (Exception e) {
			if (toReport)
				if (!(stepDescription == null || stepDescription.isEmpty()))
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured: " + stepDescription,
							"For details refer "
									+ new Logger().writeExceptionLogs(stepDescription, getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
				else
					Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while moving to the  element",
							"For details refer "
									+ new Logger().writeExceptionLogs("Exception occured while moving to the element",
											getExceptionDetails(e))
									+ Reporting.getLogger().addScreenCapture(Reporting.createScreenshot(getDriver())));
		}

		return false;

	}

	@Override
	public List<String> getDropdownOptions(String stepDescription, String properties) {
		// TODO Auto-generated method stub
		return null;
	}
}
