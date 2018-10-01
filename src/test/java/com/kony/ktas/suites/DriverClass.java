package com.kony.ktas.suites;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.kony.ktas.common.Log;
import com.kony.ktas.mobile.common.MobileBaseUtils;
import com.kony.ktas.util.ReadPropertyData;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverClass extends MobileBaseUtils{
	
	
	private static String USERNAME = null;
	private static String ACCESS_KEY = null;
	private static String URL =null;
	
	
	public static AppiumDriver<MobileElement> driver=null;
	
	
	/*public AndroidDriver<MobileElement> launchApp() throws MalformedURLException {
		
		Log.info("Intiated Method to Launch the application on Saucelabs...");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("platformName", "Android");
	    capabilities.setCapability("deviceName", "Samsung Galaxy S8 GoogleAPI Emulator");
	    capabilities.setCapability("platformVersion", "7.0");
	    capabilities.setCapability("app", "https://s3.ap-south-1.amazonaws.com/mybuckkony/amplify14.apk");
	    capabilities.setCapability("browserName", "");
	    capabilities.setCapability("deviceOrientation", "portrait");
	    capabilities.setCapability("appiumVersion", "1.8.1");
	    
	    capabilities.setCapability("appPackage", "com.orgname.ABBAfe");
	    capabilities.setCapability("appActivity", "com.orgname.ABBAfe.ABBAfe");
	 
	    driver = new AndroidDriver<>(new URL(URL), capabilities);
	  
	    return (AndroidDriver<MobileElement>) driver;
	}*/
	
    public AndroidDriver<MobileElement> launchApp() throws MalformedURLException {
		
		Log.info("Intiated Method to Launch the application on Saucelabs...");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
	    capabilities.setCapability("platformName", ReadPropertyData.readMI(Integer.toString(1),"MOBILE_OS"));
	    capabilities.setCapability("deviceName", ReadPropertyData.readMI(Integer.toString(1),"MOBILE_NAME_ID"));
	    capabilities.setCapability("platformVersion", ReadPropertyData.readMI(Integer.toString(1),"MOBILE_VERSION_Android"));
	    
	    capabilities.setCapability("app", ReadPropertyData.readMI("APK_PATH"));
	    capabilities.setCapability("browserName", "");
	    capabilities.setCapability("deviceOrientation", "portrait");
	    capabilities.setCapability("appiumVersion", ReadPropertyData.readMI("appiumVersion_Android"));
	    
	    capabilities.setCapability("appPackage", ReadPropertyData.readMI("PACKAGE_NAME"));
	    capabilities.setCapability("appActivity", ReadPropertyData.readMI("ACTIVITY_NAME"));
	 
	    USERNAME=ReadPropertyData.readMI("USERNAME");
	    ACCESS_KEY=ReadPropertyData.readMI("ACCESS_KEY");
	    URL="https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	    
	    driver = new AndroidDriver<>(new URL(URL), capabilities);
	  
	    return (AndroidDriver<MobileElement>) driver;
	}

}
