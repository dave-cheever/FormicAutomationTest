package com.Formic.OF2.utils;

import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class DataValidation extends BasePage {
    public DataValidation(WebDriver driver) {
        super(driver);
    }

    public static void getAllFieldIdByTypeName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
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

    public static boolean isFieldIdPickList(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        boolean result = false;
        for (var pages : pojo.data.project.getPages()
        ) {
            for (var object : pages.getObjects()
            ) {
                if(object.getFieldId()!=null&&object.getFieldId().equalsIgnoreCase(strFieldId)){
                    if(object.getTypename().equalsIgnoreCase("picklist")){
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}
