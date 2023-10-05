package com.wk.ilien.testscripts;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilien.pages.SubjectGuidesPage;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILienControls;
import com.wk.ilienframework.reporting.Reporting;

public class SubjectGuidesPageTest extends ILienControls {
	private String username = "testAdminUser@wolterskluwer.com";
	private String password = "Test@123";
	
	private LoginPage loginPage = null;
	private SubjectGuidesPage subjectGuidesPage = null;

	@BeforeMethod
	public void setup() throws AWTException {
		_webcontrols.get().resetImplicitWait("Set implicit wait", 10);
		
		loginPage = new LoginPage(_webcontrols);
		Assert.assertTrue(loginPage.loginToProViso(username, password));
		
		subjectGuidesPage = new SubjectGuidesPage(_webcontrols);
		Assert.assertTrue(subjectGuidesPage.getSubjectGuidesPage());
	}
	
	
	// main page - search
	
	@Test(dataProvider = "searchKeywordDataProvider", priority = 1)
	public void searchTest(String searchKeyword) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to search subject guides</b>");
		
		Assert.assertTrue(subjectGuidesPage.search(searchKeyword), "Unable to search subject guides");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "searchKeywordDataProvider")
	public String[][] searchKeywordDataProvider() {
		return new String[][] {{"test guide"}};
	}
	
	// main page - filter
	
	@Test(dataProvider = "filterDataProvider", priority = 2)
	public void filterTest(List<String> subjectGuideContents, String startDate, String endDate) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to filter subject guides</b>");
		
		Assert.assertTrue(subjectGuidesPage.filter(subjectGuideContents, startDate, endDate), "Unable to filter subject guides");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
