package com.kony.ktas.pages;

import com.kony.ktas.common.Log;
import com.kony.ktas.mobile.common.MobileBaseUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SetupProfileScreen extends MobileBaseUtils{
	
	MobileElement SetupProfileHeader;
	MobileElement AvatarNameTxtFld;
	MobileElement SelectTheme1;
	
	MobileElement UserAvailableTxt;
	MobileElement CotinueBtn;
	
	public SetupProfileScreen(AppiumDriver<MobileElement> driver){
		   
		new MobileBaseUtils(driver);
	}
	
	public boolean[] verifySetupProfileScreen() {
		
		Log.info("Intiated method to verify select your profile screen...");
		
		boolean Status[]=new boolean[2];
		
		SetupProfileHeader=getMobileElement("HeaderRichTxt");
		Status[01]=SetupProfileHeader.isDisplayed();
  	    Log.info("Status of headerTxt is ::"+Status[01]);
  	    
		AvatarNameTxtFld=getMobileElement("ProfileNametxtFld");
		Status[02]=AvatarNameTxtFld.isDisplayed();
  	    Log.info("Status of AvatarNameTxtFld is ::"+Status[02]);
  	    
		return Status;
	}
	
	public void enterProfileName(String ProfileName) {
		
		Log.info("Intiated Method to enter Profile Name ::" +ProfileName);
		clickAndEnterText("AvatarNametxtFld", ProfileName);
	}
	
	
	public void selectTheme() throws Exception {
		
		Log.info("Intiated method to Select a Theme1..");
		clickElement("PickTheme1");
	}
	
	public boolean verifyUserAvailableTxt() {
		
		Log.info("Intiated Method to verify UserAvailable Txt..");
		UserAvailableTxt=getMobileElement("lblMessage2");
		return UserAvailableTxt.isDisplayed()&&UserAvailableTxt.getText().equalsIgnoreCase("USERNAME AVAILABLE");
	}
	
	public void clickonContinueBtn() throws Exception {
		
		Log.info("Intiated method to click on Continue button..");
		clickElement("btnContinue");
		
	}


}
