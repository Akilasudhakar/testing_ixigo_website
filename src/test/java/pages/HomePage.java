package pages;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	WebDriverWait  wait;
	ExtentTest extTest;
	String validmobile_number;
	String validotp;
	String invalidmobile_number;
	String invalidotp;
	
	Scanner sc = new Scanner(System.in);
	Properties prop = PropertyReader.readProperties();

	public HomePage(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		wait  = new WebDriverWait(driver,Duration.ofSeconds(3));
		this.extTest = extTest;
		//System.out.println("Website has successfully launched");
	}
	
	
	
	public void valid_url() {
		String expResult = "https://www.ixigo.com/";
		String actResult = driver.getCurrentUrl();
		Assert.assertEquals(actResult,expResult);
		

	}
	


		      
		
	
	public boolean site_open() {
		String expResult = "https://www.ixigo.com/";
		String actResult = driver.getCurrentUrl();
		 boolean status = actResult.equals(expResult);

		   
		    Assert.assertTrue(status);
		    
		
		return status;
	}
	
	public void displayed_successfully_chrome () {
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
		Assert.assertEquals(actResult,expResult);
		
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
		    try {
		        // ✅ Check if user is already logged in
		        List<WebElement> greetings = driver.findElements(By.xpath("//*[contains(text(),'Hey')]"));
		        if (!greetings.isEmpty()) {
		            System.out.println("✅ Already logged in, skipping mobile number entry");
		            Reporter.generateReport(driver, extTest, Status.PASS, "User already logged in, skipping login");
		            return;
		        }

		        // 🔄 Normal login flow if not logged in
		        wait.until(ExpectedConditions.elementToBeClickable(Locators.loginbtn)).click();
		        wait.until(ExpectedConditions.elementToBeClickable(Locators.clkphoneNumber)).click();

		        WebElement mobile = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(Locators.clkphoneNumber)
		        );
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
	        List<WebElement> otpInputs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                By.xpath("//*[@id='otp']//input")  // <-- adjust if needed
	        ));

	        if (otpInputs.size() < otp.length()) {
	            throw new RuntimeException("Not enough OTP input boxes found. Found: " + otpInputs.size());	        }

	        // Enter each digit into corresponding box
	        for (int i = 0; i < otp.length(); i++) {
	            otpInputs.get(i).clear();
	            otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
	        }

	        System.out.println("✅ OTP entered successfully!");
	       

	    } catch (Exception e) {
	        System.err.println("❌ Error while entering OTP: " + e.getMessage());
	       
	    }
	    
      }
    

    
    

		
	
	

		
	
	public void loggedin_success() {
		  //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//	    try {
//	        // ✅ Wait until either "Hey Ixigor/username" OR "Login/Signup" is stable
//	        boolean loggedIn = wait.until(driver -> {
//	            try {
//	                WebElement greeting = driver.findElement(By.xpath("//*[contains(text(),'Hey')]"));
//	                return greeting.isDisplayed();
//	            } catch (Exception e) {
//	            	//System.out.println(e);
//	                return false;
//	                
//	            }
//	        });
//
//	        if (loggedIn) {
//	            System.out.println("✅ User successfully logged in!");
//	        } else {
//	            Assert.fail("❌ Login did not persist – still showing Login/Signup");
//	        }
//
//	    } catch (TimeoutException e) {
//	        Assert.fail("❌ Login failed – greeting not found");
//	    }
	    
		 // ✅ avoid cached session issues
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    try {
	        // ✅ Step 1: Wait for profile/avatar to be visible
	        WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("/html/body/main/div[1]/div/div[2]/div[2]/div/div[1]/p/span")
	        ));

	        // ✅ Step 2: Confirm it’s displayed
	        if (profileIcon.isDisplayed()) {
	            System.out.println("✅ User successfully logged in! Profile icon visible.");
	            utils.CookieManager.saveCookies(driver);

	            // ✅ Step 3 (Optional): Hover to check menu items
	            try {
	                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
	                actions.moveToElement(profileIcon).perform();

	                List<WebElement> menuItems = driver.findElements(
	                    By.xpath("//*[text()='Logout' or text()='My Trips']")
	                );
	                if (!menuItems.isEmpty()) {
	                    System.out.println("✅ Profile menu detected: " + menuItems.get(0).getText());
	                } else {
	                    System.out.println("ℹ️ Profile menu not expanded, but login is successful.");
	                }
	            } catch (Exception hoverEx) {
	                System.out.println("ℹ️ Could not hover profile, but profile icon is present.");
	            }

	        } else {
	            utils.CookieManager.resetCookies();
	            Assert.fail("❌ Login did not persist – profile icon hidden.");
	        }

	    } catch (TimeoutException e) {
	        utils.CookieManager.resetCookies();
	        Assert.fail("❌ Login failed – profile icon not found.");
	    }
	}
	
	public void enterinvalidmobileNumber(String invalidmobileNumber) {
		
			 try {
				 
				 
				 WebElement invalidmobile = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.clkphoneNumber));
					invalidmobile.click();
					invalidmobile_number=invalidmobileNumber;
					invalidmobile.sendKeys(invalidmobileNumber);
					String errmsg = driver.findElement(Locators.invalidmobileerrormsg).getText();
					Reporter.generateReport(driver,extTest,Status.FAIL,errmsg);
					
					}
					catch(TimeoutException te) {
						//fail the extent report
						Reporter.generateReport(driver,extTest,Status.PASS,"Invalid mobile number accepted");
					}
				
			        
		
	
	}
	
