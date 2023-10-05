package com.wk.ilien.pages;

import java.util.Properties;

import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilienframework.controlsLibrary.webControls.WebControlsLibrary;

public class ExternalFlow extends PageGenerics {
	private static Properties ClusterPageOR = null;
	private static Properties RequirementOR = null;
	private static final String config = "config";
	
	public  ExternalFlow(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "ExternalFlow";
		this._webcontrols = _webcontrols;
		if (ClusterPageOR == null) {
			ClusterPageOR = UtilLib.setPropertyObject("ExternalFlow");
		}
	}
	public boolean createmirrorcluster() throws InterruptedException {
	boolean flag=false;
	Thread.sleep(10000);
	_webcontrols.get().click("Click on Cluster tab", ClusterPageOR.getProperty("ClusterTab"));
	_webcontrols.get().waitUntilElementIsVisible("Wait for Cluster page",
			ClusterPageOR.getProperty("standardclusterheading"), timeOut);
	//\_webcontrols.get().clickUsingJSExecutor("Click on 'Standard Cluster'", ClusterPageOR.getProperty("clusterarrow"));	
	
	//String dynamicXpath="xpath~//tbody/tr/td[3]//div/div/p[2]//a[contains(text(),'"+contextname+"')]//ancestor::tr/td[1]/input";
	//System.out.println(dynamicXpath);

	//_webcontrols.get().clickUsingJSExecutor("Click on 'first cluster'",dynamicXpath);
	_webcontrols.get().click("Click on first checkbox ",ClusterPageOR.getProperty("firstcheckboxstd"));
	_webcontrols.get().click("Click on mirror icon ",ClusterPageOR.getProperty("mirroricon"));
	
	 // String Cname = UtilLib.getRandomString("Cluster"); 
	 // clusterName = Cname;
	 //System.out.println(clusterName);
	//_webcontrols.get().enterText("Enter Cluster name ", ClusterPageOR.getProperty("title"), Cname);	
	//_webcontrols.get().enterText("Enter Status ", ClusterPageOR.getProperty("status"),"Draft" );
	
	/*
	 * JavascriptExecutor js = (JavascriptExecutor)_webcontrols.get().getDriver();
	 * js.executeScript("argumets[0].innerHTML=arguments[1]",
	 * ClusterPageOR.getProperty("groupingexplanation"), "test string");
	 */	
	_webcontrols.get().click("Click on Save button",ClusterPageOR.getProperty("savebutton"));
	
	
	return true;
	}
	
}
