package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TS_001_TC_001_08 {

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriverManager.chromedriver().setup();

        // Disable JavaScript via ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-javascript"); // Disable JS

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        FileWriter report = new FileWriter("TC001-08_DisabledJSReport.txt");
        report.write("TC001-08: Homepage Load with Disabled JavaScript\n");
        report.write("------------------------------------------------\n");

        try {
            driver.get("https://www.krushistore.com/");
            Thread.sleep(2000);

            // Check key elements
            List<WebElement> headers = driver.findElements(By.tagName("header"));
            List<WebElement> footers = driver.findElements(By.tagName("footer"));

            boolean contentVisible = false;

            if (!headers.isEmpty()) {
                report.write(" Header is visible\n");
                contentVisible = true;
            } else {
                report.write(" Header is missing\n");
            }

            if (!footers.isEmpty()) {
                report.write(" Footer is visible\n");
                contentVisible = true;
            } else {
                report.write(" Footer is missing\n");
            }

            // Additional check for main links
            List<WebElement> links = driver.findElements(By.tagName("a"));
            if (!links.isEmpty()) {
                report.write(" Main links are visible: Total " + links.size() + "\n");
                contentVisible = true;
            } else {
                report.write(" No links visible\n");
            }

            if (contentVisible) {
                report.write("\n Test Case Passed — Homepage usable without JavaScript.\n");
            } else {
                report.write("\n Test Case Failed — Page not usable without JavaScript.\n");
            }

            System.out.println(" TC001-08 Completed — check TC001-08_DisabledJSReport.txt for details.");

        } catch (Exception e) {
            report.write(" Test Case Failed: " + e.getMessage() + "\n");
            e.printStackTrace();
        } finally {
            report.close();
            driver.quit();
        }
    }
}
