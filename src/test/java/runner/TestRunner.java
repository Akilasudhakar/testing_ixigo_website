package runner;

import io.cucumber.testng.CucumberOptions;
import utils.ExcelReader;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/login.feature", glue = "stepdefinitions", plugin = {
		"pretty", "html:reports/cucumber-html-report.html" })

public class TestRunner extends AbstractTestNGCucumberTests {

}
