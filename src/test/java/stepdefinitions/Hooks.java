package stepdefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import io.cucumber.java.Scenario;
import utils.Base;
import utils.CookieManager;
import utils.ExcelReader;


public class Hooks extends Base{
	
	static ExtentSparkReporter spark ;
	static ExtentReports extReports;
	static ExtentTest extTest;
	public static String[][] excelData = ExcelReader.readData();
	public static int currentRow = 0;
	
	
	@BeforeAll
		public static void beforeAll() {
		
		spark = new ExtentSparkReporter("reports//ExtentReport.html");
		extReports = new ExtentReports();
		extReports.attachReporter(spark);
			
		}
	@AfterAll
		public static void afterAll() {
		extReports.flush();
		
	}
	
	
	@Before
	public void setUp(Scenario scenario) {
		launchBrowser();
		extTest = extReports.createTest(scenario.getName());
		 driver = new ChromeDriver();
		    driver.manage().window().maximize();
		    driver.get("https://www.ixigo.com");

		    CookieManager.loadCookies(driver);
		    driver.navigate().refresh();

		    // ✅ Check login state immediately after refresh
		    List<WebElement> greetings = driver.findElements(By.xpath("//*[contains(text(),'Hey')]"));
		    if (!greetings.isEmpty()) {
		        System.out.println("✅ Logged in using cookies, skipping login flow.");
		    } else {
		        System.out.println("ℹ️ Not logged in, will continue with login steps.");
		    }
	}
	@After
	public void tearDown() {
		
		Base.sleep();
	    CookieManager.saveCookies(driver);
		//driver.quit();
		currentRow++;
		
	}

}
