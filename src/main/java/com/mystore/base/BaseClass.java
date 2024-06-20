package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.ietf.jgss.Oid;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * @author Prashant Singh BaseClass is used to load the config file and Initialize 
 * WebDriver
 *  
 */
public class BaseClass {
	public static Properties prop;
    //public static WebDriver driver;
	// Declare ThreadLocal Driver
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	//loadConfig method is to load the configuration
	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void loadConfig() {
		//calling Extent report Method from ExtentManager class to
		// to initialize all the setup for reporting.
		ExtentManager.setExtent();
		
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		// Get Driver from threadLocalmap
		
		
		return driver.get();
		
	}
	


	public void launchApp(String browserName) {
		// String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver());
			
			
			/* ChromeOptions chromeOptions = new ChromeOptions();
			    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
			    driver = new ChromeDriver(chromeOptions);
			*/
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			
			/* FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			  driver = getDriver();
			driver = new FirefoxDriver(firefoxOptions);
			*/
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			
			/*EdgeOptions edgeoptions = new EdgeOptions();
			edgeoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			  driver = getDriver();
			driver = new EdgeDriver(edgeoptions);
			*/
		}
		
		//Maximize the screen
		getDriver().manage().window().maximize();
		//Delete all the cookies
		getDriver().manage().deleteAllCookies();
		//Implicit TimeOuts
		//getDriver().manage().timeouts().pageLoadTimeout(seconds);
		//(Integer.parseInt(prop.getProperty("implicitWait")),TimeUnit.SECONDS);
		//PageLoad TimeOuts
		//getDriver().manage().timeouts().pageLoadTimeout
		//(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),TimeUnit.SECONDS);
		//Launching the URL
		getDriver().get(prop.getProperty("url"));
		getDriver().navigate().refresh();
		System.out.println("*****************************************************************");
		System.out.print("FOUND THE PROPERTIES URL" + (prop.getProperty("url")));
	}

	@AfterSuite(groups = { "Smoke", "Regression","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
}
