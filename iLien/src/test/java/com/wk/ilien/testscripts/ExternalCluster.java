package com.wk.ilien.testscripts;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilien.pages.ClusterPage;
import com.wk.ilien.pages.ExternalFlow;
import com.wk.ilien.pages.GlobalSearchPage;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilien.pages.Requirement;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ilienframework.reporting.Reporting;

public class ExternalCluster extends ILeanControls {
	
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
	public void ExternalClusterFlow() throws AWTException, InterruptedException {
		
		// info
				Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>",
						"<b>Verify User is able to add new Requirement</b>");
				
				//String user="testAdminUser@wolterskluwer.com";
				String user="testuser@usbank.com";
				
				// Page Objects
				LoginPage logInPage = new LoginPage(_webcontrols);
				GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);
				//Requirement rp = new Requirement(_webcontrols);
				ClusterPage cp = new ClusterPage(_webcontrols);
				ExternalFlow ef = new ExternalFlow(_webcontrols);
				

				Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
				
				//gs.globalSearchResult();
				Assert.assertTrue(gs.globalSearchResultbyTopic(), "Unable to do global search");
				//Assert.assertTrue(gs.globalSearchEverything(), "Unable to do global search");
				//Assert.assertTrue(ef.createmirrorcluster(), "Unable to create mirror");
				//cp.createCluster();
				//Assert.assertTrue(cp.createCluster(), "Unable to create new cluster");
			
				
				//Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
				
				
				 Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");		

			}
}



