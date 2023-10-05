package com.wk.ilean.testscripts;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilean.common.PageGenerics;
import com.wk.ilean.common.UtilLib;
import com.wk.ilean.pages.BinderPage;
import com.wk.ilean.pages.GlobalSearchPage;
import com.wk.ilean.pages.LoginPage;
import com.wk.ileanframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ileanframework.reporting.Reporting;


public class Binder extends ILeanControls {
	PageGenerics pg = new PageGenerics();
	// String userPassword = UtilLib.readSpecificColumnValue(pg.getTestData(), "WK
	// Internal Account", 1);
	String userPassword = UtilLib.readSpecificColumnValue(pg.getTestData(), "WK External Account", 1);
	String[] userDetails = userPassword.split("/");
	String user = userDetails[0];
	String pwd = userDetails[1];

	String currentPassword = pwd;
	String newPassword = "Test@1234";
	


	@Test(priority = 1)
	public void BinderFlow() throws AWTException, InterruptedException {
		
		// info
				Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>",
						"<b>Verify User is able to add new Requirement</b>");
				
				//String user="test1@wf.com";
				String user="testuser@usbank.com";
				
				// Page Objects
				LoginPage logInPage = new LoginPage(_webcontrols);
				GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);
	            BinderPage bp = new BinderPage(_webcontrols);
				

				Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
				
				//gs.globalSearchResult();
				Assert.assertTrue(gs.globalSearchResultbyTopicforBinder(), "Unable to do global search");
				
				Assert.assertTrue(bp.createBinder(), "Unable to create new Binder");
				//Assert.assertTrue(bp.editBinder(),"Unable to edit Binder");
				Assert.assertTrue(bp.deleteBinder(),"Unable to delete Binder");
				
				//Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
				
				
				 Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");		

	}
}