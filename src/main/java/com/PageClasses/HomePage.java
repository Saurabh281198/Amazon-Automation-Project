package com.PageClasses;

import java.util.Arrays;
import java.util.List;

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

    @FindBy(css = ".s-suggestion-container div")
    List<WebElement> autosuggestElement;

    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    List<WebElement> searchedProduct;

    @FindBy(xpath = "//input[@name='submit.add-to-cart']")
    WebElement addToCart;

    By autoSuggestLocator = By.cssSelector(".s-suggestion-container div");

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

    public void searchProduct(String[] productName, String productType) throws InterruptedException {

        for (int a = 0; a < productName.length; a++) {
            typeDataOnSearchBox(productName[a]);
            waitForElementToAppear(autoSuggestLocator);
            List<String> productNameList = Arrays.asList(productName);
            for (int i = 0; i < autosuggestElement.size(); i++) {
                for (int j = 0; j < productNameList.size(); j++) {
                    if (autosuggestElement.get(i).getAttribute("aria-label").trim()
                            .equalsIgnoreCase(productNameList.get(j))) {
                        autosuggestElement.get(i).click();
                        break;
                    }
                }
            }
            for (int i = 0; i < searchedProduct.size(); i++) {
                if (searchedProduct.get(i)
                        .findElement(By.xpath("//span[@class='a-badge-label-inner a-text-ellipsis']/span")).getText()
                        .contains(productType)) {
                    WebElement targElement = searchedProduct.get(i).findElement(By.xpath("//div[@data-cy='title-recipe']/h2/a"));
                    clickElement(targElement);
                    break;
                }
            }
            switchTab(1);
            waitForWebElementToAppear(addToCart);
            clickElement(addToCart);
        }
    }

}
