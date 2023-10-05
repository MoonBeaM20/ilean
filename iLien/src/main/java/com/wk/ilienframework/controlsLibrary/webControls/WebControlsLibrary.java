package com.wk.ilienframework.controlsLibrary.webControls;

import java.util.List;
import java.util.Properties;

import com.wk.ilienframework.controlsLibrary.generics.ProductGenerics;
import com.wk.ilienframework.controlsLibrary.utilities.ExcelReader;
import com.wk.ilienframework.controlsLibrary.utilities.PropFileHandler;


public abstract class WebControlsLibrary implements ProvisoWebControls {

	private List pageData;
	private ProductGenerics pageGenerics;

	/**
	 * 
	 * Property file reader:-
	 * 
	 */
	public PropFileHandler propFileHandler() {

		return new PropFileHandler(new Properties());
	}

	/**
	 * 
	 * Property file reader:-
	 * 
	 */
	public ExcelReader excelReader() {

		return new ExcelReader();
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

	/**
	 * 
	 * get Page Data:-
	 */
	public List getStoreData() {

		return this.pageData;
	}

	/**
	 * 
	 * Set Page Data:-
	 */

	public void setPageData(List pageData) {

		this.pageData = pageData;
	}

	
	/**
	 * 
	 * Get product generics information
	 */
	public ProductGenerics getProductGenerics() {

		return this.pageGenerics;
	}

	/**
	 * 
	 * Set product generics information
	 */

	public void setProductGenerics(ProductGenerics pageGenerics) {

		this.pageGenerics = pageGenerics;

	}
}
