package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.temporal.ChronoField;

public class TS_001_TC_001_01 {

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
        driver.manage().window().maximize();
        driver.get("https://www.krushistore.com/");
        System.out.println(driver.getCurrentUrl());
        if(driver.getCurrentUrl().equals("https://www.krushistore.com/")){
            System.out.println("test case passed");
        }
        else {
            System.out.println("test case failed");
        }
        driver.close();
    }

}