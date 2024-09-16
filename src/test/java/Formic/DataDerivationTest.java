package Formic;

import com.Formic.OF2.pages.HRO.DataValidation;
import com.Formic.OF2.pages.HomePage;
import com.Formic.OF2.pages.MIA.DataDerivation;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.ConfigLoader;
import com.Formic.OF2.utils.DataDrivenTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataDerivationTest extends BaseUiTest {

    HomePage homePage;
    DataValidation dataValidationHro;
    DataDerivation dataDerivationMia;
    com.Formic.OF2.pages.HRO.DataDerivation dataDerivationHro;

    String projectName = ConfigLoader.getProperty("test.Derivation");

    @Test(dataProvider = "testHroDataDerivationAdd", dataProviderClass = DataDrivenTest.class)
    public void Calculate_Sum_of_Two_Fields_and_Display_Result_HRO(String fieldId) throws Exception{
        String scenarioName = "Calculate_Sum_of_Two_Fields_and_Display_Result_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Calculate_Sum_of_Two_Fields_and_Display_Result_HRO(fieldId,scenarioName);
    }

    @Test(dataProvider = "testHroDataDerivationDivide", dataProviderClass = DataDrivenTest.class)
    public void Divide_Two_Fields_and_Display_Result_HRO(String fieldId) throws Exception{
        String scenarioName = "Divide_Two_Fields_and_Display_Result_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Divide_Two_Fields_and_Display_Result_HRO(fieldId,scenarioName);
    }

    @Test(dataProvider = "testHroDataDerivationMultiply", dataProviderClass = DataDrivenTest.class)
    public void Multiply_Two_Fields_and_Display_Result_HRO(String fieldId) throws Exception{
        String scenarioName = "Multiply_Two_Fields_and_Display_Result_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Multiply_Two_Fields_and_Display_Result_HRO(fieldId,scenarioName);
    }

    @Test(dataProvider = "testHroDataDerivationSubtract", dataProviderClass = DataDrivenTest.class)
    public void Subtract_Two_Fields_and_Display_Result_HRO(String fieldId) throws Exception{
        String scenarioName = "Subtract_Two_Fields_and_Display_Result_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Subtract_Two_Fields_and_Display_Result_HRO(fieldId,scenarioName);
    }

    @Test(dataProvider = "testHroDataDerivationPropagation", dataProviderClass = DataDrivenTest.class)
    public void Value_Propagation_Between_Fields_HRO(String fieldId, String objectId) throws Exception{
        String scenarioName = "Value_Propagation_Between_Fields_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Value_Propagation_Between_Fields_HRO(fieldId,objectId,scenarioName);
    }

    @Test(dataProvider = "testMiaDataDerivationAdd", dataProviderClass = DataDrivenTest.class)
    public void Calculate_Sum_of_Two_Fields_and_Display_Result_MIA(String fieldId) throws Exception{
        String scenarioName = "Calculate_Sum_of_Two_Fields_and_Display_Result_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Calculate_Sum_of_Two_Fields_and_Display_Result_MIA(fieldId,scenarioName);
    }

    @Test(dataProvider = "testMiaDataDerivationDivide", dataProviderClass = DataDrivenTest.class)
    public void Divide_Two_Fields_and_Display_Result_MIA(String fieldId) throws Exception{
        String scenarioName = "Divide_Two_Fields_and_Display_Result_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Divide_Two_Fields_and_Display_Result_MIA(fieldId,scenarioName);
    }

    @Test(dataProvider = "testMiaDataDerivationMultiply", dataProviderClass = DataDrivenTest.class)
    public void Multiply_Two_Fields_and_Display_Result_MIA(String fieldId) throws Exception{
        String scenarioName = "Multiply_Two_Fields_and_Display_Result_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Multiply_Two_Fields_and_Display_Result_MIA(fieldId,scenarioName);
    }

    @Test(dataProvider = "testMiaDataDerivationSubtract", dataProviderClass = DataDrivenTest.class)
    public void Subtract_Two_Fields_and_Display_Result_MIA(String fieldId) throws Exception{
        String scenarioName = "Subtract_Two_Fields_and_Display_Result_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Subtract_Two_Fields_and_Display_Result_MIA(fieldId,scenarioName);
    }

    @Test(dataProvider = "testMiaDataDerivationPropagation", dataProviderClass = DataDrivenTest.class)
    public void Value_Propagation_Between_Fields_MIA(String fieldId, String objectId) throws Exception{
        String scenarioName = "Value_Propagation_Between_Fields_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Value_Propagation_Between_Fields_MIA(fieldId,objectId,scenarioName);
    }

    @Test(dataProvider = "testMiaDataDerivationCurrentDateTime", dataProviderClass = DataDrivenTest.class)
    public void Current_DateTime_MIA(String fieldId, String dateTimeFormatMask) throws Exception{
        String scenarioName = "Current_DateTime_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Current_DateTime_MIA(fieldId,dateTimeFormatMask,scenarioName);
    }




    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        dataValidationHro = PageFactory.initElements(getDriver(), DataValidation.class);
        dataDerivationHro = PageFactory.initElements(getDriver(), com.Formic.OF2.pages.HRO.DataDerivation.class);
        dataDerivationMia = PageFactory.initElements(getDriver(), com.Formic.OF2.pages.MIA.DataDerivation.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
