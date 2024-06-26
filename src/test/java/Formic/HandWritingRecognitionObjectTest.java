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

public class HandWritingRecognitionObjectTest extends BaseUiTest {

    HomePage homePage;
    HandwritingRecognitionObject hro;
    String projectName = "DMC Test Checkbox"; // 137
//    String projectName = "Enable Disable fields Test"; // 185

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
    public void hroDataFormattingValidInputs(String fieldId, String mandatory, String name,String formatRegex, String formatMask) throws Exception{
        String scenarioName = "hroDataFormattingValidInputs";
        homePage.selectProject(projectName,scenarioName);
        hro.hroDataFormatValidInputs(fieldId,mandatory,name,formatRegex,formatMask,scenarioName);
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

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        hro = PageFactory.initElements(getDriver(), HandwritingRecognitionObject.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
