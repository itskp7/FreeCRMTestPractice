package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
// Of course we can use it in TestBase class but we can use it in the child class too (if globally) thats why here on top (below two variables)
	
	public static WebDriver driver;  // what if I don't write static  // there was redline in initElements below driver
	public static Properties prop;   // that is why theses 2 are made public while writing login page
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase()  {
	
			try {
				prop = new Properties();
				
				String projectPath = System.getProperty("user.dir");  // here user.dir =C:\Selenium_Workspace\FreeCRMTestPractice\
				FileInputStream ip = new FileInputStream(projectPath + "\\src\\main\\java"
						+ "\\com\\crm\\qa\\config\\config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\SeleniumJars\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\SeleniumJars\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else {
			System.out.println("No browser value found");
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();  // class created in util package
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		driver.manage().window().maximize();;
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		//now we will launch the url
		driver.get(prop.getProperty("url"));
	}

}
