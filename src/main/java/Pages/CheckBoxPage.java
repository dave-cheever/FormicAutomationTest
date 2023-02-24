package Pages;
import Objects.CheckboxObject;
import Pojo.FormContentPojo;
import Pojo.RulesGraphql;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.Reporter;
import java.text.ParseException;
import java.util.*;

public class CheckBoxPage extends BasePage{
    int projectId = 136;
    String emailRegEx = "^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$";
    static String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";
    static String mandatoryFieldMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div";
    String validationMessageLocator = "//div[@data-object-id='$TEXT']/div/div/div[2]";
    String fieldSetLocator = "//div[@data-object-id='$TEXT']/div/fieldset/input[1]";
    String ManualImageAreaText = "//div[@data-object-id='$TEXT']/div/textarea";
    String HandwritingRecognitionObject = "//div[@data-object-id='$TEXT']/div/input";
    static String actionLocator = "//div[@data-object-id='$TEXT']/div/fieldset/input[$NUM]";
    static String newValidationMessageUponSubmit = "//div[@data-object-id='$TEXT']/div/div/div[2]";
    static String checkboxLocator = "//div[@data-object-id='$TEXT']/div/fieldset/input";
    static String checkboxElementToBeClickedLocator = "//div[@data-object-id='$TEXT']/div/fieldset/input[$NUM]";
    String checkboxMatrixElementToBeClickedLocator = "//input[@data-field-id='$TEXT'][$NUM]";
    String completionErrorsFieldNameList = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[1]";
    String completionErrorsFieldName = "(//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[1])[$TEXT]";
    String completionErrorsFieldNameButton = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[text()='$TEXT']";

