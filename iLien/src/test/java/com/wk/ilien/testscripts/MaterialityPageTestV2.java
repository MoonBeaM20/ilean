package com.wk.ilien.testscripts;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.wk.ilien.pages.GlobalSearchPage;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilien.pages.MaterialityPage;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILienControls;

public class MaterialityPageTestV2 extends ILienControls {

	private String internalUser = "testAdminUser@wolterskluwer.com";
	private String externalUser = "admin.expert@abcbank.com";
	private String password = "Test@123";
	
	private LoginPage loginPage = null;
	private GlobalSearchPage globalSearchPage = null;
	private MaterialityPage materialityPage = null;
	
	@BeforeMethod
	public void setup() throws AWTException {
		_webcontrols.get().resetImplicitWait("Set implicit wait", 10);
		
		loginPage = new LoginPage(_webcontrols);
		Assert.assertTrue(loginPage.loginToProViso(externalUser, password));
		
		globalSearchPage = new GlobalSearchPage(_webcontrols);
		
		materialityPage = new MaterialityPage(_webcontrols);
//		Assert.assertTrue(materialityPage.getMaterialityPage());
	}
	
	@Test()
	public void enterPrimaryTopic() throws AWTException {
		
		loginPage.logOutFromProViso();
		loginPage.loginToProViso(internalUser, password);
		
		globalSearchPage.clickSearchDropDown();
		globalSearchPage.selectPrimaryTopic("Limitations on Fees and Charges (Misc.)");
		globalSearchPage.clickSearchButton();
		materialityPage.getClusterPage();
		materialityPage.clickEditCluster("testm");
		
		int randomNumber = ThreadLocalRandom.current().nextInt(100);
		
		materialityPage.enterClusterTitle("title, random number: " + randomNumber);
		materialityPage.enterClusterReleaseComments("release comments, random number: " + randomNumber);
		materialityPage.enterClusterGroupingExplanation("grouping explanation, random number: " + randomNumber);
		materialityPage.enterClusterFederalLawCitationCompared("federal law citation compared, random number: " + randomNumber);
		materialityPage.enterClusterComparativeFederalStateAnalysis("comparative federal state analysis, random number: " + randomNumber);
		materialityPage.clickClusterSaveButton();
		
		List<List<String>> substantivityPropertyData = new ArrayList<List<String>>();
		substantivityPropertyData.add(Arrays.asList("Cluster Title", "Substantive"));
		substantivityPropertyData.add(Arrays.asList("Grouping Explanation", "Non-Substantive"));
		substantivityPropertyData.add(Arrays.asList("Federal Law Citation Compared", "Substantive"));
		substantivityPropertyData.add(Arrays.asList("Comparative Federal State Analysis", "Non-Substantive"));
		
		materialityPage.saveChanges(substantivityPropertyData, "Do not suppress");
		materialityPage.submitSaveChanges();
		Assert.assertTrue(materialityPage.isClusterSaved());
		
		loginPage.logOutFromProViso();
		loginPage.loginToProViso(externalUser, password);
		
		
	}
	
	@Test
	public void test_tc_01() throws AWTException {
		// external user - materiality configuration
		materialityPage.getMaterialityPage();
		materialityPage.setPropertyToMaterial("Change Impact", "Substantive");
		materialityPage.setPropertyToMaterial("Change Impact", "Non-Substantive");
		
		materialityPage.setPropertyToMaterial("Cluster Attributes", "Cluster Title");
		materialityPage.setPropertyToMaterial("Cluster Attributes", "Grouping Explanation");
		materialityPage.setPropertyToMaterial("Cluster Attributes", "Cluster Federal Law Citation Compared");
		materialityPage.setPropertyToMaterial("Cluster Attributes", "Cluster Comparative Federal State Analysis");
		Assert.assertTrue(materialityPage.submitMateriality());
		
		// log out
		loginPage.logOutFromProViso();
		
		//internal user - update cluster
		loginPage.loginToProViso(internalUser, password);
		
		globalSearchPage.clickSearchDropDown();
		globalSearchPage.selectPrimaryTopic("Limitations on Fees and Charges (Misc.)");
		globalSearchPage.clickSearchButton();
		materialityPage.getClusterPage();
		materialityPage.clickEditCluster("testm");
		
		int randomNumber = ThreadLocalRandom.current().nextInt(100);
		
		materialityPage.enterClusterReleaseComments("release comments " + randomNumber);
		materialityPage.enterClusterGroupingExplanation("grouping explanation " + randomNumber);
		materialityPage.enterClusterFederalLawCitationCompared("federal law citation compared " + randomNumber);
		materialityPage.enterClusterComparativeFederalStateAnalysis("comparative federal state analysis " + randomNumber);
		materialityPage.clickClusterSaveButton();
		
		List<List<String>> substantivityPropertyData = new ArrayList<List<String>>();
		substantivityPropertyData.add(Arrays.asList("Grouping Explanation", "Substantive"));
		substantivityPropertyData.add(Arrays.asList("Federal Law Citation Compared", "Substantive"));
		substantivityPropertyData.add(Arrays.asList("Comparative Federal State Analysis", "Substantive"));
		
		materialityPage.saveChanges(substantivityPropertyData, "Do not suppress");
		materialityPage.submitSaveChanges();
		Assert.assertTrue(materialityPage.isClusterSaved());
		
		// log out
		loginPage.logOutFromProViso();
		
		//external user - view cmd notifications
		loginPage.loginToProViso(externalUser, password);
		materialityPage.viewCMDNotifications();
	}
	
