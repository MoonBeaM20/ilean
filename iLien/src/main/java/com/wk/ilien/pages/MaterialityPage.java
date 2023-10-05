package com.wk.ilien.pages;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilienframework.controlsLibrary.webControls.CustomWebControls;
import com.wk.ilienframework.controlsLibrary.webControls.WebControlsLibrary;

public class MaterialityPage extends PageGenerics {
	
	private static Properties MaterialityPageOR = null;
	
	private CustomWebControls customWebControls;
	
	private long defaultWaitTimeout = 10;
	
	
	// materiality page
	
	public MaterialityPage(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this._webcontrols = _webcontrols;
		this.customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(defaultWaitTimeout);
		this.pageNameForExecutionReport = "MaterialityPage";
		if(MaterialityPageOR == null) {
			MaterialityPageOR = UtilLib.setPropertyObject(pageNameForExecutionReport);
		}
	}
	
	public boolean getMaterialityPage() {
		Assert.assertTrue(click("click ellipsis menu", MaterialityPageOR.getProperty("ellipsisMenu")));
		return click("click materiality settings option", MaterialityPageOR.getProperty("ellipsisMenuMaterialityOption"));
	}
	
	public boolean setAutocompleteImmaterialAlerts(boolean isYes) {
		if(isYes) {
			return click("set auto complete immaterial alerts to \"Yes\"", MaterialityPageOR.getProperty("autoCompleteImmaterialAlertsYesOption"));
		}
		return click("set auto complete immaterial alerts to \"No\"", MaterialityPageOR.getProperty("autoCompleteImmaterialAlertsNoOption"));
	}
	
	public boolean setPropertyToMaterial(String propertySectionName, String propertyName) {
		String toggleInputLocator = String.format(MaterialityPageOR.getProperty("propertyMaterialityInput"), propertySectionName, propertyName);
		String toggleLocator = String.format(MaterialityPageOR.getProperty("propertyMaterialityToggle"), propertySectionName, propertyName);
		String checkedAttribute = "";
		try {
			checkedAttribute = customWebControls.findElement(toggleInputLocator).getAttribute("aria-checked");
		}catch(Exception e) {
			System.out.println(toggleInputLocator);
		}
		if(checkedAttribute.equalsIgnoreCase("false")) {
			customWebControls.findElement(toggleLocator).click();
		}
		return true;
	}
	
	public boolean setPropertyToImmaterial(String propertySectionName, String propertyName) {
		String toggleInputLocator = String.format(MaterialityPageOR.getProperty("propertyMaterialityInput"), propertySectionName, propertyName);
		String toggleLocator = String.format(MaterialityPageOR.getProperty("propertyMaterialityToggle"), propertySectionName, propertyName);
		String checkedAttribute = customWebControls.findElement(toggleInputLocator).getAttribute("aria-checked");
		if(checkedAttribute.equalsIgnoreCase("true")) {
			customWebControls.findElement(toggleLocator).click();
		}
		return true;
	}
	
	public boolean submitMateriality() {
		customWebControls.scrollToElement("scroll to submit button", MaterialityPageOR.getProperty("submitButton"), "{block \"center\"}");
		clickUsingActions("submit materiality configuration", MaterialityPageOR.getProperty("submitButton"));
		return _webcontrols.get().waitUntilElementIsVisible("Check visibility of saved materiality settings message", MaterialityPageOR.getProperty("successfulMaterialitySettingsUpdate"), defaultWaitTimeout);
	}
	
	public boolean isMaterialityPopupMessageInvisible() {
		return waitForInvisibilityOfElement("Check invisibility of save materiality settings message", MaterialityPageOR.getProperty("successfulMaterialitySettingsUpdate"), defaultWaitTimeout);
	}
	
	
	// requirement page
	
	public boolean getRequirementPage() {
		click("Click on requirement tab from vertical menu", MaterialityPageOR.getProperty("requirementTab"));
		return waitForVisibilityOfElement("Check visibility of cluster name table heading", "xpath~//span[text()='Requirement Search Summary']", defaultWaitTimeout);
	}
	
