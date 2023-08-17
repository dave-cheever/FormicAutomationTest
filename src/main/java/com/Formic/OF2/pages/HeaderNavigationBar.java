package com.Formic.OF2.pages;
import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderNavigationBar extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Logout')]//ancestor::div[2]//div[1]")
    WebElement employeeNameLocator;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logoutLinkLocator;

    @FindBy(xpath = "//a[contains(text(),'Logout')]//ancestor::div[2]//following::button[1]")
    WebElement loggedInThemesButtonLocator;

    @FindBy(xpath = "//div[contains(text(),'Login')]")
    WebElement loginButtonLocator;

    @FindBy(xpath = "//div[contains(text(),'Register')]")
    WebElement registerButtonLocator;

    @FindBy(xpath = "//div[contains(text(),'Themes')]")
    WebElement themesButtonLocator;


    public HeaderNavigationBar(WebDriver browserDriver) {
        super(browserDriver);
    }

    public void navigateToLoginPage(){
        click(loginButtonLocator);
    }

    public boolean loginButtonVisible() {
        return isElementPresent(loginButtonLocator);
    }

    public void navigateToRegisterPage(){
        click(registerButtonLocator);
    }

    public void navigateToThemesPopup(){
        click(themesButtonLocator);
    }

    public void navigateToThemesPopupWhileLoggedIn(){
        click(loggedInThemesButtonLocator);
    }

    public void logoutTheUser(){
        click(logoutLinkLocator);
    }

    public String getFullName(){
        return getElementText(employeeNameLocator);
    }
}
