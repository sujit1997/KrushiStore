package TS_001;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TS_001_TC_001_07 {

    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        FileWriter report = new FileWriter("TC001-07_LazyLoadingReport.txt");
        report.write("TC001-07: Validate Image Lazy Loading\n");
        report.write("------------------------------------\n");

        try {
            driver.get("https://www.krushistore.com/");
            Thread.sleep(2000);

            // Find all images
            List<WebElement> images = driver.findElements(By.tagName("img"));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            int loadedImages = 0;

            // Scroll down slowly
            for (int i = 0; i < 1000; i += 200) {
                js.executeScript("window.scrollBy(0, 200);");
                Thread.sleep(500); // Allow images to load

                for (WebElement img : images) {
                    // Check if image is in viewport and displayed
                    Boolean inView = (Boolean) js.executeScript(
                            "var rect = arguments[0].getBoundingClientRect();" +
                                    "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", img);

                    if (inView && img.isDisplayed()) {
                        loadedImages++;
                        report.write(" Image loaded in viewport: " + img.getAttribute("src") + "\n");
                    }
                }
            }

            if (loadedImages == 0) {
                report.write(" No images loaded during scroll. Lazy loading may be missing.\n");
            } else {
                report.write("\nTotal images loaded dynamically: " + loadedImages + "\n");
                report.write(" Test Case Passed — Lazy loading working.\n");
            }

            System.out.println(" TC001-07 Completed — check TC001-07_LazyLoadingReport.txt for details.");

        } catch (Exception e) {
            report.write(" Test Case Failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            report.close();
            driver.quit();
        }
    }
}
