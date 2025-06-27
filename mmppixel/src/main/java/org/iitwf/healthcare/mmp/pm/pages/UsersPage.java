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

public class UsersPage {

	protected WebDriver driver;

	public UsersPage(WebDriver driver) {
		this.driver = driver;
 
	}

	public void approveUser(HashMap<String, String> regHMap) {
		String fName = regHMap.get("fName");
		String ssn = regHMap.get("ssn");
		clickPatientName(fName, ssn);
		acceptUser();

		System.out.println("#########################User Page Values######################################");
		System.out.println("fName--ssn");
		System.out.println(fName + "--" + ssn );
		System.out.println("#########################User Page Values######################################");
	}

	public void clickPatientName(String fName, String ssn) {
		driver.findElement(By.xpath(
				"//td[normalize-space()='" + ssn + "']/preceding-sibling::td/a[normalize-space()='" + fName + "']"))
				.click();
	}

	private void acceptUser() {
		select("id", "sapproval", "Accepted");
		click("xpath", "//input[@value='Submit']");
		acceptAlert();
	}

	private void acceptAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		System.out.println("Alert message :: " + alert.getText());
		alert.accept();
	}

	private void select(String locator, String locatorRef, String value) {
		Select dropdown;
		if (locator.equals("id")) {
			dropdown = new Select(driver.findElement(By.id(locatorRef)));
			dropdown.selectByVisibleText(value);
			// dropdown.selectByIndex(2);
		}
		
	}
		public void click(String locator, String locatorRef) {
			if (locator.equals("id")) {
				driver.findElement(By.id(locatorRef)).click();
			} else if (locator.equals("name")) {
				driver.findElement(By.name(locatorRef)).click();
			} else if (locator.equals("xpath")) {
				driver.findElement(By.xpath(locatorRef)).click();
			}
		}
	}