package com.PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.UtilityClass.AbstractClass;

public class HomePage extends AbstractClass {

    WebDriver driver;

    @FindBy(className = "nav-action-signin-button")
    WebElement signInButton;

    By signIn = By.cssSelector(".nav-action-signin-button");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage goToLoginPage() {

        navigateToHelloUser();
        waitForElementToAppear(signIn);
        signInButton.click();

        LoginPage loginPage = new LoginPage(driver);
        return loginPage;

    }

}
