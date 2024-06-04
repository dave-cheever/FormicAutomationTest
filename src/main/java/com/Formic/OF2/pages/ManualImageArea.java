package com.Formic.OF2.pages;

import com.Formic.OF2.utils.*;
import com.Formic.OF2.utils.DataValidation;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;

public class ManualImageArea extends BasePage {
    int projectId = 137;
    String emailRegEx = "^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$";
    String miaSinglePickListInputLocator = "//div[@data-object-id='$TEXT']/div/div/input";
    String miaMultiPickListInputLocator = "//div[@data-object-id='$TEXT']/div/div/div";

    static String miaInputLocator = "//div[@data-object-id='$TEXT']/textarea";
    String miaTextAreaLocator = "//div[@data-object-id='$TEXT']/div/textarea";
    String miaValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div[1]";
    String miaValidationMessageMandatoryLocator = "//div[@data-object-id='$TEXT']/div/div/div";
    String miaValidationMessageMinimumMaximumLocator = "(//div[@data-object-id='$TEXT']/div/div)[2]";
    String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";
    String miaPicklistDropdownButton = "//div[@data-object-id='$TEXT']/div/div/button";
    String miaPicklistDropdownInput = "//div[@data-object-id='$TEXT']/div/div/input";
    String picklistNumberOfOptionsSelectedLocator = "//div[@data-object-id='$TEXT']/div/div/div/div";
    String picklistOptionsSelectedByNumberLocator = "(//div[@data-object-id='$TEXT']/div/div/div/div)[$NUM]";


    CompletionErrors comErrors = new CompletionErrors(driver);

    DataValidation dataValidation = new DataValidation(driver);
    public ManualImageArea(WebDriver driver) {
        super(driver);
    }
    public SideMenuNavigation sideMenuNavigation = new SideMenuNavigation(driver);

    private static boolean isMandatory;
    private static String strName;
    private static String strFormatMask;
    private static String strFormatRegex;
    private static String strDataType;
    private static String strDerivation;
    private static String strValidation;
    private static boolean isMultiResponse;
    private static String strMaximum;
    private static String strMinimum;


    public int getNumberOfOptionsSelected(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(picklistNumberOfOptionsSelectedLocator,elementId);
        return driver.findElements(By.xpath(elem)).size();
    }

    public String getPicklistSelectedOptionsName(String strElementId, int currentNumber){
        String elem = stringReplaceTwoValues(picklistOptionsSelectedByNumberLocator,strElementId,Integer.toString(currentNumber));
        WebElement element = stringToWebElement(elem);
        return element.getText();
    }

    public static void setTextToMia(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId, String strText){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaInputLocator,elementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        Reporter.log("Enter text for MIA: "+getFieldName(pojo,strFieldId)+" Inputs: "+ strText);
        enterText(element,strText);
        recordInputsFromMia(strFieldId,strText);
    }

