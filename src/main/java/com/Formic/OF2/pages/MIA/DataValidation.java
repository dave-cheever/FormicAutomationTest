package com.Formic.OF2.pages.MIA;

import com.Formic.OF2.pages.CheckBoxPageV2;
import com.Formic.OF2.pages.HandwritingRecognitionObject;
import com.Formic.OF2.pages.ManualImageArea;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.ConfigLoader;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import com.Formic.OF2.utils.RoutingRules;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import static com.Formic.OF2.pages.HRO.DataValidation.generateRandomNumber;

public class DataValidation extends BasePage {
    public DataValidation(WebDriver driver) {
        super(driver);
    }

    static String miaValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";

    int projectId = Integer.parseInt(ConfigLoader.getProperty("test.DerivationProjectId"));

    public void Positive_MIA_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,NumberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Positive_MIA_Not_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        int num = Integer.parseInt(NumberToValidate);
        num = num + 1;
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(num));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Positive_MIA_Greater_Than_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        int num = Integer.parseInt(NumberToValidate);
        num = num + 1;
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(num));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Positive_MIA_Greater_Than_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,NumberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Positive_MIA_Less_Than_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        int num = Integer.parseInt(NumberToValidate);
        num = num - 1;
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(num));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Positive_MIA_Less_Than_Or_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,NumberToValidate);

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Negative_MIA_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(generateRandomNumber("==",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Negative_MIA_Not_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(generateRandomNumber("!=",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Negative_MIA_Greater_Than_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(generateRandomNumber(">",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Negative_MIA_Greater_Than_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(generateRandomNumber(">=",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Negative_MIA_Less_Than_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(generateRandomNumber("<",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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

    public void Negative_MIA_Less_Than_Or_Equal_To_Test(String fieldId, String NumberToValidate, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(generateRandomNumber("<=",Integer.parseInt(NumberToValidate))));

        try{
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
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
