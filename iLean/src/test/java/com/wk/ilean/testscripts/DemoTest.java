package com.wk.ilean.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DemoTest {

	/*
	 * @Test public void test1() {
	 * System.out.println("This is Sample Test in pipeline"); }
	 */
	 @Test public void login() { // TODO Auto-generated method stub 
	    	
		  System.setProperty("webdriver.chrome.driver", "D:\\DemoWorkspace/chromedriver.exe"); 
		  WebDriver driver=new ChromeDriver();
		  driver.manage().window().maximize(); 
		 // driver.get("https://qa-lbawb.gsdwkglobal.com");
		  driver.get("https://stg-proviso.wolterskluwer.com"); 

		  //Locating first suggestion button and click on it. Why first suggestion bcz whatever we type hat will come always at first
				WebDriverWait wait = new WebDriverWait(driver, Duration(5));
				WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"username\"]")));
				//ele.click();
				ele.sendKeys("admin.expert@abcbank.com");
				
				
				WebElement ele1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"password\"]")));
				//ele1.click();
				ele1.sendKeys("Test@123");
				
		 
		  WebElement login=driver.findElement(By.xpath("//button[text()=' Login ']")); 

		  login.click();
		  
		    }

	private long Duration(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
}
