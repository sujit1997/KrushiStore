package TS_001;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.List;

public class TS_001_TC_001_04 {

    public static void main(String[] args) throws InterruptedException {

        // List of browsers to test
        List<String> browsers = Arrays.asList("chrome", "firefox", "edge");

        for (String browser : browsers) {
            WebDriver driver = null;

            try {
                // Setup driver dynamically
                switch (browser.toLowerCase()) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        break;
                    default:
                        System.out.println(" Browser not supported: " + browser);
                        continue;
                }

                driver.manage().window().maximize();
                driver.get("https://www.krushistore.com/");
                Thread.sleep(2000);

                System.out.println("\n🔹 Testing on browser: " + browser);

                // Check if main banner is displayed
                WebElement banner = driver.findElement(By.xpath("//*[@id=\"app-header\"]/div[2]/div/div[1]/div[1]/a/div/img"));
                if (banner.isDisplayed()) {
                    System.out.println(" Banner visible");
                } else {
                    System.out.println(" Banner not visible");
                }

                // Check if navigation menu is displayed
                WebElement menu = driver.findElement(By.xpath("//*[@id=\"app-header\"]/div[2]/div/div[2]"));
                if (menu.isDisplayed()) {
                    System.out.println(" Navigation menu visible");
                } else {
                    System.out.println(" Navigation menu not visible");
                }

                // Check if footer is displayed
                WebElement footer = driver.findElement(By.xpath("//*[@id=\"app-footer\"]"));
                if (footer.isDisplayed()) {
                    System.out.println(" Footer visible");
                } else {
                    System.out.println(" Footer not visible");
                }

                System.out.println(" Layout verification completed on " + browser);

            } catch (Exception e) {
                System.out.println(" Test failed on browser " + browser + ": " + e.getMessage());
            } finally {
                if (driver != null) {
                    driver.quit();
                }
            }
        }

        System.out.println("\n Cross-browser layout verification completed for all browsers.");
    }
}
