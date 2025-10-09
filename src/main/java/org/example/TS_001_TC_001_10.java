package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TS_001_TC_001_10 {

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        FileWriter report = new FileWriter("TC001-10_MobileResponsivenessReport.txt");
        report.write("TC001-10: Homepage Load on Mobile Devices\n");
        report.write("-----------------------------------------\n");

        try {
            driver.manage().window().maximize();
            driver.get("https://www.krushistore.com/");
            Thread.sleep(2000);

            // Device sizes for emulation
            Dimension[] mobileSizes = {
                    new Dimension(375, 667),   // iPhone 8 Portrait
                    new Dimension(667, 375),   // iPhone 8 Landscape
                    new Dimension(414, 896),   // iPhone 11 Portrait
                    new Dimension(896, 414)    // iPhone 11 Landscape
            };

            for (Dimension size : mobileSizes) {
                driver.manage().window().setSize(size);
                Thread.sleep(1000);

                report.write("🔹 Testing resolution: " + size.width + "x" + size.height + "\n");

                // Check if key elements are visible
                boolean layoutOk = true;

                List<WebElement> headers = driver.findElements(By.tagName("header"));
                List<WebElement> footers = driver.findElements(By.tagName("footer"));
                List<WebElement> mainContent = driver.findElements(By.id("app-root")); // adjust if needed

                if (headers.isEmpty()) {
                    report.write(" Header missing\n");
                    layoutOk = false;
                } else {
                    report.write(" Header visible\n");
                }

                if (footers.isEmpty()) {
                    report.write(" Footer missing\n");
                    layoutOk = false;
                } else {
                    report.write(" Footer visible\n");
                }

                if (mainContent.isEmpty()) {
                    report.write(" Main content missing\n");
                    layoutOk = false;
                } else {
                    report.write(" Main content visible\n");
                }

                if (layoutOk) {
                    report.write(" Layout OK for this resolution\n\n");
                } else {
                    report.write(" Layout issues detected at this resolution\n\n");
                }
            }

            System.out.println(" TC001-10 Completed — check TC001-10_MobileResponsivenessReport.txt for details.");

        } catch (Exception e) {
            report.write(" Test Case Failed: " + e.getMessage() + "\n");
            e.printStackTrace();
        } finally {
            report.close();
            driver.quit();
        }
    }
}

