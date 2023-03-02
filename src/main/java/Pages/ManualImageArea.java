package Pages;

import Objects.CheckboxObject;
import Pojo.FormContentPojo;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ManualImageArea extends BasePage{
    String miaInputLocator = "//div[@data-object-id='$TEXT']/div/textarea";
    String miaValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div[2]";
    String miaValidationMessageMandatoryLocator = "//div[@data-object-id='$TEXT']/div/div/div";
    String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";

    public ManualImageArea(WebDriver driver) {
        super(driver);
    }

    public void setTextToMia(FormContentPojo pojo, String strFieldId, String strText){
        String elementId = CheckBoxPage.getObjectIdFromFieldId(pojo,strFieldId);
        String elem = stringReplace(miaInputLocator,elementId);
        WebElement element = stringToWebElement(elem);
        System.out.println("Enter text for MIA: "+CheckboxObject.checkboxName+" Inputs: "+ strText);
        enterText(element,strText);
        recordInputsFromMia(strFieldId,strText);
    }

    public  String miaDataType(){
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


    public void dateTimeInputs(FormContentPojo pojo, String strFieldId) throws ParseException {
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat(CheckboxObject.strFormatMask.replace("-","/").replace("mm","MM").replace("m","M").replace("DD","dd").replace("hh","HH"));
        Date randomDate = new Date((long)(Math.random() * (System.currentTimeMillis() + 1)));
        String randomDateTime = dateFormat.format(randomDate);
        setTextToMia(pojo,strFieldId,randomDateTime);
    }

    public boolean isMiaTextAreaEmpty(FormContentPojo pojo, String strFieldId){
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

    public void assertMiaField(FormContentPojo pojo, String strFieldId){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String fieldName = getFieldName(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(CheckboxObject.mandatory){
            if(isMiaTextAreaEmpty(pojo,strFieldId)){
                //Side menu validation
                WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, fieldName);
                scrollElementIntoView(driver,ValidationMessageSideMenu);
                js.executeScript("window.scrollBy(0,350)", "");
                Reporter.log("<b>Validation message should be: </b> Required field." );
                Assert.assertEquals(ValidationMessageSideMenu.getText(),"Required field.","The expected value is : Required field. "+ValidationMessageSideMenu.getText());
                //Field validation
                WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(miaValidationMessageLocator,elementId);
                scrollElementIntoView(driver,validationMessageUnderCheckbox);
                js.executeScript("window.scrollBy(0,350)", "");
                Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Required field. "+validationMessageUnderCheckbox.getText());
            }else{
//                //Side menu validation
                WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, fieldName);
                scrollElementIntoView(driver,ValidationMessageSideMenu);
                js.executeScript("window.scrollBy(0,350)", "");
                Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field is mandatory.","The expected value is : Required field. "+ValidationMessageSideMenu.getText());
                //Field validation
                Reporter.log("<b>Validation message should be: </b> This field is mandatory." );
                String element = stringReplace(miaValidationMessageMandatoryLocator,elementId);
                WebElement validationMessageLocator = stringToWebElement(element);
                scrollElementIntoView(driver,validationMessageLocator);
                Assert.assertEquals(validationMessageLocator.getText(),"This field is mandatory.", fieldName+"The expected validation message for "+fieldName+" was: This field is mandatory. but the actual message was: "+validationMessageLocator.getText());
            }
        }else {
            String elementForValidation = stringReplace(miaValidationMessageLocator,elementId);
            String elementForTextArea = stringReplace(miaInputLocator,elementId);
            WebElement webElementTextArea = stringToWebElement(elementForTextArea);
            scrollElementIntoView(driver,webElementTextArea);
            Reporter.log("<b>No validation message should be displayed.</b>" );
            Assert.assertTrue(driver.findElements(By.xpath(elementForValidation)).size()==0,"No validation message should be displayed for "+ fieldName +".");
        }
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void recordInputsFromMia(String strElementId, String strMiaInputs){
        CheckboxObject.checkboxInputs.add(strElementId);
        CheckboxObject.checkboxInputs.add(strMiaInputs);
    }

    public String getMiaTextFromElementId(String strElementId){
        String elem = stringReplace(miaInputLocator,strElementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        return element.getAttribute("value");
    }

    public  boolean isFieldIdMia(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        outerLoop:
        for (Pojo.Page page: pojo.data.project.getPages()
        ) {
            for (Pojo.Object object: page.getObjects()
            ) {
                if(object.getTypename()!=null&&object.getTypename().equalsIgnoreCase("ManualImageAreaText")){
                    if(object.getFieldId().equalsIgnoreCase(strFieldId)){
                        result=true;
                        break outerLoop;
                    }
                }
            }
        }
        return result;
    }

    public boolean getMiaRules(FormContentPojo pojo, String strFieldId){
        for (Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&isFieldIdMia(pojo,strFieldId)){
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

    public void alphaInputs(FormContentPojo pojo, String strFieldId){
        Random rnd = new Random();
        String chars = "abcxyz";
        String output ="";
        for (int x = 0; x<10;x++){
            output = output + (chars.charAt(rnd.nextInt(chars.length())));
        }
        Reporter.log("<b>input text: </b>"+ output);
        setTextToMia(pojo,strFieldId,output);
    }

    public void numericInputs(FormContentPojo pojo, String strFieldId){
        Random rnd = new Random();
        int min = 1;
        int max = 9;
        String num ="";
        for (int x = 0; x<10;x++){
            num = num + (rnd.nextInt(max-min));
        }
        Reporter.log("<b>input number: </b>"+ num);
        setTextToMia(pojo,strFieldId,num);
    }

    public void alphaNumericInputs(FormContentPojo pojo, String strFieldId){
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
