package com.tap.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tap.base.BaseSetup;
import com.tap.report.ExtentReportManager;
import com.tap.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final ExtentReports extentReports = ExtentReportManager.initReport();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = CommonUtils.captureScreenshot(BaseSetup.driver, result.getMethod().getMethodName());
        test.get().log(Status.FAIL, "Test Failed"+ result.getThrowable());
        test.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {
        // Code to execute before starting the test suite
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