	public boolean changeApplicability(String requirementName, String applicability, List<String> circumstances) {
		String requirementApplicabilityLinkLocator = String.format("xpath~(//strong[contains(text(),'%s')]//ancestor::div[@class='row'])[1]//div[6]//div[3]//a", requirementName);
		String currentApplicability = customWebControls.findElement(requirementApplicabilityLinkLocator).getText().trim();
		if(!applicability.equals(currentApplicability)) {
			click("click requirement applicability link", requirementApplicabilityLinkLocator);
			if(applicability.equals("No")) {
				click("click radio button", "xpath~//label[contains(text(), 'No')]//input[@type='radio']");
				circumstances.stream().allMatch(circumstance -> {
					String checkBoxLocator = String.format("xpath~//label[contains(text(), '%s')]//input", circumstance);
					return click("check circumstance", checkBoxLocator);
				});
			}else if(applicability.equals("Yes")) {
				click("click radio button", "xpath~//label[contains(text(), 'Yes')]//input[@type='radio']");
			}
			click("click save settings button", "xpath~//button[text()='Save Settings']");
			return waitForVisibilityOfElement("Check visibility of message", "xpath~//pre[text()='Applicability Requirement updated successfully']", defaultWaitTimeout);
		}else {
			return true;
		}
	}
	
	public boolean selectRequirementToEdit(String requirementName) {
		String requirementNameLinkLocator = String.format("xpath~(//strong[contains(text(), '%s')])[1]", requirementName);
		click("click requirement name link", requirementNameLinkLocator);
		String requirementEditButtonLocator = String.format("xpath~(//div[contains(text(), '%s')])[1]//ancestor::div[contains(@class,'req-table')]//button[text()='Edit']", requirementName);
		click("click requirement edit button", requirementEditButtonLocator);
		return waitForVisibilityOfElement("Check visibility of save button", "xpath~//button[text()='Save']", defaultWaitTimeout);
	}
	
	public boolean enterRequirementName(String requirementName) {
		return enterText("Enter requirement name", MaterialityPageOR.getProperty("requirementNameInput"), requirementName);
	}
	
	public boolean enterRequirementSummary(String requirementSummary) {
		customWebControls.switchToIFrame("Switch to legal information iframe", MaterialityPageOR.getProperty("requirementSummaryInputIframe"));
		boolean operationFlag = customWebControls.clearAndSendKeys("Enter legal information", MaterialityPageOR.getProperty("requirementSummaryBodyInput"), requirementSummary);
		_webcontrols.get().deselectIframe("Switch to default content");
		return operationFlag;
	}
	
	public boolean selectParty(String party) {
		enterText("Enter party", MaterialityPageOR.getProperty("requirementPartyInput"), party);
		String partyListItemLocator = String.format(MaterialityPageOR.getProperty("requirementPartySelectListItem"), party);
		return click("Select party", partyListItemLocator);
	}
	
	public boolean enterReleaseNotes(String releaseNotes) {
		return enterText("Enter release notes", MaterialityPageOR.getProperty("requirementReleaseNotesTextare"), releaseNotes);
	}
	
	public boolean selectChangeType(String changeType, String changeLabel) {
		String changeTypeCheckboxLocator = String.format(MaterialityPageOR.getProperty("changeTypeCheckbox"), changeType);
		customWebControls.scrollToElement("Scroll to change label", changeTypeCheckboxLocator, "{block: \"center\"}");
		click("Click change type check box", changeTypeCheckboxLocator);
		String changeTypeLabelInputLocator = String.format(MaterialityPageOR.getProperty("changeTypeLabelInput"), changeType);
		enterText("Enter change label", changeTypeLabelInputLocator, changeLabel);
		String changeTypeLabelListItemLocator = String.format(MaterialityPageOR.getProperty("changeTypeLabelListItem"), changeType, changeLabel);
		return click("Click change label", changeTypeLabelListItemLocator);
	}
	
	public boolean clickRequirementSaveButton() {
		customWebControls.scrollToElement("", MaterialityPageOR.getProperty("requirementSaveButton"), "{block: \"center\"}");
		return click("Click save button", MaterialityPageOR.getProperty("requirementSaveButton"));
	}
	
	public boolean submitRequirementChanges() {
		return click("Click submit button", MaterialityPageOR.getProperty("requirementSaveChangesButton"));
	}
	
	public boolean isRequirementSaved() {
		return _webcontrols.get().waitUntilElementIsVisible("Check visibility of message", MaterialityPageOR.getProperty("successfulRequirementUpdate"), defaultWaitTimeout);
	}
	
	public boolean isRequirementUpdateMessageInvisible() {
		return _webcontrols.get().waitUntilElementIsInVisible("Check invisibility of message", MaterialityPageOR.getProperty("successfulRequirementUpdate"), defaultWaitTimeout);
	}
	
