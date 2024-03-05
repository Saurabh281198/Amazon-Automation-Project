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

    @FindBy(linkText = "Add an address or pick-up point")
    WebElement addAnAddress;

    @FindBy(css = ".a-section.a-spacing-none.address-plus-icon.aok-inline-block")
    WebElement addAddressPlusIcon;

    @FindBy(css = "#address-ui-widgets-countryCode span[data-action='a-dropdown-button']")
    WebElement countryNameElement;

    @FindBy(xpath = "//a[contains(@id,'address-ui-widgets-countryCode')]")
    List<WebElement> countryNamesList;

    @FindBy(id = "address-ui-widgets-enterAddressFullName")
    WebElement fullNameElement;

    @FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
    WebElement mobileNoElement;

    @FindBy(id = "address-ui-widgets-enterAddressPostalCode")
    WebElement pincodeElement;

    @FindBy(id = "address-ui-widgets-enterAddressLine1")
    WebElement buildingNameElement;

    @FindBy(id = "address-ui-widgets-enterAddressLine2")
    WebElement areaNameElement;

    @FindBy(id = "address-ui-widgets-landmark")
    WebElement landmarkElement;

    @FindBy(id = "address-ui-widgets-enterAddressCity")
    WebElement cityNameElement;

    @FindBy(xpath = "//a[contains(@id,'address-ui-widgets-enterAddressStateOrRegion')]")
    List<WebElement> stateNamesList;

    @FindBy(css = "#address-ui-widgets-enterAddressStateOrRegion .a-button-text.a-declarative")
    WebElement stateNameElement;

    @FindBy(css = "#address-ui-widgets-form-submit-button .a-button-input")
    WebElement submitElement;

    @FindBy(css = "#yaab-alert-box .a-alert-heading")
    List<WebElement> addressSuccessElement;

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

    public void updateNewAddress(String countryName, String fullName, String mobileNo, String pincode,
            String BuildingAddress, String area, String landmark, String city, String stateName) {
        waitForWebElementToAppear(addAnAddress);
        addAnAddress.click();
        addAddressPlusIcon.click();
        countryNameElement.click();
        for (int i = 0; i < countryNamesList.size(); i++) {
            if (countryNamesList.get(i).getText().trim().equalsIgnoreCase(countryName)) {
                countryNamesList.get(i).click();
                break;
            }
        }
        fullNameElement.sendKeys(fullName);
        mobileNoElement.sendKeys(mobileNo);
        pincodeElement.clear();
        pincodeElement.sendKeys(pincode);
        buildingNameElement.sendKeys(BuildingAddress);
        areaNameElement.sendKeys(area);
        landmarkElement.sendKeys(landmark);
        cityNameElement.clear();
        cityNameElement.sendKeys(city);
        stateNameElement.click();
        for (int i = 0; i < stateNamesList.size(); i++) {
            if (stateNamesList.get(i).getText().trim().equalsIgnoreCase(stateName)) {
                stateNamesList.get(i).click();
                break;
            }
        }
        submitElement.click();
    }

    public void verifyIfAddressIsUpdated() {
        boolean value = addressSuccessElement.size() == 0;
        if (value == false) {
            System.out.println(addressSuccessElement.get(0).getText());
        } else {
            System.out.println("The address is not correctly updated!!");
        }
    }
}
