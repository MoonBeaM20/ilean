package com.wk.ilean.testscripts;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilean.pages.GlobalSearchPage;
import com.wk.ilean.pages.LoginPage;
import com.wk.ilean.pages.MaterialityPage;
import com.wk.ileanframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ileanframework.reporting.Reporting;

public class MaterialityPageTest extends ILeanControls {
	
	private String internalUser = "testAdminUser@wolterskluwer.com";
	private String externalUser = "admin.expert@abcbank.com";
	private String password = "Test@123";
	
	private LoginPage loginPage = null;
	private GlobalSearchPage globalSearchPage = null;
	private MaterialityPage materialityPage = null;
	
	@BeforeMethod
	public void setup() {
		_webcontrols.get().resetImplicitWait("Set implicit wait", 10);
		loginPage = new LoginPage(_webcontrols);
		globalSearchPage = new GlobalSearchPage(_webcontrols);
		materialityPage = new MaterialityPage(_webcontrols);
	}
	
	@Test
	public void test_tc_01() throws AWTException {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID: TC_01</b>", "<b>Verify User able to get Material alert in CMD when Substantive as Material and Non-Substantive as Material under Change Impact and compare with Cluster Attributes</b>");
		
		// external login
		Assert.assertTrue(loginPage.loginToProViso(externalUser, password), "Unable to login");
		
		// get materiality page
		Assert.assertTrue(materialityPage.getMaterialityPage(), "Unable to navigate to materiality page");
		
		// set materiality settings
		Assert.assertTrue(setMaterialitySettings(1), "Unable to set materiality settings");
		
		// update materiality
		Assert.assertTrue(materialityPage.submitMateriality(), "Unable to update materiality settings");
		
		// internal login
		Assert.assertTrue(loginPage.logOutFromProViso(), "Unable to logout");
		Assert.assertTrue(loginPage.loginToProViso(internalUser, password), "Unable to login");
		
		// search primary topic
		Assert.assertTrue(globalSearchPage.clickSearchDropDown(), "Unable to click search drop down");
		Assert.assertTrue(globalSearchPage.selectPrimaryTopic("Limitations on Fees and Charges (Misc.)"), "Unable to select primary topic");
		Assert.assertTrue(globalSearchPage.clickSearchButton(), "Unable to search by primary topic");
		
		// get cluster page
		Assert.assertTrue(materialityPage.getClusterPage(), "Unable to navigate to cluster page");
		
		// click edit cluster icon
		Assert.assertTrue(materialityPage.clickEditCluster("testm"), "Unable to click edit cluster icon");
		
		// updating cluster fields
		int randomNumber = ThreadLocalRandom.current().nextInt(100);
		
//		Assert.assertTrue(materialityPage.enterClusterTitle("title, random number: " + randomNumber), "Unable to edit cluster title");
		Assert.assertTrue(materialityPage.enterClusterReleaseComments("release comments " + randomNumber), "Unable to enter release comments");
		Assert.assertTrue(materialityPage.enterClusterGroupingExplanation("grouping explanation " + randomNumber), "Unable to enter grouping explanation");
		Assert.assertTrue(materialityPage.enterClusterFederalLawCitationCompared("federal law citation compared " + randomNumber), "Unable to enter federal law citation compared");
		Assert.assertTrue(materialityPage.enterClusterComparativeFederalStateAnalysis("comparative federal state analysis " + randomNumber), "Unable to enter comparative federal state analysis");
		materialityPage.clickClusterSaveButton();
		
		// save cluster changes
		Map<String, String> materialitySettings = new LinkedHashMap<>();
		materialitySettings.put("Grouping Explanation", "Substantive");
		materialitySettings.put("Federal Law Citation Compared", "Substantive");
		materialitySettings.put("Comparative Federal State Analysis", "Substantive");
		
		Assert.assertTrue(materialityPage.saveChanges(materialitySettings), "Unable to save cluster changes");
		Assert.assertTrue(materialityPage.submitSaveChanges(), "Unable to submit cluster changes");
		Assert.assertTrue(materialityPage.isClusterSaved(), "Cluster updates not saved");
		
		// external login
		Assert.assertTrue(loginPage.logOutFromProViso(), "Unable to logout");
		Assert.assertTrue(loginPage.loginToProViso(externalUser, password), "Unable to login");
		
		// verify materiality on CMD notifications page
		Assert.assertTrue(materialityPage.viewCMDNotifications(), "Unable to navigate to CMD notifications page");
		Assert.assertTrue(materialityPage.verifyClusterChangeMateriality("testm", "Material"), "Invalid materiality");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID: TC_01</b>","<b>End test case</b>");	
	}
	
