import Helpers.ScreenshotListener;
import Objects.CheckboxObject;
import Pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class FormContentTest extends BaseUiTest {

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    HomePage homePage;
    CheckBoxPage checkBoxPage;
//    String projectName = "DMC Test Checkbox";
    String projectName = "Medications with anti-libidinal properties";



    @Test
    public void checkboxValidationEnableDisableFieldsCheckbox() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateEnableDisableFieldsCheckbox();
    }

    @Test
    public void checkboxMandatory() throws Exception{
        homePage.selectProject(projectName);
        checkBoxPage.validateCheckboxMandatory();
    }

    @Test
    public void checkboxValidationMinimumWhenSubmitButtonClicked() throws Exception{
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
        //applied testing for checkbox matrix
    }

    @Test
    public void validateSubmittedInputsCheckbox() throws Exception {
        homePage.selectProject(projectName);
        checkBoxPage.validateSubmittedInputsCheckbox();
    }

    @Test
    public void hroFormatValidation() throws Exception{
        //hro validation message
        homePage.selectProject(projectName);
        checkBoxPage.hroFormatValidation();
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
