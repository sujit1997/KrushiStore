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

public class TS_002_TC_002_02 {

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

            // Step 2: Wait for and click 'Solar Fence' link in the navigation
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement solarFenceLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Solar Fence")));
            solarFenceLink.click();

            // Step 3: Wait for the page to load (adjust selector for actual Solar Fence content)
            WebElement pageHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

            // Step 4: Validate URL and page content
            String currentUrl = driver.getCurrentUrl();
            String headingText = pageHeading.getText();

            if (currentUrl.contains("solar-fence") && headingText.toLowerCase().contains("solar fence")) {
                System.out.println(" Test Passed: 'Solar Fence' page loaded successfully with relevant content.");
            } else {
                System.out.println(" Test Failed: 'Solar Fence' page not loaded or content mismatch.");
            }

        } catch (Exception e) {
            System.out.println(" Test Failed: Exception occurred - " + e.getMessage());
        } finally {
            // Step 5: Close the browser
            driver.quit();
        }
    }
}

