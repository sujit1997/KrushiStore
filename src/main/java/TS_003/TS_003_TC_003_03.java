package TS_003;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TS_003_TC_003_03 {

    public static void main(String[] args) {

        // Set path to chromedriver if not in system PATH
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the website
            driver.get("https://www.krushistore.com/");
            Thread.sleep(3000); // Wait for site to load

            // Define different desktop screen sizes (width x height)
            int[][] resolutions = {
                    {1920, 1080},  // Full HD
                    {1366, 768},   // Common laptop
                    {1024, 768},   // Small desktop
                    {800, 600}     // Minimum supported resolution
            };

            for (int[] size : resolutions) {
                int width = size[0];
                int height = size[1];
                System.out.println("\n🔍 Testing resolution: " + width + "x" + height);
                driver.manage().window().setSize(new Dimension(width, height));
                Thread.sleep(2000); // Allow layout to adjust

                // Verify logo is visible
                try {
                    WebElement logo = driver.findElement(By.cssSelector("img[alt='Krushi Store']"));
                    if (logo.isDisplayed()) {
                        System.out.println(" Logo is visible.");
                    } else {
                        System.out.println(" Logo not visible.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println(" Logo not found.");
                }

                // Verify menu is accessible
                try {
                    WebElement menu = driver.findElement(By.cssSelector("nav"));
                    if (menu.isDisplayed()) {
                        System.out.println(" Navigation menu is visible.");
                    } else {
                        System.out.println(" Navigation menu not visible.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println(" Navigation menu not found.");
                }

                // Optionally, check if content overlaps or breaks
                JavascriptExecutor js = (JavascriptExecutor) driver;
                Long scrollWidth = (Long) js.executeScript("return document.body.scrollWidth;");
                if (scrollWidth > width + 50) {
                    System.out.println("️ Content overflows the visible screen.");
                } else {
                    System.out.println(" Layout fits within screen width.");
                }
            }

        } catch (Exception e) {
            System.out.println(" Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

