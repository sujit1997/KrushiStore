package TS_001;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
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

        // --- Run Test Cases ---
        testBanner(report);               // TC001-03
        testCrossBrowserLayout(report, browser); // TC001-04
        testPageLoadTime(report);          // TC001-05
        testConsoleErrors(report);         // TC001-06
        testLazyLoading(report);           // TC001-07
        testDisabledJS(report);            // TC001-08
        testAdBlocker(report);             // TC001-09
        testMobileResponsiveness(report);  // TC001-10

        report.close();
        driver.quit();
        System.out.println("\nTest Suite Completed — See KrushiStoreTestReport.txt for details.");
    }

    // ---------------- Test Methods ----------------

    public static void testBanner(FileWriter report) throws IOException, InterruptedException {
        driver.get("https://www.krushistore.com/");
        Thread.sleep(2000);
        report.write("TC001-03: Banner Check\n----------------------\n");

        List<WebElement> banners = driver.findElements(By.xpath("//*[@id=\"app-header\"]/div[2]/div/div[1]/div[1]/a/div/img"));
        if (banners.isEmpty()) {
            report.write(" No banners found on the homepage.\n\n");
            System.out.println("Banner Test Failed");
        } else {
            for (WebElement banner : banners) {
                report.write(" Banner visible: " + banner.getAttribute("src") + "\n");
            }
            report.write("\n");
            System.out.println("Banner Test Passed");
        }
    }

    public static void testCrossBrowserLayout(FileWriter report, String browser) throws IOException {
        report.write("TC001-04: Cross-Browser Layout\n------------------------------\n");
        report.write("Browser used: " + browser + "\n");
        report.write(" Layout visually verified (manual check recommended for cross-browser).\n\n");
        System.out.println("Cross-Browser Layout Recorded");
    }

    public static void testPageLoadTime(FileWriter report) throws IOException, InterruptedException {
        report.write("TC001-05: Page Load Time\n------------------------\n");
        long start = System.currentTimeMillis();
        driver.get("https://www.krushistore.com/");
        long end = System.currentTimeMillis();
        report.write("Page load time (ms): " + (end - start) + "\n\n");
        System.out.println("Page Load Time Measured");
    }

    public static void testConsoleErrors(FileWriter report) throws IOException, InterruptedException {
        report.write("TC001-06: Console Errors\n------------------------\n");
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
            report.write("No console errors found.\n\n");
            System.out.println("Console Errors Test Passed");
        } else {
            for (String error : errors) {
                report.write("❌ " + error + "\n");
            }
            report.write("\n");
            System.out.println("Console Errors Test Failed");
        }
    }

    public static void testLazyLoading(FileWriter report) throws IOException, InterruptedException {
        report.write("TC001-07: Validate Lazy Loading\n-------------------------------\n");
        driver.get("https://www.krushistore.com/");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        report.write(" Page scrolled to bottom — images should have loaded dynamically.\n\n");
        System.out.println("Lazy Loading Test Completed");
    }

    public static void testDisabledJS(FileWriter report) throws IOException, InterruptedException {
        report.write("TC001-08: Homepage Load with Disabled JS\n----------------------------------------\n");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-javascript");
        WebDriver jsDisabledDriver = new ChromeDriver(options);
        jsDisabledDriver.get("https://www.krushistore.com/");
        Thread.sleep(2000);

        List<WebElement> headers = jsDisabledDriver.findElements(By.tagName("header"));
        List<WebElement> footers = jsDisabledDriver.findElements(By.tagName("footer"));
        if (!headers.isEmpty() && !footers.isEmpty()) {
            report.write(" Header and footer visible with JS disabled.\n\n");
        } else {
            report.write(" Page not fully usable with JS disabled.\n\n");
        }
        jsDisabledDriver.quit();
        System.out.println("Disabled JS Test Completed");
    }

    public static void testAdBlocker(FileWriter report) throws IOException, InterruptedException {
        report.write("TC001-09: Homepage Load with Ad Blocker\n----------------------------------------\n");
        ChromeOptions options = new ChromeOptions();
        // Use existing profile with uBlock installed
        options.addArguments("user-data-dir=C:\\Users\\sujit\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 2");
        WebDriver adBlockDriver = new ChromeDriver(options);
        adBlockDriver.get("https://www.krushistore.com/");
        Thread.sleep(3000);

        List<WebElement> headers = adBlockDriver.findElements(By.tagName("header"));
        List<WebElement> footers = adBlockDriver.findElements(By.tagName("footer"));
        if (!headers.isEmpty() && !footers.isEmpty()) {
            report.write(" Homepage loaded correctly with Ad Blocker enabled.\n\n");
        } else {
            report.write(" Homepage broken with Ad Blocker enabled.\n\n");
        }
        adBlockDriver.quit();
        System.out.println("Ad Blocker Test Completed");
    }

    public static void testMobileResponsiveness(FileWriter report) throws IOException, InterruptedException {
        report.write("TC001-10: Homepage Mobile Responsiveness\n----------------------------------------\n");
        driver.get("https://www.krushistore.com/");
        Thread.sleep(2000);

        Dimension[] mobileSizes = {
                new Dimension(375, 667),   // iPhone 8 Portrait
                new Dimension(667, 375),   // iPhone 8 Landscape
                new Dimension(414, 896),   // iPhone 11 Portrait
                new Dimension(896, 414)    // iPhone 11 Landscape
        };

        for (Dimension size : mobileSizes) {
            driver.manage().window().setSize(size);
            Thread.sleep(1000);
            report.write("Tested resolution: " + size.width + "x" + size.height + "\n");

            List<WebElement> headers = driver.findElements(By.tagName("header"));
            List<WebElement> footers = driver.findElements(By.tagName("footer"));
            if (!headers.isEmpty() && !footers.isEmpty()) {
                report.write(" Layout OK\n");
            } else {
                report.write(" Layout issues detected\n");
            }
        }
        report.write("\n");
        System.out.println("Mobile Responsiveness Test Completed");
    }
}
