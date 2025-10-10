package TS_003;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class TS_003_TC_003_01 {

    public static void main(String[] args) {

        // Set path to chromedriver if not in system PATH
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Set mobile emulation (e.g., iPhone X)
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(chromeOptions);

        try {
            // Step 1: Open the website
            driver.get("https://www.krushistore.com/");
            Thread.sleep(3000); // Wait for elements to load (adjust as needed)

            // Step 2: Check for alignment and element visibility (examples)
            // Example: Verify logo is displayed
            WebElement logo = driver.findElement(By.cssSelector("img[alt='Krushi Store']"));
            if (logo.isDisplayed()) {
                System.out.println(" Logo is visible.");
            } else {
                System.out.println(" Logo not visible.");
            }

            // Example: Check menu icon is visible (hamburger menu for mobile)
            WebElement menuIcon = driver.findElement(By.cssSelector("button[class*='menu']"));
            if (menuIcon.isDisplayed()) {
                System.out.println(" Mobile menu is visible.");
            } else {
                System.out.println(" Mobile menu not visible.");
            }

            // Example: Check image responsiveness
            WebElement mainBanner = driver.findElement(By.cssSelector("img"));
            Dimension imgSize = mainBanner.getSize();
            if (imgSize.getWidth() <= 400) {
                System.out.println(" Image is responsive on mobile.");
            } else {
                System.out.println(" Image may not be optimized for mobile.");
            }

            // Step 3: Additional checks can include viewport size, font scaling, broken layout, etc.

        } catch (Exception e) {
            System.out.println(" Test failed: " + e.getMessage());
        } finally {
            // Close browser
            driver.quit();
        }
    }
}
