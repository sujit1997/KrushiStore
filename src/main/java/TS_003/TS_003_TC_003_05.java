package TS_003;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.*;

public class TS_003_TC_003_05 {

    public static void main(String[] args) throws InterruptedException {

        // List of devices with mobile emulation (mobile + tablet)
        List<Map<String, String>> devices = Arrays.asList(
                Collections.singletonMap("deviceName", "iPhone X"),
                Collections.singletonMap("deviceName", "iPad")
        );

        // Desktop resolutions to test
        int[][] desktopResolutions = {
                {1920, 1080},
                {1024, 768}
        };

        // ========== MOBILE + TABLET ==========
        for (Map<String, String> device : devices) {
            System.out.println("\n Testing on: " + device.get("deviceName"));

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("mobileEmulation", device);
            WebDriver driver = new ChromeDriver(options);

            try {
                driver.get("https://www.krushistore.com/");
                Thread.sleep(3000);

                checkMediaScaling(driver);

            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }

        // ========== DESKTOP ==========
        for (int[] size : desktopResolutions) {
            int width = size[0];
            int height = size[1];
            System.out.println("\n Testing on desktop: " + width + "x" + height);

            WebDriver driver = new ChromeDriver();
            try {
                driver.manage().window().setSize(new Dimension(width, height));
                driver.get("https://www.krushistore.com/");
                Thread.sleep(3000);

                checkMediaScaling(driver);

            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }
    }

    // ==============================
    //  Function to Check Media Scaling
    // ==============================
    public static void checkMediaScaling(WebDriver driver) {
        List<WebElement> images = driver.findElements(By.tagName("img"));
        List<WebElement> videos = driver.findElements(By.tagName("video"));

        System.out.println("📸 Found " + images.size() + " image(s)");
        for (WebElement img : images) {
            if (isElementVisibleInViewport(driver, img)) {
                Dimension size = img.getSize();
                System.out.println(" Image visible - size: " + size.getWidth() + "x" + size.getHeight());

                if (size.getWidth() == 0 || size.getHeight() == 0) {
                    System.out.println(" Image not properly loaded or scaled.");
                }
            } else {
                System.out.println(" Image not fully visible in viewport.");
            }
        }

        System.out.println(" Found " + videos.size() + " video(s)");
        for (WebElement video : videos) {
            if (isElementVisibleInViewport(driver, video)) {
                Dimension size = video.getSize();
                System.out.println("Video visible - size: " + size.getWidth() + "x" + size.getHeight());

                if (size.getWidth() == 0 || size.getHeight() == 0) {
                    System.out.println(" Video not properly loaded or scaled.");
                }
            } else {
                System.out.println(" Video not fully visible in viewport.");
            }
        }
    }

    // ==============================
    // 🔍 Check Element Visibility in Viewport
    // ==============================
    public static boolean isElementVisibleInViewport(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;", element);
    }
}

