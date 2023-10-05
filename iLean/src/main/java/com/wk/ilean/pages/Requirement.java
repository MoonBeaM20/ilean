package com.wk.ilean.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wk.ilean.common.PageGenerics;
import com.wk.ilean.common.UtilLib;
import com.wk.ileanframework.controlsLibrary.webControls.WebControlsLibrary;

public class Requirement extends PageGenerics {

	private static Properties RequirementOR = null;
	private static final String config = "config";
	String legalRqmt = "Demo_LegalRqmt";

	public Requirement(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "Requirement";
		this._webcontrols = _webcontrols;
		if (RequirementOR == null) {
			RequirementOR = UtilLib.setPropertyObject("Requirement");
		}

	}

	public boolean createRequirement() {
		boolean flag=false;
		_webcontrols.get().click("Click on Requirement tab", RequirementOR.getProperty("tooltipRequirement"));
		_webcontrols.get().waitUntilElementIsVisible("Wait for Requirement summary page table",
				RequirementOR.getProperty("firstRowRqmt"), timeOut);
		_webcontrols.get().click("Click on Requirement name in summary search table",
				RequirementOR.getProperty("firstRowRqmt"));
		
		_webcontrols.get().waitUntilElementIsVisible("Wait for View Legal Requirement page ",
				RequirementOR.getProperty("viewLegalRqmtHeader"), timeOut, true);
		reportPass("User is navigate to view legal requirements page",
				"User should navigate to view legal requirements page", pageNameForExecutionReport);
		_webcontrols.get().click("Click on 'Add New' button", RequirementOR.getProperty("addNewButton"));
		_webcontrols.get().waitUntilElementIsVisible("Wait for Legal Requirement page ",
				RequirementOR.getProperty("newRqmtHeader"), timeOut, true);
		reportPass("User is navigate to legal requirement page", "User should navigate to legal requirement page",
				pageNameForExecutionReport);
		
		_webcontrols.get().click("Click on Primary topic", RequirementOR.getProperty("primaryTopic"));
		_webcontrols.get().click("Select first element of primary topic dropdown",
				RequirementOR.getProperty("firstElement"));
		
         String RandomContext = UtilLib.getRandomString("contextname");
         contextname = RandomContext;
		_webcontrols.get().enterText("Context new text one", RequirementOR.getProperty("context1"),RandomContext);
		_webcontrols.get().click("Click add new context", RequirementOR.getProperty("addnewcontext"));
		
		_webcontrols.get().enterText("Context text one", RequirementOR.getProperty("contextname"),RandomContext);
		_webcontrols.get().click("Click Save context", RequirementOR.getProperty("contextsave"));
		
		
		  _webcontrols.get().click("Click on Context1 drop down",RequirementOR.getProperty("context1CaretDown"));
		  
		  _webcontrols.get().
		  waitUntilElementIsVisible("Wait context1 for suggestions list",
		  RequirementOR.getProperty("recontext"), timeOut);
		  _webcontrols.get().click("Select first element of Context1 drop down",
		  RequirementOR.getProperty("recontext"));
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     _webcontrols.get().selectDropDown("Selected value", RequirementOR.getProperty("status"),"Validated");
	     

		_webcontrols.get().enterText("Enter release notes", RequirementOR.getProperty("releaseNotes"),
				"Demo_relaseNotes");
		//String getRandomRequirement=UtilLib.getRandomString("Requirement");
		//requirementName=getRandomRequirement;
		_webcontrols.get().enterText("Enter party", RequirementOR.getProperty("partyTxt"),"Account Holder");

		_webcontrols.get().clickUsingJSExecutor("Click on 'Subsection Reference'", RequirementOR.getProperty("subSectionReference"));
		reportPass("Click on 'Subsection Reference","Sucessfully clicked on Subsection Reference section", pageNameForExecutionReport);
		// switch to frame
		_webcontrols.get().switchToIframe("Switch To Frame for Legal requirement",
				RequirementOR.getProperty("legalRqmtFrame"));

		_webcontrols.get().enterText("Enter legal requirement ", RequirementOR.getProperty("legalRqmtBody"), legalRqmt);
		_webcontrols.get().deselectIframe("Switch back to default frame");

		//_webcontrols.get().enterText("Enter Expir Date ", RequirementOR.getProperty("expirDate"), "26-08-2022");
		
		if(_webcontrols.get().clickUsingJSExecutor("Click on Save button", RequirementOR.getProperty("btnSave"))) {
			flag= true;
			
			_webcontrols.get().waitUntilElementIsVisible("Wait for View legal requirements page",
					RequirementOR.getProperty("viewLegalRqmtHeader"), timeOut);
			reportPass("Click on Save button",
					"Sucessfully new requiremnet got created", pageNameForExecutionReport);
		}
		
		/*
		 * //Delete rqmt String EditBtn="//p[text()='"+
		 * legalRqmt+"']/following::button[text()='Edit']";
		 * _webcontrols.get().getDriver().findElement(By.xpath(EditBtn)).click();
		 * reportPass("Click on Edit button", "Sucessfully clicked on Edit button",
		 * pageNameForExecutionReport);
		 * 
		 * _webcontrols.get().waitUntilElementIsVisible("Wait for Delete button",
		 * RequirementOR.getProperty("btnDelte"), timeOut);
		 * _webcontrols.get().clickUsingJSExecutor("Click on 'Delete' button",
		 * RequirementOR.getProperty("btnDelte")); reportPass("Click on Delete button",
		 * "Sucessfully clicked on Delete button", pageNameForExecutionReport);
		 * 
		 * 
		 * _webcontrols.get().clickUsingJSExecutor("Click on 'Ok' button",
		 * RequirementOR.getProperty("btnOk")); reportPass("Click on OK button",
		 * "Sucessfully clicked on OK button", pageNameForExecutionReport);
		 * 
		 * try { Thread.sleep(5000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 * 
		 * _webcontrols.get().
		 * waitUntilElementIsVisible("Wait for View legal requirements page",
		 * RequirementOR.getProperty("viewLegalRqmtHeader"), timeOut);
		 * reportPass("Verify Requirement is deleted or not",
		 * "Sucessfully new requiremnet is deleted", pageNameForExecutionReport);
		 */
		
		return flag;

	}
	 
