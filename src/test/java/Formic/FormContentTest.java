package Formic;

import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormContentTest extends BaseUiTest {

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
        String scenarioName = "validateMandatoryFieldsWithoutInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateCheckboxMandatory();
    }

    @Test
    public void checkboxValidationEnableDisableFieldsCheckbox() throws Exception{
        String scenarioName = "checkboxValidationEnableDisableFieldsCheckbox";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateEnableDisableFieldsCheckbox();
    }


    @Test
    public void validateMinimumRequiredWhenSubmitButtonClicked() throws Exception{
        String scenarioName = "validateMinimumRequiredWhenSubmitButtonClicked";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateCheckboxMinimumValidationUponSubmit();
    }

    @Test
    public void checkboxValidationBeyondMaximumInputs() throws Exception{
        String scenarioName = "checkboxValidationBeyondMaximumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateCheckboxBeyondMaximumInputsUponSubmit();
    }

    @Test
    public void checkboxValidationWithinMaximumInputs() throws Exception{
        String scenarioName = "checkboxValidationWithinMaximumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateCheckboxMaximumInputsWithinLimitUponSubmit();
    }

    @Test
    public void checkboxValidationWithinMinimumInputs() throws Exception{
        String scenarioName = "checkboxValidationWithinMinimumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateCheckboxWithinMinimumInputsUponSubmit();
    }

    @Test
    public void checkboxValidationLessThanMinimumInputs() throws Exception{
        String scenarioName = "checkboxValidationLessThanMinimumInputs";
        homePage.selectProject(projectName,scenarioName);
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
        String scenarioName = "miaFormatValidation";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.miaFormatValidation();
    }

    @Test
    public void hroMaximumInputsValidation() throws Exception{
        //hro validation message
        String scenarioName = "hroMaximumInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.hroMaximumInputsValidation();
    }

    @Test
    public void miaMandatoryValidation() throws Exception{
        String scenarioName = "miaMandatoryValidation";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.miaMandatoryValidation();
    }

    @Test
    public void miaPicklistLessThanMinimumInputs() throws Exception{
        String scenarioName = "miaPicklistLessThanMinimumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.miaPicklistLessThanMinimumInputs();
    }

    @Test
    public void miaPicklistMoreThanMaximumInputs() throws Exception{
        String scenarioName = "miaPicklistMoreThanMaximumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.miaPicklistMoreThanMaximumInputs();
    }

    @Test
    public void miaPicklistWithinMinimumMaximumInputs() throws Exception{
        String scenarioName = "miaPicklistWithinMinimumMaximumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.miaPicklistWithinMinimumMaximumInputs();
    }

    @Test
    public void validateSubmittedInputsCheckbox() throws Exception {
        String scenarioName = "validateSubmittedInputsCheckbox";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateSubmittedInputsCheckbox();
    }

    @Test
    public void validateSavedInputsCheckbox() throws Exception {
        String scenarioName = "validateSavedInputsCheckbox";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateSavedInputsCheckbox();
    }


//    @Test
//    public void test() throws Exception {
//        homePage.selectProject(projectName);
//        checkBoxPage.test();
//    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPage.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }

}
