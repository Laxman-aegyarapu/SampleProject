package com.kony.ktas.pages;

import com.kony.ktas.common.Log;
import com.kony.ktas.mobile.common.MobileBaseUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WelComeScreen extends MobileBaseUtils{
	
	MobileElement headerTxt;
	MobileElement subTxt_AreYouAnABO;
	MobileElement btnYes;
	MobileElement btnNO;
	
	
	public WelComeScreen(AppiumDriver<MobileElement> driver){
		   
		new MobileBaseUtils(driver);
	}
	
	public boolean[] verifyWelcomeScreen() {
		
		Log.info("Intiated Method to verify welcome screen..");
		
		boolean Status[]=new boolean[10];
		
		headerTxt=getMobileElement("HeaderRichTxt");
		Status[01]=headerTxt.isDisplayed();
  	    Log.info("Status of headerTxt is "+Status[01]);
  	    
		subTxt_AreYouAnABO=getMobileElement("AreYouAnABOtxt");
		Status[02]=subTxt_AreYouAnABO.isDisplayed();
  	    Log.info("Status of subTxt_AreYouAnABO is "+Status[02]);
  	    
		btnYes=getMobileElement("YesButton");
		Status[03]=btnYes.isDisplayed();
  	    Log.info("Status of btnYes is "+Status[03]);
  	    
		btnNO=getMobileElement("NoButton");
		Status[04]=btnNO.isDisplayed();
  	    Log.info("Status of btnNO is "+Status[04]);
  	    
		return Status;
  
	}
	
	public void clickonNobtn() throws Exception {
		
		Log.info("Intiated method to click on No button in welcome screen..");
		clickElement("NoButton");
    }
	
}
