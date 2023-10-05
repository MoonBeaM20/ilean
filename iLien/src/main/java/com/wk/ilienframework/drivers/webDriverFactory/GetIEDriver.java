package com.wk.ilienframework.drivers.webDriverFactory;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wk.ilienframework.drivers.webDrivers.ProvisoWebDriver;

class GetIEDriver implements ProvisoWebDriver {
	private WebDriver IEDriver;

	public void setDriver() {

		// TODO Auto-generated method stub
		if (IEDriver == null) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); 
			
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("enablePersistentHover", false);
			
			/*capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true) ;
			capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");*/

			File file = new File("executors\\drivers\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			// ieDriver = new InternetExplorerDriver(capabilities);
			IEDriver = new InternetExplorerDriver(capabilities);
			 IEDriver.manage().deleteAllCookies();
			//IEDriver.manage().deleteAllCookies();
		}

	}

	public WebDriver getWebDriver() {
		// TODO Auto-generated method stub

		setDriver();

		return IEDriver;
	}

}
