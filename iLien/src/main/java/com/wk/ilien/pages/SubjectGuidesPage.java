package com.wk.ilien.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.wk.ilien.common.PageGenerics;
import com.wk.ilien.common.UtilLib;
import com.wk.ilienframework.controlsLibrary.webControls.CustomWebControls;
import com.wk.ilienframework.controlsLibrary.webControls.WebControlsLibrary;

public class SubjectGuidesPage extends PageGenerics {
	
private static Properties SubjectGuidesPageOR = null;
	
	private CustomWebControls customWebControls;
	
	private long defaultWaitTimeout = 10;
	
	public SubjectGuidesPage(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this._webcontrols = _webcontrols;
		this.customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		this.pageNameForExecutionReport = "SubjectGuidesPage";
		if(SubjectGuidesPageOR == null) {
			SubjectGuidesPageOR = UtilLib.setPropertyObject("SubjectGuidesPage");
		}
		customWebControls.setDefaultExplicitWaitTimeout(10);
		
	}
	
	public boolean getSubjectGuidesPage() {
		// click on subject guides tab from vertical menu
		click("Click on subject guides tab from vertical menu", SubjectGuidesPageOR.getProperty("subjectGuidesTab"));
		
		//verify visibility of search box
		return waitForVisibilityOfElement("Check visibility of search box", SubjectGuidesPageOR.getProperty("searchBox"), defaultWaitTimeout);
	}
	
	
	// main page - search
	
	public boolean search(String searchKeyword) {
		//enter text in search box
		enterText("Enter text in search box", SubjectGuidesPageOR.getProperty("searchBox"), searchKeyword);
		
		//click on search icon
		click("Click on search icon", SubjectGuidesPageOR.getProperty("searchIcon"));
		
		//scroll to highlighted element
		scrollToElement("Scroll to results", SubjectGuidesPageOR.getProperty("searchResults"), "{block: \"center\"}");
		
		//verify visibility of highlighted elements
		return waitForVisibilityOfElement("Verify presence of highlighted elements", SubjectGuidesPageOR.getProperty("searchResults"), defaultWaitTimeout);
	}
	
	// main page - expand all
	
	public boolean clickExpandAllSections() {
		//click on expand all sections toggle
		click("Click on expand all sections toggle", SubjectGuidesPageOR.getProperty("expandAllSectionsToggle"));
		
		//verify visibility of expanded subject rows
		return waitForVisibilityOfElement("Verify visibility of expanded subject rows", SubjectGuidesPageOR.getProperty("expandedSubjectRow"), defaultWaitTimeout);
	}
	
	// main page - refresh results
	
	public boolean clickRefreshResults() {
		//click on refresh results link
		return click("Click on refresh results link", SubjectGuidesPageOR.getProperty("refereshResultLink"));
	}
	
	
	// main page - filter
	
	public boolean filter(List<String> subjectGuideContentList, String startDate, String endDate) {
		//click on filter link
		Assert.assertTrue(clickFilterLink());
		
		//select subject guide contents
		if(subjectGuideContentList.size() > 0) {
			selectFilterSubjectGuideContent(subjectGuideContentList);
		}
		
		//enter end date
		Assert.assertTrue(enterFilterEndDate(endDate));
		
		//enter start date
		Assert.assertTrue(enterFilterStartDate(startDate));
		
		//click filter button
		Assert.assertTrue(clickFilterFilterButton());
		
		//verify filter operation
		return verifyFilterResults(subjectGuideContentList, startDate, endDate);
	}
	