    public static HandwritingRecognitionObject hro = new HandwritingRecognitionObject(driver);
    public static ManualImageArea mia = new ManualImageArea(driver);
    public SideMenuNavigation sideMenuNavigation = new SideMenuNavigation(driver);
    public Stack<String> STACK = new Stack<>();
    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }

    //#region Steps
    public void validateEnableDisableFieldsCheckbox() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getRoutingItems(graphResponse);
        int indexNumber = 0;
        int num =1;
        String name;
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.strFieldId = fieldId;
            CheckboxObject.isEnableDisabledFields = true;
            name = getElementName(graphResponse,fieldId);
            Reporter.log(num+". "+name+" is being tested." );
            System.out.println(num+". "+name+" is being tested." );
            System.out.println("Field name "+name+" is not a checkbox matrix");
            Reporter.log("Field name "+name+" is not a checkbox matrix");
            checkListOfConditions(graphResponse,fieldId,indexNumber);
            Reporter.log("--------------------------------------------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------------------------------------------" );
            indexNumber++;
            num++;
        }
    }

    public void validateCheckboxMandatory() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldItems(graphResponse);
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.strFieldId = fieldId;
            CheckboxObject.isMandatoryFieldTest = true;
            if(getCheckboxRulesForMandatory(graphResponse, fieldId)&&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    System.out.println(CheckboxObject.checkboxName+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    AssertMandatoryFields(graphResponse,fieldId);
                }
            }
        }
    }

    public void validateCheckboxMinimumValidationUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIFromGraphqlResult(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.minimumConfig = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getCheckboxRulesForMinimumInputs(graphResponse,fieldId)&&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertMinimumConfig(graphResponse,fieldId);
                    }else {
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        assertMinimumConfig(graphResponse,fieldId);
                    }
                }
            }
        }
    }

    public void validateCheckboxBeyondMaximumInputsUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIFromGraphqlResult(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.beyondMaximumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            if(getCheckboxRulesForMaximumInputs(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    String name = CheckboxObject.checkboxName;
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertWithinMaximumInput(graphResponse, fieldId);
                    }else {
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        assertWithinMaximumInput(graphResponse,fieldId);
                    }
                }
            }
        }
    }

    public void validateCheckboxMaximumInputsWithinLimitUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIFromGraphqlResult(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.withinMaximumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getCheckboxRulesForMaximumInputs(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertWithinMaximumInput(graphResponse, fieldId);
                    }else {
                        System.out.println(CheckboxObject.checkboxName + " Is enabled");
                        assertWithinMaximumInput(graphResponse, fieldId);
                    }
                }
            }
        }
    }

    public void validateCheckboxWithinMinimumInputsUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldItems(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.withinMinimumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getCheckboxRulesForMinimumInputs(graphResponse, fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertWithinMinimumInput(graphResponse, fieldId);
                    }else {
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        assertWithinMinimumInput(graphResponse,fieldId);
                    }
                }
            }
        }
    }

    public void validateCheckboxLessThanMinimumInputsUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIFromGraphqlResult(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.lessThanMinimumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getCheckboxRulesForMinimumInputs(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertLessThanMinimumInput(graphResponse, fieldId);
                    }else {
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        assertLessThanMinimumInput(graphResponse,fieldId);
                    }
                }
            }
        }
    }

    public void hroFormatValidation() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldItemsHro(graphResponse);

        Reporter.log("Click submit button to show all required field validation message.");
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.lessThanMinimumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            Reporter.log("<b>Get rules for:</b> "+name);
            hro.getHroRules(graphResponse,fieldId);
            if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                //meaning this fieldId maybe disabled, and we need to enable it.
                System.out.println(name+ " Needs to check if it's disabled");
                checkListOfConditionsHro(graphResponse,fieldId);
            }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                lookForTheField(graphResponse,fieldId);
                if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
                    hro.emailAddressInputs(graphResponse,CheckboxObject.strFieldId);
                }else {
                    if (hro.hroDataType().equalsIgnoreCase("NUMERIC")) {
                        hro.alphaInputs(graphResponse, CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                        hro.assertHroValidationMessageNumeric(graphResponse, fieldId);
                    } else if (hro.hroDataType().equalsIgnoreCase("ALPHA_NUMERIC")) {
                        hro.specialCharacterInputs(graphResponse, CheckboxObject.strFieldId);
                        hro.assertHroValidationMessageAlphaNumeric(graphResponse, fieldId);
                    } else if (hro.hroDataType().equalsIgnoreCase("ALPHA")) {
                        hro.numericInputs(graphResponse, CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                        hro.assertHroValidationMessageAlphabet(graphResponse, fieldId);
                    } else if (hro.hroDataType().equalsIgnoreCase("DATE_TIME")) {

                    }
                }
            }

        }
    }


    public void hroMaximumInputsValidation() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldItemsHro(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.lessThanMinimumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            hro.getHroRules(graphResponse,fieldId);
            if(CheckboxObject.strFormatRegex!=null){
                if(!CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
                    if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                        //meaning this fieldId maybe disabled, and we need to enable it.
                        System.out.println(name+ " Needs to check if it's disabled");
                        checkListOfConditionsHro(graphResponse,fieldId);
                    }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                        lookForTheField(graphResponse,fieldId);
                        if(hro.hroDataType().equalsIgnoreCase("NUMERIC")){
                            hro.numericInputsBeyondTheMaximumAllowed(graphResponse,CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                        } else if (hro.hroDataType().equalsIgnoreCase("ALPHA_NUMERIC")) {
                            hro.alphaNumericInputsBeyondTheMaximumAllowed(graphResponse,CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                        }else if (hro.hroDataType().equalsIgnoreCase("ALPHA")) {
                            hro.alphaInputsBeyondTheMaximumAllowed(graphResponse,CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                        }else if (hro.hroDataType().equalsIgnoreCase("DATE_TIME")) {

                        }
                        hro.assertHro(graphResponse,fieldId);
                    }
                }
            }
        }
    }

    public void miaMandatoryValidation() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldItemsMia(graphResponse);
        Reporter.log("Click submit button to show all required field validation message.");
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.minimumConfig = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(getMiaRules(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                }else {
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    mia.assertMiaField(graphResponse,fieldId);
                }
            }
        }
    }

    public void validateSubmittedInputsCheckbox() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        String strListFieldName = "";
        do{
            strListFieldName = getListFieldNameByCompletionErrors();
            String strElementId = getElementIdByFieldName(graphResponse,strListFieldName);
            CheckboxObject.singleFieldId = getFieldIdByObjectId(graphResponse,strElementId);
            String strTypeName = getTypeNameByFieldId(graphResponse,CheckboxObject.singleFieldId);
            clickFieldNameInCompletionErrors(strListFieldName);

            if(strTypeName.equalsIgnoreCase("HandwritingRecognitionObject")){
                hro.getHroRules(graphResponse,CheckboxObject.singleFieldId);
                hroInputs(graphResponse,CheckboxObject.singleFieldId);
            }else if(strTypeName.equalsIgnoreCase("TickboxGroup")){
                //
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,CheckboxObject.singleFieldId)){
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                    ArrayList<String> numberOfOptions = CheckboxMatrix.checkboxMatrixOptionsCount(graphResponse,strElementId);
                    int numberOfItems = CheckboxMatrix.countNumberOfResponses(strElementId,numberOfOptions.size());
                    CheckboxMatrix.clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,numberOfOptions,numberOfItems);
                }else{
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                    int numberOfItems = countCheckboxItems(strElementId);
                    clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,strElementId,numberOfItems);
                }
            }else if(strTypeName.equalsIgnoreCase("ManualImageAreaText")){
                mia.getMiaRules(graphResponse,CheckboxObject.singleFieldId);
                miaInputs(graphResponse,CheckboxObject.singleFieldId);
            }
        }while (!getListFieldNameByCompletionErrors().isEmpty());
        sideMenuNavigation.clickSubmitButton();
        String receipt = getProjectReceipt();
        clickContinueButton();
        clickSavedFormsButton();
        Reporter.log("<b>Receipt code:<b/> "+receipt);
        enterReceiptNumber(receipt);
        clickGoButton();
        System.out.println(receipt);
        validateInputsAreCorrect(graphResponse);
    }

    public void validateSavedInputsCheckbox() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        String strListFieldName = "";
        do{
            strListFieldName = getListFieldNameByCompletionErrors();
            String strElementId = getElementIdByFieldName(graphResponse,strListFieldName);
            CheckboxObject.singleFieldId = getFieldIdByObjectId(graphResponse,strElementId);
            String strTypeName = getTypeNameByFieldId(graphResponse,CheckboxObject.singleFieldId);
            clickFieldNameInCompletionErrors(strListFieldName);

            if(strTypeName.equalsIgnoreCase("HandwritingRecognitionObject")){
                hro.getHroRules(graphResponse,CheckboxObject.singleFieldId);
                hroInputs(graphResponse,CheckboxObject.singleFieldId);
            }else if(strTypeName.equalsIgnoreCase("TickboxGroup")){
                //
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,CheckboxObject.singleFieldId)){
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                    ArrayList<String> numberOfOptions = CheckboxMatrix.checkboxMatrixOptionsCount(graphResponse,strElementId);
                    int numberOfItems = CheckboxMatrix.countNumberOfResponses(strElementId,numberOfOptions.size());
                    CheckboxMatrix.clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,numberOfOptions,numberOfItems);
                }else{
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                    int numberOfItems = countCheckboxItems(strElementId);
                    clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,strElementId,numberOfItems);
                }
            }else if(strTypeName.equalsIgnoreCase("ManualImageAreaText")){
                mia.getMiaRules(graphResponse,CheckboxObject.singleFieldId);
                miaInputs(graphResponse,CheckboxObject.singleFieldId);
            }
        }while (!getListFieldNameByCompletionErrors().isEmpty());
        sideMenuNavigation.clickSaveButton();
        String receipt = getProjectReceiptSave();
        clickContinueButton();
        clickSavedFormsButton();
        Reporter.log("<b>Receipt code:<b/> "+receipt);
        enterReceiptNumber(receipt);
        clickGoButton();
        System.out.println(receipt);
        validateInputsAreCorrect(graphResponse);
    }
    //#endregion

    public boolean skipSingleHiddenCheckbox(FormContentPojo pojo, String fieldId){
        for (Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (Pojo.Object objects: pages.getObjects()
            ) {
                if(objects.getSubQuestionFields()!=null){
                    for (Pojo.SubQuestionField sub: objects.getSubQuestionFields()
                    ) {
                        if(sub.getGuidId()!=null){
                            if(fieldId.equalsIgnoreCase(sub.getGuidId())){
                                if(objects.getTickboxResponses()!=null&&objects.getTickboxResponses().size()==1){
                                    for (Pojo.TickboxResponse tickBox: objects.getTickboxResponses()
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

    public void validateInputsAreCorrect(FormContentPojo pojo){
        String strElementId = "";
        String strCheckBoxId = null;
        String strHroId = null;
        String strMiaId = null;
        for (String value: CheckboxObject.checkboxInputs
        ) {
            strElementId = getObjectIdFromFieldId(pojo,value)==null? value : getObjectIdFromFieldId(pojo,value);
            if(isElementId(pojo,strElementId)){
                if(isElementIdCheckbox(pojo,strElementId)){
                    CheckboxObject.isCheckbox = true;
                    CheckboxObject.isMia = false;
                    CheckboxObject.isHro = false;
                    lookForTheField(pojo,value);
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,value)){
                        strCheckBoxId = value;
                    }else{
                        strCheckBoxId = strElementId;
                    }
                } else if (isElementIdHro(pojo,strElementId)) {
                    CheckboxObject.isHro = true;
                    CheckboxObject.isCheckbox = false;
                    CheckboxObject.isMia = false;
                    strHroId = strElementId;
                    lookForTheField(pojo,value);
                } else if (isElementIdMia(pojo,strElementId)) {
                    CheckboxObject.isMia = true;
                    CheckboxObject.isCheckbox = false;
                    CheckboxObject.isHro = false;
                    strMiaId = strElementId;
                    lookForTheField(pojo,value);
                }
            }else{
                String text;
                if(CheckboxObject.isCheckbox){
                    String elem = CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,strCheckBoxId)? stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strCheckBoxId,value) : stringReplaceTwoValues(actionLocator,strCheckBoxId,value);
                    WebElement element = stringToWebElement(elem);
                    scrollElementIntoView(driver,element);
                    Reporter.log("Checkbox "+getFieldNameByElementId(pojo,strCheckBoxId)+" tickbox number: "+ value+" should be selected.");
                    Reporter.log(elem);
                    Assert.assertTrue(element.isSelected(),"Checkbox "+getFieldNameByElementId(pojo,strCheckBoxId)+ " failed because tickbox number: "+ value+" is not selected.");
                } else if (CheckboxObject.isHro) {
                    text = hro.getHroTextFromElementId(strHroId);
                    Reporter.log("Expected text: "+ value+" Actual text: "+text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                } else if (CheckboxObject.isMia) {
                    text = mia.getMiaTextFromElementId(strMiaId);
                    Reporter.log("Expected text: "+ value+" Actual text: "+text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                }
            }
        }
    }

    public String getProjectReceipt(){
        try{
            String test = "//h2[contains(text(),'Complete')]/ancestor::main/p[contains(text(),'You can use the following receipt to recall this particular form at a later date.')]";
            WebElement elem = stringToWebElement(test);
            String text = elem.getText();
            String [] strReceipt;
            strReceipt = text.split(". ");
            return strReceipt[15];
        }catch (NoSuchElementException e){
            Reporter.log("Receipt not visible. "+e);
//            recordScreenshot();
            return "";
        }
    }


    public String getProjectReceiptSave(){
        try{
            String test = "//h2[contains(text(),'Saved')]/ancestor::main/p[contains(text(),'You can use the following receipt to recall this particular form at a later date.')]";
            WebElement elem = stringToWebElement(test);
            String text = elem.getText();
            String [] strReceipt;
            strReceipt = text.split(". ");
            return strReceipt[15];
        }catch (NoSuchElementException e){
            Reporter.log("Receipt not visible. "+e);
//            recordScreenshot();
            return "";
        }
    }

    public void clickContinueButton(){
        try{
            String continueBtn = "//button[text()='Continue']";
            WebElement element = stringToWebElement(continueBtn);
            click(element);
        }catch(Exception e){
//            recordScreenshot();
            Reporter.log("Continue button missing. "+e);
        }
    }

    public void clickSavedFormsButton(){
        try{
            String continueBtn = "//span[text()='Saved Forms']";
            WebElement element = stringToWebElement(continueBtn);
            click(element);
        }catch (NoSuchElementException e){
            Reporter.log("Save forms button not visible.");
        }

    }

    public void enterReceiptNumber(String strReceipt){
        String strReceiptNumber = "//input[@id='receipt-entry-input']";
        WebElement element = stringToWebElement(strReceiptNumber);
        enterText(element,strReceipt);
    }

    public void clickGoButton(){
        String strGoButton = "//button[text()='Go']";
        try{
            WebElement element = stringToWebElement(strGoButton);
            click(element);
        }catch (NoSuchElementException e){
            Reporter.log("Go button not visible. " + e);
//            recordScreenshot();
        }
    }

    public void assertLessThanMinimumInput(FormContentPojo pojo, String strFieldId) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        int numberOfInputs = clickLessThanMinimumInput(CheckboxObject.minimum,elementId,numberOfItems);
        if(CheckboxObject.minimum==1&&numberOfInputs==1||numberOfInputs==0){
            assertRequiredField(elementId,CheckboxObject.checkboxName);
        }else{
            assertRequiresMinimumOf(CheckboxObject.checkboxName,elementId,CheckboxObject.minimum);
        }
    }

    public void assertWithinMinimumInput(FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickWithinMinimumInput(CheckboxObject.minimum,elementId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public static void assertWithinMinimumMaximumInput(FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
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
            if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,strFieldId)){
                ArrayList<String> numberOfOptions = CheckboxMatrix.checkboxMatrixOptionsCount(pojo,strFieldId);
                String strElementId = getObjectIdFromFieldId(pojo,strFieldId);
                int numberOfItems = CheckboxMatrix.countNumberOfResponses(strElementId,numberOfOptions.size());
                CheckboxMatrix.clickWithinMinimumMaximumInput(pojo,CheckboxObject.minimum,CheckboxObject.maximum,numberOfOptions,numberOfItems);
            }else{
                int numberOfItems = countCheckboxItems(elementId);
                clickWithinMinimumMaximumInput(pojo,CheckboxObject.minimum,CheckboxObject.maximum,elementId,numberOfItems);
            }
        }else if(mia.isFieldIdMia(pojo,CheckboxObject.strFieldId)){
            mia.setTextToMia(pojo,CheckboxObject.strFieldId,"test");
        }

        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public void assertBeyondMaximumInput(FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickBeyondMaximumInput(pojo,CheckboxObject.maximum,strFieldId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public void assertWithinMaximumInput(FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickWithinMaximumInput(pojo,CheckboxObject.maximum,strFieldId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFields(pojo,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public void assertMinimumConfig(FormContentPojo pojo, String strFieldId) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(CheckboxObject.minimum==1){
            assertRequiredField(elementId,CheckboxObject.checkboxName);
        }else {
            assertMinimumConfigNoInputs(elementId,CheckboxObject.checkboxName,CheckboxObject.minimum);
        }
    }

    public boolean getCheckboxRulesForMandatory(FormContentPojo pojo, String strFieldId){
        for (Pojo.Field fields: pojo.data.project.getFields()
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

    public static boolean getCheckboxRulesForMinimumInputs(FormContentPojo pojo, String strFieldId){
        for (Pojo.Field fields: pojo.data.project.getFields()
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

    public boolean getMiaRules(FormContentPojo pojo, String strFieldId){
        for (Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                if(fields.getResponses()!=null){
                    Reporter.log("<b>Field name: </b>"+getFieldName(pojo,strFieldId));
                    CheckboxObject.mandatory = fields.getMandatory();
                    CheckboxObject.checkboxName = fields.getName();
                    Reporter.log("<b>Mandatory:</b> "+CheckboxObject.mandatory);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getCheckboxRulesForMinimumAndMaximumInputs(FormContentPojo pojo, String strFieldId){
        for (Pojo.Field fields: pojo.data.project.getFields()
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
            }
        }
        return false;
    }

    public static int countCheckboxItems(String strObjectElementId){
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

    public static void assertWithinAcceptedInputs(String strName, String strObjectElementId){
        String elemValidationMessage = stringReplace(newValidationMessageUponSubmit,strObjectElementId);
        String elemCompilationErrors = stringReplace(validationMessageUponSubmitSideBar,strObjectElementId);
        Assert.assertFalse(isElementVisible(driver,elemValidationMessage),"The expected for : "+strName + " There shouldn't be any validation message displayed below the object.");
        Assert.assertFalse(isElementVisible(driver,elemCompilationErrors),"The expected for: "+strName + "Completion Errors isn't displayed. The actual is Completion errors is displayed.");
        Reporter.log("There shouldn't be any validation message displayed below the field: "+ strName);
//        recordScreenshot();
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
        for (Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (Pojo.Condition conditions: routing.getConditions()
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
        for (Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (Pojo.Condition conditions: routing.getConditions()
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

    public static void AssertMandatoryFields(FormContentPojo pojo, String strFieldId) throws Exception {
        lookForTheField(pojo,strFieldId);
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String element = stringReplace(mandatoryFieldMessageLocator,elementId);
        WebElement validationMessageLocator = stringToWebElement(element);
        scrollElementIntoView(driver,validationMessageLocator);
        String fieldName = getFieldName(pojo,strFieldId);
        Assert.assertEquals(validationMessageLocator.getText(),"This field is mandatory.", fieldName+"The expected validation message for "+fieldName+" was: This field is mandatory. but the actual message was: "+validationMessageLocator.getText());
        Reporter.log("<b>Checkbox Name: <b/>"+fieldName+" <b>is mandatory. <b/>",true);
//        recordScreenshot();
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static String getFieldName(FormContentPojo pojo, String strFieldId){
        String fieldName = "";
        for (Pojo.Field fields:pojo.data.getProject().getFields()
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
        maxInput = elementCountInACheckbox == maxInput ? maxInput : maxInput+1;

        while (generated.size() < maxInput)
        {
            String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
            generated.add(next);
        }
        String[] gen = generated.toArray(new String[generated.size()]);

        for(int x = 0; x<generated.size();x++){
            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator, getObjectIdFromFieldId(pojo,strFieldId),gen[x]);
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
        while (generated.size() < maxInput)
        {
            String next = Integer.toString(rng.nextInt(elementCountInACheckbox) + 1);
            generated.add(next);
        }
        String[] gen = generated.toArray(new String[generated.size()]);

        for(int x = 0; x<generated.size();x++){
            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator, getObjectIdFromFieldId(pojo,strFieldId),gen[x]);
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

    public static String [] minMaximumGeneratedInputs(FormContentPojo pojo, int min, int max, int elementCount){
        Set<String> generated = new LinkedHashSet<String>();
        Random rng = new Random();
        if(max==1&& isCheckboxThatHasRoutingRules(pojo,CheckboxObject.strFieldId)){
            generated.add(getHasValueCheckboxThatHasRoutingRulesIfMax1(pojo,CheckboxObject.strFieldId,max));
        }else{
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
        }
        return generated.toArray(new String[generated.size()]);
    }

    /***
     * This method is created to adjust the set of inputs for a checkbox.
     * The reason is if the Checkbox has a routing rule, this will affect the inputs.
     * We need to include the item on the checkbox that is already selected.
     * For example, generated input for hasValue is 1 and 2, the maximum input for the checkbox is only 2.
     * Then we have 3 is already selected (Selected to enable some checkbox in routing rule). The selected outcome will be 1 and 3, we cant click number 2 because of the rule maximum inputs 2.
     * This will affect the assertion if we validate the inputs for the said checkbox if 1 and 2 isn't selected.
     * @param pojo is the FormContent
     * @param gen is the set of inputs that will be applied to the checkbox.
     * @return gen will be the updated inputs that will include the checkbox that is already selected from the beginning.
     */
    public static String [] adjustInputIfAlreadySelected(FormContentPojo pojo, String[] gen){
        ArrayList<String> hasValueEnable = new ArrayList<>();
        int ctr = 0;
        hasValueEnable = getHasValueToEnableFields(pojo,CheckboxObject.strFieldId);
        if(!CheckboxObject.hasValueList.isEmpty()){
            if(!isHasValueEqualToGen(gen)){
                for (String g: hasValueEnable
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

    public static ArrayList<String> getHasValueToEnableFields(FormContentPojo pojo, String strFieldId){
        ArrayList<String> result = new ArrayList<>();
        for (Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for (Pojo.Condition conditions: routing.getConditions()
            ) {
                if(conditions.getWhenField().equalsIgnoreCase(strFieldId)&&conditions.getAction().equalsIgnoreCase("enable")){
                    result.add(conditions.getHasValue());
                }
            }
        }
        return result;
    }

    public static void clickElementHasValue(String[] gen, String strObjectElementId){
        String elem;
        WebElement element;
        for (String s : gen) {
            elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator, strObjectElementId, s);
            scrollElementIntoView(driver, stringToWebElement(stringReplace(checkboxLocator, strObjectElementId)));
            element = stringToWebElement(elem);
            scrollElementIntoView(driver, element);
            Reporter.log("Click checkbox number: " + s);
            if (!element.isSelected()) {
                click(element);
            }
        }
    }

    public static int clickWithinMinimumMaximumInput(FormContentPojo pojo, int minInput, int maxInput, String strObjectElementId, int elementCountInACheckbox){
        String[] gen = minMaximumGeneratedInputs(pojo,minInput,maxInput,elementCountInACheckbox);
        gen = adjustInputIfAlreadySelected(pojo,gen);
        recordInputsFromCheckbox(getFieldIdByObjectId(pojo,strObjectElementId),gen);
        System.out.println(CheckboxObject.checkboxInputs);
        clickElementHasValue(gen,strObjectElementId);
        return gen.length;
    }

    public static String getHasValueCheckboxThatHasRoutingRulesIfMax1(FormContentPojo pojo, String fieldId, int max){
        String hasValue="";
        if(max == 1){
            for (Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getConditions()!=null){
                    for (Pojo.Condition condition : routing.getConditions()
                    ) {
                        if(condition.getWhenField().equalsIgnoreCase(fieldId)){
                            if(condition.getAction().equalsIgnoreCase("ENABLE")){
                                hasValue = condition.getHasValue();
                            }
                        }
                    }
                }
            }
        }
        return hasValue;
    }

    public static boolean isCheckboxThatHasRoutingRules(FormContentPojo pojo, String fieldId){
        boolean hasValue=false;
            for (Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getConditions()!=null){
                    for (Pojo.Condition condition : routing.getConditions()
                    ) {
                        if(condition.getWhenField().equalsIgnoreCase(fieldId)){
                            if(condition.getAction().equalsIgnoreCase("ENABLE")){
                                hasValue = true;
                            }
                        }
                    }
                }
            }
        return hasValue;
    }

    public static boolean isHasValueEqualToGen(String[] gen){
        boolean result = false;
        for (String g: gen
        ) {
            for (String value: CheckboxObject.hasValueList
            ) {
                if (value.equalsIgnoreCase(g)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static void recordInputsFromCheckbox(String strElementId, String[] strHasValue){
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
            return 0;
        }
        return generated.size();
    }

    public void checkListOfConditions(FormContentPojo pojo, String strFieldId,Integer indexNumber) throws Exception {
        String whenFieldElementId;
        String fieldIdElementId;
        for (Pojo.Condition conditions: pojo.data.project.getRouting().get(indexNumber).getConditions()
        ) {
            fieldIdElementId = getObjectIdFromFieldId(pojo,strFieldId);
            if(fieldIdElementId!=null){
                String whenField = conditions.getWhenField();
                whenFieldElementId = getObjectIdFromFieldId(pojo,whenField);
                if(whenFieldElementId!=null){
                    String typeName = getTypeNameByFieldId(pojo,strFieldId);
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
        outerLoop:
        for (Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for (Pojo.Condition conditions: routing.getConditions()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                    fieldIdElementId = getObjectIdFromFieldId(pojo,strFieldId);
                    if(fieldIdElementId!=null){
                        String whenField = conditions.getWhenField();
                        whenFieldElementId = getObjectIdFromFieldId(pojo,whenField);
                        Reporter.log("We check if the field: "+getFieldName(pojo, strFieldId) +" is disabled by another field: "+ getFieldName(pojo, whenField));
                        if(whenFieldElementId!=null&&conditions.getAction().equalsIgnoreCase("enable")){
                            System.out.println(CheckboxObject.checkboxName+ " is enabled");
                            String typeName = getTypeNameByFieldId(pojo,strFieldId);
                            STACK.push(conditions.getAction());
                            STACK.push(typeName);
                            STACK.push(strFieldId);
                            STACK.push(conditions.getHasValue());
                            STACK.push(whenField);
                            System.out.println("We now check if whenField: "+getFieldName(pojo, whenField)+ " is enabled");
                            testFindTheWhenFieldThatDisablesTheFieldId(pojo,whenField);
                            validateListOfFieldsStackedSubmit(pojo);
                            break outerLoop;
                        }
                    }
                }
            }
        }
    }

    public void checkListOfConditionsHro(FormContentPojo pojo, String strFieldId) throws Exception {
        String whenFieldElementId;
        String fieldIdElementId;
        outerLoop:
        for (Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for (Pojo.Condition conditions: routing.getConditions()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                    fieldIdElementId = getObjectIdFromFieldId(pojo,strFieldId);
                    if(fieldIdElementId!=null){
                        String whenField = conditions.getWhenField();
                        whenFieldElementId = getObjectIdFromFieldId(pojo,whenField);
                        if(whenFieldElementId!=null&&conditions.getAction().equalsIgnoreCase("enable")){
                            System.out.println(CheckboxObject.checkboxName+ " is enabled");
                            String typeName = getTypeNameByFieldId(pojo,strFieldId);
                            STACK.push(conditions.getAction());
                            STACK.push(typeName);
                            STACK.push(strFieldId);
                            STACK.push(conditions.getHasValue());
                            STACK.push(whenField);
                            System.out.println("We now check if whenField: "+whenField+ " is enabled");
                            testFindTheWhenFieldThatDisablesTheFieldId(pojo,whenField);
                            validateListOfFieldsStackedHro(pojo);
                            break outerLoop;
                        }
                    }
                }
            }
        }
    }

    public String getTypeNameByFieldId(FormContentPojo pojo, String strFieldId){
        String result=null;
        for (Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (Pojo.Object objects: pages.getObjects()
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
                            for (Pojo.SubQuestionField sub: objects.getSubQuestionFields()
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
        String whenFieldElementId = getObjectIdFromFieldId(pojo,strWhenField);
        String typeName = getTypeNameByFieldId(pojo,strWhenField);
        boolean isWhenFieldDisabled;
        lookForTheField(pojo,strWhenField);
        System.out.println("We now proceed to check if the whenfield is disabled.");
        if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,strWhenField)){
            isWhenFieldDisabled = CheckboxMatrix.checkIdIfDisabled(strWhenField);
        }else{
            isWhenFieldDisabled = checkIdIfDisabled(whenFieldElementId);
        }
        String whenFieldID;
        if(isWhenFieldDisabled){
            outerLoop:
            for (Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strWhenField)){
                    if(routing.getConditions()!= null){
                        for (Pojo.Condition conditions: routing.getConditions()
                        ) {
                            if(conditions.getAction().equalsIgnoreCase("enable")){
                                if(getObjectIdFromFieldId(pojo, conditions.getWhenField())!=null){
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
        String whenFieldElementId = getObjectIdFromFieldId(pojo,strWhenField);
        String typeName = getTypeNameByFieldId(pojo,strWhenField);
        boolean isWhenFieldDisabled;
        lookForTheField(pojo,strWhenField);
        if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,strWhenField)){
            isWhenFieldDisabled = CheckboxMatrix.checkIdIfDisabled(strWhenField);
        }else {
            isWhenFieldDisabled = checkIdIfDisabled(whenFieldElementId);
        }
        String whenFieldID;
        if(isWhenFieldDisabled){
            System.out.println("whenfield is disabled.");
            outerLoop:
            for (Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strWhenField)){
                    if(routing.getConditions()!= null){
                        for (Pojo.Condition conditions: routing.getConditions()
                        ) {
                            String whenField = conditions.getWhenField();
                            Reporter.log("We check if the field: "+getFieldName(pojo, strWhenField) +" is disabled by another field: "+ getFieldName(pojo, whenField));
                            if(conditions.getAction().equalsIgnoreCase("enable")){
                                //If Null this is a checkbox matrix, and we will skip this one
                                if(getObjectIdFromFieldId(pojo, conditions.getWhenField())!=null&&conditions.getAction().equalsIgnoreCase("enable")){
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
                            }else{
                                Reporter.log("The fieldID: "+getFieldName(pojo,strWhenField) +"is not disable.");
                            }
                        }
                    }
                }
            }
        }
        else {
            Reporter.log("The fieldID: "+strWhenField +"is not disable.");
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
                whenFieldElementId = getObjectIdFromFieldId(pojo,whenFieldId);
                fieldIdElementId = getObjectIdFromFieldId(pojo,fieldId);
                System.out.println("In validation WhenfieldID: "+whenFieldId +" HasValue: "+ hasValue +" FieldId: "+fieldId+" typeName: "+ typeName+" Action: "+ action);
                lookForTheField(pojo,whenFieldId);
                hiddenCheckBox = identifyHiddenCheckBox(pojo,whenFieldId);
                // This will adjust the hasValue when some checkbox is hidden
                if(hiddenCheckBox!=0){
                    hasValue =  adjustHasValueForHiddenFields(hasValue,hiddenCheckBox);
                }
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,whenFieldId)){
                    CheckboxMatrix.clickSpecificRadioButtonForEnableDisableFields(pojo,whenFieldId,hasValue);
                }else{
                    clickSpecificRadioButtonForEnableDisableFields(pojo,whenFieldElementId,hasValue);
                }
                lookForTheField(pojo,fieldId);
                assertEnableDisableFields(pojo,typeName, fieldIdElementId,action);
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
        if(!CheckboxObject.isEnableDisabledFields){
            if(hro.isFieldIdHro(pojo,CheckboxObject.strFieldId)&&CheckboxObject.strFormatRegex!=null){
                hroInputs(pojo,CheckboxObject.strFieldId);
            }else if(isFieldIdCheckBox(pojo,CheckboxObject.strFieldId)){
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,CheckboxObject.strFieldId)){
                    CheckboxMatrix.assertCheckboxMinMaxMandatory(pojo,CheckboxObject.strFieldId);
                }else{
                    assertCheckboxMinMaxMandatory(pojo,CheckboxObject.strFieldId);
                }
            }else if(mia.isFieldIdMia(pojo,CheckboxObject.strFieldId)){
                mia.setTextToMia(pojo,CheckboxObject.strFieldId,"test");
                mia.assertMiaField(pojo,CheckboxObject.strFieldId);
            }
        }
    }

    public void validateListOfFieldsStackedSubmit(FormContentPojo pojo) throws Exception {
        String whenFieldId;
        String hasValue;
        String fieldId;
        String typeName;
        String action;
        String whenFieldElementId;
        String strElementId;
        int hiddenCheckBox;
        for(int x = 0; x < 1;){
            if(!STACK.isEmpty()){
                whenFieldId = STACK.pop();
                hasValue = STACK.pop();
                fieldId = STACK.pop();
                typeName = STACK.pop();
                action = STACK.pop();
                whenFieldElementId = getObjectIdFromFieldId(pojo,whenFieldId);
                strElementId = getObjectIdFromFieldId(pojo,fieldId);
                System.out.println("In validation WhenfieldID: "+whenFieldId +" HasValue: "+ hasValue +" FieldId: "+fieldId+" typeName: "+ typeName+" Action: "+ action);
                lookForTheField(pojo,whenFieldId);
                hiddenCheckBox = identifyHiddenCheckBox(pojo,whenFieldId);
                // This will adjust the hasValue when some checkbox is hidden
                if(hiddenCheckBox!=0){
                    hasValue =  adjustHasValueForHiddenFields(hasValue,hiddenCheckBox);
                }
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,whenFieldId)){
                    CheckboxMatrix.clickSpecificRadioButton(pojo,whenFieldId,hasValue);
                }else {
                    clickSpecificRadioButton(pojo,whenFieldElementId,hasValue);
                }
                lookForTheField(pojo,fieldId);
                assertEnableDisableFields(pojo,typeName, strElementId,action);
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
        if(!CheckboxObject.isEnableDisabledFields){
            if(hro.isFieldIdHro(pojo,CheckboxObject.strFieldId)&&CheckboxObject.strFormatRegex!=null){
                hroInputs(pojo,CheckboxObject.strFieldId);
            }else if(isFieldIdCheckBox(pojo,CheckboxObject.strFieldId)){
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,CheckboxObject.strFieldId)){
                    CheckboxMatrix.assertCheckboxMinMaxMandatory(pojo, CheckboxObject.strFieldId);
                }else{
                    assertCheckboxMinMaxMandatory(pojo,CheckboxObject.strFieldId);
                }
            }else if(mia.isFieldIdMia(pojo,CheckboxObject.strFieldId)){
                mia.setTextToMia(pojo,CheckboxObject.strFieldId,"test");
                mia.assertMiaField(pojo,CheckboxObject.strFieldId);
            }
        }
    }

    public void assertEnableDisableFields(FormContentPojo pojo, String typeName, String strElementId, String action){
        if(typeName.equalsIgnoreCase("TickboxGroup")){
            if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,getFieldIdByObjectId(pojo,strElementId))){
                CheckboxMatrix.checkboxEnabledDisabledValidation(getFieldIdByObjectId(pojo,strElementId),action,pojo);
            }else{
                checkboxEnabledDisabledValidation(strElementId,action,pojo);
            }
        } else if (typeName.equalsIgnoreCase("HandwritingRecognitionObject")) {
            checkboxEnabledDisabledValidationForHandwritingRecognitionObject(strElementId,action,pojo);
        }else if(typeName.equalsIgnoreCase("ManualImageAreaText")){
            checkboxEnabledDisabledValidationForMia(strElementId,action,pojo);
        }
    }

    public void assertCheckboxMinMaxMandatory(FormContentPojo pojo,String strFieldId) throws Exception {
        if(CheckboxObject.isMandatoryFieldTest){
            AssertMandatoryFields(pojo,strFieldId);
        } else if(CheckboxObject.lessThanMinimumInputs){
            assertLessThanMinimumInput(pojo,strFieldId);
        }else if(CheckboxObject.withinMinimumInputs){
            assertWithinMinimumInput(pojo,strFieldId);
        }else if (CheckboxObject.withinMaximumInputs){
            assertWithinMaximumInput(pojo,strFieldId);
        }else if (CheckboxObject.beyondMaximumInputs){
            assertBeyondMaximumInput(pojo,strFieldId);
        }else if(CheckboxObject.minimumConfig){
            assertMinimumConfig(pojo,strFieldId);
        }else if(CheckboxObject.withinMinimumMaximumInputs){
            assertWithinMinimumMaximumInput(pojo,strFieldId);
        }
    }

    public void hroInputs(FormContentPojo pojo,String strFieldId) throws ParseException {
        if(hro.hroDataType().equalsIgnoreCase("NUMERIC")){
            hro.numericInputs(pojo,strFieldId, hro.identifyMaximumInputsByFieldId());
        } else if (hro.hroDataType().equalsIgnoreCase("ALPHA_NUMERIC")) {
            hro.alphaNumericInputs(pojo,strFieldId, hro.identifyMaximumInputsByFieldId());
        }else if (hro.hroDataType().equalsIgnoreCase("ALPHA")) {
            hro.alphaInputs(pojo,strFieldId, hro.identifyMaximumInputsByFieldId());
        }else if(hro.hroDataType().equalsIgnoreCase("DATE_TIME")){
            hro.dateTimeInputs(pojo,strFieldId);
        }
    }

    public void miaInputs(FormContentPojo pojo,String strFieldId) throws ParseException {
        if(mia.miaDataType().equalsIgnoreCase("NUMERIC")){
            mia.numericInputs(pojo,strFieldId);
        } else if (mia.miaDataType().equalsIgnoreCase("ALPHA_NUMERIC")) {
            mia.alphaNumericInputs(pojo,strFieldId);
        }else if (mia.miaDataType().equalsIgnoreCase("ALPHA")) {
            mia.alphaInputs(pojo,strFieldId);
        }else if(mia.miaDataType().equalsIgnoreCase("DATE_TIME")){
            mia.dateTimeInputs(pojo,strFieldId);
        }
    }

    public void validateListOfFieldsStackedHro(FormContentPojo pojo) throws Exception {
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
                whenFieldElementId = getObjectIdFromFieldId(pojo,whenFieldId);
                fieldIdElementId = getObjectIdFromFieldId(pojo,fieldId);
                System.out.println("In validation WhenfieldID: "+whenFieldId +" HasValue: "+ hasValue +" FieldId: "+fieldId+" typeName: "+ typeName+" Action: "+ action);
                lookForTheField(pojo,whenFieldId);
                hiddenCheckBox = identifyHiddenCheckBox(pojo,whenFieldId);
                // This will adjust the hasValue when some checkbox is hidden
                if(hiddenCheckBox!=0){
                    hasValue =  adjustHasValueForHiddenFields(hasValue,hiddenCheckBox);
                }
                clickSpecificRadioButton(pojo,whenFieldElementId,hasValue);
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
        if(!CheckboxObject.isEnableDisabledFields){
            if(hro.isFieldIdHro(pojo,CheckboxObject.strFieldId)&&CheckboxObject.strFormatRegex!=null){
                lookForTheField(pojo,CheckboxObject.strFieldId);
                if(hro.hroDataType().equalsIgnoreCase("NUMERIC")){
                    hro.alphaInputs(pojo,CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                    hro.assertHroValidationMessageNumeric(pojo,CheckboxObject.strFieldId);
                } else if (hro.hroDataType().equalsIgnoreCase("ALPHA_NUMERIC")) {
                    hro.specialCharacterInputs(pojo,CheckboxObject.strFieldId);
                    hro.assertHroValidationMessageAlphaNumeric(pojo,CheckboxObject.strFieldId);
                }else if (hro.hroDataType().equalsIgnoreCase("ALPHA")) {
                    hro.numericInputs(pojo,CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                    hro.assertHroValidationMessageAlphabet(pojo,CheckboxObject.strFieldId);
                }
            }else if(isFieldIdCheckBox(pojo,CheckboxObject.strFieldId)){
                if(CheckboxObject.isMandatoryFieldTest){
                    AssertMandatoryFields(pojo,CheckboxObject.strFieldId);
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
        for (Pojo.Field fields: pojo.data.project.getFields()
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
        for (Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (Pojo.Object objects: pages.getObjects()
            ) {
                if(objects.getSubQuestionFields()!=null){
                    for (Pojo.SubQuestionField sub: objects.getSubQuestionFields()
                    ) {
                        if(sub.getGuidId()!=null){
                            if(strWhenFieldId.equalsIgnoreCase(sub.getGuidId())){
                                if(objects.getTickboxResponses()!=null){
                                    for (Pojo.TickboxResponse tickBox: objects.getTickboxResponses()
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

    public boolean checkIdIfDisabled(String objectId) {
        String elem = stringReplace(fieldSetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        return !element.isEnabled();
    }

    public void getRoutingItems(FormContentPojo pojo){
        for (Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            CheckboxObject.fieldId.add(routing.getFieldId());
        }
    }

    public void getFieldItems(FormContentPojo pojo){
        for (Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            CheckboxObject.fieldId.add(routing.getFieldId());
        }

        for (Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(!skipSingleHiddenCheckbox(pojo,fields.getGuidId())){
                CheckboxObject.fieldId.add(fields.getGuidId());
            }
        }
        CheckboxObject.fieldId = removeDuplicates(CheckboxObject.fieldId);
    }

    public void getFieldIFromGraphqlResult(FormContentPojo pojo){
        for (Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (Pojo.Object object: pages.getObjects()
                 ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")){
                        CheckboxObject.fieldId.add(object.getFieldId());
                    } else if (object.getTypename().equalsIgnoreCase("TickboxGroup")) {
                        for (Pojo.SubQuestionField sub: object.getSubQuestionFields()){
                            if(!skipSingleHiddenCheckbox(pojo,sub.getGuidId())){
                                CheckboxObject.fieldId.add(sub.getGuidId());
                            }
                        }
                    }else if (object.getTypename().equalsIgnoreCase("ManualImageAreaText")) {
                        CheckboxObject.fieldId.add(object.getFieldId());
                    }
                }
            }
        }
    }

    public void getFieldItemsHro(FormContentPojo pojo){
        for (Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")){
                        CheckboxObject.fieldId.add(object.getFieldId());
                    }
                }
            }
        }
    }

    public void getFieldItemsMia(FormContentPojo pojo){
        for (Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("ManualImageAreaText")){
                        CheckboxObject.fieldId.add(object.getFieldId());
                    }
                }
            }
        }
    }

    public void clickSpecificRadioButton(FormContentPojo pojo, String elementId,String hasValue) {
        String elem = stringReplaceTwoValues(actionLocator,elementId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String name = getFieldNameByElementId(pojo,elementId);
        if(!element.isSelected()&&!hasValue.equalsIgnoreCase("0")){
            Reporter.log("Check if "+name+" is clickable.");
            if(!elementClickable(element)){
                unselectCheckboxThatDisableField(pojo,element,elementId);
            }
            click(element);
        }
    }

    public void unselectCheckboxThatDisableField(FormContentPojo pojo, WebElement element, String elementId){
        do{
            String strFieldId = getFieldIdByObjectId(pojo,elementId);
            for (Pojo.Routing routing : pojo.data.project.getRouting()
                 ) {
                if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                    for (Pojo.Condition condition : routing.getConditions()
                         ) {
                        String strAction = condition.getAction();
                        String strHasValue = condition.getHasValue();
                        String strWhenFieldId = condition.getWhenField();
                        String strWhenFieldElementId = getObjectIdFromFieldId(pojo,strWhenFieldId);
                        if(strAction.equalsIgnoreCase("DISABLE")){
                            lookForTheField(pojo,strWhenFieldId);
                            String elem = stringReplaceTwoValues(actionLocator,strWhenFieldElementId,strHasValue);
                            WebElement whenFieldElement = stringToWebElement(elem);
                            scrollElementIntoView(driver,element);
                            if(whenFieldElement.isSelected()){
                                click(whenFieldElement);
                            }
                        }
                    }
                }
            }
        }while (!elementClickable(element));
    }

    public void clickSpecificRadioButtonForEnableDisableFields(FormContentPojo pojo, String elementId,String hasValue) {
        String elem = stringReplaceTwoValues(actionLocator,elementId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String name = getFieldNameByElementId(pojo,elementId);
        if(!element.isSelected()&&!hasValue.equalsIgnoreCase("0")){
            Reporter.log("Check if "+name+" is clickable.");
            String strFieldId = getFieldIdByObjectId(pojo,elementId);
            outerLoop:
            for(boolean x = false; x == false;){
                for (Pojo.Routing routing : pojo.data.project.getRouting()
                ) {
                    if(!element.isEnabled()){
                        if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                            for (Pojo.Condition condition : routing.getConditions()
                            ) {
                                if(condition.getAction().equalsIgnoreCase("DISABLE")){
                                    lookForTheField(pojo,condition.getWhenField());
                                    String whenFieldObjectId = getObjectIdFromFieldId(pojo,condition.getWhenField());
                                    String strElementWhenField = stringReplaceTwoValues(actionLocator,whenFieldObjectId,condition.getHasValue());
                                    WebElement elementWhenField = stringToWebElement(strElementWhenField);
                                    if(elementWhenField.isSelected()){
                                        click(elementWhenField);
                                    }
                                }
                            }
                        }
                    }else {
                        x = true;
                        break outerLoop;
                    }
                }
            }
            click(element);
        }
    }

    public void clickSpecificRadioButtonAlreadyClicked(String elementId,String hasValue) {
        String elem = stringReplaceTwoValues(actionLocator,elementId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        if(element.isSelected()){
            elementClickable(element);
            click(element);
        }
    }

    public void checkboxEnabledDisabledValidation(String objectId, String action,FormContentPojo pojo) {
        String elem = stringReplace(fieldSetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String name = getFieldNameByElementId(pojo,objectId);
        if(action.equalsIgnoreCase("DISABLE")){
            System.out.println("Element: "+objectId+" is expected to be DISABLED");
            Reporter.log("Field Name: "+name+" is expected to be DISABLED");
            Assert.assertFalse(element.isEnabled(), "field is not disabled " + name + " " + objectId);
            Reporter.log("Passed Routing");
            System.out.println("Passed Routing");
        }else if(action.equalsIgnoreCase("ENABLE")){
            System.out.println("Element: "+objectId+" is expected to be ENABLED" );
            Reporter.log("Field Name: "+name+" is expected to be ENABLED");
            Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
            Reporter.log("Passed Routing");
            System.out.println("Passed Routing");
        }
    }

    public void checkboxEnabledDisabledValidationForHandwritingRecognitionObject(String objectId, String action, FormContentPojo pojo) {
        String name = getFieldNameByElementId(pojo,objectId);
        String elem = stringReplace(HandwritingRecognitionObject,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        if(action.equalsIgnoreCase("DISABLE")){
            System.out.println("Element: "+objectId+" is expected to be DISABLED MIA" );
            Reporter.log("Field Name: "+name+" is expected to be DISABLED MIA");
            Assert.assertFalse(element.isEnabled(), "field is not disabled for " + name + " " + objectId);
            Reporter.log("Passed Routing");
            System.out.println("Passed Routing");
        }else if(action.equalsIgnoreCase("ENABLE")){
            System.out.println("Element: "+objectId+" is expected to be ENABLED MIA" );
            Reporter.log("Field Name: "+name+" is expected to be ENABLED MIA");
            Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
            Reporter.log("Passed Routing");
            System.out.println("Passed Routing");
        }
    }

    public void checkboxEnabledDisabledValidationForMia(String objectId, String action, FormContentPojo pojo) {
        String name = getFieldNameByElementId(pojo,objectId);
        String elem = stringReplace(ManualImageAreaText,objectId);
        WebElement element;
        if(action.equalsIgnoreCase("DISABLE")){
            element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            System.out.println("Element: "+objectId+" is expected to be DISABLED MIA" );
            Reporter.log("Field Name: "+name+" is expected to be DISABLED MIA");
            Assert.assertFalse(element.isEnabled(), "field is not disabled for " + name + " " + objectId);
            Reporter.log("Passed Routing");
            System.out.println("Passed Routing");
        }else if(action.equalsIgnoreCase("ENABLE")){
            element = stringToWebElement(elem);
            scrollElementIntoView(driver,element);
            System.out.println("Element: "+objectId+" is expected to be ENABLED MIA" );
            Reporter.log("Field Name: "+name+" is expected to be ENABLED MIA");
            Assert.assertTrue(element.isEnabled(),"field is not Enabled "+name+" "+ objectId);
            Reporter.log("Passed Routing");
            System.out.println("Passed Routing");
        }
    }

    public String getListFieldNameByCompletionErrors(){
        String fieldNames = "";
        try{
            int numberOfElements = driver.findElements(By.xpath(completionErrorsFieldNameList)).size();
            for(int x = 1; x <= numberOfElements; x++){
                String elem = stringReplace(completionErrorsFieldName,Integer.toString(x));
                WebElement element = stringToWebElement(elem);
                scrollElementIntoView(driver,element);
                fieldNames = element.getText();
                break;
            }
            return fieldNames;
        }catch (Exception e){
            Reporter.log("No more items in Completion errors.");
            return null;
        }
    }

    public String getElementIdByFieldName(FormContentPojo pojo, String strFieldName){
        String result = "";
        outerLoop:
        for (Pojo.Field field : pojo.data.project.getFields()
             ) {
            if (field.getName().equalsIgnoreCase(strFieldName)){
                String guidId = field.getGuidId();
                result = getObjectIdFromFieldId(pojo,guidId);
                break outerLoop;
            }
        }
        return result;
    }

    public void clickFieldNameInCompletionErrors(String strFieldName){
        String elem = stringReplace(completionErrorsFieldNameButton,strFieldName);
        WebElement element = stringToWebElement(elem);
        element.click();
    }
}
