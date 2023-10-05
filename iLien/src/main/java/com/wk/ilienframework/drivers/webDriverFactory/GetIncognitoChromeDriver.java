package com.wk.ilienframework.drivers.webDriverFactory;

import java.io.File;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wk.ilienframework.drivers.webDrivers.ProvisoWebDriver;

class GetIncognitoChromeDriver implements ProvisoWebDriver {
	private WebDriver chromeDriver;

	private void setDriver() {
		if (this.chromeDriver == null) {
			File file = new File("executors\\drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments(new String[] { "--disable-extensions" });
			options.addArguments(new String[] { "--incognito" });

			options.addArguments(new String[] { "--test-type" });

			capabilities.setCapability("chromeOptions", options);

			capabilities.setCapability("unexpectedAlertBehaviour", UnexpectedAlertBehaviour.IGNORE);

			this.chromeDriver = new ChromeDriver(capabilities);
		}
	}

	public WebDriver getWebDriver() {
		setDriver();

		return this.chromeDriver;
	}
}
