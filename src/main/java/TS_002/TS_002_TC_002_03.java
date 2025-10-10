package TS_002;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TS_002_TC_002_03 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the homepage
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            System.out.println("Opened homepage.");

            // Step 2: Click on the 'Contact' link in the navigation bar
            WebElement contactLink = driver.findElement(By.linkText("Contact"));
            contactLink.click();
            System.out.println("Clicked on 'Contact' link.");

            // Wait for page to load (simple static wait - replace with WebDriverWait if needed)
            Thread.sleep(3000);

            // Step 3: Verify that Contact page is loaded
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.toLowerCase().contains("contact")) {
                System.out.println("Contact page URL verified: " + currentUrl);
            } else {
                System.out.println("ERROR: Contact page URL not correct.");
            }

            // Step 4: Verify presence of contact info text
            try {
                WebElement contactInfo = driver.findElement(By.xpath("//*[contains(text(),'Contact') or contains(text(),'contact')]"));
                if (contactInfo.isDisplayed()) {
                    System.out.println("Contact info is visible.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Contact info not found.");
            }

            // Step 5: Optionally check for contact form
            try {
                WebElement form = driver.findElement(By.tagName("form"));
                if (form.isDisplayed()) {
                    System.out.println("Contact form is present.");
                }
            } catch (Exception e) {
                System.out.println("No contact form found on the page.");
            }

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
        } finally {
            // Close browser
            driver.quit();
            System.out.println("Test completed. Browser closed.");
        }
    }
}
