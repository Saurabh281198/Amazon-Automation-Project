package com.amazon.BaseTestClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.PageClasses.HomePage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

    public WebDriver driver;
    public Properties prop;

    public WebDriver getDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\com\\GlobalData\\dataFile.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                : prop.getProperty("browser");

        EdgeOptions options = new EdgeOptions();

        if (browserName.contains("Edge")) {
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));
        } else {
            System.out.println("Enter edge browser");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;

    }

    @BeforeMethod
    public void launchBrowser() throws IOException {
        driver = getDriver();
    }

    public HomePage goToHomePageClass() {
        driver.get(prop.getProperty("URL"));
        HomePage homePage = new HomePage(driver);
        return homePage;
    }

    //@AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    public List<HashMap<String, String>> jsonTextToHashmap(String path) throws IOException {

        String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {

                });
        return data;
    }

    public void takeScreenshot(String name, WebDriver driver) throws IOException {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "\\Reports\\" + name + ".png");
        FileUtils.copyFile(src, dest);

    }

}
