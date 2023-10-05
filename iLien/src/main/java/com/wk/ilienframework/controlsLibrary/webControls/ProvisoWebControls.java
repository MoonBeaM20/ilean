package com.wk.ilienframework.controlsLibrary.webControls;

import java.util.List;
import java.util.Set;

import javax.xml.transform.TransformerException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wk.ilienframework.controlsLibrary.utilities.PropFileHandler;

public interface ProvisoWebControls {

	/**
	 * 
	 * Used to get the driver:-
	 */
	public WebDriver getDriver();

	public void launchUrl(String stepDescription, String url);

	public boolean enterText(String stepDescription, String properties, String textToEnter);

	public boolean clearTextBox(String stepDescription, String properties);

	public boolean click(String stepDescription, String properties);

	public boolean doubleClick(String stepDescription, String properties);

	public boolean rightClick(String stepDescription, String properties);

	public boolean pressEnter(String stepDescription, String properties);
	
	public boolean pressEsc(String stepDescription, String properties);

	public boolean pressTabKey(String stepDescription, String properties);

	public boolean selectDropDown(String stepDescription, String properties, String visibleText);

	public boolean selectDropDownValueByIndex(String stepDescription, String properties, int index);

	public String getText(String stepDescription, String properties);

	public String getTextValueFromTextBoxOrDropBox(String stepDescription, String properties);

	public boolean clickUsingActionBuilder(String stepDescription, String properties);

	public String getValueOfAttribute(String stepDescription, String properties, String attribute);

	public boolean isTextPresent(String stepDescription, String properties, String text);

	public List<WebElement> getWebElementsList(String stepDescription, String properties);

	public List<String> getTextFromWebElementList(String stepDescription, String properties);

	public String getPageTitle(String stepDescription);

	public boolean isElementExists(String stepDescription, String properties, boolean toReport);

	public boolean switchAndAcceptAlert(String stepDescription, boolean toReport);

	public boolean switchAndCancelAlert(String stepDescription, boolean toReport);

	public String switchAndGetTextFromAlert(String stepDescription);

	public boolean waitUntilElementIsVisible(String stepDescription, String properties, long timeOutInSeconds);

	public boolean waitUntilElementIsInVisible(String stepDescription, String properties, long timeOutInSeconds);
	
	public boolean waitUntilElementIsInVisible(String stepDescription, String properties, long timeOutInSeconds,boolean toReport);

	public boolean waitUntilElementIsVisible(String stepDescription, String properties, long timeOutInSeconds, boolean toReport); 
	
	public boolean waitUntilElementToBeClickable(String stepDescription, String properties);

	public boolean areElementsPresent(String stepdDescription, String properties);

	public String getCurrentWindowHandle(String stepDescription);

	public Set<String> getAllWindowHandles(String stepDescription);

	public boolean switchToWindow(String stepDescription, String previousWindowHandel);

	public void switchToNewWindow(String stepDescription, int i);

	public void closeWindow(String stepDescription, int i, boolean toReport);

	public void refreshPage();

	public void navigateToPreviousPage(String stepDescription);

	public void mouseHover(String stepDescription, String properties);

	public void mouseHoverClickChild(String stepDescription, String parentProperties, String childProperties);

	public String getPageSource(String stepDescription, boolean toReport);

	public void deleteAllCookies(String stepDescription);

	public String getCurrentUrl(String stepDescription);

	public void resetImplicitWaitToDefault();

	public void resetImplicitWait(String stepDescription, int newWaitTime_InSeconds);

	public void switchToIframe(String stepDescription);

	public void switchToIframeById(String stepDescription, String iFrameId);

	public void switchToIframeByIndex(String stepDescription,int frameIndex);

	public boolean switchToIframe(String stepDescription,String properties);

	public void deselectIframe(String stepDescription);

	public void executeJavaScript(String stepDescription, String jScript);
	
	public boolean enterTextUsingJSExecutor(String stepDescription, String properties, String textToEnter);

	public boolean clickUsingJSExecutor(String stepDescription, String properties);
	
	public void waitFor(int enterMiliSeconds);

	public void waitForPageLoad(String stepDescription);

	public void quitDriver();

	public void closeCurrentWindow(String stepDescription);

	public PropFileHandler propFileHandler();

	public List<String> getDropdownOptions(String stepDescription, String properties);

	public boolean verifyTitle(String stepDescription,String title);
	
	public void contextMenuClick(String stepDescription, String parentProperties, String childProperties);
	
	public boolean customDropDownselect(String stepDescription, String txtboxprop,String optionProp,String optValue);
	
	public String getCSSProperty(String stepDescription, String properties,String cssValue);
	
	public boolean customDropDownselectWitoutEdit(String stepDescription, String txtboxprop,String optionProp);
	
	public void scrollPagedown(String stepDescription, String properties);
	
	public String getCurrentMonthNumber(String stepDescription);
	
	public String getCurrentMonth(String stepDescription);
		
	public String getCurrentYear(String stepDescription);

	boolean doubleClickUsingJSExecutor(String stepDescription, String properties);
	
	public boolean moveToElement(String stepDescription, String properties, boolean toReport);
}
