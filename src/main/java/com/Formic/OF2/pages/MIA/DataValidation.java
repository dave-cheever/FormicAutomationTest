package com.Formic.OF2.pages.MIA;

import com.Formic.OF2.pages.CheckBoxPage;
import com.Formic.OF2.pages.HandwritingRecognitionObject;
import com.Formic.OF2.pages.ManualImageArea;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.Config;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class DataValidation extends BasePage {
    public DataValidation(WebDriver driver) {
        super(driver);
    }

    static String miaValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";

    int projectId = 233;

    public void Positive_MIA_Verify_that_the_Equal_To_operator_correctly_identifies_when_the_input_value_is_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationEqualByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_MIA_Verify_that_the_Not_Equal_To_operator_correctly_identifies_when_the_input_value_is_not_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationNotEqualByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_MIA_Verify_that_the_Greater_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationGreaterThanByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_MIA_Verify_that_the_Greater_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationGreaterThanOrEqualToByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_MIA_Verify_that_the_Less_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationLessThanByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber-1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Positive_MIA_Verify_that_the_Less_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationLessThanOrEqualToByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            Assert.assertTrue(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_MIA_Verify_that_the_Equal_To_operator_correctly_identifies_when_the_input_value_is_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationEqualByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_MIA_Verify_that_the_Not_Equal_To_operator_correctly_identifies_when_the_input_value_is_not_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationNotEqualByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_MIA_Verify_that_the_Greater_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationGreaterThanByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_MIA_Verify_that_the_Greater_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationGreaterThanOrEqualToByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber-1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_MIA_Verify_that_the_Less_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationLessThanByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }

    public void Negative_MIA_Verify_that_the_Less_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Config.getFieldIdValidationLessThanOrEqualToByTypeName(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(Config.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                int validationNumber = Config.getValidationNumber(graphResponse,fieldId);
                ManualImageArea.setTextToMia(graphResponse,fieldId,Integer.toString(validationNumber+1));
            }
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String elem = stringReplace(miaValidationMessageLocator,objectId);
            WebElement element = stringToWebElement(elem);
            driverWait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertFalse(driver.findElements(By.xpath(elem)).isEmpty());
        }
    }
}
