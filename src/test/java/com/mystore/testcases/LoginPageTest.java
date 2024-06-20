/**
 * 
 */
package com.mystore.testcases;
import javax.jdo.annotations.Index;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

/**
 * @author Prashant Singh
 *
 */
public class LoginPageTest extends BaseClass {
	 private LoginPage loginPage ;
	 private IndexPage indexPage;
	//private HomePage homePage;
	
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		loginPage = new LoginPage();
		launchApp(browser);
		
		
	
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = {"Smoke","Sanity"})
	public void loginTest() throws Throwable {
		
		Log.startTestCase("loginTest");
		Log.info("User is Going to Click on Login Button to Navigate to Login page");
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
		
		
		//loginPage.clickLoginButton();
		Log.info("Enter Username and Password");
		Thread.sleep(3000);
		loginPage.setUserName();
		loginPage.clickContinueButton();
		loginPage.setPassword();
		Log.info("user is going to click on SignIn");
		loginPage.login();
	    Log.info("Verifying if user is able to login");
	    loginPage.verifyLoggedUser();
	    Log.info("Login is Sucess");
	    Log.endTestCase("loginTest");
	}

}
