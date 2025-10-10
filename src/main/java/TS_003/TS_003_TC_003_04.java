package TS_003;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.*;

public class TS_003_TC_003_04 {

    public static void main(String[] args) {

        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // List of test devices
        List<Map<String, String>> devices = new ArrayList<>();

        // Mobile (iPhone X)
        devices.add(Collections.singletonMap("deviceName", "iPhone X"));

        // Tablet (iPad)
        devices.add(Collections.singletonMap("deviceName", "iPad"));

        // Desktop - no device emulation, manual window resize
        int[][] desktopResolutions = {
                {1920, 1080},
                {1366, 768}
        };

        // ========== MOBILE & TABLET TESTS ==========
        for (Map<String, String> device : devices) {
            System.out.println("\n Testing on: " + device.get("deviceName"));

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("mobileEmulation", device);

            WebDriver driver = new ChromeDriver(options);
            try {
                driver.get("https://www.krushistore.com/");
                Thread.sleep(3000);

                // Check for hamburger menu
                try {
                    WebElement hamburger = driver.findElement(By.cssSelector("button[class*='menu'], .menu-toggle"));
                    if (hamburger.isDisplayed()) {
                        System.out.println(" Hamburger menu is visible.");
                        hamburger.click();
                        Thread.sleep(1000);

                        // After opening, check if menu items are displayed
                        WebElement menuPanel = driver.findElement(By.cssSelector("nav"));
                        if (menuPanel.isDisplayed()) {
                            System.out.println(" Menu items are visible after clicking hamburger.");
                        } else {
                            System.out.println(" Menu items not visible after clicking hamburger.");
                        }
                    } else {
                        System.out.println(" Hamburger menu not visible.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println(" Hamburger menu not found.");
                }

            } catch (Exception e) {
                System.out.println(" Error on " + device.get("deviceName") + ": " + e.getMessage());
            } finally {
                driver.quit();
            }
        }

        // ========== DESKTOP TESTS ==========
        for (int[] resolution : desktopResolutions) {
            int width = resolution[0];
            int height = resolution[1];
            System.out.println("\n Testing on desktop resolution: " + width + "x" + height);

            WebDriver driver = new ChromeDriver();
            try {
                driver.manage().window().setSize(new Dimension(width, height));
                driver.get("https://www.krushistore.com/");
                Thread.sleep(3000);

                // Check navigation bar
                try {
                    WebElement navBar = driver.findElement(By.cssSelector("nav"));
                    if (navBar.isDisplayed()) {
                        System.out.println(" Navigation bar is visible at " + width + "x" + height);
                    } else {
                        System.out.println(" Navigation bar not visible.");
                    }

                    // Hamburger menu should NOT be visible on desktop
                    List<WebElement> hamburger = driver.findElements(By.cssSelector("button[class*='menu'], .menu-toggle"));
                    if (hamburger.isEmpty()) {
                        System.out.println(" No hamburger menu shown on desktop (expected).");
                    } else {
                        System.out.println(" Hamburger menu unexpectedly visible on desktop.");
                    }

                } catch (NoSuchElementException e) {
                    System.out.println(" Navigation bar not found.");
                }

            } catch (Exception e) {
                System.out.println(" Error on desktop resolution " + width + ": " + e.getMessage());
            } finally {
                driver.quit();
            }
        }
    }
}

