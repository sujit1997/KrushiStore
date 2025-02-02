package org.KrushiStore;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.logInPage;
import pages.signUpPage;

import java.time.Duration;

public class TestNGDemo2 {
    public static WebDriver driver = null;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeTest
    public void setupExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }
    @BeforeTest
    public void setUpTest() {
        //SETUP WEBDRIVER
        //WebDriverManager.chromedriver().setup(); //Its use for chrome driver
        //driver = new ChromeDriver();
        // WebDriverManager.firefoxdriver().setup(); //Its use for Firefox Driver
        //driver = new FirefoxDriver();
        WebDriverManager.edgedriver().setup(); // Its use for Microsoft Edge browser
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public static void signup()  {
        test = extent.createTest("SignUp test");
        driver.get("https://www.krushistore.com/");
        signUpPage.clickToSignUP(driver);

        signUpPage.enterName(driver).sendKeys("Test User");
        signUpPage.enterNumber(driver).sendKeys("9658749658");

        signUpPage.clickToSubmitButton(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
    }
    @Test(dependsOnMethods = "signup")
    public static void login()  {
        test = extent.createTest("Login Test");
        driver.get("https://www.krushistore.com/");
        logInPage.clickToSignUP(driver);
        logInPage.clickToSignIn(driver);
        logInPage.enterMobileNumber(driver).sendKeys("9325825024");
        logInPage.clickToRequestOTPButton(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
    }


@AfterMethod
public void tearDownTest(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
    } else if (result.getStatus() == ITestResult.SUCCESS) {
        test.log(Status.PASS, "Test Passed");
    } else {
        test.log(Status.SKIP, "Test Skipped");
    }
    }

//    @AfterTest
//    public void tearDownTest() {
//        System.out.println("Test completed successfully");
//        driver.quit();
//    }

    @AfterTest
    public void tearDownExtentReports() {
        driver.quit();
        extent.flush();
    }

}