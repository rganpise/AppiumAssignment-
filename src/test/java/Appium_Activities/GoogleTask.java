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
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

public class GoogleTask {
	AppiumDriver<MobileElement> driver = null;

	@BeforeMethod

	public void beforeMethod() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceId", "RZ8K21E8L1A");
		caps.setCapability("deviceName", "samsung SM-G615F");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "com.google.android.apps.tasks");
		caps.setCapability("appActivity", ".ui.TaskListsActivity");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:472/wd/hub");
		// URL appServer = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	@Test
	public void createTasks(){

		//driver.findElementByAccessibilityId("Create new task").click();
		driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.tasks:id/tasks_fab\")")).click();
		driver.findElementById("add_task_title").click();
		driver.findElementById("add_task_title").sendKeys("Complete Activity with Google Tasks");
		driver.findElementById("add_task_done").click();
		//2nd task adding
		driver.findElementByAccessibilityId("Create new task").click();
		driver.findElementById("add_task_title").click();
		driver.findElementById("add_task_title").sendKeys("Complete Activity with Google Keep");
		driver.findElementById("add_task_done").click();
		//3rd task adding
		driver.findElementByAccessibilityId("Create new task").click();
		driver.findElementById("add_task_title").click();
		driver.findElementById("add_task_title").sendKeys("Complete the second Activity Google Keep");
		driver.findElementById("add_task_done").click();
		//
		List<MobileElement> tasks = driver.findElementsById("task_name");
		int numberofTasks=tasks.size();
		System.out.println("Number of tasks created: "+numberofTasks);
		int actualtasks=3;
		assertEquals(actualtasks, numberofTasks);

	}
	
	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

}
