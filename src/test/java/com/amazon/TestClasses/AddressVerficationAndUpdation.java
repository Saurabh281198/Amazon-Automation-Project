package com.amazon.TestClasses;

import java.io.IOException;
import org.testng.annotations.Test;
import com.PageClasses.AddressPage;
import com.PageClasses.HomePage;
import com.PageClasses.LoginPage;
import com.amazon.BaseTestClass.BaseTest;
import com.amazon.BaseTestClass.RetryClass;

public class AddressVerficationAndUpdation extends BaseTest{

    @Test(retryAnalyzer = RetryClass.class)
    public void addressTest() throws InterruptedException, IOException {
        
        //Basic Details
        String mobileNo = "+919993433823";
        String password = "Saurabhv@28";
        
        //Steps
        HomePage homePage = goToHomePageClass();
        LoginPage loginPage = homePage.goToLoginPage();
        homePage = loginPage.sendDataAndVerifyLoginStatus(mobileNo, password);
        AddressPage addressPage = homePage.verifyAddressAndChange();
        addressPage.clickOnAppropriateAddress("Sankalp home");
        takeScreenshot("amazonHomePage", driver);
        addressPage.verifyIfAddressIsSelected();
    }

}
