package com.wk.ilienframework.controlsLibrary.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilienframework.controlsLibrary.iLeanControls.ILeanControls;
import com.wk.ilienframework.reporting.Logger;
import com.wk.ilienframework.reporting.Reporting;

public class ExcelReader {

	/**
	 * 
	 * Fetch the column value in specified excel:-
	 * 
	 * @param sheetName
	 * @param testCaseName
	 * @param columnName
	 * @return
	 * @throws FilloException
	 */
	public String readExcel(String filePath, String fileName, String sheetName, String keyColName, String keyValue,
			String columnName) {
		String excelData = null;
		try {

			Fillo fillo = new Fillo();
			sheetName = validateSheetName(sheetName);
			Connection connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String strQuery = "Select * from " + sheetName + " where " + keyColName + "='" + keyValue + "'";
			Recordset recordset = connection.executeQuery(strQuery);

			while (recordset.next()) {
				excelData = recordset.getField(columnName);
			}

			recordset.close();
			connection.close();

		} catch (FilloException e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while fetching details from excels",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception while reading excel", getExceptionDetails(e)));
		}

		return excelData;
	}

	/**
	 * Fetch complete row data based upon column mapping:-
	 * 
	 * @param sheetName
	 * @param testCaseName
	 * @return
	 * @throws FilloException
	 */
	public Map<String, String> readExcel(String filePath, String fileName, String sheetName, String keyColName,
			String keyValue) {
		Map<String, String> colValMap = new HashMap<String, String>();
		try {

			Fillo fillo = new Fillo();
			sheetName = validateSheetName(sheetName);
			Connection connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String strQuery = "Select * from " + sheetName + " where " + keyColName + "='" + keyValue + "'";

			Recordset recordset = connection.executeQuery(strQuery);

			while (recordset.next()) {
				List<String> columns = recordset.getFieldNames();
				for (String col : columns) {
					if (!col.equalsIgnoreCase(keyColName))
						colValMap.put(col, recordset.getField(col));
				}
			}

			recordset.close();
			connection.close();
		} catch (FilloException e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while fetching details from excels",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception while reading excel", getExceptionDetails(e)));
		}

		return colValMap;
	}

	/**
	 * Fetch multiple column values of test case:-
	 * 
	 * @param sheetName
	 * @param testCaseName
	 * @return
	 * @throws FilloException
	 */
	public List<Map<String, String>> readExcelMultipleColValues(String filePath, String fileName, String sheetName,
			String testCaseName) {
		// Map<String, List<String>> colValuesMap = new HashMap<String,
		// List<String>>();
		List<Map<String, String>> colMap = new ArrayList<Map<String, String>>();
		try {
			sheetName = validateSheetName(sheetName);
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String strQuery = "Select * from " + sheetName + " where TestCaseName='" + testCaseName + "'";
			Recordset recordset = connection.executeQuery(strQuery);

			while (recordset.next()) {

				List<String> fieldNames = recordset.getFieldNames();
				Map<String, String> colValMap = new HashMap<String, String>();
				for (String col : fieldNames) {

					if (!col.contains("TestCaseName")) {

						colValMap.put(col, recordset.getField(col));

					}

				}

				colMap.add(colValMap);

			}

			recordset.close();
			connection.close();
		} catch (FilloException e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while fetching details from excels",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception while reading excel", getExceptionDetails(e)));
		}

		return colMap;
	}

	/**
	 * Fetch multiple column values of test case:-
	 * 
	 * @param sheetName
	 * @param testCaseName
	 * @return
	 * @throws FilloException
	 */
	public Map<String, String> readExcelMultipleColValues(String filePath, String fileName, String sheetName,
			String Key_Col1, String Val_Col2) {
		Map<String, String> colValuesMap = new HashMap<String, String>();

		try {

			sheetName = validateSheetName(sheetName);

			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String strQuery = "Select * from " + sheetName;
			Recordset recordset = connection.executeQuery(strQuery);
			List<String> fieldNames = recordset.getFieldNames();
			if (fieldNames.contains(Key_Col1)) {
				while (recordset.next()) {

					colValuesMap.put(recordset.getField(Key_Col1), recordset.getField(Val_Col2));
				}

			} else {

				System.out.println("Please verify the column Names:-");
			}

			recordset.close();
			connection.close();
		} catch (FilloException e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while fetching details from excels",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception while reading excel", getExceptionDetails(e)));
		}

		return colValuesMap;
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

	
	public static void main(String[] args) {

		// Map<String, String> shippingInformation = new
		// ExcelReader().readExcel("pageGenerics",
		// "PageGenericTestData.xlsx", "ShippingAndBillingPage", "UserInfo",
		// "Credit Card Information");
		Map<String, String> shippingInformation = new ExcelReader().readExcel("sanity", "SanityTestData.xlsx",
				"US-HOTFIX-LoadBalancer", "TC_Name", "verifyEppUserColorSwatch");
	}

	private String validateSheetName(String sheetName) {

		if (sheetName.contains("-")) {

			sheetName = sheetName.replace("-", "");
		}

		return sheetName;
	}
}
