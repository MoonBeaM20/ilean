package com.wk.ilienframework.controlsLibrary.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilien.common.UtilLib;
import com.wk.ilienframework.reporting.Logger;
import com.wk.ilienframework.reporting.Reporting;

public class PropFileHandler {

	Properties properties = null;

	public PropFileHandler(Properties properties) {

		this.properties = properties;
	}

	/**
	 * Read from property file.
	 *
	 * @param fileName
	 * @param property
	 * @return
	 */
	public String readProperty(String fileName, String property) {
		try {
			String value = null;
			String file = "//data//" + fileName + ".properties";
			Properties propObj = UtilLib.getPropertyObject(System.getProperty("user.dir") + file);
			if (propObj.isEmpty()) {
				//Properties propObj = UtilLib.getPropertyObject(System.getProperty("user.dir") + file);
				//InputStream inPropFile = null;
				//inPropFile = new FileInputStream(file);
				//properties.load(inPropFile);
				System.out.println("File 1'" + file + "' not found");
				//value = propObj.getProperty(property);
				//inPropFile.close();
			} else {
				value = propObj.getProperty(property);
			}
			return value;
		} catch (Exception e) {
			Reporting.getLogger().log(LogStatus.FAIL,
					"Exception occured while fetching details from property file :" + fileName, "For details refer "
							+ new Logger().writeExceptionLogs("Exception while reading excel", getExceptionDetails(e)));
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Write into property file.
	 *
	 * @param fileName
	 * @param property
	 * @param value
	 * @throws IOException
	 */
	public void writeProperty(String fileName, String property, String value) throws IOException {
		try {
			String file = "data/" + fileName + ".properties";
			File f = new File(file);
			if (!f.exists()) {
				f.createNewFile();
			}
			FileInputStream in = new FileInputStream(file);
			// Properties props = new Properties();
			properties.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream(file);
			properties.setProperty(property, value);
			properties.store(out, null);
			out.close();
		} catch (Exception e) {
			Reporting.getLogger().log(LogStatus.FAIL,
					"Exception occured while fetching details from property file :" + fileName, "For details refer "
							+ new Logger().writeExceptionLogs("Exception while reading excel", getExceptionDetails(e)));
			e.printStackTrace();
		}
	}

	/**
	 * Read all properties from properties file
	 *
	 * @param filePath
	 *            - Path of the file
	 * @return - Properties class object with all properties from the file.
	 */
	public Properties readAllPropertiesFromPropertiesFile(String filePath) {
		// Properties properties = new Properties();
		try {
			InputStream inPropFile = null;
			if (new File(filePath).exists()) {
				inPropFile = new FileInputStream(filePath);
				properties.load(inPropFile);
				inPropFile.close();
			} else {
				System.out.println("File does not exist at location " + filePath);
			}
		} catch (Exception e) {
			Reporting.getLogger().log(LogStatus.FAIL,
					"Exception occured while fetching details from property file :" + filePath, "For details refer "
							+ new Logger().writeExceptionLogs("Exception while reading excel", getExceptionDetails(e)));
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * Write all properties to properties file
	 *
	 * @param filePath
	 * @param properties
	 *            - type java.util.Propeties
	 */
	public void writeAllPropertiesToPropertiesFile(String filePath, Properties properties) {
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(filePath));
			properties.store(out, null);
			out.close();
		} catch (Exception e) {
			Reporting.getLogger().log(LogStatus.FAIL,
					"Exception occured while fetching details from property file :" + filePath, "For details refer "
							+ new Logger().writeExceptionLogs("Exception while reading excel", getExceptionDetails(e)));
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Get the exception details:-
	 * 
	 * @param e
	 * @return
	 */
	public String getExceptionDetails(Exception e) {

		String stackTrace = "";
		for (StackTraceElement str : e.getStackTrace()) {

			stackTrace = stackTrace + str.toString() + "\n";
		}

		return e.fillInStackTrace().toString() + stackTrace;

	}

}