	public boolean createBinder() {
		boolean flag=false;
		
		_webcontrols.get().click("Click on Binder tab", RequirementOR.getProperty("tooltipBinder"));
		_webcontrols.get().waitUntilElementIsVisible("Wait for Binder page",
				RequirementOR.getProperty("toggleRowRight"), timeOut);
		_webcontrols.get().clickUsingJSExecutor("Click on 'right arrow'", RequirementOR.getProperty("toggleRowRight"));	
		_webcontrols.get().clickUsingJSExecutor("Click on 'first cluster'", RequirementOR.getProperty("firstChkBox"));
		_webcontrols.get().click("Click on Add binder icon",
				RequirementOR.getProperty("binderAddBtn"));
		_webcontrols.get().enterText("Enter Binder name ", RequirementOR.getProperty("binderNameTxt"), "BinderDemo");	
		_webcontrols.get().click("Click on Save button",
				RequirementOR.getProperty("binderSaveBtn"));
		
		
		return true;
		
	}
	public boolean versionhistoryRequirement() {
		boolean flag=false;
		_webcontrols.get().click("Click on Requirement tab", RequirementOR.getProperty("tooltipRequirement"));
		/*
		 * _webcontrols.get().
		 * waitUntilElementIsVisible("Wait for Requirement summary page table",
		 * RequirementOR.getProperty("firstRowRqmt"), timeOut);
		 * _webcontrols.get().click("Click on Requirement name in summary search table",
		 * RequirementOR.getProperty("firstRowRqmt"));
		 * 
		 * _webcontrols.get().
		 * waitUntilElementIsVisible("Wait for View Legal Requirement page ",
		 * RequirementOR.getProperty("viewLegalRqmtHeader"), timeOut, true);
		 * reportPass("User is navigate to view legal requirements page",
		 * "User should navigate to view legal requirements page",
		 * pageNameForExecutionReport);
		 */
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_webcontrols.get().click("Select first checkbox", RequirementOR.getProperty("firstCheckBoxVH"));
		//_webcontrols.get().clickUsingJSExecutor("Click on first checkbox", RequirementOR.getProperty("firstCheckBoxVH"));
		_webcontrols.get().click("Click on version history", RequirementOR.getProperty("versionHistoryButton"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	_webcontrols.get().clickUsingJSExecutor("Click on first version", RequirementOR.getProperty("versionfirstview"));
	//	_webcontrols.get().clickUsingJSExecutor("Click on version view", RequirementOR.getProperty("versionview"));
	//	_webcontrols.get().clickUsingJSExecutor("Click on second version", RequirementOR.getProperty("versionsecondview"));
		return true;
}
	public boolean clickRequirement() throws InterruptedException {
		boolean flag=false;
		Thread.sleep(10000);
		_webcontrols.get().click("Click on Requirement tab", RequirementOR.getProperty("tooltipRequirement"));
		//_webcontrols.get().waitUntilElementIsVisible("Wait for Cluster page",ClusterPageOR.getProperty("clusterarrow"), timeOut);
		return true;
	}
	public boolean verifyrequirementpage() throws InterruptedException
	{
		
			Thread.sleep(10000);
		//	_webcontrols.get().click("Click on Requirement tab", RequirementOR.getProperty("tooltipRequirement"));
			//Thread.sleep(1000);
			//_webcontrols.get().click("Click on Cluster tab", ClusterPageOR.getProperty("ClusterTab"));
		//_webcontrols.get().waitUntilElementIsVisible("Wait for Cluster page",
			//	ClusterPageOR.getProperty("clusterarrow"), timeOut);
		_webcontrols.get().click("Select first checkbox", RequirementOR.getProperty("firstChkBox"));
		_webcontrols.get().waitUntilElementIsVisible("wait Setting capability tab ", RequirementOR.getProperty("settingapp"),
				timeOut);
		reportPass("Verify Add icon is display", "Add Icon is displayed",
				pageNameForExecutionReport);
	
		_webcontrols.get().waitUntilElementIsVisible("wait export icon tab ", RequirementOR.getProperty("exporticon"),
				timeOut);
		reportPass("Verify export icon is display", "Export Icon is displayed",
				pageNameForExecutionReport);
		_webcontrols.get().waitUntilElementIsVisible("wait default tab ", RequirementOR.getProperty("defaulticon"),
				timeOut);
		reportPass("Verify default is display", "Default Icon is displayed",
				pageNameForExecutionReport);
		_webcontrols.get().waitUntilElementIsVisible("wait Tag review icon tab ", RequirementOR.getProperty("tagreview"),
				timeOut);
		reportPass("Verify Tagreview is display", "Tag review Icon is displayed",
				pageNameForExecutionReport);
		_webcontrols.get().waitUntilElementIsVisible("wait version history icon tab ", RequirementOR.getProperty("versionHistoryButton"),
				timeOut);
		reportPass("Verify version history is display", "Add version history is displayed",
				pageNameForExecutionReport);
		Thread.sleep(1000);
		_webcontrols.get().click("Click on version history", RequirementOR.getProperty("versionHistoryButton"));
		Thread.sleep(1000);
		_webcontrols.get().click("Click on version history view", RequirementOR.getProperty("versionhistoryview2"));
	
		
		
		return true;
		
		
		
	}
}
