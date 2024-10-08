package Formic;

import com.Formic.OF2.pages.*;
import com.Formic.OF2.pages.HRO.DataValidation;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;

import com.Formic.OF2.utils.ConfigLoader;
import com.Formic.OF2.utils.DataDrivenTest;
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
    DataValidation dataValidationHro;

    com.Formic.OF2.pages.MIA.DataValidation dataValidationMia;

    String projectName = ConfigLoader.getProperty("test.Derivation");

    @Test(dataProvider = "testHroDataValidationPositiveEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Positive_HRO_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_HRO_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Positive_HRO_Equal_To_Test(fieldId,NumberToValidate,scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveNotEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Positive_HRO_Not_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_HRO_Not_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Positive_HRO_Not_Equal_To_Test(fieldId, NumberToValidate,scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveGreaterThan", dataProviderClass = DataDrivenTest.class)
    public void Positive_HRO_Greater_Than_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_HRO_Greater_Than_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Positive_HRO_Greater_Than_Test(fieldId,NumberToValidate,scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveGreaterThanEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Positive_HRO_Greater_Than_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_HRO_Greater_Than_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Positive_HRO_Greater_Than_Equal_To_Test(fieldId,NumberToValidate,scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveLessThan", dataProviderClass = DataDrivenTest.class)
    public void Positive_HRO_Less_Than_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_HRO_Less_Than_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Positive_HRO_Less_Than_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveLessThanEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Positive_HRO_Less_Than_Or_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_HRO_Less_Than_Or_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Positive_HRO_Less_Than_Or_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Negative_HRO_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_HRO_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Negative_HRO_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveNotEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Negative_HRO_Not_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_HRO_Not_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Negative_HRO_Not_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveGreaterThan", dataProviderClass = DataDrivenTest.class)
    public void Negative_HRO_Greater_Than_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_HRO_Greater_Than_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Negative_HRO_Greater_Than_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveGreaterThanEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Negative_HRO_Greater_Than_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_HRO_Greater_Than_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Negative_HRO_Greater_Than_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveLessThan", dataProviderClass = DataDrivenTest.class)
    public void Negative_HRO_Less_Than_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_HRO_Less_Than_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Negative_HRO_Less_Than_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testHroDataValidationPositiveLessThanEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Negative_HRO_Less_Than_Or_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_HRO_Less_Than_Or_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationHro.Negative_HRO_Less_Than_Or_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }



    @Test(dataProvider = "testMiaDataValidationPositiveEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Positive_MIA_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_MIA_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Positive_MIA_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveNotEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Positive_MIA_Not_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_MIA_Not_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Positive_MIA_Not_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveGreaterThan", dataProviderClass = DataDrivenTest.class)
    public void Positive_MIA_Greater_Than_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_MIA_Greater_Than_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Positive_MIA_Greater_Than_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveGreaterThanEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Positive_MIA_Greater_Than_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_MIA_Greater_Than_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Positive_MIA_Greater_Than_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveLessThan", dataProviderClass = DataDrivenTest.class)
    public void Positive_MIA_Less_Than_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_MIA_Less_Than_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Positive_MIA_Less_Than_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveLessThanEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Positive_MIA_Less_Than_Or_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Positive_MIA_Less_Than_Or_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Positive_MIA_Less_Than_Or_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Negative_MIA_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_MIA_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Negative_MIA_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveNotEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Negative_MIA_Not_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_MIA_Not_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Negative_MIA_Not_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveGreaterThan", dataProviderClass = DataDrivenTest.class)
    public void Negative_MIA_Greater_Than_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_MIA_Greater_Than_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Negative_MIA_Greater_Than_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveGreaterThanEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Negative_MIA_Greater_Than_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_MIA_Greater_Than_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Negative_MIA_Greater_Than_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveLessThan", dataProviderClass = DataDrivenTest.class)
    public void Negative_MIA_Less_Than_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_MIA_Less_Than_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Negative_MIA_Less_Than_Test(fieldId, NumberToValidate, scenarioName);
    }

    @Test(dataProvider = "testMiaDataValidationPositiveLessThanEqualTo", dataProviderClass = DataDrivenTest.class)
    public void Negative_MIA_Less_Than_Or_Equal_To_Test(String fieldId, String NumberToValidate) throws Exception{
        String scenarioName = "Negative_MIA_Less_Than_Or_Equal_To_Test";
        homePage.selectProject(projectName,scenarioName);
        dataValidationMia.Negative_MIA_Less_Than_Or_Equal_To_Test(fieldId, NumberToValidate, scenarioName);
    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        dataValidationHro = PageFactory.initElements(getDriver(), DataValidation.class);
        dataValidationMia = PageFactory.initElements(getDriver(), com.Formic.OF2.pages.MIA.DataValidation.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
