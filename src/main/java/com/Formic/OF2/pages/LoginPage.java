package com.Formic.OF2.pages;
import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {

    @FindBy(xpath = "//img[@class='logo']")
    public WebElement formicLogoLocator;

    @FindBy(xpath = "//label[@for='Username']")
    public WebElement usernameLabelLocator;

    @FindBy(xpath = "//label[@for='Password']")
    public WebElement passwordLabelLocator;

    @FindBy(id = "Username")
    public WebElement usernameInputLocator;

    @FindBy(id = "Password")
    public WebElement passwordInputLocator;

    @FindBy(xpath = "//label[@for='RememberLogin']")
    public WebElement rememberMeCheckBoxLocator;

    @FindBy(xpath = "//button[@value='login']")
    public WebElement loginButtonLocator;

    @FindBy(xpath = "//button[@value='cancel']")
    public WebElement cancelButtonLocator;

    @FindBy(xpath = "//button[@value='register']")
    public WebElement registerButtonLocator;

    @FindBy(xpath = "//li[contains(text(),'The Username field is required.')]")
    public WebElement errorUsernameIsRequiredText;

    @FindBy(xpath = "//li[contains(text(),'The Password field is required.')]")
    public WebElement errorPasswordIsRequiredText;

    @FindBy(xpath = "//li[contains(text(),'Invalid username or password')]")
    public WebElement errorInvalidUsernameOrPasswordText;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private void setUsername(String username){
        enterText(usernameInputLocator,username);
    }

    private void setPassword(String password){
        enterText(passwordInputLocator,password);
    }

    private void clickSubmitButton(){
        click(loginButtonLocator);
    }

    public void navigateToHomePage(){
        click(cancelButtonLocator);

    }

    public void clickRegisterButton(){
        click(registerButtonLocator);
    }

    public boolean userNameLabelVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(usernameLabelLocator)));
    }

    public boolean userNameTextBoxVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(usernameInputLocator)));
    }

    public boolean passwordLabelVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(passwordLabelLocator)));
    }

    public boolean passwordTextBoxVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(passwordInputLocator)));
    }

    public boolean rememberMeCheckBoxVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(rememberMeCheckBoxLocator)));
    }

    public boolean loginButtonVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(loginButtonLocator)));
    }

    public boolean cancelButtonVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(cancelButtonLocator)));
    }

    public boolean registerButtonVisible(){
        return isElementVisible(driver,driverWait.until(ExpectedConditions.visibilityOf(registerButtonLocator)));
    }

    public void loginUser(String username, String password){
        setUsername(username);
        setPassword(password);
        clickSubmitButton();
    }

    public void loginUserWithRememberMe(String username, String password){
        setUsername(username);
        setPassword(password);
        click(rememberMeCheckBoxLocator);
        clickSubmitButton();
    }

    public boolean isUserNameTextBoxValue(String strUserName){
        String userName = usernameInputLocator.getText();
        return userName.equalsIgnoreCase(strUserName) ? true : false;
    }

    public boolean isPasswordTextBoxValue(String strPassword){
        String password = passwordInputLocator.getText();
        return password.equalsIgnoreCase(strPassword) ? true : false;
    }

    public boolean isRememberMeChecked(){
        String checked = rememberMeCheckBoxLocator.getCssValue("value");
        return checked.equalsIgnoreCase("true") ? true : false;
    }

    public String userNameRequiredTextIsVisible() throws Exception {
        return getElementText(errorUsernameIsRequiredText);
    }

    public String passwordRequiredTextIsVisible() throws Exception {
        return  getElementText(errorPasswordIsRequiredText);
    }

    public String incorrectUsernameOrPasswordTextIsVisible() throws Exception {
        driverWait.until(ExpectedConditions.visibilityOf(errorInvalidUsernameOrPasswordText));
        return getElementText(errorInvalidUsernameOrPasswordText);
    }

}

