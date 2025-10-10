package TS_002;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TS_002_TC_002_06 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the homepage
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            System.out.println("Opened homepage.");

            // Step 2: Click on the 'Get Quote' link in the navigation bar
            WebElement getQuoteLink = driver.findElement(By.linkText("Get Quote"));
            getQuoteLink.click();
            System.out.println("Clicked on 'Get Quote' link.");

            // Wait for the page to load
            Thread.sleep(3000);

            // Step 3: Verify URL or page title contains 'quote'
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.toLowerCase().contains("quote")) {
                System.out.println("Get Quote page URL verified: " + currentUrl);
            } else {
                System.out.println("ERROR: Get Quote page URL does not appear correct.");
            }

            // Step 4: Verify form presence
            try {
                WebElement form = driver.findElement(By.tagName("form"));
                if (form.isDisplayed()) {
                    System.out.println("Get Quote form is visible.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Get Quote form not found.");
            }

            // Optional: Step 5 - Fill and submit the form with dummy data
            try {
                // Replace these locators with actual input field names or IDs once known
                WebElement nameField = driver.findElement(By.name("name")); // Example
                WebElement emailField = driver.findElement(By.name("email")); // Example
                WebElement messageField = driver.findElement(By.name("message")); // Example
                WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'Submit')]")); // Example

                nameField.sendKeys("Test User");
                emailField.sendKeys("test@example.com");
                messageField.sendKeys("This is a test quote request.");
                submitButton.click();

                System.out.println("Form submitted with dummy data.");
                Thread.sleep(2000); // Wait to simulate response

                // Add confirmation check here if a success message appears
            } catch (Exception e) {
                System.out.println("NOTE: Form fields not found or form submission skipped.");
            }

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Test completed. Browser closed.");
        }
    }
}

