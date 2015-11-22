package com.pack.base;


import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterClass;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.pack.base.logpack.TestLogger;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;

public class TestBaseSetup {
	private static WebDriver driver;
	static String driverPath = "D:\\chromedriver_win32\\";
	static org.apache.log4j.Logger tLog = TestLogger.createLogger();
	
	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) {
		if (driver == null) {
			switch (browserType) {
				case "chrome":
					driver = initChromeDriver(appURL);
					break;
				case "firefox":
					driver = initFirefoxDriver(appURL);
					break;
				default:
					System.out.println("browser : " + browserType
							+ " is invalid, Launching Firefox as browser of choice..");
					driver = initFirefoxDriver(appURL);
			}	
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath
				+ "chromedriver.exe");
		DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.SEVERE);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		WebDriver driver = new ChromeDriver(caps);
        tLog.info("Chromedriver launched");	
		driver.manage().window().maximize();
		//driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.navigate().to(appURL);
		return driver;
	}
	
	//@Parameters({ "browserType", "appURL" })
	//@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	
	public void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
    }
	
//	@AfterClass
	public void tearDown() {
		analyzeLog();
		driver.quit();
	}
	
	
}
