package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.List;

public class TS_001_TC_001_02 {
    public static String browser = "chrome";
    public static WebDriver driver;
    public static void main(String[] args) throws MalformedURLException {
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://www.krushistore.com/");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total link found : " + links.size());

        int brokenLinks = 0;

        for (WebElement link : links){
            String url = link.getAttribute("href");

            if (url ==null || url.isEmpty() || url.startsWith("javascript")){
                continue;
            }try{
                HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if(responseCode >=400){
                    System.out.println("Broken Link" + url + " - code: " + responseCode);
                    brokenLinks++;
                }else {
                    System.out.println("valid link "+ url);
                }
            }catch (IOException e){
                System.out.println("Error checking link : " + url + "-" + e.getMessage());
            }
        }
        System.out.println("\n total broken link found" +brokenLinks);
        if (brokenLinks ==0){
            System.out.println("Test Case Passed - No broken link found");
        }else {
            System.out.println("Test case failed - " + brokenLinks +" broken link detected");
        }
        driver.close();

    }
}