    public void assertDateTimeFormat(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,String scenarioName){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
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

    public void processMiaInputsForValidation(com.Formic.OF2.utils.Pojo.FormContentPojo graphResponse,String fieldId,boolean flag,String scenarioName){
        if (strDataType != null&&flag!=true) {
            if (strDataType.equalsIgnoreCase("NUMERIC")) {
              processNumericDataType(graphResponse, fieldId,scenarioName);
            } else if (strDataType.equalsIgnoreCase("ALPHA_NUMERIC")) {
               processAlphaNumericDataType(graphResponse, fieldId,scenarioName);
            } else if (strDataType.equalsIgnoreCase("ALPHA")) {
               processAlphaDataType(graphResponse, fieldId,scenarioName);
            } else if (strDataType.equalsIgnoreCase("DATE_TIME")) {
                processDateTimeDataType(graphResponse,fieldId);
            }
        }
    }

    public void processMiaInputsForSubmitSave(com.Formic.OF2.utils.Pojo.FormContentPojo graphResponse,String fieldId,String scenarioName){
        if (strDataType != null&&strFormatRegex!=null) {
            if (strDataType.equalsIgnoreCase("NUMERIC")) {
                processNumericInputForSubmitSave(graphResponse, fieldId,scenarioName);
            } else if (strDataType.equalsIgnoreCase("ALPHA_NUMERIC")) {
                processAlphaNumericDataType(graphResponse, fieldId,scenarioName);
            } else if (strDataType.equalsIgnoreCase("ALPHA")) {
                processAlphaDataType(graphResponse, fieldId,scenarioName);
            } else if (strDataType.equalsIgnoreCase("DATE_TIME")) {
                processDateTimeDataType(graphResponse,fieldId);
            }
        }
    }

    public void assertFormatMaskValidation(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,String scenarioName){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        String format = strFormatMask.toUpperCase();
        try{
            Assert.assertEquals(element.getText(),"This field must match the following format: ("+format+").");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public  String miaDataType(){
        if(strFormatRegex!=null&&strFormatMask!=null){
            String[]  str = strFormatRegex.split("]");
            if(str[0].contains("^[a-zA-Z0-9")){
                return "ALPHA_NUMERIC";
            } else if (str[0].contains("^[0-9")) {
                return "NUMERIC";
            }
            else if (str[0].contains("^[a-zA-Z")) {
                return "ALPHA";
            }else {
               return DataFormatting.dataFormat(strFormatMask);
            }
        }else{
            return strDataType;
        }
    }


    public void dateTimeInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws ParseException {
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat(strFormatMask.replace("-","/").replace("mm","MM").replace("m","M").replace("DD","dd").replace("hh","HH"));
        Date randomDate = new Date((long)(Math.random() * (System.currentTimeMillis() + 1)));
        String randomDateTime = dateFormat.format(randomDate);
        setTextToMia(pojo,strFieldId,randomDateTime);
    }



    public void clickPicklistDropdownButton(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaPicklistDropdownButton,elementId);
        WebElement element = stringToWebElement(elem);
        click(element);
    }

    public boolean isMiaTextAreaEmpty(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        boolean result=false;
        lookForTheField(pojo, strFieldId);
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String element = stringReplace(miaInputLocator,elementId);
        WebElement elem = stringToWebElement(element);
        scrollElementIntoView(driver,elem);
        String text = elem.getText();
        if(text.isEmpty()){
            result = true;
        }
        return result;
    }

    public boolean isMiaPicklistEmpty(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        boolean result=false;
        lookForTheField(pojo, strFieldId);
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String element;
        WebElement elem;
        if(isMultiResponse){
             element = stringReplace(miaMultiPickListInputLocator,elementId);
             elem = stringToWebElement(element);
             scrollElementIntoView(driver,elem);
             if(driver.findElements(By.xpath(element)).size()!=0){
                 result = true;
             }
        }else{
             element = stringReplace(miaSinglePickListInputLocator,elementId);
             elem = stringToWebElement(element);
             scrollElementIntoView(driver,elem);
            String text = elem.getText();
            if(text.isEmpty()){
                result = true;
            }
        }
        return result;
    }

    public void assertMiaMandatoryField(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId, String scenarioName){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String fieldName = getFieldName(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(isMandatory){
            if(dataValidation.isFieldIdPickList(pojo,strFieldId)){
                if(isMiaPicklistEmpty(pojo,strFieldId)){
                    assertMiaPickListRequiredField(pojo,strFieldId);
                }else{
                    assertMiaPickListThisFieldIsMandatory(pojo,strFieldId);
                }
            }else{
                if(isMiaTextAreaEmpty(pojo,strFieldId)){
                    assertMiaTextAreaRequiredField(pojo,strFieldId);
                }else{
                    assertMiaTextAreaThisFieldIsMandatory(pojo,strFieldId);
                }
            }
        }else {
            assertNoValidationMessage(elementId,fieldName,scenarioName);
        }
        CheckboxObject.checkboxObjectDefaultValue();
    }





    public void assertMiaTextAreaThisFieldIsMandatory(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);

        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(miaValidationMessageMandatoryLocator,elementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        js.executeScript("window.scrollBy(0,350)", "");
        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"This field is mandatory.","The expected value is : This field is mandatory.. "+validationMessageUnderCheckbox.getText());
    }

    public void assertMiaTextAreaRequiredField(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);

        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(miaValidationMessageLocator,elementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        js.executeScript("window.scrollBy(0,350)", "");
        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Require field. "+validationMessageUnderCheckbox.getText());

    }

    public void assertMiaPickListThisFieldIsMandatory(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(miaValidationMessageMandatoryLocator,elementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        js.executeScript("window.scrollBy(0,350)", "");
        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"This field is mandatory.","The expected value is : This field is mandatory.. "+validationMessageUnderCheckbox.getText());
    }

    public void assertMiaPickListRequiredField(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(miaValidationMessageLocator,elementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        js.executeScript("window.scrollBy(0,350)", "");
        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Require field. "+validationMessageUnderCheckbox.getText());
    }

    public static void recordInputsFromMia(String strElementId, String strMiaInputs){
        CheckboxObject.checkboxInputs.add(strElementId);
        CheckboxObject.checkboxInputs.add(strMiaInputs);
    }



    public void assertMiaValidationMessageAlphaNumeric(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,String scenarioName){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: (?"+getNumberOfUnderscore()+").");
        WebElement element;
        try{
            Assert.assertTrue(isElementPresentBy(By.xpath(elem)),"Validation message not visible.");
            element = stringToWebElement(elem);
            Assert.assertEquals(element.getText(),"This field must match the following format: ("+getNumberOfUnderscore()+").",
                    "The HRO "+ strName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (?"+getNumberOfUnderscore()+").");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }

    }

    public void assertMiaValidationMessageAlphabet(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: ("+getNumberOfUnderscore()+").");
        Assert.assertEquals(element.getText(),"This field must match the following format: ("+getNumberOfUnderscore()+").",
                "The HRO "+ strName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (A"+getNumberOfUnderscore()+").");
    }

    public static void recordInputsFromPicklist(String strElementId, ArrayList<String> strPicklistInputs){
        CheckboxObject.checkboxInputs.add(strElementId);
        for (var options: strPicklistInputs
             ) {
            CheckboxObject.checkboxInputs.add(options);
        }
    }

    public String getMiaTextFromElementId(String strElementId){
        String elem = stringReplace(miaInputLocator,strElementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        return element.getAttribute("value");
    }

    public void addLessThanMinimumOptions(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,int minimum, String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaPicklistDropdownInput,elementId);
        WebElement element = stringToWebElement(elem);
        for(int x = 0; x < minimum-1;x++){
            clickPicklistDropdownButton(pojo,strFieldId);
            pressArrowDown(element);
            pressEnter(element);
        }
    }

    public void addMoreThanMaximumOptions(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,int maximum, String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaPicklistDropdownInput,elementId);
        WebElement element = stringToWebElement(elem);
        for(int x = 1; x <= maximum+1;x++){
            clickPicklistDropdownButton(pojo,strFieldId);
            pressArrowDown(element);
            pressEnter(element);
        }
    }

    public void addWithinMinimumMaximumOptions(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,int minimum, String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaPicklistDropdownInput,elementId);
        WebElement element = stringToWebElement(elem);
        int num = 0;
        if (minimum == 0) {
            num = Integer.parseInt(strMaximum);
        }
        else {
            num = minimum;
        }
        for(int x = 1; x <= num;x++){
            clickPicklistDropdownButton(pojo,strFieldId);
            pressArrowDown(element);
            pressEnter(element);
        }
    }

    public void validateLessThanTheMinimumRequired(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,int minimum,String strFieldId){
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageMinimumMaximumLocator,strElementId);
        WebElement element = stringToWebElement(elem);
        String validationMessage = element.getText();
        String fieldName = getFieldName(pojo,strFieldId);
        if(minimum==1){
            Assert.assertEquals(validationMessage,"Required field.");
            comErrors.validateCompletionErrorMessage(fieldName,"Required field.");
        }else {
            Assert.assertEquals(validationMessage,"This field requires a minimum of "+minimum+" responses.");
            comErrors.validateCompletionErrorMessage(fieldName,"This field requires a minimum of "+minimum+" responses.");
        }
    }

    public void validateMoreThanTheMaximumRequired(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,int maximum,String strFieldId){
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageMinimumMaximumLocator,strElementId);
        WebElement element = stringToWebElement(elem);
        String validationMessage = element.getText();
        Assert.assertEquals(validationMessage,"This field only allows a maximum of "+maximum+" responses.");
        String fieldName = getFieldName(pojo,strFieldId);
        comErrors.validateCompletionErrorMessage(fieldName,"This field only allows a maximum of "+maximum+" responses.");
    }

    public void validateWithinMinimumMaximumRequired(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId){
        String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageMinimumMaximumLocator,strElementId);
        String fieldName = getFieldName(pojo,strFieldId);
        if(isElementVisible(driver,elem)){
            WebElement validationMessage = stringToWebElement(elem);
            Assert.assertTrue(validationMessage.getText().equalsIgnoreCase("This field is mandatory."));
        }else{
            Assert.assertFalse(isElementVisible(driver,elem),"The expected for : "+fieldName + " There shouldn't be any validation message displayed below the field.");
        }
        comErrors.validateCompletionErrorMessageHidden(fieldName);
    }



    public void processNumericDataType(FormContentPojo graphResponse,  String strFieldId,String scenarioName) {
        String inputs = alphaInputs(graphResponse, strFieldId, InputLimitExtractor.extractInputLimit(strFormatRegex));
        setTextToMia(graphResponse, strFieldId, inputs);
        if(strFormatMask!=null&&strFormatRegex!=null||strDataType.equalsIgnoreCase("NUMERIC")){
            assertMiaValidationMessageNumeric(graphResponse, strFieldId,scenarioName);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId),scenarioName);
        }
    }

    public void assertNoValidationMessage(String elementId, String fieldName,String scenarioName) {
        String elementForValidation = stringReplace(miaValidationMessageLocator,elementId);
        String elementForTextArea = stringReplace(miaInputLocator,elementId);
        WebElement webElementTextArea = stringToWebElement(elementForTextArea);
        scrollElementIntoView(driver,webElementTextArea);
        Reporter.log("<b>No validation message should be displayed.</b>" );
        try{
            if(driver.findElements(By.xpath(elementForValidation)).size()!=0){
                WebElement webElementValidation = stringToWebElement(elementForValidation);
                Assert.assertTrue(webElementValidation.getText().equalsIgnoreCase("This field is mandatory."));
            }
            else{
                Assert.assertTrue(driver.findElements(By.xpath(elementForValidation)).size()==0,"No validation message should be displayed for "+ fieldName +".");
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

    public void assertMiaValidationMessageNumeric(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,String scenarioName){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must be a number.");
        try{
            Assert.assertEquals(element.getText(),"This field must be a number.","The HRO "+ strName+" has a validation message of "+element.getText()+" instead of - This field must be a number.");
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void processNumericInputForSubmitSave(FormContentPojo graphResponse,  String strFieldId, String scenarioName) {
        String inputs = FormatRegex.generateFormattedString(strFormatRegex);
        if(inputs!=""){
            setTextToMia(graphResponse, strFieldId, inputs);
        }
        assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId),scenarioName);
    }

    public void processAlphaNumericDataType(FormContentPojo graphResponse,  String strFieldId,String scenarioName) {
        String inputs = specialCharacterInputs(graphResponse, strFieldId);
        setTextToMia(graphResponse, strFieldId, inputs);
        if(strFormatMask!=null&&strFormatRegex!=null){
            assertMiaValidationMessageAlphaNumeric(graphResponse, strFieldId,scenarioName);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId),scenarioName);
        }

    }

    public void processAlphaDataType(FormContentPojo graphResponse, String strFieldId, String scenarioName) {
        String inputs = numericInputs(graphResponse, strFieldId, InputLimitExtractor.extractInputLimit(strFormatRegex));
        setTextToMia(graphResponse, strFieldId, inputs);
        if(strFormatMask!=null&&strFormatRegex!=null){
            assertMiaValidationMessageAlphabet(graphResponse, strFieldId);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId),scenarioName);
        }

    }

    public void processDateTimeDataType(FormContentPojo graphResponse,String strFieldId) {
       String inputs =  FormatMask.formatDateTime(strFormatMask);
       setTextToMia(graphResponse, strFieldId, inputs);
       //Create assert for date time
    }

    public boolean isMinimumMaximumNotEmpty(){
        boolean result = false;
        if(Integer.parseInt(strMinimum)!=0&&Integer.parseInt(strMaximum)!=0){
            result = true;
        }
        return result;
    }

    public void alphaInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        Random rnd = new Random();
        String chars = "abcxyz";
        String output ="";
        for (int x = 0; x<10;x++){
            output = output + (chars.charAt(rnd.nextInt(chars.length())));
        }
        Reporter.log("<b>input text: </b>"+ output);
        setTextToMia(pojo,strFieldId,output);
    }

    public void numericInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        Random rnd = new Random();
        int min = 1;
        int max = 9;
        String num ="";
        int maxNumber = 3;
        if(strFormatMask!=null){
            maxNumber = strFormatMask.length();
        }
        for (int x = 0; x<maxNumber;x++){
            num = num + (rnd.nextInt(max-min));
        }
        num = removeZeroAtTheBeginning(num);
        Reporter.log("<b>input number: </b>"+ num);
        setTextToMia(pojo,strFieldId,num);
    }

    public void alphaNumericInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        Random rnd = new Random();
        String chars = "123xyz";
        String output ="";
        for (int x = 0; x<10;x++){
            output = output + (rnd.nextInt(chars.length()));
        }
        Reporter.log("<b>input text: </b>"+ output);
        setTextToMia(pojo,strFieldId,output);
    }

    public void miaFormatValidation(String fieldId,String mandatory,String name, String formatMask, String formatRegex, String dataType, String derivation, String validation, String multiResponse, String max, String min, String scenarioName) throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        Reporter.log("Click submit button to show all required field validation message.");
        sideMenuNavigation.clickSubmitButton();
        isMandatory = Boolean.parseBoolean(mandatory);
        strName = name;
        strFormatMask = formatMask;
        strFormatRegex = formatRegex;
        strDataType = dataType;
        strDerivation = derivation;
        strValidation = validation;
        isMultiResponse = isMultiResponse;
        strMaximum = max;
        strMinimum = min;
        RoutingRules.enableDisabledFieldByFieldId(graphResponse,fieldId);
        AssertMiaFormatValidation(graphResponse,fieldId,formatMask,formatRegex,scenarioName);
    }

    public void AssertMiaFormatValidation(com.Formic.OF2.utils.Pojo.FormContentPojo graphResponse,String fieldId, String formatMask, String formatRegex, String scenarioName){
        lookForTheField(graphResponse,fieldId);
        if(formatRegex!=null&&formatRegex.equalsIgnoreCase(emailRegEx)){
            String email = emailAddressInputs();
            setTextToMia(graphResponse,fieldId,email);
        }else {
            String inputs;
            boolean flag = false;
            if (formatRegex != null) {
                inputs = FormatRegex.generateFormattedString(formatRegex);
                if (!inputs.equalsIgnoreCase("")) {
                    setTextToMia(graphResponse, fieldId, "!@#");
                    assertFormatMaskValidation(graphResponse,fieldId,scenarioName);
                    flag = true;
                }
            }

            if (formatMask!=null&&!flag){
                inputs = FormatMask.formatDateTime(formatMask);
                if(inputs!=null){
                    setTextToMia(graphResponse,fieldId,"test");
                    assertDateTimeFormat(graphResponse,fieldId,scenarioName);
                    flag = true;
                }
            }
            processMiaInputsForValidation(graphResponse,fieldId,flag,scenarioName);
        }
    }
}
