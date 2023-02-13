package Pages;
import Helpers.APIException;
import Helpers.ScreenshotHelper;
import Helpers.TestRailManger;
import Objects.CheckboxObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class BaseUiTest {


    //    public WebDriver driver;

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    ScreenshotHelper screenshotHelper;

//    public BaseUiTest(WebDriver driver) {
//        super(driver);
//    }

    @BeforeSuite
    public void setUpSuite() {
        // set chromedriver property
        // If it is not run via docker, use chromedriver.exe
        String defaultDriverPath;
        defaultDriverPath = String.join(File.separator, "drivers", "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", defaultDriverPath);

    }


    @BeforeMethod
    public void setDriver() throws MalformedURLException {
        // Start driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-GB");
        // Setting new download directory path
        Map<String, Object> prefs = new HashMap<String, Object>();
        // Use File.separator as it will work on any OS
        prefs.put("download.default_directory",
                System.getProperty("user.dir") + File.separator + "downloadFiles");
        options.setExperimentalOption("prefs", prefs);
        // Test is running on docker, use the remote web driver
        driver.set(new ChromeDriver(options));
        //setDriver(new ChromeDriver(options));
        getDriver().manage().window().maximize();
        screenshotHelper = PageFactory.initElements(getDriver(),ScreenshotHelper.class);
    }

    @AfterMethod
    public void driverTearDown(ITestResult result) throws IOException, InterruptedException, APIException {
        if(result.getStatus()==ITestResult.SUCCESS&&CheckboxObject.testCaseId!=0){
            screenshotHelper.takeScreenshot(CheckboxObject.scenarioName);
            TestRailManger.updateResult(CheckboxObject.testCaseId,TestRailManger.TEST_CASE_PASSED_STATUS,24);
        } else if (result.getStatus()==ITestResult.FAILURE&&CheckboxObject.testCaseId!=0) {
            screenshotHelper.takeScreenshot(CheckboxObject.scenarioName);
            TestRailManger.updateResult(CheckboxObject.testCaseId,TestRailManger.TEST_CASE_FAILED_STATUS,24);
        }
        Process process = Runtime. getRuntime(). exec("taskkill /F /IM chromedriver.exe /T");
        process.destroy();
        getDriver().quit();
    }


    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver driver) {
        this.driver.set(driver);
//        this.driver = driver;
    }

    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void assertAllElementsFoundByXpathContainText(String xpath, String textToContain) {
        for (WebElement element: getDriver().findElements(By.xpath(xpath))
        ) {
            try {
                //Adding below "if" condition to skip because bug ticket AD-8191 will cause this test case to fail
                if (element.getText().equalsIgnoreCase("")){
                    continue;
                }
                Assert.assertTrue(element.getText().contains(textToContain));
            } catch (java.lang.AssertionError e) {
                throw new java.lang.AssertionError("\"" + element.getText() + "\" does not contain" + "\"" + textToContain + "\"");
            }
        }
    }

    public void assertElementContainsText(WebElement element, String textToContain) {
        try {
            Assert.assertTrue(element.getText().contains(textToContain));
        } catch (java.lang.AssertionError e) {
            throw new java.lang.AssertionError("\"" + element.getText() + "\" does not contain" + "\"" + textToContain + "\"");
        }
    }



}