package com.amazon.TestClasses;

import org.testng.annotations.Test;

import com.PageClasses.CartPage;
import com.PageClasses.CheckoutPage;
import com.PageClasses.HomePage;
import com.PageClasses.LoginPage;
import com.amazon.BaseTestClass.BaseTest;

public class AdditionOfProducts extends BaseTest{

    @Test
    public void AdditionOfProductsAndNavigation() throws InterruptedException {

        //Basic Details
        String mobileNo = "+919993433823";
        String password = "Saurabhv@28";
        String[] productName = {"Cricket Bat"};

        //Steps
        HomePage homePage = goToHomePageClass();
        LoginPage loginPage = homePage.goToLoginPage();
        homePage = loginPage.sendDataAndVerifyLoginStatus(mobileNo, password);
        CartPage cartPage = homePage.searchProduct(productName);
        CheckoutPage checkoutPage = cartPage.proceedToBuyProducts();
        checkoutPage.verifyPaymentType();
        checkoutPage.selectUPIPaymentModeAndVerify();
    }
    
}
