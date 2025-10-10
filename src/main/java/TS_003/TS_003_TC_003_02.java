package TS_003;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class TS_003_TC_003_02 {

    public static void main(String[] args) {

        // Set path to chromedriver if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Set mobile emulation for iPad
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPad");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Initialize WebDriver with mobile emulation
        WebDriver driver = new ChromeDriver(chromeOptions);

        try {
            // Step 1: Open the website
            driver.get("https://www.krushistore.com/");
            Thread.sleep(3000); // Wait for site to load

            // Step 2: Check for logo visibility
            WebElement logo = driver.findElement(By.cssSelector("img[alt='Krushi Store']"));
            if (logo.isDisplayed()) {
                System.out.println("✅ Logo is visible on tablet.");
            } else {
                System.out.println("❌ Logo not visible on tablet.");
            }

            // Step 3: Check for navigation menu visibility
            WebElement menuIcon = driver.findElement(By.cssSelector("button[class*='menu']"));
            if (menuIcon.isDisplayed()) {
                System.out.println("✅ Navigation menu is accessible on tablet.");
            } else {
                System.out.println("❌ Navigation menu not accessible on tablet.");
            }

            // Step 4: Check image responsiveness
            WebElement mainImage = driver.findElement(By.cssSelector("img"));
            Dimension imageSize = mainImage.getSize();
            if (imageSize.getWidth() <= 800) {
                System.out.println("✅ Image adjusts correctly on tablet.");
            } else {
                System.out.println("❌ Image may not be responsive for tablet screen.");
            }

            // Additional optional check: Page width (simulating tablet viewport)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Long width = (Long) js.executeScript("return window.innerWidth;");
            System.out.println("📏 Viewport width: " + width);

        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
        } finally {
            // Cleanup
            driver.quit();
        }
    }
}

