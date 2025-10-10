package TS_002;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TS_002_TC_002_04 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the homepage
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            System.out.println("Opened homepage.");

            // Step 2: Click on the 'About Us' link in the navigation bar
            WebElement aboutUsLink = driver.findElement(By.linkText("About Us"));
            aboutUsLink.click();
            System.out.println("Clicked on 'About Us' link.");

            // Wait for the page to load (basic wait - for demo only)
            Thread.sleep(3000);

            // Step 3: Verify that About Us page loaded
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.toLowerCase().contains("about")) {
                System.out.println("About Us page URL verified: " + currentUrl);
            } else {
                System.out.println("ERROR: About Us page URL not correct.");
            }

            // Step 4: Verify presence of company information (basic text check)
            try {
                WebElement aboutContent = driver.findElement(By.xpath("//*[contains(text(),'About') or contains(text(),'about')]"));
                if (aboutContent.isDisplayed()) {
                    System.out.println("About Us content is visible.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: About Us content not found.");
            }

        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Test completed. Browser closed.");
        }
    }
}
