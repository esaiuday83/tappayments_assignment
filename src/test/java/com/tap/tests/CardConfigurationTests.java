package com.tap.tests;

import com.tap.base.BaseSetup;
import com.tap.constants.AppConstants;
import com.tap.dataprovider.TestData;
import com.tap.pages.CardConfigurationPage;
import com.tap.utils.CommonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CardConfigurationTests extends BaseSetup {

    CardConfigurationPage cardConfigurationPage;

    @BeforeClass
    public void initPages(){
        cardConfigurationPage = new CardConfigurationPage(driver);
    }

    @Test(dataProvider = "cardData", dataProviderClass = TestData.class)
    public void configureCardTest(String cardNumber, String expiryDate, String cvv) throws InterruptedException, IOException, UnsupportedFlavorException {
        test = extentReports.createTest("Configure Card Test: "+ cardNumber+", "+expiryDate+", "+cvv );
        cardConfigurationPage.selectCurrency(AppConstants.CURRENCY_CODE)
                .selectScopeOption(AppConstants.SCOPE_OPTION)
                .fillCardDetails(cardNumber, expiryDate, cvv)
                .clickOnCreateTokenButton()
                .switchToV3dSPageAndPressSubmitButton()
                .validateResponseUpdated()
                .clickOnCopyButton();
        CommonUtils.copyClipboardContents();

        driver.navigate().refresh();
    }

}
