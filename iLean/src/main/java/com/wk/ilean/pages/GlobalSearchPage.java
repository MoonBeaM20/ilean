package com.wk.ilean.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wk.ilean.common.PageGenerics;
import com.wk.ilean.common.UtilLib;
import com.wk.ileanframework.controlsLibrary.webControls.CustomWebControls;
import com.wk.ileanframework.controlsLibrary.webControls.WebControlsLibrary;

public class GlobalSearchPage extends PageGenerics {

	@FindBy(id = "commansearch")
	private WebElement commanSearch;

	@FindBy(xpath = "//i[@class='fa search-expand mr-2 fa-caret-down']")
	private WebElement dropDownBtn;

	@FindBy(id = "Topic")
	private WebElement topicSearch;

	@FindBy(xpath = "(//p[@class='text'])[1]")
	private WebElement firstElement;

	@FindBy(xpath = "//span[contains(text(),' Topic:')]//parent::div/following-sibling::div//i/parent::div")
	private WebElement topicSearchCriteriaText;

	@FindBy(xpath = "//input[@id='Topic']/ancestor::div/..//li[1]//input")
	WebElement firstChkBox;

	private static Properties GlobalSearchPageOR = null;
	private static String pageName = "GlobalSearchPage";
	private static final String config = "config";

	public GlobalSearchPage(ThreadLocal<WebControlsLibrary> _webcontrols) {
		this.pageNameForExecutionReport = "GlobalSearchPage";
		this._webcontrols = _webcontrols;
		if (GlobalSearchPageOR == null) {
			GlobalSearchPageOR = UtilLib.setPropertyObject("GlobalSearchPage");
		}
	}

