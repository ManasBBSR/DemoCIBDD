package com.pack.cnbctest.pageobjects;

//import java.util.Set;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
///import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.pack.base.logpack.TestLogger;
/*import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
*/

public class BasePage {
	  /** Default URL */
	  protected String URL;	
	  JavascriptExecutor jse; 
	  
	  boolean[] boolShift = new boolean[30];
	  
	  /** This page's WebDriver */ 
	  protected WebDriver driver; 
	  
	  /**This defines the log which will be instantiated for every page using the TestLogger.createLogger() 
	   * in the constructor class */
	  static org.apache.log4j.Logger  tLog;
	  
	  /** Expected Page Title.  This will be used in isPageLoad() 
	   * to check if page is loaded. */
	 protected String pageTitle; 
	 

	@SuppressWarnings("static-access")
	public BasePage(WebDriver driver, String pageTitle) {
		  this.driver = driver; 
		  this.pageTitle = pageTitle; 
		  this.jse = (JavascriptExecutor)driver;
		  this.tLog = TestLogger.createLogger();
	}
	 
	 /** 
	   * This function emulates the click function using the Java Script executor  
	   * @param WebElement
	   * @param Element Id as String
	   */
		public void click(WebElement wbElement, String element) {
			try {
			jse.executeScript("scroll(0, arguments[0])", wbElement.getLocation().y + 10);
			Thread.sleep(1000);
			String script = "var elem = document.getElementById('" + element + "');"+
					 "if( document.createEvent) {"+
					 "var evObj = document.createEvent('MouseEvents');"+
					 "evObj.initEvent( 'mouseover', true, false );"+
					 "elem.dispatchEvent(evObj);"+
					 "} else if( document.createEventObject ) {"+
					 "elem.fireEvent('onmouseover');" +
					 " } elem.click()";
			jse.executeScript(script);
			synchronized(driver) {
			driver.wait(3000);
				}
			} catch (Exception e) {
				tLog.info("Timeout while doing Java Script click for element " + element, e);
			}
		}		  
		
		public void click( WebElement element ) {
			try {
				element.click();
				synchronized(driver) {
					driver.wait(5000);
				}
			} catch (NoSuchElementException | InterruptedException e) {
				tLog.info ("NoSuchElementException for element " , e);
			}
		}
		
	 public void scrollPage(WebElement wbElement) {
		 jse.executeScript("scroll(0, arguments[0])",wbElement.getLocation().y);
	 }
	  public boolean isPageLoad(){
		  return (driver.getTitle().contains(pageTitle)); 
	  }
	  
	  /** Open the default page */ 
	  public void open(){
		  driver.get(URL); 
	  }
	  
	  /** Returns the page title */ 
	  public String getPageTitle() {
		  return driver.getTitle(); 
	  }
	  
	  /** Returns the default URL */ 
	  public String getURL() {
		return URL;
	  }
	  /**  
	   * Send text keys to the element that finds by cssSelector.  
	   * It shortens "driver.findElement(By.cssSelector()).sendKeys()". 
	   * @param cssSelector
	   * @param text
	   */
	  protected void sendText(String cssSelector, String text) {
			driver.findElement(By.cssSelector(cssSelector)).sendKeys(text);
	  }
	  
	  /** Is the text present in page. */ 
	  public boolean isTextPresent(String text){
		  return driver.getPageSource().contains(text); 
	  }
	  
	  /** Is the Element in page. */
	  public boolean isElementPresent(By by) {
			try {
				driver.findElement(by);//if it does not find the element throw NoSuchElementException, thus returns false. 
				return true;
			} catch (NoSuchElementException e) {
				tLog.info("NoSuchElementException for element ", e);
				return false;
			}
	  }

	  /** 
	   * Is the Element present in the DOM. 
	   * 
	   * @param _cssSelector 		element locater
	   * @return					WebElement
	   */
	  public boolean isElementPresent(String _cssSelector){
			try {
				driver.findElement(By.cssSelector(_cssSelector));
				return true;
			} catch (NoSuchElementException e) {
				tLog.info("NoSuchElementException for element " + _cssSelector, e);
				return false;
			}
	  }
	  

