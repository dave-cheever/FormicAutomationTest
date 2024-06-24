package Formic;

import com.Formic.OF2.pages.*;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.DataDrivenTest;
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


    //CheckBox

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


    //HRO

    @Test(dataProvider = "testDataHroNumeric", dataProviderClass = DataDrivenTest.class)
    public void hroNumericInvalidInputsValidation(String fieldId, String mandatory, String name, String max) throws Exception{
        String scenarioName = "hroNumericInvalidInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroNumericInvalidInputsValidation(fieldId,mandatory,name,max,scenarioName);
    }

    @Test(dataProvider = "testDataHroNumeric", dataProviderClass = DataDrivenTest.class)
    public void hroNumericValidInputsValidation(String fieldId, String mandatory, String name, String max) throws Exception{
        String scenarioName = "hroNumericInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroNumericInputsValidation(fieldId,mandatory,name,max,scenarioName);
    }

    @Test(dataProvider = "testDataHroEmail", dataProviderClass = DataDrivenTest.class)
    public void hroEmailInvalidInputsValidation(String fieldId, String mandatory, String name) throws Exception{
        String scenarioName = "hroEmailInvalidInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroEmailFormatInvalidInputsValidation(fieldId,mandatory,name,scenarioName);
    }

    @Test(dataProvider = "testDataHroEmail", dataProviderClass = DataDrivenTest.class)
    public void hroEmailInputsValidation(String fieldId, String mandatory, String name) throws Exception{
        String scenarioName = "hroEmailInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroEmailFormatInputsValidation(fieldId,mandatory,name,scenarioName);
    }

    @Test(dataProvider = "testDataHroDateTime", dataProviderClass = DataDrivenTest.class)
    public void hroDateTimeInvalidInputsValidation(String fieldId, String mandatory, String name,String formatMask) throws Exception{
        String scenarioName = "hroDateTimeInvalidInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroDateTimeInvalidInputsValidation(fieldId,mandatory,name,formatMask,scenarioName);
    }

    @Test(dataProvider = "testDataHroDateTime", dataProviderClass = DataDrivenTest.class)
    public void hroDateTimeValidInputsValidation(String fieldId, String mandatory, String name,String formatMask) throws Exception{
        String scenarioName = "hroDateTimeValidInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroDateTimeValidInputsValidation(fieldId,mandatory,name,formatMask,scenarioName);
    }

    @Test(dataProvider = "testDataHroDataFormatting", dataProviderClass = DataDrivenTest.class)
    public void hroDataFormattingValidInputs(String fieldId, String mandatory, String name,String formatRegex) throws Exception{
        String scenarioName = "hroDataFormattingValidInputs";
        homePage.selectProject(projectName,scenarioName);
        hro.hroDataFormatValidInputs(fieldId,mandatory,name,formatRegex,scenarioName);
    }


    @Test(dataProvider = "testDataHroDataFormatting", dataProviderClass = DataDrivenTest.class)
    public void hroDataFormattingInvalidInputs(String fieldId, String mandatory, String name,String formatRegex,String formatMask) throws Exception{
        String scenarioName = "hroDataFormattingInvalidInputs";
        homePage.selectProject(projectName,scenarioName);
        hro.hroDataFormatInvalidInputs(fieldId,mandatory,name,formatRegex,formatMask,scenarioName);
    }


    @Test(dataProvider = "testDataHroMandatory", dataProviderClass = DataDrivenTest.class)
    public void hroMandatoryInputsValidation(String fieldId) throws Exception{
        //hro validation message
        String scenarioName = "hroMandatoryInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        hro.hroMandatoryValidation(fieldId,scenarioName);
    }

    //MIA

    @Test(dataProvider = "testDataManualImageAreaEmail", dataProviderClass = DataDrivenTest.class)
    public void miaFormatValidEmail(String fieldId,String mandatory,String name) throws Exception{
        String scenarioName = "miaFormatValidEmail";
        homePage.selectProject(projectName,scenarioName);
        mia.miaFormatValidEmail(fieldId,mandatory,name,scenarioName);
    }

    @Test(dataProvider = "testDataManualImageAreaEmail", dataProviderClass = DataDrivenTest.class)
    public void miaFormatVInvalidEmail(String fieldId,String mandatory,String name) throws Exception{
        String scenarioName = "miaFormatVInvalidEmail";
        homePage.selectProject(projectName,scenarioName);
        mia.miaFormatInvalidEmail(fieldId,mandatory,name,scenarioName);
    }

    @Test(dataProvider = "testDataManualImageAreaDateTime", dataProviderClass = DataDrivenTest.class)
    public void miaFormatValidDateTime(String fieldId,String mandatory,String name, String formatMask) throws Exception{
        String scenarioName = "miaFormatValidDateTime";
        homePage.selectProject(projectName,scenarioName);
        mia.miaFormatDateTimeValidInputs(fieldId,mandatory,name,formatMask,scenarioName);
    }

    @Test(dataProvider = "testDataManualImageAreaDateTime", dataProviderClass = DataDrivenTest.class)
    public void miaFormatInvalidDateTime(String fieldId,String mandatory,String name, String formatMask) throws Exception{
        String scenarioName = "miaFormatInvalidDateTime";
        homePage.selectProject(projectName,scenarioName);
        mia.miaFormatDateTimeInvalidInputs(fieldId,mandatory,name,formatMask,scenarioName);
    }


    @Test(dataProvider = "testDataManualImageAreaNumeric", dataProviderClass = DataDrivenTest.class)
    public void miaFormatValidNumeric(String fieldId,String mandatory,String name, String formatMask) throws Exception{
        String scenarioName = "miaFormatValidNumeric";
        homePage.selectProject(projectName,scenarioName);
        mia.miaFormatNumericValidInputs(fieldId,mandatory,name,formatMask,scenarioName);
    }

    @Test(dataProvider = "testDataManualImageAreaNumeric", dataProviderClass = DataDrivenTest.class)
    public void miaFormatInvalidNumeric(String fieldId,String mandatory,String name, String formatMask) throws Exception{
        String scenarioName = "miaFormatInvalidNumeric";
        homePage.selectProject(projectName,scenarioName);
        mia.miaFormatNumericInvalidInputs(fieldId,mandatory,name,formatMask,scenarioName);
    }

    @Test(dataProvider = "testDataManualImageAreaDataFormatting", dataProviderClass = DataDrivenTest.class)
    public void miaDataFormatValidInputs(String fieldId,String mandatory,String name, String formatMask,String formatRegex) throws Exception{
        String scenarioName = "miaDataFormatValidInputs";
        homePage.selectProject(projectName,scenarioName);
        mia.miaDataFormatValidInputs(fieldId,mandatory,name,formatMask,formatRegex,scenarioName);
    }

    @Test(dataProvider = "testDataManualImageAreaDataFormatting", dataProviderClass = DataDrivenTest.class)
    public void miaDataFormatInvalidInputs(String fieldId,String mandatory,String name, String formatMask,String formatRegex) throws Exception{
        String scenarioName = "miaDataFormatInvalidInputs";
        homePage.selectProject(projectName,scenarioName);
        mia.miaDataFormatInvalidInputs(fieldId,mandatory,name,formatMask,formatRegex,scenarioName);
    }

    @Test(dataProvider = "testDataMiaMandatory", dataProviderClass = DataDrivenTest.class)
    public void miaMandatoryInputsValidation(String fieldId) throws Exception{
        //hro validation message
        String scenarioName = "miaMandatoryInputsValidation";
        homePage.selectProject(projectName,scenarioName);
        mia.miaMandatoryValidation(fieldId,scenarioName);
    }


    @Test(dataProvider = "testDataMiaPickList", dataProviderClass = DataDrivenTest.class)
    public void miaPicklistLessThanMinimumInputs(String fieldId, String mandatory, String name, String min, String max) throws Exception{
        String scenarioName = "miaPicklistLessThanMinimumInputs";
        homePage.selectProject(projectName,scenarioName);
        mia.miaPicklistLessThanMinimumInputs(fieldId,mandatory,name,min,max,scenarioName);
    }

    @Test(dataProvider = "testDataMiaPickList", dataProviderClass = DataDrivenTest.class)
    public void miaPicklistMoreThanMaximumInputs(String fieldId, String mandatory, String name, String min, String max) throws Exception{
        String scenarioName = "miaPicklistMoreThanMaximumInputs";
        homePage.selectProject(projectName,scenarioName);
        mia.miaPicklistMoreThanMaximumInputs(fieldId,mandatory,name,min,max,scenarioName);
    }

    @Test(dataProvider = "testDataMiaPickList", dataProviderClass = DataDrivenTest.class)
    public void miaPicklistWithinMinimumMaximumInputs(String fieldId, String mandatory, String name, String min, String max) throws Exception{
        String scenarioName = "miaPicklistWithinMinimumMaximumInputs";
        homePage.selectProject(projectName,scenarioName);
        mia.miaPicklistWithinMinimumMaximumInputs(fieldId,mandatory,name,min,max,scenarioName);
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








//    TEST

//    @Test
//    public void Checkbox_Beyond_Maximum_Inputs_TEST() throws Exception{
//        String scenarioName = "CheckboxBeyondMaximumInputs";
//        homePage.selectProject(projectName,scenarioName);
//        checkBoxPage.checkboxBeyondMaximumInputs("81736a5e-f905-42ea-b8e1-b52175479620","1",false,scenarioName);
//    }

//    @Test
//    public void hroDataFormattingValidInputsTest() throws Exception{
//        String scenarioName = "hroDataFormattingValidInputs";
//        homePage.selectProject(projectName,scenarioName);
//        hro.hroDataFormatValidInputs("75e08e6e-3fcf-4506-a739-73d3a59dfb17","true","5Q","^[a-zA-Z0-9]{1,5}$",scenarioName);
//    }


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
