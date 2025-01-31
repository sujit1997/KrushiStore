package org.KrushiStore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogIn {
    public static void main(String[] args) {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.krushistore.com/");
        driver.findElement(By.xpath("//*[contains(@class, 'w-[29px]')]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/main/div/p[2]/span")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/main/div/form/div/div/input")).sendKeys("9325825024");
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/main/div/form/button")).click();
        // driver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/div/div[1]/svg")).click();
        //driver.findElement(By.className("font-semibold cursor-pointer text-[#0066c0] no-underline hover:underline")).click();
    }
}
