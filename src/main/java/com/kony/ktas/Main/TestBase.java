package com.kony.ktas.Main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import com.kony.ktas.common.Log;
import com.kony.ktas.util.ReadPropertyData;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

/**
 * Simple TestNG test which demonstrates being instantiated via a DataProvider in order to supply multiple browser combinations.
 *
 * @author Neil Manvar
 */
public class TestBase  {

   /* public String buildTag = System.getenv("BUILD_TAG");

    public String username = System.getenv("SAUCE_USERNAME");

    public String accesskey = System.getenv("SAUCE_ACCESS_KEY");*/
	
	

	
	private static volatile String TESTOBJECTAPI_KEY_Android=null;
	private static volatile String MOBILE_NAME_Android=null;
	private static volatile String MOBILE_VERSION_Android=null;
	private static volatile String PACKAGE_NAME_Android=null;
	private static volatile String ACTIVITY_NAME_Android=null;
	private static volatile String appiumVersion_Android=null;
	private static volatile String APP_ID_Android=null;
	
	
	  
	private static volatile String TESTOBJECTAPI_KEY_iOS=null;
	private static volatile String MOBILE_NAME_iOS=null;
	private static volatile String MOBILE_VERSION_iOS=null;
	private static volatile String appiumVersion_iOS=null;
	private static volatile String APP_ID_iOS=null;
	private static volatile String BUNDLE_ID_iOS=null;
	

