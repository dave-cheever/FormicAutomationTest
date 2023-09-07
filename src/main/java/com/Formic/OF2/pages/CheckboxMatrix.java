package com.Formic.OF2.pages;

import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;


import static com.Formic.OF2.pages.CheckBoxPage.*;

public class CheckboxMatrix extends BasePage {

    public CheckboxMatrix(WebDriver driver) {
        super(driver);
    }

    static String checkboxMatrixLocator = "//div[@data-object-id=\"$TEXT\"]/fieldset/label";
    static String checkboxMatrixElementToBeClickedLocator = "//input[@data-field-id=\"$TEXT\"][$NUM]";
    static String checkboxMatrixValidationMessage = "//h1[@id=\"dialog-title-13\"]/ancestor::div/following-sibling::div/div/div/div[2]";
    static String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";
    static String mandatoryFieldMessageLocator = "//div[@data-object-id='$TEXT']/div/div";
    static String fieldErrorsPopUpValidationMessage = "//h1[text()=\"Field Errors\"]/ancestor::div/following-sibling::div/div/div/div[text()=\"$TEXT\"]/following::div";
    static String checkBoxMatrixSeeFieldErrorsButton = "//div[@data-object-id=\"$TEXT\"]/div/div";
    static String closeButton = "//h1[text()='Field Errors']/following::button";
    static String fieldSetLocator = "//input[@data-field-id=\"$TEXT\"][1]";
    static String checkboxMatrixCheckboxLocator = "//input[@data-field-id=\"$TEXT\"]";
    static String fieldErrorsElementCount = "//h1[text()=\"Field Errors\"]/ancestor::div/following-sibling::div/div/div/div[1]";


    CheckBoxPage checkBoxPage;
    static CheckboxObject checkboxObject;

    public static void clickCloseButton(){
        String closeButtonElement;
        closeButtonElement = closeButton;
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(closeButtonElement)));
        WebElement element = stringToWebElement(closeButtonElement);
        click(element);
    }

    public static String getValidationMessageByOptionName(String strName){
        String elem = stringReplace(fieldErrorsPopUpValidationMessage,strName);
        WebElement element = stringToWebElement(elem);
        return element.getText();
    }

    public static void clickSeeFieldErrorsByObjectId(String objectId){
        String elem = stringReplace(checkBoxMatrixSeeFieldErrorsButton,objectId);
        WebElement element = stringToWebElement(elem);
        click(element);
    }


