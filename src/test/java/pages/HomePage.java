package pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;
import utils.Base;
import utils.PropertyReader;
import utils.Reporter;

public class HomePage extends Base {

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest extTest;
	String validmobile_number;
	String validotp;
	String invalidmobile_number;
	String invalidotp;

	Scanner sc = new Scanner(System.in);
	Properties prop = PropertyReader.readProperties();

	public HomePage(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		this.extTest = extTest;
		// System.out.println("Website has successfully launched");
	}

	public void valid_url() {
		String expResult = "https://www.ixigo.com/";
		String actResult = driver.getCurrentUrl();
		Assert.assertEquals(actResult, expResult);

	}

	public boolean site_open() {
		String expResult = "https://www.ixigo.com/";
		String actResult = driver.getCurrentUrl();
		boolean status = actResult.equals(expResult);

		Assert.assertTrue(status);

		return status;
	}

	public void displayed_successfully_chrome() {
//		 driver.get("https://www.google.com");
//
//	        // Assert Title
//	        String expectedTitle = "Google";
//	        String actualTitle = driver.getTitle();
//	        
//	        
//	        boolean status= actualTitle.equals(expectedTitle);
//
//			   
//		    Assert.assertTrue(status);
//		
//		return status;
		// Assert.assertEquals(actualTitle, expectedTitle);

		// Assert URL
		// String currentUrl = driver.getCurrentUrl();
		// Assert.assertTrue(currentUrl.contains("google"), " Wrong URL loaded!");

	}

	public void invalid_url() {
		String expResult = "https://www.ixigo.com/";
		String actResult = driver.getCurrentUrl();
		Assert.assertEquals(actResult, expResult);

	}

	public boolean invalid_site_open() {
		String expResult = "https://www.ixigo.com/";
		String actResult = driver.getCurrentUrl();
		boolean status = actResult.equals(expResult);

		Assert.assertTrue(status);

		return status;
	}

	public void close_notify() {
		try {
			// ✅ Wait for iframe popup
			WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wiz-iframe-intent")));

			// Switch into iframe
			driver.switchTo().frame(iframe);

			// Find and click the close button inside iframe
			WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.closebutton));
			closeBtn.click();
			System.out.println("Popup closed");

