package com.wk.ilien.testscripts;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.pages.LoginPage;
import com.wk.ilien.pages.RichTextEditor;
import com.wk.ilien.pages.SubjectGuidesPage;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILienControls;
import com.wk.ilienframework.reporting.Reporting;

public class RichTextEditorTest extends ILienControls {
	private String username = "testAdminUser@wolterskluwer.com";
	private String password = "Test@123";
	
	private LoginPage loginPage = null;
	private SubjectGuidesPage subjectGuidesPage = null;
	private RichTextEditor richTextEditor = null;

	@BeforeMethod
	public void setup() throws AWTException {
		_webcontrols.get().resetImplicitWait("Set implicit wait", 10);
		
		loginPage = new LoginPage(_webcontrols);
		Assert.assertTrue(loginPage.loginToProViso(username, password));
		
		subjectGuidesPage = new SubjectGuidesPage(_webcontrols);
		Assert.assertTrue(subjectGuidesPage.getSubjectGuidesPage());
		
//		Assert.assertTrue(subjectGuidesPage.clickAddSubject());
//		Assert.assertTrue(subjectGuidesPage.selectSubjectGuide("Custom"));
		
		selectTestSubjectGuide();
		subjectGuidesPage.clickEditSubject();
		
		richTextEditor = new RichTextEditor(_webcontrols);
	}
	
	
	// basic styles
	
	@Test(dataProvider = "editorBasicStyleDataProvider", priority = 1)
	public void editorBasicStylesTest(String basicStyle, String text, String innerHtmlToVerify) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to use basic styles in rich text editor inside summary section</b>");
		
