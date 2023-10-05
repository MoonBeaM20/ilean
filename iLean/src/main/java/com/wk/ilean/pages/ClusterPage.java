package com.wk.ilean.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.wk.ilean.common.PageGenerics;
import com.wk.ilean.common.UtilLib;
import com.wk.ileanframework.controlsLibrary.webControls.WebControlsLibrary;

public class ClusterPage extends PageGenerics {
	
	private static Properties ClusterPageOR = null;
	private static Properties RequirementOR = null;
	private static final String config = "config";
	String legalRqmt = "Demo_LegalRqmt";

	public void Requirement(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "Requirement";
		this._webcontrols = _webcontrols;
		if (RequirementOR == null) {
			RequirementOR = UtilLib.setPropertyObject("Requirement");
		}
	}


	public boolean clickCluster() throws InterruptedException {
		boolean flag=false;
		Thread.sleep(10000);
		_webcontrols.get().click("Click on Cluster tab", ClusterPageOR.getProperty("ClusterTab"));
		//_webcontrols.get().waitUntilElementIsVisible("Wait for Cluster page",ClusterPageOR.getProperty("clusterarrow"), timeOut);
		return true;
	}

	
	
	
	public boolean createCluster() throws InterruptedException {
		boolean flag=false;
		Thread.sleep(10000);
		//_webcontrols.get().click("Click on Cluster tab", ClusterPageOR.getProperty("ClusterTab"));
		//_webcontrols.get().waitUntilElementIsVisible("Wait for Cluster page",
				//ClusterPageOR.getProperty("clusterarrow"), timeOut);
		_webcontrols.get().clickUsingJSExecutor("Click on 'unclustered requirement'", ClusterPageOR.getProperty("clusterarrow"));	
		
		String dynamicXpath="xpath~//tbody/tr/td[3]//div/div/p[2]//a[contains(text(),'"+contextname+"')]//ancestor::tr/td[1]/input";
		System.out.println(dynamicXpath);

		_webcontrols.get().clickUsingJSExecutor("Click on 'first cluster'",dynamicXpath);
		_webcontrols.get().click("Click on Add Cluster icon",
				ClusterPageOR.getProperty("addcluster"));
		
		  String Cname = UtilLib.getRandomString("Cluster"); 
		  clusterName = Cname;
		 System.out.println(clusterName);
		_webcontrols.get().enterText("Enter Cluster name ", ClusterPageOR.getProperty("title"), Cname);	
		_webcontrols.get().enterText("Enter Status ", ClusterPageOR.getProperty("status"),"Draft" );
		
		/*
		 * JavascriptExecutor js = (JavascriptExecutor)_webcontrols.get().getDriver();
		 * js.executeScript("argumets[0].innerHTML=arguments[1]",
		 * ClusterPageOR.getProperty("groupingexplanation"), "test string");
		 */	
		_webcontrols.get().click("Click on Save button",
				ClusterPageOR.getProperty("clustersavebutton"));
		
		
		return true;
		
	}
	public  ClusterPage(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "ClusterPage";
		this._webcontrols = _webcontrols;
		if (ClusterPageOR == null) {
			ClusterPageOR = UtilLib.setPropertyObject("ClusterPage");
		}
	}

	public void clusterSearch() {
		_webcontrols.get().click("Click on search box",ClusterPageOR.getProperty("txt") );
	}
	
	public boolean editcluster() {
		boolean flag=false;
	
		
		String clustnm = "xpath~//div[contains(text(),'"+clusterName+"')]/ancestor::div[@class='row cluster-search-res']//input";
		_webcontrols.get().click("Click on checkbox",clustnm);
		//_webcontrols.get().click("Click on Edit Icon",ClusterPageOR.getProperty("editcluster"));
		//_webcontrols.get().enterText("Enter Status ", ClusterPageOR.getProperty("status"),"Approved" );
		
		//_webcontrols.get().click("Click on Save button",ClusterPageOR.getProperty("clustersavebutton"));
		//_webcontrols.get().click("Click on Submit button",ClusterPageOR.getProperty("submitbutton"));
		return true;	
	}
	public void deletecluster() {
		String clustnm = "xpath~//div[contains(text(),'"+clusterName+"')]/ancestor::div[@class='row cluster-search-res']//input";
		_webcontrols.get().click("Click on checkbox",clustnm);
		_webcontrols.get().click("Click on Move Icon",ClusterPageOR.getProperty("moveicon"));
		_webcontrols.get().click("Click on dropdown",ClusterPageOR.getProperty("movedropdown"));
		_webcontrols.get().click("Click on first radio button",ClusterPageOR.getProperty("movetovalue"));
		_webcontrols.get().click("Click on Move button",ClusterPageOR.getProperty("movebutton"));
		_webcontrols.get().click("Click on Submit button",ClusterPageOR.getProperty("submitbutton"));
		
		_webcontrols.get().click("Click on Delete Icon",ClusterPageOR.getProperty("deletecluster"));
		_webcontrols.get().enterText("Enter Status ", ClusterPageOR.getProperty("status"),"Review" );
		_webcontrols.get().click("Click on Save button",
				ClusterPageOR.getProperty("clustersavebutton"));
		
	
	
		}	


	public boolean verifyclusterpage() throws InterruptedException
	{
		
			Thread.sleep(1000);
	
		//_webcontrols.get().click("Click on Cluster tab", ClusterPageOR.getProperty("ClusterTab"));
		//_webcontrols.get().waitUntilElementIsVisible("Wait for Cluster page",
			//	ClusterPageOR.getProperty("clusterarrow"), timeOut);
		_webcontrols.get().click("Select first checkbox", ClusterPageOR.getProperty("firstChkBox"));
		_webcontrols.get().waitUntilElementIsVisible("waitAdd icon tab ", ClusterPageOR.getProperty("addicon"),
				timeOut);
		reportPass("Verify Add icon is display", "Add cluster Icon is displayed",
				pageNameForExecutionReport);
	
		_webcontrols.get().waitUntilElementIsVisible("wait export icon tab ", ClusterPageOR.getProperty("exporticon"),
				timeOut);
		reportPass("Verify export icon is display", "Export Icon is displayed",
				pageNameForExecutionReport);
		_webcontrols.get().waitUntilElementIsVisible("wait version history icon tab ", ClusterPageOR.getProperty("versionhistoryicon"),
				timeOut);
		reportPass("Verify version history is display", "Add version history is displayed",
				pageNameForExecutionReport);
		_webcontrols.get().click("Click on version history", ClusterPageOR.getProperty("versionhistoryicon"));
		
		return true;
	}
	
}
