package com.Formic.OF2.pages;
import com.Formic.OF2.utils.*;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
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

import static com.Formic.OF2.pages.CheckBoxPageV2.comp;
import static com.Formic.OF2.utils.FieldManager.getCheckBoxResponseMinValue;
import static com.Formic.OF2.utils.FieldManager.isMandatory;

public class CheckboxMatrixV2 extends BasePage {

    public CheckboxMatrixV2(WebDriver driver) {
        super(driver);
    }
    int projectId = Integer.parseInt(ConfigLoader.getProperty("test.CheckboxProjectId"));

    public SideMenuNavigation sideMenuNavigation = new SideMenuNavigation(driver);

    static String checkboxMatrixLocator = "//div[@data-object-id=\"$TEXT\"]/fieldset/label";
    static String checkboxMatrixElementToBeClickedLocator = "//input[@data-field-id=\"$TEXT\"][$NUM]";
    static String checkboxMatrixValidationMessage = "//h1[@id=\"dialog-title-13\"]/ancestor::div/following-sibling::div/div/div/div[2]";
    static String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";
    static String mandatoryFieldMessageLocator = "//div[@data-object-id='$TEXT']/div/div";
    static String fieldErrorsPopUpValidationMessage = "//h1[text()=\"Field Errors\"]/ancestor::div/following-sibling::div/div/div/div[text()=\"$TEXT\"]/following::div[1]";
    static String checkBoxMatrixSeeFieldErrorsButton = "//div[@data-object-id=\"$TEXT\"]/div/div";
    static String closeButton = "//h1[text()='Field Errors']/following::button";
    static String fieldSetLocator = "//input[@data-field-id=\"$TEXT\"][1]";
    static String checkboxMatrixCheckboxLocator = "//input[@data-field-id=\"$TEXT\"]";
    static String fieldErrorsElementCount = "//h1[text()=\"Field Errors\"]/ancestor::div/following-sibling::div/div/div/div[1]";
    static String newValidationMessageUponSubmit = "//div[@data-object-id='$TEXT']/div/div[1]";

