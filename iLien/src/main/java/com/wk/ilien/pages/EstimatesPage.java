package com.wk.ilien.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilienframework.controlsLibrary.webControls.WebControlsLibrary;

public class EstimatesPage extends PageGenerics {

	private static Properties estimatesPageOr = null;
	
	public EstimatesPage(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "Login Page";
		this._webcontrols = _webcontrols;
		if (estimatesPageOr == null) {
			estimatesPageOr = UtilLib.setPropertyObject("EstimatesPage");
		}

	}
	
	public boolean clickCreateEstimate() {
		return _webcontrols.get().click("Click create estimates button", estimatesPageOr.getProperty("createEstimatesButton"));
//		_webcontrols.get().getDriver().findElement(By.xpath("//input[@id=\"btnCreateEstimate\"]")).click();
	}
	
	public boolean selectEstimateLevel(String estimateLevel) {
		String locator = String.format(estimatesPageOr.getProperty("estimationLevelRadioButton"), estimateLevel);
		return _webcontrols.get().click("Select estimation level", locator);
	}
	
	public boolean selectEstimationTransactionType(String transactionType) {
		_webcontrols.get().click("Click select", estimatesPageOr.getProperty("transactionType"));
//		Select select = new Select(_webcontrols.get().getDriver().findElement(By.xpath("//select[@id=\"TransactionTypeSelect\"]")));
//		select.selectByValue(transactionType);
		return _webcontrols.get().selectDropDown("Select transaction type", estimatesPageOr.getProperty("transactionType"), transactionType);
	}
	
	public boolean selectEstimationState(String estimationState) {
		return _webcontrols.get().selectDropDown("Select estimation state", estimatesPageOr.getProperty("estimationState"), estimationState);
	}
	
	public boolean selectEstimationCounty(String estimationCounty) {
		return _webcontrols.get().selectDropDown("Select estimation state", estimatesPageOr.getProperty("estimationCounty"), estimationCounty);
	}
	
	public boolean clickGoButton() {
		return _webcontrols.get().click("Click go button", estimatesPageOr.getProperty("goButton"));
	}
	
}
