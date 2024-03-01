package com.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.UtilityClass.AbstractClass;

public class CheckoutPage extends AbstractClass {

    WebDriver driver;

    @FindBy(css = "#payment-option-text-default .break-word")
    WebElement paymentType;

    @FindBy(id = "payChangeButtonId")
    WebElement changePaymentTypeMethod;

    @FindBy(id = "pp-QRhB9p-123")
    WebElement otherUPIElement;

    @FindBy(id = "pp-QRhB9p-111")
    WebElement otherUPIElementTextBox;

    
    @FindBy(xpath = "//*[@name='ppw-widgetEvent:ValidateUpiIdEvent']")
    WebElement verifyIcon;

    @FindBy(css =  "#pp-QRhB9p-115 span")
    WebElement verifySuccElement;
    
    @FindBy(xpath = "//input[@aria-labelledby='pp-QRhB9p-143-announce']")
    WebElement paymentMethodElement;
    
    @FindBy(xpath = "(//a[@class='a-link-normal'])[2]")
    WebElement closeIcon;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyPaymentType() {
        if (paymentType.getText().contains("Pay on delivery")) {
            System.out.println("Select some other payment type!!");
        } else {
            System.out.println("You can proceed with placing order!!");
        }
    }

    public void selectUPIPaymentModeAndVerify() throws InterruptedException {
        changePaymentTypeMethod.click();
        waitForWebElementToAppear(closeIcon);
        clickElement(otherUPIElement);
        Thread.sleep(10000);
        verifyIcon.click();
        waitForWebElementToAppear(verifySuccElement);
        System.out.println(verifySuccElement.getText());
        if (paymentMethodElement.isEnabled()==true) {
            System.out.println("Payment method is visible!!");
        } else {
            System.out.println("Payment method is not visible!!");
        }
    }

}
