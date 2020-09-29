package appium_project;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PopupCard {
	AppiumDriver<MobileElement> driver = null;

	@BeforeMethod

	public void beforeMethod() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceId", "RZ8K21E8L1A");
		caps.setCapability("deviceName", "samsung SM-G615F");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:472/wd/hub");
		// URL appServer = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void alchemyPopupCard() throws InterruptedException{
		//navigate to webpage
		driver.get("https://www.training-support.net/selenium");
		driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Popups\"))"));
		driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Popups']")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Sign In\")")).click();
		//login with correct credentials
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).sendKeys("admin");
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).sendKeys("password");
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
		String confirmationMessageValid=driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
		System.out.println(confirmationMessageValid);
		assertEquals(confirmationMessageValid, "Welcome Back, admin");

		//login with incorrect credentials
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Sign In\")")).click();
		
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).clear();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")")).sendKeys("admin");
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).clear();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")")).sendKeys("nopassword");
		driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
		String confirmationMessageInvalid=driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
		System.out.println(confirmationMessageInvalid);
		assertEquals(confirmationMessageInvalid, "Invalid Credentials");
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

}
