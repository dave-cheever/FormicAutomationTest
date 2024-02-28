package com.Formic.OF2.pages.MIA;



import com.Formic.OF2.pages.CheckBoxPage;
import com.Formic.OF2.pages.HandwritingRecognitionObject;
import com.Formic.OF2.pages.ManualImageArea;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class DataDerivation extends BasePage {
    public DataDerivation(WebDriver driver) {
        super(driver);
    }

    static String hroValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";

    static String miaElementLocator = "//div[@data-object-id='$TEXT']/textarea";

    int projectId = 233;

    public void Calculate_Sum_of_Two_Fields_and_Display_Result_MIA() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndOperator(graphResponse,"ManualImageAreaText","+");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFields(graphResponse,fieldId,5,5);
            }
            expectedDerivationOutput(graphResponse,fieldId,"10");
        }
    }

    public void Multiply_Two_Fields_and_Display_Result_MIA() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndOperator(graphResponse,"ManualImageAreaText","*");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFields(graphResponse,fieldId,5,5);
            }
            expectedDerivationOutput(graphResponse,fieldId,"25");
        }
    }

    public void Divide_Two_Fields_and_Display_Result_MIA() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndOperator(graphResponse,"ManualImageAreaText","/");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFields(graphResponse,fieldId,5,5);
            }
            expectedDerivationOutput(graphResponse,fieldId,"1");
        }
    }

    public void Subtract_Two_Fields_and_Display_Result_MIA() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndOperator(graphResponse,"ManualImageAreaText","-");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFields(graphResponse,fieldId,5,5);
            }
            expectedDerivationOutput(graphResponse,fieldId,"0");
        }
    }

    public void Value_Propagation_Between_Fields_MIA() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        com.Formic.OF2.utils.DataDerivation.getFieldIdDerivationByTypeNameAndNoOperator(graphResponse,"ManualImageAreaText");
        for (String fieldId: CheckboxObject.fieldId
        ) {
            if(com.Formic.OF2.utils.FieldMetaData.getMiaRules(graphResponse, fieldId)&&
                    !CheckBoxPage.isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                enterNumberForDerivationFieldNoArithmetic(graphResponse,fieldId,5);
            }
            expectedDerivationOutput(graphResponse,fieldId,"5");
        }
    }

    public void expectedDerivationOutput(FormContentPojo pojo, String fieldId, String expectedValue){
        String objectId = getObjectIdFromFieldId(pojo,fieldId);
        String elem = stringReplace(miaElementLocator,objectId);
        WebElement element = stringToWebElement(elem);
        Assert.assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElementValue(element,expectedValue)));
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

    public void enterNumberForDerivationFieldNoArithmetic(FormContentPojo pojo,String mainFieldId,int field1Value){
        String fieldName = CheckboxObject.fieldName;
        String elementId = getElementIdByFieldName(pojo,fieldName);
        String fieldId = getFieldIdByObjectId(pojo,elementId);
        lookForTheField(pojo,fieldId);
        ManualImageArea.setTextToMia(pojo,fieldId,Integer.toString(field1Value));
        lookForTheField(pojo,mainFieldId);
    }

}

