package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TS_001_TC_001_06 {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.krushistore.com/");

            // Wait for the page to load fully
            Thread.sleep(3000);

            // Capture browser console logs
            LogEntries logs = driver.manage().logs().get(LogType.BROWSER);

            List<String> errors = new ArrayList<>();
            List<String> warnings = new ArrayList<>();

            for (LogEntry entry : logs) {
                switch (entry.getLevel().toString()) {
                    case "SEVERE":
                        errors.add(" " + entry.getMessage());
                        break;
                    case "WARNING":
                        warnings.add(" " + entry.getMessage());
                        break;
                }
            }

            // Print summary in console
            System.out.println("---------- Console Log Summary ----------");
            System.out.println("Total Errors: " + errors.size());
            System.out.println("Total Warnings: " + warnings.size());

            if (!errors.isEmpty()) {
                System.out.println("\nErrors:");
                errors.forEach(System.out::println);
            }

            if (!warnings.isEmpty()) {
                System.out.println("\nWarnings:");
                warnings.forEach(System.out::println);
            }

            // Save to report file
            try (FileWriter writer = new FileWriter("ConsoleErrorsReport.txt")) {
                writer.write("TC001-06: Check for Console Errors\n");
                writer.write("----------------------------------\n");
                writer.write("Total Errors: " + errors.size() + "\n");
                writer.write("Total Warnings: " + warnings.size() + "\n\n");

                if (!errors.isEmpty()) {
                    writer.write("Errors:\n");
                    for (String error : errors) {
                        writer.write(error + "\n");
                    }
                    writer.write("\n");
                }

                if (!warnings.isEmpty()) {
                    writer.write("Warnings:\n");
                    for (String warning : warnings) {
                        writer.write(warning + "\n");
                    }
                }

                writer.write("\nTest Completed.\n");
            }

            // Final test result
            if (errors.isEmpty() && warnings.isEmpty()) {
                System.out.println("\n Test Case Passed — No console errors or warnings.");
            } else {
                System.out.println("\n Test Case Failed — Console errors/warnings found. See ConsoleErrorsReport.txt");
            }

        } catch (Exception e) {
            System.out.println(" Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
