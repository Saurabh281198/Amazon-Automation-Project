package com.PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.UtilityClass.AbstractClass;

public class LoginPage extends AbstractClass {

    WebDriver driver;

    @FindBy(id = "ap_email")
    WebElement mobileNoInputBox;

    @FindBy(xpath = "//span[@id='continue']/span[@class='a-button-inner']")
    WebElement continueBtn;

    @FindBy(id = "ap_password")
    WebElement passwordInputBox;

    @FindBy(css = "#auth-signin-button .a-button-inner")
    WebElement signInBtn;

    @FindBy(css = ".a-list-item")
    WebElement msgElement;

    //.a-alert-content .a-list-item

    @FindBy(css = "input[id='input-box-otp']")
    WebElement otpElement;

    @FindBy(css = "#cvf-submit-otp-button .a-button-input")
    WebElement submitElement;

    By msg = By.cssSelector(".a-alert-content .a-list-item");

    By pass = By.id("ap_password");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyLoginDetails() {

        if (msgElement.getText().trim().contains("incorrect")) {
            System.out.println("The credentails are invalid");
        } else {
            System.out.println("The credentails are valid");
        }
    }

    public HomePage sendDataAndVerifyLoginStatus(String mobileNo, String password) throws InterruptedException {

        mobileNoInputBox.sendKeys(mobileNo);
        continueBtn.click();
        waitForElementToAppear(pass);
        passwordInputBox.sendKeys(password);
        signInBtn.click();

        verifyLoginDetails();

        //Thread.sleep(10000);
        
        HomePage homePage = new HomePage(driver);
        return homePage;

    }

}
