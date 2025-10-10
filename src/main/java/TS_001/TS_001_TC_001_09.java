package TS_001;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TS_001_TC_001_09 {

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Use an existing Chrome profile where uBlock Origin is installed
        // Replace the path below with your profile path
        options.addArguments("user-data-dir=C:\\Users\\sujit\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 2"); // e.g., Profile 2 has uBlock installed

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        FileWriter report = new FileWriter("TC001-09_AdBlockerProfileReport.txt");
        report.write("TC001-09: Homepage Load with Ad Blocker Enabled (Profile Method)\n");
        report.write("----------------------------------------------------------------\n");

        try {
            driver.get("https://www.krushistore.com/");
            Thread.sleep(3000);

            boolean pageUsable = true;

            // Verify key elements
            List<WebElement> headers = driver.findElements(By.tagName("header"));
            List<WebElement> footers = driver.findElements(By.tagName("footer"));
            List<WebElement> mainContent = driver.findElements(By.id("app-root")); // example main div

            if (headers.isEmpty()) {
                report.write(" Header missing\n");
                pageUsable = false;
            } else {
                report.write(" Header visible\n");
            }

            if (footers.isEmpty()) {
                report.write(" Footer missing\n");
                pageUsable = false;
            } else {
                report.write(" Footer visible\n");
            }

            if (mainContent.isEmpty()) {
                report.write(" Main content missing\n");
                pageUsable = false;
            } else {
                report.write(" Main content visible\n");
            }

            if (pageUsable) {
                report.write("\n Test Case Passed — Homepage works with Ad Blocker.\n");
            } else {
                report.write("\n Test Case Failed — Some essential elements missing with Ad Blocker.\n");
            }

            System.out.println("TC001-09 Completed — check TC001-09_AdBlockerProfileReport.txt for details.");

        } catch (Exception e) {
            report.write(" Test Case Failed: " + e.getMessage() + "\n");
            e.printStackTrace();
        } finally {
            report.close();
            driver.quit();
        }
    }
}
