package org.KrushiStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignUp {
    WebDriver driver;

    public void setup() {
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.krushistore.com/"); // Navigate to the target URL

        // Use WebDriverWait with Duration for the timeout (new in Selenium 4.x)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the sign-up button to be clickable
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-header\"]/div[2]/div/div[2]/div[2]/div/div[1]/svg")));

        // Click on the sign-up button
        signUpButton.click();
    }

    // Method to clean up after the test
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser and end the session
        }
    }

    // Main method to execute the test
    public static void main(String[] args) {
        SignUp signUpTest = new SignUp(); // Create an instance of the SignUp class
        signUpTest.setup(); // Set up the browser and navigate to the website
        signUpTest.tearDown(); // Clean up after the test
    }
}
