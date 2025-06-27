package org.iitwf.healthcare.mmppixel;

import java.io.IOException;
import java.util.HashMap;

import org.iitwf.healthcare.mmp.pm.pages.AdminHomePage;
import org.iitwf.healthcare.mmp.pm.pages.AdminLoginPage;
import org.iitwf.healthcare.mmp.pm.pages.HomePage;
import org.iitwf.healthcare.mmp.pm.pages.LoginPage;
import org.iitwf.healthcare.mmp.pm.pages.PatientRegistrationPage;
import org.iitwf.healthcare.mmp.pm.pages.UsersPage;
import org.iitwf.lib.FrameworkLibrary;
import org.iitwf.lib.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class PatientApprovalTests extends FrameworkLibrary{
	
@Test(groups={"regression", "patientapproval" ,"ui"})
public void MMP_PAT_001_PatientApproval() throws IOException, InterruptedException
{
	ExtentTest extentTest = extent.createTest("##########Patient Approval Tests######################");
	 
	launchBrowser(prop.getProperty("patientReg_url"));
	extentTest.info("Loading the website successfully");
	ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
	String screenshotPath = screenshotUtil.captureScreenshot("RegPage");
	extentTest.addScreenCaptureFromPath(screenshotPath, "RegistrationPage of MMP WebSite");
	PatientRegistrationPage prPage = new PatientRegistrationPage(driver);
	HashMap<String,String> regHMap =prPage.registerPatient();
	System.out.println("Registration Details :::" + regHMap);
	String expectedUsername = regHMap.get("userName");
	screenshotUtil.captureScreenshot("PAT_001_PatientRegistration");
	
	launchBrowser(prop.getProperty("admin_url"));
	extentTest.info("Loading the website successfully");
	AdminLoginPage adLoginPage = new AdminLoginPage(driver);
	AdminHomePage adHomePage = adLoginPage.login(prop.getProperty("admin_username"), prop.getProperty("admin_password"));
	screenshotPath = screenshotUtil.captureScreenshot("AdminHomePage");
	extentTest.addScreenCaptureFromPath(screenshotPath, "HomePage of MMP Admin WebSite");
	adHomePage.selectModule("Users");
	UsersPage uPage = new UsersPage(driver);
	uPage.approveUser(regHMap);
	
	launchBrowser(prop.getProperty("patient_url"));
	extentTest.info("Loading the website successfully");
	LoginPage lPage = new LoginPage(driver);
	HomePage hPage = lPage.login(expectedUsername,regHMap.get("password"));
	System.out.println("#########################Expected Values######################################");
	System.out.println("Expected UserName -- " + expectedUsername );
	System.out.println("#########################Actual Values######################################");
	 screenshotPath = screenshotUtil.captureScreenshot("HomePage");
	extentTest.addScreenCaptureFromPath(screenshotPath, "HomePage of MMP WebSite");
	String actualUsername = hPage.getUsername();
	screenshotUtil.captureScreenshot("PAT_001_PatientApproval_Data");
	
	Assert.assertEquals(actualUsername, expectedUsername);
}

}