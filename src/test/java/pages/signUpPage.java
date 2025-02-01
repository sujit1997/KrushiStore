package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class signUpPage {

    private static WebElement element = null;

    private static By signUpLogoPath = By.xpath("//*[contains(@class, 'w-[29px]')]");
    private static By nameField = By.id("name");
    private static By numberField = By.id("mobile");
    private static By submitButtonPath = By.xpath("/html/body/div[3]/div[3]/main/div/form/button");
    public static void clickToSignUP(WebDriver driver){
        driver.findElement(signUpLogoPath).click();
    }
    public static WebElement enterName(WebDriver driver){
        element = driver.findElement(nameField);
        return element;
    }
    public static WebElement enterNumber(WebDriver driver){
        element = driver.findElement(numberField);
        return element;
    }
    public static void clickToSubmitButton(WebDriver driver){
       driver.findElement(submitButtonPath).click();
    }
}
