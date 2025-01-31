package org.KrushiStore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.loginpage;

public class logInPageTest {
    private static WebDriver driver = null;
    public static void main(String[] args) {
        WebDriver driver;
        //WebDriverManager.chromedriver().setup(); //Its use for chrome driver
        //driver = new ChromeDriver();
        // WebDriverManager.firefoxdriver().setup(); //Its use for Firefox Driver
        //driver = new FirefoxDriver();
        WebDriverManager.edgedriver().setup(); // Its use for Microsoft Edge browser
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.krushistore.com/");
        driver.findElement(By.xpath("//*[contains(@class, 'w-[29px]')]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/main/div/p[2]/span")).click();
       // WebElement enternumber = driver.findElement(By.xpath("/html/body/div[3]/div[3]/main/div/form/div/div/input"));
        //enternumber.sendKeys("9325825024");
        loginpage.enterMobileNumber(driver).sendKeys("9325825024");
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/main/div/form/button")).click();


        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        // driver.close();


    }

}