	public String getAssociatedClusterName(String requirementName) {
		String locatorString = String.format("xpath~(//div[contains(text(), '%s')])[1]//ancestor::div[contains(@class,'req-table')]//div[7]//a", requirementName);
		return customWebControls.findElement(locatorString).getText().trim();
	}
	
	public boolean clickAssociatedCluster(String requirementName) {
		String locatorString = String.format("xpath~(//div[contains(text(), '%s')])[1]//ancestor::div[contains(@class,'req-table')]//div[7]//a", requirementName);
		return click("click cluster link", locatorString);
	}
	
	
	// cluster page
	
	public boolean getClusterPage() {
		click("Click on cluster tab from vertical menu", MaterialityPageOR.getProperty("clusterTab"));
		return waitForVisibilityOfElement("Check visibility of cluster name table heading", "xpath~//p[text()='Cluster Name']", defaultWaitTimeout);
	}
	
	public boolean clickEditCluster(String clusterName) {
		String clusterEditIconLocator = String.format("xpath~//div[contains(text(),'%s')]//ancestor::div[contains(@class,'cluster-search-res')]//i[contains(@class, 'fa-edit')]", clusterName);
		click("click cluster edit icon", clusterEditIconLocator);
		return waitForVisibilityOfElement("Check for visibility of cluster", "xpath~//input[@id='clustername']", defaultWaitTimeout);
	}

	public boolean enterClusterTitle(String clusterTitle) {
		return enterText("enter cluster title", MaterialityPageOR.getProperty("clusterTitleInput"), clusterTitle);
	}
	
	public boolean enterClusterReleaseComments(String releaseComments) {
		return enterText("enter release comments", MaterialityPageOR.getProperty("clusterReleaseCommentsTextarea"), releaseComments);
	}
	
	public boolean enterClusterGroupingExplanation(String groupingExplanation) {
		return enterText("enter grouping explanation", MaterialityPageOR.getProperty("clusterGroupingExplanationTextarea"), groupingExplanation);
	}
	
	public boolean enterClusterFederalLawCitationCompared(String federalLawCitationCompared) {
		return enterText("enter federal law citation compared", MaterialityPageOR.getProperty("clusterFederalLawCitationComparedTextarea"), federalLawCitationCompared);
	}
	
	public boolean enterClusterComparativeFederalStateAnalysis(String comparativeFederalStateAnalysis) {
		return enterText("enter comparative federal state analysis", MaterialityPageOR.getProperty("clusterComparativeFederalStateAnalysisTextarea"), comparativeFederalStateAnalysis);
	}
	
	public boolean setClusterReviewStatus(String status) {
		click("Click dropdown", "xpath~//input[@id='reviewstatusid']//following-sibling::i");
		String locator = String.format("xpath~//input[@id='reviewstatusid']//ancestor::div[contains(@class, 'form-group')]//div[@class='filter-cluster-status-model']//div//span[text()='%s']", status);
		return click("Click status", locator);
	}
	
	public boolean clickClusterSaveButton() {
		return click("click save button", MaterialityPageOR.getProperty("clusterEditSave"));
	}
	
	public boolean saveChanges(List<List<String>> substantivityPropertyData, String notificationOption) {
		boolean substantivityOperationFlag = substantivityPropertyData.stream().allMatch(propertyData -> {
			String substantivitySelectLocator = String.format("xpath~//td[contains(text(), '%s')]//parent::tr//select", propertyData.get(0));
			return selectByVisibleText("Select substantivity", substantivitySelectLocator, propertyData.get(1));
		});
		
		String notificationOperationLocator = String.format("xpath~//h4[text()='Save Changes']//parent::div//label[contains(text(), '%s')]//input", notificationOption);
		boolean notificationOperationFlag = click("select notification option", notificationOperationLocator);
		
		return substantivityOperationFlag && notificationOperationFlag;
	}
	
	public boolean saveChanges(Map<String, String> materialitySettings) {
		boolean substantivityOperationFlag = materialitySettings.entrySet().stream().allMatch(entry -> {
			String substantivitySelectLocator = String.format("xpath~//td[contains(text(), '%s')]//parent::tr//select", entry.getKey());
			return selectByVisibleText("Select substantivity", substantivitySelectLocator, entry.getValue());
		});
		
		return substantivityOperationFlag;
	}
	
	public boolean submitSaveChanges() {
		return click("Click save changes submit button", MaterialityPageOR.getProperty("clusterUpdateSaveChangesSubmitButton"));
	}
	