//	public void enterinvalidotp() {
//		try {
//		System.out.println("Enter the otp received: ");
//		invalidotp = sc.next();
//		WebElement invalidotp_input = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.clkotp1));
//		invalidotp_input.click();
//        invalidotp_input.sendKeys(invalidotp);
//		driver.findElement(Locators.verifybtn).click();
//		
//		Reporter.generateReport(driver,extTest,Status.FAIL,"Invalid otp number is not accepted");
//		}
//		catch(TimeoutException te) {
//			//fail the extent report
//			Reporter.generateReport(driver,extTest,Status.PASS,"Invalid otp number is acccepted");
//			//driver.findElement(Locators.closebtn).click();
//		}
//	}
	
	
	
	public void rndtrip() {
		wait.until(ExpectedConditions.elementToBeClickable(Locators.rndTripBtn)).click();
	}
	
	 public void enterBoardingPlace(String bPlace) {
	        wait.until(ExpectedConditions.elementToBeClickable(Locators.frmbtn)).click();
	        driver.findElement(Locators.fromPlace).sendKeys(bPlace);
	        Base.sleep();
	        //driver.findElement(Locators.fromPlace).sendKeys(Keys.ENTER); // choose first suggestion
	        // select the first suggestion explicitly
	        List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(15))
	                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath ("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]")));
	       
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
	        //driver.findElement(Locators.toPlace).sendKeys(Keys.ENTER); // choose first suggestion
	        // select the first suggestion explicitly/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]
	        List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(20))
	                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='block truncate' and text()='" + lPlace + "']")));
	        
	        if (!results.isEmpty()) {
	            results.get(0).click();
	        } else {
	        	driver.findElement(Locators.toPlace).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	        }}
	    
	    public static  String[] getMonthYear(String monthYearVal) {
		return monthYearVal.split(" ");
	    }
	    
	    public void bookingDate(String dptdate, String dptmonth, String dptyear, String rtnday, String rtnmonth, String rtnyear) {
	    	wait.until(ExpectedConditions.elementToBeClickable(Locators.deptbtn));
		    	
		    	String monthYearVal = wait.until(ExpectedConditions.elementToBeClickable(Locators.monthYear)).getText().trim();
		    	System.out.println(dptmonth);
		    	System.out.println(dptyear);
		    	System.out.println(dptdate);
		    	System.out.println(rtnmonth);
		    	System.out.println(rtnyear);
		    	System.out.println(rtnday);
		    	System.out.println(getMonthYear(monthYearVal)[0]);
		    	System.out.println(getMonthYear(monthYearVal)[1]);
		    	System.out.println(dptmonth.equals(getMonthYear(monthYearVal)[0]));
		    	System.out.println(dptyear.equals(getMonthYear(monthYearVal)[1]));
		    	
		    	
		    	while(!(getMonthYear(monthYearVal)[0].equalsIgnoreCase(dptmonth)
		    			&&
		    			getMonthYear(monthYearVal)[1].equals(dptyear))) {
		    		System.out.println(dptmonth);
		    		System.out.println(dptyear);
		    		
		    		
		    		 wait.until(ExpectedConditions.elementToBeClickable(Locators.nextbtn)).click();
		    		monthYearVal = driver.findElement(Locators.monthYear).getText();
		    		
		    	}
		    	try {
		    		if(dptdate.startsWith("0")) {
		    			dptdate=dptdate.substring(1);
		    			
		    		}
		    		
		    		driver.findElement(By.xpath("//abbr[text()='" + dptdate + "']")).click();
		    		
		    		
		    	}catch(Exception e) {
		    		System.out.println("wrong date: "+ dptmonth + ":"+dptdate);
		    	}
		    	
		    	 monthYearVal =wait.until(ExpectedConditions.elementToBeClickable(Locators.monthYear)).getText();
		    	
		    	while(!(getMonthYear(monthYearVal)[0].equalsIgnoreCase(rtnmonth)
		    			&&
		    			getMonthYear(monthYearVal)[1].equals(rtnyear))) {
		    		wait.until(ExpectedConditions.elementToBeClickable(Locators.nextbtn)).click();
		    		monthYearVal = driver.findElement(Locators.monthYear).getText();
		    	}
		    	try {
		    		if(rtnday.startsWith("0")) {
		    			rtnday=rtnday.substring(1);
		    			
		    		}
		    		driver.findElement(By.xpath("//abbr[text()='" + rtnday + "']")).click();
		    		
		    	}catch(Exception e) {
		    		System.out.println("wrong date: "+ rtnmonth + ":"+rtnday);
		    	}
		    	
		    	
		    }
	    
	    public void trvlsandClass(String adultCount, String childCount) {
			//  wait.until(ExpectedConditions.elementToBeClickable(Locators.clickTrvlandCls));
	    	int aCount = Integer.parseInt(adultCount);
			int cCount = Integer.parseInt(childCount);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[.//p[normalize-space()='Adults'] and .//p[normalize-space()='12 yrs or above']]//button[@data-testid='"+aCount+"'])[1]"))).click();
	    	    
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[.//p[normalize-space()='Adults'] and .//p[contains(text(),'12 yrs or above')]]/following::div[.//p[normalize-space()='Children'] and .//p[contains(text(),'2 - 12 yrs')]])[1]//button[@data-testid='" + cCount + "']"))).click();

				

			  
		  }
		  
		  public void donebtn(String businesscls) {
			 // driver.findElement(By.xpath("//span[text()='"+businesscls.toLowerCase()+"']")).click();
			  driver.findElement(By.xpath("//span[text()='"+businesscls+"']")).click();
		        Base.sleep();
		        driver.findElement(Locators.doneBtn).click(); 
			  
		  }
		  
		  public void searchbtn() {
			  driver.findElement(Locators.searchBtn).click();
			  Base.sleep();
		  }
		  
		  public void validatesearchpage() {
			// driver.findElement(Locators.validateSearchPage).click();
			 String expResult = "Filters";
			  String actualResult= driver.findElement(Locators.validateSearchPage).getText();
			  Base.sleep();
			  
			  if(expResult.equals(actualResult)) {
				 // Reporter.captureScreenshot(driver, actualResult);
				  
						String userDir = System.getProperty("user.dir");

						// to take time stamp
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss"); // yyyy-MM-dd
						String dateTime = sdf.format(date); // 27-01-2025_16_43_54
						String fileName = userDir + "\\screenshots\\" + "successfully launched" + "_" + dateTime + ".png";

						TakesScreenshot scrShot = (TakesScreenshot) driver;
						File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
						File destFile = new File(fileName); // fileName System.currentTimeMillis()
						try {
							FileUtils.copyFile(srcFile, destFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// TODO Auto-generated catch block
						
					}
				  
		  }
	    

}
