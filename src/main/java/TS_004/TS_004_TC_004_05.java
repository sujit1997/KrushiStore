package TS_004;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class TS_004_TC_004_05 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open website
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            Thread.sleep(3000); // Wait for page load

            // Step 2: Scroll to footer
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000); // Wait for footer to load

            // Step 3: Store the main window handle
            String mainWindow = driver.getWindowHandle();

            // Step 4: Define expected social media domains
            Map<String, String> expectedDomains = new LinkedHashMap<>();
            expectedDomains.put("Facebook", "facebook.com");
            expectedDomains.put("Instagram", "instagram.com");
            expectedDomains.put("YouTube", "youtube.com");

            // Step 5: Find all footer <a> links
            List<WebElement> socialLinks = driver.findElements(By.cssSelector("footer a"));

            // Track test status
            Map<String, Boolean> testResults = new LinkedHashMap<>();

            for (WebElement link : socialLinks) {
                String href = link.getAttribute("href");

                if (href == null || href.isEmpty()) continue;

                for (Map.Entry<String, String> platform : expectedDomains.entrySet()) {
                    if (href.contains(platform.getValue())) {

                        System.out.println("\n Testing " + platform.getKey() + " link...");

                        try {
                            // Open link in new tab
                            js.executeScript("window.open(arguments[0]);", href);
                            Thread.sleep(2000);

                            // Switch to new tab
                            Set<String> allWindows = driver.getWindowHandles();
                            for (String handle : allWindows) {
                                if (!handle.equals(mainWindow)) {
                                    driver.switchTo().window(handle);
                                    break;
                                }
                            }

                            // Validate URL
                            String currentUrl = driver.getCurrentUrl();
                            if (currentUrl.contains(platform.getValue())) {
                                System.out.println(" Test Passed: " + platform.getKey() + " link opened correctly: " + currentUrl);
                                testResults.put(platform.getKey(), true);
                            } else {
                                System.out.println(" Test Failed: Unexpected URL for " + platform.getKey() + ": " + currentUrl);
                                testResults.put(platform.getKey(), false);
                            }

                        } catch (Exception e) {
                            System.out.println(" Test Failed: Exception while testing " + platform.getKey() + " - " + e.getMessage());
                            testResults.put(platform.getKey(), false);
                        } finally {
                            // Close new tab and switch back
                            driver.close();
                            driver.switchTo().window(mainWindow);
                        }

                        break; // Move to next platform after match
                    }
                }
            }

            // Step 6: Print test summary
            System.out.println("\n === Social Media Link Test Summary ===");
            for (Map.Entry<String, Boolean> result : testResults.entrySet()) {
                if (result.getValue()) {
                    System.out.println(" " + result.getKey() + ": Test Passed");
                } else {
                    System.out.println(" " + result.getKey() + ": Test Failed");
                }
            }

        } catch (Exception e) {
            System.out.println(" Overall Test Failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
