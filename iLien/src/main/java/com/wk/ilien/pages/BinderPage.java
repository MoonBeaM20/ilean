package com.wk.ilien.pages;

import java.util.Properties;

import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilienframework.controlsLibrary.webControls.WebControlsLibrary;

public class BinderPage extends PageGenerics {
	
	private static Properties BinderPageOR = null;
	private static Properties RequirementOR = null;
	private static final String config = "config";
	String legalRqmt = "Demo_LegalRqmt";
	
	public  BinderPage(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "BinderPage";
		this._webcontrols = _webcontrols;
		if (BinderPageOR == null) {
			BinderPageOR = UtilLib.setPropertyObject("BinderPage");
		}
	}
  
	public boolean createBinder() throws InterruptedException {
		boolean flag=false;
		Thread.sleep(10000);
		_webcontrols.get().click("Click on Binder tab", BinderPageOR.getProperty("BinderTab"));
		_webcontrols.get().waitUntilElementIsVisible("Wait for Binder page",
				BinderPageOR.getProperty("binderarrow"), timeOut);
		_webcontrols.get().clickUsingJSExecutor("Click on 'unbound cluster'", BinderPageOR.getProperty("binderarrow"));	
		
		_webcontrols.get().click("Click on first check box",BinderPageOR.getProperty("selectfirstbindercheckbox"));
		_webcontrols.get().click("Click on Add Binder icon",BinderPageOR.getProperty("addnewbinder"));
		
		  String Bname = UtilLib.getRandomString("Binder"); 
		  binderName = Bname;
		 System.out.println(binderName);
		_webcontrols.get().enterText("Enter Binder name ", BinderPageOR.getProperty("title"), Bname);	
		_webcontrols.get().enterText("Enter Status ", BinderPageOR.getProperty("status"),"Review" );
		//_webcontrols.get().selectDropDown("Enter Status", BinderPageOR.getProperty("status"), "Draft");
		
	_webcontrols.get().click("Click on Save button",BinderPageOR.getProperty("bindersavebutton"));
		
		
		return true;
		
	}
	public boolean editBinder() {
		boolean flag=false;
	
		String bindernm = "xpath~//span[contains(text(),'"+binderName+"')]/ancestor::div[@class='row cluster-search-res']//input";
		_webcontrols.get().click("Click on checkbox",bindernm);
		_webcontrols.get().click("Click on Edit Icon",BinderPageOR.getProperty("editbinder"));
		_webcontrols.get().enterText("Enter Status ", BinderPageOR.getProperty("status"),"Approved" );
		
		_webcontrols.get().click("Click on Save button",BinderPageOR.getProperty("bindersavebutton"));
		//_webcontrols.get().click("Click on Submit button",ClusterPageOR.getProperty("submitbutton"));
		return true;	
	}
	public boolean deleteBinder() {
		boolean flag=false;
		String binNm = "xpath~//span[contains(text(),'"+binderName+"')]/ancestor::div[@class='row cluster-search-res']//input";
		_webcontrols.get().click("Click on checkbox",binNm);
		_webcontrols.get().click("Click on Delete Icon",BinderPageOR.getProperty("deletebinder"));
		
		_webcontrols.get().click("Click on Submit button",BinderPageOR.getProperty("okbutton"));
		return true;
		
			
	}
	public boolean clickBinder() throws InterruptedException {
		boolean flag=false;
		Thread.sleep(10000);
		_webcontrols.get().click("Click on Cluster tab", BinderPageOR.getProperty("BinderTab"));
		//_webcontrols.get().waitUntilElementIsVisible("Wait for Cluster page",ClusterPageOR.getProperty("clusterarrow"), timeOut);
		return true;
	}
	public boolean verifybinderpage()
	{
		boolean flag=false;
		_webcontrols.get().click("Select first checkbox", BinderPageOR.getProperty("firstChkBox"));
		_webcontrols.get().waitUntilElementIsVisible("waitAdd icon tab ", BinderPageOR.getProperty("addnewbinder"),
				timeOut);
		reportPass("Verify Add icon is display", "Add cluster Icon is displayed",
				pageNameForExecutionReport);
	
		_webcontrols.get().waitUntilElementIsVisible("wait export icon tab ", BinderPageOR.getProperty("exporticon"),
				timeOut);
		reportPass("Verify export icon is display", "Export Icon is displayed",
				pageNameForExecutionReport);
		
		_webcontrols.get().waitUntilElementIsVisible("wait move icon tab ", BinderPageOR.getProperty("moveicon"),
				timeOut);
		reportPass("Verify move icon is display", "Move Icon is displayed",
				pageNameForExecutionReport);
		
		_webcontrols.get().waitUntilElementIsVisible("wait backward icon tab ", BinderPageOR.getProperty("backwardarrow"),
				timeOut);
			reportPass("Verify backward icon is display", "Backward Icon is displayed",
				pageNameForExecutionReport);
	
		_webcontrols.get().waitUntilElementIsVisible("wait previous icon tab ", BinderPageOR.getProperty("previousarrow"),
					timeOut);
		reportPass("Verify previous icon is display", "previous Icon is displayed",
					pageNameForExecutionReport);
		_webcontrols.get().waitUntilElementIsVisible("wait next icon tab ", BinderPageOR.getProperty("nextarrow"),
						timeOut);
		 reportPass("Verify next icon is display", "next Icon is displayed",
						pageNameForExecutionReport);
		 _webcontrols.get().waitUntilElementIsVisible("wait forward icon tab ", BinderPageOR.getProperty("forwardarrow"),
					timeOut);
	 reportPass("Verify forward icon is display", "forward Icon is displayed",
					pageNameForExecutionReport);
		
		_webcontrols.get().waitUntilElementIsVisible("wait version history icon tab ",BinderPageOR.getProperty("versionhistory"),
				timeOut);
		reportPass("Verify version history is display", "Add version history is displayed",
				pageNameForExecutionReport);
		_webcontrols.get().click("Click on version history", BinderPageOR.getProperty("versionhistory"));
		return true;
		
	}
}

