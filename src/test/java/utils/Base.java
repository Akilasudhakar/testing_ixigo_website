package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {
	
	static final int TIME = 2000;
	
	public static  WebDriver driver;
	
	public void launchBrowser() {
		
		Properties prop = PropertyReader.readProperties();
		
		if(prop.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("credentials_enable_service", false);
			//chromePrefs.put("profile.password_manager_enabled", false);
			chromePrefs.put("profile.default_content_setting_values.notifications", 2);
		    chromePrefs.put("profile.default_content_setting_values.geolocation", 2);
			// Initialize ChromeDriver with the configured options
		    chromeOptions.setExperimentalOption("prefs", chromePrefs);
		   
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();

			
		}
		else if(prop.getProperty("Browser").equalsIgnoreCase("Edge")){
			System.setProperty("webdriver.edge.driver","C:\\Users\\akila\\Downloads\\edgedriver_win64 (1)\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			
		}
		driver.get(prop.getProperty("URL"));
	}
	public static void sleep() {
		try {
			Thread.sleep(TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