	public boolean globalSearchResult() {
		boolean flag=false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		_webcontrols.get().waitUntilElementIsVisible("wait for dropdown",
				GlobalSearchPageOR.getProperty("drpCmdSearch"), 100);
		_webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));

		_webcontrols.get().click("Expand subject area dropdown", GlobalSearchPageOR.getProperty("drpSubjectArea"));
		reportPass("Expand Subject area dropdown", "Sucessfully expand subject area dropdown", pageName);
		_webcontrols.get().click("Select subject area dropdown", GlobalSearchPageOR.getProperty("subjectAreaValue"));
		reportPass("Select subject area dropdown", "Sucessfully Subject area dropdown value selected ", pageName);
		String enteredSubjectArea = _webcontrols.get().getDriver()
				.findElement(By.xpath("//input[@id='Subject Area']/ancestor::div/..//li[1]//input//following::p[2]"))
				.getText().trim();

		_webcontrols.get().click("Expand Topic dropdown", GlobalSearchPageOR.getProperty("drpTopic"));
		reportPass("Expand Topic Dropdown", "Sucessfully expand topic dropdown", pageName);
		_webcontrols.get().waitUntilElementIsVisible("wait for topic dropdown",GlobalSearchPageOR.getProperty("topicValue"), 100);
		String enteredTopic = _webcontrols.get().getDriver()
				.findElement(By.xpath("//input[@id='Topic']/ancestor::div/..//li[1]//input//following::p[2]")).getText()
				.trim();
		_webcontrols.get().click("Selected Topic dropdown", GlobalSearchPageOR.getProperty("topicValue"));

		_webcontrols.get().waitUntilElementIsVisible("Wait for charter dropdown",
				GlobalSearchPageOR.getProperty("drpCharter"), 90);
		_webcontrols.get().click("Expand charter dropdown", GlobalSearchPageOR.getProperty("drpCharter"));
		_webcontrols.get().waitUntilElementIsVisible("wait for topic dropdown",GlobalSearchPageOR.getProperty("charterValue"), 100);
		String enteredCharter = _webcontrols.get().getDriver()
				.findElement(By.xpath("//input[@id='Charter']/ancestor::div/..//li[1]//input//following::p[2]"))
				.getText().trim();
		_webcontrols.get().click("Selected Charter dropdown", GlobalSearchPageOR.getProperty("charterValue"));

		_webcontrols.get().click("Expand Product dropdown", GlobalSearchPageOR.getProperty("drpProduct"));
		_webcontrols.get().waitUntilElementIsVisible("wait for topic dropdown",GlobalSearchPageOR.getProperty("productValue"), 100);
		String enteredProduct = _webcontrols.get().getDriver()
				.findElement(By.xpath("//input[@id='Product']/ancestor::div/..//li[1]//input//following::p[2]"))
				.getText().trim();
		_webcontrols.get().click("Selected Product dropdown", GlobalSearchPageOR.getProperty("productValue"));

		_webcontrols.get().click("Expand Jurisdiction dropdown", GlobalSearchPageOR.getProperty("drpJurisdiction"));
		_webcontrols.get().waitUntilElementIsVisible("wait for topic dropdown",GlobalSearchPageOR.getProperty("jurisdictionValue"), 100);
		String enteredJurisdiction = _webcontrols.get().getDriver()
				.findElement(By.xpath("//input[@id='Jurisdiction']/ancestor::div/..//li[1]//input//following::p[2]"))
				.getText().trim();
		_webcontrols.get().click("Selected Jurisdiction dropdown", GlobalSearchPageOR.getProperty("jurisdictionValue"));

		
		  _webcontrols.get().click("Expand Process dropdown",
		  GlobalSearchPageOR.getProperty("drpProcess"));
		  _webcontrols.get().waitUntilElementIsVisible("wait for topic dropdown"
		  ,GlobalSearchPageOR.getProperty("processValue"), 100); String enteredProcess
		  = _webcontrols.get().getDriver() .findElement(By.xpath(
		  "//input[@id='Process']/ancestor::div/..//li[1]//input//following::p[2]"))
		  .getText().trim();
		  
		  _webcontrols.get().click("Selected Process dropdown",
		  GlobalSearchPageOR.getProperty("processValue")); try { Thread.sleep(300); }
		  catch (InterruptedException e) { e.printStackTrace(); }
		 
		 
       //  _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ESCAPE);
		_webcontrols.get().moveToElement("Move to sign in button",  GlobalSearchPageOR.getProperty("btnSearch"), false);
		_webcontrols.get().click("Clicked on search button", GlobalSearchPageOR.getProperty("btnSearch"));

		reportPass("Clicked on Search button", "Sucessfully clicked on search button", pageName);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//_webcontrols.get().waitUntilElementIsVisible("Wait for search criteria",
		//		pageGenericsOR.getProperty("searchCriteria"), 90);
		_webcontrols.get().click("Clicked on Search criteria dropdown",
				GlobalSearchPageOR.getProperty("searchCriteria"));

		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
		}

		// Validating search result
		List<WebElement> topics = _webcontrols.get().getDriver().findElements(
				By.xpath("//span[contains(text(),' Topic:')]//parent::div/following-sibling::div//i/parent::div"));

		for (WebElement webElement : topics) {
			System.out.println(webElement.getText().trim());
			if (webElement.getText().equalsIgnoreCase(enteredTopic)) {
				reportPass("Clicked on Search criteria drop down",
						"Verify selected values & sucessfully verified those criteria value", pageName);
				flag=true;
			}
		}

		return flag;

	}

	public boolean globalSearchResultbyTopic() {
		boolean flag=false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		_webcontrols.get().waitUntilElementIsVisible("wait for dropdown",
				GlobalSearchPageOR.getProperty("drpCmdSearch"), 100);
		_webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));



		/*
		 * _webcontrols.get().click("Expand Topic dropdown",
		 * GlobalSearchPageOR.getProperty("drpTopic"));
		 * reportPass("Expand Topic Dropdown", "Sucessfully expand topic dropdown",
		 * pageName);
		 * _webcontrols.get().waitUntilElementIsVisible("wait for topic dropdown"
		 * ,GlobalSearchPageOR.getProperty("topicValue"), 100);
		 */
		  
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		_webcontrols.get().enterText("Topic Value", GlobalSearchPageOR.getProperty("topicname"), "Disclosures - Timing");
		//_webcontrols.get().enterText("Topic Value", GlobalSearchPageOR.getProperty("topicname"), "Banking and Banking Services");
		_webcontrols.get().click("Selected Topic dropdown", GlobalSearchPageOR.getProperty("topicValue"));

		  String enteredTopic =
				  _webcontrols.get().getDriver() .findElement(By.xpath(
				  "//input[@id='Topic']/ancestor::div/..//li[1]//input//following::p[2]")).
				  getText() .trim();
		  
       //  _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ESCAPE);
		_webcontrols.get().moveToElement("Move to sign in button",  GlobalSearchPageOR.getProperty("btnSearch"), false);
		_webcontrols.get().click("Clicked on search button", GlobalSearchPageOR.getProperty("btnSearch"));

		reportPass("Clicked on Search button", "Sucessfully clicked on search button", pageName);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//_webcontrols.get().waitUntilElementIsVisible("Wait for search criteria",
		//		pageGenericsOR.getProperty("searchCriteria"), 90);
		_webcontrols.get().click("Clicked on Search criteria dropdown",
				GlobalSearchPageOR.getProperty("searchCriteria"));

		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
		}

		// Validating search result
		List<WebElement> topics = _webcontrols.get().getDriver().findElements(
				By.xpath("//span[contains(text(),' Topic:')]//parent::div/following-sibling::div//i/parent::div"));

		for (WebElement webElement : topics) {
			System.out.println(webElement.getText().trim());
			if (webElement.getText().equalsIgnoreCase(enteredTopic)) {
				reportPass("Clicked on Search criteria drop down",
						"Verify selected values & sucessfully verified those criteria value", pageName);
				flag=true;
			}
		}

		return flag;

	}
	public boolean globalSearchResultbyTopicforBinder() {
		boolean flag=false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		_webcontrols.get().waitUntilElementIsVisible("wait for dropdown",
				GlobalSearchPageOR.getProperty("drpCmdSearch"), 100);
		_webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));



		/*
		 * _webcontrols.get().click("Expand Topic dropdown",
		 * GlobalSearchPageOR.getProperty("drpTopic"));
		 * reportPass("Expand Topic Dropdown", "Sucessfully expand topic dropdown",
		 * pageName);
		 * _webcontrols.get().waitUntilElementIsVisible("wait for topic dropdown"
		 * ,GlobalSearchPageOR.getProperty("topicValue"), 100);
		 */
		  
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		
		_webcontrols.get().enterText("Topic Value", GlobalSearchPageOR.getProperty("topicname"), "Banking and Banking Services");
		_webcontrols.get().click("Selected Topic dropdown", GlobalSearchPageOR.getProperty("topicValue"));

		  String enteredTopic =
				  _webcontrols.get().getDriver() .findElement(By.xpath(
				  "//input[@id='Topic']/ancestor::div/..//li[1]//input//following::p[2]")).
				  getText() .trim();
		  
       //  _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ESCAPE);
		_webcontrols.get().moveToElement("Move to sign in button",  GlobalSearchPageOR.getProperty("btnSearch"), false);
		_webcontrols.get().click("Clicked on search button", GlobalSearchPageOR.getProperty("btnSearch"));

		reportPass("Clicked on Search button", "Sucessfully clicked on search button", pageName);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//_webcontrols.get().waitUntilElementIsVisible("Wait for search criteria",
		//		pageGenericsOR.getProperty("searchCriteria"), 90);
		_webcontrols.get().click("Clicked on Search criteria dropdown",
				GlobalSearchPageOR.getProperty("searchCriteria"));

		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
		}

		// Validating search result
		List<WebElement> topics = _webcontrols.get().getDriver().findElements(
				By.xpath("//span[contains(text(),' Topic:')]//parent::div/following-sibling::div//i/parent::div"));

		for (WebElement webElement : topics) {
			System.out.println(webElement.getText().trim());
			if (webElement.getText().equalsIgnoreCase(enteredTopic)) {
				reportPass("Clicked on Search criteria drop down",
						"Verify selected values & sucessfully verified those criteria value", pageName);
				flag=true;
			}
		}

		return flag;

	}
	public boolean globalSearchEverything() {
		boolean flag=false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		_webcontrols.get().waitUntilElementIsVisible("wait for search",
				GlobalSearchPageOR.getProperty("searcheverything"), 100);
		//_webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));
		_webcontrols.get().enterText("Search Topic Entered", GlobalSearchPageOR.getProperty("searcheverything"), "Disclosures - Timing");
		// _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ENTER);
		 _webcontrols.get().pressEnter("Searched Topic", GlobalSearchPageOR.getProperty("searcheverything"));
         
		 _webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));
		
		 _webcontrols.get().click("Clicked on Save search parameter ", GlobalSearchPageOR.getProperty("saveparameter"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //_webcontrols.get().click("Clicked on parameter name ", GlobalSearchPageOR.getProperty("parametername"));
		//_webcontrols.get().enterTextUsingJSExecutor("Enter Save parameter name", GlobalSearchPageOR.getProperty("parametername"), "NewSearch");
	// _webcontrols.get().pressEnter("Searched Topic", GlobalSearchPageOR.getProperty("parametername")); 
	 //_webcontrols.get().click("Clicked on parameter name ", GlobalSearchPageOR.getProperty("parametername"));
	// _webcontrols.get().mouseHover("Entered Parameter name",GlobalSearchPageOR.getProperty("parametername"));
		//String Sname = UtilLib.getRandomString("Search"); 
		// searchname = Sname;
	 //System.out.println(searchname);
		//_webcontrols.get().enterText("Enter Save Parameter name ", GlobalSearchPageOR.getProperty("parametername"), "NewAdd");
		
		_webcontrols.get().click("Clicked on Save search", GlobalSearchPageOR.getProperty("savesearch"));
		
		
				
		  
       //  _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ESCAPE);
		_webcontrols.get().moveToElement("Move to sign in button",  GlobalSearchPageOR.getProperty("btnSearch"), false);
		_webcontrols.get().click("Clicked on search button", GlobalSearchPageOR.getProperty("btnSearch"));

		reportPass("Clicked on Search button", "Sucessfully clicked on search button", pageName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		_webcontrols.get().click("Clicked on quick search", GlobalSearchPageOR.getProperty("quicksearches"));
		
		
		_webcontrols.get().waitUntilElementIsVisible("My Searches", GlobalSearchPageOR.getProperty("savedsearches"), 5000);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_webcontrols.get().click("Clicked on expand parameter", GlobalSearchPageOR.getProperty("expandparameter"));
		_webcontrols.get().click("Clicked on delete search", GlobalSearchPageOR.getProperty("searchdelete"));
		_webcontrols.get().click("Clicked on ok button", GlobalSearchPageOR.getProperty("okbutton"));
		
		
		return flag;

	}
	public boolean globalSearchCluster() {
		boolean flag=false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		_webcontrols.get().waitUntilElementIsVisible("wait for search",
				GlobalSearchPageOR.getProperty("searcheverything"), 100);
		//_webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));
		_webcontrols.get().enterText("Search Topic Entered", GlobalSearchPageOR.getProperty("searcheverything"), "Disclosures - Timing");
		// _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ENTER);
		 _webcontrols.get().pressEnter("Searched Topic", GlobalSearchPageOR.getProperty("searcheverything"));
		  
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		_webcontrols.get().enterText("Topic Value", GlobalSearchPageOR.getProperty("topicname"), "Disclosures - Timing");
		//_webcontrols.get().enterText("Topic Value", GlobalSearchPageOR.getProperty("topicname"), "Banking and Banking Services");
		_webcontrols.get().click("Selected Topic dropdown", GlobalSearchPageOR.getProperty("topicValue"));

		  String enteredTopic =
				  _webcontrols.get().getDriver() .findElement(By.xpath(
				  "//input[@id='Topic']/ancestor::div/..//li[1]//input//following::p[2]")).
				  getText() .trim();
		  
       //  _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ESCAPE);
		_webcontrols.get().moveToElement("Move to sign in button",  GlobalSearchPageOR.getProperty("btnSearch"), false);
		_webcontrols.get().click("Clicked on search button", GlobalSearchPageOR.getProperty("btnSearch"));

		reportPass("Clicked on Search button", "Sucessfully clicked on search button", pageName);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//_webcontrols.get().waitUntilElementIsVisible("Wait for search criteria",
		//		pageGenericsOR.getProperty("searchCriteria"), 90);
		_webcontrols.get().click("Clicked on Search criteria dropdown",
				GlobalSearchPageOR.getProperty("searchCriteria"));

		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
		}

		// Validating search result
		List<WebElement> topics = _webcontrols.get().getDriver().findElements(
				By.xpath("//span[contains(text(),' Topic:')]//parent::div/following-sibling::div//i/parent::div"));

		for (WebElement webElement : topics) {
			System.out.println(webElement.getText().trim());
			if (webElement.getText().equalsIgnoreCase(enteredTopic)) {
				reportPass("Clicked on Search criteria drop down",
						"Verify selected values & sucessfully verified those criteria value", pageName);
				flag=true;
			}
		}

		return flag;

	}

public boolean SearchRequirementFilter() {
	boolean flag=true;
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	_webcontrols.get().waitUntilElementIsVisible("wait for search",
			GlobalSearchPageOR.getProperty("searcheverything"), 100);
	//_webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));
	_webcontrols.get().enterText("Search Topic Entered", GlobalSearchPageOR.getProperty("searcheverything"), "Disclosures - Timing");
	// _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ENTER);
	 _webcontrols.get().pressEnter("Searched Topic", GlobalSearchPageOR.getProperty("searcheverything"));
     
	 _webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));
	 _webcontrols.get().click("Click on Citation filter", GlobalSearchPageOR.getProperty("citationfilter"));
		/*
		 * _webcontrols.get().click("Clicked on requirement filter ",
		 * GlobalSearchPageOR.getProperty("requirementfilter"));
		 * _webcontrols.get().click("Clicked on cluster filter ",
		 * GlobalSearchPageOR.getProperty("clusterfilter"));
		 * 
		 * _webcontrols.get().click("Clicked on underreview ",
		 * GlobalSearchPageOR.getProperty("underreviewonly"));
		 * _webcontrols.get().click("Clicked on searched equirement  ",
		 * GlobalSearchPageOR.getProperty("searchonrequirement"));
		 */
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// _webcontrols.get().click("Clicked on Save search parameter ", GlobalSearchPageOR.getProperty("saveparameter"));
	_webcontrols.get().moveToElement("Move to sign in button",  GlobalSearchPageOR.getProperty("btnSearch"), false);
	_webcontrols.get().click("Clicked on search button", GlobalSearchPageOR.getProperty("btnSearch"));

	reportPass("Clicked on Search button", "Sucessfully clicked on search button", pageName);
	
	
	return flag;

}

