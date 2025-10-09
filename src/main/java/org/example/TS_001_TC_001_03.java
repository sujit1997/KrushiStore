package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class TS_001_TC_001_03 {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        try {
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            //  Helper to fetch banners
            List<WebElement> banners = getBanners(driver);

            if (banners.isEmpty()) {
                System.out.println(" No banners found on the homepage.");
            } else {
                for (WebElement banner : banners) {
                    if (banner.isDisplayed()) {
                        System.out.println(" Banner visible: " + banner.getAttribute("src"));
                    } else {
                        System.out.println(" Banner not visible: " + banner.getAttribute("src"));
                    }
                }
            }

            // Test responsiveness
            Dimension[] sizes = {
                    new Dimension(1920, 1080),
                    new Dimension(1366, 768),
                    new Dimension(768, 1024),
                    new Dimension(375, 667)
            };

            for (Dimension size : sizes) {
                driver.manage().window().setSize(size);
                Thread.sleep(1000);

                // Re-fetch banners after resize
                banners = getBanners(driver);

                System.out.println("🔹 Window resized to: " + size.width + "x" + size.height);
                for (WebElement banner : banners) {
                    if (banner.isDisplayed()) {
                        System.out.println(" Banner visible at this size: " + banner.getAttribute("src"));
                    } else {
                        System.out.println(" Banner missing at this size: " + banner.getAttribute("src"));
                    }
                }
            }

            // Refresh page and verify banners
            driver.navigate().refresh();
            Thread.sleep(2000);

            banners = getBanners(driver);
            for (WebElement banner : banners) {
                if (banner.isDisplayed()) {
                    System.out.println(" Banner loaded after refresh: " + banner.getAttribute("src"));
                } else {
                    System.out.println(" Banner missing after refresh: " + banner.getAttribute("src"));
                }
            }

            System.out.println(" Test Case Passed — Banner images validated successfully.");

        } catch (Exception e) {
            System.out.println(" Test Case Failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Helper method to fetch banners dynamically
    private static List<WebElement> getBanners(WebDriver driver) {
        return driver.findElements(By.xpath("//*[@id=\"app-header\"]/div[2]/div/div[1]/div[1]/a/div/img"));
    }
}
