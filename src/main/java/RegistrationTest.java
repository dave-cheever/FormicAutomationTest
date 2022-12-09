import Pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseUiTest{

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    RegisterPage registerPage;

    @Test
    public void ValidateUsernameIsRequired(){
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        Assert.assertTrue(registerPage.usernameErrorMessageVisible());
    }

    @Test
    public void ValidateEmailIsRequired() throws Exception {
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        Assert.assertTrue(registerPage.emailErrorMessageVisible());
    }

    @Test
    public void ValidatePasswordIsRequired() throws Exception {
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        Assert.assertTrue(registerPage.passwordErrorMessageVisible());
    }

    @Test
    public void ValidateConfirmPasswordIsRequired() throws Exception {
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        Assert.assertTrue(registerPage.confirmPasswordErrorMessageVisible());
    }

    @Test
    public void ValidatePasswordDoesNotMatchErrorMessage() throws Exception {
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("TestUsername","TestFullName","test@email.com","Test1","Test2");
        Assert.assertTrue(registerPage.passwordDoesNotMatchErrorMessageVisible());
    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        headerNavigationBar = PageFactory.initElements(getDriver(), HeaderNavigationBar.class);
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        registerPage = PageFactory.initElements(getDriver(), RegisterPage.class);
    }
}
