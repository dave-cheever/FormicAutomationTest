package Formic;

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

public class RegistrationTest extends BaseUiTest{

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    RegisterPage registerPage;
    HomePage homePage;


    @Test
    public void Verify_that_username_label_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.userNameLabelVisible()," Username label isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }

    }

    @Test
    public void Verify_that_username_text_box_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.userNameTextBoxVisible()," Username text box isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_full_name_label_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.fullNameLabelVisible()," Full name label isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_full_name_text_box_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.fullNameTextBoxVisible()," Full name text box isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_email_label_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.emailLabelVisible()," Email label  isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_email_text_box_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.emailTextBoxVisible()," Email text box isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_password_label_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.passwordLabelVisible()," Password label isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_password_text_box_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.passwordTextBoxVisible()," Password text box isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_confirm_password_label_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.confirmPasswordLabelVisible()," Confirm Password label isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_confirm_password_text_box_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.confirmPasswordTextBoxVisible()," Confirm Password text box isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_register_button_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.registerButtonVisible()," Register button isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_cancel_button_is_present_on_the_registration_page(){
        headerNavigationBar.navigateToRegisterPage();
        try {
            Assert.assertTrue(registerPage.cancelButtonVisible()," Cancel button isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_username_is_a_required_field(){
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        try {
            Assert.assertTrue(registerPage.usernameErrorMessageVisible()," Username required isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_email_is_a_required_field(){
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        try {
            Assert.assertTrue(registerPage.emailErrorMessageVisible()," Email required isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_password_is_a_required_field(){
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        try {
            Assert.assertTrue(registerPage.passwordErrorMessageVisible()," Password required isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_confirm_password_is_a_required_field(){
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        try {
            Assert.assertTrue(registerPage.confirmPasswordErrorMessageVisible()," Confirm Password required isn't visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_the_application_checks_for_existing_username(){
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("dcheever","TestFullName","dave@test.com","test","test");
        try {
            Assert.assertTrue(registerPage.usernameAlreadyTakenVisible("dcheever")," Error message for existing username isn't visible");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

    @Test
    public void Verify_that_the_application_checks_for_existing_email(){
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("dcheeverTest","TestFullName","davecheever21@gmail.com","test","test");
        try {
            Assert.assertTrue(registerPage.emailAlreadyTakenVisible()," Error message for existing email isn't visible");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

//    @Test
//    public void Verify_that_the_cancel_button_takes_the_user_back_to_the_previous_page(){
//        headerNavigationBar.navigateToRegisterPage();
//        registerPage.clickCancelButton();
//        try {
//            Assert.assertTrue(homePage.confirmMyProjectsIsVisible()," User wasn't redirected to the home page.");
//        } catch (AssertionError e) {
//            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
//        }
//    }

    @Test
    public void Verify_that_the_password_and_confirm_password_fields_match(){
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("dcheeverTest","TestFullName","davecheever21@gmail.com","test1","test2");
        try {
            Assert.assertTrue(registerPage.passwordDoesNotMatchErrorMessageVisible()," Error message for Passwords do not match not visible.");
        } catch (AssertionError e) {
            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
        }
    }

//    @Test
//    public void Verify_that_the_user_can_create_an_account_successfully(){
//        CheckboxObject.testCaseId = 2351;
//        CheckboxObject.scenarioName = "Verify that the user can create an account successfully.";
//        headerNavigationBar.navigateToRegisterPage();
//        registerPage.clickCancelButton();
//        try {
//            Assert.assertTrue(registerPage.confirmPasswordErrorMessageVisible()," User wasn't able to successfully create an account.");
//        } catch (AssertionError e) {
//            throw new AssertionError(CheckboxObject.errorMessage=" Test failed: " + e.getMessage());
//        }
//    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        headerNavigationBar = PageFactory.initElements(getDriver(), HeaderNavigationBar.class);
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        registerPage = PageFactory.initElements(getDriver(), RegisterPage.class);
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
    }
}
