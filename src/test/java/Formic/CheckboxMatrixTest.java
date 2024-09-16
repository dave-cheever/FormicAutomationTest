package Formic;

import com.Formic.OF2.pages.CheckBoxPageV2;
import com.Formic.OF2.pages.CheckboxMatrixV2;
import com.Formic.OF2.pages.HomePage;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.ConfigLoader;
import com.Formic.OF2.utils.DataDrivenTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxMatrixTest extends BaseUiTest {
    String projectName = ConfigLoader.getProperty("test.CheckboxProjectName");
    HomePage homePage;
    CheckboxMatrixV2 checkBoxMatrix;

    @Test(dataProvider = "testDataMatrixMandatory", dataProviderClass = DataDrivenTest.class)
    public void No_Inputs_Mandatory_Minimum_Inputs_Validation_Test(String fieldId) throws Exception{
        String scenarioName = "NoInputsMandatoryMinimumInputsValidationTest";
        homePage.selectProject(projectName,scenarioName);
        checkBoxMatrix.NoInputsMandatoryMinimumInputsValidationTest(fieldId,scenarioName);
    }


    @Test(dataProvider = "testDataMatrixMinInputs", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Matrix_Minimum_Required_Inputs_Upon_Submit(String fieldId, String min,String mandatory) throws Exception{
        String scenarioName = "CheckboxMatrixMinimumRequiredInputsUponSubmit";
        homePage.selectProject(projectName,scenarioName);
        checkBoxMatrix.checkboxMinimumRequiredInputsUponSubmit(fieldId,min,scenarioName);
    }

    @Test(dataProvider = "testDataMatrixMaxInputs", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Matrix_Beyond_Maximum_Inputs(String fieldId, String max,String mandatory) throws Exception{
        String scenarioName = "CheckboxMatrixBeyondMaximumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxMatrix.checkboxBeyondMaximumInputs(fieldId,max,Boolean.parseBoolean(mandatory),scenarioName);
    }

    @Test(dataProvider = "testDataMatrixMaxInputs", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Matrix_Within_Maximum_Inputs(String fieldId, String max,String mandatory) throws Exception{
        String scenarioName = "Checkbox_Matrix_Within_Maximum_Inputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxMatrix.checkboxWithinMaximumInputs(fieldId,max,Boolean.parseBoolean(mandatory),scenarioName);
    }

    @Test(dataProvider = "testDataMatrixMinInputs", dataProviderClass = DataDrivenTest.class)
    public void Checkbox_Matrix_Within_Minimum_Inputs(String fieldId, String min,String mandatory) throws Exception{
        String scenarioName = "checkboxMatrixWithinMinimumInputs";
        homePage.selectProject(projectName,scenarioName);
        checkBoxMatrix.checkboxWithinMinimumInputs(fieldId,min,Boolean.parseBoolean(mandatory),scenarioName);
    }
//
//    @Test(dataProvider = "testDataMinInputs", dataProviderClass = DataDrivenTest.class)
//    public void checkboxValidationLessThanMinimumInputs(String fieldId, String min,String mandatory) throws Exception{
//        String scenarioName = "checkboxValidationLessThanMinimumInputs";
//        homePage.selectProject(projectName,scenarioName);
//        checkBoxMatrix.validateCheckboxLessThanMinimumInputsUponSubmit(fieldId,min,Boolean.parseBoolean(mandatory),scenarioName);
//    }


    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxMatrix = PageFactory.initElements(getDriver(), CheckboxMatrixV2.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
