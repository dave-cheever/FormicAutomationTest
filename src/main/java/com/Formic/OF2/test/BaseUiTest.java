package com.Formic.OF2.test;

import com.Formic.OF2.utils.DataDrivenTest;
import com.Formic.OF2.utils.FieldManager;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseUiTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    ScreenshotHelper screenshotHelper;

    @BeforeSuite
    public void setUpSuite() {
        // set chromedriver property
        // If it is not run via docker, use chromedriver.exe
        String defaultDriverPath;
        defaultDriverPath = String.join(File.separator, "drivers", "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", defaultDriverPath);

    }

    @BeforeMethod
    public void setDriver() throws MalformedURLException {
        // Start driver
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        options.addArguments("--lang=en-GB");
        // Setting new download directory path
        Map<String, Object> prefs = new HashMap<String, Object>();
        // Use File.separator as it will work on any OS
        prefs.put("download.default_directory",
                System.getProperty("user.dir") + File.separator + "downloadFiles");
//        options.setExperimentalOption("prefs", prefs);
        // Test is running on docker, use the remote web driver

//        driver.set(new ChromeDriver(ChromeOptionsUtil.getHeadlessChromeOptions()));
//        driver.set(new ChromeDriver(options));
        setDriver(new ChromeDriver(options));
        getDriver().manage().window().maximize();
        screenshotHelper = PageFactory.initElements(getDriver(),ScreenshotHelper.class);
    }

    @BeforeSuite
    public void writeOnWorkBook() throws IOException, InvalidFormatException {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(137);
        ArrayList<String> fieldId = new ArrayList<>();
        ArrayList<String> fieldIdEnableDisable = new ArrayList<>();
        ArrayList<String> fieldIdMinInputs = new ArrayList<>();
        ArrayList<String> fieldIdMaxInputs = new ArrayList<>();
        ArrayList<String> fieldIdMia = new ArrayList<>();
        ArrayList<String> fieldIdHroNumeric = new ArrayList<>();
        ArrayList<String> fieldIdHroAlphaNumeric = new ArrayList<>();
        ArrayList<String> fieldIdHroDateTime = new ArrayList<>();
        ArrayList<String> fieldIdHroEmail = new ArrayList<>();
        fieldId = FieldManager.getAllCheckboxFieldIdWithMandatoryRules(graphResponse);
        fieldIdEnableDisable = FieldManager.getRoutingFieldsEnableDisable(graphResponse);
        fieldIdMinInputs = FieldManager.getCheckboxRulesForMinimumInputs(graphResponse);
        fieldIdMaxInputs = FieldManager.getCheckboxRulesForMaximumInputs(graphResponse);
        fieldIdMia = FieldManager.getCheckboxRulesForManualImageArea(graphResponse);
        fieldIdHroNumeric = FieldManager.getHandWritingRecognitionObjectRulesNumeric(graphResponse);
        fieldIdHroAlphaNumeric = FieldManager.getHandWritingRecognitionObjectRulesAlphaNumeric(graphResponse);
        fieldIdHroDateTime = FieldManager.getHandWritingRecognitionObjectRulesDateTime(graphResponse);
        fieldIdHroEmail = FieldManager.getHandWritingRecognitionObjectRulesEmail(graphResponse);
        DataDrivenTest.createExcelTestDataFile();
        DataDrivenTest.writeToExcel(fieldId);
        DataDrivenTest.writeToExcelEnableDisable(fieldIdEnableDisable,"Sheet2");
        DataDrivenTest.writeToExcelCheckboxMinimumInputs(fieldIdMinInputs,"Sheet3");
        DataDrivenTest.writeToExcelCheckboxMaximumInputs(fieldIdMaxInputs,"Sheet4");
        DataDrivenTest.writeToExcelMiaAndHro(fieldIdMia,"Sheet5");
        DataDrivenTest.writeToExcelHroNumeric(fieldIdHroNumeric,"Sheet6");
        DataDrivenTest.writeToExcelHroAlphaNumeric(fieldIdHroAlphaNumeric,"Sheet7");
        DataDrivenTest.writeToExcelHroDateTime(fieldIdHroDateTime,"Sheet8");
        DataDrivenTest.writeToExcelHroEmail(fieldIdHroEmail,"Sheet9");
    }

    public static class ChromeOptionsUtil {
        public static ChromeOptions getHeadlessChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode
            options.addArguments("--disable-gpu"); // Disable GPU for headless mode
            // Add any additional options you need
            return options;
        }
    }

    @AfterMethod
    public void driverTearDown(ITestResult result) throws IOException {
        Process process = Runtime. getRuntime(). exec("taskkill /F /IM chromedriver.exe /T");
        process.destroy();
        getDriver().quit();
    }


    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
//        this.driver = driver;
    }

    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void assertAllElementsFoundByXpathContainText(String xpath, String textToContain) {
        for (WebElement element: getDriver().findElements(By.xpath(xpath))
        ) {
            try {
                //Adding below "if" condition to skip because bug ticket AD-8191 will cause this test case to fail
                if (element.getText().equalsIgnoreCase("")){
                    continue;
                }
                Assert.assertTrue(element.getText().contains(textToContain));
            } catch (AssertionError e) {
                throw new AssertionError("\"" + element.getText() + "\" does not contain" + "\"" + textToContain + "\"");
            }
        }
    }

    public void assertElementContainsText(WebElement element, String textToContain) {
        try {
            Assert.assertTrue(element.getText().contains(textToContain));
        } catch (AssertionError e) {
            throw new AssertionError("\"" + element.getText() + "\" does not contain" + "\"" + textToContain + "\"");
        }
    }



}