//	@Test(dataProvider = "filterDataProvider", priority = 26)
	public void filterSpanTest(List<String> subjectGuideContents, String startDate, String endDate) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to filter subject guides</b>");
		subjectGuidesPage.filter(subjectGuideContents, startDate, endDate);
		Assert.assertTrue(subjectGuidesPage.verifyFilterSpanVisibility(subjectGuideContents, startDate, endDate), "Unable to filter subject guides");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "filterDataProvider")
	public Object[][] filterDataProvider() {
		return new Object[][] {{new ArrayList<>(Arrays.asList("General Scope", "Law Topics")), "01-01-2023", "09-06-2023"}};
	}
	
	// main page - view subject
	
	@Test(dataProvider = "subjectGuideNameDataProvider", priority = 3)
	public void viewSubjectTest(String subjectGuideName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to view subject guide by clicking on its name</b>");
		
		Assert.assertTrue(subjectGuidesPage.selectSubjectBySubjectName(subjectGuideName), "Unable to view subject guide");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	// main page - view subject guide tags
	
	@Test(dataProvider = "subjectGuideNameDataProvider", priority = 4)
	public void viewSubjectGuideTagsTest(String subjectGuideName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to view subject guide tabs by clicking on view tags link</b>");
		
		Assert.assertTrue(subjectGuidesPage.clickViewTagsBySubjectName(subjectGuideName), "Unable to view subject guide tags");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	// main page - view subject guide summary
	
	@Test(dataProvider = "subjectGuideNameDataProvider", priority = 5)
	public void viewSubjectGuideSummaryTest(String subjectGuideName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to view subject guide summary by clicking on read more link</b>");
		
		Assert.assertTrue(subjectGuidesPage.clickReadSummaryBySubjectName(subjectGuideName), "Unable to view subject guide summary");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}

	// main page - expand subject guide
	
	@Test(dataProvider = "subjectGuideNameDataProvider", priority = 6)
	public void expandSubjectGuideTest(String subjectGuideName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to expand subject guide sections by clicking on its expand icon</b>");
		
		Assert.assertTrue(subjectGuidesPage.clickExpandSubjectBySubjectName(subjectGuideName), "Unable to expand subject guide sections");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	// main page - switch between subject guide section tabs
	
	@Test(dataProvider = "subjectGuideSectionDataProvider", priority = 7)
	public void switchExpandedSectionsTest(String subjectGuideSectionName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to switch between expanded subject guide sections/b>");
		
		subjectGuidesPage.clickExpandSubjectBySubjectName("Marketing Restrictions");
		Assert.assertTrue(subjectGuidesPage.selectSectionByName(subjectGuideSectionName), "Unable to switch between expanded subject guide sections");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "subjectGuideNameDataProvider")
	public String[][] subjectGuideNameDataProvider() {
		return new String[][] {{"Marketing Restrictions"}};
	}
	
	@DataProvider(name = "subjectGuideSectionDataProvider")
	public String[][] subjectGuideSectionDataProvider() {
		return new String[][] {{"Legal Information"}};
	}
	
	// main page - paginator
	
	// main page - expand all
	
	@Test(priority = 8)
	public void expandAllSectionsTest() {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to expand all sections using toggle</b>");
		
		Assert.assertTrue(subjectGuidesPage.clickExpandAllSections(), "Unable to expand all sections using toggle");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	// main page - refresh results
	
	@Test(priority = 9)
	public void refreshAllTest() {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to refresh results by clicking on refresh results link</b>");
		
		Assert.assertTrue(subjectGuidesPage.clickRefreshResults(), "Unable to refresh results");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	
	// subject page - change appendix
	
	@Test(priority = 25)
	public void viewChangeHistoryTest() {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to view change history via change appendix link</b>");
		
		subjectGuidesPage.selectSubjectBySubjectName("TEST GUIDE");
		Assert.assertTrue(subjectGuidesPage.clickChangeAppendixLink(), "Unable to view change history");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	// subject page - scope comments
	
	@Test(dataProvider = "scopeCommentDetailsDataProvider", priority = 17)
	public void subjectPageAddScopeCommentsTest(String subjectGuideName, String sectionName, String scopeIndex, String scopeComments) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to add scope comments</b>");
		
		subjectGuidesPage.selectSubjectBySubjectName(subjectGuideName);
		subjectGuidesPage.selectSectionByName(sectionName);
		subjectGuidesPage.clickEditScopeAddCommentsButton(scopeIndex);
		subjectGuidesPage.enterScopeComments(scopeComments);
		Assert.assertTrue(subjectGuidesPage.clickScopeCommentsSaveButton(), "Unable to add scope comments");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	// subject page - delete
	
	@Test(dataProvider = "subjectGuideDataProvider", priority = 24)
	public void deleteSubjectGuideTest(String subjectGuideName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to delete subject guide</b>");
		
		subjectGuidesPage.selectSubjectBySubjectName(subjectGuideName);
		subjectGuidesPage.clickDeleteSubject();
		Assert.assertTrue(subjectGuidesPage.confirmDeleteSubjectPopup(), "Unable to delete subject");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	// subject page - export
	
	@Test(dataProvider = "subjectGuideDataProvider", priority = 23)
	public void exportSubjectGuideTest(String subjectGuideName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to export subject guide</b>");
		
		subjectGuidesPage.selectSubjectBySubjectName(subjectGuideName);
		subjectGuidesPage.clickSubjectExport();
		Assert.assertTrue(subjectGuidesPage.confirmExportSubject(), "Unable to export subject");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	// add subject - add subject guide
	//
	@Test(dataProvider = "addSubjectGuideDetailsDataProvider", priority = 14)
	public void addSubjectTest(String customSubjectName, String summary, String defaultSectionName, String defaultSectionScopeTitle, String customSectionName, String customSectionScopeTitle, List<String> editedAreas, String changeComments) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to add new subject guide</b>");
		
		subjectGuidesPage.clickAddSubject();
		subjectGuidesPage.selectSubjectGuide("Custom");
		subjectGuidesPage.enterCustomSubjectGuideName(customSubjectName);
		subjectGuidesPage.enterSubjectSummary(summary);
		
		subjectGuidesPage.clickAddSectionButton();
		subjectGuidesPage.clickAddSectionDefaultSectionRadioButton();
		subjectGuidesPage.selectAddSectionDefaultSection(defaultSectionName);
		subjectGuidesPage.clickAddSectionAddButton();
		
		subjectGuidesPage.clickAddSectionButton();
		subjectGuidesPage.clickAddSectionCustomSectionRadioButton();
		subjectGuidesPage.enterAddSectionCustomSectionName(customSectionName);
		subjectGuidesPage.clickAddSectionAddButton();
		
		subjectGuidesPage.selectSectionByName(defaultSectionName);
		subjectGuidesPage.enterScopeTitle(defaultSectionScopeTitle, "last()");
		
		subjectGuidesPage.selectSectionByName(customSectionName);
		subjectGuidesPage.enterScopeTitle(customSectionScopeTitle, "last()");
		
		subjectGuidesPage.clickPublishButton();
		subjectGuidesPage.clickChangeCommentsDropDown();
		subjectGuidesPage.selectEditedAreas(editedAreas);
		subjectGuidesPage.clickChangeCommentsTextarea();
		subjectGuidesPage.enterChangeComments(changeComments);
		
		Assert.assertTrue(subjectGuidesPage.confirmPublishDialog(), "Unable to add new subject guide");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "addSubjectGuideDetailsDataProvider")
	public Object[][] addSubjectGuideDetailsDataProvider() {
		return new Object[][] {{"Custom Subject Guide", "Subject Guide Summary", "Law Topics", "some scope title", "New Section", "some other scope title", new ArrayList<>(Arrays.asList("Law Topics", "New Section")), "New Subject Guide"}};
	}
	
	// add subject - save as draft
	
	@Test(dataProvider = "saveAsDraftDetailsDataProvider", priority = 20)
	public void saveAsDraftTest(String subjectGuideName, List<String> editedAreas, String changeComments) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to save subject guide as draft</b>");
		
		subjectGuidesPage.selectSubjectBySubjectName(subjectGuideName);
		subjectGuidesPage.clickEditSubject();
		subjectGuidesPage.clickSaveAsDraftButton();
		subjectGuidesPage.clickChangeCommentsDropDown();
		subjectGuidesPage.selectEditedAreas(editedAreas);
		subjectGuidesPage.clickChangeCommentsTextarea();
		subjectGuidesPage.enterChangeComments(changeComments);
		Assert.assertTrue(subjectGuidesPage.confirmSaveAsDraftDialog(), "Unable to save subject guide as ready for publish");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");
	}
	
	@DataProvider(name = "saveAsDraftDetailsDataProvider")
	public Object[][] saveAsDraftDetailsDataProvider() {
		return new Object[][] {{"Custom Subject Guide", new ArrayList<>(Arrays.asList("Summary")), "save as draft test"}};
	}
	
	// add subject - ready for publish
	
	@Test(dataProvider = "readyForPublishDetailsDataProvider", priority = 21)
	public void readyForPublishTest(String subjectGuideName, List<String> editedAreas, String changeComments) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to save subject guide as ready for publish</b>");
		
		subjectGuidesPage.selectSubjectBySubjectName(subjectGuideName);
		subjectGuidesPage.clickEditSubject();
		subjectGuidesPage.clickReadyForPublishButton();
		subjectGuidesPage.clickChangeCommentsDropDown();
		subjectGuidesPage.selectEditedAreas(editedAreas);
		subjectGuidesPage.clickChangeCommentsTextarea();
		subjectGuidesPage.enterChangeComments(changeComments);
		Assert.assertTrue(subjectGuidesPage.confirmReadyForPublishDialog(), "Unable to save subject guide as ready for publish");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");
	}
	
	@DataProvider(name = "readyForPublishDetailsDataProvider")
	public Object[][] readyForPublishDetailsDataProvider() {
		return new Object[][] {{"Custom Subject Guide", new ArrayList<>(Arrays.asList("Summary")), "ready for publish test"}};
	}
	
	// add subject - summary
	
	@Test(dataProvider = "subjectSummaryDataProvider", priority = 10)
	public void enterSubjectSummaryTest(String subjectSummary) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to add/edit subject summary</b>");
		
		subjectGuidesPage.clickAddSubject();
		subjectGuidesPage.selectSubjectGuide("Custom");
		Assert.assertTrue(subjectGuidesPage.enterSubjectSummary(subjectSummary), "Unable to add/edit subject summary");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "subjectSummaryDataProvider")
	public String[][] subjectSummaryDataProvider() {
		return new String[][] {{"Subject summary"}};
	}
	
	// add subject - select subject guide
	
	@Test(dataProvider = "subjectGuideDataProvider", priority = 22)
	public void selectSubjectGuideTest(String subjectGuide) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to select subject guide</b>");
		
		subjectGuidesPage.clickAddSubject();
		Assert.assertTrue(subjectGuidesPage.selectSubjectGuide(subjectGuide), "Unable to select subject guide");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "subjectGuideDataProvider")
	public String[][] subjectGuideDataProvider() {
		return new String[][] {{"Custom Subject Guide"}};
	}
	
	// add subject - add default section
	
	@Test(dataProvider = "defaultSectionNameDataProvider", priority = 11)
	public void addDefaultSectionTest(String subjectGuide, String defaultSectionName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to add default subject guide sections</b>");
		
		subjectGuidesPage.clickAddSubject();
		subjectGuidesPage.selectSubjectGuide(subjectGuide);
		subjectGuidesPage.clickAddSectionButton();
		subjectGuidesPage.clickAddSectionDefaultSectionRadioButton();
		subjectGuidesPage.selectAddSectionDefaultSection(defaultSectionName);
		subjectGuidesPage.clickAddSectionAddButton();
		Assert.assertTrue(subjectGuidesPage.verifyAddedSection(defaultSectionName), "Unable to add default subject guide sections");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "defaultSectionNameDataProvider")
	public String[][] defaultSectionNameDataProvider() {
		return new String[][] {{"Custom", "General Scope"}};
	}
	
	// add subject - add custom section
	
	@Test(dataProvider = "customSectionNameDataProvider", priority = 12)
	public void addCustomSectionTest(String subjectGuide, String customSectionName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to add custom subject guide sections</b>");
		
		subjectGuidesPage.clickAddSubject();
		subjectGuidesPage.selectSubjectGuide(subjectGuide);
		subjectGuidesPage.clickAddSectionButton();
		subjectGuidesPage.clickAddSectionCustomSectionRadioButton();
		subjectGuidesPage.enterAddSectionCustomSectionName(customSectionName);
		subjectGuidesPage.clickAddSectionAddButton();
		Assert.assertTrue(subjectGuidesPage.verifyAddedSection(customSectionName), "Unable to add custom subject guide sections");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "customSectionNameDataProvider")
	public String[][] customSectionNameDataProvider() {
		return new String[][] {{"Custom", "Custom section name"}};
	}
	
	@Test(dataProvider = "deleteSectionDetailsDataProvider", priority = 19)
	public void deleteSectionTest(String subjectGuide, String sectionName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to delete subject guide sections</b>");
		
		subjectGuidesPage.clickAddSubject();
		subjectGuidesPage.selectSubjectGuide(subjectGuide);
		subjectGuidesPage.selectSectionByName(sectionName);
		subjectGuidesPage.clickDeleteSectionButton();
		Assert.assertTrue(subjectGuidesPage.clickDeleteSectionPopupOkButton(), "Unable to delete subject guide sections");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "deleteSectionDetailsDataProvider")
	public String[][] deleteSectionDetailsDataProvider() {
		return new String[][] {{"Custom Subject Guide", "New Section"}};
	}
	
	// add subject - rearrange sections
	
	@Test(dataProvider = "rearrangeSectionsDataProvider", priority = 13)
	public void rearrangeSectionsTest(String subjectGuide, String sectionName) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to rearrange subject guide sections</b>");
		
		subjectGuidesPage.clickAddSubject();
		subjectGuidesPage.selectSubjectGuide(subjectGuide);
		subjectGuidesPage.clickRearrangeSectionsButton();
		subjectGuidesPage.dragAndDropSection(sectionName);
		subjectGuidesPage.clickRearrangeSectionsSaveButton();
		Assert.assertTrue(subjectGuidesPage.verifyRearrangedSection(sectionName), "Unable to rearrange subject guide sections");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "rearrangeSectionsDataProvider")
	public String[][] rearrangeSectionsDataProvider() {
		return new String[][] {{"Escrow of Taxes, Insurance, and Assessments", "Cluster Groupings"}};
	}
	
	// add subject - add scope
	
	@Test(dataProvider = "addScopeDetailsDataProvider", priority = 15)
	public void addScopeTest(String subjectGuideName, String sectionName, String scopeTitle, String scopeDescription) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to add a new scope inside subject guide sections</b>");
		
		subjectGuidesPage.clickAddSubject();
		subjectGuidesPage.selectSubjectGuide(subjectGuideName);
		
		//cheesing the automation
		subjectGuidesPage.selectSectionByName("Change History");
		
		subjectGuidesPage.selectSectionByName(sectionName);
		subjectGuidesPage.clickAddScopeButton();
		subjectGuidesPage.enterScopeTitle(scopeTitle, "last()");
		subjectGuidesPage.enterScopeDescription(scopeDescription, "last()");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}

	@DataProvider(name = "addScopeDetailsDataProvider")
	public String[][] addScopeDetailsDataProvider() {
		return new String[][] {{"Custom Subject Guide", "Law Topics", "some scope title", "some scope description"}};
	}
	
	// add subject - delete scope
	
	@Test(dataProvider = "deleteScopeDetailsDataProvider", priority = 18)
	public void deleteScopeTest(String subjectGuideName, String sectionName, String scopeIndex) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to delete scope inside subject guide sections</b>");
		
		subjectGuidesPage.selectSubjectBySubjectName(subjectGuideName);
		subjectGuidesPage.clickEditSubject();
		
		//cheesing the automation
		subjectGuidesPage.selectSectionByName("Change History");
		
		subjectGuidesPage.selectSectionByName(sectionName);
		subjectGuidesPage.clickDeleteScope("last()");
		Assert.assertTrue(subjectGuidesPage.clickDeleteScopeConfirmButton(), "Unable to delete scope");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");
	}
	
	@DataProvider(name = "deleteScopeDetailsDataProvider")
	public String[][] deleteScopeDetailsDataProvider() {
		return new String[][] {{"Custom Subject Guide", "New Section", "last()"}};
	}
	
	// add subject - scope comments
	
	@Test(dataProvider = "scopeCommentDetailsDataProvider", priority = 16)
	public void addSubjectAddScopeCommentsTest(String subjectGuideName, String sectionName, String scopeIndex, String scopeComments) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to add scope comments</b>");
		
//		subjectGuidesPage.clickAddSubject();
//		subjectGuidesPage.selectSubjectGuide(subjectGuideName);
		
		//optional cheesing
//		subjectGuidesPage.selectSectionByName("Change History");
		
//		subjectGuidesPage.clickAddSectionButton();
//		subjectGuidesPage.clickAddSectionDefaultSectionRadioButton();
//		subjectGuidesPage.selectAddSectionDefaultSection(sectionName);
//		subjectGuidesPage.clickAddSectionAddButton();
		
		subjectGuidesPage.selectSubjectBySubjectName(subjectGuideName);
		subjectGuidesPage.clickEditSubject();
		
		subjectGuidesPage.selectSectionByName(sectionName);
		subjectGuidesPage.clickEditScopeAddCommentsButton(scopeIndex);
		
		subjectGuidesPage.enterScopeComments(scopeComments);
		Assert.assertTrue(subjectGuidesPage.clickScopeCommentsSaveButton(), "Unable to add scope comments");
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}

	@DataProvider(name = "scopeCommentDetailsDataProvider")
	public Object[][] scopeCommentDetailsDataProvider() {
		return new Object[][] {{"Custom Subject Guide", "New Section", "1", "Some scope comments"}};
	}
	
}
