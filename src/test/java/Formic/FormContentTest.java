package Formic;

import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class FormContentTest extends BaseUiTest {

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    HomePage homePage;
    CheckBoxPage checkBoxPage;
    String projectName = "DMC Test Checkbox"; // 137
//    String projectName = "MIA project"; // 147
//String projectName = "HRO Format"; //148
//    String projectName = "Medications with anti-libidinal properties"; //136
//    String projectName = "DMC Test 1"; //146
//    String projectName = "STEM Grant Application Form 2021 to 2022";
//    String projectName = "01 Children Primary"; //52
//    String projectName = "No validation Project"; //159


    @Test
    public void validateMandatoryFieldsWithoutInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateCheckboxMandatory();
    }

    @Test
    public void checkboxValidationEnableDisableFieldsCheckbox() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateEnableDisableFieldsCheckbox();
    }


    @Test
    public void validateMinimumRequiredWhenSubmitButtonClicked() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateCheckboxMinimumValidationUponSubmit();
    }

    @Test
    public void checkboxValidationBeyondMaximumInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateCheckboxBeyondMaximumInputsUponSubmit();
    }

    @Test
    public void checkboxValidationWithinMaximumInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateCheckboxMaximumInputsWithinLimitUponSubmit();
    }

    @Test
    public void checkboxValidationWithinMinimumInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateCheckboxWithinMinimumInputsUponSubmit();
    }

    @Test
    public void checkboxValidationLessThanMinimumInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateCheckboxLessThanMinimumInputsUponSubmit();
    }
//TODO: fix issue with this scneario
//    @Test
//    public void hroFormatValidation() throws Exception{
//        homePage.selectProject(projectName);
//        checkBoxPage.hroFormatValidation();
//    }

    @Test
    public void miaFormatValidation() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.miaFormatValidation();
    }

    @Test
    public void hroMaximumInputsValidation() throws Exception{
        //hro validation message
        homePage.selectProject(projectName);
        checkBoxPage.hroMaximumInputsValidation();
    }

    @Test
    public void miaMandatoryValidation() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.miaMandatoryValidation();
    }

    @Test
    public void miaPicklistLessThanMinimumInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.miaPicklistLessThanMinimumInputs();
    }

    @Test
    public void miaPicklistMoreThanMaximumInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.miaPicklistMoreThanMaximumInputs();
    }

    @Test
    public void miaPicklistWithinMinimumMaximumInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.miaPicklistWithinMinimumMaximumInputs();
    }



    @Test
    public void validateSubmittedInputsCheckbox() throws Exception {
        homePage.selectProject(projectName);
        checkBoxPage.validateSubmittedInputsCheckbox();
    }

    @Test
    public void validateSavedInputsCheckbox() throws Exception {
        homePage.selectProject(projectName);
        checkBoxPage.validateSavedInputsCheckbox();
    }

//    @Test
//    public void test() throws Exception {
//        homePage.selectProject(projectName);
//        checkBoxPage.test();
//    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        headerNavigationBar = PageFactory.initElements(getDriver(), HeaderNavigationBar.class);
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPage.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }

}
