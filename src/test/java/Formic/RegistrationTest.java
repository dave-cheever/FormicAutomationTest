package Formic;

import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.pages.*;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
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
        String scenarioName = "Verify_that_username_label_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertUserNameLabelVisible(scenarioName);
    }

    @Test
    public void Verify_that_username_text_box_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_username_text_box_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertUserNameTextBoxVisible(scenarioName);
    }

    @Test
    public void Verify_that_full_name_label_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_full_name_label_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertFullNameLabelVisible(scenarioName);
    }

    @Test
    public void Verify_that_full_name_text_box_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_full_name_text_box_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertFullNameTextBoxVisible(scenarioName);
    }

    @Test
    public void Verify_that_email_label_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_email_label_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertEmailLabelVisible(scenarioName);
    }

    @Test
    public void Verify_that_email_text_box_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_email_text_box_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertEmailTextBoxVisible(scenarioName);
    }

    @Test
    public void Verify_that_password_label_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_password_label_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertPasswordLabelVisible(scenarioName);
    }

    @Test
    public void Verify_that_password_text_box_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_password_text_box_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertPasswordTextBoxVisible(scenarioName);
    }

    @Test
    public void Verify_that_confirm_password_label_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_confirm_password_label_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertConfirmPasswordLabelBoxVisible(scenarioName);
    }

    @Test
    public void Verify_that_confirm_password_text_box_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_confirm_password_text_box_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertConfirmPasswordTextBoxVisible(scenarioName);
    }

    @Test
    public void Verify_that_register_button_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_register_button_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertRegisterButtonVisible(scenarioName);
    }

    @Test
    public void Verify_that_cancel_button_is_present_on_the_registration_page(){
        String scenarioName = "Verify_that_cancel_button_is_present_on_the_registration_page";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.assertCancelButtonVisible(scenarioName);
    }

    @Test
    public void Verify_that_username_is_a_required_field(){
        String scenarioName = "Verify_that_username_is_a_required_field";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        registerPage.assertUserNameErrorMessageVisible(scenarioName);
    }

    @Test
    public void Verify_that_email_is_a_required_field(){
        String scenarioName = "Verify_that_email_is_a_required_field";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        registerPage.assertEmailErrorMessageVisible(scenarioName);
    }

    @Test
    public void Verify_that_password_is_a_required_field(){
        String scenarioName = "Verify_that_password_is_a_required_field";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        registerPage.assertPasswordErrorMessageVisible(scenarioName);
    }

    @Test
    public void Verify_that_confirm_password_is_a_required_field(){
        String scenarioName = "Verify_that_confirm_password_is_a_required_field";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("","","","","");
        registerPage.assertConfirmPasswordErrorMessageVisible(scenarioName);
    }

    @Test
    public void Verify_that_the_application_checks_for_existing_username(){
        String scenarioName = "Verify_that_the_application_checks_for_existing_username";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("dcheever","TestFullName","dave@test.com","test","test");
        registerPage.assertUserNameAlreadyTakenMessageVisible(scenarioName,"dcheever");
    }

    @Test
    public void Verify_that_the_application_checks_for_existing_email(){
        String scenarioName = "Verify_that_the_application_checks_for_existing_email";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("dcheeverTest","TestFullName","davecheever21@gmail.com","test","test");
        registerPage.assertEmailAlreadyTakenMessageVisible(scenarioName);
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
        String scenarioName = "Verify_that_the_password_and_confirm_password_fields_match";
        headerNavigationBar.navigateToRegisterPage();
        registerPage.registerNewUser("dcheeverTest","TestFullName","davecheever21@gmail.com","test1","test2");
        registerPage.assertPasswordDoesNotMatchMessageVisible(scenarioName);
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
