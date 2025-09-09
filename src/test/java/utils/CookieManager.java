package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;

public class CookieManager {

    private static final String COOKIE_FILE = "cookies.data";

    public static void saveCookies(WebDriver driver) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COOKIE_FILE))) {
            for (Cookie ck : driver.manage().getCookies()) {
                writer.write(ck.getName() + ";" +
                        ck.getValue() + ";" +
                        ck.getDomain() + ";" +
                        ck.getPath() + ";" +
                        (ck.getExpiry() != null ? ck.getExpiry().getTime() : "null") + ";" +
                        ck.isSecure());
                writer.newLine();
            }
            System.out.println("✅ Cookies saved successfully!");
        } catch (Exception e) {
            System.err.println("❌ Could not save cookies: " + e.getMessage());
        }
    }

    public static void loadCookies(WebDriver driver) {
        try (BufferedReader reader = new BufferedReader(new FileReader(COOKIE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] token = line.split(";");
                String name = token[0];
                String value = token[1];
                String domain = token[2];
                String path = token[3];
                String expiry = token[4];
                boolean isSecure = Boolean.parseBoolean(token[5]);

                Date expiryDate = null;
                if (!expiry.equals("null")) {
                    expiryDate = new Date(Long.parseLong(expiry));
                    if (expiryDate.before(new Date())) {
                        System.out.println("⚠️ Found expired cookie for " + name + ", skipping.");
                        continue;
                    }
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

    public static void resetCookies() {
        File file = new File(COOKIE_FILE);
        if (file.exists() && file.delete()) {
            System.out.println("✅ Cookies reset. OTP login will be required again.");
        }
    }
}
