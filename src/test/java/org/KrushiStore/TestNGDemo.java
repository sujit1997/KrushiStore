package org.KrushiStore;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.logInPage;

public class TestNGDemo {
    public static WebDriver driver = null;

    @BeforeTest
    public void setUpTest() {
        //SETUP WEBDRIVER
        //WebDriverManager.chromedriver().setup(); //Its use for chrome driver
        //driver = new ChromeDriver();
        // WebDriverManager.firefoxdriver().setup(); //Its use for Firefox Driver
        //driver = new FirefoxDriver();
        WebDriverManager.edgedriver().setup(); // Its use for Microsoft Edge browser
        driver = new EdgeDriver();
    }

    @Test
    public static void login() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.krushistore.com/");

        logInPage.clickToSignUP(driver);
        logInPage.clickToSignIn(driver);
        logInPage.enterMobileNumber(driver).sendKeys("9325825024");
        logInPage.clickToRequestOTPButton(driver);
        Thread.sleep(10000);
    }

    @AfterTest
    public void tearDownTest() {
        System.out.println("Test completed successfully");
        driver.quit();
    }
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
}

