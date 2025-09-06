package pages;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import objectrepository.Locators;
import utils.Base;
import utils.Reporter;

public class HomePage {

	WebDriver driver;
	WebDriverWait  wait;
	ExtentTest extTest;

	public HomePage(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		wait  = new WebDriverWait(driver,Duration.ofSeconds(3));
		this.extTest = extTest;
		System.out.println("Website has successfully launched");
	}
	


}
