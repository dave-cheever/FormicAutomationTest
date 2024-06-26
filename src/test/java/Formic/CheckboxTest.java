package Formic;

import com.Formic.OF2.pages.CheckBoxPageV2;
import com.Formic.OF2.pages.HandwritingRecognitionObject;
import com.Formic.OF2.pages.HomePage;
import com.Formic.OF2.pages.ManualImageArea;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.DataDrivenTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxTest extends BaseUiTest {

    HomePage homePage;
    CheckBoxPageV2 checkBoxPage;
    HandwritingRecognitionObject hro;
    String projectName = "DMC Test Checkbox"; // 137
//    String projectName = "Enable Disable fields Test"; // 185

    @Test(dataProvider = "testData", dataProviderClass = DataDrivenTest.class)
    public void No_Inputs_Mandatory_Minimum_Inputs_Validation_Test(String fieldId) throws Exception{
        String scenarioName = "NoInputsMandatoryMinimumInputsValidationTest";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.NoInputsMandatoryMinimumInputsValidationTest(fieldId,scenarioName);
    }

    @Test(dataProvider = "testDataEnable", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Toggle_Enable_Disable_Fields(String WhenFieldId, String hasValue, String fieldId, String action) throws Exception{
        String scenarioName = "CheckboxToggleEnableDisableFields";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.CheckboxToggleEnableDisableFields(WhenFieldId,hasValue,fieldId,action,scenarioName);
    }

    @Test(dataProvider = "testDataMinInputs", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Minimum_Required_Inputs_Upon_Submit(String fieldId, String min,String mandatory) throws Exception{
        String scenarioName = "CheckboxMinimumRequiredInputsUponSubmit";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.checkboxMinimumRequiredInputsUponSubmit(fieldId,min,scenarioName);
    }

    @Test(dataProvider = "testDataMaxInputs", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Beyond_Maximum_Inputs(String fieldId, String max,String mandatory) throws Exception{
        String scenarioName = "CheckboxBeyondMaximumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.checkboxBeyondMaximumInputs(fieldId,max,Boolean.parseBoolean(mandatory),scenarioName);
    }

    @Test(dataProvider = "testDataMaxInputs", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Within_Maximum_Inputs(String fieldId, String max,String mandatory) throws Exception{
        String scenarioName = "Checkbox_Within_Maximum_Inputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.checkboxWithinMaximumInputs(fieldId,max,Boolean.parseBoolean(mandatory),scenarioName);
    }

    @Test(dataProvider = "testDataMinInputs", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Within_Minimum_Inputs(String fieldId, String min,String mandatory) throws Exception{
        String scenarioName = "checkboxWithinMinimumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.checkboxWithinMinimumInputs(fieldId,min,Boolean.parseBoolean(mandatory),scenarioName);
    }

    @Test(dataProvider = "testDataMinInputs", dataProviderClass = DataDrivenTest.class)
    public void checkboxValidationLessThanMinimumInputs(String fieldId, String min,String mandatory) throws Exception{
        String scenarioName = "checkboxValidationLessThanMinimumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxPage.validateCheckboxLessThanMinimumInputsUponSubmit(fieldId,min,Boolean.parseBoolean(mandatory),scenarioName);
    }


    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPageV2.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
