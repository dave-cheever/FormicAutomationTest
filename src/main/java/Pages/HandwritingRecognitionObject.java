package Pages;

import Helpers.DataFormatting;
import Helpers.FormatMask;
import Helpers.InputLimitExtractor;
import Objects.CheckboxObject;
import Pojo.FormContentPojo;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;


public class HandwritingRecognitionObject extends BasePage{
    public HandwritingRecognitionObject(WebDriver driver) {
        super(driver);
    }

//    CheckBoxPage chk = new CheckBoxPage(1);


    static String hroInputLocator = "//div[@data-object-id='$TEXT']/input";
    static String hroValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div[2]";
    static String hroValidationMessageMandatoryLocator = "//div[@data-object-id='$TEXT']/div/div[1]";
    static CompletionErrors comp = new CompletionErrors(driver);

    public void setTextToHro(FormContentPojo pojo, String strFieldId, String strText){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroInputLocator,elementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        System.out.println("Enter text for HRO: "+CheckboxObject.checkboxName+" Input: "+ strText);
        Reporter.log("<b>Enter text for HRO: <b/>"+CheckboxObject.checkboxName+"<b> Input: <b/>"+ strText);
        enterText(element,strText);
        recordInputsFromHro(elementId,strText);
    }

    public void assertDateTimeFormat(FormContentPojo pojo, String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        String format = CheckboxObject.strFormatMask.toUpperCase();
        format = format.replace("HH:MM:SS","HH:mm:ss");
        format = format.replace("HH:MM","HH:mm");
        format = format.replace("MM:SS","mm:ss");
        Assert.assertEquals(element.getText(),"Please follow date format: ("+format+").");
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
            assertHroValidationMessageNumeric(graphResponse, strFieldId);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));
        }
    }

    public void processAlphaNumericDataType(FormContentPojo graphResponse,  String strFieldId) {
        String inputs = specialCharacterInputs(graphResponse, strFieldId);
        setTextToHro(graphResponse, strFieldId, inputs);
        if(CheckboxObject.strFormatMask!=null&&CheckboxObject.strFormatRegex!=null){
            assertHroValidationMessageAlphaNumeric(graphResponse, strFieldId);
        }else {
            assertNoValidationMessage(getObjectIdFromFieldId(graphResponse,strFieldId),getFieldName(graphResponse,strFieldId));
        }
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
        //Create assert for date time
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

    public void assertHroValidationMessageNumeric(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must be a number.");
        Assert.assertEquals(element.getText(),"This field must be a number.","The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must be a number.");
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

    public void assertHroValidationMessageAlphaNumeric(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: ("+getNumberOfUnderscore()+").");
        WebElement element;
        try {
             Assert.assertTrue(isElementPresentBy(By.xpath(elem)),"Field: "+getFieldName(pojo, strFieldId)+" - Validation message not visible.");
        } catch (NoSuchElementException e) {
            throw new Error("Element not found: " + e.getMessage());
        }
        element = stringToWebElement(elem);
        Assert.assertEquals(element.getText(),"This field must match the following format: ("+getNumberOfUnderscore()+").",
                "The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (?"+getNumberOfUnderscore()+").");
    }

    public void assertHroValidationMessageAlphabet(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        Reporter.log("<b>Confirm correct validation message should be:</b> This field must match the following format: ("+getNumberOfUnderscore()+").");
        Assert.assertEquals(element.getText(),"This field must match the following format: ("+getNumberOfUnderscore()+").",
                "The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (A"+getNumberOfUnderscore()+").");
    }



    public boolean getHroRules(FormContentPojo pojo, String strFieldId){
        for (Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&isFieldIdHro(pojo,strFieldId)){
                CheckboxObject.mandatory = fields.getMandatory();
                CheckboxObject.checkboxName = fields.getName();
                CheckboxObject.strFormatMask = fields.getFormatMask();
                CheckboxObject.strFormatRegex = fields.getFormatRegex();
                CheckboxObject.strDataTypeNew = fields.getDataTypeNew();
                Reporter.log("<b>Mandatory: </b>"+CheckboxObject.mandatory);
                Reporter.log("<b>Field Name: </b>"+CheckboxObject.checkboxName);
                Reporter.log("<b>Format Mask: </b>"+CheckboxObject.strFormatMask);
                Reporter.log("<b>Format Regex: </b>"+ CheckboxObject.strFormatRegex);
                Reporter.log("<b>Data Type New: </b>"+CheckboxObject.strDataTypeNew);
                return true;
            }
        }
        return false;
    }

    public static boolean isFieldIdHro(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        outerLoop:
        for (Pojo.Page page: pojo.data.project.getPages()
        ) {
            for (Pojo.Object object: page.getObjects()
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
}