    CheckBoxPageV2 checkBoxPage;
    static CheckboxObject checkboxObject;
    private static String checkboxName;
    private static boolean checkboxMandatory;

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
        assertRequiredField(graphResponse,objectId,fieldName,scenarioName);
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
        assertWithinMaximumInput(graphResponse,fieldId,max,checkboxName,checkboxMandatory,scenarioName);
    }

    public void checkboxWithinMaximumInputs(String fieldId,String max,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        assertWithinMaximumInput(graphResponse,fieldId,max,checkboxName,checkboxMandatory,scenarioName);
    }

    public void checkboxWithinMinimumInputs(String fieldId,String min,boolean mandatory, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        checkboxName = getFieldName(graphResponse,fieldId);
        checkboxMandatory = mandatory;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        assertWithinMinimumInput(graphResponse, fieldId,Integer.parseInt(min),checkboxName,checkboxMandatory,scenarioName);
    }

    public static void checkboxEnabledDisabledValidation(String objectId, String action, FormContentPojo pojo,String scenarioName) {
        String elem = stringReplace(fieldSetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String name = getFieldNameByElementId(pojo,objectId);

        if(action.equalsIgnoreCase("DISABLE")){
            Reporter.log("Field Name: "+name+" is expected to be DISABLED");
            Assert.assertFalse(element.isEnabled(), "field is not disabled " + name + " " + objectId);
            Reporter.log("Passed Routing");
        }else if(action.equalsIgnoreCase("ENABLE")){
            Reporter.log("Field Name: "+name+" is expected to be ENABLED");
            Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
            Reporter.log("Passed Routing");
        }
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

    public static void assertWithinMinimumInput(FormContentPojo pojo, String strFieldId, Integer min,String checkboxName, Boolean mandatory, String scenarioName) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        getCheckboxRulesForMinimumInputs(pojo,strFieldId);
        ArrayList<String> numberOfOptions = checkboxMatrixOptionsCount(pojo,strFieldId);
        int numberOfItems = countNumberOfResponses(strElementId,numberOfOptions.size());
        clickWithinMinimumInput(min,strFieldId,numberOfItems);
        clickSeeFieldErrorsByObjectId(elementId);
        if(mandatory){
            AssertMandatoryFields(pojo,strFieldId,min,scenarioName);
        }else{
            assertWithinAcceptedInputs(checkboxName,elementId,scenarioName);
        }
    }
    public static void assertWithinAcceptedInputs(String strName, String strObjectElementId,String scenarioName){
        String elemValidationMessage = stringReplace(fieldErrorsPopUpValidationMessage,strObjectElementId);
        String elemCompilationErrors = stringReplace(validationMessageUponSubmitSideBar,strObjectElementId);
        try{
            Assert.assertFalse(isElementVisible(driver,elemValidationMessage),"The expected for : "+strName + " There shouldn't be any validation message displayed below the object.");
            Assert.assertFalse(isElementVisible(driver,elemCompilationErrors),"The expected for: "+strName + "Completion Errors isn't displayed. The actual is Completion errors is displayed.");
            Reporter.log("There shouldn't be any validation message displayed below the field: "+ strName);
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public static void AssertMandatoryFields(FormContentPojo pojo, String strFieldId,Integer min, String scenarioName) throws Exception {
        lookForTheField(pojo,strFieldId);
        String fieldName = getFieldName(pojo,strFieldId);
        try{
            if(min==1||CheckboxObject.minimum==0){
                assertLessThanMinimumInput(pojo,strFieldId,min,fieldName,scenarioName);
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

    public static void assertMinimumConfig(FormContentPojo pojo, String strFieldId,String minValue,String fieldName, String scenarioName) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
            if(Integer.parseInt(minValue)==1){
                assertRequiredField(pojo,elementId,fieldName,scenarioName);
            }else {
                assertMinimumConfigNoInputs(elementId,fieldName,Integer.parseInt(minValue),scenarioName);
            }
    }

    public static void assertRequiredField(FormContentPojo pojo, String strObjectElementId, String name,String scenarioName){
        comp.validateCompletionErrorMessage(name,"Required field.");
        clickSeeFieldErrorsByObjectId(strObjectElementId);
        String optionName = getFieldName(pojo,getFieldIdByObjectId(pojo,strObjectElementId));
        String validationMessage = getValidationMessageByOptionName(optionName);
        clickSeeFieldErrorsByObjectId(strObjectElementId);
        try{
            Assert.assertEquals(validationMessage,"Required field.","The expected value is : Required field. "+validationMessage);
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

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

    public static void assertMinimumConfigNoInputs(String strObjectElementId, String name, Integer minimumInput,String scenarioName){
        String elem = stringReplace(validationMessageUponSubmitSideBar,name);
        By locator = By.xpath(elem);
        scrollElementIntoView(driver,locator);
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field requires a minimum of "+minimumInput+" responses.","The expected value is : This field requires a minimum of "+minimumInput+" responses.");
        clickSeeFieldErrorsByObjectId(strObjectElementId);
        String validationMessage = getValidationMessageByOptionName(name);
        try{
            Assert.assertEquals(validationMessage,"This field requires a minimum of "+minimumInput+" responses.","The expected value is : This field requires a minimum of "+minimumInput+" responses. but the actual message is "+validationMessage);
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public static void assertWithinMaximumInput(FormContentPojo pojo, String strFieldId, String max,String checkboxName,boolean checkboxMandatory, String scenarioName) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        getCheckboxRulesForMaximumInputs(pojo,strFieldId);
        ArrayList<String> numberOfOptions = checkboxMatrixOptionsCount(pojo,strFieldId);
        System.out.println("Number of options is: "+ numberOfOptions.size());
        int numberOfItems = countNumberOfResponses(strElementId,numberOfOptions.size());
        System.out.println("number of items is: "+numberOfItems);
        clickWithinMaximumInput(pojo,Integer.parseInt(max),numberOfOptions,numberOfItems);
        if(checkboxMandatory){
            assertThisFieldIsMandatory(pojo,strFieldId,scenarioName);
        }else {
            assertWithinAcceptedInputs(checkboxName,elementId,scenarioName);
        }
    }

    public static void assertLessThanMinimumInput(FormContentPojo pojo, String strFieldId,Integer min, String checkboxName,String scenarioName) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        ArrayList<String> numberOfOptions = checkboxMatrixOptionsCount(pojo,strFieldId);
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        int numberOfItems = countNumberOfResponses(strElementId,numberOfOptions.size());
        int numberOfInputs = clickLessThanMinimumInput(pojo,min,strFieldId,numberOfItems);
        if(min==1&&numberOfInputs==1||numberOfInputs==0){
            assertRequiredField(pojo,elementId,checkboxName,scenarioName);
        }else{
            assertRequiresMinimumOf(checkboxName,elementId,min,scenarioName);
        }
    }

    public static void assertRequiresMinimumOf(String name, String strObjectElementID, int minNumberOfInputs,String scenarioName){
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field requires a minimum of "+minNumberOfInputs+" responses.","The expected value is : This field requires a minimum of "+minNumberOfInputs+" responses.");
        clickSeeFieldErrorsByObjectId(strObjectElementID);
        String validationMessage = getValidationMessageByOptionName(name);
        try{
            Assert.assertEquals(validationMessage,"This field requires a minimum of "+minNumberOfInputs+" responses.","The expected value is : This field requires a minimum of "+minNumberOfInputs+" responses. but the actual message is "+validationMessage);
            clickCloseButton();
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

    public static int countNumberOfResponses(String strObjectId, int numberOfOptions){
        String elem = stringReplace(checkboxMatrixLocator,strObjectId);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elem)));
        int num = driver.findElements(By.xpath(elem)).size();
        return num - numberOfOptions;
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

    public static void assertThisFieldIsMandatory(FormContentPojo pojo, String strFieldId, String scenarioName){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(mandatoryFieldMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        clickSeeFieldErrorsByObjectId(elementId);
        try{
            Assert.assertEquals(element.getText(),"This field is mandatory.","The expected value is : This field is mandatory. "+element.getText());
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

    public static int clickWithinMinimumMaximumInput(FormContentPojo pojo,int minInput,int maxInput,ArrayList<String> strObjectFieldId,int elementCountInACheckbox){
        String[] gen = CheckBoxPageV2.minMaximumGeneratedInputs(pojo,minInput,maxInput,elementCountInACheckbox);
        gen = CheckBoxPageV2.adjustInputIfAlreadySelected(pojo,gen);
        for (String fieldId: strObjectFieldId
        ) {
            CheckBoxPageV2.recordInputsFromCheckbox(getObjectIdFromFieldId(pojo,fieldId),gen);
            System.out.println(CheckboxObject.checkboxInputs);
            clickElementHasValue(gen,fieldId);
        }
        return gen.length;
    }

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

}
