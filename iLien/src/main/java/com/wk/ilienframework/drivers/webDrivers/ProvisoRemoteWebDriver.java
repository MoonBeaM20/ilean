package com.wk.ilienframework.drivers.webDrivers;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.wk.ilienframework.controlsLibrary.utilities.PropFileHandler;

public abstract class ProvisoRemoteWebDriver {

	PropFileHandler _properties = new PropFileHandler(new Properties());

	public static final String GRID_PORT_NO = "4444";
	public String localhost_Grid = _properties.readProperty("config", "HubIP");
	
	
	public String Node = "http://" + localhost_Grid + ":" + GRID_PORT_NO + "/wd/hub";

	protected abstract WebDriver getDriver();
}
