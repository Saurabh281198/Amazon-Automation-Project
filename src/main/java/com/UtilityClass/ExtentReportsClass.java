package com.UtilityClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsClass {
    
    public static ExtentReports getExtentReport() {

        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//index.html");
        reporter.config().setDocumentTitle("Amazon Automation Test Reports");
        reporter.config().setReportName("Automation Resuts");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester Name", "Saurabh Verma");

        return extent;
    }
}
