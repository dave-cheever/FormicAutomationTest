package com.Formic.OF2.test;

import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Reporter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;



public class BasePage {

    protected static WebDriver driver;
    protected static WebDriverWait driverWait;


    static String nextPageButton = "//li//button//div[contains(text(),'Next Page')]";
    static String previousPageButton = "//li//button//div[contains(text(),'Previous Page')]";
    static String page = "//div[@data-testid='page-progress']";


    public BasePage(WebDriver driver){
        this.driver = driver;
        driverWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void visit(String url) {
        driver.get(url);
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

    public static void click(WebElement element){
        try{
            driverWait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (Exception e){
            Reporter.log("Element not visible. ");
        }
    }

    public void enterText(WebElement element, String textToEnter){
        driverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(textToEnter);
    }

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static Integer getCurrentPage(){
        //code to get the current page
        try {
            driver.findElement(By.xpath(page));
            WebElement element = stringToWebElement(page);
            String currentPage = element.getText();
            String [] str = currentPage.split(" ");
            return Integer.parseInt(str[1]);
        }catch (NoSuchElementException e){
            return null;
        }
    }
    public static void lookForTheField(FormContentPojo contentPojo, String strFieldId) {
        Integer currentPage = getCurrentPage();
        int pageCounter = 0;
        //loop through the pages
        if(currentPage!=null){
            outerLoop:
            for (com.Formic.OF2.utils.Pojo.Page page: contentPojo.data.project.getPages()
            ) {
                //loop through the objects of the page
                ++pageCounter;
                for (com.Formic.OF2.utils.Pojo.Object objects: page.getObjects()
                ) {
                    if(objects.getSubQuestionFields()!=null){
                        for (com.Formic.OF2.utils.Pojo.SubQuestionField item: objects.getSubQuestionFields()
                        ) {
                            String test = item.getGuidId();
                            if(test.equalsIgnoreCase(strFieldId)){
                                break outerLoop;
                            }
                        }
                    }else if(objects.getFieldId()!=null){
                        String test = objects.getFieldId();
                        if(test.equalsIgnoreCase(strFieldId)){
                            break outerLoop;
                        }
                    }
                }
            }
            if(currentPage < pageCounter){
                int nextPageCounter = pageCounter - currentPage;
                for(int x = 1; x<=nextPageCounter; ++x){
                    clickNextPage();
                }
            }else if(currentPage > pageCounter){
                int previousPageCounter = currentPage - pageCounter;
                for(int x = 1; x<=previousPageCounter; ++x){
                    clickPreviousPage();
                }
            }
        }
    }

    public boolean isElementClickable(WebElement element){
        boolean result = false;
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(element));
            result = true;
        }catch (TimeoutException e){
//            recordScreenshot();
            Reporter.log("Element wasn't clickable");
            result =false;
        }
        return result;
    }

    public static void clickNextPage() {
        WebElement element = stringToWebElement(nextPageButton);
        click(element);
    }
    public static void clickPreviousPage() {
        WebElement element = stringToWebElement(previousPageButton);
        click(element);
    }

    public static String getFieldName(FormContentPojo pojo, String strFieldId){
        String fieldName = "";
        for (com.Formic.OF2.utils.Pojo.Field fields:pojo.data.getProject().getFields()
        ) {
            if(fields.getGuidId()!=null){
                if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                    fieldName = fields.getName();
                }
            }
        }
        return fieldName;
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

    public static String stringReplace(String element, String stringToReplace){
        element = element.replace("$TEXT",stringToReplace);
        return element;
    }

    public static String stringReplaceTwoValues(String elementOne, String stringToReplaceOne, String stringToReplaceTwo){
        elementOne = elementOne.replace("$TEXT",stringToReplaceOne);
        elementOne = elementOne.replace("$NUM",stringToReplaceTwo);
        return elementOne;
    }

    public static WebElement convertByToWebElement(String element){
        By elem = By.xpath(element);
//        scrollElementIntoView(driver,driver.findElement(elem));
        WebElement webElem = driverWait.until(ExpectedConditions.presenceOfElementLocated(elem));
        return webElem;
    }

    public static boolean isFieldIdCheckBox(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: page.getObjects()
            ) {
                if(object.getTypename()!=null&&object.getTypename().equalsIgnoreCase("TickboxGroup")){
                    for (com.Formic.OF2.utils.Pojo.SubQuestionField sub: object.getSubQuestionFields()
                    ) {
                        if(sub.getGuidId().equalsIgnoreCase(strFieldId)){
                            result=true;
                            break outerLoop;
                        }
                    }
                }

            }
        }
        return result;
    }

    public static boolean getCheckboxRulesForMaximumInputs(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                if(fields.getResponses()!=null&&fields.getResponses().getMaximum()!=0&&isFieldIdCheckBox(pojo,strFieldId)){
                    CheckboxObject.mandatory = fields.getMandatory();
                    CheckboxObject.maximum = fields.getResponses().getMaximum();
                    CheckboxObject.checkboxName = fields.getName();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean getCheckboxRulesForMinimumInputs(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                if(fields.getResponses()!=null&&fields.getResponses().getMinimum()!=0){
                    CheckboxObject.mandatory = fields.getMandatory();
                    CheckboxObject.minimum = fields.getResponses().getMinimum();
                    CheckboxObject.checkboxName = fields.getName();
                    return true;
                }
            }
        }
        return false;
    }

    public static WebElement stringReplaceAndConvertToWebElement(String elem, String stringToReplace){
        elem = stringReplace(elem,stringToReplace);
        WebElement element = convertByToWebElement(elem);
        return element;
    }

    public static boolean elementClickable(WebElement element){
        try {
            if(element.isEnabled()){
                return true;
            }
            return false;
        }catch (TimeoutException e){
//            recordScreenshot();
            Reporter.log("Element wasn't clickable");
            return false;
        }
    }

    public static void elementVisible(WebElement element){
        try {
            driverWait.until(ExpectedConditions.visibilityOf(element));
        }catch (TimeoutException e){
//            recordScreenshot();
            Reporter.log("Element wasn't visible.");
        }
    }

    public static int numberOfElementsVisible(String element){
        WebElement elem = stringToWebElement(element);
        scrollElementIntoView(driver,elem);
        elementVisible(elem);
        return driver.findElements(By.xpath(element)).size();
    }

    public void pressArrowDown(WebElement element){
        element.sendKeys(Keys.DOWN);
    }

    public void pressEnter(WebElement element){
        element.sendKeys(Keys.ENTER);
    }

    public static String getObjectIdFromFieldId(FormContentPojo pojo, String strWhenFieldId){
        String elementId = null;
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object obj: pages.getObjects()
            ) {
                if(obj.getSubQuestionFields()!=null){
                    for (com.Formic.OF2.utils.Pojo.SubQuestionField sub: obj.getSubQuestionFields()
                    ) {
                        if(sub.getGuidId().equalsIgnoreCase(strWhenFieldId)){
                            elementId = obj.getGuidId();
                            break  outerLoop;
                        }
                    }
                } else if(obj.getFieldId()!=null){
                    if(obj.getFieldId().equalsIgnoreCase(strWhenFieldId)){
                        elementId = obj.getGuidId();
                        break  outerLoop;
                    }
                }
            }
        }
        return elementId;
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
    protected static void scrollElementIntoView(WebDriver driver, WebElement element) {
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
    protected static boolean isElementVisible(WebDriver driver, String xpath) {
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
            elementVisible(element);
            return driver.findElements(By.xpath(getXpathOfWebElement(element))).size() > 0;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean isElementPresentBy(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
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
        elementVisible(element);
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

    public static String getFieldIdByObjectId(FormContentPojo pojo, String strObjectId){
        String result = "";
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Page page : pojo.data.getProject().getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object : page.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getGuidId().equalsIgnoreCase(strObjectId)){
                        if(object.getFieldId()!=null){
                            result = object.getFieldId();
                        }else{
                            for ( com.Formic.OF2.utils.Pojo.SubQuestionField sub : object.getSubQuestionFields()
                            ) {
                                if(sub.getGuidId()!=null){
                                    result = sub.getGuidId();
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static String alphaNumericInputs(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "123xyz";
        String output ="";
        for (int x = 0; x<hroMaximumInputAllowed;x++){
            output = output + (rnd.nextInt(chars.length()));
        }
        Reporter.log("<b>input text: </b>"+ output);
        return output;
    }

    public String getElementIdByFieldName(FormContentPojo pojo, String strFieldName){
        String result = "";
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Field field : pojo.data.project.getFields()
        ) {
            if (field.getName().equalsIgnoreCase(strFieldName)){
                String guidId = field.getGuidId();
                result = getObjectIdFromFieldId(pojo,guidId);
                break outerLoop;
            }
        }
        return result;
    }

    public static String alphaInputs(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String chars = "abcxyz";
        String output ="";
        if(hroMaximumInputAllowed==0){
            hroMaximumInputAllowed=5;
        }
        for (int x = 0; x<hroMaximumInputAllowed;x++){
            output = output + (chars.charAt(rnd.nextInt(chars.length())));
        }
        Reporter.log("<b>input text: </b>"+ output);
        return output;
    }

    public String specialCharacterInputs(FormContentPojo pojo, String strFieldId){
        Random rnd = new Random();
        String chars []= {"!","@","#","$","%","^","&","*","(",")","_","+","=","<",">","/"};
        String output ="";
        for (int x = 0; x<10;x++){
            output = output + chars[rnd.nextInt(16)];
        }
        Reporter.log("<b>input text: </b>"+ output);
        return output;
    }

    public String getNumberOfUnderscore(){
        return CheckboxObject.strFormatMask;
    }

    public static String numericInputs(FormContentPojo pojo, String strFieldId, int hroMaximumInputAllowed){
        Random rnd = new Random();
        String num ="";
        for (int x = 0; x<hroMaximumInputAllowed;x++){
            num = num + (rnd.nextInt(9)+1);
        }
        Reporter.log("<b>input number: </b>"+ num);
        return num;
    }

    public String removeZeroAtTheBeginning(String num){
        String strNum = Long.toString(Long.parseLong(num)); // convert integer to string
        if (strNum.charAt(0) == '0') {
            strNum = strNum.substring(1); // remove leading zero
        }
        return  strNum;
    }

    public static WebElement stringToWebElement(String element){
        WebElement elem;
        try{
            driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
            elem = driver.findElement(By.xpath(element));
            elementVisible(elem);
        }catch (TimeoutException e){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.body.style.zoom='80%'");
            scrollElementIntoView(driver,driver.findElement(By.xpath(element)));
            elem = driver.findElement(By.xpath(element));
        }
        return elem;
    }

    public String inputTelephoneNumber(FormContentPojo pojo, String strFieldId){
        Random rnd = new Random();
        int min = 1;
        int max = 9;
        String num ="0";
        for (int x = 0; x<10;x++){
            num = num + (rnd.nextInt(max-min));
        }
        Reporter.log("<b>input number: </b>"+ num);
        return num;
    }

    public String emailAddressInputs(){
        Random rand = new Random();
        String[] emailProviders = {"gmail.com", "yahoo.com", "hotmail.com", "aol.com", "outlook.com"};
        String[] names = {"john", "jane", "doe", "smith", "miller"};
        int randomNameIndex = rand.nextInt(names.length);
        int randomProviderIndex = rand.nextInt(emailProviders.length);
        return names[randomNameIndex] + "@" + emailProviders[randomProviderIndex];
    }

    public static String getFieldNameByElementId(FormContentPojo pojo, String strElementId){
        String strFieldId="";
        String strFieldName ="";
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object : page.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getGuidId().equalsIgnoreCase(strElementId)){
                        if(object.getTypename().equalsIgnoreCase("TickboxGroup")){

                            for (com.Formic.OF2.utils.Pojo.SubQuestionField sub : object.getSubQuestionFields()
                            ) {
                                strFieldId = sub.getGuidId();
                            }
                        }else if(object.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")||object.getTypename().equalsIgnoreCase("ManualImageAreaText")){
                            strFieldId = object.getFieldId();
                        }
                    }
                }
            }
        }
        for (com.Formic.OF2.utils.Pojo.Field field: pojo.data.project.getFields()
        ) {
            if(field.getGuidId().equalsIgnoreCase(strFieldId)){
                strFieldName = field.getName();
            }
        }
        return strFieldName;
    }

    public boolean isElementId(FormContentPojo pojo, String strElementId){
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.getProject().getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object : page.getObjects()
            ) {
                if(object.getGuidId()!=null&&object.getGuidId().equalsIgnoreCase(strElementId)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isElementIdCheckbox(FormContentPojo pojo, String strElementId){
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.getProject().getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object : page.getObjects()
            ) {
                if(object.getTypename()!=null){
                    if(object.getTypename().equalsIgnoreCase("TickboxGroup")){
                        if(object.getGuidId()!=null&&object.getGuidId().equalsIgnoreCase(strElementId)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isElementIdHro(FormContentPojo pojo, String strElementId){
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.getProject().getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object : page.getObjects()
            ) {
                if(object.getTypename()!=null){
                    if(object.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")){
                        if(object.getGuidId()!=null&&object.getGuidId().equalsIgnoreCase(strElementId)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isElementIdMia(FormContentPojo pojo, String strElementId){
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.getProject().getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object : page.getObjects()
            ) {
                if(object.getTypename()!=null){
                    if(object.getTypename().equalsIgnoreCase("ManualImageAreaText")){
                        if(object.getGuidId()!=null&&object.getGuidId().equalsIgnoreCase(strElementId)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isElementIdPicklist(FormContentPojo pojo, String strElementId){
        for (com.Formic.OF2.utils.Pojo.Page page: pojo.data.getProject().getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object : page.getObjects()
            ) {
                if(object.getTypename()!=null){
                    if(object.getTypename().equalsIgnoreCase("PickList")){
                        if(object.getGuidId()!=null&&object.getGuidId().equalsIgnoreCase(strElementId)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
