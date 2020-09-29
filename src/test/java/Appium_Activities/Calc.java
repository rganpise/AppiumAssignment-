package appium_project;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Calculator {
	AppiumDriver<MobileElement> driver = null;
	
    @BeforeClass
	
    public void beforeClass() throws MalformedURLException {
    	 // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "RZ8K21E8L1A");
		caps.setCapability("deviceName", "samsung SM-G615F");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.oneplus.calculator");
        caps.setCapability("appActivity", ".Calculator");
        caps.setCapability("noReset", true);
        
        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:472/wd/hub");
       // URL appServer = new URL("http://localhost:472/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        
    }
	    @Test
	    public void mul(){
	       
	        driver.findElementById("digit_7").click();
	        driver.findElementById("op_mul").click();
	        driver.findElementById("digit_4").click();
	        driver.findElementById("eq").click();
	        String output=driver.findElementById("result").getText();
	        System.out.println(output);
	        
	                
	    }

}
