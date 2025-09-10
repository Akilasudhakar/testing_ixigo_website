package stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.ExcelReader;

public class BooksFlights {

	HomePage homePage;

	WebDriver driver = Hooks.driver;
	ExtentTest extTest = Hooks.extTest;
	// ValidationPage validationPage ;

	static String[][] excelData;

	// positive scenario of launch

	@Given("the user enters the valid Ixigo website URL in the browser")
	public void the_user_enters_the_valid_ixigo_website_url_in_the_browser() {
		homePage = new HomePage(driver, extTest);
		// Try to load cookies before doing login
	    driver.get("https://www.ixigo.com/");
	    utils.CookieManager.loadCookies(driver);
	    driver.navigate().refresh(); // apply loaded cookies
		homePage.valid_url();
		homePage.close_notify();

	}

	@When("the website should open successfully")
	public void the_website_should_open_successfully() {
		homePage.site_open();

	}

	@Then("the website should display correctly in Chrome, Firefox, and Edge")
	public void the_website_should_display_correctly_in_chrome_firefox_and_edge() {
		homePage.displayed_successfully_chrome();
	}

	// negative scenario of launch

	@Given("the user enters an invalid website URL in the browser")
	public void the_user_enters_an_invalid_website_url_in_the_browser() {
		homePage = new HomePage(driver, extTest);
		 try {
		        homePage.close_notify();
		        homePage.enterValidotp();
		    } catch (Exception e) {
		        utils.CookieManager.resetCookies();   // OTP wrong or expired → reset cookies
		        throw e;
		    }

	}

	@When("the system should not load the Ixigo website")
	public void the_system_should_not_load_the_ixigo_website() {
		//homePage.invalid_site_open();

	}
//	@Then("an error page like {string} or {string} should be displayed")
//	public void an_error_page_like_or_should_be_displayed(String string, String string2) {
//	   
//	}

	// login positive scenario
	@Given("the user enters the valid Ixigo website URL in the browser and the user is on the Ixigo Login\\/Signup page")
	public void the_user_enters_the_valid_ixigo_website_url_in_the_browser_and_the_user_is_on_the_ixigo_login_signup_page() {

	}

	@When("the user enters valid mobile number as {string}")
	public void the_user_enters_valid_mobile_number_as(String mobileNumber) {

		homePage = new HomePage(driver, extTest);
		int row = Hooks.currentRow;
		if (excelData == null) {
			excelData = ExcelReader.readData(); // load Excel data once
		}
		mobileNumber = excelData[row][0];

		homePage.validMobileNumber(mobileNumber);

	}

	@When("the user enters a valid email address")
	public void the_user_enters_a_valid_email_address() {

	}

	@When("the user enters the correct OTP")
	public void the_user_enters_the_correct_otp() {
		homePage.close_notify();
		homePage.enterValidotp();

	}

	@When("the user submits the OTP within the valid time frame")
	public void the_user_submits_the_otp_within_the_valid_time_frame() {
		//homePage.enterValidotp();

	}

	@Then("the system should authenticate the user successfully")
	public void the_system_should_authenticate_the_user_successfully() {

		homePage.loggedin_success();

	}

	// login negative scenario

	@When("the user enters invalid mobile number as {string}")
	public void the_user_enters_invalid_mobile_number_as(String invalidmobileNumber) {
//		homePage = new HomePage(driver, extTest);
//		int row = Hooks.currentRow;
//		 if (excelData == null) {
//	            excelData = ExcelReader.readData(); // load Excel data once
//	        } 
//		 invalidmobileNumber= excelData[row][1]; 
//		System.out.println(invalidmobileNumber);
//		homePage.enterinvalidmobileNumber(invalidmobileNumber) ;

	}

	@When("the user enters an incorrect OTP")
	public void the_user_enters_an_incorrect_otp() {
		// homePage.enterinvalidotp();

	}

	@When("the user fails to enter the OTP within the time frame")
	public void the_user_fails_to_enter_the_otp_within_the_time_frame() {
		// homePage.enterinvalidotp();

	}

	@When("the user tries to submit without entering an OTP")
	public void the_user_tries_to_submit_without_entering_an_otp() {
		// homePage.enterinvalidotp();

	}
	
	//search flight for normal person without any special fares
	
	@Given("the user is on login page")
	public void the_user_is_on_login_page() {
		homePage = new HomePage(driver, extTest);
		
		
		if(excelData == null) {
			excelData = ExcelReader.readData();
		}
		
		homePage.valid_url();
		homePage.close_notify();
	}
	@Given("the user click on the round trip")
	public void the_user_click_on_the_round_trip() {
		homePage.rndtrip();
	}
	@When("the user enter boarding place as {string} and landing place as {string}")
	public void the_user_enter_boarding_place_as_and_landing_place_as(String bPlace, String lPlace) {
		homePage = new HomePage(driver,extTest);
		int row = Hooks.currentRow;
		bPlace  = excelData[row][2];
		lPlace = excelData[row][3];
		homePage.enterBoardingPlace(bPlace) ;
	    homePage.enterLandingPlace(lPlace);
	}
	@When("the user selects the departure date as {string} and return date as {string}")
	public void the_user_selects_the_departure_date_as_and_return_date_as(String dDate, String rDate) {
		homePage = new HomePage(driver,extTest);
		int row = Hooks.currentRow;
		dDate = excelData[row][4];
		rDate = excelData[row][5];
		String[] parts = dDate.split(" ");  
		String dptday = parts[0];        // "20"
		String dptmonth = parts[1];      // "May"
		String dptyear = parts[2];
		//homePage.bookingDate(dptday,dptmonth,dptyear);
		String[] parts1 = rDate.split(" ");  
		String rtnday = parts1[0];        // "20"
		String rtnmonth = parts1[1];      // "May"
		String rtnyear = parts1[2];
		homePage.bookingDate(dptday,dptmonth,dptyear,rtnday,rtnmonth,rtnyear);
	   
	}
	@When("the user clicks the travellers & class options increase the value for adults as {string} and child as {string}")
	public void the_user_clicks_the_travellers_class_options_increase_the_value_for_adults_as_and_child_as(String adultCount, String childCount) {
	 
		int row = Hooks.currentRow;
		//String str = "25";
		//int num = Integer.parseInt(str);
		String adultCount1  = excelData[row][6];
		String childCount1 = excelData[row][7];
		
		//homePage.enterBoardingPlace(bPlace);
		homePage.trvlsandClass(adultCount1,childCount1);
	}
	@When("the user select the business class as {string} and clicks done button")
	public void the_user_select_the_business_class_as_and_clicks_done_button(String businesscls) {
		homePage.donebtn(businesscls);
	}
	@When("the user clicks on the search flight button")
	public void the_user_clicks_on_the_search_flight_button() {
		homePage.searchbtn();
	    
	}
	@Then("the user validates the search result page has the text filter and capture screen shot")
	public void the_user_validates_the_search_result_page_has_the_text_filter_and_capture_screen_shot() {
	    
	}

}