	private static volatile ReadPropertyData data = null;
	

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
	 public ThreadLocal <AppiumDriver<MobileElement>> driver = new ThreadLocal <AppiumDriver<MobileElement>>();
  

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod
     * @return Two dimensional array of objects with browser, version, and platform information
     */
   @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                new Object[]{"Android", 3},
                new Object[]{"iOS",4},
                
        };
    }

    /**
     * @return the {@link WebDriver} for the current thread
     */
    @SuppressWarnings("rawtypes")
	public AppiumDriver<MobileElement> getMobileDriver() {
        return driver.get();
    }

    /**
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part of the test run.
     * @param os Represents the operating system to be used as part of the test run.
     * @param methodName Represents the name of the test case that will be used to identify the test on Sauce.
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */
    @SuppressWarnings("static-access")
	protected void createDriver(String platform, int deviceID,String methodName)
            throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = null;
        capabilities=new DesiredCapabilities();

        // set desired capabilities to launch appropriate browser on Sauce
        
        if(platform.equalsIgnoreCase("Android")) {
        	
        	System.out.println("Creating Driver instance for Android");
        	
        	
        	try {
        	
        	//Printing Desired capabilities-Android
        		
        	Thread.sleep(15000);  //Mandatory as reading values is mixing up
        		
        	data=new ReadPropertyData("./resources/propfiles/MobileInventory.properties");
	  	   
        	
        	TESTOBJECTAPI_KEY_Android=data.getValue("TESTOBJECTAPI_KEY_Android");
        	appiumVersion_Android=data.getValue("appiumVersion_Android");
	  	    APP_ID_Android=data.getValue("APP_ID_Android");
	  	    
        	PACKAGE_NAME_Android= data.getValue("PACKAGE_NAME");
	  	    ACTIVITY_NAME_Android=data.getValue("ACTIVITY_NAME");
	  	    
        	MOBILE_NAME_Android=ReadPropertyData.readMI(Integer.toString(deviceID),"MOBILE_NAME_Android");
        	MOBILE_VERSION_Android=ReadPropertyData.readMI(Integer.toString(deviceID),"MOBILE_VERSION_Android");
	  	    
	  	    
        	
        	 
        	System.out.println("*****************************************************");
        	System.out.println("*****************************************************");
        	
	  	    System.out.println("**Android Test Object API **::"+TESTOBJECTAPI_KEY_Android);
	  	    
	  	    System.out.println("**Android Device Name is **::"+MOBILE_NAME_Android);
	  	    System.out.println("**Android Platform version is **::"+MOBILE_VERSION_Android);
	  	    System.out.println("**Android App Package Name **::"+ PACKAGE_NAME_Android);
	  	    System.out.println("**Android App Activity Name **::"+ ACTIVITY_NAME_Android);
	  	    
	  	    System.out.println("**Android Appium Version **::"+appiumVersion_Android);
	  	    System.out.println("**Android Test Object App ID **::"+APP_ID_Android);
	  	    
	  	    System.out.println("*****************************************************");
      	    System.out.println("*****************************************************");
	  	    
	    	  
	    	  capabilities.setCapability("testobjectApiKey", TESTOBJECTAPI_KEY_Android);
	    	  capabilities.setCapability("testobject_app_id",APP_ID_Android);
	    	  
	    	  capabilities.setCapability("appPackage", PACKAGE_NAME_Android);
	  	      capabilities.setCapability("appActivity", ACTIVITY_NAME_Android);
		      
	  	      capabilities.setCapability("deviceName",MOBILE_NAME_Android); 
	  	      capabilities.setCapability("platformVersion",MOBILE_VERSION_Android); 
	  	      capabilities.setCapability("platformName",platform);
			  capabilities.setCapability("appiumVersion", appiumVersion_Android);
			  
			  capabilities.setCapability("deviceOrientation","portrait");
			  capabilities.setCapability("name", methodName);
			  
			    /*String android_Version=ReadPropertyData.readMI(Integer.toString(deviceID),"MOBILE_VERSION");
		  	    float android_deviceVersion = Float.parseFloat(android_Version);
		  	    
		  	    if(Float.compare(android_deviceVersion, (float) 7.0)>=0)
				{
		  	    	capabilities.setCapability("automationName", "uiautomator2");
					System.out.println("**Automation Name is **:: uiautomator2");
				}*/
		  	    
		  	    
		        
		  	    
		  	    //driver.set(new AndroidDriver<MobileElement>(new URL(data.getValue("SERVICE_URL_Android")), capabilities));
			  
		  	    // set current sessionId
		        String id = getMobileDriver().getSessionId().toString();
		        System.out.println("Android Session :"+id);
		        sessionId.set(id);
		      
		      System.out.println("Caps were Done..for real_Android");
        }
		      

	    	  catch(Exception e) {
	    		  
	    		  System.out.println("Exception while launching IOS app..");
	    		  e.printStackTrace();
	    	  }
	      }
	      
	      if(platform.equalsIgnoreCase("iOS")) {
	    	  
	    	  System.out.println("Creating Driver instance for iOS");
	    	  
	    	  try {
	    		  
	    		  //Printing Desired capabilities-iOS
	    		  
	    		 data = new ReadPropertyData("./resources/propfiles/MobileInventory.properties");
	    	
	          	TESTOBJECTAPI_KEY_iOS=data.getValue("TESTOBJECTAPI_KEY_IOS");
	          	BUNDLE_ID_iOS=data.getValue("BUNDLE_ID");
	          	appiumVersion_iOS=data.getValue("appiumVersion_iOS");
	  	  	    APP_ID_iOS=data.getValue("APP_ID_iOS");
	  	  	    
	          	MOBILE_NAME_iOS=ReadPropertyData.readMI(Integer.toString(deviceID),"MOBILE_NAME_iOS");
	          	MOBILE_VERSION_iOS=ReadPropertyData.readMI(Integer.toString(deviceID),"MOBILE_VERSION_iOS");
	          	
	    		  
	    	 System.out.println("*****************************************************");
		     System.out.println("*****************************************************");
			      
			 System.out.println("**iOS Test Object API **::"+TESTOBJECTAPI_KEY_iOS);
			 System.out.println("**iOS Device Name is **::"+MOBILE_NAME_iOS);
			
			 System.out.println("**iOS Platform version is **::"+MOBILE_VERSION_iOS);
			 System.out.println("**iOS Bundle ID is **::"+BUNDLE_ID_iOS);
			 System.out.println("**iOS Appium Version **::"+appiumVersion_iOS);
			 System.out.println("**iOSTest Object App ID **::"+APP_ID_iOS);
			        
			  System.out.println("*****************************************************");
		      System.out.println("*****************************************************");
	    	  
	    	  capabilities.setCapability("testobjectApiKey", TESTOBJECTAPI_KEY_iOS);
	    	  
	    	  capabilities.setCapability("deviceName",MOBILE_NAME_iOS); 
	  	      capabilities.setCapability("deviceOrientation","portrait"); 
	  	      capabilities.setCapability("platformVersion",MOBILE_VERSION_iOS); 
	  	      capabilities.setCapability("platformName",platform);
			  capabilities.setCapability("appiumVersion", appiumVersion_iOS);
			
		      capabilities.setCapability("bundleId", BUNDLE_ID_iOS);
		      //capabilities.setCapability("app","sauce-storage:ABBA_20.ipa");
		      capabilities.setCapability("testobject_app_id",APP_ID_iOS);
		      
		      capabilities.setCapability("name", methodName);
		      
		      
		       /* String iOS_Version=ReadPropertyData.readMI(Integer.toString(deviceID),"MOBILE_VERSION");
		  	    float iOS_deviceVersion = Float.parseFloat(iOS_Version);
		  	    
		  	    if(Float.compare(iOS_deviceVersion, (float) 9.3)>=0)
				{
		  	    	capabilities.setCapability("automationName", "XCUITest");
					Log.info("Automation Name is : xcuitest");
				}*/
		      
		    
		      
		        
			  //driver.set(new IOSDriver<MobileElement>(new URL(data.getValue("SERVICE_URL_iOS")), capabilities));
		      
			  
			  // set current sessionId
			   String id = getMobileDriver().getSessionId().toString();
			   System.out.println("IOS Session :"+id);
		       sessionId.set(id);
		        
		      System.out.println("Caps were Done..for real_iOS");
		      
	    	  }
	    	  
	    	  catch(Exception e) {
	    		  
	    		  System.out.println("Exception while launching IOS app..");
	    		  e.printStackTrace();
	    	  }
	      }
          

        /*if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }*/


       
    }

    /**
     * Method that gets invoked after test.
     * Dumps browser log and
     * Closes the browser
     */
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
    	
    	System.out.println("Inside a tear down method...");
    	driver.get().closeApp();
    	//WebDriver d = (WebDriver)driver;
    	
       //((JavascriptExecutor) d).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
    	driver.get().quit();
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) driver.get()).executeScript("sauce:context=" + text);
    }
}
