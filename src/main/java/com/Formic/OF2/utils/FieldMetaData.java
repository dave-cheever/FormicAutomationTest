package com.Formic.OF2.utils;

import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class FieldMetaData extends BasePage {
    public FieldMetaData(WebDriver driver) {
        super(driver);
    }



    public static boolean getHroRules(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&& FieldValidation.isFieldIdHro(pojo,strFieldId)){
                CheckboxObject.mandatory = fields.getMandatory();
                CheckboxObject.checkboxName = fields.getName();
                CheckboxObject.strFormatMask = fields.getFormatMask();
                CheckboxObject.strFormatRegex = fields.getFormatRegex();
                CheckboxObject.strDataTypeNew = fields.getDataTypeNew();
                CheckboxObject.strDerivation = fields.getDerivation();
                CheckboxObject.strValidation = fields.getValidation();
                if(fields.getResponses()!=null){
                    CheckboxObject.isMultiResponse = fields.getResponses().getIsMultiResponse();
                    CheckboxObject.minimum = fields.getResponses().getMinimum();
                    CheckboxObject.maximum = fields.getResponses().getMaximum();
                }
                Reporter.log("<b>Mandatory: </b>"+CheckboxObject.mandatory);
                Reporter.log("<b>Field Name: </b>"+getFieldName(pojo,strFieldId));
                Reporter.log("<b>Format Mask: </b>"+CheckboxObject.strFormatMask);
                Reporter.log("<b>Format Regex: </b>"+ CheckboxObject.strFormatRegex);
                Reporter.log("<b>Data Type New: </b>"+CheckboxObject.strDataTypeNew);
                Reporter.log("<b>Derivation: </b>"+ CheckboxObject.strDerivation);
                Reporter.log("<b>Validation: </b>"+CheckboxObject.strValidation);
                return true;
            }
        }
        return false;
    }

    public static boolean getMiaRules(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&(DataValidation.isFieldIdPickList(pojo,strFieldId)|| FieldValidation.isFieldIdMia(pojo,strFieldId))){
                CheckboxObject.mandatory = fields.getMandatory();
                CheckboxObject.checkboxName = fields.getName();
                CheckboxObject.strFormatMask = fields.getFormatMask();
                CheckboxObject.strFormatRegex = fields.getFormatRegex();
                CheckboxObject.strDataTypeNew = fields.getDataTypeNew();
                CheckboxObject.strDerivation = fields.getDerivation();
                CheckboxObject.strValidation = fields.getValidation();
                if(fields.getResponses()!=null){
                    CheckboxObject.isMultiResponse = fields.getResponses().getIsMultiResponse();
                    CheckboxObject.minimum = fields.getResponses().getMinimum();
                    CheckboxObject.maximum = fields.getResponses().getMaximum();
                }
                Reporter.log("<b>Mandatory: </b>"+CheckboxObject.mandatory);
                Reporter.log("<b>Field Name: </b>"+CheckboxObject.checkboxName);
                Reporter.log("<b>Format Mask: </b>"+CheckboxObject.strFormatMask);
                Reporter.log("<b>Format Regex: </b>"+ CheckboxObject.strFormatRegex);
                Reporter.log("<b>Data Type New: </b>"+CheckboxObject.strDataTypeNew);
                Reporter.log("<b>Minimum: </b>"+CheckboxObject.minimum);
                Reporter.log("<b>Maximum: </b>"+CheckboxObject.maximum);
                Reporter.log("<b>Derivation: </b>"+ CheckboxObject.strDerivation);
                Reporter.log("<b>Validation: </b>"+CheckboxObject.strValidation);
                return true;
            }
        }
        return false;
    }
}
