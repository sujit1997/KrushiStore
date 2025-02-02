package org.KrushiStore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import pages.signUpPage;

public class DesiredCapabilityDemo {
    public static void main(String[] args) throws InterruptedException {

        EdgeOptions options = new EdgeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);

        WebDriverManager.edgedriver().setup();
        EdgeDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.krushistore.com/");
        signUpPage.clickToSignUP(driver);
        signUpPage.enterName(driver).sendKeys("sujit");
        signUpPage.enterNumber(driver).sendKeys("9325825024");
        signUpPage.clickToSubmitButton(driver);
        Thread.sleep(10000);
        driver.quit();
    }
}
