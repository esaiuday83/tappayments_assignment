package com.tap.base;

import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {

    private static volatile WebDriver driver;
    private static Browser browserType = Browser.CHROME;

    protected static void setBrowserType(Browser browser) {
        browserType = browser;
    }


    private DriverManager(){}

    public static WebDriver getDriver(){
        if(driver == null){
            synchronized (DriverManager.class){
                if(driver == null){
                    switch (browserType){
                        case CHROME:
                            driver = new ChromeDriver(getChromeOptions());
                            break;
                        case EDGE:
                            driver = new EdgeDriver(getEdgeOptions());
                            break;
                        case FIREFOX:
                            driver = new FirefoxDriver(getFirefoxOptions());
                            break;
                        default:
                            throw new IllegalStateException("Unexpected browser type: " + browserType);
                    }
                }
            }
        }
        return driver;
    }


    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--ignore-certificate-errors");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("acceptInsecureCerts", true);
        return options;
    }


    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
