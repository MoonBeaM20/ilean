package com.wk.ilien.testscripts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.wk.ilien.common.UtilLib;

public class Runner {

	Map<String, ArrayList<String>> data = UtilLib.readTestDataSheet("", "materiality_data.xlsx", "Sheet1");
	
	@Test
	public void main() {
		List<List<String>> res = new ArrayList<>();
		for(int i=0; i<2; i++) {
			List<String> concatList = new ArrayList<>();
			for(Entry<String, ArrayList<String>> entry : data.entrySet()) {
				String key = entry.getKey();
				List<String> values = entry.getValue();
				if(i < values.size()) {
					concatList.add(String.format("%s.%s", key, values.get(i)));
				}
			}
			res.add(concatList);
		}
		System.out.println(res);
	}
	
	@Test
	public void main2() {
		List<Map<String, String>> data = getSheetRowCount("", "materiality_data.xlsx", "Sheet1");
		System.out.println(data);
		Map<String, String> data2 = readDataFromRow("", "materiality_data.xlsx", "Sheet1", 2);
		System.out.println(data2);
	}
	
	public List<Map<String, String>> getSheetRowCount(String filePath, String fileName, String sheetName) {
		List<Map<String, String>> data = new ArrayList<>();
		Connection connection = null;
		Fillo fillo = new Fillo();
		try {
			connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String query = String.format("SELECT * FROM %s", sheetName);
			Recordset recordset = connection.executeQuery(query);
			recordset.getCount();
			List<String> fieldNames = recordset.getFieldNames();
			while(recordset.next()) {
				Map<String, String> record = new LinkedHashMap<>();
				for(String fieldName : fieldNames) {
					String value = recordset.getField(fieldName);
					if(StringUtils.isNotBlank(value)) {
						record.put(fieldName, value);
						data.add(record);
					}
				}
			}
			recordset.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}
		connection.close();
		return data;
	}
	
	private Map<String, String> readDataFromRow(String filePath, String fileName, String sheetName, int rowNum) {
		Map<String, String> data = new LinkedHashMap<>();
		Connection connection = null;
		Fillo fillo = new Fillo();
		try {
			connection = fillo.getConnection("./data/" + filePath + "/" + fileName);
			String query = String.format("SELECT * FROM %s", sheetName);
			Recordset recordset = connection.executeQuery(query);
			recordset.getCount();
			List<String> fieldNames = recordset.getFieldNames();
			int currentRow = 0;
			while(recordset.next()) {
				currentRow++;
				if(currentRow == rowNum) {
					for(String fieldName : fieldNames) {
						String value = recordset.getField(fieldName);
						if(StringUtils.isNotBlank(value)) {
							data.put(fieldName, value);
						}
					}
					break;
				}
			}
			recordset.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}
		connection.close();
		return data;
	}
	
}
