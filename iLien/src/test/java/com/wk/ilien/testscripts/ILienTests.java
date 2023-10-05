package com.wk.ilien.testscripts;

import java.awt.AWTException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wk.ilien.pages.EstimatesPage;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILienControls;

public class ILienTests extends ILienControls {
	
	private String username = "331665";
	private String password = "Viaan@71285**";

	private LoginPage loginPage = new LoginPage(_webcontrols);
	private EstimatesPage estimatesPage = new EstimatesPage(_webcontrols);
	
	@BeforeMethod
	public void setup() {
		_webcontrols.get().resetImplicitWait("", 10);
	}
	
	@Test
	public void loginTest() throws AWTException {
		loginPage.loginToiLien(username, password);
	}
	
	@Test
	public void createEsitimatesTest() throws AWTException {
		loginPage.loginToiLien(username, password);
		
		estimatesPage.clickCreateEstimate();
		estimatesPage.selectEstimateLevel("Commercial");
		estimatesPage.selectEstimationTransactionType("Title Only - Prep Only");
		estimatesPage.selectEstimationState("Kansas");
		estimatesPage.selectEstimationCounty("Brown");
		estimatesPage.clickGoButton();
	}
}
