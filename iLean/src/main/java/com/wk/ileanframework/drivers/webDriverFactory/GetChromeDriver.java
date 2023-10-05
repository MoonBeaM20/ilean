package com.wk.ileanframework.drivers.webDriverFactory;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wk.ileanframework.drivers.webDrivers.ProvisoWebDriver;

/**
 * @author Samarendra
 *
 */
class GetChromeDriver implements ProvisoWebDriver {

	private WebDriver chromeDriver;

	private void setDriver() {

		if (chromeDriver == null) {

			// TODO Auto-generated method stub
			File file = new File("executors\\drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			//options.addArguments("--headless");
			//options.addArguments("window-size=2048x1536");
			
			/*
			 * if(isPlatformLinux()) { options.addArguments("--headless");
			 * options.addArguments("--no-sandbox");
			 * options.addArguments("window-size=2400x1800"); //
			 * options.setAcceptInsecureCerts(true);
			 * capabilities.setCapability("PlatformName" , Platform.LINUX);
			 * 
			 * 
			 * }
			 */
			 
			options.addArguments("--test-type");
			// options.addArguments(
			// "load-extension=C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User
			// Data\\Default\\Extensions\\pcpaogkiacmmehpclbomfdhknjmndgpf");

			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.IGNORE);

			// chromeDriver = new C hromeDriver(capabilities);
			chromeDriver = new ChromeDriver(capabilities);
			((JavascriptExecutor)chromeDriver).executeScript("document.body.style.transform='0.5'");
		}

	}

	public WebDriver getWebDriver() {
		
		setDriver();

		return chromeDriver;
	}
	/*
	 * public static boolean isPlatformLinux() { String osName =
	 * System.getProperty("os.name").toLowerCase(); return osName.contains("nix")||
	 * osName.contains("nux")|| osName.contains("aix"); }
	 */

}
