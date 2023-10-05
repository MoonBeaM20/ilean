package com.wk.ilean.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Keys;

import com.wk.ilean.common.PageGenerics;
import com.wk.ilean.common.UtilLib;
import com.wk.ileanframework.controlsLibrary.webControls.CustomWebControls;
import com.wk.ileanframework.controlsLibrary.webControls.WebControlsLibrary;

public class RichTextEditor extends PageGenerics {

	private static Properties RichTextEditorOR = null;

	private CustomWebControls customWebControls;
	
	public RichTextEditor(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this._webcontrols = _webcontrols;
		this.customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		this.pageNameForExecutionReport = "SubjectGuidesPage";
		if(RichTextEditorOR == null) {
			RichTextEditorOR = UtilLib.setPropertyObject("RichTextEditor");
		}
		customWebControls.setDefaultExplicitWaitTimeout(10);
	}
	
	public boolean clickEditor(String editorIndex) {
		return customWebControls.click("Click on editor", String.format(RichTextEditorOR.getProperty("editorLocator"), editorIndex));
	}
	
	public boolean clickToolButton(String toolName, String editorIndex) {
		return _webcontrols.get().click("Click on editor tool button", String.format(RichTextEditorOR.getProperty("editorTooButtonLocator"), toolName, editorIndex));
	}
	
	public boolean enterText(String text, String editorIndex) {
		return customWebControls.sendKeys("Enter text inside editor", String.format(RichTextEditorOR.getProperty("editorLocator"), editorIndex), text);
	}
	
	public boolean clearAndEnterText(String text, String editorIndex) {
		return customWebControls.clearAndSendKeys("Enter text inside editor", String.format(RichTextEditorOR.getProperty("editorLocator"), editorIndex), text);
	}
	
	public boolean selectAll(String editorIndex) {
		return customWebControls.sendKeys("Enter text inside editor", String.format(RichTextEditorOR.getProperty("editorLocator"), editorIndex), Keys.chord(Keys.CONTROL + "a"));
	}
	
	public boolean confirmDialog(String dialogTitle) {
		boolean operationFlag = false;
		operationFlag = _webcontrols.get().click("Click on ok button", String.format(RichTextEditorOR.getProperty("dialogConfirmButtonLocator"), dialogTitle));
		customWebControls.acceptAlertIfPresent("Close alert");
		return operationFlag;
	}
	
	public boolean cancelDialog(String dialogTitle) {
		boolean operationFlag = false;
		operationFlag = _webcontrols.get().click("Click on cancel button", String.format(RichTextEditorOR.getProperty("dialogCancelButtonLocator"), dialogTitle));
		customWebControls.acceptAlertIfPresent("Close alert");
		return operationFlag;
	}
	
	public boolean closeDialog(String dialogTitle) {
		boolean operationFlag = false;
		operationFlag = _webcontrols.get().click("Click on close button", String.format(RichTextEditorOR.getProperty("dialogCloseButtonLocator"), dialogTitle));
		customWebControls.acceptAlertIfPresent("Close alert");
		return operationFlag;
	}
	
	// link dialog
	
	public boolean selectLinkDialogTab(String tabName) {
		return _webcontrols.get().click("Click on tab", String.format(RichTextEditorOR.getProperty("linksDialogTabLocator"), tabName));
	}
	
	public boolean enterLinkDisplayText(String displayText) {
		return customWebControls.clearAndSendKeys("Enter link display text", RichTextEditorOR.getProperty("linksDisplayTextInputLocator"), displayText);
	}
	
	public boolean selectLinkType(String linkType) {
		return customWebControls.selectByValue("Select link type", RichTextEditorOR.getProperty("linksLinkTypeSelectLocator"), linkType);
	}
	
	public boolean selectProtocol(String protocol) {
		return customWebControls.selectByValue("Select protocol", RichTextEditorOR.getProperty("linksProtocolSelectLocator"), protocol);
	}
	
	public boolean selectAnchorByName(String anchorName) {
		return _webcontrols.get().selectDropDown("Select anchor", RichTextEditorOR.getProperty("linksAnchorSelectByNameLocator"), anchorName);
	}
	
	public boolean selectAnchorByElementId(String elementId) {
		return _webcontrols.get().selectDropDown("Select anchor", RichTextEditorOR.getProperty("linksAnchorSelectByElementIdLocator"), elementId);
	}
	
