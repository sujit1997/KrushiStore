package org.KrushiStore;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.logInPage;

public class TestNGDemo {
    public static WebDriver driver = null;
    public static ExtentReports extent;
    public static ExtentTest test;

//    @BeforeTest
//    public void setupExtentReports() {
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/report.html");
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//    }
    @BeforeTest
    public void setUpTest() {
        //SETUP WEBDRIVER
        //WebDriverManager.chromedriver().setup(); //Its use for chrome driver
        //driver = new ChromeDriver();
        // WebDriverManager.firefoxdriver().setup(); //Its use for Firefox Driver
        //driver = new FirefoxDriver();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        WebDriverManager.edgedriver().setup(); // Its use for Microsoft Edge browser
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public static void login() throws InterruptedException {
        driver.get("https://www.krushistore.com/");
        logInPage.clickToSignUP(driver);
        logInPage.clickToSignIn(driver);
        logInPage.enterMobileNumber(driver).sendKeys("9325825024");
        logInPage.clickToRequestOTPButton(driver);
        Thread.sleep(10000);
    }

//    @AfterTest
//    public void tearDownTest() {
//        System.out.println("Test completed successfully");
//        driver.quit();
//    }
@AfterMethod
public void tearDownTest(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
    } else if (result.getStatus() == ITestResult.SUCCESS) {
        test.log(Status.PASS, "Test Passed");
    } else {
        test.log(Status.SKIP, "Test Skipped");
    }
    driver.quit();
}

    @AfterTest
    public void tearDownExtentReports() {
        extent.flush();
    }

    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
}

