package com.wk.ilien.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilienframework.controlsLibrary.webControls.WebControlsLibrary;

public class LoginPage extends PageGenerics {

	// WebDriver _webcontrols;
	private static Properties loginPageOR = null;
	private static final String config = "config";
	
	public LoginPage(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "Login Page";
		this._webcontrols = _webcontrols;
		if (loginPageOR == null) {
			loginPageOR = UtilLib.setPropertyObject("LoginPage");
		}

	}

	public boolean loginToProViso(String uname, String pwd) throws AWTException {
		
		

		_webcontrols.get().launchUrl("Launch application", "https://qa-proviso.wolterskluwer.com");

		reportPass("Login to Proviso application", "Sucessfully navigate to Proviso application", "loginPage");

		_webcontrols.get().enterText("Enter user name", loginPageOR.getProperty("txtUserName"), uname);

		_webcontrols.get().enterText("Enter password", loginPageOR.getProperty("txtPassword"), pwd);

		if (_webcontrols.get().click("Clicked on Sign in button", loginPageOR.getProperty("btnSignIn"))) {
			reportPass("Click on Sign in button in Proviso application", "Sucessfully clicked on sign in button", "loginPage");
			if (_webcontrols.get().waitUntilElementIsVisible("Sucessfully redirected to home page",
					loginPageOR.getProperty("homePageExpand"), timeOut, true)) {
				reportPass("Verify user in home page ", "Sucessfully navigate to Proviso home page", "homepage");

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;

			}
		}
		//_webcontrols.get().getDriver().findElement(By.xpath("body")).sendKeys(Keys.CONTROL+"-");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SUBTRACT);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_SUBTRACT);
		return false;

	}

	public boolean logOutFromProViso() {
		boolean flag = false;
		_webcontrols.get().waitUntilElementIsVisible("Wait for expand icon", loginPageOR.getProperty("homePageExpand"),
				120);
		_webcontrols.get().click("Clicked on expand icon in home page", loginPageOR.getProperty("homePageExpand"));
		_webcontrols.get().click("Clicked on logOut button in home page", loginPageOR.getProperty("logOutBtn"));
		if (_webcontrols.get().waitUntilElementIsVisible("Wait Sign in page user namet text field",
				loginPageOR.getProperty("txtUserName"), timeOut, false)) {
			reportPass("Logout from Proviso application", "Sucessfully logout from Proviso application", "loginPage");
			flag = true;
		}

		return flag;

	}
	
	public boolean changePassword(String currentPwd,String newPwd) {
		boolean flag = false;
		_webcontrols.get().waitUntilElementIsVisible("Wait for expand icon", loginPageOR.getProperty("homePageExpand"),
				120);
		_webcontrols.get().click("Clicked on expand icon in home page", loginPageOR.getProperty("homePageExpand"));
		
		_webcontrols.get().click("Clicked on change password in menu", loginPageOR.getProperty("changePwdBtn"));
		_webcontrols.get().enterText("Enter Current password", loginPageOR.getProperty("currentPwdTxt"),currentPwd);
		_webcontrols.get().enterText("Enter New password", loginPageOR.getProperty("newPwdTxt"),newPwd);
		_webcontrols.get().enterText("Re-enter the new password", loginPageOR.getProperty("confirmPwdTxt"),newPwd);
		_webcontrols.get().click("Clicked on change password button", loginPageOR.getProperty("changePwdBtn"));
		
		if(_webcontrols.get().waitUntilElementIsInVisible("Wait for change password button be invisible", loginPageOR.getProperty("changePwdBtn"),timeOut,false)){
			reportPass("Verify Change the password in Proviso application", "Sucessfully changed the paswword i.e. "+newPwd, "loginPage");
			flag = true;
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}

	public boolean Activityreport() throws InterruptedException
	{
		boolean flag = true;
		_webcontrols.get().waitUntilElementIsVisible("Wait for expand icon", loginPageOR.getProperty("homePageExpand"),
				120);
		_webcontrols.get().click("Clicked on expand icon in home page", loginPageOR.getProperty("homePageExpand"));
		
		_webcontrols.get().click("Clicked on activiy report in menu", loginPageOR.getProperty("activityreport"));
		
		_webcontrols.get().selectDropDownValueByIndex("Select value from dropdown", loginPageOR.getProperty("entitytype"),1);
		//_webcontrols.get().selectDropDown("Select value from dropdown", loginPageOR.getProperty("entitytype"), "Cluster");
		
		_webcontrols.get().click("Clicked on export report", loginPageOR.getProperty("exportreport"));
		Thread.sleep(1000);
		_webcontrols.get().click("Clicked on close button", loginPageOR.getProperty("closebtn"));
		
		return flag;
		
	
	}
}