	@Test
	public void test_tc_02() throws AWTException {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID: TC_01</b>", "<b>Verify User able to get Material alert in CMD when Substantive as Material and Non-Substantive as Immaterial under Change Impact and compare with Cluster Attributes</b>");
		
		// external login
		Assert.assertTrue(loginPage.loginToProViso(externalUser, password), "Unable to login");
		
		// get materiality page
		Assert.assertTrue(materialityPage.getMaterialityPage(), "Unable to navigate to materiality page");
		
		// set materiality settings
		Assert.assertTrue(setMaterialitySettings(2), "Unable to set materiality settings");
		
		// update materiality
		Assert.assertTrue(materialityPage.submitMateriality(), "Unable to update materiality settings");
		
		// internal login
		Assert.assertTrue(loginPage.logOutFromProViso(), "Unable to logout");
		Assert.assertTrue(loginPage.loginToProViso(internalUser, password), "Unable to login");
		
		// search primary topic
		Assert.assertTrue(globalSearchPage.clickSearchDropDown(), "Unable to click search drop down");
		Assert.assertTrue(globalSearchPage.selectPrimaryTopic("Limitations on Fees and Charges (Misc.)"), "Unable to select primary topic");
		Assert.assertTrue(globalSearchPage.clickSearchButton(), "Unable to search by primary topic");
		
		// get cluster page
		Assert.assertTrue(materialityPage.getClusterPage(), "Unable to navigate to cluster page");
		
		// click edit cluster icon
		Assert.assertTrue(materialityPage.clickEditCluster("testm"), "Unable to click edit cluster icon");
		
		// updating cluster fields
		int randomNumber = ThreadLocalRandom.current().nextInt(100);
		
//		Assert.assertTrue(materialityPage.enterClusterTitle("title, random number: " + randomNumber), "Unable to edit cluster title");
		Assert.assertTrue(materialityPage.enterClusterReleaseComments("release comments " + randomNumber), "Unable to enter release comments");
		Assert.assertTrue(materialityPage.enterClusterGroupingExplanation("grouping explanation " + randomNumber), "Unable to enter grouping explanation");
		Assert.assertTrue(materialityPage.enterClusterFederalLawCitationCompared("federal law citation compared " + randomNumber), "Unable to enter federal law citation compared");
		Assert.assertTrue(materialityPage.enterClusterComparativeFederalStateAnalysis("comparative federal state analysis " + randomNumber), "Unable to enter comparative federal state analysis");
		materialityPage.clickClusterSaveButton();
		
		// save cluster changes
		Map<String, String> materialitySettings = new LinkedHashMap<>();
		materialitySettings.put("Grouping Explanation", "Substantive");
		materialitySettings.put("Federal Law Citation Compared", "Non-Substantive");
		materialitySettings.put("Comparative Federal State Analysis", "Non-Substantive");
		
		Assert.assertTrue(materialityPage.saveChanges(materialitySettings), "Unable to save cluster changes");
		Assert.assertTrue(materialityPage.submitSaveChanges(), "Unable to submit cluster changes");
		Assert.assertTrue(materialityPage.isClusterSaved(), "Cluster updates not saved");
		
		// external login
		Assert.assertTrue(loginPage.logOutFromProViso(), "Unable to logout");
		Assert.assertTrue(loginPage.loginToProViso(externalUser, password), "Unable to login");
		
		// verify materiality on CMD notifications page
		Assert.assertTrue(materialityPage.viewCMDNotifications(), "Unable to navigate to CMD notifications page");
		Assert.assertTrue(materialityPage.verifyClusterChangeMateriality("testm", "Material"), "Invalid materiality");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID: TC_01</b>","<b>End test case</b>");	
	}
	
