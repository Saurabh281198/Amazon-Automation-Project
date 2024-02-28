package com.amazon.TestClasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.PageClasses.HomePage;
import com.PageClasses.LoginPage;
import com.amazon.BaseTestClass.BaseTest;

public class LoginFunctionality extends BaseTest {

    @Test(dataProvider = "getData")
    public void testLoginCases(HashMap<String, String> data) throws InterruptedException {

        HomePage homePage = goToHomePageClass();
        LoginPage loginPage = homePage.goToLoginPage();
        homePage = loginPage.sendDataAndVerifyLoginStatus(data.get("MobileNo"), data.get("Password"));

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = jsonTextToHashmap(
                "C:\\Users\\904904\\OneDrive - Cognizant\\Desktop\\Java Project\\AmazonProject\\product\\src\\main\\java\\com\\GlobalData\\userDetails.json");
        return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };

    }

}
