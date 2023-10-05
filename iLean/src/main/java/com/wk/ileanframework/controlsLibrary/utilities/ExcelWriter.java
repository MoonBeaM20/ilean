package com.wk.ileanframework.controlsLibrary.utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wk.ileanframework.reporting.Logger;
import com.wk.ileanframework.reporting.Reporting;

import java.io.PrintStream;

public class ExcelWriter {
	public void writeExcel(String filePath, String fileName, String sheetName, String keyColName, String keyValue,
			String columnName, String excelData) {
		try {
			Fillo fillo = new Fillo();
			sheetName = validateSheetName(sheetName);
			Connection connection = fillo.getConnection("./data/" + filePath + "/" + fileName);

			String strQuery = "Update " + sheetName + " Set " + columnName + " ='" + excelData + "'" + " where "
					+ keyColName + " ='" + keyValue + "'";

			connection.executeUpdate(strQuery);

			connection.close();
		} catch (FilloException e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while fetching details from excels",
					"For details refer " + new Logger().writeExceptionLogs("Exception while reading excel",
							e.fillInStackTrace().toString()));
		}
	}

	public void writeEntireRow(String filePath, String fileName, String sheetName, String[] columnName,
			String[] columnValue) {
		Fillo fillo = new Fillo();
		sheetName = validateSheetName(sheetName);
		Connection connection;
		int count=0;
		String[] tempValues=null;
		String colName="";
		String colValue="";
		try {
			if(columnName.length==columnValue.length) {
				count=columnName.length;
				tempValues=new String[count];
				for(int i=0;i<columnName.length;i++) {
					if(i==columnName.length-1) {
						colName=colName+columnName[i];
					}
					else {
					colName=colName+columnName[i]+",";
					}
				}
			for(int i=0;i<columnValue.length;i++) {
				if(i==columnValue.length-1) {
					colValue=colValue+"'"+columnValue[i]+"'";
				}
				else {
				colValue=colValue+"'"+columnValue[i]+"'"+",";
				}
			}
			
			connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String strQuery = "INSERT INTO " + sheetName + "(" + colName + ") VALUES("+colValue+")";
			connection.executeUpdate(strQuery);

			connection.close();
			}
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public String getExceptionDetails(Exception e) {
		String stackTrace = "";
		StackTraceElement[] arrayOfStackTraceElement;
		int j = (arrayOfStackTraceElement = e.getStackTrace()).length;
		for (int i = 0; i < j; i++) {
			StackTraceElement str = arrayOfStackTraceElement[i];

			stackTrace = stackTrace + str.toString() + "\n";
		}
		return e.fillInStackTrace().toString() + stackTrace;
	}

	public static void main(String[] args) {
		//update the exsisting data
		//note system wont allow to access - symbol in sheet name
		new ExcelWriter().writeExcel("sanity", "SanityTestData.xlsx", "Sheet1", "Name",
				"teja", "Data1", "prabhu");
		
		//insert a new data below is the code
		/*String[] columname= {"Name","Data1"};
		String[] columval= {"teja","abcd"};
		new ExcelWriter().writeEntireRow("sanity", "SanityTestData.xlsx", "Sheet1",columname,columval );
		System.out.println("pass");*/
	}

	private String validateSheetName(String sheetName) {
		if (sheetName.contains("-")) {
			sheetName = sheetName.replace("-", "");
		}
		return sheetName;
	}
}
