package Pages;

import Objects.CheckboxObject;
import Pojo.FormContentPojo;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;


public class HandwritingRecognitionObject extends BasePage{
    public HandwritingRecognitionObject(WebDriver driver) {
        super(driver);
    }


    static String hroInputLocator = "//div[@data-object-id='$TEXT']/div/input";
    String hroValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div[2]";
    String hroValidationMessageMandatoryLocator = "//div[@data-object-id='$TEXT']/div/div/div[1]";

    public void setTextToHro(FormContentPojo pojo, String strFieldId, String strText){
        String elementId = CheckBoxPage.getElementIdFromWhenFieldId(pojo,strFieldId);
        String elem = stringReplace(hroInputLocator,elementId);
        WebElement element = stringToWebElement(elem);
        System.out.println("Enter text for HRO: "+CheckboxObject.checkboxName+" Inputs: "+ strText);
        enterText(element,strText);
        recordInputsFromHro(strFieldId,strText);
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
        String elementId = CheckBoxPage.getElementIdFromWhenFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        Assert.assertEquals(element.getText(),"This field must be a number.","The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must be a number.");
    }

    public void assertHro(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getElementIdFromWhenFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        String elemMandatory = stringReplace(hroValidationMessageMandatoryLocator,elementId);
        if(CheckboxObject.mandatory){
            WebElement elementMandatory = stringToWebElement(elemMandatory);
            Assert.assertEquals(elementMandatory.getText(),"This field is mandatory.","Validation message incorrect. Expected");
        }else {
            Assert.assertTrue(driver.findElements(By.xpath(elem)).size()==0,"No validation message should be displayed. Validation message present: "+driver.findElement(By.xpath(elem)).getText());
        }

    }

    public void assertHroValidationMessageAlphaNumeric(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getElementIdFromWhenFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        Assert.assertEquals(element.getText(),"This field must match the following format: (?"+getNumberOfUnderscore()+").",
                "The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (?"+getNumberOfUnderscore()+").");
    }

    public void assertHroValidationMessageAlphabet(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getElementIdFromWhenFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        Assert.assertEquals(element.getText(),"This field must match the following format: (A"+getNumberOfUnderscore()+").",
                "The HRO "+ CheckboxObject.checkboxName+" has a validation message of "+element.getText()+" instead of - This field must match the following format: (A"+getNumberOfUnderscore()+").");
    }

    public String getNumberOfUnderscore(){
        int num = identifyMaximumInputsByFieldId();
        String underScore="";
        for(int x =1; x<num;x++){
            underScore = underScore+"_";
        }
        return underScore;
    }

    public boolean getHroRules(FormContentPojo pojo, String strFieldId){
        for (var fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&isFieldIdHro(pojo,strFieldId)){
                CheckboxObject.mandatory = fields.getMandatory();
                CheckboxObject.checkboxName = fields.getName();
                CheckboxObject.strFormatMask = fields.getFormatMask();
                CheckboxObject.strFormatRegex = fields.getFormatRegex();
                CheckboxObject.strDataTypeNew = fields.getDataTypeNew();
                return true;
            }
        }
        return false;
    }

    public  String hroDataType(){
        if(CheckboxObject.strFormatRegex!=null){
            String[]  str = CheckboxObject.strFormatRegex.split("]");
            if(str[0].contains("^[a-zA-Z0-9")){
                return "ALPHA_NUMERIC";
            } else if (str[0].contains("^[0-9")) {
                return "NUMERIC";
            }
            else if (str[0].contains("^[a-zA-Z")) {
                return "ALPHA";
            }else {
                return null;
            }
        }else{
            return CheckboxObject.strDataTypeNew;
        }
    }

    public  boolean isFieldIdHro(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        outerLoop:
        for (var page: pojo.data.project.getPages()
        ) {
            for (var object: page.getObjects()
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
        String[]  str = CheckboxObject.strFormatRegex.split("]");
        String num = str[1].substring(str[1].indexOf(",")+1, str[1].lastIndexOf("}"));
        return Integer.parseInt(num);
    }

    public void numericInputs(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        int min = 0;
        int max = 9;
        String num ="";
        for (int x = 0; x<hroMaximumInputAllowed;x++){
            num = num + Integer.toString(rnd.nextInt(max-min));
        }
        setTextToHro(pojo,strFieldId,num);
    }

    public void alphaNumericInputs(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "123xyz";
        String output ="";
        for (int x = 0; x<hroMaximumInputAllowed;x++){
            output = output + Integer.toString(rnd.nextInt(chars.length()));
        }
        setTextToHro(pojo,strFieldId,output);
    }

    public void specialCharacterInputs(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "!@#$%^&*()_+=<>/";
        String output ="";
        for (int x = 0; x<hroMaximumInputAllowed;x++){
            output = output + Integer.toString(rnd.nextInt(chars.length()));
        }
        setTextToHro(pojo,strFieldId,output);
    }

    public void alphaInputs(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "abcxyz";
        String output ="";
        for (int x = 0; x<hroMaximumInputAllowed;x++){
            output = output + Character.toString(chars.charAt(rnd.nextInt(chars.length())));
//            output = output + Integer.toString(rnd.nextInt(chars.length()));
        }
        setTextToHro(pojo,strFieldId,output);
    }

    public void alphaInputsBeyondTheMaximumAllowed(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "abcxyz";
        String output ="";
        for (int x = 0; x<hroMaximumInputAllowed+1;x++){
            output = output + Character.toString(chars.charAt(rnd.nextInt(chars.length())));
//            output = output + Integer.toString(rnd.nextInt(chars.length()));
        }
        setTextToHro(pojo,strFieldId,output);
    }

    public void alphaNumericInputsBeyondTheMaximumAllowed(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "123xyz";
        String output ="";
        for (int x = 0; x<hroMaximumInputAllowed+1;x++){
            output = output + Integer.toString(rnd.nextInt(chars.length()));
        }
        setTextToHro(pojo,strFieldId,output);
    }

    public void numericInputsBeyondTheMaximumAllowed(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        int min = 0;
        int max = 9;
        String num ="";
        for (int x = 0; x<hroMaximumInputAllowed+1;x++){
            num = num + Integer.toString(rnd.nextInt(max-min));
        }
        setTextToHro(pojo,strFieldId,num);
    }
}
