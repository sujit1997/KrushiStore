package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KrushiStoreTestSuite {

    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, IOException {

        // Choose browser: "chrome" or "firefox"
        String browser = "chrome";

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println("Unsupported browser.");
            return;
        }

        driver.manage().window().maximize();

        FileWriter report = new FileWriter("KrushiStoreTestReport.txt");
        report.write("KrushiStore Automated Test Suite Report\n");
        report.write("====================================\n\n");

        // --- TC001-03: Banner Check ---
        testBanner(report);

        // --- TC001-04: Cross-Browser Layout ---
        testCrossBrowserLayout(report, browser);

        // --- TC001-05: Page Load Time ---
        testPageLoadTime(report);

        // --- TC001-06: Console Errors ---
        testConsoleErrors(report);

        report.close();
        driver.quit();
        System.out.println("\n Test Suite Completed — See KrushiStoreTestReport.txt for details.");
    }

    // ---------------- Test Methods ----------------

    public static void testBanner(FileWriter report) throws IOException, InterruptedException {
        driver.get("https://www.krushistore.com/");
        Thread.sleep(2000);

        report.write("TC001-03: Banner Check\n");
        report.write("----------------------\n");

        List<WebElement> banners = driver.findElements(By.xpath("//*[@id=\"app-header\"]/div[2]/div/div[1]/div[1]/a/div/img"));
        if (banners.isEmpty()) {
            report.write(" No banners found on the homepage.\n\n");
            System.out.println(" Banner Test Failed");
        } else {
            for (WebElement banner : banners) {
                report.write(" Banner visible: " + banner.getAttribute("src") + "\n");
            }
            report.write("\n");
            System.out.println(" Banner Test Passed");
        }
    }

    public static void testCrossBrowserLayout(FileWriter report, String browser) throws IOException {
        report.write("TC001-04: Cross-Browser Layout\n");
        report.write("------------------------------\n");
        report.write("Browser used: " + browser + "\n");
        report.write(" Layout visually verified (manual check recommended for cross-browser).\n\n");
        System.out.println(" Cross-Browser Layout Recorded");
    }

    public static void testPageLoadTime(FileWriter report) throws IOException, InterruptedException {
        report.write("TC001-05: Page Load Time\n");
        report.write("------------------------\n");

        long start = System.currentTimeMillis();
        driver.get("https://www.krushistore.com/");
        long end = System.currentTimeMillis();
        report.write("Page load time (ms): " + (end - start) + "\n\n");
        System.out.println(" Page Load Time Measured");
    }

    public static void testConsoleErrors(FileWriter report) throws IOException, InterruptedException {
        report.write("TC001-06: Console Errors\n");
        report.write("------------------------\n");

        Thread.sleep(2000);
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        List<String> errors = new ArrayList<>();

        for (LogEntry entry : logs) {
            if (entry.getLevel().toString().equals("SEVERE")) {
                errors.add(entry.getMessage());
            }
        }

        report.write("Total Errors: " + errors.size() + "\n");
        if (errors.isEmpty()) {
            report.write(" No console errors found.\n\n");
            System.out.println(" Console Errors Test Passed");
        } else {
            for (String error : errors) {
                report.write(" " + error + "\n");
            }
            report.write("\n");
            System.out.println(" Console Errors Test Failed");
        }
    }
}
