package com.wk.ilienframework.drivers.webDriverFactory;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

	public WebDriver getDriver(String browserName, String deviceName) {
		System.out.println("Inside get driver ---> browser name is --> "+browserName );

		if (browserName == null) {

			return null;
		}

		if (browserName.equalsIgnoreCase("FIREFOX")) {
			return new GetFirefoxDriver().getWebDriver();

		}
		if (browserName.equalsIgnoreCase("CHROME")) {
			return new GetChromeDriver().getWebDriver();
		}
		if (browserName.equalsIgnoreCase("CHROMEHEADLESS")) {
			return new GetChromeHeadLessDriver().getWebDriver();
		}
		if (browserName.equalsIgnoreCase("CHROMEINCOGNITO")) {
			return new GetIncognitoChromeDriver().getWebDriver();
		}

		if (browserName.equalsIgnoreCase("IE")) {
			return new GetIEDriver().getWebDriver();
		}
		
		
		return null;
	}

}
