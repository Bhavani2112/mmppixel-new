package org.iitwf.healthcare.mmp.pm.pages;

import java.time.Duration;
import java.util.HashMap;

import org.iitwf.healthcare.mmppixel.FutureDate;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MessagesPage {

	protected WebDriver driver;
	public MessagesPage(WebDriver driver)
	{
		this.driver = driver;

	}
	public HashMap<String, String> sendMessage(String expectedPatientName, String expectedReason,String expectedMessage)
	{
		
		String expectedDate = FutureDate.generateFutureDate(0, "dd-MM-yyyy", "Asia/Kolkata");
	//	String expectedDate = FutureDate.generateFutureDate(0, "dd-MM-yyyy");
	//	String expectedDate = "02-07-2025";
		driver.findElement(By.id("subject")).sendKeys(expectedReason);
		driver.findElement(By.id("message")).sendKeys(expectedMessage);
		driver.findElement(By.xpath("//input[@value='Send']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alrt = driver.switchTo().alert();
		String alrtMsg = alrt.getText();
		System.out.println("Alert Message ::  " + alrtMsg);
		alrt.accept();
		
	   //Expected Result
	   System.out.println("#########################Expected Values######################################");
	   System.out.println("expectedPatientName--expectedReason---expectedMessage---expectedDate");
	   System.out.println(expectedPatientName+"--"+expectedReason+"---"+expectedMessage+"---"+expectedDate);
	   System.out.println("#########################Expected Values######################################");
	   
	   HashMap<String,String> expectedHMap = new HashMap<String,String>();
	   expectedHMap.put("patientName", expectedPatientName);
	   expectedHMap.put("reason", expectedReason);
	   expectedHMap.put("msg", expectedMessage);
	   expectedHMap.put("date", expectedDate);
	   return expectedHMap;
	   
	   
	}
	 

}