	@Test
	public void test_tc_03() throws AWTException {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID: TC_01</b>", "<b>Verify User able to get Material alert in CMD when Substantive as Immaterial and Non-Substantive as Material under Change Impact and compare with Cluster Attributes</b>");
		
		// external login
		Assert.assertTrue(loginPage.loginToProViso(externalUser, password), "Unable to login");
		
		// get materiality page
		Assert.assertTrue(materialityPage.getMaterialityPage(), "Unable to navigate to materiality page");
		
		// set materiality settings
		Assert.assertTrue(setMaterialitySettings(3), "Unable to set materiality settings");
		
		// update materiality
		Assert.assertTrue(materialityPage.submitMateriality(), "Unable to update materiality settings");
		
		// internal login
		Assert.assertTrue(loginPage.logOutFromProViso(), "Unable to logout");
		Assert.assertTrue(loginPage.loginToProViso(internalUser, password), "Unable to login");
		
		// search primary topic
		Assert.assertTrue(globalSearchPage.clickSearchDropDown(), "Unable to click search drop down");
		Assert.assertTrue(globalSearchPage.selectPrimaryTopic("Limitations on Fees and Charges (Misc.)"), "Unable to select primary topic");
		Assert.assertTrue(globalSearchPage.clickSearchButton(), "Unable to search by primary topic");
		
		// get cluster page
		Assert.assertTrue(materialityPage.getClusterPage(), "Unable to navigate to cluster page");
		
		// click edit cluster icon
		Assert.assertTrue(materialityPage.clickEditCluster("testm"), "Unable to click edit cluster icon");
		
		// updating cluster fields
		int randomNumber = ThreadLocalRandom.current().nextInt(100);
		
//		Assert.assertTrue(materialityPage.enterClusterTitle("title, random number: " + randomNumber), "Unable to edit cluster title");
		Assert.assertTrue(materialityPage.enterClusterReleaseComments("release comments " + randomNumber), "Unable to enter release comments");
		Assert.assertTrue(materialityPage.enterClusterGroupingExplanation("grouping explanation " + randomNumber), "Unable to enter grouping explanation");
		Assert.assertTrue(materialityPage.enterClusterFederalLawCitationCompared("federal law citation compared " + randomNumber), "Unable to enter federal law citation compared");
		Assert.assertTrue(materialityPage.enterClusterComparativeFederalStateAnalysis("comparative federal state analysis " + randomNumber), "Unable to enter comparative federal state analysis");
		materialityPage.clickClusterSaveButton();
		
		// save cluster changes
		Map<String, String> materialitySettings = new LinkedHashMap<>();
		materialitySettings.put("Grouping Explanation", "Substantive");
		materialitySettings.put("Federal Law Citation Compared", "Non-Substantive");
		materialitySettings.put("Comparative Federal State Analysis", "Substantive");
		
		Assert.assertTrue(materialityPage.saveChanges(materialitySettings), "Unable to save cluster changes");
		Assert.assertTrue(materialityPage.submitSaveChanges(), "Unable to submit cluster changes");
		Assert.assertTrue(materialityPage.isClusterSaved(), "Cluster updates not saved");
		
		// external login
		Assert.assertTrue(loginPage.logOutFromProViso(), "Unable to logout");
		Assert.assertTrue(loginPage.loginToProViso(externalUser, password), "Unable to login");
		
		// verify materiality on CMD notifications page
		Assert.assertTrue(materialityPage.viewCMDNotifications(), "Unable to navigate to CMD notifications page");
		Assert.assertTrue(materialityPage.verifyClusterChangeMateriality("testm", "Material"), "Invalid materiality");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID: TC_01</b>","<b>End test case</b>");	
	}
	
	@Test
	public void test_tc_04() {
		//similar to test_tc_01
	}
	
	@Test
	public void test_tc_05() throws AWTException {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID: TC_01</b>", "<b>Verify User able to get Material alert in CMD when Substantive as Material and Non-Substantive as Immaterial under Change Impact and compare with Requirement Applicability</b>");
		
		// external login
		Assert.assertTrue(loginPage.loginToProViso(externalUser, password), "Unable to login");
		
		// get materiality page
		Assert.assertTrue(materialityPage.getMaterialityPage(), "Unable to navigate to materiality page");
		
		// set materiality settings
		Assert.assertTrue(setMaterialitySettings(5), "Unable to set materiality settings");
		
		// update materiality
		Assert.assertTrue(materialityPage.submitMateriality(), "Unable to update materiality settings");
		Assert.assertTrue(materialityPage.isMaterialityPopupMessageInvisible(), "Materiality message still visible");
		
		// search by primary topic
		Assert.assertTrue(globalSearchPage.clickSearchDropDown(), "Unable to click search drop down");
		Assert.assertTrue(globalSearchPage.selectPrimaryTopic("Lending and Financing"), "Unable to select primary topic");
		Assert.assertTrue(globalSearchPage.clickSearchButton(), "Unable to search by primary topic");
		
		// get requirement page
		Assert.assertTrue(materialityPage.getRequirementPage(), "Unable to navigate to requirement page");
		
		// change applicability
//		Assert.assertTrue(materialityPage.changeApplicability("New req - ni3", "No", new ArrayList<>(Arrays.asList("Product Not Offered"))));
		
		// internal login
		Assert.assertTrue(loginPage.logOutFromProViso(), "Unable to logout");
		Assert.assertTrue(loginPage.loginToProViso(internalUser, password), "Unable to login");
		
		// search by primary topic
		Assert.assertTrue(globalSearchPage.clickSearchDropDown(), "Unable to click search drop down");
		Assert.assertTrue(globalSearchPage.selectPrimaryTopic("Lending and Financing"), "Unable to select primary topic");
		Assert.assertTrue(globalSearchPage.clickSearchButton(), "Unable to search by primary topic");
		
		// get requirement page
		Assert.assertTrue(materialityPage.getRequirementPage(), "Unable to navigate to requirement page");
		
		// select requirement to edit
		Assert.assertTrue(materialityPage.selectRequirementToEdit("New req - ni3"), "Unable to select requirment to edit");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID: TC_01</b>","<b>End test case</b>");	
	}
	
