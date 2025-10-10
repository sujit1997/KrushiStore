package TS_002;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TS_002_TC_001_01 {

    public static String browser = "firefox";
    public static WebDriver driver;
    public static void main(String[] args) {
        if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        try {
            // Step 1: Open the homepage
            driver.get("https://www.krushistore.com/");
            driver.manage().window().maximize();

            // Step 2: Wait for the 'Home' link and click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement homeLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));

            // Click the 'Home' link
            homeLink.click();

            // Step 3: Verify the homepage is reloaded
            // You can wait for a specific element that confirms homepage is loaded
            // Adjust the selector below as per actual content
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class*='main']")));

            // Step 4: Confirm URL is still homepage
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals("https://www.krushistore.com/")) {
                System.out.println(" Test Passed: 'Home' link is functional and homepage loaded.");
            } else {
                System.out.println(" Test Failed: Unexpected URL after clicking 'Home' link: " + currentUrl);
            }

        } catch (Exception e) {
            System.out.println(" Test Failed: Exception occurred - " + e.getMessage());
        } finally {
            // Step 5: Close the browser
            driver.quit();
        }
    }
}

