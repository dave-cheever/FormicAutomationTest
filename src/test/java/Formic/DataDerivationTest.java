package Formic;

import com.Formic.OF2.pages.HRO.DataValidation;
import com.Formic.OF2.pages.HomePage;
import com.Formic.OF2.pages.MIA.DataDerivation;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataDerivationTest extends BaseUiTest {

    HomePage homePage;
    DataValidation dataValidationHro;
    DataDerivation dataDerivationMia;
    com.Formic.OF2.pages.HRO.DataDerivation dataDerivationHro;

    String projectName = "TEST Data Validation";

    @Test
    public void Calculate_Sum_of_Two_Fields_and_Display_Result_HRO() throws Exception{
        String scenarioName = "Calculate_Sum_of_Two_Fields_and_Display_Result_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Calculate_Sum_of_Two_Fields_and_Display_Result_HRO(scenarioName);
    }

    @Test
    public void Divide_Two_Fields_and_Display_Result_HRO() throws Exception{
        String scenarioName = "Divide_Two_Fields_and_Display_Result_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Divide_Two_Fields_and_Display_Result_HRO();
    }

    @Test
    public void Multiply_Two_Fields_and_Display_Result_HRO() throws Exception{
        String scenarioName = "Multiply_Two_Fields_and_Display_Result_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Multiply_Two_Fields_and_Display_Result_HRO();
    }

    @Test
    public void Subtract_Two_Fields_and_Display_Result_HRO() throws Exception{
        String scenarioName = "Subtract_Two_Fields_and_Display_Result_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Subtract_Two_Fields_and_Display_Result_HRO();
    }

    @Test
    public void Value_Propagation_Between_Fields_HRO() throws Exception{
        String scenarioName = "Value_Propagation_Between_Fields_HRO";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationHro.Value_Propagation_Between_Fields_HRO();
    }

    @Test
    public void Calculate_Sum_of_Two_Fields_and_Display_Result_MIA() throws Exception{
        String scenarioName = "Calculate_Sum_of_Two_Fields_and_Display_Result_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Calculate_Sum_of_Two_Fields_and_Display_Result_MIA();
    }

    @Test
    public void Divide_Two_Fields_and_Display_Result_MIA() throws Exception{
        String scenarioName = "Divide_Two_Fields_and_Display_Result_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Divide_Two_Fields_and_Display_Result_MIA();
    }

    @Test
    public void Multiply_Two_Fields_and_Display_Result_MIA() throws Exception{
        String scenarioName = "Multiply_Two_Fields_and_Display_Result_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Multiply_Two_Fields_and_Display_Result_MIA();
    }

    @Test
    public void Subtract_Two_Fields_and_Display_Result_MIA() throws Exception{
        String scenarioName = "Subtract_Two_Fields_and_Display_Result_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Subtract_Two_Fields_and_Display_Result_MIA();
    }

    @Test
    public void Value_Propagation_Between_Fields_MIA() throws Exception{
        String scenarioName = "Value_Propagation_Between_Fields_MIA";
        homePage.selectProject(projectName,scenarioName);
        dataDerivationMia.Value_Propagation_Between_Fields_MIA();
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
