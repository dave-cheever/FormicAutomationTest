import Pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseUiTest{

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    HomePage homePage;

    @Test
    public void LoginUsingIncorrectUsername() throws Exception{
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("Test123","password");
        assertEquals(loginPage.incorrectUsernameOrPasswordTextIsVisible(), "Invalid username or password", "The expected error message: "+loginPage.incorrectUsernameOrPasswordTextIsVisible() + " is not visible.");
    }

    @Test
    public void LoginUsingIncorrectPassword() throws Exception {
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("Test123","password123");
        assertEquals(loginPage.incorrectUsernameOrPasswordTextIsVisible(), "Invalid username or password", "The expected error message: "+loginPage.incorrectUsernameOrPasswordTextIsVisible() + " is not visible.");
    }

    @Test
    public void VerifyIfUsernameIsRequired() throws Exception {
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("","password123");
        assertEquals(loginPage.userNameRequiredTextIsVisible(), "The Username field is required.", "The expected error message: "+loginPage.userNameRequiredTextIsVisible() + " is not visible.");
    }

    @Test
    public void VerifyIfPasswordIsRequired() throws Exception {
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("asdasdad","");
        assertEquals(loginPage.passwordRequiredTextIsVisible(), "The Password field is required.", "The expected error message: "+ loginPage.passwordRequiredTextIsVisible() + " is not visible.");
    }

    @Test
    public void NavigateToLoginPageAndBackToHomePage() throws Exception {
        headerNavigationBar.navigateToLoginPage();
        loginPage.navigateToHomePage();
        Assert.assertTrue(headerNavigationBar.loginButtonVisible());
    }

    @Test
    public void LoginUsingCorrectCredentialsThenLogout() throws Exception {
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("dcheever","Test123");
        assertEquals( "Dave Cheever",headerNavigationBar.getFullName(), "The expected full name: " + "Dave Cheever" + " is not the same as the actual: "+ headerNavigationBar.getFullName() );
        headerNavigationBar.logoutTheUser();
        Assert.assertTrue(headerNavigationBar.loginButtonVisible());
    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        headerNavigationBar = PageFactory.initElements(getDriver(), HeaderNavigationBar.class);
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
    }
}

