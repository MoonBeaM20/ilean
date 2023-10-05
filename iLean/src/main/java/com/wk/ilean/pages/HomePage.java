package com.wk.ilean.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.wk.ilean.common.PageGenerics;
import com.wk.ilean.common.UtilLib;
import com.wk.ileanframework.controlsLibrary.webControls.WebControlsLibrary;

public class HomePage extends PageGenerics {
	private static Properties homePageOR = null;
	private static final String config = "config";

	public HomePage(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "Home Page";
		this._webcontrols = _webcontrols;
		if (homePageOR == null) {
			homePageOR = UtilLib.setPropertyObject("HomePage");
		}

	}

	public boolean homePageSections(String loggedInUser) {
		boolean flag = false;
		_webcontrols.get().waitUntilElementIsVisible("wait proviso logo ", homePageOR.getProperty("proVisoLogo"),
				timeOut);
		reportPass("Verify Proviso Logo is display", "Proviso Logo is displayed sucessfully.",
				pageNameForExecutionReport);
		_webcontrols.get().waitUntilElementIsVisible("Wait proviso product name in Header ",
				homePageOR.getProperty("proVisoProductName"), timeOut);
		reportPass("Verify Proviso Product is display", "Proviso Product is displayed sucessfully.",
				pageNameForExecutionReport);
		_webcontrols.get().enterText("Verify search bar is enable ", homePageOR.getProperty("searchBarTxt"), "Debt");
		_webcontrols.get().clearTextBox("Clear the enterd text from search bar",
				homePageOR.getProperty("searchBarTxt"));
		reportPass("Verify search bar displayed", "Proviso search bar is enabled.", pageNameForExecutionReport);
		_webcontrols.get().waitUntilElementIsVisible("wait proviso content management dashboard ",
				homePageOR.getProperty("contentMgmtdashBrd"), timeOut);
		reportPass("Verify Proviso content management dashboard",
				"Proviso content management dashboard is displayed sucessfully.", pageNameForExecutionReport);

		_webcontrols.get().waitUntilElementIsVisible("Wait for expand icon", homePageOR.getProperty("verticalDots"),
				timeOut);
		_webcontrols.get().clickUsingJSExecutor("Clicked on vertical dots in home page", homePageOR.getProperty("verticalDots"));
		reportPass("Verify Proviso vertical dots is display", "Proviso vertical dots is displayed sucessfully.",
				pageNameForExecutionReport);

		_webcontrols.get().waitUntilElementIsVisible("wait proviso breadcrumb ", homePageOR.getProperty("breadcrumb"),
				timeOut);
		reportPass("Verify Proviso breadcrumb is display", "Proviso breadcrumb is displayed sucessfully.",
				pageNameForExecutionReport);

		String actLoggedInUser = _webcontrols.get().getText("Get logged in user", homePageOR.getProperty("logInUsr"))
				.trim();
		if (loggedInUser.equalsIgnoreCase(actLoggedInUser)) {
			reportPass("Verify Proviso logged in user", "Proviso logged user is same as expected. " + actLoggedInUser,
					pageNameForExecutionReport);
			flag = true;
		} else {
			reportFail("Verify Proviso logged in user", "Proviso logged user is not same. " + actLoggedInUser,
					pageNameForExecutionReport);
		}
		
		_webcontrols.get().waitUntilElementIsVisible("wait proviso Section tab ", homePageOR.getProperty("tooltipSection"),
				timeOut);
		reportPass("Verify Section tab is display", "Section tab is displayed with appropriate requirement",
				pageNameForExecutionReport);
		
		_webcontrols.get().waitUntilElementIsVisible("wait proviso Requirement tab ", homePageOR.getProperty("tooltipRequirement"),
				timeOut);
		reportPass("Verify Requirement tab is display", "Requirement tab is displayed",pageNameForExecutionReport);
		
		_webcontrols.get().waitUntilElementIsVisible("wait proviso Cluster tab ", homePageOR.getProperty("tooltipCluster"),
				timeOut);
		reportPass("Verify Cluster tab is display", "Cluster tab is displayed",pageNameForExecutionReport);
		
		_webcontrols.get().waitUntilElementIsVisible("wait proviso Binder tab ", homePageOR.getProperty("tooltipBinder"),
				timeOut);
		reportPass("Verify Binder tab is display", "Binder tab is displayed",pageNameForExecutionReport);
		_webcontrols.get().waitUntilElementIsVisible("wait Copy write footer", homePageOR.getProperty("copyWriteFooter"),
				timeOut);
		String copyWriteFooter= _webcontrols.get().getText("Get copy write footer value", homePageOR.getProperty("copyWriteFooter")).trim();
		reportPass("Verify Copy write footer is display", "Copy write footer is displayed. i.e.  "+copyWriteFooter,pageNameForExecutionReport);

		return flag;

	}
	public boolean verifycmdpage() throws InterruptedException
	{
		
			Thread.sleep(1000);
	
		_webcontrols.get().click("Click on Content Management Dashboard tab", homePageOR.getProperty("contentMgmtdashBrd"));
		//_webcontrols.get().waitUntilElementIsVisible("Wait for Cluster page",
			//	ClusterPageOR.getProperty("clusterarrow"), timeOut);
			//_webcontrols.get().waitUntilElementIsVisible("wait proviso content management dashboard ",
				//	homePageOR.getProperty("contentMgmtdashBrd"), timeOut);
			//reportPass("Verify Proviso content management dashboard",
				//	"Proviso content management dashboard is displayed sucessfully.", pageNameForExecutionReport);
			_webcontrols.get().waitUntilElementIsVisible("wait changed record icon ", homePageOR.getProperty("changerecordicon"),
					timeOut);
			reportPass("Verify change record is display", "Change record Icon is displayed",
					pageNameForExecutionReport);
			_webcontrols.get().waitUntilElementIsVisible("wait regulation changed icon ", homePageOR.getProperty("regulationchangecalicon"),
					timeOut);
			reportPass("Verify regulation changed is display", "Regulation changed Icon is displayed",
					pageNameForExecutionReport);
			_webcontrols.get().waitUntilElementIsVisible("wait complete icon ", homePageOR.getProperty("completebtn"),
					timeOut);
			reportPass("Verify completed button is display", "Complete button is displayed",
					pageNameForExecutionReport);
			_webcontrols.get().waitUntilElementIsVisible("wait export result icon ", homePageOR.getProperty("exportresult"),
					timeOut);
			reportPass("Verify export result is display", "Export result is displayed",
					pageNameForExecutionReport);
			_webcontrols.get().waitUntilElementIsVisible("wait refresh result icon ", homePageOR.getProperty("refreshresult"),
					timeOut);
			reportPass("Verify refresh result is display", "Refresh result is displayed",
					pageNameForExecutionReport);
		
		return true;
	}

}
