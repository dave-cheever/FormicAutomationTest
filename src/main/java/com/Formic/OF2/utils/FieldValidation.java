package com.Formic.OF2.utils;

import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import org.openqa.selenium.WebDriver;

public class FieldValidation extends BasePage {
    public FieldValidation(WebDriver driver) {
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

}
