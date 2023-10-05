package com.wk.ilien.testscripts;

import java.awt.AWTException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wk.ilien.pages.GlobalSearchPage;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ilienframework.controlsLibrary.webControls.CustomWebControls;

public class GlobalSearchFilterTest extends ILeanControls  {

	private String username = "testAdminUser@wolterskluwer.com";
	private String password = "Test@123";
	
	private LoginPage loginPage = null;
	private GlobalSearchPage globalSearchPage = null;

	@BeforeMethod
	public void setup() throws AWTException {
		_webcontrols.get().resetImplicitWait("Set implicit wait", 10);
		
		loginPage = new LoginPage(_webcontrols);
		Assert.assertTrue(loginPage.loginToProViso(username, password));
		
		globalSearchPage = new GlobalSearchPage(_webcontrols);
	}
	
	@Test(dataProvider = "citationFilterOptionsDataProvider")
	public void citationFilterTest(String filterButtonName, String filterOptionsListString) {
		List<String> filterOptions = Arrays.asList(filterOptionsListString.split(";"));
		globalSearchPage.globalSearchResult();
		
		globalSearchPage.clickSearchDropDown();
		globalSearchPage.clickFilterButton(filterButtonName);
		globalSearchPage.clickFilterOptionButton(filterOptions);
	}
	
	@DataProvider(name = "citationFilterOptionsDataProvider")
	public String[][] citationFilterOptionsDataProvider() {
		return new String[][] {{"Citation Filters", "Validated Citation;Has Future Citation;No Future Citation"}};
	}
	
	@Test(dataProvider = "requirementFilterOptionsDataProvider")
	public void requirementFilter(String citationFilterOptionsListString, String requirementFilterOptionsListString) {
		List<String> citationFilterOptions = Arrays.asList(citationFilterOptionsListString.split(";"));
		List<String> requirementFilterOptions = Arrays.asList(requirementFilterOptionsListString.split(";"));
		globalSearchPage.globalSearchResult();
		
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(10);

		customWebControls.waitForLoadingToComplete();
		customWebControls.click("Click on requirements page", "xpath~//a[@mattooltip='Requirement']");
		customWebControls.waitForLoadingToComplete();
		
		globalSearchPage.clickSearchDropDown();
		globalSearchPage.clickFilterButton("Citation Filters");
		globalSearchPage.clickFilterOptionButton(citationFilterOptions);
		globalSearchPage.closeFilterMenu("Citation Filters");

		globalSearchPage.clickFilterButton("Requirement Filters");
		globalSearchPage.clickFilterOptionButton(requirementFilterOptions);
		globalSearchPage.closeFilterMenu("Requirement Filters");
		
		globalSearchPage.clickSearchButton();
	}
	
	@DataProvider(name = "requirementFilterOptionsDataProvider")
	public String[][] requirementFilterOptionsDataProvider() {
		return new String[][] {{"Validated Citation", "Under Review;All Tagged"}};
	}
	
	@Test(dataProvider = "clusterFilterOptionsDataProvider")
	public void clusterFilter(String requirementFilterOptionsListString, String clusterFilterOptionsListString) {
		List<String> requirementFilterOptions = Arrays.asList(requirementFilterOptionsListString.split(";"));
		List<String> clusterFilterOptions = Arrays.asList(clusterFilterOptionsListString.split(";"));
		globalSearchPage.globalSearchResult();
		
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(10);

		customWebControls.waitForLoadingToComplete();
		customWebControls.click("Click on requirements page", "xpath~//a[@mattooltip='Cluster']");
		customWebControls.waitForLoadingToComplete();
		
		globalSearchPage.clickSearchDropDown();
		
		globalSearchPage.clickFilterButton("Requirement Filters");
		globalSearchPage.clickFilterOptionButton(requirementFilterOptions);
		globalSearchPage.closeFilterMenu("Requirement Filters");
		
		globalSearchPage.clickFilterButton("Cluster Filters");
		globalSearchPage.clickFilterOptionButton(clusterFilterOptions);
		globalSearchPage.closeFilterMenu("Cluster Filters");
		
		globalSearchPage.clickSearchButton();
	}
	
	@DataProvider(name = "clusterFilterOptionsDataProvider")
	public String[][] clusterFilterOptionsDataProvider() {
		return new String[][] {{"All Tagged", "Under Review"}};
	}
	
}
