package appium_project;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

public class GoogleKeep {
	AppiumDriver<MobileElement> driver = null;

	@BeforeMethod

	public void beforeMethod() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceId", "RZ8K21E8L1A");
		caps.setCapability("deviceName", "samsung SM-G615F");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "com.google.android.keep");
		caps.setCapability("appActivity", ".activities.BrowseActivity");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:472/wd/hub");
		// URL appServer = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void createNote(){
		//create new note
		driver.findElementByAccessibilityId("New text note").click();

		driver.findElementById("editable_title").click();
		driver.findElementById("editable_title").sendKeys("New note for testing");
		driver.findElementById("edit_note_text").click();
		driver.findElementById("edit_note_text").sendKeys("Description");

		driver.findElementByAccessibilityId("Open navigation drawer").click();
		String newTitleName=driver.findElementById("index_note_title").getText();
		System.out.println("New title Name : "+newTitleName);
		assertEquals(newTitleName, "New note for testing");

	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

}
