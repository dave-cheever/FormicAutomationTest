package Formic;

import com.Formic.OF2.pages.*;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.DataDrivenTest;
import com.Formic.OF2.utils.FieldManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormContentV2Test extends BaseUiTest {

    HomePage homePage;
    CheckBoxPageV2 checkBoxPage;
    ManualImageArea mia;
    HandwritingRecognitionObject hro;
    String projectName = "DMC Test Checkbox"; // 137
//    String projectName = "Enable Disable fields Test"; // 185


//    @Test(dataProvider = "testData", dataProviderClass = DataDrivenTest.class)
//    public void loginTest(String username, String password) {
//        // Code to open browser, navigate to login page, and perform login
//        System.out.println("Logging in with username: " + username + ", password: " + password);
//        // Assertions or further actions
//    }

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

    @Test(dataProvider = "testDataManualImageArea", dataProviderClass = DataDrivenTest.class)
    public void miaFormatValidation(String fieldId,String mandatory,String name, String formatMask, String formatRegex, String dataType, String derivation, String validation, String multiResponse, String max, String min) throws Exception{
        String scenarioName = "miaFormatValidation";
        homePage.selectProject(projectName,scenarioName);
        mia.miaFormatValidation(fieldId,mandatory,name,formatMask,formatRegex,dataType,derivation,validation,multiResponse,max,min,scenarioName);
    }


    @Test(dataProvider = "testDataHroNumeric", dataProviderClass = DataDrivenTest.class)
    public void hroNumericInputsValidation(String fieldId, String mandatory, String name, String max) throws Exception{
        //hro validation message
        String scenarioName = "hroNumericInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroNumericInputsValidation(fieldId,mandatory,name,max,scenarioName);
    }

    @Test(dataProvider = "testDataHroAlphaNumeric", dataProviderClass = DataDrivenTest.class)
    public void hroAlphaNumericInputsValidation(String fieldId, String mandatory, String name, String max,String formatMask) throws Exception{
        //hro validation message
        String scenarioName = "hroAlphaNumericInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroAlphaNumericInputsValidation(fieldId,mandatory,name,max,formatMask,scenarioName);
    }

    @Test(dataProvider = "testDataHroDateTime", dataProviderClass = DataDrivenTest.class)
    public void hroDateTimeInputsValidation(String fieldId, String mandatory, String name,String formatMask) throws Exception{
        //hro validation message
        String scenarioName = "hroDateTimeInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroDateTimeInputsValidation(fieldId,mandatory,name,formatMask,scenarioName);
    }

    @Test(dataProvider = "testDataHroEmail", dataProviderClass = DataDrivenTest.class)
    public void hroEmailInputsValidation(String fieldId, String mandatory, String name) throws Exception{
        //hro validation message
        String scenarioName = "hroEmailInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroEmailFormatInputsValidation(fieldId,mandatory,name,scenarioName);
    }



    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPageV2.class);
        mia = PageFactory.initElements(getDriver(), ManualImageArea.class);
        hro = PageFactory.initElements(getDriver(), HandwritingRecognitionObject.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
