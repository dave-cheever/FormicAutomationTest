package Formic;

import com.Formic.OF2.pages.*;
import com.Formic.OF2.pages.HRO.DataValidation;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;

import com.Formic.OF2.utils.UpdateTestCaseStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class DataValidationTest extends BaseUiTest {

    HomePage homePage;
    CheckBoxPage checkBoxPage;
    DataValidation dataValidationHro;

    com.Formic.OF2.pages.MIA.DataValidation dataValidationMia;

    String projectName = "TEST Data Validation";

    @Test
    public void Positive_HRO_ValidateEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Verify_that_the_Equal_To_operator_correctly_identifies_when_the_input_value_is_equal_to_the_configured_value();
    }

    @Test
    public void Positive_HRO_ValidateNotEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Verify_that_the_Not_Equal_To_operator_correctly_identifies_when_the_input_value_is_not_equal_to_the_configured_value();
    }

    @Test
    public void Positive_HRO_ValidateGreaterThan() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Verify_that_the_Greater_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_the_configured_value();
    }

    @Test
    public void Positive_HRO_ValidateGreaterThanOrEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Verify_that_the_Greater_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Positive_HRO_ValidateLessThan() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Verify_that_the_Less_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Positive_HRO_ValidateLessThanOrEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Verify_that_the_Less_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Negative_HRO_ValidateEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Verify_that_the_Equal_To_operator_correctly_identifies_when_the_input_value_is_equal_to_the_configured_value();
    }

    @Test
    public void Negative_HRO_ValidateNotEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Verify_that_the_Not_Equal_To_operator_correctly_identifies_when_the_input_value_is_not_equal_to_the_configured_value();
    }

    @Test
    public void Negative_HRO_ValidateGreaterThan() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Verify_that_the_Greater_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_the_configured_value();
    }

    @Test
    public void Negative_HRO_ValidateGreaterThanOrEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Verify_that_the_Greater_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Negative_HRO_ValidateLessThan() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Verify_that_the_Less_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Negative_HRO_ValidateLessThanOrEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Verify_that_the_Less_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Positive_MIA_ValidateEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Verify_that_the_Equal_To_operator_correctly_identifies_when_the_input_value_is_equal_to_the_configured_value();
    }

    @Test
    public void Positive_MIA_ValidateNotEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Verify_that_the_Not_Equal_To_operator_correctly_identifies_when_the_input_value_is_not_equal_to_the_configured_value();
    }

    @Test
    public void Positive_MIA_ValidateGreaterThan() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Verify_that_the_Greater_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_the_configured_value();
    }

    @Test
    public void Positive_MIA_ValidateGreaterThanOrEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Verify_that_the_Greater_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Positive_MIA_ValidateLessThan() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Verify_that_the_Less_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Positive_MIA_ValidateLessThanOrEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Verify_that_the_Less_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Negative_MIA_ValidateEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Verify_that_the_Equal_To_operator_correctly_identifies_when_the_input_value_is_equal_to_the_configured_value();
    }

    @Test
    public void Negative_MIA_ValidateNotEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Verify_that_the_Not_Equal_To_operator_correctly_identifies_when_the_input_value_is_not_equal_to_the_configured_value();
    }

    @Test
    public void Negative_MIA_ValidateGreaterThan() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Verify_that_the_Greater_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_the_configured_value();
    }

    @Test
    public void Negative_MIA_ValidateGreaterThanOrEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Verify_that_the_Greater_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Negative_MIA_ValidateLessThan() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Verify_that_the_Less_Than_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @Test
    public void Negative_MIA_ValidateLessThanOrEqualTo() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Verify_that_the_Less_Than_or_Equal_To_operator_correctly_identifies_when_the_input_value_is_greater_than_or_equal_to_the_configured_value();
    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPage.class);
        dataValidationHro = PageFactory.initElements(getDriver(), DataValidation.class);
        dataValidationMia = PageFactory.initElements(getDriver(), com.Formic.OF2.pages.MIA.DataValidation.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
