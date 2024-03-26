package com.Formic.OF2.pages;
import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    public void selectProject(String project){
        Reporter.log("<b>Navigating to test website.<b/>");
        visit("https://formic-onlineforms-test.azurewebsites.net/");
        WebElement projectElement = stringReplaceAndConvertToWebElement(projectNameLocator,project);
        scrollElementIntoView(driver,projectElement);
        Reporter.log("<b>Scroll through the project list to find the project: <b/>"+project);
        clickWithTries(projectElement,3);
        Reporter.log("<b>Click project name: <b/>"+project,true);
    }
}
