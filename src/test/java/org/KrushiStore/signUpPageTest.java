package org.KrushiStore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.signUpPage;
import java.time.Duration;


    // Main method to execute the test
    public class signUpPageTest {
    public static void main(String[] args) {
        //SETUP WEBDRIVER
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {


        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.krushistore.com/"); // Navigate to the target URL

        //Perform Signup action
        //driver.findElement(By.xpath("//*[contains(@class, 'w-[29px]')]")).click();
        signUpPage.clickToSignUP(driver);
        signUpPage.enterName(driver).sendKeys("sujit");
        signUpPage.enterNumber(driver).sendKeys("9325825024");
        signUpPage.clickToSubmitButton(driver);
       // driver.findElement(By.xpath("/html/body/div[3]/div[3]/main/div/form/button")).click();

        // Use WebDriverWait with Duration for the timeout (new in Selenium 4.x)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Set up the browser and navigate to the website
        //driver.close();
        } catch (Exception e){
            System.out.println("Test failed " + e.getMessage());
        }finally {
            driver.close();
        }
    }
}
