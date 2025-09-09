package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

public class CookieManager {

    private static final String COOKIE_FILE = "cookies.data";

    // Save cookies to file
    public static void saveCookies(WebDriver driver) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COOKIE_FILE))) {
            for (Cookie ck : driver.manage().getCookies()) {
                writer.write(
                        ck.getName() + ";" +
                        ck.getValue() + ";" +
                        ck.getDomain() + ";" +
                        ck.getPath() + ";" +
                        ck.getExpiry() + ";" +
                        ck.isSecure()
                );
                writer.newLine();
            }
            writer.flush();
            System.out.println("✅ Cookies saved successfully!");
        } catch (Exception e) {
            System.err.println("❌ Could not save cookies: " + e.getMessage());
        }
    }

    // Load cookies from file
    public static void loadCookies(WebDriver driver) {
        try (BufferedReader reader = new BufferedReader(new FileReader(COOKIE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, ";");
                String name = token.nextToken();
                String value = token.nextToken();
                String domain = token.nextToken();
                String path = token.nextToken();
                String expiry = token.nextToken();
                boolean isSecure = Boolean.parseBoolean(token.nextToken());

                Date expiryDate = null;
                if (!expiry.equals("null")) {
                    expiryDate = new Date(expiry);
                }

                Cookie ck = new Cookie.Builder(name, value)
                        .domain(domain)
                        .path(path)
                        .expiresOn(expiryDate)
                        .isSecure(isSecure)
                        .build();

                driver.manage().addCookie(ck);
            }
            System.out.println("✅ Cookies loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ No cookies file found. Fresh login needed.");
        } catch (Exception e) {
            System.err.println("❌ Could not load cookies: " + e.getMessage());
        }
    }

    // Delete cookie file (reset login)
    public static void resetCookies() {
        File file = new File(COOKIE_FILE);
        if (file.exists() && file.delete()) {
            System.out.println("✅ Cookies reset. OTP login will be required again.");
        }
    }
}
