package com.wk.ilienframework.drivers.webDriverFactory;

import java.io.File;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wk.ilienframework.drivers.webDrivers.ProvisoWebDriver;

class GetChromeHeadLessDriver implements ProvisoWebDriver {

	private WebDriver chromeDriver;

	private void setDriver() {

		if (chromeDriver == null) {

			// TODO Auto-generated method stub
			File file = new File("executors\\drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--headless");
			options.addArguments("window-size=1600x794");
			
			 
			options.addArguments("--test-type");
			// options.addArguments(
			// "load-extension=C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User
			// Data\\Default\\Extensions\\pcpaogkiacmmehpclbomfdhknjmndgpf");

			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.IGNORE);

			// chromeDriver = new C hromeDriver(capabilities);
			chromeDriver = new ChromeDriver(capabilities);
		}

	}

	public WebDriver getWebDriver() {
		// TODO Auto-generated method stub

		setDriver();

		return chromeDriver;
	}

}
