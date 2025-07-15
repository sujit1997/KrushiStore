package UI_Use_Only_Selenium_TestNg;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class A_signIn {

    public static String browser = "chrome";
    public static WebDriver driver;

    public static void main(String[] args) {
        if (browser.equals("firefox")){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();

        driver.get("https://www.krushistore.com/?srsltid=AfmBOoroHwdVOs36vsQ5VMVvTtfLCirAINjiIG35YFrM-1uGQEJ1D93F");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[contains(@class, 'w-[29px]')]")).click();

    }
}
