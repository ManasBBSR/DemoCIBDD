package com.pack.cnbctest.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage extends BasePage {
	
	@FindBy(css="#profile-area > div:nth-child(8) > div > p > a") WebElement delAccount;
	@FindBy(id="firstname")	WebElement firstname;
	@FindBy(id="lastname")	WebElement lastname;
	@FindBy(id="birthyear") WebElement birthyear;
	@FindBy(id="country") 	WebElement country;
	@FindBy(name="gender") List <WebElement> gender;
	@FindBy(id="industry") WebElement industry;
	@FindBy(id="occupation") WebElement occupation;
	@FindBy(id="household_income") WebElement income;
	@FindBy(id="trades_per_week") WebElement trades;
	@FindBy(name="broker") List <WebElement> broker;
	@FindBy(id="btnSaveDetails") WebElement btnSaveDetails;
	@FindBy(id="password") WebElement password;
	@FindBy(id="newPassword1") WebElement newPassword1;
	@FindBy(id="newPassword2") WebElement newPassword2;
	@FindBy(id="btnChangePassword") WebElement btnChangePassword;
	@FindBy(css="#reg-user > li > a") WebElement myAccountMenu;
	@FindBy(css="#signout") WebElement signout;
	@FindBy(css="#profile-area > div.row.surf-message > div.col-xs-6 > div") WebElement successMsg;
	
	public MyProfilePage(WebDriver driver) {
		super(driver,"My Profile Page");
		PageFactory.initElements(driver, this);
	}
	
	public MainPage clickDelAccount() {
		tLog.info("Deleting the CNBC Account");
		click(delAccount);
		  try {
			  Robot robot = new Robot();
			  robot.keyPress(KeyEvent.VK_TAB);
			  robot.keyRelease(KeyEvent.VK_TAB);
			  robot.delay(100);
			  robot.keyPress(KeyEvent.VK_TAB);
			  robot.keyRelease(KeyEvent.VK_TAB);
			  robot.delay(100);
			  robot.keyPress(KeyEvent.VK_TAB);
			  robot.keyRelease(KeyEvent.VK_TAB);
			  robot.delay(100);
			  robot.keyPress(KeyEvent.VK_ENTER);
		      robot.keyRelease(KeyEvent.VK_ENTER);
		      robot.delay(500);
		  } catch (AWTException e) {
				e.printStackTrace();
				return null;
			}
		  
		  PageLoadWait(10000);
		  tLog.info("Account Deleted");
		return new MainPage(this.driver);
	}
	
	public void EnterProfileDetails(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10) {
		if (!arg1.isEmpty()) {
			type(firstname,arg1);
		} 
		if (!arg2.isEmpty()) {
			type(lastname,arg2);
		} 
		if (!arg3.isEmpty()) {
			fieldSelect(birthyear,arg3);
		} 
		if (!arg4.isEmpty()) {
			fieldSelect(country,arg4);
		} 
		if (!arg5.isEmpty()) {
			click(gender.get(Integer.parseInt(arg5)));
		} 
		if (!arg6.isEmpty()) {
			fieldSelect(industry,arg6);
		} 
		if (!arg7.isEmpty()) {
			fieldSelect(occupation,arg7);
		} 
		if (!arg8.isEmpty()) {
			fieldSelect(income,arg8);
		} 
		if (!arg9.isEmpty()) {
			fieldSelect(trades,arg9);
		} 
		if (!arg10.isEmpty()) {
			click(broker.get(Integer.parseInt(arg10)));
		} 
		
		tLog.info("Profile Details entered.");
	}
	
	public void clickSave() {
		click(btnSaveDetails);
		PageLoadWait(5000);
		tLog.info("Changes Saved.");
	}
	
	public void EnterNewPassword(String strPassword) {
		type(password,"Bdd!45678");
		tLog.info(strPassword);
		type(newPassword1,strPassword);
		type(newPassword2,strPassword);
	}
	
	public void clickSavePassword() {
		click(btnSaveDetails);
		PageLoadWait(5000);
		tLog.info("Password Updated.");
	}
	
	public void verifyUpdate() {
		assertTrue("Message - Your changes have been saved.", successMsg.getText().contains("Your changes have been saved."));
	}
}
