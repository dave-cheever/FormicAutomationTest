package com.Formic.OF2.pages;

import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.*;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.text.ParseException;
import java.util.*;

import static com.Formic.OF2.pages.CheckboxMatrix.clickCloseButton;
import static com.Formic.OF2.pages.CheckboxMatrixV2.getValidationMessageByOptionName;
import static com.Formic.OF2.utils.FieldManager.*;

public class CheckBoxPageV2 extends BasePage {
    public CheckBoxPageV2(WebDriver driver) {
        super(driver);
    }

    int projectId = Integer.parseInt(ConfigLoader.getProperty("test.CheckboxProjectId"));
    int projectIdNavigation = Integer.parseInt(ConfigLoader.getProperty("test.NavigationProjectId"));

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
    public static HandwritingRecognitionObject hro = new HandwritingRecognitionObject(driver);
    public static ManualImageArea mia = new ManualImageArea(driver);
    public static FieldValidation fieldValidation = new FieldValidation(driver);
    public SideMenuNavigation sideMenuNavigation = new SideMenuNavigation(driver);
    public static CompletionErrors comp = new CompletionErrors(driver);
    static JavascriptExecutor js = (JavascriptExecutor) driver;

    public static boolean isFieldIdInRoutingRulesWhenFieldDisable(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
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
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,whenFieldId);
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
        assertMinimumConfig(graphResponse,fieldId,min,checkboxName,scenarioName);
    }

    public void checkboxBeyondMaximumInputs(String fieldId,String max,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        assertWithinMaximumInput(graphResponse,fieldId,max,scenarioName);

    }

    public void checkboxWithinMaximumInputs(String fieldId,String max,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        assertWithinMaximumInput(graphResponse,fieldId,max,scenarioName);

    }

    public void checkboxWithinMinimumInputs(String fieldId,String min,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
        assertWithinMinimumInput(graphResponse,fieldId,min,scenarioName);

    }

    public void validateCheckboxLessThanMinimumInputsUponSubmit(String fieldId,String min,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        assertLessThanMinimumInput(graphResponse,fieldId,Integer.parseInt(min),scenarioName);

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

    public void validateSubmittedInputsCheckbox() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectIdNavigation);
        sideMenuNavigation.clickSubmitButton();
        String strListFieldName;
        String fieldId;
        String strTypeName;
        String strElementId;
        do{
            strListFieldName = getListFieldNameByCompletionErrors();
            strElementId = getElementIdByFieldName(graphResponse,strListFieldName);
            fieldId = getFieldIdByObjectId(graphResponse,strElementId);
            strTypeName = getTypeNameByFieldId(graphResponse,fieldId);
            clickFieldNameInCompletionErrors(strListFieldName);

            if(strTypeName.equalsIgnoreCase("HandwritingRecognitionObject")){

                FieldMetaData.getHroRules(graphResponse,fieldId);
                lookForTheField(graphResponse,fieldId);
                hroInputs(graphResponse,fieldId);

            }else if(strTypeName.equalsIgnoreCase("TickboxGroup")){
                //Checkbox Matrix
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,fieldId);
                    ArrayList<String> numberOfOptions = CheckboxMatrix.checkboxMatrixOptionsCount(graphResponse,fieldId);
                    int numberOfItems = CheckboxMatrix.countNumberOfResponses(strElementId,numberOfOptions.size());
                    CheckboxMatrixV2.clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,numberOfOptions,numberOfItems);

                }else{
                 //Checkbox
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,fieldId);
                    int numberOfItems = countCheckboxItems(strElementId);
                    clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,strElementId,numberOfItems);
                }

            }else if(strTypeName.equalsIgnoreCase("ManualImageAreaText")){

                FieldMetaData.getMiaRules(graphResponse,fieldId);
                lookForTheField(graphResponse,fieldId);
                miaInputs(graphResponse,fieldId);

            }else if(strTypeName.equalsIgnoreCase("PickList")){

                FieldMetaData.getMiaRules(graphResponse,fieldId);
                picklistInputs(graphResponse,fieldId);

            }
            sleep(2000);
        }while (!getListFieldNameByCompletionErrors().isEmpty());
        sideMenuNavigation.clickSubmitButton();
        String receipt = getProjectReceipt();
        clickContinueButton();
        clickSavedFormsButton();
        Reporter.log("<b>Receipt code:<b/> "+receipt);
        enterReceiptNumber(receipt);
        clickGoButton();
        System.out.println(receipt);
        validateInputsAreCorrect(graphResponse);
    }

    public static void clickContinueButton(){
        try{
            String continueBtn = "//button[text()='Continue']";
            WebElement element = stringToWebElement(continueBtn);
            click(element);
        }catch(Exception e){
            Reporter.log("Continue button missing. "+e);
        }
    }

    public static void clickSavedFormsButton(){
        try{
            String continueBtn = "//span[text()='Saved Forms']";
            WebElement element = stringToWebElement(continueBtn);
            click(element);
        }catch (NoSuchElementException e){
            Reporter.log("Save forms button not visible.");
        }

    }

    public static void enterReceiptNumber(String strReceipt){
        String strReceiptNumber = "//input[@id='receipt-entry-input']";
        WebElement element = stringToWebElement(strReceiptNumber);
        enterText(element,strReceipt);
    }

    public static void clickGoButton(){
        String strGoButton = "//button[text()='Go']";
        try{
            WebElement element = stringToWebElement(strGoButton);
            click(element);
        }catch (NoSuchElementException e){
            Reporter.log("Go button not visible. " + e);
        }
    }

    public String getProjectReceipt(){
        try{
            String test = "//h2[contains(text(),'Complete')]/ancestor::main/p[contains(text(),'You can use the following receipt to recall this particular form at a later date.')]";
            WebElement elem = stringToWebElement(test);
            String text = elem.getText();
            String [] strReceipt;
            strReceipt = text.split(". ");
            return strReceipt[15];
        }catch (NoSuchElementException e){
            Reporter.log("Receipt not visible. "+e);
            return "";
        }
    }

    public void picklistInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId) throws ParseException {
        mia.addWithinMinimumMaximumOptions(pojo,CheckboxObject.minimum,strFieldId);
        int numberOfOptionsSelected = mia.getNumberOfOptionsSelected(pojo,strFieldId);
        ArrayList<String> optionsName = new ArrayList<>();
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        for(int x = 0; x<numberOfOptionsSelected;x++){
            optionsName.add(mia.getPicklistSelectedOptionsName(elementId,x+1));
        }
        mia.recordInputsFromPicklist(elementId,optionsName);
    }

    public void miaInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId) throws ParseException {
        lookForTheField(pojo, strFieldId);
        if (CheckboxObject.strFormatRegex != null && CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)) {
            String email = emailAddressInputs();
            mia.setTextToMia(pojo, strFieldId, email);
        } else {

            String inputs = "";
            boolean flag = false;
            if (CheckboxObject.strFormatRegex != null) {
                inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
                if (!inputs.equalsIgnoreCase("")) {
                    mia.setTextToMia(pojo, strFieldId, inputs);
                    flag = true;
                }
            }

            if (CheckboxObject.strFormatMask != null && flag != true) {
                inputs = FormatMask.formatDateTime(CheckboxObject.strFormatMask);
                if (inputs != null) {
                    mia.setTextToMia(pojo, strFieldId, inputs);
                    flag = true;
                }
            }
            if(flag!=true){
                if(mia.miaDataType().equalsIgnoreCase("NUMERIC")){
                    mia.numericInputs(pojo,strFieldId);
                } else if (mia.miaDataType().equalsIgnoreCase("ALPHA_NUMERIC")) {
                    mia.alphaNumericInputs(pojo,strFieldId);
                }else if (mia.miaDataType().equalsIgnoreCase("ALPHA")) {
                    mia.alphaInputs(pojo,strFieldId);
                }
            }
        }
    }

    public String getListFieldNameByCompletionErrors(){
        String fieldNames = "";
        try{
            int numberOfElements = driver.findElements(By.xpath(completionErrorsFieldNameList)).size();
            for(int x = 1; x <= numberOfElements; x++){
                String elem = stringReplace(completionErrorsFieldName,Integer.toString(x));
                WebElement element = stringToWebElement(elem);
                scrollElementIntoView(driver,element);
                fieldNames = element.getText();
                break;
            }
            return fieldNames;
        }catch (Exception e){
            Reporter.log("No more items in Completion errors.");
            return null;
        }
    }

    public void clickFieldNameInCompletionErrors(String strFieldName){
        String elem = stringReplace(completionErrorsFieldNameButton,strFieldName);
        WebElement element = stringToWebElement(elem);
        element.click();
    }

    public void hroInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId) throws ParseException {
        lookForTheField(pojo, strFieldId);
        if (CheckboxObject.strFormatRegex != null && CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)) {
            String email = emailAddressInputs();
            hro.setTextToHro(pojo, strFieldId, email);
        } else {

            String inputs = "";
            boolean flag = false;
            if (CheckboxObject.strFormatRegex != null) {
                inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
                if (!inputs.equalsIgnoreCase("")) {
                    hro.setTextToHro(pojo, strFieldId, inputs);
                    flag = true;
                }
            }

            if (CheckboxObject.strFormatMask != null && flag != true) {
                inputs = FormatMask.formatDateTime(CheckboxObject.strFormatMask);
                if (inputs != null) {
                    hro.setTextToHro(pojo, strFieldId, inputs);
                    flag = true;
                }
            }
            if(flag!=true&&CheckboxObject.strFormatRegex!=null){
                FormatRegex.RegexType regex = FormatRegex.getRegexType(CheckboxObject.strFormatRegex);
                if(regex.equals(FormatRegex.RegexType.ONLY_NUMBERS)){
                    inputs = hro.numericInputs(pojo,strFieldId, InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
                    hro.setTextToHro(pojo, strFieldId, inputs);
                } else if (regex.equals(FormatRegex.RegexType.ALPHANUMERIC)) {
                    inputs = hro.alphaNumericInputs(pojo,strFieldId,InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
                    hro.setTextToHro(pojo, strFieldId, inputs);
                }else if (regex.equals(FormatRegex.RegexType.ONLY_LETTERS)) {
                    inputs =  hro.alphaInputs(pojo,strFieldId,InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
                    hro.setTextToHro(pojo, strFieldId, inputs);
                }
            }else if(flag!=true){
                if(CheckboxObject.strDataTypeNew.equalsIgnoreCase("NUMERIC")){
                    inputs = hro.numericInputs(pojo,strFieldId,3);
                    hro.setTextToHro(pojo, strFieldId, inputs);
                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA_NUMERIC")) {
                    inputs = hro.alphaNumericInputs(pojo,strFieldId,3);
                    hro.setTextToHro(pojo, strFieldId, inputs);
                }
            }
        }
    }

    public boolean getCheckboxRulesForMinimumAndMaximumInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&isFieldIdCheckBox(pojo,strFieldId)){
                if(fields.getResponses()!=null){
                    if(fields.getResponses().getMinimum()!=0){
                        CheckboxObject.mandatory = fields.getMandatory();
                        CheckboxObject.minimum = fields.getResponses().getMinimum();
                        CheckboxObject.checkboxName = fields.getName();
                    }

                    if(fields.getResponses().getMaximum()!=0){
                        CheckboxObject.mandatory = fields.getMandatory();
                        CheckboxObject.maximum = fields.getResponses().getMaximum();
                        CheckboxObject.checkboxName = fields.getName();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static int clickWithinMinimumMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, int minInput, int maxInput, String strObjectElementId, int elementCountInACheckbox){
        String[] gen = minMaximumGeneratedInputs(pojo,minInput,maxInput,elementCountInACheckbox);
        gen = adjustInputIfAlreadySelected(pojo,gen);
        recordInputsFromCheckbox(strObjectElementId,gen);
        System.out.println(CheckboxObject.checkboxInputs);
        clickElementHasValue(gen,strObjectElementId);
        return gen.length;
    }

    public static void recordInputsFromCheckbox(String strElementId, String[] strHasValue){
        CheckboxObject.checkboxInputs.add(strElementId);
        CheckboxObject.checkboxInputs.addAll(Arrays.asList(strHasValue));
    }

    public static void clickElementHasValue(String[] gen, String strObjectElementId){
        String elem;
        WebElement element;
        for (String s : gen) {
            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator, strObjectElementId, s);
            scrollElementIntoView(driver, stringToWebElement(stringReplace(checkboxLocator, strObjectElementId)));
            element = stringToWebElement(elem);
            scrollElementIntoView(driver, element);
            Reporter.log("Click checkbox number: " + s);
            if (!element.isSelected()) {
                click(element);
            }
        }
    }

    public static String [] minMaximumGeneratedInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, int min, int max, int elementCount){
        Set<String> generated = new LinkedHashSet<String>();
        Random rng = new Random();
        if(max==1&& isCheckboxThatHasRoutingRules(pojo,CheckboxObject.strFieldId)){
            generated.add(getHasValueCheckboxThatHasRoutingRulesIfMax1(pojo,CheckboxObject.strFieldId,max));
        }else{
            if(min != 0){
                while (generated.size() < min)
                {
                    String next = Integer.toString(rng.nextInt(elementCount) + 1);
                    generated.add(next);
                }
            }else{
                while (generated.size() < max)
                {
                    String next = Integer.toString(rng.nextInt(elementCount) + 1);
                    generated.add(next);
                }
            }
        }
        return generated.toArray(new String[generated.size()]);
    }

    public static String getHasValueCheckboxThatHasRoutingRulesIfMax1(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId, int max){
        String hasValue="";
        if(max == 1){
            for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getConditions()!=null){
                    for (com.Formic.OF2.utils.Pojo.Condition condition : routing.getConditions()
                    ) {
                        if(condition.getWhenField().equalsIgnoreCase(fieldId)){
                            if(condition.getAction().equalsIgnoreCase("ENABLE")){
                                hasValue = condition.getHasValue();
                            }
                        }
                    }
                }
            }
        }
        return hasValue;
    }

    public static boolean isCheckboxThatHasRoutingRules(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        boolean hasValue=false;
        for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (com.Formic.OF2.utils.Pojo.Condition condition : routing.getConditions()
                ) {
                    if(condition.getWhenField().equalsIgnoreCase(fieldId)){
                        if(condition.getAction().equalsIgnoreCase("ENABLE")){
                            hasValue = true;
                        }
                    }
                }
            }
        }
        return hasValue;
    }

    /***
     * This method is created to adjust the set of inputs for a checkbox.
     * The reason is if the Checkbox has a routing rule, this will affect the inputs.
     * We need to include the item on the checkbox that is already selected.
     * For example, generated input for hasValue is 1 and 2, the maximum input for the checkbox is only 2.
     * Then we have 3 is already selected (Selected to enable some checkbox in routing rule). The selected outcome will be 1 and 3, we cant click number 2 because of the rule maximum inputs 2.
     * This will affect the assertion if we validate the inputs for the said checkbox if 1 and 2 isn't selected.
     * @param pojo is the FormContent
     * @param gen is the set of inputs that will be applied to the checkbox.
     * @return gen will be the updated inputs that will include the checkbox that is already selected from the beginning.
     */
    public static String [] adjustInputIfAlreadySelected(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String[] gen){
        ArrayList<String> hasValueEnable = new ArrayList<>();
        int ctr = 0;
        hasValueEnable = getHasValueToEnableFields(pojo,CheckboxObject.strFieldId);
        if(!CheckboxObject.hasValueList.isEmpty()){
            if(!isHasValueEqualToGen(gen)){
                for (String g: hasValueEnable
                ) {
                    if(!g.equalsIgnoreCase(gen[ctr])){
                        gen[ctr] = CheckboxObject.hasValueList.get(0);
                    }
                    ctr++;
                }
            }
        }
        return gen;
    }

    public static ArrayList<String> getHasValueToEnableFields(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        ArrayList<String> result = new ArrayList<>();
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
            ) {
                if(conditions.getWhenField().equalsIgnoreCase(strFieldId)&&conditions.getAction().equalsIgnoreCase("enable")){
                    result.add(conditions.getHasValue());
                }
            }
        }
        return result;
    }

    public static boolean isHasValueEqualToGen(String[] gen){
        boolean result = false;
        for (String g: gen
        ) {
            for (String value: CheckboxObject.hasValueList
            ) {
                if (value.equalsIgnoreCase(g)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public void validateInputsAreCorrect(com.Formic.OF2.utils.Pojo.FormContentPojo pojo) throws InterruptedException {
        String strElementId;
        boolean isCheckboxMatrix = false;
        String strCheckBoxId = null;
        String strHroId = null;
        String strMiaId = null;
        String strPickListId = null;
        Thread.sleep(500);
        for (String value: CheckboxObject.checkboxInputs
        ) {
            strElementId = getObjectIdFromFieldId(pojo,value)==null? value : getObjectIdFromFieldId(pojo,value);
            if(isElementId(pojo,strElementId)){
                if(isElementIdCheckbox(pojo,strElementId)){
                    CheckboxObject.isCheckbox = true;
                    CheckboxObject.isMia = false;
                    CheckboxObject.isHro = false;
                    String fieldId = getFieldIdByObjectId(pojo,strElementId);
                    lookForTheField(pojo,fieldId);
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,fieldId)){
                        isCheckboxMatrix = true;
                        strCheckBoxId = fieldId;
                    }else{
                        isCheckboxMatrix = false;
                        strCheckBoxId = strElementId;
                    }
                } else if (isElementIdHro(pojo,strElementId)) {
                    CheckboxObject.isHro = true;
                    CheckboxObject.isCheckbox = false;
                    CheckboxObject.isMia = false;
                    CheckboxObject.isPicklist = false;
                    strHroId = strElementId;
                    String fieldId = getFieldIdByObjectId(pojo,strHroId);
                    lookForTheField(pojo,fieldId);
                } else if (isElementIdMia(pojo,strElementId)) {
                    CheckboxObject.isMia = true;
                    CheckboxObject.isCheckbox = false;
                    CheckboxObject.isHro = false;
                    CheckboxObject.isPicklist = false;
                    strMiaId = strElementId;
                    String fieldId = getFieldIdByObjectId(pojo,strMiaId);
                    lookForTheField(pojo,fieldId);
                }else if (isElementIdPicklist(pojo,strElementId)) {
                    CheckboxObject.isPicklist = true;
                    CheckboxObject.isMia = false;
                    CheckboxObject.isCheckbox = false;
                    CheckboxObject.isHro = false;
                    strPickListId = strElementId;
                    String fieldId = getFieldIdByObjectId(pojo,strPickListId);
                    lookForTheField(pojo,fieldId);
                    CheckboxObject.picklistOptionsCtr = 1;
                }
            }else{
                String text;
                if(CheckboxObject.isCheckbox){
                    String elem = isCheckboxMatrix? stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strCheckBoxId,value) : stringReplaceTwoValues(actionLocator,strCheckBoxId,value);
                    WebElement element = stringToWebElement(elem);
                    scrollElementIntoView(driver,element);
                    Reporter.log("Checkbox "+getFieldNameByElementId(pojo,strCheckBoxId)+" tickbox number: "+ value+" should be selected.");
                    Reporter.log(elem);
                    Assert.assertTrue(element.isSelected(),"Checkbox "+getFieldNameByElementId(pojo,strCheckBoxId)+ " failed because tickbox number: "+ value+" is not selected.");
                } else if (CheckboxObject.isHro) {
                    text = hro.getHroTextFromElementId(strHroId);
                    Reporter.log("Field name: "+getFieldNameByElementId(pojo,strHroId)+" Expected text: "+ value+" Actual text: "+text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                } else if (CheckboxObject.isMia) {
                    text = mia.getMiaTextFromElementId(strMiaId);
                    Reporter.log("Field name: "+getFieldNameByElementId(pojo,strMiaId)+" Expected text: "+ value+" Actual text: "+text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                } else if (CheckboxObject.isPicklist) {
                    text = mia.getPicklistSelectedOptionsName(strPickListId,CheckboxObject.picklistOptionsCtr);
                    Reporter.log("Field name: "+getFieldNameByElementId(pojo,strPickListId)+ " Expected text: "+ value+" Actual text: "+text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                    CheckboxObject.picklistOptionsCtr++;
                }
            }
        }
    }

    public void validateSavedInputsCheckbox() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectIdNavigation);
        sideMenuNavigation.clickSubmitButton();
        String strListFieldName;
        String fieldId;
        String strTypeName;
        String strElementId;
        do{
                strListFieldName = getListFieldNameByCompletionErrors();
                strElementId = getElementIdByFieldName(graphResponse,strListFieldName);
                fieldId = getFieldIdByObjectId(graphResponse,strElementId);
                strTypeName = getTypeNameByFieldId(graphResponse,fieldId);
                clickFieldNameInCompletionErrors(strListFieldName);

                if(strTypeName.equalsIgnoreCase("HandwritingRecognitionObject")){

                    FieldMetaData.getHroRules(graphResponse,fieldId);
                    lookForTheField(graphResponse,fieldId);
                    hroInputs(graphResponse,fieldId);

                }else if(strTypeName.equalsIgnoreCase("TickboxGroup")){
                    //Checkbox Matrix
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,fieldId);
                        ArrayList<String> numberOfOptions = CheckboxMatrix.checkboxMatrixOptionsCount(graphResponse,fieldId);
                        int numberOfItems = CheckboxMatrix.countNumberOfResponses(strElementId,numberOfOptions.size());
                        CheckboxMatrixV2.clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,numberOfOptions,numberOfItems);

                    }else{
                        //Checkbox
                        getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,fieldId);
                        int numberOfItems = countCheckboxItems(strElementId);
                        clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,strElementId,numberOfItems);
                    }

                }else if(strTypeName.equalsIgnoreCase("ManualImageAreaText")){

                    FieldMetaData.getMiaRules(graphResponse,fieldId);
                    lookForTheField(graphResponse,fieldId);
                    miaInputs(graphResponse,fieldId);

                }else if(strTypeName.equalsIgnoreCase("PickList")){

                    FieldMetaData.getMiaRules(graphResponse,fieldId);
                    picklistInputs(graphResponse,fieldId);

                }
                sleep(1000);
            }while (!getListFieldNameByCompletionErrors().isEmpty());
        sideMenuNavigation.clickSaveButton();
        String receipt = getProjectReceiptSave();
        clickContinueButton();
        clickSavedFormsButton();
        Reporter.log("<b>Receipt code:<b/> "+receipt);
        enterReceiptNumber(receipt);
        clickGoButton();
        System.out.println(receipt);
        validateInputsAreCorrect(graphResponse);
    }

    public static String getProjectReceiptSave(){
        try{
            String test = "//h2[contains(text(),'Saved')]/ancestor::main/p[contains(text(),'You can use the following receipt to recall this particular form at a later date.')]";
            WebElement elem = stringToWebElement(test);
            String text = elem.getText();
            String [] strReceipt;
            strReceipt = text.split(". ");
            return strReceipt[15];
        }catch (NoSuchElementException e){
            Reporter.log("Receipt not visible. "+e);
            return "";
        }
    }

    public static void assertWithinAcceptedInputs(String strName, String strObjectElementId){
        String elemValidationMessage = stringReplace(newValidationMessageUponSubmit,strObjectElementId);
        String elemCompilationErrors = stringReplace(validationMessageUponSubmitSideBar,strObjectElementId);
        Assert.assertFalse(isElementVisible(driver,elemValidationMessage),"The expected for : "+strName + " There shouldn't be any validation message displayed below the object.");
        Assert.assertFalse(isElementVisible(driver,elemCompilationErrors),"The expected for: "+strName + "Completion Errors isn't displayed. The actual is Completion errors is displayed.");
        Reporter.log("There shouldn't be any validation message displayed below the field: "+ strName);
        CheckboxObject.checkboxObjectDefaultValue();
    }

}