	public boolean isClusterSaved() {
		return _webcontrols.get().waitUntilElementIsVisible("Check visibility of successful cluster update message", MaterialityPageOR.getProperty("successfulClusterUpdate"), 5);
	}
	
	
	// CMD notifications
	
	public boolean viewCMDNotifications() {
		return click("Click CMD notifications link", MaterialityPageOR.getProperty("cmdNotificationLink"));
	}
	
	public boolean verifyClusterChangeMateriality(String clusterName, String materiality) {
		String locator = String.format("xpath~(//td//a[contains(text(), '%s')]//ancestor::tr//td//p[contains(text(), 'Cluster Update')])[1]//ancestor::tr//td[5]//p[text()='%s']", clusterName, materiality);
		return waitForVisibilityOfElement("Check visibility of expected materiality", locator, defaultWaitTimeout);
	}
	
	
	// helper methods
	
	private boolean click(String stepDescription, String locator) {
		performWaits();
		boolean operationFlag = _webcontrols.get().click(stepDescription, locator);
		performWaits();
		
		return operationFlag;
	}
	
	private boolean clickUsingJSExecutor(String stepDescription, String locatorString) {
		performWaits();
		boolean operationFlag = customWebControls.clickUsingJSExecutor(stepDescription, locatorString);
		performWaits();
		
		return operationFlag;
	}
	
	private boolean clickUsingActions(String stepDescription, String locator) {
		performWaits();
		boolean operationFlag = _webcontrols.get().clickUsingActionBuilder(stepDescription, locator);
		performWaits();
		
		return operationFlag;
	}
	
	private boolean selectByVisibleText(String stepDescription, String locatorString, String text) {
		performWaits();
		boolean operationFlag = _webcontrols.get().selectDropDown(stepDescription, locatorString, text);
		performWaits();
		
		return operationFlag;
	}
	
	private boolean enterText(String stepDescription, String locatorString, String value) {
		performWaits();
		boolean operationFlag = _webcontrols.get().enterText(stepDescription, locatorString, value);
		performWaits();
		return operationFlag;
	}
	
	private boolean clearAndEnterText(String stepDescription, String locatorString, String value) {
		performWaits();
		boolean operationFlag = customWebControls.clearAndSendKeys(stepDescription, locatorString, value);
		performWaits();
		return operationFlag;
	}
	
	private boolean scrollToElement(String stepDescription, String locatorString, String scrollOptions) {
		performWaits();
		boolean operationFlag = customWebControls.scrollToElement(stepDescription, locatorString, scrollOptions);
		performWaits();
		return operationFlag;
	}
	
	private boolean dragAndDrop(String stepDesription, String sourceLocatorString, String targetLocatorString) {
		boolean operationFlag = false;
		performWaits();
		try {
			WebDriver driver = _webcontrols.get().getDriver();
			Actions actions = new Actions(driver);
			WebElement sourceElement = customWebControls.findElement(sourceLocatorString);
			WebElement targetElement = customWebControls.findElement(targetLocatorString);
			actions.moveToElement(sourceElement).clickAndHold(sourceElement).moveToElement(targetElement).release(targetElement).build().perform();
			operationFlag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		performWaits();
		return operationFlag;
	}
	
	private String getAttributeFromWebElement(String stepDescription, String locator, String attributeName) {
		performWaits();
		String value = _webcontrols.get().getValueOfAttribute(stepDescription, locator, attributeName);
		performWaits();
		return value;
	}
	
	private boolean waitForPresenceOfElement(String stepDescription, String locatorString, long timeout) {
		performWaits();
		boolean operationFlag = customWebControls.waitForPresenceOfElement(stepDescription, locatorString, timeout);
		performWaits();
		return operationFlag;
	}
	
	private boolean waitForVisibilityOfElement(String stepDescription, String locatorString, long timeout) {
		performWaits();
		boolean operationFlag = customWebControls.waitForVisibilityOfElement(stepDescription, locatorString, timeout);
		performWaits();
		return operationFlag;
	}
	
	private boolean waitForInvisibilityOfElement(String stepDesription, String locatorString, long timeout) {
		performWaits();
		boolean operationFlag = customWebControls.waitForInvisibilityOfElement(stepDesription, locatorString, timeout);
		performWaits();
		return operationFlag;
	}
	
	private void performWaits() {
		customWebControls.waitForPageLoad("Wait for page to load"); 
		customWebControls.waitForLoadingToComplete();
	}

}
