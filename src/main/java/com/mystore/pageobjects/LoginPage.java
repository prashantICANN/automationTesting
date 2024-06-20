/**
 * 
 */
package com.mystore.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * @author Prashant Singh
 *  
 */
public class LoginPage extends BaseClass {
	
	
	
	Action action= new Action();
	
	
	//Elements to login******************************
     
	@FindBy(xpath = "//div/span[@id='nav-link-accountList-nav-line-1']")
	private WebElement loginButton;
	
	@FindBy(id="ap_email")
	private WebElement userName;
	
	@FindBy(id="continue")
	private WebElement continueButton;
	
	@FindBy(id="ap_password")
	private WebElement password;

	@FindBy(id="signInSubmit")
	private WebElement signInBtn;
	
	//Elements to create Account**********************
	
	@FindBy(id="createAccountSubmit")
	private WebElement createAccountBtn;
	
	@FindBy(name="ap_customer_name")
	private WebElement customerName;
	
	@FindBy(name="ap_phone_number")
	private WebElement customerPhone;
	
	@FindBy(name="ap_password")
	private WebElement customerPassword;
	
	//Elements to Verify Logged in user
	@FindBy(id="nav-link-accountList-nav-line-1")
	private WebElement loggedInUser;
	
	@FindBy(name="continue")
	private WebElement verifyNumber;
	
	//LoginPage Constructor to initialize the elements used in this class
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//Amazon Action methods
	
	//ClickLoginButton Navigate to Login Page
		public void clickLoginButton() throws Throwable
		{
			
			loginButton.click();
		}
	
	//setUserName
	public void setUserName() throws Throwable
	{
		userName.sendKeys(prop.getProperty("username"));
		
	}
	
	//click On ContinueButton to Enter Password
	public void clickContinueButton() throws Throwable
	{
		continueButton.click();
	}
	
	//setPassword
	public void setPassword() throws Throwable
	{
		password.sendKeys(prop.getProperty("password"));
	}
	

	//Click on Login Button to submit
	public void login() throws Throwable 
	
	{
		signInBtn.click();
	}
	
	//Verify Logged In User
	public void verifyLoggedUser() throws Throwable 
	
	{
		String loggedUser = loggedInUser.getText();
		Assert.assertEquals(loggedUser,"Hello, prashant");
	}
	
	
	
	
	
	
	
	
	
}






