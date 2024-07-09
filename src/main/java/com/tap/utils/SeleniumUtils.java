package com.tap.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtils {
    private static  WebDriver driver = null;
    private static JavascriptExecutor executor;
    protected WebDriverWait wait;

    public SeleniumUtils(WebDriver driver){
        SeleniumUtils.driver = driver;
        executor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public  void click(WebElement element){
        element.click();
    }

    public  void enterText(WebElement element, String textToEnter){
        element.sendKeys(textToEnter);
    }

    public  void printText(String text){
        System.out.println(text);
    }
    public  void switchToFrame(WebElement element){
        driver.switchTo().frame(element);
    }

    public  void switchBackFromFrame(){
        driver.switchTo().defaultContent();
    }

    public  void scrollToElement(WebElement element){
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public   void scrollToTop(){
        executor.executeScript("window.scrollTo(0, 0);");
    }

    public  Select selectDropdown(WebElement element){
        return new Select(element);
        //select.selectByVisibleText(text);
    }

    public void executeScript(String argument, WebElement element){
        executor.executeScript(argument,element);
    }

    public void validateResponse(){
        ExpectedCondition<Boolean> idChangedToResponseWithTick = driver1 -> {
            assert driver1 != null;
            WebElement element1 = driver1.findElement(By.id("Response ✅"));
            String idValue = element1.getAttribute("id");
            return idValue.equals("Response ✅");
        };
        wait.until(idChangedToResponseWithTick);
    }
}
