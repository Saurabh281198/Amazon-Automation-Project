package com.amazon.TestClasses;

import org.testng.annotations.Test;

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
        String productType = "Best seller";

        //Steps
        HomePage homePage = goToHomePageClass();
        LoginPage loginPage = homePage.goToLoginPage();
        homePage = loginPage.sendCorrectDataAndLogin(mobileNo, password);
        homePage.searchProduct(productName, productType);

    }
    
}
