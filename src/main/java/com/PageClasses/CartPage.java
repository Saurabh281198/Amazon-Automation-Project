package com.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.UtilityClass.AbstractClass;

public class CartPage extends AbstractClass {

    WebDriver driver;

    @FindBy(xpath = "//*[@name='proceedToRetailCheckout']")
    WebElement proceedToBuy;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage proceedToBuyProducts() {
        proceedToBuy.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }

}
