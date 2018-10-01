package com.kony.ktas.pages;

import com.kony.ktas.common.Log;
import com.kony.ktas.mobile.common.MobileBaseUtils;

import io.appium.java_client.MobileElement;

public class SelectPassionScreen extends MobileBaseUtils{
	
	MobileElement WhatsPassion_HeadrTxt;
	MobileElement Passion_Beauty;
	MobileElement IamReadyBtn;
	
	public boolean[] verifyPassionScreen() {
		
		Log.info("Intiate method to verify a Passion Screen...");
		
        boolean Status[]=new boolean[3];
		
        WhatsPassion_HeadrTxt=getMobileElement("HeaderRichTxt");
		Status[01]=WhatsPassion_HeadrTxt.isDisplayed()&&WhatsPassion_HeadrTxt.getText().equalsIgnoreCase("WHAT?S YOUR PASSION?");
  	    Log.info("Status of headerTxt is "+Status[01]);
		
		Passion_Beauty=getMobileElement("btnClick1");
		Status[02]=Passion_Beauty.isDisplayed();
  	    Log.info("Status of Passion_Beauty is "+Status[02]);
  	    
		IamReadyBtn=getMobileElement("btnDone");
		Status[03]=IamReadyBtn.isDisplayed();
  	    Log.info("Status of AvatarNameTxtFld is "+Status[03]);
  	    
		return Status;
	}
	
	public void selectBeautyAsPassion() throws Exception {
		
		Log.info("Intiated method to select BeautyAsPassion");
		clickElement("btnClick1");
	}
	
    public void clickonIamReadyButton() throws Exception {
		
		Log.info("Intiated method to clickonIamReadyBtn");
		clickElement("btnDone");
	}
	

}
