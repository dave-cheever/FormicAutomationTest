package com.Formic.OF2.pages.HRO;

import com.Formic.OF2.pages.HandwritingRecognitionObject;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.pages.CheckBoxPage;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;


public class DataValidation extends BasePage {
    public DataValidation(WebDriver driver) {
        super(driver);
    }

    static String hroValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";

    int projectId = 233;

    public void Positive_HRO_Verify_that_the_Equal_To_operator_correctly_identifies_when_the_input_value_is_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationEqualByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_HRO_Verify_that_the_Not_Equal_To_operator_correctly_identifies_when_the_input_value_is_not_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationNotEqualByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_HRO_Verify_that_the_Greater_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationGreaterThanByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_HRO_Verify_that_the_Greater_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationGreaterThanOrEqualToByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_HRO_Verify_that_the_Less_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationLessThanByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber-1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_HRO_Verify_that_the_Less_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationLessThanOrEqualToByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_HRO_Verify_that_the_Equal_To_operator_correctly_identifies_when_the_input_value_is_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationEqualByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_HRO_Verify_that_the_Not_Equal_To_operator_correctly_identifies_when_the_input_value_is_not_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationNotEqualByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));

            try{
                Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
            }catch (AssertionError assertionError){
                ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
                screenshotHelper.takeScreenshot("Negative_HRO_Test");
                // Rethrow the exception to mark the test as failed
                String pathName = screenshotHelper.getScreenshotPath("Negative_HRO_Test");
                Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
                throw assertionError;
            }
        }
    }

    public void Negative_HRO_Verify_that_the_Greater_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationGreaterThanByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_HRO_Verify_that_the_Greater_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationGreaterThanOrEqualToByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber-1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_HRO_Verify_that_the_Less_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationLessThanByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_HRO_Verify_that_the_Less_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataValidation.getFieldIdValidationLessThanOrEqualToByTypeName(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = com.Formic.OF2.utils.DataValidation.getValidationNumber(graphResponse,fieldId);
                HandwritingRecognitionObject.setTextToHro(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(hroValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }


    
}