	  /**
		* Checks if the elment is in the DOM and displayed. 
		* 
		* @param by - selector to find the element
		* @return true or false
		*/
	  public boolean isElementPresentAndDisplay(By by) {
			try {			
				return driver.findElement(by).isDisplayed();
			} catch (NoSuchElementException e) {
				tLog.info("NoSuchElementException for element " + by.toString(), e);
				return false;
			}
	  }
	  
	  /** 
	   * Returns the first WebElement using the given method.  	   
	   * It shortens "driver.findElement(By)". 
	   * @param by 		element locater. 
	   * @return 		the first WebElement
	   */
	  public WebElement getWebElement(By by){
		  	return driver.findElement(by); 			
	  }	  
	  
		public void fieldSelect(WebElement wbElement, String value) {
		    // Write code here that turns the phrase above into concrete actions
			try {
			  Select selectVal = new Select(wbElement);
			  selectVal.selectByVisibleText(value);
			  synchronized(driver) {
				  driver.wait(500);
			  }
			} catch (NoSuchElementException | InterruptedException e){
				tLog.info("Element not found exception for " + wbElement.toString() + value, e);
			}
		}
		
		public void type( WebElement element, String text ) {
			try {
				Actions selAction = new Actions(driver);
				selAction.sendKeys( element, text ).perform();
			} catch (NoSuchElementException e) {
				tLog.info("Error in script execution" + element.toString() + " not found", e);
			}
		}
		
