package com.wk.ilienframework.drivers.webDriverFactory;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wk.ilienframework.drivers.webDrivers.ProvisoWebDriver;

class GetFirefoxDriverWithProxy implements ProvisoWebDriver {
	private WebDriver firefoxDriverWithProxy;

	private void setDriver() {
	    try{

		if (firefoxDriverWithProxy == null) {
			// TODO Auto-generated method stub
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
            try {
            	File proxy = new File("executors\\runners\\setProxy.vbs");
                Runtime.getRuntime().exec( "wscript "+proxy.getAbsolutePath() );
               
             }
             catch( IOException e ) {
                e.printStackTrace();
             }

            FirefoxProfile firefoxProfile = new FirefoxProfile();
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
			firefoxDriverWithProxy = new FirefoxDriver(capabilities);
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

		return firefoxDriverWithProxy;
	}
	
}
	


