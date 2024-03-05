package com.amazon.TestClasses;

import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.PageClasses.AddressPage;
import com.PageClasses.HomePage;
import com.PageClasses.LoginPage;
import com.amazon.BaseTestClass.BaseTest;
import com.amazon.BaseTestClass.RetryClass;

public class AddressVerficationAndUpdation extends BaseTest {

    @Test(retryAnalyzer = RetryClass.class,dataProvider = "getData")
    public void verifyAddressAndSelect(HashMap<String, String> data) throws InterruptedException, IOException {

        // Steps
        HomePage homePage = goToHomePageClass();
        LoginPage loginPage = homePage.goToLoginPage();
        homePage = loginPage.sendDataAndVerifyLoginStatus(data.get("loginMobileNo"),data.get("password"));
        AddressPage addressPage = homePage.verifyAddressAndChange();
        addressPage.clickOnAppropriateAddress("Classic Garden");
        takeScreenshot("amazonHomePage", driver);
        addressPage.verifyIfAddressIsSelected();
    }

    @Test(dataProvider = "getData",retryAnalyzer = RetryClass.class)
    public void updateNewAddress(HashMap<String, String> data) throws InterruptedException {

        // Steps
        HomePage homePage = goToHomePageClass();
        LoginPage loginPage = homePage.goToLoginPage();
        homePage = loginPage.sendDataAndVerifyLoginStatus(data.get("loginMobileNo"),data.get("password"));
        AddressPage addressPage = homePage.verifyAddressAndChange();
        addressPage.updateNewAddress(data.get("Country"), data.get("FullName"), data.get("MobileNo"),
                data.get("PinCode"), data.get("BuildingAddress"), data.get("Area"), data.get("Landmark"),
                data.get("City"), data.get("State"));
        addressPage.verifyIfAddressIsUpdated();
    }

    @DataProvider
    public Object[][] getData() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginMobileNo", "+919993433823");
        map.put("password", "Saurabhv@28");
        map.put("Country", "India");
        map.put("FullName", "Harini G");
        map.put("MobileNo", "9876543210");
        map.put("PinCode", "411046");
        map.put("BuildingAddress", "C-4 Classic Garden");
        map.put("Area", "Katraj");
        map.put("Landmark", "Opp. BVP University");
        map.put("City", "Pune");
        map.put("State", "MAHARASHTRA");

        return new Object[][] { { map } };
    }

}
