package org.iitwf.healthcare.mmp.pm.pages;

import java.time.Duration;
import java.util.HashMap;

import org.iitwf.healthcare.mmppixel.FutureDate;
import org.iitwf.healthcare.mmppixel.RandomUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientRegistrationPage {

	protected WebDriver driver;
	
	// Locators for various elements on the Register Patient page can be added here
		By firstNameBy = By.id("firstname");
		By lastNameBy = By.id("lastname");
		By dobBy = By.id("datepicker");
		By licenseNumberBy = By.id("license");
		By ssnBy = By.id("ssn");
		By stateBy = By.id("state");
		By cityBy = By.id("city");
		By addressBy = By.id("address");
		By zipCodeBy = By.id("zipcode");
		By ageBy = By.id("age"); 
		By heightBy = By.id("height");
		By weightBy = By.id("weight");
		By pharmacyBy = By.id("pharmacy");
		By pharmacyAddressBy = By.id("pharma_adress");
		By emailBy = By.id("email");
	    By phoneBy = By.id("phone");
	    By usernameBy = By.id("username");
	    By passwordBy = By.id("password");
	    By confirmPasswordBy = By.id("confirmpassword");
	    By selectSecurityQuestionBy = By.id("security");
	    By securityAnswerBy = By.id("answer");
	    By registerBy = By.name("register");
	    By resetButtonBy = By.xpath("//input[@type='reset']");
	    
	public PatientRegistrationPage(WebDriver driver) {
		this.driver = driver;

	} 

	public HashMap<String, String> registerPatient() throws InterruptedException {
		String fName = RandomUtils.generateRandomString(6);
		String lName = RandomUtils.generateRandomString(6);
		String licenseNumber = RandomUtils.generateRandomNumberDigitOf(8);
		String ssn = RandomUtils.generateRandomNumberDigitOf(9);
		String userName = RandomUtils.generateRandomString(6);
		String password = RandomUtils.generateRandomPassword(9);
		
		driver.findElement(firstNameBy).sendKeys(fName); 
		driver.findElement(lastNameBy).sendKeys(lName);
		driver.findElement(dobBy).sendKeys(RandomUtils.generateRandomNumberDigitOf(8));
	 // driver.findElement(licenseNumberBy).sendKeys(licenseNumber);
		driver.findElement(licenseNumberBy).sendKeys("25639852");
		driver.findElement(ssnBy).sendKeys(ssn);
		driver.findElement(stateBy).sendKeys(RandomUtils.generateRandomString(6));
		driver.findElement(cityBy).sendKeys(RandomUtils.generateRandomString(6));
		driver.findElement(addressBy).sendKeys(RandomUtils.generateRandomString(9));
		driver.findElement(zipCodeBy).sendKeys(RandomUtils.generateRandomNumberDigitOf(5));
		driver.findElement(ageBy).sendKeys(RandomUtils.generateRandomNumberDigitOf(2));
		driver.findElement(heightBy).sendKeys(String.valueOf(RandomUtils.generateRandomInteger(50, 100)));
		driver.findElement(weightBy).sendKeys(String.valueOf(RandomUtils.generateRandomInteger(20, 100)));
		driver.findElement(pharmacyBy).sendKeys(RandomUtils.generateRandomString(6));
		driver.findElement(pharmacyAddressBy).sendKeys(RandomUtils.generateRandomString(12));
		driver.findElement(emailBy).sendKeys(RandomUtils.generateRandomEmailID(6));
		driver.findElement(usernameBy).sendKeys(userName);
		driver.findElement(passwordBy).sendKeys(password); 
		driver.findElement(confirmPasswordBy).sendKeys(password);
		driver.findElement(selectSecurityQuestionBy).sendKeys("what is your pet name"); 
		driver.findElement(securityAnswerBy).sendKeys(RandomUtils.generateRandomString(3)); 
		driver.findElement(registerBy).click(); 
		
		Thread.sleep(50);
		acceptAlert();

		System.out.println("#########################Registration page Values######################################");
		System.out.println("fName--userName---password--ssn--licenseNumber");
		System.out.println(fName + "--" + userName + "---" + password + "---" + ssn + "---" + licenseNumber);
		System.out.println("#########################Registration page Values######################################");

		HashMap<String, String> regHMap = new HashMap<String, String>();
		regHMap.put("fName", fName);
		regHMap.put("userName", userName);
		regHMap.put("password", password);
		regHMap.put("ssn", ssn);
		regHMap.put("licenseNumber", licenseNumber);

		return regHMap;

	}


	public void clickMenu(String menuName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions
				.textToBePresentInElementLocated(By.xpath("//span[normalize-space()='" + menuName + "']"), "Users"));
		driver.findElement(By.xpath("//span[normalize-space()='" + menuName + "']")).click();
	}

	public void clickPatientName(String ssn, String fName) {
		// td[normalize-space()='478496633']/preceding-sibling::td/a[normalize-space()='ATFirstNametucr']
		driver.findElement(By.xpath(
				"//td[normalize-space()='" + ssn + "']/preceding-sibling::td/a[normalize-space()='" + fName + "']"))
				.click();
	}

	private void acceptAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		System.out.println("Alert message :: " + alert.getText());
		alert.accept();
	}

}