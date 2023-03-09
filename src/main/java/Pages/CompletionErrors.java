package Pages;

import Pojo.FormContentPojo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CompletionErrors extends BasePage{


    public CompletionErrors(WebDriver driver) {
        super(driver);
    }

    String completionErrorValidationMessage = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";

    public void validateCompletionErrorMessage(String strFieldName, String strExpectedErrorMessage){
        String elem = stringReplace(completionErrorValidationMessage,strFieldName);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String actualValidationMessage = element.getText();
        Assert.assertEquals(actualValidationMessage,strExpectedErrorMessage,"The validation message differs from what is expected.");
    }

    public void validateCompletionErrorMessageHidden(String strFieldName){
        String elem = stringReplace(completionErrorValidationMessage,strFieldName);
        Assert.assertFalse(isElementVisible(driver,elem),"The expected for: "+strFieldName + "Completion Errors isn't displayed. The actual is Completion errors is displayed.");
    }

}
