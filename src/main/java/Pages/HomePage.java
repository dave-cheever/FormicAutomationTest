package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

public class HomePage extends BasePage{


    @FindBy(xpath = "//h1[@id='projects-heading']")
    public WebElement projectHeadingLocator;

    @FindBy(xpath = "//h3[contains(text(),'DMC Test Checkbox')]")
    public WebElement HeaderTextLocator;

    @FindBy(xpath = "//button[contains(text(),\"Ok, let's start!\")]")
    public WebElement okStartButtonLocator;

    @FindBy(xpath = "//*[contains(text(),'Your experience')]")
    public WebElement yourExperienceLocator;

    @FindBy(xpath = "//strong[contains(text(),\"Q1. What is your name?\")]//following::textarea[1]")
    public WebElement q1;

    String projectNameLocator = "//div[contains(text(),'$TEXT')]";
    String q2 = "//strong[contains(text(),\"Q2. Which class are you in?\")]//following::input[$TEXT]";




    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean confirmMyProjectsIsVisible(){
        try {
            waitUntilElementIsPresent(projectHeadingLocator,5000);
            return isElementPresent(projectHeadingLocator);
        }catch (InterruptedException e){
            return false;
        }
    }

    public void selectProject(String project){
        visit("https://formic-onlineforms-test.azurewebsites.net/");
        WebElement projectElement = stringReplaceAndConvertToWebElement(projectNameLocator,project);
        scrollElementIntoView(driver,projectElement);
        click(projectElement);
        Reporter.log("Click project name: "+project,true);
    }

    public boolean questionnaireForChildrenTextIsVisible() throws InterruptedException {
        waitUntilElementIsPresent(HeaderTextLocator,5000);
        return isElementPresent(HeaderTextLocator);
    }

    public void clickOkStartButton(){
        driverWait.until(ExpectedConditions.visibilityOf(okStartButtonLocator));
        scrollElementIntoView(driver,okStartButtonLocator);
        click(okStartButtonLocator);
    }

    public boolean yourExperienceTextVisible() throws InterruptedException {
        waitUntilElementIsPresent(yourExperienceLocator,5000);
        scrollElementIntoView(driver,yourExperienceLocator);
        return isElementPresent(yourExperienceLocator);
    }

    public void inputYourName(String name) throws InterruptedException {
        waitUntilElementIsPresent(q1,5000);
        enterText(q1,name);
    }

    public void selectQ2(String num) throws InterruptedException {
        WebElement projectElement = stringReplaceAndConvertToWebElement(q2,num);
        scrollElementIntoView(driver,projectElement);
        waitUntilElementIsPresent(projectElement,5000);
        click(projectElement);
    }





}
