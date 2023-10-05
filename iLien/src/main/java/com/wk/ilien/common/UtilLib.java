/**
 * 
 */
package com.wk.ilien.common;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilienframework.reporting.Reporting;



public class UtilLib extends PageGenerics {
	

	public static Properties getPropertyObject(String propertyFile) {    	
    	try {
			Properties obj = new Properties();			
			FileInputStream objfile = new FileInputStream(propertyFile);
			obj.load(objfile);
			objfile.close();
			return obj;
			/*
			 * FileInputStream ip = new FileInputStream("Config/Config.properties");
			 * prop.load(ip); return prop;
			 */
		} catch (Exception e) {
			System.out.println("prop" + e.getMessage());
			return new Properties();
		}
    }
	
	public static int getTimeoutVal() {   		
    	try {	
    		//ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Properties propObj = getPropertyObject(System.getProperty("user.dir") + "//data//config.properties");
			System.out.println("abc  " + propObj);
			//System.out.println("abc  " + getPropertyObject(System.getProperties().toString()));
    		//Properties propObj = getPropertyObject( ".//data//config.properties");
    		;
    		//FileInputStream inProp = new FileInputStream(Paths.get("/data/config.properties"));
    		//InputStream file = Files.newInputStream(Paths.get("/data/config.properties")) ;
    		//InputStream file = loader.getResourceAsStream("/Proviso/data/config.properties");
    		//propObj.load(file);
    		//file.close();
			System.out.println("abcd  " + Integer.parseInt(propObj.getProperty("DefaultTimeout")));
			return Integer.parseInt(propObj.getProperty("DefaultTimeout"));
			
		} catch (Exception e) {
			System.out.println("time" + e.getMessage());
			return -1;
		}
    }
    
    public static Properties setPropertyObject(String propFile) {
		try {						
			return getPropertyObject(System.getProperty("user.dir") + "//ObjectRepository/" + propFile + ".properties");				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}	
    
    public static String getURLFromConfig() {		
    	String url = "";    	
		String env = System.getProperty("env");
		Properties propObj = getPropertyObject(System.getProperty("user.dir") + "//data//config.properties");
		//Properties propObj = getPropertyObject( ".//data//config.properties");
		url = propObj.getProperty(env.toUpperCase() + "-" + "URL");		
		return url;
	}	
          
    private String validateSheetName(String sheetName) {
		if (sheetName.contains("-")) {
			sheetName = sheetName.replace("-", "");
		}
		return sheetName;
	}
    
	public Map<String, String> readSpecificColumnValues(String filePath, String fileName, String sheetName,
			String columnName) {
		HashMap<String, String> colValMap = new HashMap<String, String>();
		Connection connection = null;
		try {
			Fillo f = new Fillo();
			sheetName = this.validateSheetName(sheetName);
			connection = f.getConnection("./data/" + filePath + "/" + fileName);
			String strQuery = "Select * from  " + sheetName;
			Recordset recordset = connection.executeQuery(strQuery);
			int i = 1;
			while (recordset.next()) {
				String columns = recordset.getField(columnName);
				if (columns.isEmpty() || columns.equalsIgnoreCase("") || columns.equalsIgnoreCase(null)) {
					break;
				}
				colValMap.put(columnName + i, columns);
				i++;

			}
			recordset.close();
			connection.close();
		} catch (Exception e) {
		  reportException("Read data from excel", "Exception occured while reading data from excel ","Data Sheet" + sheetName,e);
		}
		return colValMap;
	}
	
	public static String readSpecificColumnValue(Map<String, ArrayList<String>> tdMap,String columnName,int index) {		
		String colVal = "";
		try {						
			if (!tdMap.isEmpty()) {				
				colVal = tdMap.get(columnName).get(index-1);
			}			
		} catch (Exception e) {					
			Reporting.getLogger().log(LogStatus.FAIL, "Reading data from column " + columnName, "Exception occured while reading data from column " + columnName + " at row number " + index);
			
		}
		return colVal;
	}
	
	public static String getTestDataFileNameFromConfig() {
		try {				
			Properties propObj = getPropertyObject(System.getProperty("user.dir") + "//data//config.properties");	
			//Properties propObj = getPropertyObject( ".//data//config.properties");
			return propObj.getProperty("TestDataFile");
		} catch (Exception e) {			
			return null;
		}		
	}
	
	public static Map<String, ArrayList<String>> readTestDataSheet(String filePath, String fileName, String sheetName) {
		HashMap<String, ArrayList<String>> testDataMap = new HashMap<String, ArrayList<String>>();
		System.out.println("XXX*****XXXX:: filePath :: " +filePath +" ,FileName:: "+fileName);
		Connection connection = null;
		try {
			Fillo f = new Fillo();	
			System.out.println("XXXXX Object Created.....XXXXX ::: "+f.toString());
			connection = f.getConnection("./data/" + filePath + "/" + fileName);
			System.out.println("XXXXX Connection Created.....XXXXX ::: "+ connection.toString());
			if (sheetName.contains("-CRM")) {
				sheetName = sheetName.replace("-CRM", "");
			}
			String strQuery = "Select * from  " + sheetName;
			Recordset recordset = connection.executeQuery(strQuery);			
			ArrayList<String> cols = recordset.getFieldNames();
			String colName;
			int colCount = cols.size();					
			for (int i=0;i<colCount;i++) {
				ArrayList<String> colValues = new ArrayList<String>();
				recordset = connection.executeQuery(strQuery);
				colName = cols.get(i);					
				while (recordset.next()) {
					String colVal = recordset.getField(colName);
					if (colVal.isEmpty() || colVal.equalsIgnoreCase("") || colVal.equalsIgnoreCase(null)) {
						break;
					}					
					colValues.add(colVal);								
				}				
				testDataMap.put(colName, colValues);					
			}			
			recordset.close();
			connection.close();
		} catch (Exception e) {
			//catchBlockReporter(e, "Reading test data", "Exception occured while reading data from excel",
			//		"Data Sheet" + sheetName);
			Reporting.getLogger().log(LogStatus.FAIL, "Reading test data", "Exception occured while reading data from sheet " + sheetName);
		}
		return testDataMap;
	}	
	
	public static String getHashMapAsString(HashMap<String, String> argument,String delimeter){
		String local="";
		Set<Entry<String, String>> set = argument.entrySet();
		for(Entry<String, String> SET:set){
			//local=local+"<br>"+SET.getKey()+":"+SET.getValue();
			local=local+"<br>"+SET.getKey()+delimeter+SET.getValue();
			//local=local+" "+SET.getKey()+":"+SET.getValue();
			
		}
		return local;
	}
	
	/**
	 * Gets current and time
	 * @return String
	 * @author samarendra
	 * @since Oct 8, 2020 8:48:21 PM
	*/
	public static String getCurentDateTime(String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat); 
		String currentDateTimeString=df.format(new Date());	
		return currentDateTimeString;
	}
	public static String getRandomString(String initial) {
		   Random rand = new Random();

		    String str = initial+"_"+rand.ints(48, 123)

		                .filter(num -> (num<58 || num>64) && (num<91 || num>96))

		    .limit(15)

		    .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)

		          .toString();
		    System.out.println(str);
		    return str;
	
	}
	
}
