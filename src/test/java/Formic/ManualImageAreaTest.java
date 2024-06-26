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

public class ManualImageAreaTest extends BaseUiTest {
    HomePage homePage;

    ManualImageArea mia;

    String projectName = "DMC Test Checkbox"; // 137
//    String projectName = "Enable Disable fields Test"; // 185

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


    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        mia = PageFactory.initElements(getDriver(), ManualImageArea.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
