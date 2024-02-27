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

    @FindBy(className = "a-list-item")
    WebElement msgElement;

    By msg = By.cssSelector(".a-list-item");

    By pass = By.id("ap_password");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage sendData(String mobileNo, String password) throws InterruptedException {

        mobileNoInputBox.sendKeys(mobileNo);
        continueBtn.click();
        waitForElementToAppear(pass);
        passwordInputBox.sendKeys(password);
        signInBtn.click();

        if(msgElement.getText().contains("Incorrect")) {
            waitForElementToAppear(msg);
            System.out.println(msgElement.getText());
        }

        Thread.sleep(2000);
        HomePage homePage = new HomePage(driver);
        return homePage;

    }

}
