package Formic;

import com.Formic.OF2.pages.CheckBoxPageV2;
import com.Formic.OF2.pages.HandwritingRecognitionObject;
import com.Formic.OF2.pages.HomePage;
import com.Formic.OF2.pages.ManualImageArea;
import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.ConfigLoader;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigationOptionTest extends BaseUiTest {

    HomePage homePage;
    CheckBoxPageV2 checkBoxPage;
    String projectName = ConfigLoader.getProperty("test.NavigationOption");

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

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        checkBoxPage = PageFactory.initElements(getDriver(), CheckBoxPageV2.class);
        CheckboxObject.fieldId.clear();
        CheckboxObject.checkboxInputs.clear();
        CheckboxObject.checkboxObjectDefaultValue();
    }
}
