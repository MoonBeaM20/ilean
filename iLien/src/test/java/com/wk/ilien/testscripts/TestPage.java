package com.wk.ilien.testscripts;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilien.pages.GlobalSearchPage;
import com.wk.ilien.pages.HomePage;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilien.pages.Requirement;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILienControls;
import com.wk.ilienframework.reporting.Reporting;

public class TestPage extends ILienControls {

	PageGenerics pg = new PageGenerics();
	//String userPassword = UtilLib.readSpecificColumnValue(pg.getTestData(), "WK Internal Account", 1);
	String userPassword = UtilLib.readSpecificColumnValue(pg.getTestData(), "WK External Account", 1);
	String[] userDetails = userPassword.split("/");
	
	String user = userDetails[0];
	String pwd = userDetails[1];

	String currentPassword = pwd;
	String newPassword = "Test@1234";

	@Test(priority = 1)
	public void loginToProvisoApplication() throws AWTException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>",
				"<b>Verify User is able to Login ProViso application</b>");

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);

		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");

	}

	@Test(priority = 2)
	public void logOutFromProvisoApplication() throws AWTException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:03</b>",
				"<b>Verify User is able to LogOut ProViso application</b>");

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);
		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");

		Assert.assertTrue(logInPage.logOutFromProViso(), "Unable to logOut Proviso application");

	}

	@Test(priority = 3)
	public void verifyGlobalSearch() throws AWTException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:02</b>",
				"<b>Verify User is able to search in global search</b>");

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);
		GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);

		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
		Assert.assertTrue(gs.globalSearchResult(), "Unable to do global search");

	}

	@Test(priority = 4)
	public void changePasswordInProvisoApplication() throws AWTException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:04</b>",
				"<b>Verify User is able to change password in ProViso application</b>");

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);

		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
		Assert.assertTrue(logInPage.changePassword(currentPassword, newPassword),
				"Unable to change password Proviso application");
		Assert.assertTrue(logInPage.changePassword(newPassword, currentPassword),
				"Unable update the password to previous one");

	}

	@Test(priority = 5)
	public void verifyHomepageSections() throws AWTException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:05</b>",
				"<b>Verify homepage differents Sections in ProViso application</b>");

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);
		HomePage homePage = new HomePage(_webcontrols);

		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");

		Assert.assertTrue(homePage.homePageSections(user), "Unable to verify home page sections");

	}

	/*
	 * @Test(priority = 6) public void verifyAddNewAndDeleteRequiement() throws
	 * AWTException { // info Reporting.getLogger().log(LogStatus.INFO,
	 * "<b>Test Case ID:06</b>",
	 * "<b>Verify User is able to add new Requirement</b>");
	 * 
	 * String user="testAdminUser@wolterskluwer.com";
	 * 
	 * // Page Objects LoginPage logInPage = new LoginPage(_webcontrols);
	 * GlobalSearchPage gs = new GlobalSearchPage(_webcontrols); Requirement rp =
	 * new Requirement(_webcontrols);
	 * 
	 * Assert.assertTrue(logInPage.loginToProViso(user, pwd),
	 * "Unable to login Proviso application");
	 * Assert.assertTrue(gs.globalSearchResult(), "Unable to do global search");
	 * Assert.assertTrue(rp.createRequirement(),
	 * "Unable to create new requirement");
	 * 
	 * Reporting.getLogger().log(LogStatus.INFO,
	 * "<b>Test Case ID:06</b>","<b>End test case</b>");
	 * 
	 * }
	 * 
	 * @Test(priority = 7) public void verifyAddBinder() throws AWTException { //
	 * info Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:06</b>",
	 * "<b>Verify User is able to add new Binder</b>");
	 * 
	 * String user="testUser1@usbank.com";
	 * 
	 * // Page Objects LoginPage logInPage = new LoginPage(_webcontrols);
	 * GlobalSearchPage gs = new GlobalSearchPage(_webcontrols); Requirement rp =
	 * new Requirement(_webcontrols);
	 * 
	 * Assert.assertTrue(logInPage.loginToProViso(user, pwd),
	 * "Unable to login Proviso application");
	 * Assert.assertTrue(gs.globalSearchResult(), "Unable to do global search");
	 * Assert.assertTrue(rp.createBinder(), "Unable to create new Binder");
	 * 
	 * Reporting.getLogger().log(LogStatus.INFO,
	 * "<b>Test Case ID:06</b>","<b>End test case</b>");
	 * 
	 * }
	 */

}
