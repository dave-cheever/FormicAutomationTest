package com.Formic.OF2.pages.MIA;



import com.Formic.OF2.pages.CheckBoxPageV2;
import com.Formic.OF2.pages.ManualImageArea;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.ConfigLoader;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import com.Formic.OF2.utils.RoutingRules;
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

    static String miaElementLocator = "//div[@data-object-id='$TEXT']/textarea";

    int projectId = Integer.parseInt(ConfigLoader.getProperty("test.DerivationProjectId"));

    public void Calculate_Sum_of_Two_Fields_and_Display_Result_MIA(String fieldId,String scenarioName) throws Exception {
        int firstValue = 5;
        int secondValue = 5;
        String operation = "+";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);

        enterNumberForDerivationFields(graphResponse,fieldId,firstValue,secondValue);
        expectedDerivationOutput(graphResponse,fieldId,firstValue,secondValue,operation,scenarioName);
    }

    public void Multiply_Two_Fields_and_Display_Result_MIA(String fieldId,String scenarioName) throws Exception {
        int firstValue = 5;
        int secondValue = 5;
        String operation = "*";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);

        enterNumberForDerivationFields(graphResponse,fieldId,firstValue,secondValue);
        expectedDerivationOutput(graphResponse,fieldId,firstValue,secondValue,operation,scenarioName);
    }

    public void Divide_Two_Fields_and_Display_Result_MIA(String fieldId,String scenarioName) throws Exception {
        int firstValue = 5;
        int secondValue = 5;
        String operation = "/";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);

        enterNumberForDerivationFields(graphResponse,fieldId,firstValue,secondValue);
        expectedDerivationOutput(graphResponse,fieldId,firstValue,secondValue,operation,scenarioName);
    }

    public void Subtract_Two_Fields_and_Display_Result_MIA(String fieldId,String scenarioName) throws Exception {
        int firstValue = 5;
        int secondValue = 5;
        String operation = "-";
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);

        enterNumberForDerivationFields(graphResponse,fieldId,firstValue,secondValue);
        expectedDerivationOutput(graphResponse,fieldId,firstValue,secondValue,operation,scenarioName);
    }

    public void Value_Propagation_Between_Fields_MIA(String fieldId,String objectId,String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);

        String fieldId1 = getFieldIdByObjectId(graphResponse,objectId);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId1);
        lookForTheField(graphResponse,fieldId1);

        enterNumberForDerivationFieldNoArithmetic(graphResponse,objectId,5);

        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);

        expectedPropagationOutput(graphResponse,fieldId,"5",scenarioName);
    }

    public void expectedDerivationOutput(FormContentPojo pojo, String fieldId,int firstFieldValue, int secondFieldValue, String operatorUsed,String scenarioName){
        int result = 0;
        String objectId = getObjectIdFromFieldId(pojo,fieldId);
        String elem = stringReplace(miaElementLocator,objectId);
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
        System.out.println("Validating the Derivation output.");

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

    public void expectedPropagationOutput(FormContentPojo pojo, String fieldId, String expectedValue,String scenarioName){
        String objectId = getObjectIdFromFieldId(pojo,fieldId);
        String elem = stringReplace(miaElementLocator,objectId);
        WebElement element = stringToWebElement(elem);

        try{
            Assert.assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElementValue(element,expectedValue)));
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
        lookForTheField(pojo,fieldId1);
        ManualImageArea.setTextToMia(pojo,fieldId1,Integer.toString(field1Value));
        String SecondFieldName = com.Formic.OF2.utils.DataDerivation.getDerivationFieldNameByNumber(pojo,fieldId,2);
        String elementId2 = getElementIdByFieldName(pojo,SecondFieldName);
        String fieldId2 = getFieldIdByObjectId(pojo,elementId2);
        lookForTheField(pojo,fieldId2);
        ManualImageArea.setTextToMia(pojo,fieldId2,Integer.toString(field2Value));
    }

    public void enterNumberForDerivationFieldNoArithmetic(FormContentPojo pojo,String objectId,int field1Value){
        String fieldId = getFieldIdByObjectId(pojo,objectId);
        lookForTheField(pojo,fieldId);
        ManualImageArea.setTextToMia(pojo,fieldId,Integer.toString(field1Value));
    }

}

