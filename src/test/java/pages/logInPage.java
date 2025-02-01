package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class logInPage {
     //private static WebElement element = null;

        private static By signUpLogoPath = By.xpath("//*[contains(@class, 'w-[29px]')]");
        private static By signInPath = By.xpath("//span[@class='font-semibold cursor-pointer text-[#0066c0] no-underline hover:underline']");
        private static By mobileNumber = By.xpath("//input[@id='mobile']");
        private static By requestButton = By.xpath("//button[@type='submit']");

        public static void clickToSignUP(WebDriver driver){
            if (driver.findElements(signUpLogoPath).size() > 0) {
                driver.findElement(signUpLogoPath).click();
            } else {
                System.out.println("Sign Up button not found!");
            }
         }

         public static void clickToSignIn(WebDriver driver){
             if (driver.findElements(signInPath).size() > 0) {
                 driver.findElement(signInPath).click();
             } else {
                 System.out.println("Sign In button not found!");
             }
         }

         public static WebElement enterMobileNumber(WebDriver driver){
             return  driver.findElement(mobileNumber);

        }

        public static void clickToRequestOTPButton(WebDriver driver){
            if (driver.findElements(requestButton).size() > 0) {
                driver.findElement(requestButton).click();
            } else {
                System.out.println("Request OTP button not found!");
            }
        }
}
