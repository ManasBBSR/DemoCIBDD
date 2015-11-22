package com.pack.cnbc.tests;

import com.pack.base.TestBaseSetup;
import com.pack.base.logpack.TestLogger;
import com.pack.cnbctest.pageobjects.*;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CNBCUserProfileSteps {

	TestBaseSetup testsetup;
	MainPage HomePage, ReturnHome;
	MyProfilePage MProfPage;

	static org.apache.log4j.Logger  tLog;
			
	@Before
	public void setup() {
		tLog = TestLogger.createLogger();
		tLog.info("Test Initiated");
		testsetup = new TestBaseSetup();
		testsetup.initializeTestBaseSetup("chrome","http://www.cnbc.com");
	}
	
	@After("@End")
	public void teardown () {
		  testsetup.tearDown();
		  tLog.info("Test Completed");
	}
	  
	@Given("^user is on CNBC home page$")
	public void user_is_on_CNBC_home_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		HomePage = new MainPage(testsetup.getDriver());
		HomePage.OpenMainPage();
	}

	@When("^user selects register$")
	public void user_selects_register() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		HomePage.RegisterClick();
		HomePage.takeScreenshot("RegisterPopUp");
	}

	@When("^user provides the details and registers$")
	public void user_is_navigated_to_register_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		HomePage.EnterRegDetails();
	}

	@Then("^the user should be logged in$")
	public void user_should_get_NO_ACCOUNTS_LINKED_error_message() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		tLog.info(HomePage.VerifyMyAccount());
	}
	
	@When("^user selects login$")
	public void user_selects_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		HomePage.LoginClick();
		HomePage.takeScreenshot("LoginPopUp");
	}

	@When("^provides the login details \"(.*?)\"$")
	public void provides_the_login_details(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if (arg1.contains("old")) { 
			HomePage.EnterLoginDetails(false);
		} else {
			HomePage.EnterLoginDetails(true);
		}
		MProfPage = HomePage.clickMyProfile();
	}

	@When("^user updates profile \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\" \"(.*?)\"$")
	public void user_updates_profile(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		MProfPage.EnterProfileDetails(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}
	
	@When("^saves the \"(.*?)\"$")
	public void saves_the(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if (arg1.contains("profile")) { 
			MProfPage.clickSave();
		} else {
			MProfPage.clickSavePassword();
		}
	}

	@Then("^\"(.*?)\" changes are saved$")
	public void changes_are_saved(String arg1) throws Throwable {
	    MProfPage.verifyUpdate();
	}

	@When("^updates the new password as \"(.*?)\"$")
	public void updates_the_new_password_as(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		MProfPage.EnterNewPassword(arg1);
		MProfPage.clickDelAccount();
	}

	@Then("^user signs out$")
	public void user_signs_out() throws Throwable {
	    HomePage.SignOut();
	}
	
	@When("^user deletes account$")
	public void user_deletes_account() throws Throwable {
		ReturnHome = MProfPage.clickDelAccount();
	}

	@Then("^user should be on CNBC home page$")
	public void user_should_be_on_CNBC_home_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		tLog.info(ReturnHome.VerifyRegisterLink());
		tLog.info(ReturnHome.getPageTitle());
	}


}
