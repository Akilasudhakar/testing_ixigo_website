package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.Base;
import utils.ExcelReader;

public class Hooks extends Base {

	static ExtentSparkReporter spark;
	static ExtentReports extReports;
	static ExtentTest extTest;
	public static ExtentReports extent;
	public static String[][] excelData = ExcelReader.readData();
	public static int currentRow = 0;

	@BeforeAll
	public static void beforeAll() {
		spark = new ExtentSparkReporter("reports/ExtentReport.html");
		extReports = new ExtentReports();
		extReports.attachReporter(spark);
	}

	@Before
	public void setUp(Scenario scenario) {
		Base.initDriver();
		extTest = extReports.createTest(scenario.getName());
	}

	@After
	public void tearDown(Scenario scenario) {
//	    if (scenario.isFailed()) {
//	        extTest.fail("❌ Scenario failed: " + scenario.getName());
//	    } else {
//	        extTest.pass("✅ Scenario passed: " + scenario.getName());
//	    }
		//Base.sleep();
		// driver.quit();

		// Move to next row after each scenario
		currentRow++;
		if (currentRow >= excelData.length) {
			currentRow = 0; // reset if you want to loop again
		}
		// Base.quitDriver();
		if (scenario.isFailed()) {
			extTest.fail("Scenario failed: " + scenario.getName());
		} else {
			extTest.pass("Scenario passed: " + scenario.getName());
		}
	}

	@AfterAll
	public static void afterAll() {
		// Base.quitDriver();
		extReports.flush();
	}

}