	public boolean enterLinkUrl(String url) {
		return customWebControls.clearAndSendKeys("Enter link url", RichTextEditorOR.getProperty("linksUrlInputLocator"), url);
	}
	
	public boolean enterEmailLinkEmailAddress(String emailAddress) {
		return customWebControls.clearAndSendKeys("Enter email address", RichTextEditorOR.getProperty("linksEmailAddressInputLocator"), emailAddress);
	}
	
	public boolean enterEmailLinkMessageSubject(String messageSubject) {
		return customWebControls.clearAndSendKeys("Enter message subject", RichTextEditorOR.getProperty("linksMessageSubjectInputLocator"), messageSubject);
	}
	
	public boolean enterEmailLinkMessageBody(String messageBody) {
		return customWebControls.clearAndSendKeys("Enter message subject", RichTextEditorOR.getProperty("linksMessageBodyInputLocator"), messageBody);
	}
	
	public boolean enterPhoneNumber(String phoneNumber) {
		return customWebControls.clearAndSendKeys("Enter phone number", RichTextEditorOR.getProperty("linksPhoneNumberInputLocator"), phoneNumber);
	}
	
	public boolean selectTargetType(String targetType) {
		return customWebControls.selectByVisibleText("Select target type", RichTextEditorOR.getProperty("linksTargetTypeLocator"), targetType);
	}
	
	public boolean enterTargetFrameName(String frameName) {
		return _webcontrols.get().enterText("Enter target frame name", RichTextEditorOR.getProperty("linksTargetFrameNameInputLocator"), frameName);
	}
	
	public boolean enterTargetPopupWindowName(String popupWindowName) {
		return _webcontrols.get().enterText("Enter popup window name", RichTextEditorOR.getProperty("linksTargetPopupWindowNameLocator"), popupWindowName);
	}
	
	public boolean selectPopupWindowFeatures(List<String> featureNames) {
		return featureNames.stream()
				.allMatch(featureName -> {
					return customWebControls.click("Click feature checkbox", String.format(RichTextEditorOR.getProperty("linksTargetPopupWindowFeatureChechboxLocator"), featureName));
				});
	}
	
	public boolean enterTargetPopupWindowWidth(String width) {
		return _webcontrols.get().enterText("Enter popup window width", RichTextEditorOR.getProperty("linksTargetPopupWindowWidthInputLocator"), width);
	}
	
	public boolean enterTargetPopupWindowHeight(String height) {
		return _webcontrols.get().enterText("Enter popup window height", RichTextEditorOR.getProperty("linksTargetPopupWindowHeightInputLocator"), height);
	}
	
	public boolean enterTargetPopupWindowLeftPosition(String LeftPosition) {
		return _webcontrols.get().enterText("Enter popup window left Position", RichTextEditorOR.getProperty("linksTargetPopupWindowLeftPositionInputLocator"), LeftPosition);
	}
	
	public boolean enterTargetPopupWindowTopPosition(String TopPosition) {
		return _webcontrols.get().enterText("Enter popup window top Position", RichTextEditorOR.getProperty("linksTargetPopupWindowTopPositionInputLocator"), TopPosition);
	}
	
	// anchor dialog
	
	public boolean enterAnchorName(String anchorName) {
		return customWebControls.clearAndSendKeys("Enter anchor name", RichTextEditorOR.getProperty("anchorNameInputLocator"), anchorName);
	}
	
	public boolean verifyInnerHtml(String innerHTMLToVerify, String editorIndex) {
		String editorLocator = String.format(RichTextEditorOR.getProperty("editorLocator"), editorIndex);
		String innerHTML = _webcontrols.get().getValueOfAttribute("Get inner HTML", editorLocator, "innerHTML").replaceAll("\u200B", "");
		System.out.println(innerHTML);
		return innerHTML.equals(innerHTMLToVerify);
	}
	
	// special character dialog
	
	public boolean selectSpecialCharacter(String specialCharacter) {
		return _webcontrols.get().click("Select special character", String.format(RichTextEditorOR.getProperty("specialCharacterLinkLocator"), specialCharacter));
	}
	
	// table dialog
	
	public boolean setTableRows(int rows) {
		return customWebControls.clearAndSendKeys("Enter table rows", RichTextEditorOR.getProperty("tableRowsInputLocator"), String.valueOf(rows));
	}
	
