package com.Formic.OF2.utils;

import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Config extends BasePage {
    public Config(WebDriver driver) {
        super(driver);
    }

    public static boolean isFieldIdHro(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: page.getObjects()
            ) {
                if(object.getTypename()!=null&&object.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")){
                    if(object.getFieldId().equalsIgnoreCase(strFieldId)){
                        result=true;
                        break outerLoop;
                    }
                }
            }
        }
        return result;
    }

    public static void getFieldIdByTypeName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        CheckboxObject.fieldId.add(object.getFieldId());
                    }
                }
            }
        }
    }

    public static void getFieldIdValidationEqualByTypeName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        if(isValidationEqualsTo(pojo,object.getFieldId())){
                            CheckboxObject.fieldId.add(object.getFieldId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean isValidationEqualsTo(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String validation = fields.getValidation();
                String[] EqualsTo = validation.split(" ");
                if(EqualsTo[1].equalsIgnoreCase("=")){
                    return true;
                }
            }
        }
        return false;
    }

    public static void getFieldIdValidationNotEqualByTypeName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        if(isValidationNotEqualsTo(pojo,object.getFieldId())){
                            CheckboxObject.fieldId.add(object.getFieldId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean isValidationNotEqualsTo(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String validation = fields.getValidation();
                String[] EqualsTo = validation.split(" ");
                if(EqualsTo[1].equalsIgnoreCase("<>")){
                    return true;
                }
            }
        }
        return false;
    }

    public static void getFieldIdValidationGreaterThanByTypeName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        if(isValidationGreaterThan(pojo,object.getFieldId())){
                            CheckboxObject.fieldId.add(object.getFieldId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean isValidationGreaterThan(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String validation = fields.getValidation();
                String[] EqualsTo = validation.split(" ");
                if(EqualsTo[1].equalsIgnoreCase(">")){
                    return true;
                }
            }
        }
        return false;
    }

    public static void getFieldIdValidationGreaterThanOrEqualToByTypeName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        if(isValidationGreaterThanOrEqualTo(pojo,object.getFieldId())){
                            CheckboxObject.fieldId.add(object.getFieldId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean isValidationGreaterThanOrEqualTo(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String validation = fields.getValidation();
                String[] EqualsTo = validation.split(" ");
                if(EqualsTo[1].equalsIgnoreCase(">=")){
                    return true;
                }
            }
        }
        return false;
    }

    public static void getFieldIdValidationLessThanByTypeName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        if(isValidationLessThan(pojo,object.getFieldId())){
                            CheckboxObject.fieldId.add(object.getFieldId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean isValidationLessThan(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String validation = fields.getValidation();
                String[] EqualsTo = validation.split(" ");
                if(EqualsTo[1].equalsIgnoreCase("<")){
                    return true;
                }
            }
        }
        return false;
    }

    public static void getFieldIdValidationLessThanOrEqualToByTypeName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        if(isValidationLessThanOrEqualTo(pojo,object.getFieldId())){
                            CheckboxObject.fieldId.add(object.getFieldId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean isValidationLessThanOrEqualTo(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String validation = fields.getValidation();
                String[] EqualsTo = validation.split(" ");
                if(EqualsTo[1].equalsIgnoreCase("<=")){
                    return true;
                }
            }
        }
        return false;
    }

    public static Integer getValidationNumber(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String validation = fields.getValidation();
                String[] EqualsTo = validation.split(" ");
                return Integer.parseInt(EqualsTo[2]);
            }
        }
        return 0;
    }

    public  static boolean isFieldIdMia(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        boolean result = false;
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: page.getObjects()
            ) {
                if(object.getTypename()!=null&&object.getTypename().equalsIgnoreCase("ManualImageAreaText")){
                    if(object.getFieldId().equalsIgnoreCase(strFieldId)){
                        result=true;
                        break outerLoop;
                    }
                }
            }
        }
        return result;
    }

    public static boolean isFieldIdPickList(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        boolean result = false;
        for (var pages : pojo.data.project.getPages()
        ) {
            for (var object : pages.getObjects()
            ) {
                if(object.getFieldId()!=null&&object.getFieldId().equalsIgnoreCase(strFieldId)){
                    if(object.getTypename().equalsIgnoreCase("picklist")){
                        result = true;
                    }else{
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    public static boolean getMiaRules(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&(isFieldIdPickList(pojo,strFieldId)||isFieldIdMia(pojo,strFieldId))){
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
                return true;
            }
        }
        return false;
    }

    public static boolean getHroRules(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&isFieldIdHro(pojo,strFieldId)){
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
                Reporter.log("<b>Derivation: </b>"+ CheckboxObject.strDerivation);
                Reporter.log("<b>Validation: </b>"+CheckboxObject.strValidation);
                return true;
            }
        }
        return false;
    }
}
