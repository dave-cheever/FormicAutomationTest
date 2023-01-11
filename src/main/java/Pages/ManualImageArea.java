package Pages;

import Helpers.ScreenshotListener;
import Objects.CheckboxObject;
import Pojo.FormContentPojo;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Base64;

public class ManualImageArea extends BasePage{
    String miaInputLocator = "//div[@data-object-id='$TEXT']/div/textarea";
    String miaValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div[2]";
    String miaValidationMessageMandatoryLocator = "//div[@data-object-id='$TEXT']/div/div/div";
    String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";

    public ManualImageArea(WebDriver driver) {
        super(driver);
    }

    public void setTextToMia(FormContentPojo pojo, String strFieldId, String strText){
        String elementId = CheckBoxPage.getElementIdFromWhenFieldId(pojo,strFieldId);
        String elem = stringReplace(miaInputLocator,elementId);
        WebElement element = stringToWebElement(elem);
        System.out.println("Enter text for MIA: "+CheckboxObject.checkboxName+" Inputs: "+ strText);
        enterText(element,strText);
        recordInputsFromMia(strFieldId,strText);
    }

    public boolean isMiaTextAreaEmpty(FormContentPojo pojo, String strFieldId){
        boolean result=false;
        lookForTheField(pojo, strFieldId);
        String elementId = getElementIdFromWhenFieldId(pojo,strFieldId);
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
        String elementId = getElementIdFromWhenFieldId(pojo,strFieldId);
        String fieldName = getFieldName(pojo,strFieldId);
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
//                WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, fieldName);
//                scrollElementIntoView(driver,ValidationMessageSideMenu);
//                js.executeScript("window.scrollBy(0,350)", "");
//                Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field is mandatory.","The expected value is : Required field. "+ValidationMessageSideMenu.getText());
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
        //Screenshot
        recordScreenshot();
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void recordInputsFromMia(String strElementId, String strHroInputs){
        CheckboxObject.checkboxInputs.add(strElementId);
        CheckboxObject.checkboxInputs.add(strHroInputs);
    }

//    public void assertRequiredField(String strObjectElementId, String name){
//        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
//        scrollElementIntoView(driver,ValidationMessageSideMenu);
//        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(validationMessageLocator,strObjectElementId);
//        scrollElementIntoView(driver,validationMessageUnderCheckbox);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,350)", "");
//        Assert.assertEquals(ValidationMessageSideMenu.getText(),"Required field.","The expected value is : Required field. "+ValidationMessageSideMenu.getText());
//        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Required field. "+validationMessageUnderCheckbox.getText());
//        recordScreenshot();
//        CheckboxObject.checkboxObjectDefaultValue();
//    }

    public String getMiaTextFromElementId(String strElementId){
        String elem = stringReplace(miaInputLocator,strElementId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        return element.getAttribute("value");
    }

    public  boolean isFieldIdMia(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        outerLoop:
        for (var page: pojo.data.project.getPages()
        ) {
            for (var object: page.getObjects()
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
        for (var fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&isFieldIdMia(pojo,strFieldId)){
                CheckboxObject.mandatory = fields.getMandatory();
                CheckboxObject.checkboxName = fields.getName();
                return true;
            }
        }
        return false;
    }
}
