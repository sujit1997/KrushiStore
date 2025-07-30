package SELENIUM_UI_TC;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TC_001_TS_001 {
    public static String browser = "chrome";
    public static WebDriver driver;

    public static void main(String[] args) {

    if (browser.equals("chrome")){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    } else if (browser.equals("firefox")) {
        WebDriverManager.chromedriver().setup();
        driver = new FirefoxDriver();
    } else if (browser.equals("edge")) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }
    driver.manage().window().maximize();
    driver.get("https://www.krushistore.com/");


        // Check page title
        String actualTitle = driver.getTitle();
        System.out.println("Actual title is : " +actualTitle);

        String expectedTitle = "Krushi Store - For The Farmers By The Farmers";
        if(actualTitle.equals(expectedTitle)){
            System.out.println("Test passed: Title matched");
        }else {
            System.out.println("Test failed: title did not matched");
        }
        driver.quit();



    }
}