			// Switch back to main page
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			System.out.println("No popup detected, continuing...");
			driver.switchTo().defaultContent(); // ensure back to main content
		}
	}

	public void validMobileNumber(String mobileNumber) {

		// 🔄 Normal login flow if not logged in
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.loginbtn)).click();
			wait.until(ExpectedConditions.elementToBeClickable(Locators.clkphoneNumber)).click();

			WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.clkphoneNumber));
			mobile.click();
			validmobile_number = mobileNumber;
			mobile.sendKeys(mobileNumber);

			wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // longer wait
			wait.until(ExpectedConditions.elementToBeClickable(Locators.clkcontinuebtn)).click();

			Reporter.generateReport(driver, extTest, Status.PASS, "Valid mobile number is accepted");

		} catch (TimeoutException te) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Valid mobile number is not accepted");
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Unexpected error: " + e.getMessage());
		}
	}

	public void enterValidotp() {

		System.out.println("Enter the otp: ");
		String otp = sc.nextLine().trim(); // Example: 444550

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// Grab all OTP input boxes inside the OTP container
			List<WebElement> otpInputs = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='otp']//input") 
					));

			if (otpInputs.size() < otp.length()) {
				throw new RuntimeException("Not enough OTP input boxes found. Found: " + otpInputs.size());
			}

			// Enter each digit into corresponding box
			for (int i = 0; i < otp.length(); i++) {
				otpInputs.get(i).clear();
				otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
			}

			System.out.println("OTP entered successfully!");

		} catch (Exception e) {
			System.err.println("Error while entering OTP: " + e.getMessage());

		}

	}

	public void loggedin_success() {
		

		// ✅ avoid cached session issues
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			//  Step 1: Wait for profile/avatar to be visible
			WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/main/div[1]/div/div[2]/div[2]/div/div[1]/p/span")));

			//  Step 2: Confirm it’s displayed
			if (profileIcon.isDisplayed()) {
				System.out.println("User successfully logged in! Profile icon visible.");
				// utils.CookieManager.saveCookies(driver);

				//Step 3 (Optional): Hover to check menu items
				try {
					org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(
							driver);
					actions.moveToElement(profileIcon).perform();

					List<WebElement> menuItems = driver
							.findElements(By.xpath("//*[text()='Logout' or text()='My Trips']"));
					if (!menuItems.isEmpty()) {
						System.out.println("Profile menu detected: " + menuItems.get(0).getText());
					} else {
						System.out.println("Profile menu not expanded, but login is successful.");
					}
				} catch (Exception hoverEx) {
					System.out.println("Could not hover profile, but profile icon is present.");
				}

			} else {
				// utils.CookieManager.resetCookies();

				Assert.fail("Login did not persist – profile icon hidden.");

		
			}

		} catch (TimeoutException e) {
			// utils.CookieManager.resetCookies();
			Assert.fail("Login failed – profile icon not found.");
		}
	}

	public void enterinvalidmobileNumber(String invalidmobileNumber) {

		try {

			WebElement invalidmobile = wait
					.until(ExpectedConditions.visibilityOfElementLocated(Locators.clkphoneNumber));
			invalidmobile.click();
			invalidmobile_number = invalidmobileNumber;
			invalidmobile.sendKeys(invalidmobileNumber);
			String errmsg = driver.findElement(Locators.invalidmobileerrormsg).getText();
			Reporter.generateReport(driver, extTest, Status.FAIL, errmsg);

		} catch (TimeoutException te) {
			// fail the extent report
			Reporter.generateReport(driver, extTest, Status.PASS, "Invalid mobile number accepted");
		}

	}

	public void enterinvalidotp() {
		try {
			System.out.println("Enter the otp received: ");
			invalidotp = sc.next();
			WebElement invalidotp_input = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.clkotp1));
			invalidotp_input.click();
			invalidotp_input.sendKeys(invalidotp);
			driver.findElement(Locators.verifybtn).click();

			Reporter.generateReport(driver, extTest, Status.FAIL, "Invalid otp number is not accepted");
		} catch (TimeoutException te) {
			// fail the extent report
			Reporter.generateReport(driver, extTest, Status.PASS, "Invalid otp number is acccepted");
			// driver.findElement(Locators.closebtn).click();
		}
	}


	public void rndtrip() {
	    try {
	        // Close notification popup if any
	        driver.navigate().back();
	        close_notify();

	        // Wait for the Round Trip button to be present
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        WebElement roundTripBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(Locators.rndTripBtn)
	        );

	        // Scroll into view
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", roundTripBtn);

	        // Wait until it’s clickable (after scroll/overlay clear)
	        wait.until(ExpectedConditions.elementToBeClickable(roundTripBtn));

	        // Try clicking
	        try {
	            roundTripBtn.click();
	        } catch (Exception clickError) {
	            // Fallback to JS click if normal click fails
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", roundTripBtn);
	        }

	        System.out.println("Round Trip button clicked successfully");

	    } catch (Exception e) {
	        System.out.println("Could not click Round Trip button: " + e.getMessage());
	        throw e;
	    }
	}


	public void enterBoardingPlace(String bPlace) {
		wait.until(ExpectedConditions.elementToBeClickable(Locators.frmbtn)).click();
		driver.findElement(Locators.fromPlace).sendKeys(bPlace);
		Base.sleep();
		
		// suggestion
		// select the first suggestion explicitly
		List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]")));

		if (!results.isEmpty()) {
			results.get(0).click();
		} else {
			driver.findElement(Locators.fromPlace).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		}
	}

	public void enterLandingPlace(String lPlace) {
		System.out.println(lPlace);
		wait.until(ExpectedConditions.elementToBeClickable(Locators.tobtn));
		wait.until(ExpectedConditions.elementToBeClickable(Locators.toPlace)).sendKeys(lPlace);
		Base.sleep();
		// driver.findElement(Locators.toPlace).sendKeys(Keys.ENTER); // choose first
		// suggestion
		// select the first suggestion
		
		List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//span[@class='block truncate' and text()='" + lPlace + "']")));

		if (!results.isEmpty()) {
			results.get(0).click();
		} else {
			driver.findElement(Locators.toPlace).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		}
	}

	public static String[] getMonthYear(String monthYearVal) {
		return monthYearVal.split(" ");
	}
	

	public void bookingDate(String dptdate, String dptmonth, String dptyear, String rtnday, String rtnmonth,
	        String rtnyear) throws InterruptedException {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    wait.until(ExpectedConditions.elementToBeClickable(Locators.deptbtn));

	    String monthYearVal = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.monthYear)).getText().trim();

	    //  Departure Date Selection 
	    while (!(getMonthYear(monthYearVal)[0].equalsIgnoreCase(dptmonth)
	            && getMonthYear(monthYearVal)[1].equals(dptyear))) {

	        WebElement nextBtn = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.nextbtn));

	        if (nextBtn.isEnabled()) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
	            Thread.sleep(500); // allow calendar animation
	        } else {
	            System.out.println("Next button disabled. Could not reach departure month.");
	            break;
	        }
	        monthYearVal = driver.findElement(Locators.monthYear).getText();
	    }

	    try {
	        if (dptdate.startsWith("0")) {
	            dptdate = dptdate.substring(1);
	        }
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//abbr[text()='" + dptdate + "']"))).click();

	    } catch (Exception e) {
	        System.out.println("Wrong departure date: " + dptmonth + ":" + dptdate);
	    }

	    // Return Date Selection
	    monthYearVal = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.monthYear)).getText();

	    while (!(getMonthYear(monthYearVal)[0].equalsIgnoreCase(rtnmonth)
	            && getMonthYear(monthYearVal)[1].equals(rtnyear))) {

	        WebElement nextBtn = wait.until(ExpectedConditions.presenceOfElementLocated(Locators.nextbtn));

	        if (nextBtn.isEnabled()) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
	            Thread.sleep(500); // allow calendar animation
	        } else {
	            System.out.println("Next button disabled. Could not reach return month.");
	            break;
	        }
	        monthYearVal = driver.findElement(Locators.monthYear).getText();
	    }

	    try {
	        if (rtnday.startsWith("0")) {
	            rtnday = rtnday.substring(1);
	        }
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//abbr[text()='" + rtnday + "']"))).click();

	    } catch (Exception e) {
	        System.out.println("Wrong return date: " + rtnmonth + ":" + rtnday);
	    }
	}

	
	
	
	
	
	


	public void trvlsandClass(String adultCount, String childCount) {
		// wait.until(ExpectedConditions.elementToBeClickable(Locators.clickTrvlandCls));
		int aCount = Integer.parseInt(adultCount);
		int cCount = Integer.parseInt(childCount);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(//div[.//p[normalize-space()='Adults'] and .//p[normalize-space()='12 yrs or above']]//button[@data-testid='"
						+ aCount + "'])[1]")))
				.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(//div[.//p[normalize-space()='Adults'] and .//p[contains(text(),'12 yrs or above')]]/following::div[.//p[normalize-space()='Children'] and .//p[contains(text(),'2 - 12 yrs')]])[1]//button[@data-testid='"
						+ cCount + "']")))
				.click();

	}

	public void donebtn(String businesscls) {
		// driver.findElement(By.xpath("//span[text()='"+businesscls.toLowerCase()+"']")).click();
		driver.findElement(By.xpath("//span[text()='" + businesscls + "']")).click();
		Base.sleep();
		driver.findElement(Locators.doneBtn).click();

	}

	public void searchbtn() {
		driver.findElement(Locators.searchBtn).click();
		Base.sleep();
	}

	public void validatesearchpage() {

		String currentUrl = driver.getCurrentUrl();


		if (currentUrl.contains("/search/result/flight")) {
			System.out.println("Successfully navigated to results page. URL: " + currentUrl);
			Reporter.generateReport(driver, extTest, Status.PASS, "Validated search page successfully");
		} else {
			System.out.println("Still on home page. URL: " + currentUrl);
			Reporter.generateReport(driver, extTest, Status.FAIL, "Validated search page failure");
		}

	}

	// applicable for all 3 offers(student,senior citizen,armed officer)
	public void selectStudentoffer(String studentoffer) {

		
		if (studentoffer != null && !studentoffer.trim().isEmpty()) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + studentoffer + "']")))
					.click();
			System.out.println(studentoffer);
		} else {
			System.out.println("No student offer selected, skipping this step.");
		}
	}

	public void closeAddpopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // shorter wait
		try {
			// Wait for the popup button if it exists
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='portal-root']//button")));
					} catch (Exception e) {
			System.out.println("Popup not found, continuing...");
		}

		// Refresh the page after handling popup
		driver.navigate().refresh();
	}

	// Utility: Scroll element into view
	private void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // small pause to stabilize
	}

	private void actionsClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).pause(Duration.ofMillis(300)).click().perform();
	}

	// Select Stops dynamically with scroll
	public void selectStops(String stops) {
		try {
			WebElement stopCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//section[.//p[contains(text(),'Stops')]]//*[contains(text(),'" + stops + "')] ")));
			scrollToElement(stopCheckbox);
			actionsClick(stopCheckbox);

			System.out.println("Selected stop: " + stops);
		} catch (Exception e) {
			System.out.println("Could not select stop: " + stops + " - " + e.getMessage());
		}
	}

	// Select Airline dynamically with scroll
	public void selectAirline(String airlineName) {
		try {
			WebElement airlineCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//section[.//p[contains(text(),'Airlines')]]//*[contains(text(),'" + airlineName + "')]")));
			scrollToElement(airlineCheckbox);
			actionsClick(airlineCheckbox);

			System.out.println("Selected airline: " + airlineName);
		} catch (Exception e) {
			System.out.println("Could not select airline: " + airlineName + " - " + e.getMessage());
		}
	}

	// Select Departure Time dynamically with scroll
	public void selectDepartureTime(String departureTime) {


		try {
			// Map logical names to visible text on UI
			Map<String, String> timeMap = new HashMap<>();
			timeMap.put("EARLY_MORNING", "Before 6AM");
			timeMap.put("MORNING", "6AM - 12PM");
			timeMap.put("AFTERNOON", "12PM - 6PM");
			timeMap.put("EVENING", "After 6PM");

			String uiText = timeMap.get(departureTime);

			// Locate by visible text
			WebElement clickDepTime = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@name='takeOff' and @value='" + uiText + "']")));

			// Force scroll into view with JS
			for (int i = 0; i < 3; i++) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",
						clickDepTime);
				Thread.sleep(700);
				if (clickDepTime.isDisplayed())
					break;
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
			}
			try {
				// First try Actions click
				Actions actions = new Actions(driver);
				actions.moveToElement(clickDepTime).click().perform();
			} catch (Exception ex) {
				// If Actions fails, do JS click
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickDepTime);
			}

			System.out.println("Selected departure time: " + uiText);

		} catch (Exception e) {
			System.out.println("Could not select departure time: " + departureTime + " - " + e.getMessage());
		}

	}

	public void selectFirstFlight() {
		try {
			WebElement firstFlightCard = driver.findElement(
					By.xpath("(//div[contains(@class,'shadow-card') and contains(@class,'r-pointer')])[1]"));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
					firstFlightCard);
			Base.sleep();

			firstFlightCard.click();
			System.out.println("First flight clicked successfully!");
		} catch (Exception e) {
			System.out.println("Could not click first flight - " + e.getMessage());
		}
	}

	public void clickbook() {
		driver.findElement(Locators.bookbtn).click();
		Base.sleep();
	}


	public void validatebookingpage() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait until booking page heading is visible
	        WebElement bookingHeader = wait.until(ExpectedConditions
	                .visibilityOfElementLocated(By.xpath("//*[contains(text(),'Review & Traveller Details')]")));

	        if (driver.getCurrentUrl().contains("/flight/booking")) {
	            System.out.println(" Successfully navigated to Booking page. URL: " + driver.getCurrentUrl());
	            Reporter.generateReport(driver, extTest, Status.PASS, "Validated search page successfully");
	        }

	    } catch (Exception e) {
	        System.out.println(" Booking page URL not detected, current: " + driver.getCurrentUrl());
	        System.out.println(" Could not validate booking page - " + e.getMessage());
	        Reporter.generateReport(driver, extTest, Status.FAIL, "Validated search page failure");
	    }

	    // Always go back, even if validation failed
	    driver.navigate().back();

	    //Wait until search results page loads (optional but safer)
	    try {
	        WebDriverWait waitBack = new WebDriverWait(driver, Duration.ofSeconds(15));
	        waitBack.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h2[contains(text(),'Offers for you')] | //button[contains(text(),'Continue')]")
	        ));
	        System.out.println("Returned to search results page successfully.");
	    } catch (TimeoutException te) {
	        System.out.println(" Did not land back on search results page properly: " + te.getMessage());
	    }
	}


	public void clickContinueBtnfromReviewPage() {
		try {
			//driver.findElement(Locators.flightReviewPageContinuebtn).click();
			//Base.sleep();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			WebElement continueBtn = wait.until(
			    ExpectedConditions.elementToBeClickable(Locators.flightReviewPageContinuebtn));
			continueBtn.click();

			Reporter.generateReport(driver, extTest, Status.PASS, "Validated search page successfully");

		}

		catch (TimeoutException te) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Validated search page failure");
		}
	}
	
}
