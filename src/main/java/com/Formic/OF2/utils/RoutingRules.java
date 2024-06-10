package com.Formic.OF2.utils;

import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.ListIterator;

import static com.Formic.OF2.utils.FieldManager.getObjectIdByFieldId;

public class RoutingRules extends BasePage {
    public RoutingRules(WebDriver driver) {
        super(driver);
    }

    static String ManualImageAreaLocator = "//div[@data-object-id='$TEXT']/textarea";
    static String HandwritingRecognitionObjectLocator = "//div[@data-object-id='$TEXT']/input";
    static String checkboxLocator = "//div[@data-object-id='$TEXT']/fieldset/input";
    static String PicklistLocator = "//div[@data-object-id='66942adf-4472-492d-824e-9ee5978824eb']/div/div/input";
    static String fieldSetLocator = "//input[@data-field-id=\"$TEXT\"][1]";
    static String actionLocator = "//div[@data-object-id='$TEXT']/fieldset/input[$NUM]";

    private static String disablingFieldId = null;


    private static boolean isFieldIdDisabledInRoutingRules(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        boolean result = false;
        loopBreak:
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
                ) {
                    if(routing.getFieldId().equalsIgnoreCase(fieldId)){
                        if(conditions.getAction().equalsIgnoreCase("enable")){
                            result = true;
                            break loopBreak;
                        }
                    }
                }
            }
        }
        return result;
    }

    private static boolean isFieldIdDisabledInForm(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        boolean result = false;
        lookForTheField(pojo,fieldId);
        String objectId = getObjectIdFromFieldId(pojo,fieldId);
        if(isFieldIdCheckBoxMatrix(pojo,fieldId)){
            String elem = stringReplace(fieldSetLocator,objectId);
            WebElement element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            result = !element.isEnabled();
        } else if (isFieldIdTickBoxGroup(pojo,fieldId)) {
            String elem = stringReplace(checkboxLocator,objectId);
            WebElement element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            result = !element.isEnabled();
        } else if (isFieldIdHro(pojo,fieldId)) {
            String elem = stringReplace(HandwritingRecognitionObjectLocator,objectId);
            WebElement element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            result = !element.isEnabled();
        } else if (isFieldIdMia(pojo,fieldId)) {
            String elem = stringReplace(ManualImageAreaLocator,objectId);
            WebElement element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            result = !element.isEnabled();
        } else if (isFieldIdPickList(pojo,fieldId)) {
            String elem = stringReplace(PicklistLocator,objectId);
            WebElement element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            result = !element.isEnabled();
        }
        return result;
    }

    private static boolean isFieldIdTickBoxGroup(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        return FieldManager.getTypeNameByFieldId(pojo,fieldId).equalsIgnoreCase("TickboxGroup");
    }

    private static boolean isFieldIdCheckBoxMatrix(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        boolean result = false;
        String strObjectId = getObjectIdFromFieldId(pojo,fieldId);
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getSubQuestionFields()!=null){
                    if(object.getGuidId().equalsIgnoreCase(strObjectId)){
                        if(object.getSubQuestionFields().size()>1){
                            result = true;
                            break outerLoop;
                        }
                    }

                }
            }
        }
        return result;
    }

    private static boolean isFieldIdHro(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        return FieldManager.getTypeNameByFieldId(pojo,fieldId).equalsIgnoreCase("HandwritingRecognitionObject");
    }

    private static boolean isFieldIdMia(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        return FieldManager.getTypeNameByFieldId(pojo,fieldId).equalsIgnoreCase("ManualImageAreaText");
    }

    private static boolean isFieldIdPickList(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        return FieldManager.getTypeNameByFieldId(pojo,fieldId).equalsIgnoreCase("PickList");
    }

    public static void enableDisabledFieldByFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        ArrayList<String> routingDetails = new ArrayList<>();
        if(isFieldIdDisabledInRoutingRules(pojo,fieldId)){
            routingDetails = getDisablingFieldId(pojo,fieldId);
            enableDisabledFields(pojo,routingDetails);
        }
    }

    private static ArrayList<String> getDisablingFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        ArrayList<String> result = new ArrayList<>();
        boolean flag = true;
        disablingFieldId = fieldId;
        while (flag){
            OuterLoop:
            for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
            ) {
                if(routing.getConditions()!=null){
                    for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
                    ) {
                        if(isFieldIdDisabledInRoutingRules(pojo,disablingFieldId)){
                            if(routing.getFieldId().equalsIgnoreCase(disablingFieldId)){
                                if(conditions.getAction().equalsIgnoreCase("enable")){
                                    result.add(conditions.getWhenField());
                                    result.add(conditions.getHasValue());
                                    disablingFieldId = conditions.getWhenField();
                                    break;
                                }
                            }
                        }else{
                            flag = false;
                            break OuterLoop;
                        }
                    }
                }
            }
        }
        return result;
    }

    private static void  enableDisabledFields(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, ArrayList<String> arrayList){
        for(;arrayList.size()!=0;){
            int endingIndex = arrayList.size()-1;
            String action = arrayList.get(endingIndex);
            arrayList.remove(endingIndex);
            String fieldId = arrayList.get(endingIndex-1);
            arrayList.remove(endingIndex-1);
            lookForTheField(pojo,fieldId);
            String elem = stringReplaceTwoValues(actionLocator,getObjectIdByFieldId(pojo,fieldId),action);
            By byXPathFieldId = By.xpath(elem);
            scrollElementIntoView(driver,byXPathFieldId);
            WebElement element = convertToWebElement(elem);
            click(element);
        }
    }


}
