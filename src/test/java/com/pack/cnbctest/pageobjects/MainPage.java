package com.pack.cnbctest.pageobjects;

//import org.openqa.selenium.Keys;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.How;
import org.junit.Assert;

public class MainPage  extends BasePage {
	
	@FindBy(css="#unreg-user > li.cnbcRegister > a")
	WebElement Register;
	
	@FindBy(css="#signin")
	WebElement Singin;
	
	@FindBy(css="#reg-user > li > a")
	WebElement myAccountMenu;
	
	@FindBy(css="#reg-user > li > ul > li:nth-child(1) > a")
	WebElement myProfile;
	
	@FindBy(css="#signout")
	WebElement Signout;
	
	public MainPage(WebDriver driver) {
		super(driver,"CNBC International – World Business News Leader");
		PageFactory.initElements(driver, this);
	}

	public void OpenMainPage()
	{
		URL = "http://www.cnbc.com";
		try {
			open();
			PageLoadWait(12000); 
		} catch (Exception e) {
				tLog.info("Error while opening the page", e);
		}
		System.out.println(driver.getTitle());
		Assert.assertEquals( isPageLoad(), true);
	}
	
	public void RegisterClick() {
	   tLog.info("Registration screen");
		//travelQuote.click();
	   Actions builder = new Actions(driver);
	   builder = new Actions(driver);
	   builder.moveToElement(Register).click().build().perform();
	  //Register.click();
	  
	   PageLoadWait(12000);
	}
	
	public void LoginClick() {
		   tLog.info("Login screen");
			//travelQuote.click();
		   Actions builder = new Actions(driver);
		   builder = new Actions(driver);
		   builder.moveToElement(Singin).click().build().perform();
		  //Register.click();
		  
		   PageLoadWait(12000);
		}
	
	public void EnterRegDetails() {
		  try {
			  Robot robot = new Robot();
		  
			  String email_id = "cnbc.user@user.com"; // driver.findElement(By.cssSelector("#field_email > div > input"));
		  		  
			  enterRobotText(getKeyCode(email_id,true),robot);
		     robot.keyPress(KeyEvent.VK_TAB);
		     robot.keyRelease(KeyEvent.VK_TAB);
		     robot.delay(100);
		     tLog.info("Entered email id");
			  String password = "Bdd!45678"; //driver.findElement(By.cssSelector("#field_password > div.input_container > input"));
		      
			  enterRobotText(getKeyCode(password,true),robot);
		  	  robot.keyPress(KeyEvent.VK_TAB);
			  robot.keyRelease(KeyEvent.VK_TAB);
			  robot.delay(100);			  
		  
			  tLog.info("Entered password");
			  
			  Random rand = new Random();
			  String screenName = "cnbcdemo" + (rand.nextInt(50) + 1); //driver.findElement(By.xpath("#field_username > div > input"));
			  enterRobotText(getKeyCode(screenName,true),robot);
			  
			  try {
				takeScreenshot("RegisterDetails");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		      robot.keyPress(KeyEvent.VK_ENTER);
		      robot.keyRelease(KeyEvent.VK_ENTER);
		      robot.delay(500);
			  
		      tLog.info("Entered Screen Name" + screenName );
		 
		      PageLoadWait(5000);
		  
			} catch (AWTException e) {
				e.printStackTrace();
			}
		//new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, Keys.TAB)).perform();
		//return new MainPage(this.driver);
	}
	
	public void EnterLoginDetails(boolean newPass) {
		  try {
			  Robot robot = new Robot();
		  
			  String email_id = "cnbc.user@user.com"; // driver.findElement(By.cssSelector("#field_email > div > input"));
		  		  
			  enterRobotText(getKeyCode(email_id,true),robot);
		     robot.keyPress(KeyEvent.VK_TAB);
		     robot.keyRelease(KeyEvent.VK_TAB);
		     robot.delay(100);
		     tLog.info("Entered email id");
		     
		     String password;
		     
		     if (newPass) {
		    	 password = "Bdd!5678";
		     } else {
		    	 password = "Bdd!45678"; //driver.findElement(By.cssSelector("#field_password > div.input_container > input"));
		     }
		      
			  enterRobotText(getKeyCode(password,true),robot);
			  tLog.info("Entered password");
			  
			  try {
				takeScreenshot("RegisterDetails");
			  } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			  }
			  
		      robot.keyPress(KeyEvent.VK_ENTER);
		      robot.keyRelease(KeyEvent.VK_ENTER);
		      robot.delay(500);
			  
		      PageLoadWait(8000);
		  
			} catch (AWTException e) {
				e.printStackTrace();
			}
		//new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, Keys.TAB)).perform();
		//return new MainPage(this.driver);
	}

	public String VerifyMyAccount() {
		return myAccountMenu.getAttribute("innerHTML");
	}
	
	public String VerifyRegisterLink() {
		return Register.getAttribute("innerHTML");
	}
	
	public MyProfilePage clickMyProfile() {
		  Actions builder = new Actions(driver);    
		  builder.moveToElement(myAccountMenu).moveToElement(myProfile).click().build().perform();
		  PageLoadWait(8000);
		return new MyProfilePage(this.driver);
	}
	
	public void SignOut() {
		  Actions builder = new Actions(driver);    
		  builder.moveToElement(myAccountMenu).moveToElement(Signout).click().build().perform();
		  PageLoadWait(10000);
	}
}
