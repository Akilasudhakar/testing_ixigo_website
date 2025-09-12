package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {

    public static WebDriver driver;
    static final int TIME = 2000;

    //  Launch driver once
    public static void initDriver() {
        if (driver == null) {
            Properties prop = PropertyReader.readProperties();

            if (prop.getProperty("Browser").equalsIgnoreCase("Chrome")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("credentials_enable_service", false);
                chromePrefs.put("profile.default_content_setting_values.notifications", 2);
                chromePrefs.put("profile.default_content_setting_values.geolocation", 2);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);

                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();

            } else if (prop.getProperty("Browser").equalsIgnoreCase("Edge")) {
                System.setProperty("webdriver.edge.driver", 
                    "C:\\Users\\akila\\Downloads\\edgedriver_win64 (1)\\msedgedriver.exe");
                driver = new EdgeDriver();
                driver.manage().window().maximize();
            }

            driver.get(prop.getProperty("URL"));
        }
    }

    //  Get current driver
    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    //  Quit driver once
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    //  Simple sleep helper
    public static void sleep() {
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
