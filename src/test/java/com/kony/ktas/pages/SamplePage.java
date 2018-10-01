package com.kony.ktas.pages;

import com.kony.ktas.common.Log;
import com.kony.ktas.mobile.common.MobileBaseUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SamplePage extends MobileBaseUtils{
	
	MobileElement ele;
	
	
	
	 public SamplePage(AppiumDriver<MobileElement> driver){
		 
		   
	  new MobileBaseUtils(driver);
	 }
	

	public void sampleMethod() throws InterruptedException {
		
		Log.info("Intiated Sample Method...");
		ele=getMobileElement(200, 120);
		ele.click();
		Thread.sleep(20000);
		Log.info("Clicked on Mobile Element Yes");
	}

}
