package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class logInPage {
     private static WebElement element = null;

        public static WebElement enterMobileNumber(WebDriver driver){
            element = driver.findElement(By.xpath("//input[@id = 'mobile']"));
            return element;
        }
}
