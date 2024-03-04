package com.PageClasses;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.UtilityClass.AbstractClass;

public class AddressPage extends AbstractClass {

    WebDriver driver;

    public AddressPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".a-button.a-button-toggle.GLUX_Block .a-button-input")
    List<WebElement> addressDetailList;

    @FindBy(css = ".a-popover-trigger")
    WebElement deliverToUser;

    public void clickOnAppropriateAddress(String addressKeyword) throws InterruptedException {
        for (int i = 0; i < addressDetailList.size(); i++) {
            if (addressDetailList.get(i).getAttribute("aria-label").contains(addressKeyword)) {
                addressDetailList.get(i).click();
                break;
            }
        }
        Thread.sleep(3000);
    }

    public void verifyIfAddressIsSelected() {
        boolean value = addressDetailList.size() == 0;
        if (value == false) {
            System.out.println("Select appropriate keyword. Currently the address in not present!!");
        }
    }
}
