package TS_004;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TS_004_TC_004_01 {

    public static void main(String[] args) {

        // Set path to chromedriver if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open website
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            Thread.sleep(3000); // Wait for page to load

            // Step 2: Scroll to footer
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000); // Wait for footer to become visible

            // Step 3: Locate and click 'About Us' link
            WebElement aboutUsLink = driver.findElement(By.linkText("About Us"));
            aboutUsLink.click();
            Thread.sleep(3000); // Wait for navigation

            // Step 4: Verify that 'About Us' page is loaded
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("about") || currentUrl.contains("about-us")) {
                System.out.println(" 'About Us' page URL: " + currentUrl);
            } else {
                System.out.println(" Unexpected URL: " + currentUrl);
            }

            // Step 5: Check if expected content exists
            try {
                WebElement heading = driver.findElement(By.xpath("//*[contains(text(), 'About')]"));
                if (heading.isDisplayed()) {
                    System.out.println(" 'About Us' content is visible.");
                } else {
                    System.out.println(" 'About Us' content not visible.");
                }
            } catch (NoSuchElementException e) {
                System.out.println(" 'About Us' content not found.");
            }

        } catch (Exception e) {
            System.out.println(" Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
