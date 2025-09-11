package runner;

import io.cucumber.testng.CucumberOptions;
import utils.ExcelReader;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src\\test\\resources\\features\\login.feature", glue = "stepdefinitions", plugin = {
		"pretty", "html:reports/cucumber-html-report.html" })


public class TestRunner extends AbstractTestNGCucumberTests {
//	@Override
//	@DataProvider(parallel = false)
//	public Object[][] scenarios() {
//		// Read Excel data
//		String[][] excelData = ExcelReader.readData();
//		Object[][] cucumberScenarios = super.scenarios();
//
//		// Create a new array: number of rows * number of original scenarios
//		Object[][] finalScenarios = new Object[excelData.length * cucumberScenarios.length][];
//		int index = 0;
//
//		for (int i = 0; i < excelData.length; i++) {
//			for (int j = 0; j < cucumberScenarios.length; j++) {
//				finalScenarios[index++] = cucumberScenarios[j];
//			}
//		}
//
//		return finalScenarios;
//	}
}
