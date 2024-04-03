package com.Formic.OF2.pages;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//label[@for='Username']")
    private WebElement usernameLabelLocator;

    @FindBy(xpath = "//label[@for='FullName']")
    private WebElement fullNameLabelLocator;

    @FindBy(xpath = "//label[@for='EmailAddress']")
    private WebElement emailLabelLocator;

    @FindBy(xpath = "//label[@for='Password']")
    private WebElement passwordLabelLocator;

    @FindBy(xpath = "//label[@for='ConfirmPassword']")
    private WebElement confirmPasswordLabelLocator;

    @FindBy(xpath = "//button[@value='register']")
    private WebElement registerButtonLocator;

    @FindBy(xpath = "//button[@value='cancel']")
    private WebElement cancelButtonLocator;

    @FindBy(xpath = "//h1[text()='Register']")
    private WebElement registerText;

    @FindBy(id = "Username")
    private WebElement usernameTextBoxLocator;

    @FindBy(id = "FullName")
    private WebElement fullNameTextBoxLocator;

    @FindBy(id = "EmailAddress")
    private WebElement emailAddressTextBoxLocator;

    @FindBy(id = "Password")
    private WebElement passwordTextBoxLocator;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextBoxLocator;

    @FindBy(xpath = "//li[contains(text(),'Username is required.')]")
    private WebElement usernameErrorMessageLocator;

    String usernameDuplicateErrorMessageLocator = "//li[text()=\"Username '$TEXT' already taken.\"]";

    @FindBy(xpath = "//li[text()='Email already taken.']")
    private WebElement emailDuplicateErrorMessageLocator;


    @FindBy(xpath = "//li[contains(text(),'Email is required.')]")
    private WebElement emailErrorMessageLocator;

    @FindBy(xpath = "//li[contains(text(),'Password is required.')]")
    private WebElement passwordErrorMessageLocator;

    @FindBy(xpath = "//li[contains(text(),'The ConfirmPassword field is required.')]")
    private WebElement confirmPasswordErrorMessageLocator;

    @FindBy(xpath = "//li[contains(text(),'Passwords do not match.')]")
    private WebElement passwordDoNotMatchErrorMessageLocator;

    public RegisterPage(WebDriver browserDriver){
        super(browserDriver);
    }

    public void registerNewUser(String username, String fullName, String email, String password, String confirmPassword){
        setUsername(username);
        setFullName(fullName);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        clickRegisterButton();
    }

    public boolean isRegisterTextVisible(){
        driverWait.until(ExpectedConditions.visibilityOf(registerText));
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(registerText)));
    }

    private void setUsername(String username){
        enterText(usernameTextBoxLocator,username);
    }

    private void setFullName(String fullName){
        enterText(fullNameTextBoxLocator,fullName);
    }

    private void setEmail(String email){
        enterText(emailAddressTextBoxLocator,email);
    }

    private void setPassword(String password){
        enterText(passwordTextBoxLocator,password);
    }

    private void setConfirmPassword(String confirmPassword){
        enterText(confirmPasswordTextBoxLocator,confirmPassword);
    }

    private void clickRegisterButton(){
        click(registerButtonLocator);
    }

    public void clickCancelButton(){
        click(cancelButtonLocator);
    }

    public void assertUserNameLabelVisible(String scenarioName){
        validateRegisterField(scenarioName,usernameLabelLocator);
    }

    public void assertUserNameTextBoxVisible(String scenarioName){
        validateRegisterField(scenarioName,usernameTextBoxLocator);
    }

    public void assertFullNameLabelVisible(String scenarioName){
        validateRegisterField(scenarioName,fullNameLabelLocator);
    }

    public void assertFullNameTextBoxVisible(String scenarioName){
        validateRegisterField(scenarioName,fullNameTextBoxLocator);
    }

    public void assertEmailLabelVisible(String scenarioName){
        validateRegisterField(scenarioName,emailLabelLocator);
    }

    public void assertEmailTextBoxVisible(String scenarioName){
        validateRegisterField(scenarioName,emailAddressTextBoxLocator);
    }

    public void assertPasswordLabelVisible(String scenarioName){
        validateRegisterField(scenarioName,passwordLabelLocator);
    }

    public void assertPasswordTextBoxVisible(String scenarioName){
        validateRegisterField(scenarioName,passwordTextBoxLocator);
    }

    public void assertConfirmPasswordLabelBoxVisible(String scenarioName){
        validateRegisterField(scenarioName,confirmPasswordLabelLocator);
    }

    public void assertConfirmPasswordTextBoxVisible(String scenarioName){
        validateRegisterField(scenarioName,confirmPasswordTextBoxLocator);
    }

    public void assertRegisterButtonVisible(String scenarioName){
        validateRegisterField(scenarioName,registerButtonLocator);
    }

    public void assertCancelButtonVisible(String scenarioName){
        validateRegisterField(scenarioName,cancelButtonLocator);
    }

    public void assertUserNameErrorMessageVisible(String scenarioName){
        validateRegisterField(scenarioName,usernameErrorMessageLocator);
    }

    public void assertEmailErrorMessageVisible(String scenarioName){
        validateRegisterField(scenarioName,emailErrorMessageLocator);
    }

    public void assertPasswordErrorMessageVisible(String scenarioName){
        validateRegisterField(scenarioName,passwordErrorMessageLocator);
    }

    public void assertConfirmPasswordErrorMessageVisible(String scenarioName){
        validateRegisterField(scenarioName,confirmPasswordErrorMessageLocator);
    }

    public void assertUserNameAlreadyTakenMessageVisible(String scenarioName,String username){
        validateRegisterField(scenarioName,username,usernameDuplicateErrorMessageLocator);
    }

    public void assertEmailAlreadyTakenMessageVisible(String scenarioName){
        validateRegisterField(scenarioName,emailDuplicateErrorMessageLocator);
    }

    public void assertPasswordDoesNotMatchMessageVisible(String scenarioName){
        validateRegisterField(scenarioName,passwordDoNotMatchErrorMessageLocator);
    }

    public void validateRegisterField(String scenarioName,WebElement elem) {
        try {
            isElementVisible(driver, driverWait.until(ExpectedConditions.visibilityOf(elem)));
        } catch (AssertionError assertionError) {
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }

    public void validateRegisterField(String scenarioName,String text, String elem) {
        WebElement element = stringReplaceAndConvertToWebElement(elem,text);
        try {
            isElementVisible(driver, driverWait.until(ExpectedConditions.visibilityOf(element)));
        } catch (AssertionError assertionError) {
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("<br><b>Failed test screenshot:</b> <a href='" + pathName + "'>Screenshot</a><br>");
            throw assertionError;
        }
    }
}
