package com.Formic.OF2.pages;

import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.*;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import static com.Formic.OF2.pages.CheckboxMatrix.clickCloseButton;
import static com.Formic.OF2.pages.CheckboxMatrixV2.getValidationMessageByOptionName;
import static com.Formic.OF2.utils.FieldManager.*;

public class CheckBoxPageV2 extends BasePage {
    public CheckBoxPageV2(WebDriver driver) {
        super(driver);
    }

    int projectId = 137;
    String emailRegEx = "^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$";
    static String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";
    static String mandatoryFieldMessageLocator = "//div[@data-object-id='$TEXT']/div/div";
    static String defaultFieldLocator = "//div[@data-object-id='$TEXT']";
    static String mandatoryFieldMessagePickListLocator = "(//div[@data-object-id='$TEXT']/div/div/div)[2]";
    static String validationMessageLocator = "//div[@data-object-id='$TEXT']/div/div[1]";
    static String fieldSetLocator = "//div[@data-object-id='$TEXT']/fieldset/input[1]";
    String ManualImageAreaText = "//div[@data-object-id='$TEXT']/textarea";
    String HandwritingRecognitionObject = "//div[@data-object-id='$TEXT']/input";
    static String actionLocator = "//div[@data-object-id='$TEXT']/fieldset/input[$NUM]";
    static String newValidationMessageUponSubmit = "//div[@data-object-id='$TEXT']/div/div[1]";
    static String checkboxLocator = "//div[@data-object-id='$TEXT']/fieldset/input";
    static String checkboxElementToBeClickedLocator = "//div[@data-object-id='$TEXT']/fieldset/input[$NUM]";
    String checkboxMatrixElementToBeClickedLocator = "//input[@data-field-id='$TEXT'][$NUM]";
    String completionErrorsFieldNameList = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[1]";
    String completionErrorsFieldName = "(//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[1])[$TEXT]";
    String completionErrorsFieldNameButton = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[text()='$TEXT']";
    private static String checkboxName;
    private static boolean checkboxMandatory;

    public static DataValidation dataValidation = new DataValidation(driver);
    public static FieldValidation fieldValidation = new FieldValidation(driver);
    public SideMenuNavigation sideMenuNavigation = new SideMenuNavigation(driver);
    public static CompletionErrors comp = new CompletionErrors(driver);
    static JavascriptExecutor js = (JavascriptExecutor) driver;

    public void NoInputsMandatoryMinimumInputsValidationTest(String fieldId,String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
            String objectId = getObjectIdFromFieldId(graphResponse,fieldId);
            String fieldName = getFieldName(graphResponse,fieldId);
            boolean mandatory = isMandatory(graphResponse,fieldId);
            int minValue = getCheckBoxResponseMinValue(graphResponse,fieldId);
            Reporter.log("Object Id: "+objectId);
            Reporter.log("Field Id: "+fieldId);
            Reporter.log("Field Name: "+fieldName);
            Reporter.log("Mandatory: "+mandatory);
            Reporter.log("Submit button clicked");
            sideMenuNavigation.clickSubmitButton();
            Reporter.log("Navigate to the location of the element");
            lookForTheField(graphResponse,fieldId);
            Reporter.log("Validate checkbox error message");
            lookForTheField(graphResponse,fieldId);
            clickSpecificRadioButtonAlreadyClicked(graphResponse,FieldManager.findDisablingFieldIds(graphResponse,fieldId));
            assertRequiredField(objectId,minValue,scenarioName);

    }

    public void CheckboxToggleEnableDisableFields(String whenFieldId, String hasValue, String fieldId, String action,String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        String whenFieldObjectId = getObjectIdFromFieldId(graphResponse,whenFieldId);
        String strElementWhenField = stringReplaceTwoValues(actionLocator,whenFieldObjectId,hasValue);
        By byXPath = By.xpath(strElementWhenField);
        waitForPageToLoad();
        lookForTheField(graphResponse,whenFieldId);
        scrollElementIntoView(driver,byXPath);
        WebElement elementWhenField = stringToWebElement(strElementWhenField);
        if(!elementWhenField.isSelected()){
            click(elementWhenField);
        }
        lookForTheField(graphResponse,fieldId);
        String elementId = getObjectIdByFieldId(graphResponse,fieldId);
        String typeName = FieldManager.getTypeNameByFieldId(graphResponse,fieldId);
        String element = stringReplace(defaultFieldLocator,elementId);
        By byXPathFieldId = By.xpath(element);
        scrollElementIntoView(driver,byXPathFieldId);
        assertEnableDisableFields(graphResponse,typeName,elementId,action,fieldId,scenarioName);
    }