		//clear summary
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton(basicStyle, "1");
		richTextEditor.enterText(text, "1");
		richTextEditor.clickToolButton(basicStyle, "1");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		
		Assert.assertTrue(subjectGuidesPage.getSummaryInnerHtml().equals(innerHtmlToVerify));
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");	
	}
	
	@DataProvider(name = "editorBasicStyleDataProvider")
	public String[][] editorBasicStyleDataProvider() {
		return new String[][] {{"Bold", "bold text", "<p><strong>bold text</strong></p>"}};
	}
	
	
	// paragraph styles
	
	@Test(dataProvider = "editorParagraphStyleDataProvider", priority = 2)
	public void editorParaghraphStylesTest(String paragraphStyle, String text, String innerHtmlToVerify) {
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>", "<b>Verify user is able to use basic styles in rich text editor inside summary section</b>");
		
		//clear summary
		richTextEditor.clearAndEnterText("", "1");
		
		//maximize
		richTextEditor.clickToolButton("Maximize", "1");
		
		richTextEditor.clickToolButton(paragraphStyle, "1");
		for(int i=0; i<3; i++) {
			richTextEditor.enterText(text, "1");
			if(i != 2) {
				richTextEditor.enterText(Keys.chord(Keys.ENTER), "1");
			}
		}
		
		//minimize
		richTextEditor.clickToolButton("Minimize", "1");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		
		Assert.assertTrue(subjectGuidesPage.getSummaryInnerHtml().equals(innerHtmlToVerify));
		
		Reporting.getLogger().log(LogStatus.INFO, "<b>Test Case ID:01</b>","<b>End test case</b>");
	}
	
	@DataProvider(name = "editorParagraphStyleDataProvider")
	public String[][] editorParagraphStyleDataProvider() {
		return new String[][] {{"Bulleted", "list item", "<ul><li>list item</li><li>list item</li><li>list item</li></ul>"}};
	}
	
	
	// link styles
	
	// URL link
	
	@Test(dataProvider = "urlLinkDataProvider", priority = 3)
	public void editorUrlLinkTest(String displayText, String protocol, String url) {
		//clear summary
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Link", "1");
		richTextEditor.enterLinkDisplayText(displayText);
		richTextEditor.selectLinkType("url");
		richTextEditor.selectProtocol(protocol);
		richTextEditor.enterLinkUrl(url);
		
//		richTextEditor.selectLinkDialogTab("Target");
		
//		richTextEditor.selectTargetType("<frame>");
//		richTextEditor.enterTargetFrameName("Frame");

//		richTextEditor.selectTargetType("<popup window>");
//		richTextEditor.enterTargetPopupWindowName("Popup window");
//		richTextEditor.enterTargetPopupWindowWidth("500");
//		richTextEditor.enterTargetPopupWindowHeight("500");
//		richTextEditor.enterTargetPopupWindowTopPosition("100");
//		richTextEditor.enterTargetPopupWindowLeftPosition("100");
//		richTextEditor.selectPopupWindowFeatures(new ArrayList<>(Arrays.asList("Toolbar", "Scroll Bars", "Resizable")));
		
		richTextEditor.confirmDialog("Link");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
	}
	
	@DataProvider(name = "urlLinkDataProvider")
	public String[][] urlLinkDataProvider() {
		return new String[][] {{"youtube", "ftp://", "www.youtube.com"}};
	}
	
	// anchor link
	
	@Test(dataProvider = "anchorLinkDataProvider", priority = 4)
	public void editorAnchorLinkTest(String anchorName, String displayText) {
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Anchor", "1");
		richTextEditor.enterAnchorName(anchorName);
		richTextEditor.confirmDialog("Anchor Properties");
		
		richTextEditor.clickToolButton("Link", "1");
		richTextEditor.enterLinkDisplayText(displayText);
		richTextEditor.selectLinkType("anchor");
		richTextEditor.selectAnchorByName(anchorName);
		richTextEditor.selectAnchorByElementId(anchorName);

		richTextEditor.confirmDialog("Link");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
	}
	
	@DataProvider(name = "anchorLinkDataProvider")
	public String[][] anchorLinkDataProvider() {
		return new String[][] {{"some anchor", "youtube"}};
	}
	
	// email link
	
	@Test(dataProvider = "emailLinkDataProvider", priority = 5)
	public void editorEmailLinkTest(String displayText, String emailAddress, String messageSubject, String messageBody) {
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Link", "1");
		richTextEditor.enterLinkDisplayText(displayText);
		richTextEditor.selectLinkType("email");
		richTextEditor.enterEmailLinkEmailAddress(emailAddress);
		richTextEditor.enterEmailLinkMessageSubject(messageSubject);
		richTextEditor.enterEmailLinkMessageBody(messageBody);
		
		richTextEditor.confirmDialog("Link");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
	}
	
	@DataProvider(name = "emailLinkDataProvider")
	public String[][] emailLinkDataProvider() {
		return new String[][] {{"youtube", "someone@something.com", "some subject", "some body"}};
	}
	
	// phone link
	
	@Test(dataProvider = "phoneLinkDataProvider", priority = 6)
	public void editorPhoneLinkTest(String displayText, String phoneNumber) {
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Link", "1");
		richTextEditor.enterLinkDisplayText(displayText);
		richTextEditor.selectLinkType("tel");
		richTextEditor.enterPhoneNumber(phoneNumber);

		richTextEditor.confirmDialog("Link");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
	}
	
	@DataProvider(name = "phoneLinkDataProvider")
	public String[][] phoneLinkDataProvider() {
		return new String[][] {{"youtube", "1234567890"}};
	}
	
	
	// anchor
	
	@Test(dataProvider = "anchorNameDataProvider", priority = 7)
	public void editorAnchorTest(String anchorName) {
		//clear summary
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Anchor", "1");
		richTextEditor.enterAnchorName(anchorName);
		richTextEditor.confirmDialog("Anchor Properties");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
	}
	
	@DataProvider(name = "anchorNameDataProvider")
	public String[][] anchorNameDataProvider() {
		return new String[][] {{"some anchor name"}};
	} 
	
	
	// special characters
	
	@Test(dataProvider = "specialCharacterDataProvider", priority = 8)
	public void specialChracterTest(String specialCharacter) {
		//clear summary
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Character", "1");
		richTextEditor.selectSpecialCharacter(specialCharacter);
		richTextEditor.closeDialog("Select Special Character");

		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
	}
	
	@DataProvider(name = "specialCharacterDataProvider")
	public String[][] specialCharacterDataProvider() {
		return new String[][] {{"@"}};
	}
	
	
	// table styles
	
	@Test(dataProvider = "insertTableDataProvider", priority = 9)
	public void editorInsertTableTest(int row, int columns, String headers, int borderSize, String alignment, String width, String height, int cellSpacing, int cellPading, String caption, String summary) {
		//clear summary
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Table", "1");
		
		richTextEditor.setTableRows(row);
		richTextEditor.setTableColumns(columns);
		richTextEditor.selectTableHeaders(headers);
		richTextEditor.setTableBorderSize(String.valueOf(borderSize));
		richTextEditor.selectTableAlignment(alignment);
		richTextEditor.setTableWidth(width);
		richTextEditor.setTableHeight(height);
		richTextEditor.setTableCellSpacing(String.valueOf(cellSpacing));
		richTextEditor.setTableCellPadding(String.valueOf(cellPading));
		richTextEditor.setTableCaption(caption);
		richTextEditor.setTableSummary(summary);
		
		richTextEditor.confirmDialog("Table Properties");
		
		richTextEditor.openContextMenu("1");
//		richTextEditor.clickContextMenuOption("Cell");
		richTextEditor.clickContenxtMenuInnerOption("Cell", "Cell Properties");
//			
		richTextEditor.setCellWidth("10");
		richTextEditor.setCellWidthUnit("percent");
		richTextEditor.setCellHeight("10");
		richTextEditor.setCellHeightUnit("percent");
		richTextEditor.setCellWordWrap("No");
		richTextEditor.setCellHorizontalAlignment("Center");
		richTextEditor.setCellVerticalAlignment("Middle");
		richTextEditor.setCellType("Header");
		richTextEditor.setCellRowsSpan("2");
		richTextEditor.setCellColumnsSpan("2");
		richTextEditor.setCellBackgroundColor("Black");
		richTextEditor.setCellBorderColor("Red");
		
		richTextEditor.confirmDialog("Cell Properties");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
	}
	
	@DataProvider(name = "insertTableDataProvider")
	public Object[][] insertTableDataProvider() {
		return new Object[][] {{3, 3, "First Row", 2, "Left", "200px", "", 2, 2, "some caption", "some summary"}};
	}
	
	
	// foot notes
	
	@Test(dataProvider = "footNotesDataProvider", priority = 10)
	public void editorFootNotesTest(String footNoteText, String footNoteTool) {
		richTextEditor.clearAndEnterText("", "1");
		richTextEditor.enterText("Some text", "1");
		
		richTextEditor.clickToolButton("Footnote", "1");
		richTextEditor.enterFootnoteText(footNoteText);
		richTextEditor.footnotesEditorSelectAll();
		richTextEditor.clickFootnotesEditorToolButtons(footNoteTool);
		richTextEditor.confirmDialog("Manage Footnotes");
		
//		richTextEditor.clickToolButton("Footnote", "1");
//		richTextEditor.selectExistingFootnote(footNoteText);
//		richTextEditor.confirmDialog("Manage Footnotes");

		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
	}
	
	@DataProvider(name = "footNotesDataProvider")
	public String[][] footNotesDataProvider() {
		return new String[][] {{"Some footnote", "Bold"}};
	}
	
	// format
	
	@Test(dataProvider = "formatDataProvider", priority = 11)
	public void editotFormatSelectTest(String format) {
		//clear summary
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Format", "1");
		richTextEditor.selectFormat(format);
		richTextEditor.enterText("Some text", "1");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
		
//		subjectGuidesPage.closeSubject();
	}
	
	@DataProvider(name = "formatDataProvider")
	public String[][] formatDataProvider() {
		return new String[][] {{"Heading 2"}};
	}
	
	// font size
	
	@Test(dataProvider = "fontSizeDataProvider", priority = 12)
	public void editotFontSizeSelectTest(String fontSize) {
		//clear summary
		richTextEditor.clearAndEnterText("", "1");
		
		richTextEditor.clickToolButton("Font", "1");
		richTextEditor.selectFormat(fontSize);
		richTextEditor.enterText("Some text", "1");
		
		saveTestSubjectGuideChanges();
		selectTestSubjectGuide();
		System.out.println(subjectGuidesPage.getSummaryInnerHtml());
		
//		subjectGuidesPage.closeSubject();
	}
	
	@DataProvider(name = "fontSizeDataProvider")
	public String[][] fontSizeDataProvider() {
		return new String[][] {{"26"}};
	}
	
	public void selectTestSubjectGuide() {
		subjectGuidesPage.selectSubjectBySubjectName("TEST GUIDE");
	}
	
	public void saveTestSubjectGuideChanges() {
		subjectGuidesPage.clickPublishButton();
		subjectGuidesPage.clickChangeCommentsDropDown();
		subjectGuidesPage.selectEditedAreas(new ArrayList<>(Arrays.asList("Summary")));
		subjectGuidesPage.clickChangeCommentsTextarea();
		subjectGuidesPage.enterChangeComments("summary changed");
		subjectGuidesPage.confirmPublishDialog();
	}
}
