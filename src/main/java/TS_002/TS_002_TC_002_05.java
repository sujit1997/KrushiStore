package TS_002;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Set;

public class TS_002_TC_002_05 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the homepage
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            System.out.println("Opened homepage.");

            // Store the original window handle
            String originalWindow = driver.getWindowHandle();

            // Step 2: Click on the 'Complaint' link
            WebElement complaintLink = driver.findElement(By.linkText("Complaint"));
            complaintLink.click();
            System.out.println("Clicked on 'Complaint' link.");

            // Step 3: Switch to the new window or tab (Google Form)
            Thread.sleep(3000);  // Wait for new tab to open

            Set<String> allWindows = driver.getWindowHandles();
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            // Step 4: Verify that it's a Google Form
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("docs.google.com/forms")) {
                System.out.println("Google Form loaded successfully: " + currentUrl);
            } else {
                System.out.println("ERROR: The opened page is not a Google Form.");
            }

            // Step 5: Check if the form is accessible
            try {
                WebElement formTitle = driver.findElement(By.className("freebirdFormviewerViewHeaderTitle"));
                if (formTitle.isDisplayed()) {
                    System.out.println("Google Form is visible with title: " + formTitle.getText());
                }
            } catch (Exception e) {
                System.out.println("ERROR: Google Form not found or not visible.");
            }

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Test completed. Browser closed.");
        }
    }
}