public boolean SearchClusterFilter() {
	boolean flag=false;
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	_webcontrols.get().waitUntilElementIsVisible("wait for search",
			GlobalSearchPageOR.getProperty("searcheverything"), 100);
	//_webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));
	_webcontrols.get().enterText("Search Topic Entered", GlobalSearchPageOR.getProperty("searcheverything"), "Disclosures - Timing");
	// _webcontrols.get().getDriver().findElement(By.xpath("//body")).sendKeys(Keys.ENTER);
	 _webcontrols.get().pressEnter("Searched Topic", GlobalSearchPageOR.getProperty("searcheverything"));
	// _webcontrols.get().click("Click on Cluster tab", GlobalSearchPageOR.getProperty("ClusterTab"));
     
	 _webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));
	 
	 _webcontrols.get().click("Clicked on requirement filter ",GlobalSearchPageOR.getProperty("requirementfilter"));
	 try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// _webcontrols.get().click("Clicked on cluster filter ",GlobalSearchPageOR.getProperty("clusterfilter"));
		  
		
    // _webcontrols.get().click("Clicked on underreview ",GlobalSearchPageOR.getProperty("underreviewonly"));
    // _webcontrols.get().click("Clicked on searched equirement  ",GlobalSearchPageOR.getProperty("searchonrequirement"));
		 

	// _webcontrols.get().click("Clicked on Save search parameter ", GlobalSearchPageOR.getProperty("saveparameter"));
	//_webcontrols.get().moveToElement("Move to sign in button",  GlobalSearchPageOR.getProperty("btnSearch"), false);
	//_webcontrols.get().click("Clicked on search button", GlobalSearchPageOR.getProperty("btnSearch"));

	reportPass("Clicked on Search button", "Sucessfully clicked on search button", pageName);
	 try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return flag;

}

	public boolean clickSearchButton() {
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(10);
		
		boolean flag = false;
		_webcontrols.get().waitUntilElementIsVisible("wait for dropdown",
				GlobalSearchPageOR.getProperty("btnSearch"), 100);
		flag = _webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("btnSearch"));
		
		customWebControls.waitForPageLoad("");
		customWebControls.waitForLoadingToComplete();
		
		return flag;
	}
	
	public boolean clickSearchDropDown() {
//		boolean flag = false;
//		_webcontrols.get().waitUntilElementIsVisible("wait for dropdown",
//				GlobalSearchPageOR.getProperty("drpCmdSearch"), 100);
//		flag = _webcontrols.get().click("Expand global search dropdown ", GlobalSearchPageOR.getProperty("drpCmdSearch"));
//		return flag;
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(5);
		return customWebControls.click("", GlobalSearchPageOR.getProperty("drpCmdSearch"));
	}
	
	public boolean selectPrimaryTopic(String primaryTopic) {
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(5);
		
		String topicInputLocator = "xpath~//input[@id='Topic']";
		_webcontrols.get().enterText("Enter primary topic", topicInputLocator, primaryTopic);
		String primaryTopicCheckboxLocator = String.format("xpath~//input[@id='Topic']//parent::div//ul//li//p[contains(text(), '%s')]//ancestor::li//input", primaryTopic);
		customWebControls.waitForPageLoad("");
		return customWebControls.click("click check box", primaryTopicCheckboxLocator);
	}
	
	public boolean clickFilterButton(String buttonName) {
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(5);
		boolean flag = false;
		String buttonLocator = String.format(GlobalSearchPageOR.getProperty("filterButtonLocator"), buttonName);
		System.out.println(buttonLocator);
		customWebControls.waitForVisibilityOfElement("wait for filter button", buttonLocator, 5);
		flag = customWebControls.click("Click filter button", buttonLocator);
		return flag;
	}
	
	public boolean closeFilterMenu(String buttonName) {
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(5);
		boolean flag = false;
		String buttonLocator = String.format(GlobalSearchPageOR.getProperty("filterButtonLocator"), buttonName);
		System.out.println(buttonLocator);
		customWebControls.waitForVisibilityOfElement("wait for filter button", buttonLocator, 5);
		flag = customWebControls.clickUsingJSExecutor("Click filter button", buttonLocator);
		return flag;
	}
	
	public boolean clickFilterOptionButton(List<String> filterOptions) {
		clearAllFilterOptions();
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(5);
		boolean flag = filterOptions.stream().allMatch(filterOption -> {
			String buttonLocator = String.format(GlobalSearchPageOR.getProperty("filterOptionButtonLocator"), filterOption);
			customWebControls.waitForVisibilityOfElement(config, buttonLocator, 5);
			return customWebControls.click("Click filter option button", buttonLocator);
		});
		return flag;
	}
	
	private boolean clearAllFilterOptions() {
		CustomWebControls customWebControls = new CustomWebControls(_webcontrols.get().getDriver());
		customWebControls.setDefaultExplicitWaitTimeout(5);
		List<WebElement> menuOptionButtons = customWebControls.findElements(GlobalSearchPageOR.getProperty("filterOptionButtonList"));
		return menuOptionButtons.stream().allMatch(button -> {
			customWebControls.waitForVisibilityOfElement(config, button, 5);
			return customWebControls.click("Click option button", button);
		});
	}
}
