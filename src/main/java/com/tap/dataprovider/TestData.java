package com.tap.dataprovider;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "cardData")
    public static Object[][] cardData() {
        return new Object[][] {
                {"5123450000000008", "01/39","100"},
                {"4508750015741019","01/39", "100"}
        };
    }
}