//    public static int clickWithinMinimumMaximumInput(FormContentPojo pojo, int minInput, int maxInput, String strObjectElementId, int elementCountInACheckbox){
//        String[] gen = minMaximumGeneratedInputs(pojo,minInput,maxInput,elementCountInACheckbox);
//        gen = adjustInputIfAlreadySelected(pojo,gen);
//        String strFieldId = getFieldIdByObjectId(pojo,strObjectElementId);
//        recordInputsFromCheckbox(getFieldIdByObjectId(pojo,strFieldId),gen);
//        System.out.println(CheckboxObject.checkboxInputs);
//        clickElementHasValue(gen,strObjectElementId);
//        return gen.length;
//    }

    public static void clickElementHasValue(String[] gen, String strObjectElementId){
        String elem;
        WebElement element;
        for(int x = 0; x<gen.length;x++){
            elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strObjectElementId,gen[x]);
            scrollElementIntoView(driver,stringToWebElement(elem));
            element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            Reporter.log("Click checkbox number: "+gen[x]);
            if(!element.isSelected()) {
                click(element);
            }
        }
    }

    public static int countCheckboxItems(String strObjectElementId){
        String element = stringReplace(checkboxMatrixCheckboxLocator,strObjectElementId);
        int numberOfElements = numberOfElementsVisible(element);
        for(int x = 1; x <= numberOfElements; x++){
            element = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strObjectElementId,Integer.toString(x));
            WebElement elem = stringToWebElement(element);
            if(elem.isSelected()){
                CheckboxObject.hasValueList.add(Integer.toString(x));
            }
        }
        return numberOfElements;
    }

    public static boolean isCheckboxMatrixFieldNotChecked(FormContentPojo pojo, String strFieldId){
        int numberOfCheckbox = countCheckboxItems(strFieldId);
        String elem = "";
        WebElement element;
        boolean result = true;
        outerLoop:
        for(int x = 1; x<=numberOfCheckbox;x++){
            elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strFieldId,Integer.toString(x));
            element = stringToWebElement(elem);
            if(element.isSelected()){
                result = false;
                break outerLoop;
            }
        }
        return result;
    }

    public static void assertCheckboxMinMaxMandatory(FormContentPojo pojo, String strFieldId) throws Exception {
        if(CheckboxObject.isMandatoryFieldTest){
            AssertMandatoryFields(pojo,strFieldId);
        } else if(CheckboxObject.lessThanMinimumInputs){
            assertLessThanMinimumInput(pojo,strFieldId);
        }else if(CheckboxObject.withinMinimumInputs){
            assertWithinMinimumInput(pojo,strFieldId);
        }else if (CheckboxObject.withinMaximumInputs){
            assertWithinMaximumInput(pojo,strFieldId);
        }else if (CheckboxObject.beyondMaximumInputs){
            assertBeyondMaximumInput(pojo,strFieldId);
        }else if(CheckboxObject.minimumConfig){
            assertMinimumConfig(pojo,strFieldId);
        }else if(CheckboxObject.withinMinimumMaximumInputs){
            CheckBoxPage.assertWithinMinimumMaximumInput(pojo,strFieldId);
        }
    }

    public static void assertMinimumConfig(FormContentPojo pojo, String strFieldId) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(CheckboxObject.minimum==1){
            assertRequiredField(pojo,elementId,CheckboxObject.checkboxName);
        }else {
            assertMinimumConfigNoInputs(elementId,CheckboxObject.checkboxName,CheckboxObject.minimum);
        }
    }

    public static void assertMinimumConfigNoInputs(String strObjectElementId, String name, Integer minimumInput){
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field requires a minimum of "+minimumInput+" responses.","The expected value is : This field requires a minimum of "+minimumInput+" responses.");
        clickSeeFieldErrorsByObjectId(strObjectElementId);
        String validationMessage = getValidationMessageByOptionName(name);
        Assert.assertEquals(validationMessage,"This field requires a minimum of "+minimumInput+" responses.","The expected value is : This field requires a minimum of "+minimumInput+" responses. but the actual message is "+validationMessage);
        clickCloseButton();
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static boolean checkIdIfDisabled(String objectId) {
        String elem = stringReplace(fieldSetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        return !element.isEnabled();
    }

    public static void clickSpecificRadioButton(FormContentPojo pojo, String strFieldId, String hasValue) {
        String elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strFieldId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        if(!element.isSelected()&&!hasValue.equalsIgnoreCase("0")){
            if(!elementClickable(element)){
                unselectCheckboxThatDisableField(pojo,element,strFieldId);
            }
            click(element);
        }
    }

    public static void clickSpecificRadioButtonForEnableDisableFields(FormContentPojo pojo, String elementId, String hasValue) {
        String elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,elementId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String name = getFieldNameByElementId(pojo,elementId);
        if(!element.isSelected()&&!hasValue.equalsIgnoreCase("0")){
            Reporter.log("Check if "+name+" is clickable.");
            //pasok dito yung pag check kung clickable ba yung element
            String strFieldId = getFieldIdByObjectId(pojo,elementId);
            outerLoop:
            for(boolean x = false; x == false;){
                for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
                ) {
                    if(!element.isEnabled()){
                        if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                            for (com.Formic.OF2.utils.Pojo.Condition condition : routing.getConditions()
                            ) {
                                if(condition.getAction().equalsIgnoreCase("DISABLE")){
                                    lookForTheField(pojo,condition.getWhenField());
                                    String strElementWhenField = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strFieldId,condition.getHasValue());
                                    WebElement elementWhenField = stringToWebElement(strElementWhenField);
                                    if(elementWhenField.isSelected()){
                                        click(elementWhenField);
                                    }
                                }
                            }
                        }

                    }else {
                        x = true;
                        break outerLoop;
                    }
                }
            }
            click(element);
        }
    }

    public static void unselectCheckboxThatDisableField(FormContentPojo pojo, WebElement element, String strFieldId){
        do{
            for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                    for (com.Formic.OF2.utils.Pojo.Condition condition : routing.getConditions()
                    ) {
                        String strAction = condition.getAction();
                        String strHasValue = condition.getHasValue();
                        String strWhenFieldId = condition.getWhenField();
                        if(strAction.equalsIgnoreCase("DISABLE")){
                            lookForTheField(pojo,strWhenFieldId);
                            String elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strFieldId,strHasValue);
                            WebElement whenFieldElement = stringToWebElement(elem);
                            scrollElementIntoView(driver,element);
                            if(whenFieldElement.isSelected()){
                                click(whenFieldElement);
                            }
                        }
                    }
                }
            }
        }while (!elementClickable(element));
    }

    public static void assertBeyondMaximumInput(FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);

        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        getCheckboxRulesForMaximumInputs(pojo,strFieldId);
        ArrayList<String> numberOfOptions = checkboxMatrixOptionsCount(pojo,strFieldId);
        for (String fieldId : numberOfOptions
             ) {
            System.out.println("Number of options is: "+ numberOfOptions.size());
            int numberOfItems = countNumberOfResponses(strElementId,numberOfOptions.size());
            System.out.println("number of items is: "+numberOfItems);
            clickBeyondMaximumInput(pojo,CheckboxObject.maximum,fieldId,numberOfItems);
        }
        if(CheckboxObject.mandatory){
            assertThisFieldIsMandatory(pojo,strFieldId,getFieldName(pojo,strFieldId));
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public static int clickBeyondMaximumInput(FormContentPojo pojo, int maxInput, String strFieldId, int elementCountInACheckbox){
        String elem;
        WebElement element;
        Random rng = new Random();
        Set<String> generated = new LinkedHashSet<String>();
        //if the number of checkboxes available to a certain checkbox is equal to the amount of Max input, do not add+1 because this will trigger an
        //infinite loop in the while loop below
        maxInput = elementCountInACheckbox == maxInput ? maxInput : maxInput+1;

        while (generated.size() < maxInput)
        {
            String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
            generated.add(next);
        }
        String[] gen = generated.toArray(new String[generated.size()]);

        for(int x = 0; x<generated.size();x++){
            elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator, strFieldId,gen[x]);
            element = stringToWebElement(elem);

            if(maxInput!=1){
                scrollElementIntoView(driver,element);
                if(!element.isSelected()) {
                    click(element);
                }
            }else{
                scrollElementIntoView(driver,element);
                if(!element.isSelected()) {
                    click(element);
                }
                return 1;
            }
        }
        return generated.size();
    }

    public static void checkboxEnabledDisabledValidation(String objectId, String action, FormContentPojo pojo) {
        //2233066b00dc495c884448d69641f0f9
        String elem = stringReplace(fieldSetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String name = getFieldNameByElementId(pojo,objectId);
        if(action.equalsIgnoreCase("DISABLE")){
            System.out.println("Element: "+objectId+" is expected to be DISABLED");
            Reporter.log("Field Name: "+name+" is expected to be DISABLED");
//            recordScreenshot();
            Assert.assertFalse(element.isEnabled(), "field is not disabled " + name + " " + objectId);
            Reporter.log("Passed Routing");
            System.out.println("Passed Routing");
        }else if(action.equalsIgnoreCase("ENABLE")){
            System.out.println("Element: "+objectId+" is expected to be ENABLED" );
            Reporter.log("Field Name: "+name+" is expected to be ENABLED");
//            recordScreenshot();
            Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
            Reporter.log("Passed Routing");
            System.out.println("Passed Routing");
        }
    }

    public static int countNumberOfResponses(String strObjectId, int numberOfOptions){
        String elem = stringReplace(checkboxMatrixLocator,strObjectId);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elem)));
        int num = driver.findElements(By.xpath(elem)).size();
        return num - numberOfOptions;
    }

    public static boolean isFieldIdCheckBoxMatrix(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        String strObjectId = getObjectIdFromFieldId(pojo,strFieldId);
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

    public static ArrayList checkboxMatrixOptionsCount(FormContentPojo pojo, String strObjectId){
        ArrayList<String> fieldIdList = new ArrayList<String>();
        boolean flag = false;
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getSubQuestionFields()!=null){
                    for (var sub: object.getSubQuestionFields()
                         ) {
                        fieldIdList.add(sub.getGuidId());
                        if(sub.getGuidId().equalsIgnoreCase(strObjectId)){
                            flag = true;
                        }
                    }
                    if(flag){
                        return fieldIdList;
                    }else{
                        fieldIdList.clear();
                    }
                }
            }
        }
        return fieldIdList;
    }

    public static void assertWithinMaximumInput(FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        getCheckboxRulesForMaximumInputs(pojo,strFieldId);
        ArrayList<String> numberOfOptions = checkboxMatrixOptionsCount(pojo,strFieldId);
        System.out.println("Number of options is: "+ numberOfOptions.size());
        int numberOfItems = countNumberOfResponses(strElementId,numberOfOptions.size());
        System.out.println("number of items is: "+numberOfItems);
        clickWithinMaximumInput(pojo,checkboxObject.maximum,numberOfOptions,numberOfItems);
        if(checkboxObject.mandatory){
            assertThisFieldIsMandatory(pojo,strFieldId,getFieldName(pojo,strFieldId));
        }else {
            assertWithinAcceptedInputs(checkboxObject.checkboxName,elementId);
        }
    }

    public static void assertWithinMinimumInput(FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        getCheckboxRulesForMinimumInputs(pojo,strFieldId);
        ArrayList<String> numberOfOptions = checkboxMatrixOptionsCount(pojo,strFieldId);
        int numberOfItems = countNumberOfResponses(strElementId,numberOfOptions.size());
        clickWithinMinimumInput(CheckboxObject.minimum,strFieldId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public static int clickWithinMinimumInput(int minInput, String strFieldId, int elementCountInACheckbox){
        Set<String> generated = new LinkedHashSet<String>();
        if(minInput != 0){
            String elem;
            WebElement element;
            Random rng = new Random();
            while (generated.size() < minInput)
            {
                String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
                generated.add(next);
            }
            String[] gen = generated.toArray(new String[generated.size()]);
                for(int x = 0; x<generated.size();x++){
                    elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strFieldId,gen[x]);
                    element = stringToWebElement(elem);
                    scrollElementIntoView(driver,element);
                    if(!element.isSelected()) {
                        click(element);
                    }
                }

        }
        else {
            return 0;
        }
        return generated.size();
    }

    public static void assertLessThanMinimumInput(FormContentPojo pojo, String strFieldId) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        ArrayList<String> numberOfOptions = checkboxMatrixOptionsCount(pojo,strFieldId);
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        int numberOfItems = countNumberOfResponses(strElementId,numberOfOptions.size());
        int numberOfInputs = clickLessThanMinimumInput(pojo,CheckboxObject.minimum,strFieldId,numberOfItems);
        if(CheckboxObject.minimum==1&&numberOfInputs==1||numberOfInputs==0){
            assertRequiredField(pojo,elementId,CheckboxObject.checkboxName);
        }else{
            assertRequiresMinimumOf(CheckboxObject.checkboxName,elementId,CheckboxObject.minimum);
        }
    }

    public static void assertRequiredField(FormContentPojo pojo, String strObjectElementId, String name){
        comp.validateCompletionErrorMessage(name,"Required field.");
        clickSeeFieldErrorsByObjectId(strObjectElementId);
        String optionName = getFieldName(pojo,getFieldIdByObjectId(pojo,strObjectElementId));
        String validationMessage = getValidationMessageByOptionName(optionName);
        Assert.assertEquals(validationMessage,"Required field.","The expected value is : Required field. "+validationMessage);
        clickCloseButton();
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void assertThisFieldIsMandatory(FormContentPojo pojo, String strFieldId, String name){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(mandatoryFieldMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        Assert.assertEquals(element.getText(),"This field is mandatory.","The expected value is : This field is mandatory. "+element.getText());
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void assertRequiresMinimumOf(String name, String strObjectElementID, int minNumberOfInputs){
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field requires a minimum of "+minNumberOfInputs+" responses.","The expected value is : This field requires a minimum of "+minNumberOfInputs+" responses.");
        clickSeeFieldErrorsByObjectId(strObjectElementID);
        String validationMessage = getValidationMessageByOptionName(name);
        Assert.assertEquals(validationMessage,"This field requires a minimum of "+minNumberOfInputs+" responses.","The expected value is : This field requires a minimum of "+minNumberOfInputs+" responses. but the actual message is "+validationMessage);
        clickCloseButton();
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static int clickLessThanMinimumInput(FormContentPojo pojo,int minInput, String strObjectElementId, int elementCountInACheckbox){
        Set<String> generated = new LinkedHashSet<String>();
        if(minInput!=1){
            String elem;
            WebElement element;
            Random rng = new Random();
            while (generated.size() < minInput-1)
            {
                String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
                generated.add(next);
            }
            String[] gen = generated.toArray(new String[generated.size()]);
            for (String s : gen) {
                elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator, strObjectElementId, s);
                element = stringToWebElement(elem);
                scrollElementIntoView(driver, element);
                if (!element.isSelected()) {
                    click(element);
                }
            }
        }else{
            //returns 0 when the minimum input in a checkbox is 1 or no settings for minimum input. Because we have different validation message for
            // more than 1 minimum input
            return 0;
        }
        return generated.size();
    }

    public static void assertWithinAcceptedInputs(String strName, String strObjectElementId){
        String elemValidationMessage = stringReplace(checkboxMatrixValidationMessage,strObjectElementId);
        String elemCompilationErrors = stringReplace(validationMessageUponSubmitSideBar,strObjectElementId);
        Assert.assertFalse(isElementVisible(driver,elemValidationMessage),"The expected for : "+strName + " There shouldn't be any validation message displayed below the object.");
        Assert.assertFalse(isElementVisible(driver,elemCompilationErrors),"The expected for: "+strName + "Completion Errors isn't displayed. The actual is Completion errors is displayed.");
        Reporter.log("There shouldn't be any validation message displayed below the field: "+ strName);
//        recordScreenshot();
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void AssertMandatoryFields(FormContentPojo pojo, String strFieldId) throws Exception {
        lookForTheField(pojo,strFieldId);
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String fieldName = getFieldName(pojo,strFieldId);
        if(CheckboxObject.minimum==1||CheckboxObject.minimum==0){
            assertLessThanMinimumInput(pojo,strFieldId);
        }
    }

    public static int clickWithinMaximumInput(FormContentPojo pojo, int maxInput, ArrayList<String> strFieldId, int elementCountInACheckbox){
        String elem;
        WebElement element;
        Random rng = new Random();
        Set<String> generated = new LinkedHashSet<String>();
        //if the number of checkboxes available to a certain checkbox is equal to the amount of Max input, do not add+1 because this will trigger an
        //infinite loop in the while loop below
        System.out.println("Element count is: "+elementCountInACheckbox);
        while (generated.size() < maxInput)
        {
            String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
            generated.add(next);
        }
        String[] gen = generated.toArray(new String[generated.size()]);
        for (String fieldId : strFieldId
             ) {
            for(int x = 0; x<generated.size();x++){
                elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,fieldId,gen[x]);
                element = stringToWebElement(elem);
                scrollElementIntoView(driver,element);
                if(!element.isSelected()) {
                    click(element);
                }
            }
        }
        return generated.size();
    }

    public static int clickWithinMinimumMaximumInput(FormContentPojo pojo,int minInput,int maxInput,ArrayList<String> strObjectFieldId,int elementCountInACheckbox){
        String[] gen = minMaximumGeneratedInputs(pojo,minInput,maxInput,elementCountInACheckbox);
        gen = adjustInputIfAlreadySelected(pojo,gen);
        for (String fieldId: strObjectFieldId
        ) {
            recordInputsFromCheckbox(getObjectIdFromFieldId(pojo,fieldId),gen);
            System.out.println(CheckboxObject.checkboxInputs);
            clickElementHasValue(gen,fieldId);
        }
        return gen.length;
    }

}
