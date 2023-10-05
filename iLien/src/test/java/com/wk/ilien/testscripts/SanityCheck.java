package com.wk.ilien.testscripts;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.pages.BinderPage;
import com.wk.ilien.pages.ClusterPage;
import com.wk.ilien.pages.GlobalSearchPage;
import com.wk.ilien.pages.HomePage;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilien.pages.Requirement;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ilienframework.reporting.Reporting;

public class SanityCheck extends ILeanControls  {
	PageGenerics pg = new PageGenerics();
	String pwd = "Test@123";
	
	@Test(priority = 3)
	public void activityreportInProvisoApplication() throws AWTException, InterruptedException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:04</b>",
				"<b>Verify User is able to change password in ProViso application</b>");
		String user="admin.expert@abcbank.com";
		

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);
		
		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
		Assert.assertTrue(logInPage.Activityreport(), "Unable to login Proviso application");

		}
	
	@Test(priority = 1)
	public void verifyHomepageSections() throws AWTException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:05</b>",
				"<b>Verify homepage differents Sections in ProViso application</b>");
		String user="admin.expert@abcbank.com";

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);
		HomePage homePage = new HomePage(_webcontrols);

		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");

		Assert.assertTrue(homePage.homePageSections(user), "Unable to verify home page sections");

	}
	
	/*
	 * @Test(priority = 3) public void verifyGlobalSearch() throws AWTException { //
	 * info Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:02</b>",
	 * "<b>Verify User is able to search in global search</b>"); String
	 * user="admin.expert@abcbank.com";
	 * 
	 * // Page Objects LoginPage logInPage = new LoginPage(_webcontrols);
	 * GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);
	 * 
	 * Assert.assertTrue(logInPage.loginToProViso(user, pwd),
	 * "Unable to login Proviso application");
	 * Assert.assertTrue(gs.globalSearchEverything(), "Unable to do global search");
	 * 
	 * }
	 */
	
	
	  @Test(priority = 2) 
	  public void GlobalSearch() throws AWTException {
		  // info
	  Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:02</b>",
	  "<b>Verify User is able to search in global search</b>"); 
	  String user="admin.expert@abcbank.com";
	  
	  // Page Objects
	  LoginPage logInPage = new LoginPage(_webcontrols);
	  GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);
	  
	  Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
	  Assert.assertTrue(gs.globalSearchResult(), "Unable to do global search");
	 

	}
	
	@Test(priority = 4)
	public void requirementverify() throws AWTException, InterruptedException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:02</b>",
				"<b>Verify User is able to search in global search</b>");
		String user="admin.expert@abcbank.com";

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);
		GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);
		HomePage homePage = new HomePage(_webcontrols);
        Requirement rp = new Requirement(_webcontrols);

		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
		Assert.assertTrue(gs.globalSearchResultbyTopic(), "Unable to do global search");
		Assert.assertTrue(rp.clickRequirement(), "Unable to create new requirement");
		Assert.assertTrue(rp.verifyrequirementpage(), "Unable to create new requirement");

	}
	
	@Test(priority = 5)
	public void clusterflow() throws AWTException, InterruptedException {
		
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
				Assert.assertTrue(gs.globalSearchResultbyTopic(), "Unable to do global search");
				Assert.assertTrue(cp.clickCluster(), "Unable to  edit cluster");
				Assert.assertTrue(cp.verifyclusterpage(), "Unable to create new cluster");
				Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");		

			}
	
   @Test(priority = 6)
	public void binderverify() throws AWTException, InterruptedException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:02</b>",
				"<b>Verify User is able to search in global search</b>");
		String user="admin.expert@abcbank.com";

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);
		GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);
		HomePage homePage = new HomePage(_webcontrols);
        Requirement rp = new Requirement(_webcontrols);
        ClusterPage cp = new ClusterPage(_webcontrols);
		BinderPage bp = new BinderPage(_webcontrols);

		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
		Assert.assertTrue(gs.globalSearchResultbyTopicforBinder(), "Unable to do global search");
		Assert.assertTrue(bp.clickBinder(), "Unable to create new requirement");
		Assert.assertTrue(bp.verifybinderpage(), "Unable to create new requirement");
	

	}
   @Test(priority = 7)
  	public void bindercreation() throws AWTException, InterruptedException {
  		// info
  		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:02</b>",
  				"<b>Verify User is able to search in global search</b>");
  		String user="admin.expert@abcbank.com";

  		// Page Objects
  		LoginPage logInPage = new LoginPage(_webcontrols);
  		GlobalSearchPage gs = new GlobalSearchPage(_webcontrols);
  		HomePage homePage = new HomePage(_webcontrols);
          Requirement rp = new Requirement(_webcontrols);
          ClusterPage cp = new ClusterPage(_webcontrols);
  		BinderPage bp = new BinderPage(_webcontrols);

  		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");
  		Assert.assertTrue(gs.globalSearchResultbyTopicforBinder(), "Unable to do global search");
  		Assert.assertTrue(bp.createBinder(), "Unable to create new Binder");
  	
  	}
   @Test(priority = 8)
	public void verifyCMDSections() throws AWTException, InterruptedException {
		// info
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:05</b>",
				"<b>Verify homepage differents Sections in ProViso application</b>");
		String user="admin.expert@abcbank.com";

		// Page Objects
		LoginPage logInPage = new LoginPage(_webcontrols);
		HomePage homePage = new HomePage(_webcontrols);

		Assert.assertTrue(logInPage.loginToProViso(user, pwd), "Unable to login Proviso application");

		Assert.assertTrue(homePage.verifycmdpage(), "Unable to CMD page sections");

	}
   
}
