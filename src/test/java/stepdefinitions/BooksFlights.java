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
	

	HomePage homePage ;
	
	WebDriver driver = Hooks.driver;
	ExtentTest extTest = Hooks.extTest;
	//ValidationPage validationPage ;
	
	static String[][] excelData;

		
	@Given("the user has a valid browser installed")
	public void the_user_has_a_valid_browser_installed() {

		String expResult = "https://www.ixigo.com/";
		String actResult = driver.getCurrentUrl();
		Assert.assertEquals(actResult,expResult);
		System.out.println("Website has been launched successfully");
		
//		if(excelData == null) {
//			excelData = ExcelReader.readData();
//		}
//		try {
//		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		    WebElement closePopup = wait.until(
//		        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.logSprite.icClose"))
//		    );
//		    closePopup.click();
//		} catch (Exception e) {
//		    // ignore if popup not present
//		}
//		try {
//		    // Close notification popup if visible
//		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		    WebElement closeNotificationPopup = wait.until(
//		        ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sc-gsNilK.bPJaMI")) // adjust if locator changes
//		    );
//		    closeNotificationPopup.click();
//		} catch (Exception e) {
//		    // ignore if notification popup not present
//		}
	    
	}
	@When("the user enters the valid Ixigo website URL in the browser")
	public void the_user_enters_the_valid_ixigo_website_url_in_the_browser() {
	    
	}
	@Then("the website should open successfully")
	public void the_website_should_open_successfully() {
	    
	}
	@Then("the website should display correctly in Chrome, Firefox, and Edge")
	public void the_website_should_display_correctly_in_chrome_firefox_and_edge() {
	    
	}
	
	@Given("the user has a browser open")
	public void the_user_has_a_browser_open() {
	    
	}
	@When("the user enters an invalid website URL in the browser")
	public void the_user_enters_an_invalid_website_url_in_the_browser() {
	   
	}
	@Then("the system should not load the Ixigo website")
	public void the_system_should_not_load_the_ixigo_website() {
	    
	}
	@Then("an error page like {string} or {string} should be displayed")
	public void an_error_page_like_or_should_be_displayed(String string, String string2) {
	    
	}


}
