package Pages;
import Objects.CheckboxObject;
import Pojo.FormContentPojo;
import Pojo.RulesGraphql;
//import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CheckBoxPage extends BasePage{

    String validationMessageUponSubmitSideBar = "//h4[contains(text(),'Completion Errors')]//following-sibling::ul//div[contains(text(),'$TEXT')]//following::div[1]";
    String mandatoryFieldMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";
    String validationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div[2]";
    String fieldsetLocator = "//div[@data-object-id='$TEXT']/div/fieldset/input[1]";
    String ManualImageAreaText = "//div[@data-object-id='$TEXT']/div/textarea";
    String HandwritingRecognitionObject = "//div[@data-object-id='$TEXT']/div/input";
    String actionLocator = "//div[@data-object-id='$TEXT']/div/fieldset/input[$NUM]";
    String nextPageButton = "//li//button//div[contains(text(),'Next Page')]";
    String newValidationMessageUponSubmit = "//div[@data-object-id='$TEXT']/div/div/div[2]";
    String checkboxLocator = "//div[@data-object-id='$TEXT']/div/fieldset/input";
    String page = "//div[@data-testid='page-progress']";
    String previousPageButton = "//li//button//div[contains(text(),'Previous Page')]";
    String checkboxElementToBeClickedLocator = "//div[@data-object-id='$TEXT']/div/fieldset/input[$NUM]";
    //    String countCheckboxMatrixAvailableInputLocator = "//input[@data-field-id='$TEXT']";
    String checkboxMatrixElementToBeClickedLocator = "//input[@data-field-id='$TEXT'][$NUM]";
    String checkboxHiddenLabelLocator = "//div[@data-object-id='$TEXT']/div/fieldset/label";

    public HandwritingRecognitionObject hro = new HandwritingRecognitionObject(driver);
    public SideMenuNavigation sideMenuNavigation = new SideMenuNavigation(driver);
    public CheckboxObject checkboxObject = new CheckboxObject();
    public Stack<String> STACK = new Stack<>();
    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }

    //don't remove this
