package com.wk.ileanframework.controlsLibrary.utilities;

import org.jasypt.util.text.BasicTextEncryptor;

import com.codoid.products.exception.FilloException;
import com.relevantcodes.extentreports.LogStatus;
import com.wk.ileanframework.reporting.Logger;
import com.wk.ileanframework.reporting.Reporting;

public class Ency {

	/**
	 * 
	 * <strong>EncryptString</strong>-
	 * 
	 * @since July 12, 2022
	 * @author samarendra
	 * @return String TODO
	 * @param toEncy
	 * @return
	 */
	public static String EncryptString(String toEncy) {

		try {
			BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
			textEncryptor.setPasswordCharArray("some-random-data".toCharArray());
			String myEncryptedText = textEncryptor.encrypt(toEncy);

			return myEncryptedText;

		} catch (Exception e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while Encrypting  details from String",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception while Encrypting data", getExceptionDetails(e)));
			return null;
		}

	}

	/**
	 * 
	 *<strong>DecryptString</strong>-
	 *@since July 11, 2022
	 *@author samarendra
	 *@return String
	 *TODO
	 *@param toDecy
	 *@return
	 */
	public static String DecryptString(String toDecy) {
		try {
			BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
			textEncryptor.setPasswordCharArray("some-random-data".toCharArray());
			String plainText = textEncryptor.decrypt(toDecy);
			// System.out.println("decripted :" + plainText);
			return plainText;

		} catch (Exception e) {
			Reporting.getLogger().log(LogStatus.FAIL, "Exception occured while decrypting  details from String",
					"For details refer "
							+ new Logger().writeExceptionLogs("Exception while decrypting data", getExceptionDetails(e)));
			return null;
		}

		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String encydata = Ency.EncryptString("welcome*123");

		String decydata = Ency.DecryptString(encydata);

		System.out.println("after ency" + encydata);

		System.out.println("after decy" + decydata);

	}

	/**
	 * 
	 * Get the exception details:-
	 * 
	 * @param e
	 * @return
	 */
	public static String getExceptionDetails(Exception e) {

		String stackTrace = "";
		for (StackTraceElement str : e.getStackTrace()) {

			stackTrace = stackTrace + str.toString() + "\n";
		}

		return e.fillInStackTrace().toString() + stackTrace;

	}

}