		public void takeScreenshot(String action) throws IOException {
			action = action.replaceAll("[^a-zA-Z0-9]", "");  
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("target/" + System.currentTimeMillis() + action + ".jpg"));    	
		}
		
		public void highlightElement(WebElement element) {
			  //Creating JavaScriptExecuter Interface
			   JavascriptExecutor js = (JavascriptExecutor)driver;
			   for (int iCnt = 0; iCnt < 5; iCnt++) {
			      //Execute javascript
			         js.executeScript("arguments[0].style.border='4px solid yellow'", element);
			         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			         js.executeScript("arguments[0].style.border=''", element);
			   }
		}
		
		public void PageLoadWait(long time)
		{
		   synchronized(driver) {
				try {
					driver.wait(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
		
		protected void enterRobotText(int strText[], Robot robot) {
			for (int i = 0; i < strText.length; i++)
	        {    
				int keyInputP = strText[i];
	        	if (boolShift[i]) {
	        		robot.keyPress(KeyEvent.VK_SHIFT);
	    			robot.keyPress(keyInputP);
	    			robot.keyRelease(KeyEvent.VK_SHIFT);
	    			robot.keyRelease(keyInputP);
	        	} else {
	        		robot.keyPress(keyInputP);
	        		robot.keyRelease(keyInputP);
	        	}
	          robot.delay(200);
	        }
		}
		
		protected int[] getKeyCode(String str, boolean boolID) {
			int keyCodes[] = new int[str.length()];
			boolShift = new boolean[str.length()];
			Arrays.fill(boolShift, Boolean.FALSE);
			for (int i = 0; i < str.length(); i++ ){
				switch (str.charAt(i)) {
				case ')': keyCodes[i] = KeyEvent.VK_0; 
						boolShift[i] = true; 
						break;
				case '!': keyCodes[i] = KeyEvent.VK_1; 
						boolShift[i] = true; 
						break;
				case '@': keyCodes[i] = KeyEvent.VK_2; 
						boolShift[i] = true; 
						break;			
				case '#': keyCodes[i] = KeyEvent.VK_3;
						boolShift[i] = true; 
						break;
				case '$': keyCodes[i] = KeyEvent.VK_4;
						boolShift[i] = true;
						break;
				case '%': keyCodes[i] = KeyEvent.VK_5; 
						boolShift[i] = true; 
						break;
				case '^': keyCodes[i] = KeyEvent.VK_6; 
						boolShift[i] = true; 
						break;
				case '&': keyCodes[i] = KeyEvent.VK_7;
						boolShift[i] = true; 
						break;
				case '*': keyCodes[i] = KeyEvent.VK_8; 
						boolShift[i] = true; 
						break;
				case '(': keyCodes[i] = KeyEvent.VK_9; 
						boolShift[i] = true; 
						break;
				case '0': keyCodes[i] = KeyEvent.VK_0;
						boolShift[i] = false;
						break;
				case '1': keyCodes[i] = KeyEvent.VK_1;
						boolShift[i] = false;
						break;
				case '2': keyCodes[i] = KeyEvent.VK_2;
						boolShift[i] = false;
						break;			
				case '3': keyCodes[i] = KeyEvent.VK_3;
						boolShift[i] = false;
						break;
				case '4': keyCodes[i] = KeyEvent.VK_4;
						boolShift[i] = false; 
						break;
				case '5': keyCodes[i] = KeyEvent.VK_5; 
						boolShift[i] = false; 
						break;
				case '6': keyCodes[i] = KeyEvent.VK_6;
						boolShift[i] = false;
						break;
				case '7': keyCodes[i] = KeyEvent.VK_7;
						boolShift[i] = false;
						break;
				case '8': keyCodes[i] = KeyEvent.VK_8; 
						boolShift[i] = false; 
						break;
				case '9': keyCodes[i] = KeyEvent.VK_9; 
						boolShift[i] = false; 
						break;
				case '-': keyCodes[i] = KeyEvent.VK_MINUS;
						boolShift[i] = false; 
						break;
				case '_': keyCodes[i] = KeyEvent.VK_MINUS; 
						boolShift[i] = true; 
						break;
				case '+': keyCodes[i] = KeyEvent.VK_ADD;
						boolShift[i] = false; 
						break;
				case '.': keyCodes[i] = KeyEvent.VK_PERIOD;
						boolShift[i] = false;
						break;
				case '\\': keyCodes[i] = KeyEvent.VK_BACK_SLASH;
						boolShift[i] = false;
						break;					
				case 'a': keyCodes[i] = KeyEvent.VK_A;
						boolShift[i] = false;
						break;
				case 'b': keyCodes[i] = KeyEvent.VK_B;
						boolShift[i] = false;
						break;
				case 'c': keyCodes[i] = KeyEvent.VK_C;
						boolShift[i] = false;
						break;
				case 'd': keyCodes[i] = KeyEvent.VK_D; 
						boolShift[i] = false;
						break;
				case 'e': keyCodes[i] = KeyEvent.VK_E; 
						boolShift[i] = false;
						break;
				case 'f': keyCodes[i] = KeyEvent.VK_F;
						boolShift[i] = false;
						break;
				case 'g': keyCodes[i] = KeyEvent.VK_G; 
						boolShift[i] = false;
						break;
				case 'h': keyCodes[i] = KeyEvent.VK_H;
						boolShift[i] = false;
						break;
				case 'i': keyCodes[i] = KeyEvent.VK_I; 
						boolShift[i] = false;
						break;
				case 'j': keyCodes[i] = KeyEvent.VK_J; 
						boolShift[i] = false;
						break;
				case 'k': keyCodes[i] = KeyEvent.VK_K; 
						boolShift[i] = false;
						break;
				case 'l': keyCodes[i] = KeyEvent.VK_L; 
						boolShift[i] = false;
						break;
				case 'm': keyCodes[i] = KeyEvent.VK_M; 
						boolShift[i] = false;
						break;
				case 'n': keyCodes[i] = KeyEvent.VK_N; 
						boolShift[i] = false;
						break;
				case 'o': keyCodes[i] = KeyEvent.VK_O;
						boolShift[i] = false;
						break;
				case 'p': keyCodes[i] = KeyEvent.VK_P; 
						boolShift[i] = false;
						break;
				case 'q': keyCodes[i] = KeyEvent.VK_Q; 
						boolShift[i] = false;
						break;
				case 'r': keyCodes[i] = KeyEvent.VK_R; 
						boolShift[i] = false;
						break;
				case 's': keyCodes[i] = KeyEvent.VK_S; 
						boolShift[i] = false;
						break;
				case 't': keyCodes[i] = KeyEvent.VK_T; 
						boolShift[i] = false;
						break;
				case 'u': keyCodes[i] = KeyEvent.VK_U; 
						boolShift[i] = false;
						break;
				case 'v': keyCodes[i] = KeyEvent.VK_V;
						boolShift[i] = false;
						break;
				case 'w': keyCodes[i] = KeyEvent.VK_W; 
						boolShift[i] = false;
						break;
				case 'x': keyCodes[i] = KeyEvent.VK_X; 
						boolShift[i] = false;
						break;
				case 'y': keyCodes[i] = KeyEvent.VK_Y; 
						boolShift[i] = false;
						break;
				case 'z': keyCodes[i] = KeyEvent.VK_Z; 
						boolShift[i] = false;
						break;
				case 'A': keyCodes[i] = KeyEvent.VK_A; 
						boolShift[i] = true;
						break;
				case 'B': keyCodes[i] = KeyEvent.VK_B; 
						boolShift[i] = true;
						break;
				case 'C': keyCodes[i] = KeyEvent.VK_C; 
						boolShift[i] = true;
						break;
				case 'D': keyCodes[i] = KeyEvent.VK_D; 
						boolShift[i] = true;
						break;
				case 'E': keyCodes[i] = KeyEvent.VK_E; 
						boolShift[i] = true;
						break;
				case 'F': keyCodes[i] = KeyEvent.VK_F; 
						boolShift[i] = true;
						break;
				case 'G': keyCodes[i] = KeyEvent.VK_G; 
						boolShift[i] = true;
						break;
				case 'H': keyCodes[i] = KeyEvent.VK_H; 
						boolShift[i] = true;
						break;
				case 'I': keyCodes[i] = KeyEvent.VK_I; 
						boolShift[i] = true;
						break;
				case 'J': keyCodes[i] = KeyEvent.VK_J; 
						boolShift[i] = true;
						break;
				case 'K': keyCodes[i] = KeyEvent.VK_K; 
						boolShift[i] = true;
						break;
				case 'L': keyCodes[i] = KeyEvent.VK_L; 
						boolShift[i] = true;
						break;
				case 'M': keyCodes[i] = KeyEvent.VK_M; 
						boolShift[i] = true;
						break;
				case 'N': keyCodes[i] = KeyEvent.VK_N; 
						boolShift[i] = true;
						break;
				case 'O': keyCodes[i] = KeyEvent.VK_O; 
						boolShift[i] = true;
						break;
				case 'P': keyCodes[i] = KeyEvent.VK_P; 
						boolShift[i] = true;
						break;
				case 'Q': keyCodes[i] = KeyEvent.VK_Q; 
						boolShift[i] = true;
						break;
				case 'R': keyCodes[i] = KeyEvent.VK_R; 
						boolShift[i] = true;
						break;
				case 'S': keyCodes[i] = KeyEvent.VK_S; 
						boolShift[i] = true;
						break;
				case 'T': keyCodes[i] = KeyEvent.VK_T; 
						boolShift[i] = true;
						break;
				case 'U': keyCodes[i] = KeyEvent.VK_U; 
						boolShift[i] = true;
						break;
				case 'V': keyCodes[i] = KeyEvent.VK_V; 
						boolShift[i] = true;
						break;
				case 'W': keyCodes[i] = KeyEvent.VK_W; 
						boolShift[i] = true;
						break;
				case 'X': keyCodes[i] = KeyEvent.VK_X; 
						boolShift[i] = true;
						break;
				case 'Y': keyCodes[i] = KeyEvent.VK_Y; 
						boolShift[i] = true;
						break;
				case 'Z': keyCodes[i] = KeyEvent.VK_Z; 
						boolShift[i] = true;
						break;
				}
			}
			return keyCodes;
		}

}
