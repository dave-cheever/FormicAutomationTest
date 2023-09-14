package Formic.testcases;

import com.Formic.OF2.test.BaseUiTest;
import com.Formic.OF2.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ThemesTest extends BaseUiTest {

    HeaderNavigationBar headerNavigationBar;
    LoginPage loginPage;
    RegisterPage registerPage;
    ThemesPopup themesPopup;
    HomePage homePage;

    @Ignore
    public void ApplyDefaultThemes() throws Exception {
        headerNavigationBar.navigateToThemesPopup();
        themesPopup.clickDefaultThemeButton();
        themesPopup.clickSaveButton();
        Assert.assertTrue(homePage.confirmMyProjectsIsVisible());
    }

    @Ignore
    public void ApplyDefaultThemesWhileLoggedIn() throws Exception {
        headerNavigationBar.navigateToLoginPage();
        loginPage.loginUser("dcheever","Test123");
        headerNavigationBar.navigateToThemesPopupWhileLoggedIn();
        themesPopup.clickDefaultThemeButton();
        themesPopup.clickSaveButton();
        Assert.assertTrue(homePage.confirmMyProjectsIsVisible());
    }

    @Ignore
    public void OpenThemesThenCloseThemes() throws Exception {
        headerNavigationBar.navigateToThemesPopup();
        themesPopup.clickDefaultThemeButton();
        themesPopup.clickCancelButton();
        Assert.assertFalse(themesPopup.isThemesPopupDisplayed());
        Assert.assertTrue(homePage.confirmMyProjectsIsVisible());
    }

    @BeforeMethod
    public void initialisePageElements(ITestContext iTestContext) {
        headerNavigationBar = PageFactory.initElements(getDriver(), HeaderNavigationBar.class);
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        registerPage = PageFactory.initElements(getDriver(), RegisterPage.class);
        themesPopup = PageFactory.initElements(getDriver(), ThemesPopup.class);
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
    }
}
