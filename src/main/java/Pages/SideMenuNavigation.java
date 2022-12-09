package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SideMenuNavigation extends BasePage{

    @FindBy(xpath = "//li/button/div[contains(text(),'Cancel')]")
    public WebElement cancelButtonLocator;

    @FindBy(xpath = "//li/button/div[contains(text(),'Submit')]")
    public WebElement submitButtonLocator;

    @FindBy(xpath = "//li/button/div[contains(text(),'Save')]")
    public WebElement saveButtonLocator;

    @FindBy(xpath = "//section[@aria-label='Form Error']")
    public WebElement navBarErrorMessage;


    public SideMenuNavigation(WebDriver driver) {
        super(driver);
    }

    public boolean cancelButtonVisible() throws Exception {
        try{
            driverWait.until(ExpectedConditions.visibilityOf(cancelButtonLocator));
        }catch (Exception e){
            throw new Exception("Cancel button wasn't visible");
        }
        return cancelButtonLocator.isDisplayed();
    }

    public boolean submitButtonVisible() throws Exception {
        try{
            driverWait.until(ExpectedConditions.visibilityOf(submitButtonLocator));
        }catch (Exception e){
            throw new Exception("submit button wasn't visible");
        }
        return submitButtonLocator.isDisplayed();
    }

    public boolean saveButtonVisible() throws Exception {
        try{
            driverWait.until(ExpectedConditions.visibilityOf(saveButtonLocator));
        }catch (Exception e){
            throw new Exception("save button wasn't visible");
        }
        return saveButtonLocator.isDisplayed();
    }

    public void clickSaveButton() throws Exception {
        try{
            saveButtonVisible();
            click(saveButtonLocator);
        }catch (Exception e){
            throw new Exception("save button wasn't visible");
        }
    }

    public boolean clickSubmitButton() throws Exception {
        try{
            sleep(1000);
            submitButtonVisible();
            click(submitButtonLocator);
            return true;
        }catch (Exception e){
            throw new Exception("Submit button wasn't visible");
        }
    }

    public void clickCancelButton() throws Exception {
        try{
            cancelButtonVisible();
            click(cancelButtonLocator);
        }catch (Exception e){
            throw new Exception("Cancel button wasn't visible");
        }
    }

    //Add this to detect if there's a problem during Submit or save
    public void navBarErrorMessageVisible() throws Exception {
        try{
            driverWait.until(ExpectedConditions.invisibilityOf(navBarErrorMessage));
        }catch (Exception e){
            throw new Exception("Error message visible: "+navBarErrorMessage.getText());
        }
    }


}
