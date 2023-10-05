package com.wk.ilienframework.drivers.webDriverFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wk.ilienframework.drivers.webDrivers.ProvisoWebDriver;

class GetChromeDeviceDriver implements ProvisoWebDriver {

	private WebDriver chromeDeviceDriver;

	@Override
	public WebDriver getWebDriver() {
		// TODO Auto-generated method stub
		return null;

	}

	public WebDriver getWebDriver(String deviceName) {
		// TODO Auto-generated method stub
		System.out.println("Get Chrome Driver Device Name :************" + deviceName);
		setDriver(deviceName);
		return chromeDeviceDriver;

	}

	public void setDriver(String deviceName) {
		// TODO Auto-generated method stub

		if (chromeDeviceDriver == null) {

			 File file = new File("executors\\drivers\\chromedriver.exe");
		      System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		      
		      Map<String, String> mobileEmulation = new HashMap();
		      mobileEmulation.put("deviceName", deviceName);
		      
		      Map<String, Object> chromeOptions = new HashMap();
		      
		      chromeOptions.put("mobileEmulation", mobileEmulation);
		      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		      capabilities.setCapability("chromeOptions", chromeOptions);
		      chromeDeviceDriver = new ChromeDriver(capabilities);
	
		}
	}

}
