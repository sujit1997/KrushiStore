package TS_004;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

 public class TS_004_TC_004_03 {


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
            Thread.sleep(2000); // Wait for footer to load

            // Step 3: Click on "Terms & Conditions" link
            WebElement termsLink = driver.findElement(By.linkText("Terms & Conditions"));
            termsLink.click();
            Thread.sleep(3000); // Wait for navigation

            // Step 4: Validate URL contains "terms" or similar
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            if (currentUrl.contains("terms")) {
                System.out.println(" Navigated to Terms & Conditions page: " + currentUrl);
            } else {
                System.out.println(" Unexpected URL: " + currentUrl);
            }

            // Step 5: Verify terms content is visible
            try {
                WebElement termsContent = driver.findElement(
                        By.xpath("//*[contains(text(), 'Terms') or contains(text(), 'conditions')]")
                );
                if (termsContent.isDisplayed()) {
                    System.out.println(" Terms & Conditions content is visible.");
                } else {
                    System.out.println(" Terms content not visible.");
                }
            } catch (NoSuchElementException e) {
                System.out.println(" Terms & Conditions content not found.");
            }

        } catch (Exception e) {
            System.out.println(" Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