	public boolean setTableColumns(int columns) {
		return customWebControls.clearAndSendKeys("Enter table rows", RichTextEditorOR.getProperty("tableColumnsInputLocator"), String.valueOf(columns));
	}
	
	public boolean selectTableHeaders(String header) {
		return _webcontrols.get().selectDropDown("Select table headers", RichTextEditorOR.getProperty("tableHeaderSelectLocator"), header);
	}
	
	public boolean setTableBorderSize(String borderSize) {
		return customWebControls.clearAndSendKeys("Set table border size", RichTextEditorOR.getProperty("tableBorderSizeInputLocator"), borderSize);
	}
	
	public boolean selectTableAlignment(String alignment) {
		return _webcontrols.get().selectDropDown("Select table alignment", RichTextEditorOR.getProperty("tableAlignmentSelectLocator"), alignment);
	}
	
	public boolean setTableWidth(String width) {
		return customWebControls.clearAndSendKeys("Set table border size", RichTextEditorOR.getProperty("tableWidthInputLocator"), width);
	}
	
	public boolean setTableHeight(String height) {
		return customWebControls.clearAndSendKeys("Set table border size", RichTextEditorOR.getProperty("tableHeightInputLocator"), height);
	}
	
	public boolean setTableCellSpacing(String cellSpacing) {
		return customWebControls.clearAndSendKeys("Set table cell spacing", RichTextEditorOR.getProperty("tableCellSpacingInputLocator"), cellSpacing);
	}
	
	public boolean setTableCellPadding(String cellPadding) {
		return customWebControls.clearAndSendKeys("Set table cell padding", RichTextEditorOR.getProperty("tableCellPaddingInputLocator"), cellPadding);
	}
	
	public boolean setTableCaption(String caption) {
		return customWebControls.clearAndSendKeys("Set table caption", RichTextEditorOR.getProperty("tableCaptionInputLocator"), caption);
	}
	
	public boolean setTableSummary(String caption) {
		return customWebControls.clearAndSendKeys("Set table summary", RichTextEditorOR.getProperty("tableSummaryInputLocator"), caption);
	}
	
	// cell properties dialog
	
	public boolean setCellWidth(String width) {
		return customWebControls.clearAndSendKeys("Enter cell width", RichTextEditorOR.getProperty("cellWidthInputLocator"), width);
	}
	
	public boolean setCellWidthUnit(String cellWidthUnit) {
		return customWebControls.selectByVisibleText("Select cell width unit", RichTextEditorOR.getProperty("cellWidthUnitSelectLocator"), cellWidthUnit);
	}
	
	public boolean setCellHeight(String height) {
		return customWebControls.clearAndSendKeys("Enter cell height", RichTextEditorOR.getProperty("cellHeightInputLocator"), height);
	}
	
	public boolean setCellHeightUnit(String cellHeightUnit) {
		return customWebControls.selectByVisibleText("Select cell height unit", RichTextEditorOR.getProperty("cellHeightUnitSelectLocator"), cellHeightUnit);
	}
	
	public boolean setCellWordWrap(String wordWrap) {
		return customWebControls.selectByVisibleText("Select cell Word Wrap", RichTextEditorOR.getProperty("cellWordWrapSelectLocator"), wordWrap);
	}
	
	public boolean setCellVerticalAlignment(String verticalAlignment) {
		return customWebControls.selectByVisibleText("Select cell vertical alignment", RichTextEditorOR.getProperty("cellVerticalAlignmentSelectLocator"), verticalAlignment);
	}
	
	public boolean setCellHorizontalAlignment(String horizontalAlignment) {
		return customWebControls.selectByVisibleText("Select cell horizontal alignment", RichTextEditorOR.getProperty("cellHorizontalAlignmentSelectLocator"), horizontalAlignment);
	}
	
	public boolean setCellType(String cellType) {
		return customWebControls.selectByVisibleText("Selct cell type", RichTextEditorOR.getProperty("cellTypeSelectLocator"), cellType);
	}
	
	public boolean setCellRowsSpan(String rowsSpan) {
		return customWebControls.clearAndSendKeys("Enter cell rows span", RichTextEditorOR.getProperty("cellRowsSpanInputLocator"), rowsSpan);
	}
	
	public boolean setCellColumnsSpan(String columnsSpan) {
		return customWebControls.clearAndSendKeys("Enter cell columns span", RichTextEditorOR.getProperty("cellColumnsSpanInputLocator"), columnsSpan);
	}
	
