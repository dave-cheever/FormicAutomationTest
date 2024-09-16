package com.Formic.OF2.pages.HRO;

import com.Formic.OF2.pages.CheckBoxPageV2;
import com.Formic.OF2.pages.HandwritingRecognitionObject;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.ConfigLoader;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import com.Formic.OF2.utils.RoutingRules;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.apache.tools.ant.filters.ConcatFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Random;


public class DataValidation extends BasePage {
    public DataValidation(WebDriver driver) {
        super(driver);
    }

    static String hroValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";

    int projectId = Integer.parseInt(ConfigLoader.getProperty("test.DerivationProjectId"));

    public void Positive_HRO_Equal_To_Test(String fieldId, String numberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,numberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Positive_HRO_Not_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,NumberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Positive_HRO_Greater_Than_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,NumberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Positive_HRO_Greater_Than_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,NumberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Positive_HRO_Less_Than_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,NumberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Positive_HRO_Less_Than_Or_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,NumberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public static int generateRandomNumber(String comparison, int value) {
        Random random = new Random();
        int generatedNumber;

        switch (comparison) {
            case ">":
                // Generate a number less than or equal to the given value
                generatedNumber = random.nextInt(value + 1);
                break;
            case "<":
                // Generate a number greater than or equal to the given value
                generatedNumber = value + random.nextInt(Integer.MAX_VALUE - value);
                break;
            case ">=":
                // Generate a number less than the given value
                generatedNumber = random.nextInt(value);
                break;
            case "<=":
                // Generate a number greater than the given value
                generatedNumber = value + 1 + random.nextInt(Integer.MAX_VALUE - value - 1);
                break;
            case "==":
                // Generate a number not equal to the given value
                do {
                    generatedNumber = random.nextInt(Integer.MAX_VALUE);
                } while (generatedNumber == value);
                break;
            case "!=":
                // Generate the given value
                generatedNumber = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid comparison operator");
        }
        return generatedNumber;
    }

    public void Negative_HRO_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(generateRandomNumber("==",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Negative_HRO_Not_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(generateRandomNumber("!=",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Negative_HRO_Greater_Than_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(generateRandomNumber(">",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Negative_HRO_Greater_Than_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(generateRandomNumber(">=",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void Negative_HRO_Less_Than_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(generateRandomNumber("<",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }


    }

    public void Negative_HRO_Less_Than_Or_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(generateRandomNumber("<=",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }
}
