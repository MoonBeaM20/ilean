package com.wk.ileanframework.drivers.webDriverFactory;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wk.ileanframework.controlsLibrary.utilities.PropFileHandler;
import com.wk.ileanframework.drivers.webDrivers.ProvisoWebDriver;

class GetFirefoxDriver implements ProvisoWebDriver {
	private WebDriver firefoxDriver;

	/*
	 * This method is used to set the WebDriver based upon the Firefox Driver:-
	 */
	private void setDriver() {
	    try{

		if (firefoxDriver == null) {
			// TODO Auto-generated method stub
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			 PropFileHandler propFileHandler = new PropFileHandler(new Properties());
			 if (propFileHandler.readProperty("config", "ProjectName").equalsIgnoreCase("ApolloPerson"))
			 {
				 firefoxProfile.setPreference("network.proxy.type",1);
				 firefoxProfile.setPreference("network.proxy.http","web-proxy.austin.hpicorp.net");
				 firefoxProfile.setPreference("network.proxy.http_port","8080");
			 }

			firefoxProfile.setPreference("xpinstall.signatures.required", false);
			firefoxProfile.setPreference("security.mixed_content.block_active_content", false);
			firefoxProfile.setPreference("security.mixed_content.block_display_content", true);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/plain, application/vnd.ms-excel, text/csv,text/comma-separated-values, application/octet-stream");
			firefoxProfile.setAcceptUntrustedCertificates(true);
		    firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
		    capabilities.setBrowserName("firefox");
		    capabilities.setCapability("acceptInsecureCerts", true);
			File file = new File("executors\\drivers\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
			firefoxDriver = new FirefoxDriver(capabilities);
		}
	}
		catch(Exception e){
		    e.printStackTrace();
		}

	}

	/*
	 * This method is used to get the WebDriver of the Object:-
	 */
	public WebDriver getWebDriver() {

		setDriver();

		return firefoxDriver;
	}
}