	public boolean verifyFilterResults(List<String> subjectGuideContentList, String startDateString, String endDateString) {
		clickExpandAllSections();
		
		boolean subjectGuideFilter = false;
		if(subjectGuideContentList.size() > 0) {
			subjectGuideFilter = subjectGuideContentList.stream()
					.allMatch(subjectGuideContent -> {
						String sectionTabLocator = String.format(SubjectGuidesPageOR.getProperty("filteredTabLocator"), subjectGuideContent);
						return waitForVisibilityOfElement("Verify visibility of section tab", sectionTabLocator, defaultWaitTimeout);
					});
		}else {
			subjectGuideFilter = true;
		}
		
		boolean dateFilter = false;
		if(StringUtils.isNotBlank(startDateString) && StringUtils.isNotBlank(endDateString)) {
			String lastUpdateDateString = _webcontrols.get().getText("Get last update date", SubjectGuidesPageOR.getProperty("filteredLastUpdateDateLocator")).split(",")[0];
			try {
				Date lastUpdateDate = new SimpleDateFormat("MM-dd-yyyy").parse(lastUpdateDateString);
				Date startDate = new SimpleDateFormat("MM-dd-yyyy").parse(startDateString);
				Date endDate = new SimpleDateFormat("MM-dd-yyyy").parse(endDateString);
				dateFilter = lastUpdateDate.before(endDate) && lastUpdateDate.after(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			dateFilter = true;
		}
		
		return subjectGuideFilter && dateFilter;
	}
	
	public boolean verifyFilterSpanVisibility(List<String> subjectGuideContentList, String startDateString, String endDateString) {
		String spanLocator = "xpath~//span[contains(text(), '%s')]";
		boolean operationFlag = false;
		if(subjectGuideContentList.size() > 0) {
			operationFlag = subjectGuideContentList.stream().allMatch(subjectGuideContent -> {
				return waitForVisibilityOfElement("Wait for visibility of span", String.format(spanLocator, subjectGuideContent), defaultWaitTimeout);
			});
		}
		
		if(StringUtils.isNotBlank(startDateString) && StringUtils.isNotBlank(endDateString)) {
			String text = customWebControls.findElement("xpath~//span[text()='Start Date:']//parent::span").getText();
			operationFlag = text.equals(String.format("Start Date: %s End Date: %s", startDateString, endDateString));
		}
		
		return operationFlag;
	}
	
	public boolean clickFilterLink() {
		//click on filter link
		click("Click on filter link", SubjectGuidesPageOR.getProperty("filterLink"));
		
		//verify visibility of filter button
		return waitForVisibilityOfElement("Verify visibility of filter button", SubjectGuidesPageOR.getProperty("filterButton"), defaultWaitTimeout);
	}
	
	public boolean selectFilterSubjectGuideContent(List<String> subjectGuideContentList) {
		//search for the check box of particular subject content guide and click
		return subjectGuideContentList.stream()
				.allMatch(subjectGuideContent -> {
					String checkBoxLocator = String.format(SubjectGuidesPageOR.getProperty("filterSubjectGuideContentCheckboxLocator"), subjectGuideContent);
					return click("Click on subject guide content checkbox", checkBoxLocator);
				});
	}
	
	public boolean enterFilterStartDate(String startDate) {
		//enter filter start date
		return enterText("Enter filter start date", SubjectGuidesPageOR.getProperty("filterStartDate"), startDate);
	}
	
	public boolean enterFilterEndDate(String endDate) {
		//enter filter end date
		return enterText("Enter filter end date", SubjectGuidesPageOR.getProperty("filterEndDate"), endDate);
	}
	
	public boolean clickFilterFilterButton() {
		//click filter button inside pop up
		return click("Click filter button inside pop up", SubjectGuidesPageOR.getProperty("filterButton"));
	}
	
	public boolean clickFilterCancelButton() {
		//click filter cancel button inside pop up
		return click("Click filter cancel button inside pop up", SubjectGuidesPageOR.getProperty("filterCancelButton"));
	}
	
	public boolean clickClearAllFilters() {
		//click clear all filters link
		return click("Click clear all filters link", SubjectGuidesPageOR.getProperty("filterClearAll"));
	}
	
	
	// main page - results
	
	public boolean clickExpandSubjectBySubjectName(String subjectName) {
		//search for expand icon based on the subject name
		String expandIconlocator = String.format(SubjectGuidesPageOR.getProperty("expandIconLocator"), subjectName);
		scrollToElement("Scroll to icon", expandIconlocator, "{block: \"center\"}");
		clickUsingActions("Click on expand icon", expandIconlocator);
		//verify visibility of expanded subject row
		return waitForVisibilityOfElement("Verify visibility of expanded subject row", String.format(SubjectGuidesPageOR.getProperty("expandedSubjectTableRowLocator"), subjectName), defaultWaitTimeout);
	}
	
	public boolean clickViewTagsBySubjectName(String subjectName) {
		//search for expand icon based on the subject name
		String viewTagsLinkLocator = String.format(SubjectGuidesPageOR.getProperty("viewTagsLinkLocator"), subjectName);
		scrollToElement("Scroll to icon", viewTagsLinkLocator, "{block: \"center\"}");
		clickUsingActions("Click on view tags link", viewTagsLinkLocator);
		
		//verify visibility of view tags dialog box header
		return waitForVisibilityOfElement("Verify visibility of view tags dialog box header", SubjectGuidesPageOR.getProperty("viewTagsDialogHeader"), defaultWaitTimeout);
	}
	
	public boolean closeViewTagsDialogBox() {
		//close view tags dialog box
		return click("Close view tags dialog box", SubjectGuidesPageOR.getProperty("viewTagsDialogCloseButton"));
	}
	
	public boolean clickReadSummaryBySubjectName(String subjectName) {
		//search for read more link based on subject
		String summaryLinklocator = String.format(SubjectGuidesPageOR.getProperty("summaryLinkLocator"), subjectName);
		Assert.assertTrue(clickUsingActions("Click on read more link", summaryLinklocator));
		
		//verify visibility of summary dialog box header
		return waitForVisibilityOfElement("Verify visibility of summary dialog box header", SubjectGuidesPageOR.getProperty("summaryDialogHeader"), defaultWaitTimeout);
	}
	
	public boolean closeSummaryDialogBox() {
		//close summary dialog box
		return click("Close summary dialog box", SubjectGuidesPageOR.getProperty("summaryDialogCloseButton"));
	}
	
	
	// main page - paginator
	
	public boolean changePageSize(String pageSize) {
		//set page size from page size select
		return selectByVisibleText("Set page size from page size select", SubjectGuidesPageOR.getProperty("paginatorPageSize"), pageSize);
	}
	
	public boolean clickPaginatorFirstPage() {
		//click on first page button
		return click("Click on first page button", SubjectGuidesPageOR.getProperty("paginatorFirst"));
	}
	
	public boolean clickPaginatorPreviousPage() {
		//click on previous page button
		return click("Click on previous page button", SubjectGuidesPageOR.getProperty("paginatorPrevious"));
	}
	
	public boolean clickPaginatorNextPage() {
		//click on next page button
		return click("Click on next page button", SubjectGuidesPageOR.getProperty("paginatorNext"));
	}
	
	public boolean clickPaginatorLastPage() {
		//click on last page button
		return click("Click on last page button", SubjectGuidesPageOR.getProperty("paginatorLast"));
	}
	
	
	// subject page
	
	public boolean selectSubjectBySubjectName(String subjectName) {
		//click on subject name link based on subject name
		String subjectNameLinkLocator = String.format(SubjectGuidesPageOR.getProperty("subjectNameLocator"), subjectName);
		click("Click on subject name link", subjectNameLinkLocator);
		
		//verify visibility of page title
		String pageTitleLocator = String.format(SubjectGuidesPageOR.getProperty("subjectPageTitleLocator"), subjectName);
		return waitForVisibilityOfElement("Verify visibility of page title", pageTitleLocator, defaultWaitTimeout);
	}
	
	public boolean clickEditSubject() {
		//click on edit button
		clickUsingActions("Click on edit button", SubjectGuidesPageOR.getProperty("subjectEditButton"));
		
		//verify visibility of publish button
		return waitForVisibilityOfElement("Verify visibility of publish button", SubjectGuidesPageOR.getProperty("addSubjectPublishButton"), defaultWaitTimeout);
	}
	
	public boolean clickCancelEditSubject() {
		//click on cancel edit button
		return click("Click on cancel edit button", SubjectGuidesPageOR.getProperty("subjectCancelEditButton"));
	}
	
	public boolean closeSubject() {
		//close subject
		return clickUsingActions("Close subject", SubjectGuidesPageOR.getProperty("subjectCloseIcon"));
	}
	
	public boolean closeSubjectNotification() {
		//close subject notification
		return click("Close subject notification", SubjectGuidesPageOR.getProperty("subjectNotificationCloseButton"));
	}
	
	public boolean clickChangeAppendixLink() {
		//click change appendix link
		click("Click change appendix link", SubjectGuidesPageOR.getProperty("subjectNotificationChangeAppendixLink"));
		//verify if change history tab is selected
		return waitForVisibilityOfElement("Wait for visibility of activated tab", SubjectGuidesPageOR.getProperty("subjectChangeHistorySection"), defaultWaitTimeout);
	}
	
	
 	// subject page - delete subject
	
	public boolean clickDeleteSubject() {
		//click on delete button
		clickUsingActions("Click on delete button", SubjectGuidesPageOR.getProperty("subjectDeleteButton"));
		
		//verify visibility of delete pop up message
		return waitForVisibilityOfElement("Verify visibility of delete pop up message", SubjectGuidesPageOR.getProperty("subjectDeletePopupHeader"), defaultWaitTimeout);
	}
	
	public boolean confirmDeleteSubjectPopup() {
		//click on ok button inside pop up
		click("Click on ok button", SubjectGuidesPageOR.getProperty("subjectDeletePopupOkButton"));
		
		//verify visibility of confirmation message without waits
		return _webcontrols.get().waitUntilElementIsVisible("Verify visibility of confirmation message", SubjectGuidesPageOR.getProperty("subjectDeletedConfirmationMessage"), defaultWaitTimeout);
	}
	
	public boolean cancelDeleteSubject() {
		//click on cancel button inside pop up
		return click("Click on cancel button inside pop up", SubjectGuidesPageOR.getProperty("subjectDeletePopupCancelButton"));
		
	}
	
	public boolean closeDeleteSubjectPopup() {
		//close delete subject pop up
		return click("Click on close button inside pop up", SubjectGuidesPageOR.getProperty("subjectDeletePopupCloseButton"));
	}
	
	
	// subject page - export subject
	
	public boolean clickSubjectExport() {
		//click on subject export icon
		clickUsingActions("Click on subject export icon", SubjectGuidesPageOR.getProperty("subjectExportIcon"));
		
		//verify visibility of dialog box header
		return waitForVisibilityOfElement("Verify visibility of dialog header", SubjectGuidesPageOR.getProperty("subjectExportDialogHeader"), defaultWaitTimeout);
	}
	
	public boolean confirmExportSubject() {
		//click on ok button inside dialog box
		return click("Click on ok button inside dialog", SubjectGuidesPageOR.getProperty("subjectExportDialogExportButton"));
	}
	
	public boolean cancelExportSubject() {
		//click on cancel button inside dialog box
		return click("Click on cancel button inside dialog", SubjectGuidesPageOR.getProperty("subjectExportDialogCancelButton"));
	}
	
	public boolean closeExportSubjectDialogBox() {
		//close export subject dialog box
		return click("Click on close button inside dialog", SubjectGuidesPageOR.getProperty("subjectExportDialogCloseButton"));
	}
	
	
	// subject page - scope comments
	
	public boolean clickScopeAddCommentsButton(int scopeIndex) {
		//click on add comments button based on the location of scope
		String buttonLocator = String.format(SubjectGuidesPageOR.getProperty("scopeCommentsAddButtonLocator"), scopeIndex);
		click("Click on add comments button", buttonLocator);
		
		//verify visibility of dialog box header
		return waitForVisibilityOfElement("Verify visibility of dialog box header", SubjectGuidesPageOR.getProperty("scopeCommentsDialogHeader"), defaultWaitTimeout);
	}
	
	
	// add subject page 
	
	public boolean clickAddSubject() {
		//click on add subject link
		click("Click on add subject link", SubjectGuidesPageOR.getProperty("addSubjectLink"));
		
		//verify visibility of subject guide select
		return waitForVisibilityOfElement("Verify visibility of subject guide select", SubjectGuidesPageOR.getProperty("addSubjectSubjectGuideSelect"), defaultWaitTimeout);
	}
	
	// add subject page - subject guide select
	
	public boolean selectSubjectGuide(String subjectGuideName) {
		//select subject guide by visible text
		return selectByVisibleText("Select subject guide by visible text", SubjectGuidesPageOR.getProperty("addSubjectSubjectGuideSelect"), subjectGuideName);
	}
	
	// add subject page - custom subject guide name
	
	public boolean enterCustomSubjectGuideName(String subjectGuideName) {
		//enter custom subject guide name
		return enterText("Enter custom subject guide name", SubjectGuidesPageOR.getProperty("addSubjectSubjectCustomSubjectName"), subjectGuideName);
	}
	
	// add subject page - save as draft
	
	public boolean clickSaveAsDraftButton() {
		//click on save as draft button
		clickUsingActions("Click on save as draft button", SubjectGuidesPageOR.getProperty("addSubjectSaveAsDraftButton"));
		
		//verify visibility of change comments dialog box header
		return waitForVisibilityOfElement("Verify visibility of change comments dialog box header", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsHeader"), defaultWaitTimeout);
	}
	
	public boolean confirmSaveAsDraftDialog() {
		//click on save button inside dialog box
		click("Click on save button inside dialog box", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsSaveButton"));
		
		//verify visibility of confirmation message
		return waitForVisibilityOfElement("Verify visibility of confirmation message", SubjectGuidesPageOR.getProperty("addSubjectSaveAsDraftConfirmationMessage"), defaultWaitTimeout);
	}
	
	
	// add subject page - ready for publish
	
	public boolean clickReadyForPublishButton() {
		//click on ready for publish button
		clickUsingActions("Click on ready for publish button", SubjectGuidesPageOR.getProperty("addSubjectReadyForPublishButton"));
		
		//verify visibility of change comments dialog box header
		return waitForVisibilityOfElement("Verify visibility of change comments dialog box header", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsHeader"), defaultWaitTimeout);
	}
	
	public boolean confirmReadyForPublishDialog() {
		//click on save button inside dialog box
		click("Click on save button inside dialog box", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsSaveButton"));
		
		//verify visibility of confirmation message
		return waitForVisibilityOfElement("Verify visibility of confirmation message", SubjectGuidesPageOR.getProperty("addSubjectReadyForPublishConfirmationMessage"), defaultWaitTimeout);
	}
	
	// add subject page - ready for publish
	
	public boolean clickPublishButton() {
		//click on ready for publish button
		clickUsingActions("Click on ready for publish button", SubjectGuidesPageOR.getProperty("addSubjectPublishButton"));
		
		//verify visibility of change comments dialog box header
		return waitForVisibilityOfElement("Verify visibility of change comments dialog box header", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsHeader"), defaultWaitTimeout);
	}
	
	public boolean confirmPublishDialog() {
		//click on save button inside dialog box
		click("Click on save button inside dialog box", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsSaveButton"));
		
		//verify visibility of confirmation message
		return waitForVisibilityOfElement("Verify visibility of confirmation message", SubjectGuidesPageOR.getProperty("addSubjectPublishConfirmationMessage"), defaultWaitTimeout);
	}
	
	// add subject page - change comments
	
	public boolean clickChangeCommentsDropDown() {
		//click on drop down icon
		click("Click on drop down icon", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsDropDownIcon"));
		
		//verify visibility of scope list element
		return waitForVisibilityOfElement("Verify visibility of scope list element", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsScopeList"), defaultWaitTimeout);
	}
	
	public boolean selectEditedAreas(List<String> editedAreas) {
		if(editedAreas.size() > 0) {
			//search for edited area check box and click
			editedAreas.forEach(editedArea -> {
				String checkBoxLocator = String.format(SubjectGuidesPageOR.getProperty("addSubjectEditedAreasOptionLocator"), editedArea);
				click("Click on check box", checkBoxLocator);
			});
			
			//verify visibility of span element mentioning size of selected edited areas
			String spanLocator = String.format(SubjectGuidesPageOR.getProperty("addSubjectEditedAreasSpanLocator"), editedAreas.size());
			return waitForVisibilityOfElement("Verify visibility of span element", spanLocator, defaultWaitTimeout);
		}
		return false;
	}
	
	public boolean clickChangeCommentsTextarea() {
		//click on change comments text area
		return click("Click on change comments text area", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsCommentsTextarea"));
	}
	
	public boolean enterChangeComments(String changeComments) {
		//enter change comments
		return enterText("Enter change comments", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsCommentsTextarea"), changeComments);
	}
	
	public boolean clickChangeCommentsCancelButton() {
		//click on cancel button inside dialog box
		return click("Click on cancel button inside dialog box", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsCancelButton"));
	}
	
	public boolean closeChangeCommentsDialog() {
		//close change comments dialog box
		return click("Close change comments dialog box", SubjectGuidesPageOR.getProperty("addSubjectChangeCommentsCloseIcon"));
	}
	
	
	// add subject - summary
	
	public boolean enterSubjectSummary(String subjectSummary) {
		//enter subject summary
		return enterText("Enter subject summary", SubjectGuidesPageOR.getProperty("addSubjectSummaryTextarea"), subjectSummary);
	}
	
	
	// add subject - add scope
	
	public boolean clickAddScopeButton() {
		//get number of scopes for current section
		int currentSize = _webcontrols.get().getWebElementsList("Get scope elements", SubjectGuidesPageOR.getProperty("addSubjectEditScopeLocator")).size();
		
		//click on add scope button
		click("Click add scope button", SubjectGuidesPageOR.getProperty("addSubjectAddScope"));
		int newSize = _webcontrols.get().getWebElementsList("Get scope elements", SubjectGuidesPageOR.getProperty("addSubjectEditScopeLocator")).size();
		
		return newSize > currentSize;
	}
	
	// add subject - scope details
	
	public boolean enterScopeTitle(String scopeTitle, String scopeIndex) {
		//enter scope title based on location of scope
		String scopeTitleTextareaLocator = String.format(SubjectGuidesPageOR.getProperty("addSubjectScopeTitleLocator"), scopeIndex);
		return enterText("Enter scope title", scopeTitleTextareaLocator, scopeTitle);
	}
	
	public boolean enterScopeDescription(String scopeDescription, String scopeIndex) {
		//enter scope title based on location of scope
		String scopeDescriptionTextareaLocator = String.format(SubjectGuidesPageOR.getProperty("addSubjectScopeDescriptionLocator"), scopeIndex);
		return enterText("Enter scope description", scopeDescriptionTextareaLocator, scopeDescription);
	}
	
	// add subject page - scope comments
	
	public boolean clickEditScopeAddCommentsButton(String scopeIndex) {
		// click on add comments button based on location of scope
		String buttonLocator = String.format(SubjectGuidesPageOR.getProperty("scopeCommentsAddButtonLocator"), scopeIndex);
		scrollToElement("Scroll to button", buttonLocator, "{block: \"center\"}");
		clickUsingActions("Click on add coments button", buttonLocator);

		//verify visibility of dialog box header
		return waitForVisibilityOfElement("Verify visibility of dialog box header", SubjectGuidesPageOR.getProperty("scopeCommentsDialogHeader"), defaultWaitTimeout);
	}
	
	// add subject page - delete scope
	
	public boolean clickDeleteScope(String scopeIndex) {
		//click on delete button based on scope location
		String buttonLocator = String.format(SubjectGuidesPageOR.getProperty("addSubjectDeleteScopeButtonLocator"), scopeIndex);
		click("Click on delete button", buttonLocator);
		
		//verify visibility of pop up header
		return waitForVisibilityOfElement("Verify visibility of pop up header", SubjectGuidesPageOR.getProperty("addSubjetcDeleteScopePopupHeader"), defaultWaitTimeout);
	}
	
	public boolean clickDeleteScopeConfirmButton() {
		//click on ok button inside pop up
		click("Click on ok button inside pop up", SubjectGuidesPageOR.getProperty("addSubjectDeleteScopePopupOkButton"));
		
		//verify visibility of confirmation message without waits
		return _webcontrols.get().waitUntilElementIsVisible("Verify visibility of confirmation message", SubjectGuidesPageOR.getProperty("addSubjectDeleteScopeConfirmationMessage"), defaultWaitTimeout);
	}
	
	public boolean clickDeleteScopeCancelButton() {
		//click on cancel button inside pop up
		return click("Click on cancel button inside pop up", SubjectGuidesPageOR.getProperty("addSubjectDeleteScopePopupCancelButton"));
	}
	
	public boolean closeDeleteScopePopup() {
		//close delete scope pop up
		return click("Close delete scope pop up", SubjectGuidesPageOR.getProperty("addSubjetcDeleteScopePopupCloseButton"));
	}
	
	
	// add subject - add section
	
	public boolean clickAddSectionButton() {
		//click add section button
		click("Click add section button", SubjectGuidesPageOR.getProperty("addSubjectAddSectionButton"));
		
		//verify visibility of pop up header
		return waitForVisibilityOfElement("Verify visibility of pop up header", SubjectGuidesPageOR.getProperty("addSubjectAddSectionPopupHeader"), defaultWaitTimeout);
	}
	
	public boolean clickAddSectionDefaultSectionRadioButton() {
		//click default section radio button
		return click("Click default section radio button", SubjectGuidesPageOR.getProperty("addSubjectAddSectionDefaultSectionRadioButton"));
	}
	
	public boolean selectAddSectionDefaultSection(String sectionName) {
		//select default section by visible text
		return selectByVisibleText("Select default section by visible text", SubjectGuidesPageOR.getProperty("addSubjectAddSectionDefaultSectionSelect"), sectionName);
	}
	
	public boolean clickAddSectionCustomSectionRadioButton() {
		//click custom section name radio button
		return click("Click custom section name radio button", SubjectGuidesPageOR.getProperty("addSubjectAddSectionCustomSectionRadioButton"));
	}
	
	public boolean enterAddSectionCustomSectionName(String sectionName) {
		//enter custom section name
		return enterText("Enter custom section name", SubjectGuidesPageOR.getProperty("addSubjectAddSectionCustomSectionNameInput"), sectionName);
	}
	
	public boolean clickAddSectionAddButton() {
		//click on add button inside pop up
		return click("Click on add button inside pop up", SubjectGuidesPageOR.getProperty("addSubjectAddSectionPopupAddButton"));
	}
	
	public boolean verifyAddedSection(String subjectName) {
		//verify visibility of tab element
		String tabLocator = String.format(SubjectGuidesPageOR.getProperty("addSubjectAddedSectionLocator"), subjectName);
		return waitForVisibilityOfElement("Verify visibility of tab element", tabLocator, defaultWaitTimeout);
	}
	
	public boolean clickAddSectionCancelButton() {
		//click cancel button inside pop up
		return click("Click cancel button inside pop up", SubjectGuidesPageOR.getProperty("addSubjectAddSectionPopupCancelButton"));
	}
	
	public boolean closeAddSectionPopup() {
		//close add section pop up
		return click("Close add section pop up", SubjectGuidesPageOR.getProperty("addSubjectAddSectionPopupCloseButton"));
	}
	
	
	// add subject - rearrange sections
	
	public boolean clickRearrangeSectionsButton() {
		//click rearrange sections button
		click("Click rearrange sections button", SubjectGuidesPageOR.getProperty("addSubjectRearrangeSectionsButton"));
		
		//verify visibility of pop up header
		return waitForVisibilityOfElement("Verify visibility of pop up header", SubjectGuidesPageOR.getProperty("addSubjectRearrangeSectionsPopupHeader"), defaultWaitTimeout);
	}
	
	public boolean verifyRearrangedSection(String sectionName) {
		return waitForVisibilityOfElement("Verify position of rearranged section", String.format(SubjectGuidesPageOR.getProperty("addSubjectRearrangedSectionTabLocator"), sectionName), defaultWaitTimeout);
	}
	
	public boolean dragAndDropSection(String sectionName) {
		String sourceListItemLocator = String.format(SubjectGuidesPageOR.getProperty("addSubjectRearrangeSectionListItemLocator"), sectionName);
		String targetListItemLocator = SubjectGuidesPageOR.getProperty("addSubjectRearrangeSectionFirstListItemLocator");
		dragAndDrop("Drag and drop section list item to first position", sourceListItemLocator, targetListItemLocator);
		
		String rearrangedListItemLocator = String.format(SubjectGuidesPageOR.getProperty("addSubjectRearrangeSectionFirstListItemVerificationLocator"), sectionName);
		return waitForVisibilityOfElement("Verify list item is rearranged", rearrangedListItemLocator, defaultWaitTimeout);
	}
	
	public boolean clickRearrangeSectionsSaveButton() {
		//click on save button inside pop up
		return click("Click on save button inside pop up", SubjectGuidesPageOR.getProperty("addSubjectRearrangeSectionsSaveButton"));
	}
	
	public boolean clickRearrangeSectionsCancelButton() {
		//click on save button inside pop up
		return click("Click on cancel button inside pop up", SubjectGuidesPageOR.getProperty("addSubjectRearrangeSectionsCancelButton"));
	}
	
	public boolean closeRearrangeSectionsPopup() {
		//close rearrange sections pop up
		return click("Close rearrange sections pop up", SubjectGuidesPageOR.getProperty("addSubjectRearrangeSectionsCloseButton"));
	}
	
	
	// add subject - delete section
	
	public boolean clickDeleteSectionButton() {
		//click delete section button
		click("Click delete section button", SubjectGuidesPageOR.getProperty("addSubjectDeleteSectionButton"));
		
		//verify visibility of pop up message
		return waitForVisibilityOfElement("Verify visibility of pop up message", SubjectGuidesPageOR.getProperty("addSubjectDeleteSectionPopupMessage"), defaultWaitTimeout);
	}
	
	public boolean clickDeleteSectionPopupOkButton() {
		//click ok button inside pop up
		click("Click ok button inside pop up", SubjectGuidesPageOR.getProperty("addSubjectDeleteSectionPopupOkButton"));
		
		//verify visibility of confirmation message without waits
		return _webcontrols.get().waitUntilElementIsVisible("Verify visibility of confirmation message", SubjectGuidesPageOR.getProperty("addSubjectDeleteSectionConfirmationMessage"), defaultWaitTimeout);
	}
	
	public boolean clickDeleteSectionPopupCancelButton() {
		//click cancel button inside pop up
		return click("Click cancel button inside pop up", SubjectGuidesPageOR.getProperty("addSubjectDeleteSectionPopupCancelButton"));
	}
	
	public boolean closeDeleteSectionPopup() {
		//click cancel button inside pop up
		return click("Close delete sections pop up", SubjectGuidesPageOR.getProperty("addSubjectDeleteSectionPopupCloseButton"));
	}
	
	// add subject - close
	
	public boolean clickAddSubjectCloseIcon() {
		//click on close button
		return click("Click on close icon", SubjectGuidesPageOR.getProperty("addSubjectCloseIcon"));
	}
	
	
	// subject page / add subject page - subject summary
	
	public boolean clickSummaryExpandIcon() {
		//click on expand icon
		return click("Click on expand icon", SubjectGuidesPageOR.getProperty("subjectSummaryExpandIcon"));
	}
	
	public String getSummaryInnerHtml() {
		String innerHTML = getAttributeFromWebElement("Get summary inner HTMl", SubjectGuidesPageOR.getProperty("subjectSummaryContent"), "innerHTML")
				.replaceAll("\u200B", "")
				.replaceAll("\\n|\\t|\\r;", "")
				.replaceAll("&nbsp;", "")
				.trim();
		System.out.println(innerHTML);
		return innerHTML;
	}
	
	// subject page / add subject page - sections
	
	public boolean selectSectionByName(String sectionName) {
		scrollToElement("Scroll to sections", SubjectGuidesPageOR.getProperty("sectionContainer"), "{block: \"center\"}");
		//click on section tab using javascript executor
		String sectionDivLocator = String.format(SubjectGuidesPageOR.getProperty("sectionDivLocator"), sectionName);
		return clickUsingJSExecutor("Click on section tab", sectionDivLocator);
	}
	
	public boolean clickPreviousSectionPage() {
		//click previous in sections paginator
		return click("Click next in sections paginator", SubjectGuidesPageOR.getProperty("sectionsPaginatorBefore"));
	}
	
	public boolean clickNextSectionPage() {
		//click next in sections paginator
		return click("Click next in sections paginator", SubjectGuidesPageOR.getProperty("sectionsPaginatorAfter"));
	}
	
	
	// subject page / add subject page - scope comments
	
	
	public boolean enterScopeComments(String scopeComments) {
		//enter scope comments
		return enterText("Enter scope comments", SubjectGuidesPageOR.getProperty("scopeCommentsTextarea"), scopeComments);
	}
	
	public boolean clickScopeCommentsSaveButton() {
		//click on save button inside dialog box
		click("Click on save button inside dialog box", SubjectGuidesPageOR.getProperty("scopeCommentsSaveButton"));
		
		//verify visibility of confirmation pop up message without using waits
		return _webcontrols.get().waitUntilElementIsVisible("Verify visibility of confirmation pop up message", SubjectGuidesPageOR.getProperty("scopeCommentsConfirmationMessage"), defaultWaitTimeout);
	}
	
	public boolean clickScopeCommentsCancelButton() {
		//click on cancel button inside dialog box
		return click("Click on cancel button inside dialog box", SubjectGuidesPageOR.getProperty("scopeCommentsCancelButton"));
	}
	
	public boolean closeScopeCommentsDialog() {
		//close scope comments dialog box
		return click("Close scope comments dialog box", SubjectGuidesPageOR.getProperty("scopeCommentsCloseButton"));
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
	
	@SuppressWarnings("unused")
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
	
	@SuppressWarnings("unused")
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
	
	@SuppressWarnings("unused")
	private boolean waitForInvisibilityOfElement(String stepDesription, String locatorString, long timeout) {
		performWaits();
		boolean operationFlag = _webcontrols.get().waitUntilElementIsInVisible(stepDesription, locatorString, timeout);
		performWaits();
		return operationFlag;
	}
	
	private void performWaits() {
		customWebControls.waitForPageLoad("Wait for page to load"); 
		customWebControls.waitForLoadingToComplete();
	}
}
