package com.Formic.OF2.pages.HRO;

import com.Formic.OF2.pages.CheckBoxPageV2;
import com.Formic.OF2.pages.HandwritingRecognitionObject;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

public class DataDerivation extends BasePage {
    public DataDerivation(WebDriver driver) {
        super(driver);
    }

    static String hroValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";

    static String hroElementLocator = "//div[@data-object-id='$TEXT']/input";

    int projectId = 233;

    public void Calculate_Sum_of_Two_Fields_and_Display_Result_HRO(String scenarioName) throws Exception {
        int firstValue = 5;
        int secondValue = 5;
        String operation = "+";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndOperator(graphResponse,"HandwritingRecognitionObject",operation);
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPageV2.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFields(graphResponse,fieldId,firstValue,secondValue);
            }
            expectedDerivationOutput(graphResponse,fieldId,firstValue,secondValue,operation,scenarioName);
        }
    }

    public void Multiply_Two_Fields_and_Display_Result_HRO() throws Exception {
        String scenarioName = "Multiply_Two_Fields_and_Display_Result_HRO";
        int firstValue = 5;
        int secondValue = 5;
        String operation = "*";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndOperator(graphResponse,"HandwritingRecognitionObject",operation);
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPageV2.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFields(graphResponse,fieldId,firstValue,secondValue);
            }
            expectedDerivationOutput(graphResponse,fieldId,firstValue,secondValue,operation,scenarioName);
        }
    }

    public void Divide_Two_Fields_and_Display_Result_HRO() throws Exception {
        String scenarioName = "Divide_Two_Fields_and_Display_Result_HRO";
        int firstValue = 5;
        int secondValue = 5;
        String operation = "/";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndOperator(graphResponse,"HandwritingRecognitionObject",operation);
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPageV2.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFields(graphResponse,fieldId,firstValue,secondValue);
            }
            expectedDerivationOutput(graphResponse,fieldId,firstValue,secondValue,operation,scenarioName);
        }
    }

    public void Subtract_Two_Fields_and_Display_Result_HRO() throws Exception {
        String scenarioName = "Subtract_Two_Fields_and_Display_Result_HRO";
        int firstValue = 5;
        int secondValue = 5;
        String operation = "-";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndOperator(graphResponse,"HandwritingRecognitionObject",operation);
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPageV2.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFields(graphResponse,fieldId,firstValue,secondValue);
            }
            expectedDerivationOutput(graphResponse,fieldId,firstValue,secondValue,operation,scenarioName);
        }
    }

    public void Value_Propagation_Between_Fields_HRO() throws Exception {
        String scenarioName = "Value_Propagation_Between_Fields_HRO";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndNoOperator(graphResponse,"HandwritingRecognitionObject");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getHroRules(graphResponse, fieldId)&&
                    !CheckBoxPageV2.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFieldNoArithmetic(graphResponse,fieldId,5);
            }
            expectedPropagationOutput(graphResponse,fieldId,"5",scenarioName);
        }
    }


    public void expectedDerivationOutput(FormContentPojo pojo, String fieldId,int firstFieldValue, int secondFieldValue, String operatorUsed, String scenarioName){
        int result = 0;
        String objectId = getObjectIdFromFieldId(pojo,fieldId);
        String elem = stringReplace(hroElementLocator,objectId);
        WebElement element = stringToWebElement(elem);
        switch (operatorUsed) {
            case "+":
                result = firstFieldValue + secondFieldValue;
                break;
            case "-":
                result = firstFieldValue - secondFieldValue;
                break;
            case "*":
                result = firstFieldValue * secondFieldValue;
                break;
            case "/":
                if (secondFieldValue != 0) {
                    result = firstFieldValue / secondFieldValue;
                } else {
                    System.out.println("Error: Division by zero");
                }
                break;
            default:
                System.out.println("Error: Unsupported operator");
        }
        Reporter.log("<b>Validating the Derivation output.<b/>");
        try{
            Assert.assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElementValue(element,Integer.toString(result))));
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void expectedPropagationOutput(FormContentPojo pojo, String fieldId, String expectedOutput,String scenarioName){
        String objectId = getObjectIdFromFieldId(pojo,fieldId);
        String elem = stringReplace(hroElementLocator,objectId);
        WebElement element = stringToWebElement(elem);

        try{
            Assert.assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElementValue(element,expectedOutput)));
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void enterNumberForDerivationFields(FormContentPojo pojo,String fieldId,int field1Value,int field2Value){
        String firstFieldName = com.Formic.OF2.utils.DataDerivation.getDerivationFieldNameByNumber(pojo,fieldId,0);
        String elementId1 = getElementIdByFieldName(pojo,firstFieldName);
        String fieldId1 = getFieldIdByObjectId(pojo,elementId1);
        Reporter.log("<b>Looking for the first field to enter field value: <b/>" + field1Value);
        lookForTheField(pojo,fieldId1);
        Reporter.log("<b>Field: <b/>"+ firstFieldName + "<b> found!<b/>");
        Reporter.log("<b>Entering text: "+field1Value+"<b> to Field: <b/>"+firstFieldName);
        HandwritingRecognitionObject.setTextToHro(pojo,fieldId1,Integer.toString(field1Value));
        Reporter.log("<b>Successfully entered the text: <b/>"+ field1Value+"<b> to the Field: <b/>"+firstFieldName);
        String SecondFieldName = com.Formic.OF2.utils.DataDerivation.getDerivationFieldNameByNumber(pojo,fieldId,2);
        String elementId2 = getElementIdByFieldName(pojo,SecondFieldName);
        String fieldId2 = getFieldIdByObjectId(pojo,elementId2);
        Reporter.log("<b>Looking for the second field to enter field value: <b/>"+ field2Value);
        lookForTheField(pojo,fieldId2);
        Reporter.log("<b>Field: <b/>"+ SecondFieldName + "<b> found!<b/>");
        Reporter.log("<b>Entering text: <b/>"+field2Value+"<b> to Field: <b/>"+SecondFieldName);
        HandwritingRecognitionObject.setTextToHro(pojo,fieldId2,Integer.toString(field2Value));
        Reporter.log("<b>Successfully entered the text: <b/>"+ field2Value+"<b> to the Field: <b/>"+SecondFieldName);
    }

    public void enterNumberForDerivationFieldNoArithmetic(FormContentPojo pojo,String mainFieldId,int field1Value){
        String fieldName = CheckboxObject.fieldName;
        String elementId = getElementIdByFieldName(pojo,fieldName);
        String fieldId = getFieldIdByObjectId(pojo,elementId);
        lookForTheField(pojo,fieldId);
        HandwritingRecognitionObject.setTextToHro(pojo,fieldId,Integer.toString(field1Value));
        lookForTheField(pojo,mainFieldId);
    }

}
