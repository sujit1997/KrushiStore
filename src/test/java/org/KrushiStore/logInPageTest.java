package org.KrushiStore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.logInPage;

import java.time.Duration;

public class logInPageTest {
    public static WebDriver driver = null;

    public static void main(String[] args) {
        //SETUP WEBDRIVER
        //WebDriverManager.chromedriver().setup(); //Its use for chrome driver
        //driver = new ChromeDriver();
        // WebDriverManager.firefoxdriver().setup(); //Its use for Firefox Driver
        //driver = new FirefoxDriver();
        WebDriverManager.edgedriver().setup(); // Its use for Microsoft Edge browser
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.krushistore.com/");

        try {
            logInPage.clickToSignUP(driver);
            logInPage.clickToSignIn(driver);
            logInPage.enterMobileNumber(driver).sendKeys("9325825024");
            logInPage.clickToRequestOTPButton(driver);
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Test failed :" + e.getMessage());
        } finally {
            driver.quit();
        }
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
    }
}

