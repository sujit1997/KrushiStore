package TS_004;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TS_004_TC_004_04 {

    public static void main(String[] args) {

        // Set ChromeDriver path if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the website
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            Thread.sleep(3000); // Wait for page to load

            // Step 2: Scroll to footer
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000); // Wait for footer elements to load

            // Step 3: Click on "Contact Us" link
            WebElement contactUsLink = driver.findElement(By.linkText("Contact Us"));
            contactUsLink.click();
            Thread.sleep(3000); // Wait for navigation

            // Step 4: Verify page URL
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            if (currentUrl.contains("contact")) {
                System.out.println(" Navigated to Contact Us page: " + currentUrl);
            } else {
                System.out.println(" Unexpected URL: " + currentUrl);
            }

            // Step 5: Check if contact form is visible
            try {
                WebElement form = driver.findElement(By.tagName("form"));
                if (form.isDisplayed()) {
                    System.out.println(" Contact form is visible on the page.");
                } else {
                    System.out.println(" Contact form is not visible.");
                }
            } catch (NoSuchElementException e) {
                System.out.println(" Contact form element not found.");
            }

        } catch (Exception e) {
            System.out.println(" Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

