package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage{

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

    public void loginUser(String username, String password){
        setUsername(username);
        setPassword(password);
        clickSubmitButton();
    }

    public String userNameRequiredTextIsVisible() throws Exception {
        return getElementText(errorUsernameIsRequiredText);
    }

    public String passwordRequiredTextIsVisible() throws Exception {
        return  getElementText(errorPasswordIsRequiredText);
    }

    public String incorrectUsernameOrPasswordTextIsVisible() throws Exception {
        return getElementText(errorInvalidUsernameOrPasswordText);
    }

}

