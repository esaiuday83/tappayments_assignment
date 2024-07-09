package com.tap.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.tap.utils.CommonUtils;

import java.net.UnknownHostException;

public class ExtentReportManager {
    private static ExtentReports extentReports;
    private static final String frameworkPath = System.getProperty("user.dir");


    public static ExtentReports initReport() {
        System.out.println(frameworkPath);
        if(extentReports == null){
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(frameworkPath+"/execution_reports/report.html");
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Test Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Host Name", CommonUtils.getHostName());
            extentReports.setSystemInfo("Environment", "QA");
            extentReports.setSystemInfo("User Name", "Esaikiapapn Udayakumar");
        }
        return extentReports;
    }
}
