import Objects.CheckboxObject;
import Pages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class FormContentTest extends BaseUiTest {

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    HomePage homePage;
    CheckBoxPage checkBoxPage;
//    String projectName = "DMC Test Checkbox";
//    String projectName = "MIA project";
String projectName = "HRO Format";
//    String projectName = "Medications with anti-libidinal properties";
//    String projectName = "DMC Test 1";
//    String projectName = "STEM Grant Application Form 2021 to 2022";


    @Test
    public void checkboxValidationEnableDisableFieldsCheckbox() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateEnableDisableFieldsCheckbox();
    }

    @Test
    public void validateMandatoryFieldsWithoutInputs() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateCheckboxMandatory();
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

    @Test
    public void hroFormatValidation() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.hroFormatValidation();
    }

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
