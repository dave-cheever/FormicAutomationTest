package Formic;

import com.Formic.OF2.pages.*;
import com.Formic.OF2.pages.HRO.DataValidation;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;

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
    CheckBoxPage checkBoxPage;
    DataValidation dataValidationHro;

    com.Formic.OF2.pages.MIA.DataValidation dataValidationMia;

    String projectName = "TEST Data Validation";

    @Test
    public void Positive_HRO_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Equal_To_Test();
    }

    @Test
    public void Positive_HRO_Not_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Not_Equal_To_Test();
    }

    @Test
    public void Positive_HRO_Greater_Than_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Greater_Than_Test();
    }

    @Test
    public void Positive_HRO_Greater_Than_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Greater_Than_Equal_To_Test();
    }

    @Test
    public void Positive_HRO_Less_Than_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Less_Than_Test();
    }

    @Test
    public void Positive_HRO_Less_Than_Or_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Positive_HRO_Less_Than_Or_Equal_To_Test();
    }

    @Test
    public void Negative_HRO_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Equal_To_Test();
    }

    @Test
    public void Negative_HRO_Not_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Not_Equal_To_Test();
    }

    @Test
    public void Negative_HRO_Greater_Than_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Greater_Than_Test();
    }

    @Test
    public void Negative_HRO_Greater_Than_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Greater_Than_Equal_To_Test();
    }

    @Test
    public void Negative_HRO_Less_Than_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Less_Than_Test();
    }

    @Test
    public void Negative_HRO_Less_Than_Or_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationHro.Negative_HRO_Less_Than_Or_Equal_To_Test();
    }

    @Test
    public void Positive_MIA_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Equal_To_Test();
    }

    @Test
    public void Positive_MIA_Not_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Not_Equal_To_Test();
    }

    @Test
    public void Positive_MIA_Greater_Than_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Greater_Than_Test();
    }

    @Test
    public void Positive_MIA_Greater_Than_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Greater_Than_Equal_To_Test();
    }

    @Test
    public void Positive_MIA_Less_Than_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Less_Than_Test();
    }

    @Test
    public void Positive_MIA_Less_Than_Or_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Positive_MIA_Less_Than_Or_Equal_To_Test();
    }

    @Test
    public void Negative_MIA_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Equal_To_Test();
    }

    @Test
    public void Negative_MIA_Not_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Not_Equal_To_Test();
    }

    @Test
    public void Negative_MIA_Greater_Than_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Greater_Than_Test();
    }

    @Test
    public void Negative_MIA_Greater_Than_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Greater_Than_Equal_To_Test();
    }

    @Test
    public void Negative_MIA_Less_Than_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Less_Than_Test();
    }

    @Test
    public void Negative_MIA_Less_Than_Or_Equal_To_Test() throws Exception{
        homePage.selectProject(projectName);
        dataValidationMia.Negative_MIA_Less_Than_Or_Equal_To_Test();
    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPage.class);
        dataValidationHro = PageFactory.initElements(getDriver(), DataValidation.class);
        dataValidationMia = PageFactory.initElements(getDriver(), com.Formic.OF2.pages.MIA.DataValidation.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
