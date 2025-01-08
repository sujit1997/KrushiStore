package org.KrushiStore;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import static org.testng.Assert.assertTrue;

public class Test {
    public static void main(String args[]){
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.krushistore.com/");
        String url = driver.getCurrentUrl();
//        assertTrue(url.contains("krushistore"));
        System.out.println(url);
        driver.findElement(By.xpath("//*[contains(@class, ' w-[29px]' )]")).click();
    }
}