    public void assertEnableDisableFields(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String typeName, String strElementId, String action,String fieldId, String scenarioName){
        if(typeName.equalsIgnoreCase("TickboxGroup")){
            if(CheckboxMatrixV2.isFieldIdCheckBoxMatrix(pojo,getFieldIdByObjectId(pojo,strElementId))){
                CheckboxMatrixV2.checkboxEnabledDisabledValidation(fieldId,action,pojo,scenarioName);
            }else{
                checkboxEnabledDisabledValidation(strElementId,action,pojo,scenarioName);
            }
        } else if (typeName.equalsIgnoreCase("HandwritingRecognitionObject")) {
            checkboxEnabledDisabledValidationForHandwritingRecognitionObject(strElementId,action,pojo,scenarioName);
        }else if(typeName.equalsIgnoreCase("ManualImageAreaText")){
            checkboxEnabledDisabledValidationForMia(strElementId,action,pojo,scenarioName);
        }
    }

    public static void checkboxEnabledDisabledValidation(String objectId, String action, FormContentPojo pojo, String scenarioName) {
        String elem = stringReplace(fieldSetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String name = getFieldNameByElementId(pojo,objectId);
        try{
            if(action.equalsIgnoreCase("DISABLE")){
                Reporter.log("Field Name: "+name+" is expected to be DISABLED");
                Assert.assertFalse(element.isEnabled(), "field is not disabled " + name + " " + objectId);
                Reporter.log("Passed Routing");
            }else if(action.equalsIgnoreCase("ENABLE")){
                Reporter.log("Field Name: "+name+" is expected to be ENABLED");
                Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
                Reporter.log("Passed Routing");
            }
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void checkboxEnabledDisabledValidationForHandwritingRecognitionObject(String objectId, String action, com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String scenarioName) {
        String name = getFieldNameByElementId(pojo,objectId);
        String elem = stringReplace(HandwritingRecognitionObject,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        try{
            if(action.equalsIgnoreCase("DISABLE")){
                System.out.println("Element: "+objectId+" is expected to be DISABLED MIA" );
                Reporter.log("Field Name: "+name+" is expected to be DISABLED MIA");
                Assert.assertFalse(element.isEnabled(), "field is not disabled for " + name + " " + objectId);
                Reporter.log("Passed Routing");
                System.out.println("Passed Routing");
            }else if(action.equalsIgnoreCase("ENABLE")){
                System.out.println("Element: "+objectId+" is expected to be ENABLED MIA" );
                Reporter.log("Field Name: "+name+" is expected to be ENABLED MIA");
                Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
                Reporter.log("Passed Routing");
                System.out.println("Passed Routing");
            }
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void checkboxEnabledDisabledValidationForMia(String objectId, String action, com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String scenarioName) {
        String name = getFieldNameByElementId(pojo,objectId);
        String elem = stringReplace(ManualImageAreaText,objectId);
        WebElement element;
        try{
            if(action.equalsIgnoreCase("DISABLE")){
                element = stringToWebElement(elem);
                scrollElementIntoView(driver,element);
                System.out.println("Element: "+objectId+" is expected to be DISABLED MIA" );
                Reporter.log("Field Name: "+name+" is expected to be DISABLED MIA");
                Assert.assertFalse(element.isEnabled(), "field is not disabled for " + name + " " + objectId);
                Reporter.log("Passed Routing");
                System.out.println("Passed Routing");
            }else if(action.equalsIgnoreCase("ENABLE")){
                element = stringToWebElement(elem);
                scrollElementIntoView(driver,element);
                System.out.println("Element: "+objectId+" is expected to be ENABLED MIA" );
                Reporter.log("Field Name: "+name+" is expected to be ENABLED MIA");
                Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
                Reporter.log("Passed Routing");
                System.out.println("Passed Routing");
            }
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void clickSpecificRadioButtonAlreadyClicked(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,ArrayList<String> disablingFields) {
         while(!disablingFields.isEmpty()){
             String elementId = FieldManager.getObjectIdByFieldId(pojo,disablingFields.get(0));
             disablingFields.remove(0);
             String hasValue = disablingFields.get(0);
             disablingFields.remove(0);
             String elem = stringReplaceTwoValues(actionLocator,elementId,hasValue);
             WebElement element = stringToWebElement(elem);
             scrollElementIntoView(driver,element);
             if(!element.isSelected()){
                 elementClickable(element);
                 click(element);
             }
         }
    }

    public static void assertRequiredField(String strObjectElementId, int minValue,String scenarioName){
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(validationMessageLocator,strObjectElementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        sleep(2000);
        try{
            if(minValue==0||minValue==1){
                Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Required field. "+validationMessageUnderCheckbox.getText());
            }else{
                Assert.assertEquals(validationMessageUnderCheckbox.getText(),"This field requires a minimum of "+minValue+" responses.","The expected value is : This field requires a minimum of "+minValue+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
            }
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public static void assertRequiredField(String strObjectElementId, String name,String scenarioName){
        comp.validateCompletionErrorMessage(name,"Required field.");
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(validationMessageLocator,strObjectElementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        try{
            Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Required field. "+validationMessageUnderCheckbox.getText());
            CheckboxObject.checkboxObjectDefaultValue();
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public static boolean isFieldIdDisabled(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        boolean result = false;
        loopBreak:
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
                ) {
                    if(conditions.getWhenField().equalsIgnoreCase(strFieldId)){
                        if(conditions.getAction().equalsIgnoreCase("disable")){
                            result = true;
                            break loopBreak;
                        }
                    }
                }
            }
        }
        return result;
    }

    public void checkboxMinimumRequiredInputsUponSubmit(String fieldId,String min, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        checkboxName = getFieldName(graphResponse,fieldId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        if(CheckboxMatrixV2.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
            CheckboxMatrixV2.assertMinimumConfig(graphResponse,fieldId,min,checkboxName,scenarioName);
        }else {
            assertMinimumConfig(graphResponse,fieldId,min,checkboxName,scenarioName);
        }
    }

    public void checkboxBeyondMaximumInputs(String fieldId,String max,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        if(CheckboxMatrixV2.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
            CheckboxMatrixV2.assertWithinMaximumInput(graphResponse, fieldId,max,checkboxName,checkboxMandatory,scenarioName);
        }else {
            assertWithinMaximumInput(graphResponse,fieldId,max,scenarioName);
        }
    }

    public void checkboxWithinMaximumInputs(String fieldId,String max,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        if(CheckboxMatrixV2.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
            CheckboxMatrixV2.assertWithinMaximumInput(graphResponse, fieldId,max,checkboxName,checkboxMandatory,scenarioName);
        }else {
            assertWithinMaximumInput(graphResponse,fieldId,max,scenarioName);
        }
    }

    public void checkboxWithinMinimumInputs(String fieldId,String min,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        if(CheckboxMatrixV2.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
            CheckboxMatrixV2.assertWithinMinimumInput(graphResponse, fieldId,Integer.parseInt(min),checkboxName,mandatory,scenarioName);
        }else {
            System.out.println(CheckboxObject.checkboxName+ " Is enabled");
            assertWithinMinimumInput(graphResponse,fieldId,min,scenarioName);
        }
    }

    public void validateCheckboxLessThanMinimumInputsUponSubmit(String fieldId,String min,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        if(CheckboxMatrixV2.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
            CheckboxMatrixV2.assertLessThanMinimumInput(graphResponse, fieldId,Integer.parseInt(min),checkboxName,scenarioName);
        }else {
            assertLessThanMinimumInput(graphResponse,fieldId,Integer.parseInt(min),scenarioName);
        }
    }

    public void assertLessThanMinimumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,Integer min, String scenarioName) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        int numberOfInputs = clickLessThanMinimumInput(min,elementId,numberOfItems);
        if(min==1&&numberOfInputs==1||numberOfInputs==0){
            assertRequiredField(elementId,checkboxName,scenarioName);
        }else{
            assertRequiresMinimumOf(checkboxName,elementId,min,scenarioName);
        }
    }

    public void assertRequiresMinimumOf(String name,String strObjectElementID, int minNumberOfInputs,String scenarioName){
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(newValidationMessageUponSubmit,strObjectElementID);
        try{
            Assert.assertEquals(validationMessageUnderCheckbox.getText(),"This field requires a minimum of "+minNumberOfInputs+" responses.","The expected value is : This field requires a minimum of "+minNumberOfInputs+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
            Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field requires a minimum of "+minNumberOfInputs+" responses.","The expected value is : This field requires a minimum of "+minNumberOfInputs+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
            CheckboxObject.checkboxObjectDefaultValue();
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public int clickLessThanMinimumInput(int minInput,String strObjectElementId, int elementCountInACheckbox){
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
                elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator, strObjectElementId, s);
                element = stringToWebElement(elem);
                scrollElementIntoView(driver, element);
                if (!element.isSelected()) {
                    click(element);
                }
            }
        }else{
            return 0;
        }
        return generated.size();
    }

    public void assertWithinMinimumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,String min, String scenarioName) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickWithinMinimumInput(Integer.parseInt(min),elementId,numberOfItems);
        if(checkboxMandatory){
            AssertMandatoryFieldsWithInputs(pojo,strFieldId,scenarioName);
        }else{
            assertWithinAcceptedInputs(checkboxName,elementId,scenarioName);
        }
    }

    public int clickWithinMinimumInput(int minInput,String strObjectElementId,int elementCountInACheckbox){
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
                elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator,strObjectElementId,gen[x]);
                scrollElementIntoView(driver,stringToWebElement(stringReplace(checkboxLocator,strObjectElementId)));
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

    public void assertWithinMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId, String max,String scenarioName) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        waitForPageToLoad();
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickWithinMaximumInput(pojo,Integer.parseInt(max),strFieldId,numberOfItems);
        if(checkboxMandatory){
            AssertMandatoryFieldsWithInputs(pojo,strFieldId,scenarioName);
        }else{
            assertWithinAcceptedInputs(checkboxName,elementId,scenarioName);
        }
    }

    public static void assertWithinAcceptedInputs(String strName, String strObjectElementId,String scenarioName){
        String elemValidationMessage = stringReplace(newValidationMessageUponSubmit,strObjectElementId);
        String elemCompilationErrors = stringReplace(validationMessageUponSubmitSideBar,strObjectElementId);
        try{
            Assert.assertFalse(isElementVisible(driver,elemValidationMessage),"The expected for : "+strName + " There shouldn't be any validation message displayed below the object.");
            Assert.assertFalse(isElementVisible(driver,elemCompilationErrors),"The expected for: "+strName + "Completion Errors isn't displayed. The actual is Completion errors is displayed.");
            Reporter.log("There shouldn't be any validation message displayed below the field: "+ strName);
            CheckboxObject.checkboxObjectDefaultValue();
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public static void AssertMandatoryFieldsWithInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,String scenarioName) throws Exception {
        lookForTheField(pojo,strFieldId);
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String element;
        if(dataValidation.isFieldIdPickList(pojo,strFieldId)|| fieldValidation.isFieldIdMia(pojo,strFieldId)){
            element = stringReplace(mandatoryFieldMessagePickListLocator,elementId);
        }else{
            element = stringReplace(mandatoryFieldMessageLocator,elementId);
        }
        WebElement validationMessageLocator = stringToWebElement(element);
        scrollElementIntoView(driver,validationMessageLocator);
        String fieldName = getFieldName(pojo,strFieldId);
        try{
            Assert.assertEquals(validationMessageLocator.getText(),"This field is mandatory.", fieldName+"The expected validation message for "+fieldName+" was: This field is mandatory. but the actual message was: "+validationMessageLocator.getText());
            Reporter.log("<b>Checkbox Name: <b/>"+fieldName+" <b>is mandatory. <b/>",true);
            CheckboxObject.checkboxObjectDefaultValue();
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public int clickWithinMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,int maxInput,String strFieldId,int elementCountInACheckbox){
        String elem;
        WebElement element;
        Random rng = new Random();
        Set<String> generated = new LinkedHashSet<String>();
        while (generated.size() < maxInput)
        {
            String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
            generated.add(next);
        }
        String[] gen = generated.toArray(new String[generated.size()]);
        for(int x = 0; x<generated.size();x++){
            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator, getObjectIdFromFieldId(pojo,strFieldId),gen[x]);
            element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            if(!element.isSelected()) {
                click(element);
            }
        }
        return generated.size();
    }

    public static int countCheckboxItems(String strObjectElementId){
        String element = stringReplace(checkboxLocator,strObjectElementId);
        int numberOfElements = numberOfElementsVisible(element);
        for(int x = 1; x <= numberOfElements; x++){
            element = stringReplaceTwoValues(actionLocator,strObjectElementId,Integer.toString(x));
            WebElement elem = stringToWebElement(element);
            if(elem.isSelected()){
                CheckboxObject.hasValueList.add(Integer.toString(x));
            }
        }
        return numberOfElements;
    }



    public void assertMinimumConfig(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,String minValue,String checkBoxName,String scenarioName) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(Integer.parseInt(minValue)==1){
            assertRequiredField(elementId,checkBoxName,scenarioName);
        }else {
            assertMinimumConfigNoInputs(elementId,checkBoxName,Integer.parseInt(minValue),scenarioName);
        }

    }

    public void assertMinimumConfigNoInputs(String strObjectElementId, String name, Integer minimumInput, String scenarioName){
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(validationMessageLocator,strObjectElementId);
        try{
            Assert.assertEquals(validationMessageUnderCheckbox.getText(),"This field requires a minimum of "+minimumInput+" responses.","The expected value is : This field requires a minimum of "+minimumInput+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
            Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field requires a minimum of "+minimumInput+" responses.","The expected value is : This field requires a minimum of "+minimumInput+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
            CheckboxObject.checkboxObjectDefaultValue();
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

}
