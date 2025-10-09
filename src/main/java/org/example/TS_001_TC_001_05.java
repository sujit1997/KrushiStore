package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TS_001_TC_001_05 {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            String url = "https://www.krushistore.com/";

            // ---------- Test on WiFi ----------
            System.out.println("🔹 Testing on WiFi network...");
            long startTime = System.currentTimeMillis();
            driver.get(url);
            long endTime = System.currentTimeMillis();
            System.out.println(" Page load time on WiFi: " + (endTime - startTime) + " ms");

            // Optional wait
            Thread.sleep(2000);

            // ---------- Test on Mobile Data ----------
            System.out.println("\n🔹 Switch to mobile network (4G/5G) and press Enter to continue...");
            System.in.read(); // Wait for user to switch network

            startTime = System.currentTimeMillis();
            driver.navigate().refresh(); // Reload the page on mobile network
            endTime = System.currentTimeMillis();
            System.out.println(" Page load time on mobile network: " + (endTime - startTime) + " ms");

            System.out.println("\n Test Case Completed — Homepage load time measured on different networks.");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