//    public Integer countCheckboxMatrixInputs(String strFieldId){
//        String element = stringReplace(countCheckboxMatrixAvailableInputLocator,strFieldId);
//        driverWait.until(ExpectedConditions.visibilityOf(convertByToWebElement(element)));
//        return driver.findElements(By.xpath(element)).size();
//    }

    public void validateCheckboxMandatory() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(136);
        getFieldItems(graphResponse);
        int num = 1;
        for (var fieldId: CheckboxObject.fieldId
        ) {
            String name;
            CheckboxObject.strFieldId = fieldId;
            CheckboxObject.isMandatoryFieldTest = true;
            if(getCheckboxRulesForMandatory(graphResponse, fieldId)
                    &&!isFieldIdCheckBoxMatrix(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                name = CheckboxObject.checkboxName;
                System.out.println(num+". "+name+" is being tested." );
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    System.out.println(name+ " Is enabled");
                    AssertMandatoryFields(graphResponse,getElementIdFromWhenFieldId(graphResponse,fieldId),fieldId);
                }
            }
            num++;
        }
    }

    public void validateCheckboxMinimumValidationUponSubmit() throws Exception {

        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(136);
        getFieldItemsMinimumInputs(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (var fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.minimumConfig = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getCheckboxRulesForMinimumInputs(graphResponse,fieldId)
                    &&!isFieldIdCheckBoxMatrix(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    assertMinimumConfig(graphResponse,fieldId);
                }
            }
        }
    }

    public void validateCheckboxBeyondMaximumInputsUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(136);
        getFieldItemsMinimumInputs(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (var fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.beyondMaximumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            if(getCheckboxRulesForMaximumInputs(graphResponse,fieldId)
                    &&!isFieldIdCheckBoxMatrix(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    String name = CheckboxObject.checkboxName;
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    assertWithinMaximumInput(graphResponse,fieldId);
                }
            }
        }
    }

    public void validateCheckboxMaximumInputsWithinLimitUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(136);
        getFieldItemsMinimumInputs(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (var fieldId: CheckboxObject.fieldId
        ) {
            checkboxObject.withinMaximumInputs = true;
            checkboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getCheckboxRulesForMaximumInputs(graphResponse,fieldId)
                    &&!isFieldIdCheckBoxMatrix(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    assertWithinMaximumInput(graphResponse,fieldId);
                }
            }
        }
    }

    public void validateEnableDisableFieldsCheckbox() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(136);
        //check the ID in the Routing = fieldId and action is enabled
        getRoutingItems(graphResponse);
        int indexNumber = 0;
        int num =1;
        String name;
        for (var fieldId: CheckboxObject.fieldId
        ) {
            checkboxObject.strFieldId = fieldId;
            checkboxObject.isEnableDisabledFields = true;
            name = getElementName(graphResponse,fieldId);
            System.out.println(num+". "+name+" is being tested." );
            if(!isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                System.out.println("FieldId is not checkboxmatrix, we proceed to check the conditions.");
                checkListOfConditions(graphResponse,fieldId,indexNumber);
                System.out.println("--------------------------------------------------------------------------------------------------------------" );
            }
            else{
                System.out.println("This fieldId: "+fieldId+" with an elementId: "+getElementIdFromWhenFieldId(graphResponse,fieldId)+" is a checkbox matrix so we skip");
            }
            indexNumber++;
            num++;
        }
    }

    public void validateCheckboxLessThanMinimumInputsUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(136);
        getFieldItemsMinimumInputs(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (var fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.lessThanMinimumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getCheckboxRulesForMinimumInputs(graphResponse,fieldId)
                    &&!isFieldIdCheckBoxMatrix(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    assertLessThanMinimumInput(graphResponse,fieldId);
                }
            }
        }
    }

    public void validateCheckboxWithinMinimumInputsUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(136);
        getFieldItems(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (var fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.withinMinimumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getCheckboxRulesForMinimumInputs(graphResponse, fieldId)
                    &&!isFieldIdCheckBoxMatrix(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    assertWithinMinimumInput(graphResponse,fieldId);
                }
            }
        }
    }

    public void validateSubmittedInputsCheckbox() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(136);
        getFieldItemsMinimumInputs(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (var fieldId: CheckboxObject.fieldId
        ) {
                CheckboxObject.withinMinimumMaximumInputs = true;
                CheckboxObject.strFieldId = fieldId;
                if(hro.isFieldIdHro(graphResponse,fieldId)){
                    hro.getHroRules(graphResponse,fieldId); //d71849e253ab484687b88d4e4405bb7d
                }else{
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,fieldId);
                }
                String name = CheckboxObject.checkboxName;
                if(!isFieldIdCheckBoxMatrix(graphResponse,fieldId)
                        &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)
                        &&isFieldIdHasElementId(graphResponse,fieldId))
                {
                    if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                        //meaning this fieldId maybe disabled, and we need to enable it.
                        System.out.println(name+ " Needs to check if it's disabled");
                        checkListOfConditions(graphResponse,fieldId);
                    }else {
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        assertWithinMinimumMaximumInput(graphResponse,fieldId);
                    }
                }
        }
        sideMenuNavigation.clickSubmitButton();
        String receipt = getProjectReceipt();
        clickContinueButton();
        clickSavedFormsButton();
        enterReceiptNumber(receipt);
        clickGoButton();
        System.out.println(receipt);
        validateInputsAreCorrect(graphResponse);
    }

    public boolean skipSingleHiddenCheckbox(FormContentPojo pojo, String fieldId){
        for (var pages: pojo.data.project.getPages()
        ) {
            for (var objects: pages.getObjects()
            ) {
                if(objects.getSubQuestionFields()!=null){
                    for (var sub: objects.getSubQuestionFields()
                    ) {
                        if(sub.getGuidId()!=null){
                            if(fieldId.equalsIgnoreCase(sub.getGuidId())){
                                if(objects.getTickboxResponses()!=null&&objects.getTickboxResponses().size()==1){
                                    for (var tickBox: objects.getTickboxResponses()
                                    ) {
                                        if(tickBox.getBox().isHidden()){
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }




    /***
     * Some fieldId in graphql doesn't have any elementId
     * that's why we need to filter items that can be tested
     * FieldId with ElementId only
     * @return true if the fieldId does have an elementId
     */
    public boolean isFieldIdHasElementId(FormContentPojo pojo, String strFieldId){
        for (var pagesObject: pojo.data.project.getPages()
        ) {
            for (var objects : pagesObject.getObjects()
            ) {

                if(objects.getTypename()!=null){
                    if(objects.getTypename().equalsIgnoreCase("TickboxGroup")){
                        for (var sub: objects.getSubQuestionFields()
                        ) {
                            if(sub.getGuidId().equalsIgnoreCase(strFieldId)){
                                return true;
                            }
                        }
                    } else if (objects.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")) {
                        if(objects.getFieldId().equalsIgnoreCase(strFieldId)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void validateInputsAreCorrect(FormContentPojo pojo){
        String strElementId = "";
        String strCheckBoxId = null;
        String strHroId = null;
        //57ce
        for (var value: CheckboxObject.checkboxInputs
        ) {
            strElementId = getElementIdFromWhenFieldId(pojo,value)==null? value : getElementIdFromWhenFieldId(pojo,value);
            if(isElementId(pojo,strElementId)){

                if(isElementIdCheckbox(pojo,strElementId)){
                    checkboxObject.isCheckbox = true;
                    checkboxObject.isHro = false;
                    lookForTheField(pojo,value);
                    strCheckBoxId = strElementId;
                } else if (isElementIdHro(pojo,strElementId)) {
                    checkboxObject.isHro = true;
                    checkboxObject.isCheckbox = false;
                    strHroId = strElementId;
                    lookForTheField(pojo,value);
                }
            }else{
                if(checkboxObject.isCheckbox){
                    String elem = stringReplaceTwoValues(actionLocator,strCheckBoxId,value);
                    System.out.println(elem);
                    WebElement element = stringToWebElement(elem);
                    scrollElementIntoView(driver,element);
                    Assert.assertTrue(element.isSelected());
                } else if (checkboxObject.isHro) {
                    String text = hro.getHroTextFromElementId(strHroId);
                    System.out.println(text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                }
            }
        }
    }

    public boolean isElementId(FormContentPojo pojo, String strElementId){
        for (var page: pojo.data.getProject().getPages()
        ) {
            for (var object : page.getObjects()
            ) {
                if(object.getGuidId()!=null&&object.getGuidId().equalsIgnoreCase(strElementId)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isElementIdCheckbox(FormContentPojo pojo, String strElementId){
        for (var page: pojo.data.getProject().getPages()
        ) {
            for (var object : page.getObjects()
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
        for (var page: pojo.data.getProject().getPages()
        ) {
            for (var object : page.getObjects()
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

    public String getProjectReceipt(){
        String test = "//h2[contains(text(),'Complete')]/ancestor::main/p[contains(text(),'You can use the following receipt to recall this particular form at a later date.')]";
        WebElement elem = stringToWebElement(test);
        String text = elem.getText();
        String [] strReceipt;
        strReceipt = text.split(". ");

        return strReceipt[15];
    }

    public void clickContinueButton(){
        String continueBtn = "//button[text()='Continue']";
        WebElement element = stringToWebElement(continueBtn);
        click(element);
    }

    public void clickSavedFormsButton(){
        String continueBtn = "//span[text()='Saved Forms']";
        WebElement element = stringToWebElement(continueBtn);
        click(element);
    }

    public void enterReceiptNumber(String strReceipt){
        String strReceiptNumber = "//input[@id='receipt-entry-input']";
        WebElement element = stringToWebElement(strReceiptNumber);
        enterText(element,strReceipt);
    }

    public void clickGoButton(){
        String strGoButton = "//button[text()='Go']";
        WebElement element = stringToWebElement(strGoButton);
        click(element);
    }

    public void assertLessThanMinimumInput(FormContentPojo pojo, String strFieldId) {
        String elementId = getElementIdFromWhenFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        int numberOfInputs = clickLessThanMinimumInput(CheckboxObject.minimum,elementId,numberOfItems);
        if(CheckboxObject.minimum==1&&numberOfInputs==1||numberOfInputs==0){
            assertRequiredField(elementId,CheckboxObject.checkboxName);
        }else{
            assertRequiresMinimumOf(CheckboxObject.checkboxName,elementId,CheckboxObject.minimum);
        }
    }

    public void assertWithinMinimumInput(FormContentPojo pojo, String strFieldId) {
        String elementId = getElementIdFromWhenFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickWithinMinimumInput(CheckboxObject.minimum,elementId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,elementId,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public void assertWithinMinimumMaximumInput(FormContentPojo pojo, String strFieldId) {
        String elementId = getElementIdFromWhenFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(hro.isFieldIdHro(pojo,strFieldId)){
            if(Objects.requireNonNull(hro.hroDataType()).equalsIgnoreCase("NUMERIC")){
                hro.numericInputs(pojo,strFieldId,hro.identifyMaximumInputsByFieldId());
            } else if (Objects.requireNonNull(hro.hroDataType()).equalsIgnoreCase("ALPHA_NUMERIC")) {
                hro.alphaNumericInputs(pojo,strFieldId, hro.identifyMaximumInputsByFieldId());
            }else if (Objects.requireNonNull(hro.hroDataType()).equalsIgnoreCase("ALPHA")) {
                hro.alphaInputs(pojo,strFieldId, hro.identifyMaximumInputsByFieldId());
            }
        }else if(isFieldIdCheckBox(pojo,strFieldId)){
            int numberOfItems = countCheckboxItems(elementId);
            clickWithinMinimumMaximumInput(pojo,CheckboxObject.minimum,CheckboxObject.maximum,elementId,numberOfItems);
        }

        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,elementId,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public void assertBeyondMaximumInput(FormContentPojo pojo, String strFieldId) {
        String elementId = getElementIdFromWhenFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickBeyondMaximumInput(pojo,CheckboxObject.maximum,strFieldId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,elementId,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public void assertWithinMaximumInput(FormContentPojo pojo, String strFieldId) {
        String elementId = getElementIdFromWhenFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickWithinMaximumInput(pojo,CheckboxObject.maximum,strFieldId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,elementId,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public void assertMinimumConfig(FormContentPojo pojo, String strFieldId) {
        String elementId = getElementIdFromWhenFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(CheckboxObject.minimum==1){
            assertRequiredField(elementId,CheckboxObject.checkboxName);
        }else {
            assertMinimumConfigNoInputs(elementId,CheckboxObject.checkboxName,CheckboxObject.minimum);
        }
    }

    public boolean getCheckboxRulesForMandatory(FormContentPojo pojo, String strFieldId){
        for (var fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                if(fields.getResponses()!=null&& fields.getMandatory()){
                    CheckboxObject.mandatory = fields.getMandatory();
                    CheckboxObject.checkboxName = fields.getName();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getCheckboxRulesForMinimumInputs(FormContentPojo pojo, String strFieldId){
        for (var fields: pojo.data.project.getFields()
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

    public boolean getCheckboxRulesForMinimumAndMaximumInputs(FormContentPojo pojo, String strFieldId){
        for (var fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)&&isFieldIdCheckBox(pojo,strFieldId)){
                if(fields.getResponses()!=null){
                    if(fields.getResponses().getMinimum()!=0){
                        CheckboxObject.mandatory = fields.getMandatory();
                        CheckboxObject.minimum = fields.getResponses().getMinimum();
                        CheckboxObject.checkboxName = fields.getName();
                    }

                    if(fields.getResponses().getMaximum()!=0){
                        CheckboxObject.mandatory = fields.getMandatory();
                        CheckboxObject.maximum = fields.getResponses().getMaximum();
                        CheckboxObject.checkboxName = fields.getName();
                    }
                    return true;
                }
            }else if(fields.getGuidId().equalsIgnoreCase(strFieldId)&& hro.isFieldIdHro(pojo,strFieldId)){
                hro.getHroRules(pojo,strFieldId);
            }
        }
        return false;
    }

    public boolean getCheckboxRulesForMaximumInputs(FormContentPojo pojo, String strFieldId){
        for (var fields: pojo.data.project.getFields()
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

    public boolean isFieldIdCheckBox(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        outerLoop:
        for (var page: pojo.data.project.getPages()
        ) {
            for (var object: page.getObjects()
            ) {
                if(object.getTypename()!=null&&object.getTypename().equalsIgnoreCase("TickboxGroup")){
                    for (var sub: object.getSubQuestionFields()
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


//    public int countCheckboxItems(String strObjectElementId){
//        String element = stringReplace(checkboxLocator,strObjectElementId);
//        return numberOfElementsVisible(element);
//    }

    public int countCheckboxItems(String strObjectElementId){
        String element = stringReplace(checkboxLocator,strObjectElementId);
        int numberOfElements =  numberOfElementsVisible(element);
        for(int x = 1; x <= numberOfElements; x++){
            element = stringReplaceTwoValues(actionLocator,strObjectElementId,Integer.toString(x));
            WebElement elem = stringToWebElement(element);
            if(elem.isSelected()){
                CheckboxObject.hasValueList.add(Integer.toString(x));
            }
        }
        return numberOfElements;
    }

    public void assertWithinAcceptedInputs(String strName,String strObjectElementId){
        String elemValidationMessage = stringReplace(newValidationMessageUponSubmit,strObjectElementId);
        String elemCompilationErrors = stringReplace(validationMessageUponSubmitSideBar,strObjectElementId);
        Assert.assertFalse(isElementVisible(driver,elemValidationMessage),"The expected for : "+strName + " There shouldn't be any validation message displayed below the object.");
        Assert.assertFalse(isElementVisible(driver,elemCompilationErrors),"The expected for: "+strName + "Completion Errors isn't displayed. The actual is Completion errors is displayed.");
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public void assertMinimumConfigNoInputs(String strObjectElementId, String name, Integer minimumInput){
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(validationMessageLocator,strObjectElementId);

        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"This field requires a minimum of "+minimumInput+" responses.","The expected value is : This field requires a minimum of "+minimumInput+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
        Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field requires a minimum of "+minimumInput+" responses.","The expected value is : This field requires a minimum of "+minimumInput+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public void assertRequiredField(String strObjectElementId, String name){
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(validationMessageLocator,strObjectElementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Assert.assertEquals(ValidationMessageSideMenu.getText(),"Required field.","The expected value is : Required field. "+ValidationMessageSideMenu.getText());
        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Required field. "+validationMessageUnderCheckbox.getText());
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public void assertRequiresMinimumOf(String name,String strObjectElementID, int minNumberOfInputs){
        WebElement ValidationMessageSideMenu = stringReplaceAndConvertToWebElement(validationMessageUponSubmitSideBar, name);
        scrollElementIntoView(driver,ValidationMessageSideMenu);
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(newValidationMessageUponSubmit,strObjectElementID);

        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"This field requires a minimum of "+minNumberOfInputs+" responses.","The expected value is : This field requires a minimum of "+minNumberOfInputs+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
        Assert.assertEquals(ValidationMessageSideMenu.getText(),"This field requires a minimum of "+minNumberOfInputs+" responses.","The expected value is : This field requires a minimum of "+minNumberOfInputs+" responses. but the actual message is "+validationMessageUnderCheckbox.getText());
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public boolean isFieldIdInRoutingRules(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        loopBreak:
        for (var routing: pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (var conditions: routing.getConditions()
                ) {
                    if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                        if(conditions.getAction().equalsIgnoreCase("enable")){
                            result = true;
                            break loopBreak;
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean isFieldIdInRoutingRulesWhenFieldDisable(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        loopBreak:
        for (var routing: pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (var conditions: routing.getConditions()
                ) {
                    if(conditions.getWhenField().equalsIgnoreCase(strFieldId)){
                        if(conditions.getAction().equalsIgnoreCase("disable")){
                            result = true;
                            break loopBreak;
                        }
                    }

                }
            }
        }
        return result;
    }

    public void AssertMandatoryFields(FormContentPojo pojo, String strElementId, String strFieldId) {
        lookForTheField(pojo,strFieldId);
        WebElement validationMessageLocator = stringReplaceAndConvertToWebElement(mandatoryFieldMessageLocator,strElementId);
        scrollElementIntoView(driver,validationMessageLocator);
        String fieldName = getFieldName(pojo,strFieldId);
        Assert.assertEquals(validationMessageLocator.getText(),"This field is mandatory.", fieldName+"The expected validation message for "+fieldName+" was: This field is mandatory. but the actual message was: "+validationMessageLocator.getText());
        Reporter.log("Checkbox Name: "+fieldName+" is mandatory.",true);
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public String getFieldName(FormContentPojo pojo,String strFieldId){
        String fieldName = "";
        for (var fields:pojo.data.getProject().getFields()
        ) {
            if(fields.getGuidId()!=null){
                if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                    fieldName = fields.getName();
                }
            }
        }
        return fieldName;
    }

    public int clickBeyondMaximumInput(FormContentPojo pojo,int maxInput,String strFieldId,int elementCountInACheckbox){
        String elem;
        WebElement element;
        Random rng = new Random();
        Set<String> generated = new LinkedHashSet<String>();

        //if the number of checkboxes available to a certain checkbox is equal to the amount of Max input, do not add+1 because this will trigger an
        //infinite loop in the while loop below
        maxInput = elementCountInACheckbox == maxInput ? maxInput : maxInput+1;

        while (generated.size() < maxInput)
        {
            String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
            generated.add(next);
        }
        String[] gen = generated.toArray(new String[generated.size()]);

        for(int x = 0; x<generated.size();x++){
            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator,getElementIdFromWhenFieldId(pojo,strFieldId),gen[x]);
            element = stringToWebElement(elem);

            if(maxInput!=1){
                scrollElementIntoView(driver,element);
                if(!element.isSelected()) {
                    click(element);
                }
            }else{
                scrollElementIntoView(driver,element);
                if(!element.isSelected()) {
                    click(element);
                }
                return 1;
            }
        }
        return generated.size();
    }

    public int clickWithinMaximumInput(FormContentPojo pojo,int maxInput,String strFieldId,int elementCountInACheckbox){
        String elem;
        WebElement element;
        Random rng = new Random();
        Set<String> generated = new LinkedHashSet<String>();
        //if the number of checkboxes available to a certain checkbox is equal to the amount of Max input, do not add+1 because this will trigger an
        //infinite loop in the while loop below
        while (generated.size() < maxInput)
        {
            String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
            generated.add(next);
        }
        String[] gen = generated.toArray(new String[generated.size()]);

        for(int x = 0; x<generated.size();x++){
            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator,getElementIdFromWhenFieldId(pojo,strFieldId),gen[x]);
            element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            if(!element.isSelected()) {
                click(element);
            }
        }
        return generated.size();
    }

    public int clickWithinMinimumInput(int minInput,String strObjectElementId,int elementCountInACheckbox){
        Set<String> generated = new LinkedHashSet<String>();
        if(minInput != 0){
            String elem;
            WebElement element;
            Random rng = new Random();
            while (generated.size() < minInput)
            {
                String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
                generated.add(next);
            }
            String[] gen = generated.toArray(new String[generated.size()]);
            for(int x = 0; x<generated.size();x++){
                elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator,strObjectElementId,gen[x]);
                scrollElementIntoView(driver,stringToWebElement(stringReplace(checkboxLocator,strObjectElementId)));
                element = stringToWebElement(elem);
                scrollElementIntoView(driver,element);
                if(!element.isSelected()) {
                    click(element);
                }
            }
        }
        else {
            return 0;
        }
        return generated.size();
    }

    public String [] minMaximumGeneratedInputs(int min, int max, int elementCount ){
        Set<String> generated = new LinkedHashSet<String>();
        Random rng = new Random();
        if(min != 0){
            while (generated.size() < min)
            {
                String next = Integer.toString(rng.nextInt(elementCount) + 1);
                generated.add(next);
            }
        }else{
            while (generated.size() < max)
            {
                String next = Integer.toString(rng.nextInt(elementCount) + 1);
                generated.add(next);
            }
        }
        String[] gen = generated.toArray(new String[generated.size()]);
        return gen;
    }



    /***
     * This method is created to adjust the set of inputs for a checkbox.
     * The reason is if the Checkbox has a routing rule, this will affect the inputs.
     * We need to include the item on the checkbox that is already selected.
     * For example, generated input for hasValue is 1 and 2, the maximum input for the checkbox is only 2.
     * Then we have 3 is already selected (Selected to enable some checkbox in routing rule). The selected outcome will be 1 and 3, we cant click number 2 because of the rule maximum inputs 2.
     * This will affect the assertion if we validate the inputs for the said checkbox if 1 and 2 isn't selected.
     * @param pojo
     * @param gen is the set of inputs that will be applied to the checkbox.
     * @return gen will be the updated inputs that will include the checkbox that is already selected from the beginning.
     */
    public String [] adjustInputIfAlreadySelected(FormContentPojo pojo,String [] gen){
        ArrayList<String> hasValueEnable = new ArrayList<>();
        int ctr = 0;
        hasValueEnable = getHasValueToEnableFields(pojo,CheckboxObject.strFieldId);
        if(!CheckboxObject.hasValueList.isEmpty()){
            if(!isHasValueEqualToGen(gen)){
                for (var g: hasValueEnable
                ) {
                    if(!g.equalsIgnoreCase(gen[ctr])){
                        gen[ctr] = CheckboxObject.hasValueList.get(0);
                    }
                    ctr++;
                }
            }
        }
        return gen;
    }


    public ArrayList<String> getHasValueToEnableFields(FormContentPojo pojo, String strFieldId){
        ArrayList<String> result = new ArrayList<>();
        for (var routing: pojo.data.project.getRouting()
        ) {
            for (var conditions: routing.getConditions()
            ) {
                if(conditions.getWhenField().equalsIgnoreCase(strFieldId)&&conditions.getAction().equalsIgnoreCase("enable")){
                    result.add(conditions.getHasValue());
                }
            }
        }
        return result;
    }

    public void clickElementHasValue(String [] gen,String strObjectElementId){
        String elem;
        WebElement element;
        for(int x = 0; x<gen.length;x++){
            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator,strObjectElementId,gen[x]);
            scrollElementIntoView(driver,stringToWebElement(stringReplace(checkboxLocator,strObjectElementId)));
            element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            if(!element.isSelected()) {
                click(element);
            }
        }
    }

    public int clickWithinMinimumMaximumInput(FormContentPojo pojo,int minInput,int maxInput,String strObjectElementId,int elementCountInACheckbox){
        String[] gen = minMaximumGeneratedInputs(minInput,maxInput,elementCountInACheckbox);
        adjustInputIfAlreadySelected(pojo,gen);
        recordInputsFromCheckbox(CheckboxObject.strFieldId,gen);
        System.out.println(CheckboxObject.checkboxInputs);
        clickElementHasValue(gen,strObjectElementId);
        return gen.length;
    }

    public boolean isHasValueEqualToGen(String [] gen){
        boolean result = false;
        for (var g: gen
        ) {
            for (var value: CheckboxObject.hasValueList
            ) {
                if (value.equalsIgnoreCase(g)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public void recordInputsFromCheckbox(String strElementId,String [] strHasValue){
        CheckboxObject.checkboxInputs.add(strElementId);
        CheckboxObject.checkboxInputs.addAll(Arrays.asList(strHasValue));
    }


    public int clickLessThanMinimumInput(int minInput,String strObjectElementId, int elementCountInACheckbox){
        Set<String> generated = new LinkedHashSet<String>();
        if(minInput!=1){
            String elem;
            WebElement element;
            Random rng = new Random();
            while (generated.size() < minInput-1)
            {
                String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
                generated.add(next);
            }
            String[] gen = generated.toArray(new String[generated.size()]);
            for (String s : gen) {
                elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator, strObjectElementId, s);
                element = stringToWebElement(elem);
                scrollElementIntoView(driver, element);
                if (!element.isSelected()) {
                    click(element);
                }
            }
        }else{
            //returns 0 when the minimum input in a checkbox is 1 or no settings for minimum input. Because we have different validation message for
            // more than 1 minimum input
            return 0;
        }
        return generated.size();
    }

    //don't remove this.
    public int clickLessThanMinimumInputMatrix(int minInput,String strObjectElementId, int elementCountInACheckbox){
        Set<String> generated = new LinkedHashSet<String>();
        if(minInput!=1){
            String elem;
            WebElement element;
            Random rng = new Random();
            while (generated.size() < minInput-1)
            {
                String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
                generated.add(next);
            }
            String[] gen = generated.toArray(new String[generated.size()]);
            for(int x = 0; x<gen.length;x++){
                elem = stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strObjectElementId,gen[x]);
                element = stringToWebElement(elem);
                scrollElementIntoView(driver,element);
                if(!element.isSelected()) {
                    click(element);
                }
            }
        }else{
            //returns 0 when the minimum input in a checkbox is 1 or no settings for minimum input. Because we have different validation message for
            // more than 1 minimum input
            return 0;
        }
        return generated.size();
    }

    public boolean isFieldIdCheckBoxMatrix(FormContentPojo pojo,String strFieldId){
        boolean flag = false;
        for (var pages: pojo.data.project.getPages()
        ) {
            for (var object: pages.getObjects()
            ) {
                if(object.getSubQuestionFields()!=null){
                    if(object.getSubQuestionFields().size()>1){
                        for (var sub: object.getSubQuestionFields()
                        ) {
                            if(sub.getGuidId().equalsIgnoreCase(strFieldId)){
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }


    public void checkListOfConditions(FormContentPojo pojo, String strFieldId,Integer indexNumber) throws Exception {
        String whenFieldElementId;
        String fieldIdElementId;
        boolean isCheckboxMatrix;
        for (var conditions: pojo.data.project.getRouting().get(indexNumber).getConditions()
        ) {
            fieldIdElementId = getElementIdFromWhenFieldId(pojo,strFieldId);
            if(fieldIdElementId!=null){
                String whenField = conditions.getWhenField();
                whenFieldElementId = getElementIdFromWhenFieldId(pojo,whenField);
                isCheckboxMatrix = isFieldIdCheckBoxMatrix(pojo,whenField);
                if(whenFieldElementId!=null&&!isCheckboxMatrix){
                    String typeName = getTypeName(pojo,strFieldId);
                    STACK.push(conditions.getAction());
                    STACK.push(typeName);
                    STACK.push(strFieldId);
                    STACK.push(conditions.getHasValue());
                    STACK.push(conditions.getWhenField());
                    findTheWhenFieldThatDisablesTheFieldId(pojo,whenField);
                    validateListOfFieldsStacked(pojo);
                }
            }
        }
    }


    public void checkListOfConditions(FormContentPojo pojo, String strFieldId) throws Exception {
        String whenFieldElementId;
        String fieldIdElementId;
        boolean isCheckboxMatrix;
        outerLoop:
        for (var routing: pojo.data.project.getRouting()
        ) {
            for (var conditions: routing.getConditions()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                    fieldIdElementId = getElementIdFromWhenFieldId(pojo,strFieldId);
                    if(fieldIdElementId!=null){
                        String whenField = conditions.getWhenField();
                        whenFieldElementId = getElementIdFromWhenFieldId(pojo,whenField);
                        isCheckboxMatrix = isFieldIdCheckBoxMatrix(pojo,whenField);
                        if(whenFieldElementId!=null&&!isCheckboxMatrix&&conditions.getAction().equalsIgnoreCase("enable")){
                            System.out.println(CheckboxObject.checkboxName+ " is enabled");
                            String typeName = getTypeName(pojo,strFieldId);
                            STACK.push(conditions.getAction());
                            STACK.push(typeName);
                            STACK.push(strFieldId);
                            STACK.push(conditions.getHasValue());
                            STACK.push(whenField);
                            System.out.println("We now check if whenField: "+whenField+ " is enabled");
                            testFindTheWhenFieldThatDisablesTheFieldId(pojo,whenField);
                            validateListOfFieldsStacked(pojo);
                            break outerLoop;
                        }
                    }
                }
            }
        }
    }

    public String getTypeName(FormContentPojo pojo, String strFieldId){
        String result=null;
        for (var pages: pojo.data.project.getPages()
        ) {
            for (var objects: pages.getObjects()
            ) {
                if(objects.getTypename()!=null){
                    if(objects.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")||objects.getTypename().equalsIgnoreCase("ManualImageAreaText")){

                        if(objects.getFieldId().equalsIgnoreCase(strFieldId)){
                            result =  objects.getTypename();
                        }

                    }
                    else if(objects.getTypename().equalsIgnoreCase("TickboxGroup")){
                        if(objects.getSubQuestionFields()!=null)
                        {
                            for (var sub: objects.getSubQuestionFields()
                            ) {
                                if (sub.getGuidId().equalsIgnoreCase(strFieldId)) {
                                    result = objects.getTypename();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * This method has a parameter of strFieldId and strAction
     * strFieldId is supplied by using a whenFieldID.
     * We use this function to identify what field is causing the whenFieldId to be disabled.
     */
    public void findTheWhenFieldThatDisablesTheFieldId(FormContentPojo pojo, String strWhenField) {
        String whenFieldElementId = getElementIdFromWhenFieldId(pojo,strWhenField);
        String typeName = getTypeName(pojo,strWhenField);
        boolean isWhenFieldDisabled;
        lookForTheField(pojo,strWhenField);
        System.out.println("We now proceed to check if the whenfield is disabled.");
        isWhenFieldDisabled = checkIdIfDisabled(whenFieldElementId);
        String whenFieldID;
        if(isWhenFieldDisabled){
            outerLoop:
            for (var routing : pojo.data.project.getRouting()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strWhenField)){
                    if(routing.getConditions()!= null){
                        for (var conditions: routing.getConditions()
                        ) {
                            if(conditions.getAction().equalsIgnoreCase("enable")){
                                //If Null this is a checkbox matrix, and we will skip this one
                                if(getElementIdFromWhenFieldId(pojo, conditions.getWhenField())!=null){
                                    System.out.println("inside findTheWhenFieldThatDisablesTheFieldId if action is enable");
                                    STACK.push(conditions.getAction());
                                    STACK.push(typeName);
                                    STACK.push(routing.getFieldId());
                                    STACK.push(conditions.getHasValue());
                                    STACK.push(conditions.getWhenField());
                                    whenFieldID = conditions.getWhenField();
                                    findTheWhenFieldThatDisablesTheFieldId(pojo,whenFieldID);
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void testFindTheWhenFieldThatDisablesTheFieldId(FormContentPojo pojo, String strWhenField) {
        String whenFieldElementId = getElementIdFromWhenFieldId(pojo,strWhenField);
        String typeName = getTypeName(pojo,strWhenField);
        boolean isWhenFieldDisabled;
        lookForTheField(pojo,strWhenField);
        isWhenFieldDisabled = checkIdIfDisabled(whenFieldElementId);
        String whenFieldID;
        if(isWhenFieldDisabled){
            System.out.println("whenfield is disabled.");
            outerLoop:
            for (var routing : pojo.data.project.getRouting()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strWhenField)){
                    if(routing.getConditions()!= null){
                        for (var conditions: routing.getConditions()
                        ) {
                            if(conditions.getAction().equalsIgnoreCase("enable")){
                                //If Null this is a checkbox matrix, and we will skip this one
                                if(getElementIdFromWhenFieldId(pojo, conditions.getWhenField())!=null&&conditions.getAction().equalsIgnoreCase("enable")){
                                    STACK.push(conditions.getAction());
                                    STACK.push(typeName);
                                    STACK.push(routing.getFieldId());
                                    STACK.push(conditions.getHasValue());
                                    STACK.push(conditions.getWhenField());
                                    whenFieldID = conditions.getWhenField();
                                    System.out.println("We now check if whenField: "+whenFieldID+ " is enabled");
                                    testFindTheWhenFieldThatDisablesTheFieldId(pojo,whenFieldID);
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            System.out.println(strWhenField+"is enabled");
        }
    }

    public void validateListOfFieldsStacked(FormContentPojo pojo) throws Exception {
        String whenFieldId;
        String hasValue;
        String fieldId;
        String typeName;
        String action;
        String whenFieldElementId;
        String fieldIdElementId;
        int hiddenCheckBox;
        for(int x = 0; x < 1;){
            if(!STACK.isEmpty()){
                whenFieldId = STACK.pop();
                hasValue = STACK.pop();
                fieldId = STACK.pop();
                typeName = STACK.pop();
                action = STACK.pop();
                whenFieldElementId = getElementIdFromWhenFieldId(pojo,whenFieldId);
                fieldIdElementId = getElementIdFromWhenFieldId(pojo,fieldId);
                System.out.println("In validation WhenfieldID: "+whenFieldId +" HasValue: "+ hasValue +" FieldId: "+fieldId+" typeName: "+ typeName+" Action: "+ action);
                lookForTheField(pojo,whenFieldId);
                hiddenCheckBox = identifyHiddenCheckBox(pojo,whenFieldId);
                // This will adjust the hasValue when some checkbox is hidden
                if(hiddenCheckBox!=0){
                    hasValue =  adjustHasValueForHiddenFields(hasValue,hiddenCheckBox);
                }
                clickSpecificRadioButton(whenFieldElementId,hasValue);
                lookForTheField(pojo,fieldId);
                if(typeName.equalsIgnoreCase("TickboxGroup")){
                    checkboxEnabledDisabledValidation(fieldIdElementId,action,pojo);
                } else if (typeName.equalsIgnoreCase("HandwritingRecognitionObject")) {
                    checkboxEnabledDisabledValidationForHandwritingRecognitionObject(fieldIdElementId,action,pojo);
                }else if(typeName.equalsIgnoreCase("ManualImageAreaText")){
                    checkboxEnabledDisabledValidationForMia(fieldIdElementId,action,pojo);
                }
                //include here the validation of the fields
                if(action.equalsIgnoreCase("disable")){
                    lookForTheField(pojo,whenFieldId);
                    clickSpecificRadioButtonAlreadyClicked(whenFieldElementId,hasValue);
                    lookForTheField(pojo,fieldId);
                }
                System.out.println("___________________________________________________");
            }
            else {
                x = 1;
            }
        }
        if(!checkboxObject.isEnableDisabledFields){
            if(hro.isFieldIdHro(pojo,CheckboxObject.strFieldId)&&checkboxObject.strFormatRegex!=null){
                if(hro.hroDataType().equalsIgnoreCase("NUMERIC")){
                    hro.numericInputs(pojo,CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                } else if (hro.hroDataType().equalsIgnoreCase("ALPHA_NUMERIC")) {
                    hro.alphaNumericInputs(pojo,CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                }else if (hro.hroDataType().equalsIgnoreCase("ALPHA")) {
                    hro.alphaInputs(pojo,CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                }
            }else if(isFieldIdCheckBox(pojo,CheckboxObject.strFieldId)){
                if(CheckboxObject.isMandatoryFieldTest){
                    AssertMandatoryFields(pojo,getElementIdFromWhenFieldId(pojo,CheckboxObject.strFieldId),CheckboxObject.strFieldId);
                } else if(CheckboxObject.lessThanMinimumInputs){
                    assertLessThanMinimumInput(pojo,CheckboxObject.strFieldId);
                }else if(CheckboxObject.withinMinimumInputs){
                    assertWithinMinimumInput(pojo,CheckboxObject.strFieldId);
                }else if (CheckboxObject.withinMaximumInputs){
                    assertWithinMaximumInput(pojo,CheckboxObject.strFieldId);
                }else if (CheckboxObject.beyondMaximumInputs){
                    assertBeyondMaximumInput(pojo,CheckboxObject.strFieldId);
                }else if(CheckboxObject.minimumConfig){
                    assertMinimumConfig(pojo,CheckboxObject.strFieldId);
                }else if(CheckboxObject.withinMinimumMaximumInputs){
                    assertWithinMinimumMaximumInput(pojo,CheckboxObject.strFieldId);
                }
            }
        }

    }

    public String getElementName(FormContentPojo pojo, String strFieldId){
        String result="";
        for (var fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId()!=null){
                if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                    result = fields.getName();
                    break;
                }
            }
        }
        return result;
    }

    public int identifyHiddenCheckBox(FormContentPojo pojo, String strWhenFieldId){
        int checkBoxNumber = 0;
        for (var pages: pojo.data.project.getPages()
        ) {
            for (var objects: pages.getObjects()
            ) {
                if(objects.getSubQuestionFields()!=null){
                    for (var sub: objects.getSubQuestionFields()
                    ) {
                        if(sub.getGuidId()!=null){
                            if(strWhenFieldId.equalsIgnoreCase(sub.getGuidId())){
                                if(objects.getTickboxResponses()!=null){
                                    for (var tickBox: objects.getTickboxResponses()
                                    ) {
                                        if(tickBox.getBox().isHidden()){
                                            checkBoxNumber = tickBox.getOrdinal();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return checkBoxNumber;
    }

    public String adjustHasValueForHiddenFields(String strHasValue, Integer intHiddenCheckBox){
        int num;
        if(intHiddenCheckBox<Integer.parseInt(strHasValue)){
            num = Integer.parseInt(strHasValue)-1;
            strHasValue = Integer.toString(num);
        }
        return strHasValue;
    }

    public static String getElementIdFromWhenFieldId(FormContentPojo pojo, String strWhenFieldId){
        String elementId = null;
        outerLoop:
        for (var pages: pojo.data.project.getPages()
        ) {
            for (var obj: pages.getObjects()
            ) {
                if(obj.getSubQuestionFields()!=null){
                    for (var sub: obj.getSubQuestionFields()
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

    public boolean checkIdIfDisabled(String objectId) {
        String elem = stringReplace(fieldsetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        return !element.isEnabled();
    }

    public void getRoutingItems(FormContentPojo pojo){
        for (var routing: pojo.data.project.getRouting()
        ) {
            CheckboxObject.fieldId.add(routing.getFieldId());
        }
    }

    public void addFields(String fieldID){

    }

    public void getFieldItems(FormContentPojo pojo){
        for (var routing: pojo.data.project.getRouting()
        ) {
            CheckboxObject.fieldId.add(routing.getFieldId());
        }

        for (var fields: pojo.data.project.getFields()
        ) {
            if(!skipSingleHiddenCheckbox(pojo,fields.getGuidId())){
                CheckboxObject.fieldId.add(fields.getGuidId());
            }
        }
        CheckboxObject.fieldId = removeDuplicates(CheckboxObject.fieldId);
    }

    public void getFieldItemsMinimumInputs(FormContentPojo pojo){
        for (var pages: pojo.data.project.getPages()
        ) {
            for (var object: pages.getObjects()
                 ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")){
                        CheckboxObject.fieldId.add(object.getFieldId());
                    } else if (object.getTypename().equalsIgnoreCase("TickboxGroup")) {
                        for (var sub: object.getSubQuestionFields()){
                            if(!skipSingleHiddenCheckbox(pojo,sub.getGuidId())){
                                CheckboxObject.fieldId.add(sub.getGuidId());
                            }
                        }
                    }
                }
            }
        }
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

    public void clickSpecificRadioButton(String elementId,String hasValue) {
        String elem = stringReplaceTwoValues(actionLocator,elementId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        if(!element.isSelected()&&!hasValue.equalsIgnoreCase("0")){
            driverWait.until(ExpectedConditions.elementToBeClickable(element));
            click(element);
        }
    }
    public void clickSpecificRadioButtonAlreadyClicked(String elementId,String hasValue) {
        String elem = stringReplaceTwoValues(actionLocator,elementId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        if(element.isSelected()){
            driverWait.until(ExpectedConditions.elementToBeClickable(element));
            click(element);
        }
    }

    public void checkboxEnabledDisabledValidation(String objectId, String action,FormContentPojo pojo) {
        String name = getFieldName(pojo,objectId);
        String elem = stringReplace(fieldsetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        if(action.equalsIgnoreCase("DISABLE")){
            System.out.println("Element: "+objectId+" is expected to be DISABLED" );
            Assert.assertFalse(element.isEnabled(), "field is not disabled " + name + " " + objectId);
            System.out.println("Passed Routing");
        }else if(action.equalsIgnoreCase("ENABLE")){
            System.out.println("Element: "+objectId+" is expected to be ENABLED" );
            Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
            System.out.println("Passed Routing");
        }
    }

    public void checkboxEnabledDisabledValidationForHandwritingRecognitionObject(String objectId, String action, FormContentPojo pojo) {
        String name = getFieldName(pojo,objectId);
        String elem = stringReplace(HandwritingRecognitionObject,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        if(action.equalsIgnoreCase("DISABLE")){
            System.out.println("Element: "+objectId+" is expected to be DISABLED MIA" );
            Assert.assertFalse(element.isEnabled(), "field is not disabled for " + name + " " + objectId);
            System.out.println("Passed Routing");
        }else if(action.equalsIgnoreCase("ENABLE")){
            System.out.println("Element: "+objectId+" is expected to be ENABLED MIA" );
            Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
            System.out.println("Passed Routing");
        }
    }

    public void checkboxEnabledDisabledValidationForMia(String objectId, String action, FormContentPojo pojo) {
        String name = getFieldName(pojo,objectId);
        String elem = stringReplace(ManualImageAreaText,objectId);
        WebElement element;
        if(action.equalsIgnoreCase("DISABLE")){
            element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            System.out.println("Element: "+objectId+" is expected to be DISABLED MIA" );
            Assert.assertFalse(element.isEnabled(), "field is not disabled for " + name + " " + objectId);
            System.out.println("Passed Routing");
        }else if(action.equalsIgnoreCase("ENABLE")){
            element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            System.out.println("Element: "+objectId+" is expected to be ENABLED MIA" );
            Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
            System.out.println("Passed Routing");
        }
    }

    public Integer getCurrentPage(){
        //code to get the current page
        WebElement element = stringToWebElement(page);
        String currentPage = element.getText();
        String [] str = currentPage.split(" ");
        return Integer.parseInt(str[1]);
    }
    public void lookForTheField(FormContentPojo contentPojo, String guidId) {
        Integer currentPage = getCurrentPage();
        int pageCounter = 0;
        //loop through the pages
        outerLoop:
        for (var page: contentPojo.data.project.getPages()
        ) {
            //loop through the objects of the page
            ++pageCounter;
            for (var objects: page.getObjects()
            ) {
                if(objects.getSubQuestionFields()!=null){
                    for (var item: objects.getSubQuestionFields()
                    ) {
                        String test = item.getGuidId();
                        if(test.equalsIgnoreCase(guidId)){
                            break outerLoop;
                        }
                    }
                }else if(objects.getFieldId()!=null){
                    String test = objects.getFieldId();
                    if(test.equalsIgnoreCase(guidId)){
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
    public void clickNextPage(){
        WebElement element = stringToWebElement(nextPageButton);
        click(element);
    }
    public void clickPreviousPage(){
        WebElement element = stringToWebElement(previousPageButton);
        click(element);
    }
}
