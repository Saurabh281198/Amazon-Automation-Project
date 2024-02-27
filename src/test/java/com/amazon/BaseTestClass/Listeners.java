package com.amazon.BaseTestClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.UtilityClass.ExtentReportsClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent;
    ExtentTest test;
    ThreadLocal<ExtentTest> safeTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        extent = ExtentReportsClass.getExtentReport();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        safeTest.get().fail(result.getThrowable());

        String path = System.getProperty("user.dir") + "\\Reports\\" + result.getMethod().getMethodName() + ".png";
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            takeScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            e.printStackTrace();
        }

        safeTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        safeTest.get().log(Status.SKIP, "The test was skipped");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());
        safeTest.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        safeTest.get().log(Status.PASS, "The test passed successfully");
    }

}
