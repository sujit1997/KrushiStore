package org.KrushiStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


    // Main method to execute the test
    public class SignUp{
    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.krushistore.com/"); // Navigate to the target URL
        driver.findElement(By.xpath("//*[contain(@class, 'w-[29px]')]*")).click();

        // Use WebDriverWait with Duration for the timeout (new in Selenium 4.x)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Set up the browser and navigate to the website

    }
}
