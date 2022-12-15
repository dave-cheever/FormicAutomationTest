import Objects.CheckboxObject;
import Pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormContentTest extends BaseUiTest {

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    HomePage homePage;
    CheckBoxPage checkBoxPage;

    @Test
    public void checkboxValidationEnableDisableFieldsCheckbox() throws Exception{
        homePage.selectProject("Medications with anti-libidinal properties");
        checkBoxPage.validateEnableDisableFieldsCheckbox();
    }

    @Test
    public void checkboxMandatory() throws Exception{
        homePage.selectProject("Medications with anti-libidinal properties");
        checkBoxPage.validateCheckboxMandatory();
    }

    @Test
    public void checkboxValidationMinimumWhenSubmitButtonClicked() throws Exception{
        homePage.selectProject("Medications with anti-libidinal properties");
        checkBoxPage.validateCheckboxMinimumValidationUponSubmit();
    }

    @Test
    public void checkboxValidationBeyondMaximumInputs() throws Exception{
        homePage.selectProject("Medications with anti-libidinal properties");
        checkBoxPage.validateCheckboxBeyondMaximumInputsUponSubmit();
    }

    @Test
    public void checkboxValidationWithinMaximumInputs() throws Exception{
        homePage.selectProject("Medications with anti-libidinal properties");
        checkBoxPage.validateCheckboxMaximumInputsWithinLimitUponSubmit();
    }

    @Test
    public void checkboxValidationWithinMinimumInputs() throws Exception{
        homePage.selectProject("Medications with anti-libidinal properties");
        checkBoxPage.validateCheckboxWithinMinimumInputsUponSubmit();
    }

    @Test
    public void checkboxValidationLessThanMinimumInputs() throws Exception{
        homePage.selectProject("Medications with anti-libidinal properties");
        checkBoxPage.validateCheckboxLessThanMinimumInputsUponSubmit();
        //applied testing for checkbox matrix
    }

    @Test
    public void validateSubmittedInputsCheckbox() throws Exception {
        homePage.selectProject("Medications with anti-libidinal properties");
        checkBoxPage.validateSubmittedInputsCheckbox();
    }


    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        headerNavigationBar = PageFactory.initElements(getDriver(), HeaderNavigationBar.class);
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPage.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
