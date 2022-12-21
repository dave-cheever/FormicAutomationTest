package Pages;

import Objects.CheckboxObject;
import Pojo.FormContentPojo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManualImageArea extends BasePage{
    String miaInputLocator = "//div[@data-object-id='$TEXT']/div/textarea";
    String miaValidationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div[2]";
    String miaValidationMessageMandatoryLocator = "//div[@data-object-id='$TEXT']/div/div/div";

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

    public static void recordInputsFromMia(String strElementId, String strHroInputs){
        CheckboxObject.checkboxInputs.add(strElementId);
        CheckboxObject.checkboxInputs.add(strHroInputs);
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
