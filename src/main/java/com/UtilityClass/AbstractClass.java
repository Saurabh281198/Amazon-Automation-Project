package com.UtilityClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClass {

    WebDriver driver;

    public AbstractClass(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-logo-sprites")
    WebElement amazonIn;

    @FindBy(css = ".a-popover-trigger")
    WebElement deliverToUser;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchElement;

    @FindBy(css = ".s-suggestion-container .s-suggestion")
    List<WebElement> autoSuggestionNames;

    @FindBy(id = "nav-link-accountList")
    WebElement helloUser;

    @FindBy(id = "nav-orders")
    WebElement returnOrders;

    @FindBy(id = "nav-cart")
    WebElement cartIcon;

    public void clickOnAmazonIn() {
        amazonIn.click();
    }

    public void clickOnDeliverToUser() {
        deliverToUser.click();
    }

    public void typeDataOnSearchBox(String data) {
        searchBox.sendKeys(data);
    }

    public void clickOnSearchProduct() {
        searchElement.click();
    }

    public void handlingAutomativeSuggestionSection(String productName) {
        WebElement correctElement = autoSuggestionNames.stream()
                .filter(s -> s.getAttribute("aria-label").contains(productName)).findFirst().orElse(null);
        correctElement.click();
    }

    public void navigateToHelloUser() {
        Actions act = new Actions(driver);
        act.moveToElement(helloUser).build().perform();
    }

    public void clikOnReturnOrder() {
        returnOrders.click();
    }

    public void clickOnCartIcon() {
        cartIcon.click();
    }

    public void waitForElementToAppear(By find) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(find));
    }

    public void switchWindows() {
        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
    }

    public boolean isElementDisplayed(WebElement element) {
        boolean abc = element.isDisplayed();
        return abc;
    }

    public void clickElement(WebElement ele) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
    }

    public void switchTab(int n) {
        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(n));
    }

    public void waitForWebElementToAppear(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

}
