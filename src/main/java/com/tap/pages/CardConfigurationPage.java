package com.tap.pages;

import com.tap.base.BaseSetup;
import com.tap.constants.AppConstants;
import com.tap.utils.CommonUtils;
import com.tap.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class CardConfigurationPage extends BaseSetup {
    WebDriver driver;
    SeleniumUtils seleniumUtils;

    public CardConfigurationPage(WebDriver driver) {
        this.driver = driver;
        seleniumUtils = new SeleniumUtils(driver);
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(xpath = "//select[@id='currency']")
    private WebElement currencyDropdown;

    @FindBy(xpath = "//select[@id='scope']")
    private WebElement scopeDropdown;

    @FindBy(id = "tap-card-iframe")
    private WebElement flexContainer;

    @FindBy(id="tap-card-iframe-authentication")
    private WebElement page3DsContainer;

    @FindBy(id="challengeFrame")
    private WebElement submitButtonFrame;

    @FindBy(id = "card_input_mini")
    private WebElement cardNumberField;

    @FindBy(id = "date_input")
    private WebElement dateField;

    @FindBy(id = "cvv_input")
    private WebElement cvvField;

    @FindBy(css = "button[type='button']")
    private WebElement createTokenButton;

    @FindBy(id="challengeFrame")
    private WebElement challengeFrame;

    @FindBy(id="acssubmit")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@data-label='copy button']")
    private WebElement copyButton;

    public CardConfigurationPage selectCurrency(String currencyID){
        Select select = seleniumUtils.selectDropdown(currencyDropdown);
        select.selectByVisibleText(currencyID);
        return this;
    }

    public CardConfigurationPage selectScopeOption(String optionName){
        Select select = seleniumUtils.selectDropdown(scopeDropdown);
        select.selectByVisibleText(optionName);
        return this;
    }

    public CardConfigurationPage fillCardDetails(String cardNumber, String expiryDate, String cvv){
        seleniumUtils.switchToFrame(flexContainer);
        seleniumUtils.enterText(cardNumberField, cardNumber);
        seleniumUtils.enterText(dateField, expiryDate);
        seleniumUtils.enterText(cvvField, cvv);
        seleniumUtils.switchBackFromFrame();
        return this;
    }

    public CardConfigurationPage clickOnCreateTokenButton() throws InterruptedException {
        seleniumUtils.scrollToElement(createTokenButton);
        seleniumUtils.click(createTokenButton);
        Thread.sleep(5000);
        seleniumUtils.scrollToTop();
        return this;
    }

    public CardConfigurationPage switchToV3dSPageAndPressSubmitButton() throws InterruptedException {
        seleniumUtils.switchToFrame(flexContainer);
        seleniumUtils.switchToFrame(page3DsContainer);
        seleniumUtils.switchToFrame(submitButtonFrame);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        seleniumUtils.scrollToElement(submitButton);
        Thread.sleep(1000);
        seleniumUtils.executeScript(AppConstants.CLICK_FUN, submitButton);
        seleniumUtils.switchBackFromFrame();
        seleniumUtils.scrollToTop();
        return this;
    }

    public CardConfigurationPage validateResponseUpdated(){
        seleniumUtils.validateResponse();
        return this;
    }

    public void clickOnCopyButton() throws IOException, UnsupportedFlavorException {
        seleniumUtils.click(copyButton);
        System.out.println(CommonUtils.copyClipboardContents());
    }
}
