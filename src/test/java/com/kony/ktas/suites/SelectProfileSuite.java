package com.kony.ktas.suites;

import java.net.MalformedURLException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.kony.ktas.common.Log;
import com.kony.ktas.pages.SetupProfileScreen;
import com.kony.ktas.pages.WelComeScreen;
import com.kony.ktas.util.ReadPropertyData;

public class SelectProfileSuite extends DriverClass{
	
	SoftAssert s_assert;
	SetupProfileScreen setpProfile;
	
	//Reading Test data from DataRepo.properties
	String profileName=ReadPropertyData.readTd(Integer.toString(1),"avatarName1");
	
	@BeforeClass(alwaysRun=true)
	public void startApp() throws MalformedURLException {
	    
		s_assert=new SoftAssert();
		//launchApp();
		setpProfile=new SetupProfileScreen(driver);
	}
	
	@Test(groups = { "Sanity"},description="To Verify profileSetup screen",priority = 2)
	public void verifySetupProfileScreen() throws Exception {
		
		
		s_assert.assertEquals(setpProfile.verifySetupProfileScreen(), true, "SetupProfileScreen is not as expected....");
		setpProfile.enterProfileName(profileName);
		setpProfile.selectTheme();
		setpProfile.verifyUserAvailableTxt();
		setpProfile.clickonContinueBtn();
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void setSetResult(ITestResult result) throws Exception{
		
		driver.executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
	}
	
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		
		Log.info("Closing the driver instance...");
		driver.closeApp();
		driver.quit();
	}
	
	

}
