package TS_004;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TS_004_TC_004_02 {

    public static void main(String[] args) {

        // Set path to chromedriver if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the website
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            Thread.sleep(3000); // Wait for page load

            // Step 2: Scroll to the footer
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000); // Let footer load

            // Step 3: Click on "Privacy Policy" link
            WebElement privacyPolicyLink = driver.findElement(By.linkText("Privacy Policy"));
            privacyPolicyLink.click();
            Thread.sleep(3000); // Wait for navigation

            // Step 4: Verify URL contains expected term
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.toLowerCase().contains("privacy")) {
                System.out.println(" Navigated to Privacy Policy page: " + currentUrl);
            } else {
                System.out.println(" Unexpected URL after clicking Privacy Policy: " + currentUrl);
            }

            // Step 5: Verify presence of legal/policy-related text
            try {
                WebElement policyContent = driver.findElement(
                        By.xpath("//*[contains(text(), 'Privacy') or contains(text(), 'policy')]")
                );
                if (policyContent.isDisplayed()) {
                    System.out.println(" Privacy Policy content is visible.");
                } else {
                    System.out.println(" Privacy Policy content not visible.");
                }
            } catch (NoSuchElementException e) {
                System.out.println(" Privacy Policy content not found.");
            }

        } catch (Exception e) {
            System.out.println(" Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