	@Test
	public void test_tc_16() throws AWTException {
		// external user - materiality configuration
		materialityPage.getMaterialityPage();
		materialityPage.setPropertyToMaterial("Change Impact", "Substantive");
		materialityPage.setPropertyToImmaterial("Change Impact", "Non-Substantive");
		
		materialityPage.setPropertyToMaterial("Cluster Attributes", "Cluster Title");
		materialityPage.setPropertyToMaterial("Cluster Attributes", "Grouping Explanation");
		materialityPage.setPropertyToMaterial("Cluster Attributes", "Cluster Federal Law Citation Compared");
		materialityPage.setPropertyToMaterial("Cluster Attributes", "Cluster Comparative Federal State Analysis");
		Assert.assertTrue(materialityPage.submitMateriality());
		
		// log out
		loginPage.logOutFromProViso();
		
		//internal user - update cluster
		loginPage.loginToProViso(internalUser, password);
		
		globalSearchPage.clickSearchDropDown();
		globalSearchPage.selectPrimaryTopic("Limitations on Fees and Charges (Misc.)");
		globalSearchPage.clickSearchButton();
		materialityPage.getClusterPage();
		materialityPage.clickEditCluster("testm");
		
		int randomNumber = ThreadLocalRandom.current().nextInt(100);
		
		materialityPage.enterClusterReleaseComments("release comments " + randomNumber);
		materialityPage.enterClusterGroupingExplanation("grouping explanation " + randomNumber);
		materialityPage.enterClusterFederalLawCitationCompared("federal law citation compared " + randomNumber);
		materialityPage.enterClusterComparativeFederalStateAnalysis("comparative federal state analysis " + randomNumber);
		materialityPage.clickClusterSaveButton();
		
		List<List<String>> substantivityPropertyData = new ArrayList<List<String>>();
		substantivityPropertyData.add(Arrays.asList("Grouping Explanation", "Non-Substantive"));
		substantivityPropertyData.add(Arrays.asList("Federal Law Citation Compared", "Non-Substantive"));
		substantivityPropertyData.add(Arrays.asList("Comparative Federal State Analysis", "Non-Substantive"));
		
		materialityPage.saveChanges(substantivityPropertyData, "Do not suppress");
		materialityPage.submitSaveChanges();
		Assert.assertTrue(materialityPage.isClusterSaved());
		
		// log out
		loginPage.logOutFromProViso();
		
		//external user - view cmd notifications
		loginPage.loginToProViso(externalUser, password);
		materialityPage.viewCMDNotifications();
	}
	
	
	@Test
	public void test() {
		Map<String, String> materialitySettings = readDataFromRow("", "materiality_data.xlsx", "Sheet1", 1);
		materialityPage.getMaterialityPage();
		
		// set materiality settings
		materialitySettings.entrySet().stream().forEach(entry -> {
			String propertySectionName = entry.getKey().split("\\.")[0];
			String propertyName = entry.getKey().split("\\.")[1];
			if(entry.getValue().equals("Material")) {
				materialityPage.setPropertyToMaterial(propertySectionName, propertyName);
			}else {
				materialityPage.setPropertyToImmaterial(propertySectionName, propertyName);
			}
		});
	}
	
	
	// Fillo methods
	
	@SuppressWarnings("unused")
	private List<Map<String, String>> getSheetRowCount(String filePath, String fileName, String sheetName) {
		List<Map<String, String>> data = new ArrayList<>();
		Connection connection = null;
		Fillo fillo = new Fillo();
		try {
			connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String query = String.format("SELECT * FROM %s", sheetName);
			Recordset recordset = connection.executeQuery(query);
			recordset.getCount();
			List<String> fieldNames = recordset.getFieldNames();
			while(recordset.next()) {
				Map<String, String> record = new LinkedHashMap<>();
				for(String fieldName : fieldNames) {
					String value = recordset.getField(fieldName);
					if(StringUtils.isNotBlank(value)) {
						record.put(fieldName, value);
						data.add(record);
					}
				}
			}
			recordset.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}
		connection.close();
		return data;
	}
	
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
