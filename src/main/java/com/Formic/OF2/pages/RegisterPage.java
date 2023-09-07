package com.Formic.OF2.pages;
import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public boolean userNameLabelVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(usernameLabelLocator)));
    }

    public boolean fullNameLabelVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(fullNameLabelLocator)));
    }

    public boolean emailLabelVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(emailLabelLocator)));
    }

    public boolean passwordLabelVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(passwordLabelLocator)));
    }

    public boolean confirmPasswordLabelVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(confirmPasswordLabelLocator)));
    }

    public boolean registerButtonVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(registerButtonLocator)));
    }

    public boolean cancelButtonVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(cancelButtonLocator)));
    }

    public boolean userNameTextBoxVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(usernameTextBoxLocator)));
    }

    public boolean fullNameTextBoxVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(fullNameTextBoxLocator)));
    }

    public boolean emailTextBoxVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(emailAddressTextBoxLocator)));
    }

    public boolean passwordTextBoxVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(passwordTextBoxLocator)));
    }

    public boolean confirmPasswordTextBoxVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(confirmPasswordTextBoxLocator)));
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

    public boolean usernameAlreadyTakenVisible(String userName){
        WebElement element = stringReplaceAndConvertToWebElement(usernameDuplicateErrorMessageLocator,userName);
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(element)));
    }

    public boolean emailAlreadyTakenVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(emailDuplicateErrorMessageLocator)));
    }

    private void clickRegisterButton(){
        click(registerButtonLocator);
    }

    public void clickCancelButton(){
        click(cancelButtonLocator);
    }

    public boolean usernameErrorMessageVisible(){
        return isElementPresent(usernameErrorMessageLocator);
    }

    public boolean emailErrorMessageVisible(){
        return isElementPresent(emailErrorMessageLocator);
    }

    public boolean passwordErrorMessageVisible(){
        return isElementPresent(passwordErrorMessageLocator);
    }

    public boolean confirmPasswordErrorMessageVisible(){
        return isElementPresent(confirmPasswordErrorMessageLocator);
    }

    public boolean passwordDoesNotMatchErrorMessageVisible(){
        return isElementPresent(passwordDoNotMatchErrorMessageLocator);
    }

}
