package com.tap.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.tap.constants.AppConstants;
import com.tap.listeners.TestListener;
import com.tap.report.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseSetup {

    public static WebDriver driver;
    protected ExtentReports extentReports;
    protected ExtentTest test;

    @BeforeSuite
    public void suiteSteUp() {
        extentReports = ExtentReportManager.initReport();
    }

    @BeforeClass
    public void initDriver(){
        driver = new ChromeDriver(getChromeOptions());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.navigate().to(AppConstants.APP_URL);
    }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

    @AfterSuite
    public void afterSuiteSetUp(){
        if(extentReports != null){
            extentReports.flush();
            extentReports = null;
        }
    }


    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--ignore-certificate-errors");
        return options;
    }
}