	@Test
	public void internalTests() throws AWTException {
		loginPage.loginToProViso(internalUser, password);
		
		// search by primary topic
		Assert.assertTrue(globalSearchPage.clickSearchDropDown(), "Unable to click search drop down");
		Assert.assertTrue(globalSearchPage.selectPrimaryTopic("Lending and Financing"), "Unable to select primary topic");
		Assert.assertTrue(globalSearchPage.clickSearchButton(), "Unable to search by primary topic");
		
		// get requirement page
		Assert.assertTrue(materialityPage.getRequirementPage(), "Unable to navigate to requirement page");
		
		// select requirement to edit
		Assert.assertTrue(materialityPage.selectRequirementToEdit("New req - ni3"), "Unable to select requirment to edit");
		
		// updating requirement fields
		int randomNumber = ThreadLocalRandom.current().nextInt(100);
		
		materialityPage.enterRequirementName("New req - ni3 " + randomNumber);
		materialityPage.enterRequirementSummary("new legal information " + randomNumber);
		materialityPage.selectParty("Individual");
		materialityPage.enterReleaseNotes("New release notes " + randomNumber);
//		materialityPage.selectChangeType("WK Change", "DATA1");
//		materialityPage.selectChangeType("Reg Change", "BLI");
		
		// save changes
		Assert.assertTrue(materialityPage.clickRequirementSaveButton());
		Map<String, String> materialitySettings = new LinkedHashMap<>();
		materialitySettings.put("Requirement Name", "Substantive");
		materialitySettings.put("Requirement Summary", "Substantive");
//		materialitySettings.put("Party Name", "Substantive");
		Assert.assertTrue(materialityPage.saveChanges(materialitySettings), "Unable to save requirement changes");
		Assert.assertTrue(materialityPage.submitRequirementChanges(), "Unable to submit requirement changes");
		Assert.assertTrue(materialityPage.isRequirementSaved(), "Requirement updates not saved");
		
		// click associated cluster
		String associatedClusterName = materialityPage.getAssociatedClusterName("New req - ni3 " + randomNumber);
		Assert.assertTrue(materialityPage.clickAssociatedCluster("New req - ni3 " + randomNumber), "Unable to click associated cluster");
		Assert.assertTrue(materialityPage.clickEditCluster(associatedClusterName), "Unable to click edit cluster");
		
		// set cluster review status
		Assert.assertTrue(materialityPage.setClusterReviewStatus("Approved"));
		
		// save cluster
		Assert.assertTrue(materialityPage.saveChanges(new ArrayList<>(), associatedClusterName));
		
	}
	
	
	// helper methods
	
	private boolean setMaterialitySettings(int rowNum) {
		Map<String, String> materialitySettings = readDataFromRow("", "materiality_data.xlsx", "Sheet1", rowNum);
		return materialitySettings.entrySet().stream().allMatch(entry -> {
			String propertySectionName = entry.getKey().split("\\.")[0];
			String propertyName = entry.getKey().split("\\.")[1];
			if(entry.getValue().equals("Material")) {
				return materialityPage.setPropertyToMaterial(propertySectionName, propertyName);
			}else if(entry.getValue().equals("Immaterial")){
				return materialityPage.setPropertyToImmaterial(propertySectionName, propertyName);
			}
			return false;
		});
	}
	
	
	
	
	// fillo methods
	
	private Map<String, String> readDataFromRow(String filePath, String fileName, String sheetName, int rowNum) {
		Map<String, String> data = new LinkedHashMap<>();
		Connection connection = null;
		Fillo fillo = new Fillo();
		try {
			connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String query = String.format("SELECT * FROM %s", sheetName);
			Recordset recordset = connection.executeQuery(query);
			recordset.getCount();
			List<String> fieldNames = recordset.getFieldNames();
			int currentRow = 0;
			while(recordset.next()) {
				currentRow++;
				if(currentRow == rowNum) {
					for(String fieldName : fieldNames) {
						String value = recordset.getField(fieldName);
						if(StringUtils.isNotBlank(value)) {
							data.put(fieldName, value);
						}
					}
					break;
				}
			}
			recordset.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}
		connection.close();
		return data;
	}
	
}
