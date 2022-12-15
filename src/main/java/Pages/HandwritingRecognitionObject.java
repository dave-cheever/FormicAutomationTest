package Pages;

import Objects.CheckboxObject;
import Pojo.FormContentPojo;
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

    CheckboxObject check = new CheckboxObject();
    static String hroInputLocator = "//div[@data-object-id='$TEXT']/div/input";
    String hroValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div[2]";

    public void setTextToHro(FormContentPojo pojo, String strFieldId, String strText){

        String elementId = CheckBoxPage.getElementIdFromWhenFieldId(pojo,strFieldId);
        String elem = stringReplace(hroInputLocator,elementId);
        WebElement element = stringToWebElement(elem);
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

    public void validateHroMessage(FormContentPojo pojo, String strFieldId){
        String elementId = CheckBoxPage.getElementIdFromWhenFieldId(pojo,strFieldId);
        String elem = stringReplace(hroValidationMessageLocator,elementId);
        WebElement element = stringToWebElement(elem);
        //assert here
    }

    public boolean getHroRules(FormContentPojo pojo, String strFieldId){
        for (var fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&isFieldIdHro(pojo,strFieldId)){
                check.checkboxName = fields.getName();
                check.strFormatMask = fields.getFormatMask();
                check.strFormatRegex = fields.getFormatRegex();
                check.strDataTypeNew = hroDataType();
                return true;
            }
        }
        return false;
    }

    public  String hroDataType(){
        if(check.strFormatRegex!=null){
            String[]  str = check.strFormatRegex.split("]");
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
            return check.strDataTypeNew;
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
        String[]  str = check.strFormatRegex.split("]");
        String num = str[1].substring(str[1].indexOf(",")+1, str[1].lastIndexOf("}"));
        return Integer.parseInt(num);
    }

    public void numericInputsBeyondTheMaximumAllowed(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        int min = 0;
        int max = 9;
        int num =0;
        for (int x = 0; x<hroMaximumInputAllowed+1;x++){
            num = rnd.nextInt(max-min);
        }
        setTextToHro(pojo,strFieldId,Integer.toString(num));
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
        String num ="";
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
}
