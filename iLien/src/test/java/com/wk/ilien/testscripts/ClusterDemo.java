package com.wk.ilien.testscripts;

import java.awt.AWTException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilien.pages.BinderPage;
import com.wk.ilien.pages.ClusterPage;
import com.wk.ilien.pages.GlobalSearchPage;
import com.wk.ilien.pages.HomePage;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilien.pages.Requirement;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILienControls;
import com.wk.ilienframework.reporting.Reporting;

public class ClusterDemo extends ILienControls {
	PageGenerics pg = new PageGenerics();
	// String userPassword = UtilLib.readSpecificColumnValue(pg.getTestData(), "WK
	// Internal Account", 1);
	/*
	 * String userPassword = UtilLib.readSpecificColumnValue(pg.getTestData(),
	 * "WK External Account", 1); String[] userDetails = userPassword.split("/");
	 * 
	 * String user = userDetails[0]; String pwd = userDetails[1];
	 * 
	 * String currentPassword = pwd;
	 */
	String pwd = "Test@123";
	


	@Test(priority = 1)
	public void ClusterFlow() throws AWTException, InterruptedException {
		
		// info
				Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>",
						"<b>Verify User is able to add new Requirement</b>");
				
				String user="admin.expert@abcbank.com";
				
				// Page Objects
				LoginPage logInPage = new LoginPage(_webcontrols);
				GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);
				HomePage homePage = new HomePage(_webcontrols);

				Requirement rp = new Requirement(_webcontrols);
				ClusterPage cp = new ClusterPage(_webcontrols);
				BinderPage bp = new BinderPage(_webcontrols);
				

				Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
				//Assert.assertTrue(logInPage.Activityreport(), "Unable to login Proviso application");
				//Assert.assertTrue(gs.globalSearchResultbyTopicforBinder(), "Unable to do global search");
				//Assert.assertTrue(bp.createBinder(), "Unable to create new Binder");
				Assert.assertTrue(homePage.verifycmdpage(), "Unable to verify home page sections");
				
				//gs.globalSearchResult();
				//Assert.assertTrue(gs.globalSearchResultbyTopic(), "Unable to do global search");
				//Assert.assertTrue(bp.clickBinder(), "Unable to create new requirement");
				//Assert.assertTrue(bp.verifybinderpage(), "Unable to create new requirement");
				//Assert.assertTrue(rp.verifyrequirementpage(), "Unable to create new requirement");
				//cp.createCluster();
				//cp.verifyclusterpage();
				//Assert.assertTrue(cp.verifyclusterpage(), "Unable to create new cluster");
				//Assert.assertTrue(cp.clickCluster(), "Unable to  edit cluster");
				//Assert.assertTrue(cp.createCluster(), "Unable to create new cluster");
				
				//Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
				
				
				 Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");		

			}
	/*
	 * public void verifyAddCluster() throws AWTException { // info
	 * Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:06</b>",
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




