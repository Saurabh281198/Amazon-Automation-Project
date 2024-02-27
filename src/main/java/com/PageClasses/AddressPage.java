package com.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.UtilityClass.AbstractClass;

public class AddressPage extends AbstractClass {

    WebDriver driver;

    public AddressPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        
    }

}
