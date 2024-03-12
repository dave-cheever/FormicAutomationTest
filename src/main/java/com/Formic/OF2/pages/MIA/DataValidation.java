package com.Formic.OF2.pages.MIA;

import com.Formic.OF2.pages.CheckBoxPage;
import com.Formic.OF2.pages.ManualImageArea;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

public class DataValidation extends BasePage {
    public DataValidation(WebDriver driver) {
        super(driver);
    }

    static String miaValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";

    int projectId = 233;

    public void Positive_MIA_Equal_To_Test() throws Exception {
        String scenarioName = "Positive_MIA_Equal_To_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationEqualByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);

            try{
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
    }

    public void Positive_MIA_Not_Equal_To_Test() throws Exception {
        String scenarioName = "Positive_MIA_Not_Equal_To_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationNotEqualByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);

            try{
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
    }

    public void Positive_MIA_Greater_Than_Test() throws Exception {
        String scenarioName = "Positive_MIA_Greater_Than_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationGreaterThanByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);

            try{
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
    }

    public void Positive_MIA_Greater_Than_Equal_To_Test() throws Exception {
        String scenarioName = "Positive_MIA_Greater_Than_Equal_To_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationGreaterThanOrEqualToByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);

            try{
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
    }

    public void Positive_MIA_Less_Than_Test() throws Exception {
        String scenarioName = "Positive_MIA_Less_Than_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationLessThanByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber-1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);

            try{
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
    }

    public void Positive_MIA_Less_Than_Or_Equal_To_Test() throws Exception {
        String scenarioName = "Positive_MIA_Less_Than_Or_Equal_To_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationLessThanOrEqualToByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);

            try{
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
    }

    public void Negative_MIA_Equal_To_Test() throws Exception {
        String scenarioName = "Negative_MIA_Equal_To_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationEqualByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));

            try{
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

    public void Negative_MIA_Not_Equal_To_Test() throws Exception {
        String scenarioName = "Negative_MIA_Not_Equal_To_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationNotEqualByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));

            try{
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

    public void Negative_MIA_Greater_Than_Test() throws Exception {
        String scenarioName = "Negative_MIA_Greater_Than_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationGreaterThanByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));

            try{
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

    public void Negative_MIA_Greater_Than_Equal_To_Test() throws Exception {
        String scenarioName = "Negative_MIA_Greater_Than_Equal_To_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationGreaterThanOrEqualToByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber-1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));

            try{
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

    public void Negative_MIA_Less_Than_Test() throws Exception {
        String scenarioName = "Negative_MIA_Less_Than_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationLessThanByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));

            try{
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

    public void Negative_MIA_Less_Than_Or_Equal_To_Test() throws Exception {
        String scenarioName = "Negative_MIA_Less_Than_Or_Equal_To_Test";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationLessThanOrEqualToByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));

            try{
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
}
