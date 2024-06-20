package com.Formic.OF2.pages;

import com.Formic.OF2.utils.*;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class HandwritingRecognitionObject extends BasePage {
    public HandwritingRecognitionObject(WebDriver driver) {
        super(driver);
    }

//    CheckBoxPage chk = new CheckBoxPage(1);
    int projectId = 137;
    private String strFormatRegex;
    private String strFormatMask;
    private static boolean isMandatory = false;
    private static String strFieldName;
    String emailRegEx = "^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$";
    public SideMenuNavigation sideMenuNavigation = new SideMenuNavigation(driver);
    static String hroInputLocator = "//div[@data-object-id='$TEXT']/input";
    static String hroValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div[1]";
    static String hroValidationMessageMandatoryLocator = "//div[@data-object-id='$TEXT']/div/div[1]";
    static CompletionErrors comp = new CompletionErrors(driver);

    public static void setTextToHro(FormContentPojo pojo, String strFieldId, String strText){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroInputLocator,elementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        Reporter.log("<b>Enter text for HRO: <b/>"+getFieldName(pojo,strFieldId)+"<b> Input: <b/>"+ strText);
        enterText(element,strText);
        recordInputsFromHro(elementId,strText);
    }

    public void assertInvalidDateTimeFormat(FormContentPojo pojo, String strFieldId,String scenarioName){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        String format = strFormatMask.toUpperCase();
        format = format.replace("HH:MM:SS","HH:mm:ss");
        format = format.replace("HH:MM","HH:mm");
        format = format.replace("MM:SS","mm:ss");

        try{
            Assert.assertEquals(element.getText(),"Please follow date format: ("+format+").");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void assertValidDateTimeFormat(FormContentPojo pojo, String strFieldId, String name, String scenarioName){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        try{
            if(isMandatory){
                WebElement element = stringToWebElement(elem);
                Assert.assertEquals(element.getText(),"This field is mandatory.","The HRO "+ name+" has a validation message of "+element.getText()+" instead of - This field is mandatory.");
            }else{
                Assert.assertTrue(driver.findElements(By.xpath(elem)).size()==0,"No validation message should be displayed for "+ name +".");
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

    public void assertDataFormattedString(FormContentPojo pojo, String strFieldId,String scenarioName){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);

        try{
            Assert.assertEquals(element.getText(),"Please follow date format: ("+strFormatMask+").");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void assertInvalidEmailFormat(FormContentPojo pojo, String strFieldId,String scenarioName){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        try{
            Assert.assertEquals(element.getText(),"this must be a valid email");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void assertValidEmailFormat(FormContentPojo pojo, String strFieldId, String name, String scenarioName){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);

        try{
            if(isMandatory){
                WebElement element = stringToWebElement(elem);
                Assert.assertEquals(element.getText(),"This field is mandatory.","The HRO "+ name+" has a validation message of "+element.getText()+" instead of - This field is mandatory.");
            }else{
                Assert.assertTrue(driver.findElements(By.xpath(elem)).size()==0,"No validation message should be displayed for "+ name +".");
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



    public void assertFormatMaskValidation(FormContentPojo pojo, String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        String format = CheckboxObject.strFormatMask.toUpperCase();
        Assert.assertEquals(element.getText(),"This field must match the following format: ("+format+").");
    }

    public void processNumericDataType(FormContentPojo graphResponse,  String strFieldId) {
        String inputs = alphaInputs(graphResponse, strFieldId, InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
        setTextToHro(graphResponse, strFieldId, inputs);
        if(CheckboxObject.strFormatMask!=null&&CheckboxObject.strFormatRegex!=null){
            assertHroValidationMessageNumericInvalid(graphResponse, strFieldId,"","");
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));
        }
    }

//    public void AssertNumericDataTypeInputs(FormContentPojo graphResponse,  String strFieldId) {
//
//        assertHroValidationMessageNumeric(graphResponse, strFieldId,"","");
//
//    }

    public void invalidNumericInput(FormContentPojo graphResponse,  String strFieldId, int max) {
        String inputs = alphaInputs(graphResponse, strFieldId, max);
        setTextToHro(graphResponse, strFieldId, inputs);
    }

    public void validNumericInput(FormContentPojo graphResponse,  String strFieldId, int max) {
        String inputs = numericInputs(graphResponse, strFieldId, max);
        setTextToHro(graphResponse, strFieldId, inputs);
    }

    public void processAlphaNumericDataType(FormContentPojo graphResponse,  String strFieldId) {
        String inputs = specialCharacterInputs(graphResponse, strFieldId);
        setTextToHro(graphResponse, strFieldId, inputs);
        if(CheckboxObject.strFormatMask!=null&&CheckboxObject.strFormatRegex!=null){
            assertHroValidationMessageAlphaNumeric(graphResponse, strFieldId,"","");
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));
        }
    }

    public void invalidAlphaNumericInput(FormContentPojo graphResponse,  String strFieldId) {
        String inputs = specialCharacterInputs(graphResponse, strFieldId);
        setTextToHro(graphResponse, strFieldId, inputs);
    }


    public void processAlphaDataType(FormContentPojo graphResponse, String strFieldId) {
        String inputs = numericInputs(graphResponse, strFieldId, InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
        setTextToHro(graphResponse, strFieldId, inputs);
        if(CheckboxObject.strFormatMask!=null&&CheckboxObject.strFormatRegex!=null){
            assertHroValidationMessageAlphabet(graphResponse, strFieldId);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));
        }

    }

    public void assertNoValidationMessage(String elementId, String fieldName) {
        String elementForValidation = stringReplace(hroValidationMessageLocator,elementId);
        String elementForTextArea = stringReplace(hroInputLocator,elementId);
        WebElement webElementTextArea = stringToWebElement(elementForTextArea);
        scrollElementIntoView(driver,webElementTextArea);
        Reporter.log("<b>No validation message should be displayed.</b>" );
        Assert.assertTrue(driver.findElements(By.xpath(elementForValidation)).size()==0,"No validation message should be displayed for "+ fieldName +".");
    }

    public void processDateTimeDataType(FormContentPojo graphResponse,String strFieldId) {
        String inputs =  FormatMask.formatDateTime(CheckboxObject.strFormatMask);
        setTextToHro(graphResponse, strFieldId, inputs);
    }

    public void InvalidDateTimeInputs(FormContentPojo graphResponse,String strFieldId) {
        setTextToHro(graphResponse, strFieldId, "!@#");
    }

    public void validDateTimeInputs(FormContentPojo graphResponse,String strFieldId) {
        String inputs =  FormatMask.getRandomDate(strFormatMask);
        setTextToHro(graphResponse, strFieldId, inputs);
    }

    public void InvalidDataFormatInputs(FormContentPojo graphResponse,String strFieldId) {
//        String inputs =  FormatMask.formatDateTime(strFormatMask);
        setTextToHro(graphResponse, strFieldId, "!@#");
    }

    public void validDataFormatInputs(FormContentPojo graphResponse,String strFieldId) {
        String inputs =  FormatRegex.generateFormattedString(strFormatRegex);
        setTextToHro(graphResponse, strFieldId, inputs);
    }

    public void invalidEmailInputs(FormContentPojo graphResponse,String strFieldId){
        setTextToHro(graphResponse, strFieldId, "email");
    }

    public void validEmailInputs(FormContentPojo graphResponse,String strFieldId){
        String email = emailAddressInputs();
        setTextToHro(graphResponse, strFieldId, email);
    }

    public static void recordInputsFromHro(String strElementId, String strHroInputs){
        CheckboxObject.checkboxInputs.add(strElementId);
        CheckboxObject.checkboxInputs.add(strHroInputs);
    }

    public String getHroTextFromElementId(String strElementId){
        String elem = stringReplace(hroInputLocator,strElementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        return element.getAttribute("value");
    }

    public void assertHroValidationMessageNumericInvalid(FormContentPojo pojo, String strFieldId,String name, String scenarioName){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must be a number.");

        try{
            Assert.assertEquals(element.getText(),"This field must be a number.","The HRO "+ name+" has a validation message of "+element.getText()+" instead of - This field must be a number.");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void assertHroValidationMessageNumericValid(FormContentPojo pojo, String strFieldId,String name, String scenarioName){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        scrollElementIntoView(driver,By.xpath(elem));
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must be a number.");
        try{
            if(isMandatory){
                WebElement element = stringToWebElement(elem);
                Assert.assertEquals(element.getText(),"This field is mandatory.","The HRO "+ name+" has a validation message of "+element.getText()+" instead of - This field is mandatory.");
            }else{
                Assert.assertTrue(driver.findElements(By.xpath(elem)).size()==0,"No validation message should be displayed for "+ name +".");
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



    public void assertHro(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        String elemMandatory = stringReplace(hroValidationMessageMandatoryLocator,elementId);
        String fieldName = getFieldName(pojo,strFieldId);
        if(CheckboxObject.mandatory){
            WebElement elementMandatory = stringToWebElement(elemMandatory);
            scrollElementIntoView(driver,elementMandatory);
            Assert.assertEquals(elementMandatory.getText(),"This field is mandatory.","Validation message incorrect for "+fieldName+". Expected: This field is mandatory. Actual: "+elementMandatory.getText());

        }else {
            Assert.assertTrue(driver.findElements(By.xpath(elem)).size()==0,"No validation message should be displayed for "+ fieldName +".");
        }
    }

    public static void assertHroMandatoryField(FormContentPojo pojo, String strFieldId) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String fieldName = getFieldName(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(CheckboxObject.mandatory){
            if(isHroEmpty(pojo,strFieldId)){
                //Side menu validation
                comp.validateCompletionErrorMessage(fieldName,"Required Field.");
                //Field validation
                WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(hroValidationMessageLocator,elementId);
                scrollElementIntoView(driver,validationMessageUnderCheckbox);
                js.executeScript("window.scrollBy(0,350)", "");
                Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Required field. "+validationMessageUnderCheckbox.getText());
            }else{
//                //Side menu validation
                WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(hroValidationMessageMandatoryLocator, fieldName);
                scrollElementIntoView(driver,ValidationMessageSideMenu);
                js.executeScript("window.scrollBy(0,350)", "");
                Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field is mandatory.","The expected value is : Required field. "+ValidationMessageSideMenu.getText());
                //Field validation
                Reporter.log("<b>Validation message should be: </b> This field is mandatory." );
                String element = stringReplace(hroValidationMessageMandatoryLocator,elementId);
                WebElement validationMessageLocator = stringToWebElement(element);
                scrollElementIntoView(driver,validationMessageLocator);
                Assert.assertEquals(validationMessageLocator.getText(),"This field is mandatory.", fieldName+"The expected validation message for "+fieldName+" was: This field is mandatory. but the actual message was: "+validationMessageLocator.getText());
            }
        }else {
            String elementForValidation = stringReplace(hroValidationMessageLocator,elementId);
            Reporter.log("<b>No validation message should be displayed.</b>" );
            Assert.assertTrue(driver.findElements(By.xpath(elementForValidation)).size()==0,"No validation message should be displayed for "+ fieldName +".");
        }
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void assertHroMandatoryFieldOnly(FormContentPojo pojo, String strFieldId,String scenarioName) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        //Field validation
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(hroValidationMessageLocator,elementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        js.executeScript("window.scrollBy(0,350)", "");
        try{
            Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Required field. "+validationMessageUnderCheckbox.getText());
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }


    }

    public static boolean isHroEmpty(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroInputLocator,elementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        if(element.getText().equalsIgnoreCase("")){
            result = true;
        }
        return result;
    }

    public void assertHroValidationMessageAlphaNumeric(FormContentPojo pojo, String strFieldId,String name, String scenarioName){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: ("+strFormatMask+").");
        WebElement element;
        try {
             Assert.assertTrue(isElementPresentBy(By.xpath(elem)),"Field: "+getFieldName(pojo, strFieldId)+" - Validation message not visible.");
        } catch (NoSuchElementException e) {
            throw new Error("Element not found: " + e.getMessage());
        }
        element = stringToWebElement(elem);

        try{
            Assert.assertEquals(element.getText(),"This field must match the following format: ("+strFormatMask+").",
                    "The HRO "+ name+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (?"+strFormatMask+").");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void assertHroValidationMessageDataFormat(FormContentPojo pojo, String strFieldId,String name, String scenarioName){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: ("+strFormatMask+").");
        WebElement element;
        try {
            Assert.assertTrue(isElementPresentBy(By.xpath(elem)),"Field: "+getFieldName(pojo, strFieldId)+" - Validation message not visible.");
        } catch (NoSuchElementException e) {
            throw new Error("Element not found: " + e.getMessage());
        }
        element = stringToWebElement(elem);

        try{
            Assert.assertEquals(element.getText(),"This field must match the following format: ("+strFormatMask+").",
                    "The HRO "+ name+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (?"+strFormatMask+").");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void assertHroValidationMessageDataFormatValid(FormContentPojo pojo, String strFieldId,String name, String scenarioName){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: ("+strFormatMask+").");
        try{
            if(isMandatory){
                WebElement element = stringToWebElement(elem);
                Assert.assertEquals(element.getText(),"This field is mandatory.","The HRO "+ name+" has a validation message of "+element.getText()+" instead of - This field is mandatory.");
            }else{
                Assert.assertTrue(driver.findElements(By.xpath(elem)).size()==0,"No validation message should be displayed for "+ name +".");
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



    public void assertHroValidationMessageAlphabet(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: ("+getNumberOfUnderscore()+").");
        Assert.assertEquals(element.getText(),"This field must match the following format: ("+getNumberOfUnderscore()+").",
                "The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (A"+getNumberOfUnderscore()+").");
    }


    public int enterWithinMaximumInput(FormContentPojo pojo,int maxInput,String strFieldId,int elementCountInACheckbox){
        String elem;
        WebElement element;
        Random rng = new Random();
        Set<String> generated = new LinkedHashSet<String>();
        //if the number of checkboxes available to a certain checkbox is equal to the amount of Max input, do not add+1 because this will trigger an
        //infinite loop in the while loop below
        while (generated.size() < maxInput)
        {
            String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
            generated.add(next);
        }
        String[] gen = generated.toArray(new String[generated.size()]);

        for(int x = 0; x<generated.size();x++){
//            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator,getElementIdFromWhenFieldId(pojo,strFieldId),gen[x]);
//            element = stringToWebElement(elem);
//            scrollElementIntoView(driver,element);
//            if(!element.isSelected()) {
//                click(element);
//            }
        }
        return generated.size();
    }

    public int identifyMaximumInputsByFieldId(){
        if(CheckboxObject.strFormatRegex!=null&&!CheckboxObject.strFormatRegex.equalsIgnoreCase("^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$")){
            String[]  str = CheckboxObject.strFormatRegex.split("]");
            String num = str[1].substring(str[1].indexOf(",")+1, str[1].lastIndexOf("}"));
            return Integer.parseInt(num);
        }
        return 0;
    }

    public String dateTimeInputs(FormContentPojo pojo, String strFieldId) throws ParseException {
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat(CheckboxObject.strFormatMask.replace("-","/").replace("mm","MM").replace("m","M").replace("DD","dd").replace("hh","HH"));
        Date randomDate = new Date((long)(Math.random() * (System.currentTimeMillis() + 1)));
        String randomDateTime = dateFormat.format(randomDate);
        return randomDateTime;
    }

    public void alphaInputsBeyondTheMaximumAllowed(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "abcxyz";
        String output ="";
        for (int x = 0; x<hroMaximumInputAllowed+1;x++){
            output = output + (chars.charAt(rnd.nextInt(chars.length())));
        }
        Reporter.log("<b>input text: </b>"+ output);
        setTextToHro(pojo,strFieldId,output);
    }

    public void alphaNumericInputsBeyondTheMaximumAllowed(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "123xyz";
        String output ="";
        for (int x = 0; x<hroMaximumInputAllowed+1;x++){
            output = output + (rnd.nextInt(chars.length()));
        }
        Reporter.log("<b>input text: </b>"+ output);
        setTextToHro(pojo,strFieldId,output);
    }

    public void numericInputsBeyondTheMaximumAllowed(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        int min = 1;
        int max = 9;
        String num ="";
        if(hroMaximumInputAllowed!=0){
            for (int x = 0; x<hroMaximumInputAllowed+1;x++){
                num = num + (rnd.nextInt(max-min));
            }
            Reporter.log("<b>input number: </b>"+ num);
            setTextToHro(pojo,strFieldId,num);
        }
    }


    //Create test for HRO Numeric *
    //Create test for HRO AlphaNumeric *
    //Create test for HRO Date Time *
    //Create test for HRO email *
    //Create test for HRO String Format *
    //Create test for HRO Alpha *
    //Create test for HRO Mandatory Fields *
    //Create test for HRO Maximum inputs allowed

    public void hroNumericInvalidInputsValidation(String fieldId, String mandatory, String name, String max, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        invalidNumericInput(graphResponse,fieldId, Integer.parseInt(max));
        assertHroValidationMessageNumericInvalid(graphResponse,fieldId,name,scenarioName);
    }

    public void hroNumericInputsValidation(String fieldId, String mandatory, String name, String max, String scenarioName) throws Exception {
        isMandatory = Boolean.parseBoolean(mandatory);
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        validNumericInput(graphResponse,fieldId, Integer.parseInt(max));
        assertHroValidationMessageNumericValid(graphResponse,fieldId,name,scenarioName);
    }


    public void hroDateTimeInvalidInputsValidation(String fieldId, String mandatory, String name,String formatMask, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        strFormatMask = formatMask;
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        InvalidDateTimeInputs(graphResponse,fieldId);
        assertInvalidDateTimeFormat(graphResponse,fieldId,scenarioName);
    }

    public void hroDateTimeValidInputsValidation(String fieldId, String mandatory, String name,String formatMask, String scenarioName) throws Exception {
        isMandatory = Boolean.parseBoolean(mandatory);
        RulesGraphql rules = new RulesGraphql();
        strFormatMask = formatMask;
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        validDateTimeInputs(graphResponse,fieldId);
        assertValidDateTimeFormat(graphResponse,fieldId,name,scenarioName);
    }

    public void hroDataFormatInputsValidation(String fieldId, String mandatory, String name, String formatMask, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        strFormatMask = formatMask;
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        InvalidDataFormatInputs(graphResponse,fieldId);
        assertHroValidationMessageDataFormat(graphResponse,fieldId,name,scenarioName);
    }

    public void hroDataFormatValidInputs(String fieldId, String mandatory, String name, String formatRegex, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        isMandatory = Boolean.parseBoolean(mandatory);
        strFormatRegex = formatRegex;
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse,fieldId);
        validDataFormatInputs(graphResponse,fieldId);
        assertHroValidationMessageDataFormatValid(graphResponse,fieldId,name,scenarioName);
    }

    public void hroEmailFormatInvalidInputsValidation(String fieldId, String mandatory, String name, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        isMandatory = Boolean.parseBoolean(mandatory);
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse, fieldId);
        invalidEmailInputs(graphResponse,fieldId);
        assertInvalidEmailFormat(graphResponse,fieldId,scenarioName);
    }

    public void hroEmailFormatInputsValidation(String fieldId, String mandatory, String name, String scenarioName) throws Exception {
        isMandatory = Boolean.parseBoolean(mandatory);
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse, fieldId);
        validEmailInputs(graphResponse,fieldId);
        assertValidEmailFormat(graphResponse,fieldId,name,scenarioName);
    }

    public void hroMandatoryValidation(String fieldId, String scenarioNam) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse = rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        lookForTheField(graphResponse, fieldId);
        assertHroMandatoryFieldOnly(graphResponse, fieldId,scenarioNam);
    }




    public void AssertHroFormatValidation(FormContentPojo graphResponse, String fieldId){
        lookForTheField(graphResponse,fieldId);
        if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
            String email = emailAddressInputs();
            setTextToHro(graphResponse,fieldId,email);
        }else {
            String inputs;
            boolean flag = false;
            if (CheckboxObject.strFormatRegex != null) {
                inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
                if (!inputs.equalsIgnoreCase("")) {
                    setTextToHro(graphResponse, fieldId, "!@#");
                    assertFormatMaskValidation(graphResponse,fieldId);
                    flag = true;
                }
            }

            if (CheckboxObject.strFormatMask!=null&&!flag){
                inputs = FormatMask.formatDateTime(CheckboxObject.strFormatMask);
                if(inputs!=null){
                    setTextToHro(graphResponse, fieldId, "test");
                    assertInvalidDateTimeFormat(graphResponse,fieldId,"");
                    flag = true;
                }
            }

            if (CheckboxObject.strDataTypeNew != null&&!flag) {
                if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("NUMERIC")) {
                    processNumericDataType(graphResponse, fieldId);
                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA_NUMERIC")) {
                    processAlphaNumericDataType(graphResponse, fieldId);
                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA")) {
                    processAlphaDataType(graphResponse, fieldId);
                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("DATE_TIME")) {
                    processDateTimeDataType(graphResponse,fieldId);
                }
            }
        }
    }
}
