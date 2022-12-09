package Pages;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.util.ArrayList;



public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait driverWait;


    public BasePage(WebDriver driver){
        this.driver = driver;
        driverWait = new WebDriverWait(this.driver,15);
        driver.get("https://formic-onlineforms-test.azurewebsites.net/");
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void javascriptClearInputValue(WebElement element){
        executeJavascript(element,"argument[0].value='';");
    }

    public void executeJavascript(WebElement element,String script){
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript(script,element);
    }

    public void javascriptClick(WebElement element) {
        executeJavascript(element,"arguments[0].click();");
    }

    public void click(WebElement element){
        driverWait.until(ExpectedConditions.elementToBeClickable(element));
        try{
            element.click();
        }catch (org.openqa.selenium.ElementClickInterceptedException e){
            e.printStackTrace();
            javascriptClick(element);
        }
    }

    public void enterText(WebElement element, String textToEnter){
        driverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(textToEnter);
    }

    public void enterTextWithDelay(WebElement element, String textToEnter, int milliseconds){
        driverWait.until(ExpectedConditions.visibilityOf(element));
        for(char c : textToEnter.toCharArray()){
            element.sendKeys(Character.toString(c));
            sleep(milliseconds);
        }
    }

    public String getElementText(WebElement element) {
        driverWait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public void selectFromDropdown(String selectXpath, String selectVisibleText) {
        By byProductType = By.xpath(selectXpath);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(byProductType));
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byProductType));
        Select select = new Select(driver.findElement(byProductType));
        select.selectByVisibleText(selectVisibleText);
    }

    public void selectFromDropdown(String selectXpath, int selectIndex) {
        By byProductType = By.xpath(selectXpath);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(byProductType));
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byProductType));
        Select select = new Select(driver.findElement(byProductType));
        select.selectByIndex(selectIndex);
    }

    public void selectFromDropdown(WebElement element, String selectVisibleText) {
        By byProductType = By.xpath(getXpathOfWebElement(element));
        driverWait.until(ExpectedConditions.presenceOfElementLocated(byProductType));
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byProductType));
        Select select = new Select(driver.findElement(byProductType));
        select.selectByVisibleText(selectVisibleText);
    }

    public void selectFromDropdown(WebElement element, int selectIndex) {
        By byProductType = By.xpath(getXpathOfWebElement(element));
        driverWait.until(ExpectedConditions.presenceOfElementLocated(byProductType));
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byProductType));
        Select select = new Select(driver.findElement(byProductType));
        select.selectByIndex(selectIndex);
    }

    public void selectTextSearchResult() {
        // xpath of first search result
        String xpath = "//ul[@role='listbox']/li[1]";

        WebElement element = driver.findElement(By.xpath(xpath));
        scrollElementIntoView(driver, element);
        element.click();
    }

    public void waitForTextNotification(String text) {
        String xpath = "//*[contains(text(), '$TEXT')]".replace("$TEXT", text);
        By byProductType = By.xpath(xpath);
        System.out.println("waiting for Element to be Found");
        driverWait.until(ExpectedConditions.presenceOfElementLocated(byProductType));
        System.out.println("Element Found");
    }

    public String stringReplace(String element, String stringToReplace){
        element = element.replace("$TEXT",stringToReplace);
        return element;
    }

    public String stringReplaceTwoValues(String elementOne, String stringToReplaceOne, String stringToReplaceTwo){
        elementOne = elementOne.replace("$TEXT",stringToReplaceOne);
        elementOne = elementOne.replace("$NUM",stringToReplaceTwo);
        return elementOne;
    }

    public WebElement convertByToWebElement(String element){
        By elem = By.xpath(element);
        WebElement webElem = driverWait.until(ExpectedConditions.presenceOfElementLocated(elem));
        return webElem;
    }

    public WebElement stringReplaceAndConvertToWebElement(String elem, String stringToReplace){
        elem = stringReplace(elem,stringToReplace);
        WebElement element = convertByToWebElement(elem);
        return element;
    }

    public int numberOfElementsVisible(String element){
        WebElement elem = stringToWebElement(element);
        scrollElementIntoView(driver,elem);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        return driver.findElements(By.xpath(element)).size();
    }



    public void waitForTextNotificationToBeInvisible(String text) {
        String xpath = "//*[contains(text(), '$TEXT')]".replace("$TEXT", text);
        By byProductType = By.xpath(xpath);
        System.out.println("Waiting for element to be removed");
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(byProductType));
        System.out.println("element removed");
    }

    protected void sleep(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    // Below method will be used to scroll element into view via
    // JavaScriptExecuter as some elements are not intractable until
    // they are scrolled into view
    protected void scrollElementIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // Check for element's visibility
    protected boolean isElementVisible(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver);

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Check for element's visibility
    protected boolean isElementVisible(WebDriver driver, String xpath ) {
        Wait<WebDriver> wait = new FluentWait<>(driver);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementPresent(WebElement element) {
        try{
            driverWait.until(ExpectedConditions.visibilityOf(element));
            return driver.findElements(By.xpath(getXpathOfWebElement(element))).size() > 0;
        }catch (Exception e){
            return false;
        }
    }

    public String getXpathOfWebElement(WebElement element) {
        return element.toString().substring(element.toString().indexOf("xpath: ") + 7, element.toString().length() - 1);
    }

    public void driverSwitchLastTab() {
        int numberOfTabs = driver.getWindowHandles().size();
        driver.switchTo().window(driver.getWindowHandles().toArray()[numberOfTabs-1].toString());
    }

    public void waitForElementText(WebElement elem, int seconds){
        int ctr = 0;
        for(boolean x = false; x == false;)
        {
            if(ctr == seconds*10){
                x = true;
            }
            else if (StringUtils.isBlank(elem.getText())){
                sleep(100);
                ctr++;
            }
            else{
                x = true;
            }
        }
    }

    public void waitForElementAttributeText(WebElement elem, int seconds){
        int ctr = 0;
        for(boolean x = false; x == false;)
        {
            if(ctr == seconds*10){
                x = true;
            }
            else if (StringUtils.isBlank(elem.getAttribute("value"))){
                sleep(100);
                ctr++;
            }
            else{
                x = true;
            }
        }
    }

    private String getNameOfFileDownloaded(WebDriver driver, String fileName, int millisecondsToStopWaiting) throws InterruptedException {
        driver.get("chrome://downloads");
        JavascriptExecutor je = (JavascriptExecutor) driver;
        long currentTime = System.currentTimeMillis();
        String result = null;

        while(true) {
            long b = System.currentTimeMillis();
            try {
                result = (String) je.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content  #file-link').text");
            } catch (JavascriptException e){
                continue;
            }

            if(b-currentTime == millisecondsToStopWaiting || result != null){
                break;
            }
            Thread.sleep(500);   //sleep half a seconds
        }
        return result;
    }

    public void switchToParentTab(){
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }

    public void switchToChildTab(){
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }

    public WebElement getWebElement(String baseXpath, String stringToReplace, String stringToReplaceBy) {
        WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(baseXpath.replace(stringToReplace,stringToReplaceBy))));
        driverWait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    protected void waitUntilElementIsNotPresent(WebElement element, int millisecondsToStopWaiting) throws TimeoutException, InterruptedException {
        long currentTime = System.currentTimeMillis();
        while(true) {
            long b = System.currentTimeMillis();
            if(b-currentTime == millisecondsToStopWaiting){
                throw new TimeoutException("Element is still present");
            }

            Thread.sleep(500);   //sleep half a seconds
            if (!isElementPresent(element)) break;
        }
    }

    protected void waitUntilElementIsPresent(WebElement element, int millisecondsToStopWaiting) throws TimeoutException, InterruptedException {
        long currentTime = System.currentTimeMillis();
        long b = 0;
        while(true) {
            if(b == millisecondsToStopWaiting){
                throw new TimeoutException("Element is still present");
            }

            Thread.sleep(500);   //sleep half a seconds
            if (isElementVisible(driver,element)){
                break;
            }
            else{
                b+=500;
            }
        }
    }

    public WebElement stringToWebElement(String element){
        WebElement elem;
        try{
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
            elem = driver.findElement(By.xpath(element));
        }catch (Exception e){
            scrollElementIntoView(driver,driver.findElement(By.xpath(element)));
            elem = driver.findElement(By.xpath(element));
        }
        return elem;
    }

    public void validateIsFileDownloaded(WebDriver driver, String fileName, int millisecondsToStopWaiting) throws InterruptedException {
        Assert.assertEquals(getNameOfFileDownloaded(driver,fileName,millisecondsToStopWaiting),fileName);
    }
}
