package org.iitwf.healthcare.mmp.pm.pages;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private By validMsgBy = By.tagName("h3");

	protected WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

	}

	public String getLoginSuccessfulMsg() {
		return driver.findElement(validMsgBy).getText().trim();
	}

	public void selectModule(String moduleName) {
		// 2. Click on Schedule Appointment module.
		driver.findElement(By.xpath("//span[normalize-space()='" + moduleName + "']")).click();

	}

	public HashMap<String, String> fetchPatientPortalData() {
		System.out.println("#########################Actual Values######################################");
		System.out.println("actualDate--actualDoctor---actualTime--actualSymptom");
		String actualDate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		String actualTime = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText();
		String actualSymptom = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		String actualDoctor = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText();
		System.out.println(actualDate + "--" + actualDoctor + "---" + actualTime + "---" + actualSymptom);
		System.out.println("#########################Actual Values######################################");
		HashMap<String, String> actualHMap = new HashMap<String, String>();
		actualHMap.put("date", actualDate);
		actualHMap.put("doctor", actualDoctor);
		actualHMap.put("time", actualTime);
		actualHMap.put("sym", actualSymptom);
		return actualHMap;
	}

	public String getPatientName() {
		String expectedPatientName = driver.findElement(By.id("fname")).getDomAttribute("value");
		return expectedPatientName;
	}
	public String getUsername() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='username']")));
		String username = driver.findElement(By.xpath("//span[@class='username']")).getText();
		System.out.println("#########################Actual Values######################################");
		System.out.println("Actual UserName -- " + username );
		System.out.println("#########################Actual Values######################################");
		return username;
	}
}