	public boolean setCellBackgroundColor(String backgroundColor) {
		return customWebControls.clearAndSendKeys("Enter cell background color", RichTextEditorOR.getProperty("cellBackgroundColorInputLocator"), backgroundColor);
	}
	
	public boolean setCellBorderColor(String borderColor) {
		return customWebControls.clearAndSendKeys("Enter cell border color", RichTextEditorOR.getProperty("cellBorderColorInputLocator"), borderColor);
	}
	
	// footnotes
	
	public boolean clickFootnotesEditorToolButtons(String toolName) {
		return _webcontrols.get().click("Click on tool button", String.format(RichTextEditorOR.getProperty("footNotesToolButtonLocator"), toolName));
	}
	
	public boolean enterFootnoteText(String text) {
		_webcontrols.get().switchToIframe("Switch to iframe", RichTextEditorOR.getProperty("footNotesTextareaFrameLocator"));
		boolean operationFlag =  _webcontrols.get().enterText("Enter footnotes", RichTextEditorOR.getProperty("foorNotesTextareaLocator"), text);
		_webcontrols.get().deselectIframe("Switch back to default content");
		return operationFlag;
	}
	
	public boolean footnotesEditorSelectAll() {
		_webcontrols.get().switchToIframe("Switch to iframe", RichTextEditorOR.getProperty("footNotesTextareaFrameLocator"));
//		boolean operationFlag =  false;
//		try {
//			WebDriver driver = _webcontrols.get().getDriver();
//			Actions actions = new Actions(driver);
//			WebElement editorTextArea = driver.findElement(By.xpath("//body"));
//			actions.moveToElement(editorTextArea).keyDown(Keys.CONTROL).sendKeys(String.valueOf("\u0061")).build().perform();
//			operationFlag = true;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		boolean operationFlag = customWebControls.sendKeys("Select all", RichTextEditorOR.getProperty("foorNotesTextareaLocator"), Keys.chord(Keys.CONTROL, "a"));
		_webcontrols.get().deselectIframe("Switch back to default content");
		return operationFlag;
	}
	
	public boolean selectExistingFootnote(String footnote) {
		return _webcontrols.get().click("Click on radio button", String.format(RichTextEditorOR.getProperty("footNotesRadioButtonLocator"), footnote));
	}
	
	// format box
	
	public boolean selectFormat(String format) {
		customWebControls.switchToIFrame("Switch to frame", RichTextEditorOR.getProperty("formatComboBoxLocator"));
		boolean operationFlag = _webcontrols.get().click("Click on format option", String.format(RichTextEditorOR.getProperty("formatOptionLocator"), format));
		_webcontrols.get().deselectIframe("Switch to default content");
		return operationFlag;
	}
	
	// font size box
	
	public boolean selectFontSize(String fontSize) {
		customWebControls.switchToIFrame("Switch to frame", RichTextEditorOR.getProperty("fontSizeComboBoxLocator"));
		boolean operationFlag = _webcontrols.get().click("Click on format option", String.format(RichTextEditorOR.getProperty("fontSizeOptionLocator"), fontSize));
		_webcontrols.get().deselectIframe("Switch to default content");
		return operationFlag;
	}
	
	// context menu
	
	public boolean openContextMenu(String editorIndex) {
		return _webcontrols.get().rightClick("Right click inside editor", String.format(RichTextEditorOR.getProperty("contextMenuTextareaLocator"), editorIndex));
	}
	
	public boolean clickContextMenuOption(String optionName) {
		customWebControls.switchToIFrame("Switch to frame", RichTextEditorOR.getProperty("parentContextMenuLocator"));
		boolean operationFlag = _webcontrols.get().click("Click on context menu option", String.format(RichTextEditorOR.getProperty("parentContextMenuOption"), optionName));
		_webcontrols.get().deselectIframe("Switch to default content");
		return operationFlag;
	}
	
	public boolean clickContenxtMenuInnerOption(String parentOption, String childOption) {
		clickContextMenuOption(parentOption);
		customWebControls.switchToIFrame("Switch to frame", RichTextEditorOR.getProperty("childContextMenuLocator"));
		boolean operationFlag = _webcontrols.get().click("Click on context menu option", String.format(RichTextEditorOR.getProperty("childContextMenuOption"), childOption));
		_webcontrols.get().deselectIframe("Switch to default content");
		return operationFlag;
	}
	
}
