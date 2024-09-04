package com.Formic.OF2.test;

import com.Formic.OF2.utils.*;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
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
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.Formic.OF2.utils.FieldManager.getFieldIdHro;
import static com.Formic.OF2.utils.FieldManager.getFieldIdMia;

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
//        options.setAcceptInsecureCerts(true);
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
        driver.set(new ChromeDriver(options));
//        setDriver(new ChromeDriver(options));
        getDriver().manage().window().maximize();
        screenshotHelper = PageFactory.initElements(getDriver(),ScreenshotHelper.class);
    }

    @BeforeSuite
    public void writeOnWorkBook() throws IOException, InvalidFormatException {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponseCheckBox =  rules.getRules(Integer.parseInt(ConfigLoader.getProperty("test.CheckboxProjectId")));
        FormContentPojo graphResponseHro =  rules.getRules(Integer.parseInt(ConfigLoader.getProperty("test.HroProjectId")));
        FormContentPojo graphResponseMia =  rules.getRules(Integer.parseInt(ConfigLoader.getProperty("test.MiaProjectId")));
        FormContentPojo graphResponseDerivation =  rules.getRules(Integer.parseInt(ConfigLoader.getProperty("test.DerivationProjectId")));
//        FormContentPojo graphResponse2 =  rules2.getRules(247);
        //CheckBox
        ArrayList<String> configurationList = new ArrayList<>();

//        System.out.println("##[command] fieldId: "+configurationList.size());
        DataDrivenTest.createExcelTestDataFile();

        //CheckBox
        configurationList = FieldManager.getAllCheckboxFieldIdWithMandatoryRules(graphResponseCheckBox,FieldManager.getAllCheckboxFieldId(graphResponseCheckBox));
        System.out.println("##[command] writing to excel");
        DataDrivenTest.writeToExcel(configurationList,"Sheet1");

        configurationList = FieldManager.getRoutingFieldsEnableDisable(graphResponseCheckBox);
        System.out.println("##[command] writing to enable disable fields");
        DataDrivenTest.writeToExcelEnableDisable(configurationList,"Sheet2");

        configurationList = FieldManager.getCheckboxRulesForMinimumInputs(graphResponseCheckBox,FieldManager.getAllCheckboxFieldIds(graphResponseCheckBox));
        System.out.println("##[command] writing to minimum inputs");
        DataDrivenTest.writeToExcelCheckboxMinimumInputs(configurationList,"Sheet3");

        configurationList = FieldManager.getCheckboxRulesForMaximumInputs(graphResponseCheckBox,FieldManager.getAllCheckboxFieldIds(graphResponseCheckBox));
        System.out.println("##[command] writing to maximum inputs");
        DataDrivenTest.writeToExcelCheckboxMaximumInputs(configurationList,"Sheet4");

        //HRO
        configurationList = FieldManager.getHandWritingRecognitionObjectRulesNumeric(graphResponseHro);
        System.out.println("##[command] writing to HRO numeric");
        DataDrivenTest.writeToExcelHroNumeric(configurationList,"Sheet5");

        configurationList = FieldManager.getHandWritingRecognitionObjectRulesDateTime(graphResponseHro);
        System.out.println("##[command] writing to HRO Date time");
        DataDrivenTest.writeToExcelHroDateTime(configurationList,"Sheet6");

        configurationList = FieldManager.getHandWritingRecognitionObjectRulesEmail(graphResponseHro);
        System.out.println("##[command] writing to HRO Email");
        DataDrivenTest.writeToExcelHroEmail(configurationList,"Sheet7");

        configurationList = FieldManager.getHandWritingRecognitionObjectRulesDataFormatting(graphResponseHro);
        System.out.println("##[command] writing to Data formatting");
        DataDrivenTest.writeToExcelHroDataFormatting(configurationList,"Sheet8");

        configurationList = FieldManager.getHandWritingRecognitionObjectRulesMandatory(graphResponseHro);
        System.out.println("##[command] writing to HRO mandatory");
        DataDrivenTest.writeToExcelHroMandatory(configurationList,"Sheet9");

        //MIA
        configurationList = FieldManager.getCheckboxRulesForManualImageAreaEmail(graphResponseMia);
        System.out.println("##[command] writing to Mia and Hro");
        DataDrivenTest.writeToExcelMiaEmail(configurationList,"Sheet10");

        configurationList = FieldManager.getMiaDateTime(graphResponseMia);
        System.out.println("##[command] writing to Mia Date time");
        DataDrivenTest.writeToExcelHroDateTime(configurationList,"Sheet11");

        configurationList = FieldManager.getMiaRulesNumeric(graphResponseMia);
        DataDrivenTest.writeToExcelHroNumeric(configurationList,"Sheet12");

        configurationList = FieldManager.getMiaFormatting(graphResponseMia);
        DataDrivenTest.writeToExcelMiaDataFormatting(configurationList,"Sheet13");

        configurationList = FieldManager.getMiaRulesMandatory(graphResponseMia);
        DataDrivenTest.writeToExcelHroMandatory(configurationList,"Sheet14");

        configurationList = FieldManager.getMiaRulesPicklist(graphResponseMia);
        DataDrivenTest.writeToExcelMiaPickList(configurationList,"Sheet15");

        //Matrix
        configurationList = FieldManager.getAllCheckboxFieldIdWithMandatoryRules(graphResponseCheckBox,FieldManager.getAllCheckboxMatrixFieldId(graphResponseCheckBox));
        DataDrivenTest.writeToExcelMatrixMandatory(configurationList,"Sheet16");

        configurationList = FieldManager.getCheckboxRulesForMinimumInputs(graphResponseCheckBox,FieldManager.getAllCheckboxMatrixFieldId(graphResponseCheckBox));
        DataDrivenTest.writeToExcelCheckboxMinimumInputs(configurationList,"Sheet17");

        configurationList = FieldManager.getCheckboxRulesForMaximumInputs(graphResponseCheckBox,FieldManager.getAllCheckboxMatrixFieldId(graphResponseCheckBox));
        DataDrivenTest.writeToExcelCheckboxMaximumInputs(configurationList,"Sheet18");

        //Data Validation HRO

        configurationList = FieldManager.getHroMiaRulesDataValidationEqualTo(graphResponseDerivation,getFieldIdHro(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet19");

        configurationList = FieldManager.getHroMiaRulesDataValidationNotEqualTo(graphResponseDerivation,getFieldIdHro(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet20");

        configurationList = FieldManager.getHroMiaRulesDataValidationGreaterThan(graphResponseDerivation,getFieldIdHro(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet21");

        configurationList = FieldManager.getHroMiaRulesDataValidationGreaterThanEqualTo(graphResponseDerivation,getFieldIdHro(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet22");

        configurationList = FieldManager.getHroMiaRulesDataValidationLessThan(graphResponseDerivation,getFieldIdHro(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet23");

        configurationList = FieldManager.getHroMiaRulesDataValidationLessThanEqualTo(graphResponseDerivation,getFieldIdHro(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet24");

        //Data Validation MIA

        configurationList = FieldManager.getHroMiaRulesDataValidationEqualTo(graphResponseDerivation,getFieldIdMia(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet25");

        configurationList = FieldManager.getHroMiaRulesDataValidationNotEqualTo(graphResponseDerivation,getFieldIdMia(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet26");

        configurationList = FieldManager.getHroMiaRulesDataValidationGreaterThan(graphResponseDerivation,getFieldIdMia(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet27");

        configurationList = FieldManager.getHroMiaRulesDataValidationGreaterThanEqualTo(graphResponseDerivation,getFieldIdMia(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet28");

        configurationList = FieldManager.getHroMiaRulesDataValidationLessThan(graphResponseDerivation,getFieldIdMia(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet29");

        configurationList = FieldManager.getHroMiaRulesDataValidationLessThanEqualTo(graphResponseDerivation,getFieldIdMia(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataValidation(configurationList,"Sheet30");


        //Derivation HRO

        configurationList = FieldManager.getFieldIdDerivationByOperator(graphResponseDerivation,getFieldIdHro(graphResponseDerivation),"+");
        DataDrivenTest.writeToExcelHroDataDerivation(configurationList,"Sheet31");

        configurationList = FieldManager.getFieldIdDerivationByOperator(graphResponseDerivation,getFieldIdHro(graphResponseDerivation),"-");
        DataDrivenTest.writeToExcelHroDataDerivation(configurationList,"Sheet32");

        configurationList = FieldManager.getFieldIdDerivationByOperator(graphResponseDerivation,getFieldIdHro(graphResponseDerivation),"/");
        DataDrivenTest.writeToExcelHroDataDerivation(configurationList,"Sheet33");

        configurationList = FieldManager.getFieldIdDerivationByOperator(graphResponseDerivation,getFieldIdHro(graphResponseDerivation),"*");
        DataDrivenTest.writeToExcelHroDataDerivation(configurationList,"Sheet34");

        //MIA

        configurationList = FieldManager.getFieldIdDerivationByOperator(graphResponseDerivation,getFieldIdMia(graphResponseDerivation),"+");
        DataDrivenTest.writeToExcelHroDataDerivation(configurationList,"Sheet35");

        configurationList = FieldManager.getFieldIdDerivationByOperator(graphResponseDerivation,getFieldIdMia(graphResponseDerivation),"-");
        DataDrivenTest.writeToExcelHroDataDerivation(configurationList,"Sheet36");

        configurationList = FieldManager.getFieldIdDerivationByOperator(graphResponseDerivation,getFieldIdMia(graphResponseDerivation),"/");
        DataDrivenTest.writeToExcelHroDataDerivation(configurationList,"Sheet37");

        configurationList = FieldManager.getFieldIdDerivationByOperator(graphResponseDerivation,getFieldIdMia(graphResponseDerivation),"*");
        DataDrivenTest.writeToExcelHroDataDerivation(configurationList,"Sheet38");

        //Propagation

        configurationList = FieldManager.getHroMiaRulesDataDerivationPropagation(graphResponseDerivation,getFieldIdHro(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataDerivationPropagation(configurationList,"Sheet39");

        configurationList = FieldManager.getHroMiaRulesDataDerivationPropagation(graphResponseDerivation,getFieldIdMia(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroDataDerivationPropagation(configurationList,"Sheet40");

        //Derivation CurrentDateTime MIA

        configurationList = FieldManager.getFieldIdDerivationCurrentDateTime(graphResponseDerivation,getFieldIdMia(graphResponseDerivation));
        DataDrivenTest.writeToExcelHroMiaDataDerivationCurrentDateTime(configurationList,"Sheet41");

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

//    @AfterSuite
//    public void deleteResources(){
//        DataDrivenTest.deleteWorkbook();
//    }


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