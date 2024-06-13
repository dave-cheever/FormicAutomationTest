package com.Formic.OF2.pages;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.ScreenshotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePage extends BasePage {


    @FindBy(xpath = "//h1[@id='projects-heading']")
    public WebElement projectHeadingLocator;

    String projectNameLocator = "//div[contains(text(),'$TEXT')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean confirmMyProjectsIsVisible(){
//        try {
//            waitUntilElementIsPresent(projectHeadingLocator,20000);
            return isElementPresent(projectHeadingLocator);
//        }catch (InterruptedException e){
//            return false;
//        }
    }

    public void selectProject(String project, String scenarioName){
        Reporter.log("<b>Navigating to test website.</b>");
        visit("https://formic-onlineforms-test.azurewebsites.net/");
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),\"My Projects\")]")));
        driver.navigate().refresh();
        WebElement projectElement = stringReplaceAndConvertToWebElement(projectNameLocator,project);
        Reporter.log("Scroll through the project list to find the project: "+project);
        scrollElementIntoView(driver,projectElement);
        try{
            clickWithTries(projectElement,5);
            Reporter.log("Click project name: "+project,true);
        }catch (AssertionError assertionError){
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);
            screenshotHelper.takeScreenshot(scenarioName);
            // Rethrow the exception to mark the test as failed
            String pathName = screenshotHelper.getScreenshotPath(scenarioName);
            Reporter.log("Failed test screenshot: <a href='" + pathName + "'>Screenshot</a>");
            throw assertionError;
        }
    }
}
