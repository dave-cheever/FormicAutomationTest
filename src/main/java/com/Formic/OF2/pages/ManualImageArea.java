package com.Formic.OF2.pages;

import com.Formic.OF2.utils.*;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.test.BasePage;
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
    String miaSinglePickListInputLocator = "//div[@data-object-id='$TEXT']/div/div/div/input";
    String miaMultiPickListInputLocator = "//div[@data-object-id='$TEXT']/div/div/div/div";

    static String miaInputLocator = "//div[@data-object-id='$TEXT']/textarea";
    String miaTextAreaLocator = "//div[@data-object-id='$TEXT']/div/textarea";
    String miaValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div[1]";
    String miaValidationMessageMandatoryLocator = "//div[@data-object-id='$TEXT']/div/div/div";
    String miaValidationMessageMinimumMaximumLocator = "(//div[@data-object-id='$TEXT']/div/div)[2]";
    String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";
    String miaPicklistDropdownButton = "//div[@data-object-id='$TEXT']/div/div/button";
    String miaPicklistDropdownInput = "//div[@data-object-id='$TEXT']/div/div/input";
    String picklistNumberOfOptionsSelectedLocator = "//div[@data-object-id='$TEXT']/div/div/div/div";
    String picklistOptionsSelectedByNumberLocator = "(//div[@data-object-id='$TEXT']/div/div/div/div)[$NUM]";


    CompletionErrors comErrors = new CompletionErrors(driver);

    Config config = new Config(driver);
    public ManualImageArea(WebDriver driver) {
        super(driver);
    }

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
        System.out.println("Enter text for MIA: "+CheckboxObject.checkboxName+" Inputs: "+ strText);
        enterText(element,strText);
        recordInputsFromMia(strFieldId,strText);
    }

    public void assertDateTimeFormat(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        String format = CheckboxObject.strFormatMask.toUpperCase();
        format = format.replace("HH:MM:SS","HH:mm:ss");
        format = format.replace("HH:MM","HH:mm");
        format = format.replace("MM:SS","mm:ss");
        Assert.assertEquals(element.getText(),"Please follow date format: ("+format+").");
    }

    public void processMiaInputsForValidation(com.Formic.OF2.utils.Pojo.FormContentPojo graphResponse,String fieldId,boolean flag){
        if (CheckboxObject.strDataTypeNew != null&&flag!=true) {
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

    public void processMiaInputsForSubmitSave(com.Formic.OF2.utils.Pojo.FormContentPojo graphResponse,String fieldId){
        if (CheckboxObject.strDataTypeNew != null&&CheckboxObject.strFormatRegex!=null) {
            if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("NUMERIC")) {
                processNumericInputForSubmitSave(graphResponse, fieldId);
            } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA_NUMERIC")) {
                processAlphaNumericDataType(graphResponse, fieldId);
            } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA")) {
                processAlphaDataType(graphResponse, fieldId);
            } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("DATE_TIME")) {
                processDateTimeDataType(graphResponse,fieldId);
            }
        }
    }

    public void assertFormatMaskValidation(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        String format = CheckboxObject.strFormatMask.toUpperCase();
        Assert.assertEquals(element.getText(),"This field must match the following format: ("+format+").");
    }

    public  String miaDataType(){
        if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatMask!=null){
            String[]  str = CheckboxObject.strFormatRegex.split("]");
            if(str[0].contains("^[a-zA-Z0-9")){
                return "ALPHA_NUMERIC";
            } else if (str[0].contains("^[0-9")) {
                return "NUMERIC";
            }
            else if (str[0].contains("^[a-zA-Z")) {
                return "ALPHA";
            }else {
               return DataFormatting.dataFormat(CheckboxObject.strFormatMask);
            }
        }else{
            return CheckboxObject.strDataTypeNew;
        }
    }


    public void dateTimeInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws ParseException {
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat(CheckboxObject.strFormatMask.replace("-","/").replace("mm","MM").replace("m","M").replace("DD","dd").replace("hh","HH"));
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
        if(CheckboxObject.isMultiResponse){
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

    public void assertMiaMandatoryField(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String fieldName = getFieldName(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(CheckboxObject.mandatory){
            if(config.isFieldIdPickList(pojo,strFieldId)){
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
            assertNoValidationMessage(elementId,fieldName);
        }
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public void assertNoValidationMessage(String elementId, String fieldName) {
        String elementForValidation = stringReplace(miaValidationMessageLocator,elementId);
        String elementForTextArea = stringReplace(miaInputLocator,elementId);
        WebElement webElementTextArea = stringToWebElement(elementForTextArea);
        scrollElementIntoView(driver,webElementTextArea);
        Reporter.log("<b>No validation message should be displayed.</b>" );
        if(driver.findElements(By.xpath(elementForValidation)).size()!=0){
            WebElement webElementValidation = stringToWebElement(elementForValidation);
            Assert.assertTrue(webElementValidation.getText().equalsIgnoreCase("This field is mandatory."));
        }
        else{
            Assert.assertTrue(driver.findElements(By.xpath(elementForValidation)).size()==0,"No validation message should be displayed for "+ fieldName +".");

        }
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

    public void assertMiaValidationMessageNumeric(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must be a number.");
        Assert.assertEquals(element.getText(),"This field must be a number.","The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must be a number.");
    }

    public void assertMiaValidationMessageAlphaNumeric(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: (?"+getNumberOfUnderscore()+").");
        WebElement element;
        try {
            Assert.assertTrue(isElementPresentBy(By.xpath(elem)),"Validation message not visible.");
        } catch (NoSuchElementException e) {
            throw new Error("Element not found: " + e.getMessage());
        }
        element = stringToWebElement(elem);
        Assert.assertEquals(element.getText(),"This field must match the following format: ("+getNumberOfUnderscore()+").",
                "The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (?"+getNumberOfUnderscore()+").");
    }

    public void assertMiaValidationMessageAlphabet(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: ("+getNumberOfUnderscore()+").");
        Assert.assertEquals(element.getText(),"This field must match the following format: ("+getNumberOfUnderscore()+").",
                "The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (A"+getNumberOfUnderscore()+").");
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
            num = CheckboxObject.maximum;
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



    public void processNumericDataType(FormContentPojo graphResponse,  String strFieldId) {
        String inputs = alphaInputs(graphResponse, strFieldId, InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
        setTextToMia(graphResponse, strFieldId, inputs);
        if(CheckboxObject.strFormatMask!=null&&CheckboxObject.strFormatRegex!=null||CheckboxObject.strDataTypeNew.equalsIgnoreCase("NUMERIC")){
            assertMiaValidationMessageNumeric(graphResponse, strFieldId);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));
        }
    }

    public void processNumericInputForSubmitSave(FormContentPojo graphResponse,  String strFieldId) {
        String inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
        if(inputs!=""){
            setTextToMia(graphResponse, strFieldId, inputs);
        }


        assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));

    }

    public void processAlphaNumericDataType(FormContentPojo graphResponse,  String strFieldId) {
        String inputs = specialCharacterInputs(graphResponse, strFieldId);
        setTextToMia(graphResponse, strFieldId, inputs);
        if(CheckboxObject.strFormatMask!=null&&CheckboxObject.strFormatRegex!=null){
            assertMiaValidationMessageAlphaNumeric(graphResponse, strFieldId);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));
        }
    }

    public void processAlphaDataType(FormContentPojo graphResponse, String strFieldId) {
        String inputs = numericInputs(graphResponse, strFieldId, InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
        setTextToMia(graphResponse, strFieldId, inputs);
        if(CheckboxObject.strFormatMask!=null&&CheckboxObject.strFormatRegex!=null){
            assertMiaValidationMessageAlphabet(graphResponse, strFieldId);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));
        }

    }

    public void processDateTimeDataType(FormContentPojo graphResponse,String strFieldId) {
       String inputs =  FormatMask.formatDateTime(CheckboxObject.strFormatMask);
       setTextToMia(graphResponse, strFieldId, inputs);
       //Create assert for date time
    }

    public boolean isMinimumMaximumNotEmpty(){
        boolean result = false;
        if(CheckboxObject.minimum!=0&&CheckboxObject.maximum!=0){
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
        if(CheckboxObject.strFormatMask!=null){
            maxNumber = CheckboxObject.strFormatMask.length();
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
}
