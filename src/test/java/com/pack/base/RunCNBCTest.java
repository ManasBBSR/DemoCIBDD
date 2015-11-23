package com.pack.base;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
//import gherkin.util.FixJava; 

	//this is a cucumber annotation dictating the cucumber runner
	//@SuppressWarnings("deprecation")sahu
	@RunWith(Cucumber.class)
	@CucumberOptions(
			features = {"src/test/resources"},
			glue = {"com.pack.cnbc.tests"},
			format = {"pretty",
					"html:target/cucumber-html-report",
					"json:target/cucumber-report.json"}
			//,tags = {"@WIP"}
			)
	
	//this is an empty class to run with. This needs to remain empty
	public class RunCNBCTest {
	}