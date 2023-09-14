package Formic.testcases;

import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseUiTest{

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    HomePage homePage;
    RegisterPage registerPage;

    @Ignore
    public void Verify_that_the_page_displays_error_message_for_invalid_username_or_password() throws Exception{
        CheckboxObject.testCaseId = 2310;
        CheckboxObject.scenarioName = "Verify that the page displays error message for invalid username or password";
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("Test123","password");
        try {
            assertEquals(loginPage.incorrectUsernameOrPasswordTextIsVisible(), "Invalid username or password", "The expected error message: "+loginPage.incorrectUsernameOrPasswordTextIsVisible() + " is not visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_error_message_for_invalid_username_or_password_for_valid_username_and_invalid_password() throws Exception {
        CheckboxObject.testCaseId = 2316;
        CheckboxObject.scenarioName = "Verify that the page displays error message for invalid username or password for valid username and invalid password";
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("dcheever1","password123");
        try {
            assertEquals(loginPage.incorrectUsernameOrPasswordTextIsVisible(), "Invalid username or password", "The expected error message: "+loginPage.incorrectUsernameOrPasswordTextIsVisible() + " is not visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_error_message_for_username_and_password_required() throws Exception {
        CheckboxObject.testCaseId = 2309;
        CheckboxObject.scenarioName = "Verify_that_the_page_displays_error_message_for_username_and_password_required";
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("","");
        try {
            assertEquals(loginPage.userNameRequiredTextIsVisible(), "The Username field is required.", "The expected error message: "+loginPage.userNameRequiredTextIsVisible() + " is not visible.");
            assertEquals(loginPage.passwordRequiredTextIsVisible(), "The Password field is required.", "The expected error message: "+ loginPage.passwordRequiredTextIsVisible() + " is not visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_Username_label() throws Exception {
        CheckboxObject.testCaseId = 2301;
        CheckboxObject.scenarioName = "Verify_that_the_page_displays_Username_label";
        headerNavigationBar.navigateToLoginPage();
        try {
            Assert.assertTrue(loginPage.userNameLabelVisible()," The username label is not visible. ");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_Username_textBox() throws Exception {
        CheckboxObject.testCaseId = 2302;
        CheckboxObject.scenarioName = "Verify_that_the_page_displays_Username_textBox";
        headerNavigationBar.navigateToLoginPage();
        try {
            Assert.assertTrue(loginPage.userNameTextBoxVisible()," The username text box is not visible. ");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_Password_label() throws Exception {
        CheckboxObject.testCaseId = 2303;
        CheckboxObject.scenarioName = "Verify_that_the_page_displays_Password_label";
        headerNavigationBar.navigateToLoginPage();
        try {
            Assert.assertTrue(loginPage.passwordLabelVisible()," The password label is not visible. ");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_Password_textBox() throws Exception {
        CheckboxObject.testCaseId = 2304;
        CheckboxObject.scenarioName = "Verify_that_the_page_displays_Password_textBox";
        headerNavigationBar.navigateToLoginPage();
        try {
            Assert.assertTrue(loginPage.passwordTextBoxVisible()," The password text box is not visible. ");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_remember_me_checkbox() throws Exception {
        CheckboxObject.testCaseId = 2305;
        CheckboxObject.scenarioName = "Verify_that_the_page_displays_remember_me_checkbox";
        headerNavigationBar.navigateToLoginPage();
        loginPage.rememberMeCheckBoxVisible();
        try {
            Assert.assertTrue(loginPage.rememberMeCheckBoxVisible()," The remember me checkbox is not visible. ");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_login_button() throws Exception {
        CheckboxObject.testCaseId = 2306;
        CheckboxObject.scenarioName = "Verify_that_the_page_displays_login_butto";
        headerNavigationBar.navigateToLoginPage();
        try {
            Assert.assertTrue(loginPage.loginButtonVisible()," The login button is not visible. ");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_cancel_button() throws Exception {
        CheckboxObject.testCaseId = 2307;
        CheckboxObject.scenarioName = "Verify_that_the_page_displays_cancel_butto";
        headerNavigationBar.navigateToLoginPage();
        loginPage.cancelButtonVisible();
        try {
            Assert.assertTrue(loginPage.cancelButtonVisible()," The cancel button is not visible. ");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_page_displays_register_button() throws Exception {
        CheckboxObject.testCaseId = 2308;
        CheckboxObject.scenarioName = "Verify that the user is redirected to the correct page after clicking register button";
        headerNavigationBar.navigateToLoginPage();
        try {
            Assert.assertTrue(loginPage.registerButtonVisible()," The register button is not visible. ");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Ignore
    public void Verify_that_the_user_is_redirected_to_the_correct_page_after_clicking_register_button() throws Exception {
        CheckboxObject.testCaseId = 2312;
        CheckboxObject.scenarioName = "Verify that the user is redirected to the correct page after clicking register button";
        headerNavigationBar.navigateToLoginPage();
        loginPage.clickRegisterButton();
        try {
            Assert.assertTrue(registerPage.isRegisterTextVisible(),"Page is not in register page.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }



    @Ignore
    public void Verify_that_remember_me_checkbox_in_login_page_works_as_intended() throws Exception {
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUserWithRememberMe("dcheever","Test123");
        headerNavigationBar.logoutTheUser();
        headerNavigationBar.navigateToLoginPage();
        Assert.assertTrue(loginPage.isUserNameTextBoxValue("dcheever"),"The username textbox is empty even if the remember me checkbox is checked prior to login.");
        Assert.assertTrue(loginPage.isPasswordTextBoxValue("Test123"));
        Assert.assertTrue(loginPage.isRememberMeChecked());
    }

    @Ignore
    public void Verify_that_the_user_is_redirected_to_the_correct_page_after_a_successful_login() throws Exception {
        CheckboxObject.testCaseId = 2311;
        CheckboxObject.scenarioName = "Verify that the user is redirected to the correct page after a successful login.";
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("dcheever","Test123");
        try {
            assertEquals( "dave",headerNavigationBar.getFullName(), "The expected full name: " + "Dave Cheever" + " is not the same as the actual: "+ headerNavigationBar.getFullName() );
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        headerNavigationBar = PageFactory.initElements(getDriver(), HeaderNavigationBar.class);
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        registerPage = PageFactory.initElements(getDriver(),RegisterPage.class);
    }
}


