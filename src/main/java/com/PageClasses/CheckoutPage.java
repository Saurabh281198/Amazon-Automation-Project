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

    @FindBy(xpath = "//*[@aria-label = 'Other UPI Apps']/label/input")
    WebElement otherUPIElement;

    @FindBy(xpath = "//input[@placeholder='Enter UPI ID']")
    WebElement otherUPIElementTextBox;

    @FindBy(xpath = "//*[@name='ppw-widgetEvent:ValidateUpiIdEvent']")
    WebElement verifyIcon;

    @FindBy(css =  ".a-color-success.a-text-beside-button")
    WebElement verifySuccElement;
    
    @FindBy(xpath = "//*[@class='a-button-inner'] //input[@name='ppw-widgetEvent:SetPaymentPlanSelectContinueEvent']")
    WebElement paymentMethodElement;
    
    @FindBy(css = ".a-link-normal .a-icon-close")
    WebElement closeIcon;

    @FindBy(css = ".a-size-medium.a-color-success.a-text-bold span span")
    WebElement expectedDeliveryDate;

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

    public void verifyPaymentModeEnabledAndProceed() {
        if (paymentMethodElement.isEnabled()==true) {
            System.out.println("Payment method is visible!!");
            clickElement(paymentMethodElement);
            waitForWebElementToAppear(expectedDeliveryDate);
            System.out.println(expectedDeliveryDate.getText());
        } else {
            System.out.println("Payment method is not visible. Please enter appropraite UPI details!!");
        }
    }

    public void selectUPIPaymentModeAndVerify() throws InterruptedException {
        changePaymentTypeMethod.click();
        waitForWebElementToAppear(closeIcon);
        scrollToWebElement(otherUPIElement);
        clickElement(otherUPIElement);
        Thread.sleep(15000);
        verifyIcon.click();
        waitForWebElementToAppear(verifySuccElement);
        verifyPaymentModeEnabledAndProceed();
    }

}
