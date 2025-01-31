package org.KrushiStore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


    // Main method to execute the test
    public class signUpPageTest {
    public static void main(String[] args) {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.krushistore.com/"); // Navigate to the target URL
        driver.findElement(By.xpath("//*[contains(@class, 'w-[29px]')]")).click();
        driver.findElement(By.id("name")).sendKeys("sujit");
        driver.findElement(By.id("mobile")).sendKeys("9325825024");
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/main/div/form/button")).click();

        // Use WebDriverWait with Duration for the timeout (new in Selenium 4.x)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Set up the browser and navigate to the website
        driver.close();

    }
}
