package Formic;

import com.Formic.OF2.pages.CheckBoxPage;
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
    CheckBoxPage checkBoxPage;
    DataValidation dataValidationHro;

    DataDerivation dataDerivationMia;

    com.Formic.OF2.pages.HRO.DataDerivation dataDerivationHro;

    String projectName = "TEST Data Validation";

    @Test
    public void Calculate_Sum_of_Two_Fields_and_Display_Result_HRO() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationHro.Addition();
    }

    @Test
    public void Divide_Two_Fields_and_Display_Result_HRO() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationHro.Division();
    }

    @Test
    public void Multiply_Two_Fields_and_Display_Result_HRO() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationHro.Multiplication();
    }

    @Test
    public void Subtract_Two_Fields_and_Display_Result_HRO() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationHro.Subtraction();
    }

    @Test
    public void Value_Propagation_Between_Fields_HRO() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationHro.EqualToAnotherFieldHro();
    }

    @Test
    public void Calculate_Sum_of_Two_Fields_and_Display_Result_MIA() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationMia.Addition();
    }

    @Test
    public void Divide_Two_Fields_and_Display_Result_MIA() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationMia.Division();
    }

    @Test
    public void Multiply_Two_Fields_and_Display_Result_MIA() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationMia.Multiplication();
    }

    @Test
    public void Subtract_Two_Fields_and_Display_Result_MIA() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationMia.Subtraction();
    }

    @Test
    public void Value_Propagation_Between_Fields_MIA() throws Exception{
        homePage.selectProject(projectName);
        dataDerivationMia.EqualToAnotherFieldMia();
    }




    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPage.class);
        dataValidationHro = PageFactory.initElements(getDriver(), DataValidation.class);
        dataDerivationHro = PageFactory.initElements(getDriver(), com.Formic.OF2.pages.HRO.DataDerivation.class);
        dataDerivationMia = PageFactory.initElements(getDriver(), com.Formic.OF2.pages.MIA.DataDerivation.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
