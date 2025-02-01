package org.KrushiStore;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportsBasicReports {
    public static void main(String[] args) {
       ExtentSparkReporter spark_report = new ExtentSparkReporter("C:/Users/sujit/IdeaProjects/KrushiStore/Reports/report.html");
       ExtentReports report = new ExtentReports();
       report.attachReporter(spark_report);
       report.flush();
   }
}
