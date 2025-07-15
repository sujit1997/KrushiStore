package UI_Use_Only_Selenium_TestNg;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class A_signIn {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        // WebDriverManager.firefoxdriver().setup();
        // WebDriverManager.edgedriver().setup();

        ChromeDriver driver = new ChromeDriver();
        // FirefoxDriver driver1 = new FirefoxDriver();
        // EdgeDriver driver2 = new EdgeDriver();

        driver.get("https://www.krushistore.com/?srsltid=AfmBOoroHwdVOs36vsQ5VMVvTtfLCirAINjiIG35YFrM-1uGQEJ1D93F");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[contains(@class, 'w-[29px]')]")).click();

    }
}
