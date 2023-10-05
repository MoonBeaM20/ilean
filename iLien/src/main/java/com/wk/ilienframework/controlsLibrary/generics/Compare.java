package com.wk.ilienframework.controlsLibrary.generics;

import com.relevantcodes.extentreports.LogStatus;
import com.wk.ilienframework.reporting.Reporting;


public class Compare {

	
	public static boolean compareObjects(Object expectedObject, Object actualObject){
		try{
		if(expectedObject!=null && actualObject!=null){
			if(expectedObject.getClass().getTypeName().equals(actualObject.getClass().getTypeName())){
			if(expectedObject.equals(actualObject)){
				return true;
			}else{
				return false;
			}
			}else{
				
				Reporting.getLogger().log(LogStatus.INFO, "Object have different Type - Expected Object type : "+expectedObject.getClass().getTypeName()
						+" Actual Object type : "+actualObject.getClass().getTypeName());	
				return false;
			}
		}
		else{
			Reporting.getLogger().log(LogStatus.INFO, "Object is empty - Expected Object : "+expectedObject+" Actual Object : "+actualObject);
			return false;
		}
		}catch(Exception e){
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while comparing object! "+e.getMessage());
			return false;
		}
	}
	
		
	public static boolean compareString(String expectedString, String actualString){
		try{
		if(expectedString!=null && actualString!=null){
			if(expectedString.equals(actualString)){
				return true;
			}else{
				return false;
			}
		}else{
			Reporting.getLogger().log(LogStatus.INFO, "String is empty Expected String : "+expectedString+" Actual String : "+actualString);
			return false;
		}
		}catch(Exception e){
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while comparing String! "+e.getMessage());
			return false;
		}
	}
	
	public static boolean compareInt(int expectedInt, int actualInt){
		try{
			if(expectedInt == actualInt){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while comparing Integer values! "+e.getMessage());
			return false;
		}
	}
	
	public static boolean compareFloat(float expectedFloat, float actualFloat){
		try{
			if(expectedFloat == actualFloat){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while comparing Float values! "+e.getMessage());
			return false;
		}
	}
	
	public static boolean compareDouble(double expectedDouble, double actualDouble){
		try{
			if(expectedDouble == actualDouble){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while comparing Double values! "+e.getMessage());
			return false;
		}
	}
}
