package com.Formic.OF2.pages;
import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThemesPopup extends BasePage {

    @FindBy(id = "dialog-title-1")
    public WebElement themesTitleLocator;

    @FindBy(xpath = "//legend[contains(text(),'Please select a theme; selection will display its description.')]")
    public WebElement pleaseSelectAThemeTextLocator;

    @FindBy(xpath = "//span[contains(text(),'Default')]")
    public WebElement defaultThemeButtonLocator;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    public WebElement cancelButtonLocator;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    public WebElement saveButtonLocator;

    public ThemesPopup(WebDriver driver) {
        super(driver);
    }

    public void clickDefaultThemeButton(){
        click(defaultThemeButtonLocator);
    }

    public void clickCancelButton(){
        click(cancelButtonLocator);
    }

    public void clickSaveButton(){
        click(saveButtonLocator);
    }

    public boolean isThemesPopupDisplayed(){
        return isElementPresent(themesTitleLocator);
    }
}
