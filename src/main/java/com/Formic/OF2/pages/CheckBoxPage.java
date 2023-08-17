package com.Formic.OF2.pages;

import com.Formic.OF2.utils.FormatMask;
import com.Formic.OF2.utils.FormatRegex;
import com.Formic.OF2.utils.InputLimitExtractor;
import com.Formic.OF2.utils.CheckboxObject;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.Formic.OF2.utils.Pojo.RulesGraphql;
import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;

import java.text.ParseException;
import java.util.*;

public class CheckBoxPage extends BasePage {
    int projectId = 137;
    String emailRegEx = "^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$";
    static String validationMessageUponSubmitSideBar = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[contains(text(),'$TEXT')]//following::div[1]";
    static String mandatoryFieldMessageLocator = "//div[@data-object-id='$TEXT']/div/div";
    static String mandatoryFieldMessagePickListLocator = "(//div[@data-object-id='$TEXT']/div/div/div)[2]";
    static String validationMessageLocator = "//div[@data-object-id='$TEXT']/div/div[2]";
    String fieldSetLocator = "//div[@data-object-id='$TEXT']/fieldset/input[1]";
    String ManualImageAreaText = "//div[@data-object-id='$TEXT']/textarea";
    String HandwritingRecognitionObject = "//div[@data-object-id='$TEXT']/input";
    static String actionLocator = "//div[@data-object-id='$TEXT']/fieldset/input[$NUM]";
    static String newValidationMessageUponSubmit = "//div[@data-object-id='$TEXT']/div/div[2]";
    static String checkboxLocator = "//div[@data-object-id='$TEXT']/fieldset/input";
    static String checkboxElementToBeClickedLocator = "//div[@data-object-id='$TEXT']/fieldset/input[$NUM]";
    String checkboxMatrixElementToBeClickedLocator = "//input[@data-field-id='$TEXT'][$NUM]";
    String completionErrorsFieldNameList = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[1]";
    String completionErrorsFieldName = "(//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[1])[$TEXT]";
    String completionErrorsFieldNameButton = "//h1[contains(text(),'Completion Errors')]//following-sibling::ul/li/button/div/div[text()='$TEXT']";

    public static com.Formic.OF2.pages.HandwritingRecognitionObject hro = new HandwritingRecognitionObject(driver);
    public static ManualImageArea mia = new ManualImageArea(driver);
    public SideMenuNavigation sideMenuNavigation = new SideMenuNavigation(driver);
    public static CompletionErrors comp = new CompletionErrors(driver);
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
        getAllFieldId(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.strFieldId = fieldId;
            CheckboxObject.isMandatoryFieldTest = true;
            if(getFieldItemRules(graphResponse, fieldId)&&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    System.out.println(CheckboxObject.checkboxName+ " Needs to check if it's disabled");
                    checkListOfConditionsWithNoInputs(graphResponse,fieldId);
                }else {
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    AssertMandatoryFieldsWithoutInputs(graphResponse,fieldId);
                }
            }
        }
    }

    public void uncheckWhenFieldIdThatDisablesFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String hasValue = "0";
        String whenFieldId = "";
        String elementId;
        outerLoop:
        for (var routing : pojo.data.project.getRouting()
             ) {
            if(routing.getFieldId()!=null&&routing.getFieldId().equalsIgnoreCase(strFieldId)){
                for (var conditions : routing.getConditions()
                     ) {
                    hasValue = conditions.getHasValue();
                    whenFieldId = conditions.getWhenField();
                    break outerLoop;
                }
            }
        }
        lookForTheField(pojo,whenFieldId);
        elementId = getObjectIdFromFieldId(pojo,whenFieldId);
        String elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator,elementId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        clickSpecificRadioButtonAlreadyClicked(elementId,hasValue);
    }

    public void validateCheckboxMinimumValidationUponSubmit() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getCheckboxFieldItems(graphResponse);
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
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertMinimumConfig(graphResponse,fieldId);
                    }else {
                        assertMinimumConfig(graphResponse,fieldId);
                    }
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
        getCheckboxFieldItems(graphResponse);
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
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertWithinMaximumInput(graphResponse, fieldId);
                    }else {
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        assertWithinMaximumInput(graphResponse,fieldId);
                    }
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
        getCheckboxFieldItems(graphResponse);
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
        getCheckboxFieldItems(graphResponse);
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
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertWithinMinimumInput(graphResponse, fieldId);
                    }else {
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        assertWithinMinimumInput(graphResponse,fieldId);
                    }
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
        getCheckboxFieldItems(graphResponse);
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
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertLessThanMinimumInput(graphResponse, fieldId);
                    }else {
                        assertLessThanMinimumInput(graphResponse,fieldId);
                    }
                }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,fieldId)){
                        CheckboxMatrix.assertLessThanMinimumInput(graphResponse, fieldId);
                    }else {
                        assertLessThanMinimumInput(graphResponse,fieldId);
                    }
                }
            }
        }
    }

    public void hroFormatValidation() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIdHro(graphResponse);
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
                checkListOfConditions(graphResponse,fieldId);
                AssertHroFormatValidation(graphResponse,fieldId);
            }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                AssertHroFormatValidation(graphResponse,fieldId);
            }
        }
    }

    public void AssertMiaFormatValidation(com.Formic.OF2.utils.Pojo.FormContentPojo graphResponse,String fieldId){
        lookForTheField(graphResponse,fieldId);
        if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
            String email = emailAddressInputs();
            mia.setTextToMia(graphResponse,fieldId,email);
        }else {

            String inputs;
            boolean flag = false;
            if (CheckboxObject.strFormatRegex != null) {
                inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
                if (!inputs.equalsIgnoreCase("")) {
                    mia.setTextToMia(graphResponse, fieldId, "!@#");
                    mia.assertFormatMaskValidation(graphResponse,fieldId);
                    flag = true;
                }
            }

            if (CheckboxObject.strFormatMask!=null&&!flag){
                inputs = FormatMask.formatDateTime(CheckboxObject.strFormatMask);
                if(inputs!=null){
                    mia.setTextToMia(graphResponse,fieldId,"test");
                    mia.assertDateTimeFormat(graphResponse,fieldId);
                    flag = true;
                }
            }
            mia.processMiaInputsForValidation(graphResponse,fieldId,flag);
        }
    }

    public void AssertHroFormatValidation(FormContentPojo graphResponse, String fieldId){
        lookForTheField(graphResponse,fieldId);
        if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
            String email = emailAddressInputs();
            hro.setTextToHro(graphResponse,fieldId,email);
        }else {
            String inputs;
            boolean flag = false;
            if (CheckboxObject.strFormatRegex != null) {
                inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
                if (!inputs.equalsIgnoreCase("")) {
                    hro.setTextToHro(graphResponse, fieldId, "!@#");
                    hro.assertFormatMaskValidation(graphResponse,fieldId);
                    flag = true;
                }
            }

            if (CheckboxObject.strFormatMask!=null&&!flag){
                inputs = FormatMask.formatDateTime(CheckboxObject.strFormatMask);
                if(inputs!=null){
                    hro.setTextToHro(graphResponse, fieldId, "test");
                    hro.assertDateTimeFormat(graphResponse,fieldId);
                    flag = true;
                }
            }

            if (CheckboxObject.strDataTypeNew != null&&!flag) {
                if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("NUMERIC")) {
                    hro.processNumericDataType(graphResponse, fieldId);
                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA_NUMERIC")) {
                    hro.processAlphaNumericDataType(graphResponse, fieldId);
                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA")) {
                    hro.processAlphaDataType(graphResponse, fieldId);
                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("DATE_TIME")) {
                    hro.processDateTimeDataType(graphResponse,fieldId);
                }
            }
        }
    }

    public void miaFormatValidation() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIdMia(graphResponse);
        Reporter.log("Click submit button to show all required field validation message.");
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.lessThanMinimumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            Reporter.log("<b>Get rules for:</b> "+name);
            mia.getMiaRules(graphResponse,fieldId);
            if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                //meaning this fieldId maybe disabled, and we need to enable it.
                System.out.println(name+ " Needs to check if it's disabled");
                checkListOfConditions(graphResponse,fieldId);
                AssertMiaFormatValidation(graphResponse,fieldId);
            }else if(!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)){
                AssertMiaFormatValidation(graphResponse,fieldId);
            }
        }
    }


    public void hroMaximumInputsValidation() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse = rules.getRules(projectId);
        getFieldIdHro(graphResponse);
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId : CheckboxObject.fieldId
        ) {
            CheckboxObject.lessThanMinimumInputs = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            hro.getHroRules(graphResponse, fieldId);
            if (CheckboxObject.strFormatRegex != null) {
                if (!CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)) {
                    if (isFieldIdInRoutingRules(graphResponse, fieldId)) {
                        //meaning this fieldId maybe disabled, and we need to enable it.
                        System.out.println(name + " Needs to check if it's disabled");
                        checkListOfConditions(graphResponse, fieldId);
                        lookForTheField(graphResponse, fieldId);

                        if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("NUMERIC")) {
                            hro.numericInputsBeyondTheMaximumAllowed(graphResponse, CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                        } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA_NUMERIC")) {
                            FormatRegex.RegexType strDataType = FormatRegex.getRegexType(CheckboxObject.strFormatRegex);
                            if (strDataType.equals(FormatRegex.RegexType.ONLY_LETTERS)) {
                                hro.alphaInputsBeyondTheMaximumAllowed(graphResponse, CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                            } else {
                                hro.numericInputsBeyondTheMaximumAllowed(graphResponse, CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                            }
                        } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("DATE_TIME")) {

                        }
                        if (CheckboxObject.strFormatRegex != null && CheckboxObject.strFormatMask != null) {
                            hro.assertHro(graphResponse, fieldId);
                        } else {
                            AssertHroFormatValidation(graphResponse, fieldId);
                        }
                    } else if (!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse, fieldId)) {
                        lookForTheField(graphResponse, fieldId);
                        if (CheckboxObject.strFormatRegex != null && CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)) {
                            String email = emailAddressInputs();
                            hro.setTextToHro(graphResponse, fieldId, email);
                        } else {

                            String inputs;
                            boolean flag = false;
                            if (CheckboxObject.strFormatRegex != null) {
                                inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
                                if (!inputs.equalsIgnoreCase("")) {
                                    hro.setTextToHro(graphResponse, fieldId, inputs);
                                    flag = true;
                                }
                            }

                            if (CheckboxObject.strFormatMask != null && !flag) {
                                inputs = FormatMask.formatDateTime(CheckboxObject.strFormatMask);
                                if (inputs != null) {
                                    hro.setTextToHro(graphResponse, fieldId, inputs);
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("NUMERIC")) {
                                    hro.numericInputsBeyondTheMaximumAllowed(graphResponse, CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA_NUMERIC")) {
                                    FormatRegex.RegexType strDataType = FormatRegex.getRegexType(CheckboxObject.strFormatRegex);
                                    if (strDataType.equals(FormatRegex.RegexType.ONLY_LETTERS)) {
                                        hro.alphaInputsBeyondTheMaximumAllowed(graphResponse, CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                                    } else {
                                        hro.numericInputsBeyondTheMaximumAllowed(graphResponse, CheckboxObject.strFieldId, hro.identifyMaximumInputsByFieldId());
                                    }
                                }
                                if (CheckboxObject.strFormatRegex != null && CheckboxObject.strFormatMask != null) {
                                    hro.assertHro(graphResponse, fieldId);
                                } else {
                                    AssertHroFormatValidation(graphResponse, fieldId);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void miaMandatoryValidation() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIdMia(graphResponse);
        Reporter.log("Click submit button to show all required field validation message.");
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.minimumConfig = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(mia.getMiaRules(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                    lookForTheField(graphResponse,fieldId);
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    mia.assertMiaMandatoryField(graphResponse,fieldId);
                }else {
                    lookForTheField(graphResponse,fieldId);
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    mia.assertMiaMandatoryField(graphResponse,fieldId);
                }
            }
        }
    }

    public void miaPicklistLessThanMinimumInputs() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIdPicklist(graphResponse);
        Reporter.log("Click submit button to show all required field validation message.");
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.minimumConfig = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(mia.getMiaRules(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(mia.isMinimumMaximumNotEmpty()){
                    if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                        System.out.println(name+ " Needs to check if it's disabled");
                        checkListOfConditions(graphResponse,fieldId);
                        lookForTheField(graphResponse,fieldId);
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        mia.addLessThanMinimumOptions(graphResponse,CheckboxObject.minimum,fieldId);
                        mia.validateLessThanTheMinimumRequired(graphResponse,CheckboxObject.minimum,fieldId);
                    }else {
                        lookForTheField(graphResponse,fieldId);
                        System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                        mia.addLessThanMinimumOptions(graphResponse,CheckboxObject.minimum,fieldId);
                        mia.validateLessThanTheMinimumRequired(graphResponse,CheckboxObject.minimum,fieldId);
                    }
                }
            }
            CheckboxObject.checkboxObjectDefaultValue();
        }
    }

    public void miaPicklistMoreThanMaximumInputs() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIdPicklist(graphResponse);
        Reporter.log("Click submit button to show all required field validation message.");
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.minimumConfig = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(mia.getMiaRules(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId)&&CheckboxObject.maximum!=0&&CheckboxObject.isMultiResponse)
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                    lookForTheField(graphResponse,fieldId);
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    mia.addMoreThanMaximumOptions(graphResponse,CheckboxObject.maximum,fieldId);
                    mia.validateMoreThanTheMaximumRequired(graphResponse,CheckboxObject.maximum,fieldId);
                }else {
                    lookForTheField(graphResponse,fieldId);
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    mia.addMoreThanMaximumOptions(graphResponse,CheckboxObject.maximum,fieldId);
                    mia.validateMoreThanTheMaximumRequired(graphResponse,CheckboxObject.maximum,fieldId);
                }
            }
            CheckboxObject.checkboxObjectDefaultValue();
        }
    }

    public void miaPicklistWithinMinimumMaximumInputs() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getFieldIdPicklist(graphResponse);
        Reporter.log("Click submit button to show all required field validation message.");
        sideMenuNavigation.clickSubmitButton();
        for (String fieldId: CheckboxObject.fieldId
        ) {
            CheckboxObject.minimumConfig = true;
            CheckboxObject.strFieldId = fieldId;
            String name = CheckboxObject.checkboxName;
            if(mia.getMiaRules(graphResponse,fieldId)
                    &&!isFieldIdInRoutingRulesWhenFieldDisable(graphResponse,fieldId))
            {
                if(isFieldIdInRoutingRules(graphResponse,fieldId)){
                    //meaning this fieldId maybe disabled, and we need to enable it.
                    System.out.println(name+ " Needs to check if it's disabled");
                    checkListOfConditions(graphResponse,fieldId);
                    lookForTheField(graphResponse,fieldId);
                    mia.addWithinMinimumMaximumOptions(graphResponse,CheckboxObject.minimum,fieldId);
                    mia.validateWithinMinimumMaximumRequired(graphResponse,fieldId);
                }else {
                    System.out.println(CheckboxObject.checkboxName+ " Is enabled");
                    lookForTheField(graphResponse,fieldId);
                    mia.addWithinMinimumMaximumOptions(graphResponse,CheckboxObject.minimum,fieldId);
                    mia.validateWithinMinimumMaximumRequired(graphResponse,fieldId);
                }
            }
        }
    }

    public void validateSubmittedInputsCheckbox() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        sideMenuNavigation.clickSubmitButton();
        String strListFieldName;
        do{
            strListFieldName = getListFieldNameByCompletionErrors();
            String strElementId = getElementIdByFieldName(graphResponse,strListFieldName);
            CheckboxObject.singleFieldId = getFieldIdByObjectId(graphResponse,strElementId);
            String strTypeName = getTypeNameByFieldId(graphResponse,CheckboxObject.singleFieldId);
            clickFieldNameInCompletionErrors(strListFieldName);

            if(strTypeName.equalsIgnoreCase("HandwritingRecognitionObject")){
                hro.getHroRules(graphResponse,CheckboxObject.singleFieldId);
                lookForTheField(graphResponse,CheckboxObject.singleFieldId);
                if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
                    String email = emailAddressInputs();
                    hro.setTextToHro(graphResponse,CheckboxObject.singleFieldId,email);
                }else {
                    hroInputs(graphResponse,CheckboxObject.singleFieldId);
                }
            }else if(strTypeName.equalsIgnoreCase("TickboxGroup")){
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,CheckboxObject.singleFieldId)){
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                    ArrayList<String> numberOfOptions = CheckboxMatrix.checkboxMatrixOptionsCount(graphResponse,CheckboxObject.singleFieldId);
                    int numberOfItems = CheckboxMatrix.countNumberOfResponses(strElementId,numberOfOptions.size());
                    CheckboxMatrix.clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,numberOfOptions,numberOfItems);
                }else{
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                    int numberOfItems = countCheckboxItems(strElementId);
                    clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,strElementId,numberOfItems);
                }
            }else if(strTypeName.equalsIgnoreCase("ManualImageAreaText")){
                mia.getMiaRules(graphResponse,CheckboxObject.singleFieldId);
                lookForTheField(graphResponse,CheckboxObject.singleFieldId);
                if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
                    String email = emailAddressInputs();
                    mia.setTextToMia(graphResponse,CheckboxObject.singleFieldId,email);
                }else {
                    miaInputs(graphResponse,CheckboxObject.singleFieldId);
                }
            }else if(strTypeName.equalsIgnoreCase("PickList")){
                mia.getMiaRules(graphResponse,CheckboxObject.singleFieldId);
                picklistInputs(graphResponse,CheckboxObject.singleFieldId);
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

    public void test() throws Exception {
        RulesGraphql rules = new RulesGraphql();
        FormContentPojo graphResponse =  rules.getRules(projectId);
        getAllFieldId(graphResponse);
        String strListFieldName;
        for (String fieldId: CheckboxObject.fieldId
        ) {
            strListFieldName = getFieldName(graphResponse,fieldId);
            String strElementId = getElementIdByFieldName(graphResponse,strListFieldName);
            CheckboxObject.singleFieldId = getFieldIdByObjectId(graphResponse,strElementId);
            String strTypeName = getTypeNameByFieldId(graphResponse,CheckboxObject.singleFieldId);
            lookForTheField(graphResponse,fieldId);
            if(strTypeName!=null){
                if(strTypeName.equalsIgnoreCase("HandwritingRecognitionObject")){
                    hro.getHroRules(graphResponse,CheckboxObject.singleFieldId);
                    lookForTheField(graphResponse,CheckboxObject.singleFieldId);
                    if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
                        String email = emailAddressInputs();
                        hro.setTextToHro(graphResponse,CheckboxObject.singleFieldId,email);
                    }else {
                        hroInputs(graphResponse,CheckboxObject.singleFieldId);
                    }
                }else if(strTypeName.equalsIgnoreCase("TickboxGroup")){
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,CheckboxObject.singleFieldId)){
                        getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                        ArrayList<String> numberOfOptions = CheckboxMatrix.checkboxMatrixOptionsCount(graphResponse,CheckboxObject.singleFieldId);
                        int numberOfItems = CheckboxMatrix.countNumberOfResponses(strElementId,numberOfOptions.size());
                        CheckboxMatrix.clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,numberOfOptions,numberOfItems);
                    }else{
                        getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                        int numberOfItems = countCheckboxItems(strElementId);
                        clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,strElementId,numberOfItems);
                    }
                }else if(strTypeName.equalsIgnoreCase("ManualImageAreaText")){
                    mia.getMiaRules(graphResponse,CheckboxObject.singleFieldId);
                    lookForTheField(graphResponse,CheckboxObject.singleFieldId);
                    if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
                        String email = emailAddressInputs();
                        mia.setTextToMia(graphResponse,CheckboxObject.singleFieldId,email);
                    }else {
                        miaInputs(graphResponse,CheckboxObject.singleFieldId);
                    }
                }else if(strTypeName.equalsIgnoreCase("PickList")){
                    mia.getMiaRules(graphResponse,CheckboxObject.singleFieldId);
                    picklistInputs(graphResponse,CheckboxObject.singleFieldId);
                }
            }
        }
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
        String strListFieldName;
        do{
            strListFieldName = getListFieldNameByCompletionErrors();
            String strElementId = getElementIdByFieldName(graphResponse,strListFieldName);
            CheckboxObject.singleFieldId = getFieldIdByObjectId(graphResponse,strElementId);
            String strTypeName = getTypeNameByFieldId(graphResponse,CheckboxObject.singleFieldId);
            clickFieldNameInCompletionErrors(strListFieldName);

            if(strTypeName.equalsIgnoreCase("HandwritingRecognitionObject")){
                hro.getHroRules(graphResponse,CheckboxObject.singleFieldId);
                lookForTheField(graphResponse,CheckboxObject.singleFieldId);
                if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
                    String email = emailAddressInputs();
                    hro.setTextToHro(graphResponse,CheckboxObject.singleFieldId,email);
                }else {
                    hroInputs(graphResponse,CheckboxObject.singleFieldId);
                }
            }else if(strTypeName.equalsIgnoreCase("TickboxGroup")){
                //
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(graphResponse,CheckboxObject.singleFieldId)){
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                    ArrayList<String> numberOfOptions = CheckboxMatrix.checkboxMatrixOptionsCount(graphResponse, CheckboxObject.singleFieldId);
                    int numberOfItems = CheckboxMatrix.countNumberOfResponses(strElementId,numberOfOptions.size());
                    CheckboxMatrix.clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,numberOfOptions,numberOfItems);
                }else{
                    getCheckboxRulesForMinimumAndMaximumInputs(graphResponse,CheckboxObject.singleFieldId);
                    int numberOfItems = countCheckboxItems(strElementId);
                    clickWithinMinimumMaximumInput(graphResponse,CheckboxObject.minimum,CheckboxObject.maximum,strElementId,numberOfItems);
                }
            }else if(strTypeName.equalsIgnoreCase("ManualImageAreaText")){
                mia.getMiaRules(graphResponse,CheckboxObject.singleFieldId);
                lookForTheField(graphResponse,CheckboxObject.singleFieldId);
                if(CheckboxObject.strFormatRegex!=null&&CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)){
                    String email = emailAddressInputs();
                    mia.setTextToMia(graphResponse,CheckboxObject.singleFieldId,email);
                }else {
                    miaInputs(graphResponse,CheckboxObject.singleFieldId);
                }
            }else if(strTypeName.equalsIgnoreCase("PickList")){
            mia.getMiaRules(graphResponse,CheckboxObject.singleFieldId);
                picklistInputs(graphResponse,CheckboxObject.singleFieldId);
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

    public boolean skipSingleHiddenCheckbox(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object objects: pages.getObjects()
            ) {
                if(objects.getSubQuestionFields()!=null){
                    for (com.Formic.OF2.utils.Pojo.SubQuestionField sub: objects.getSubQuestionFields()
                    ) {
                        if(sub.getGuidId()!=null){
                            if(fieldId.equalsIgnoreCase(sub.getGuidId())){
                                if(objects.getTickboxResponses()!=null&&objects.getTickboxResponses().size()==1){
                                    for (com.Formic.OF2.utils.Pojo.TickboxResponse tickBox: objects.getTickboxResponses()
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

    public void validateInputsAreCorrect(com.Formic.OF2.utils.Pojo.FormContentPojo pojo) throws InterruptedException {
        String strElementId;
        boolean isCheckboxMatrix = false;
        String strCheckBoxId = null;
        String strHroId = null;
        String strMiaId = null;
        String strPickListId = null;
        Thread.sleep(500);
        for (String value: CheckboxObject.checkboxInputs
        ) {
            strElementId = getObjectIdFromFieldId(pojo,value)==null? value : getObjectIdFromFieldId(pojo,value);
            if(isElementId(pojo,strElementId)){
                if(isElementIdCheckbox(pojo,strElementId)){
                    CheckboxObject.isCheckbox = true;
                    CheckboxObject.isMia = false;
                    CheckboxObject.isHro = false;
                    String fieldId = getFieldIdByObjectId(pojo,strElementId);
                    lookForTheField(pojo,fieldId);
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,fieldId)){
                        isCheckboxMatrix = true;
                        strCheckBoxId = fieldId;
                    }else{
                        isCheckboxMatrix = false;
                        strCheckBoxId = strElementId;
                    }
                } else if (isElementIdHro(pojo,strElementId)) {
                    CheckboxObject.isHro = true;
                    CheckboxObject.isCheckbox = false;
                    CheckboxObject.isMia = false;
                    CheckboxObject.isPicklist = false;
                    strHroId = strElementId;
                    String fieldId = getFieldIdByObjectId(pojo,strHroId);
                    lookForTheField(pojo,fieldId);
                } else if (isElementIdMia(pojo,strElementId)) {
                    CheckboxObject.isMia = true;
                    CheckboxObject.isCheckbox = false;
                    CheckboxObject.isHro = false;
                    CheckboxObject.isPicklist = false;
                    strMiaId = strElementId;
                    String fieldId = getFieldIdByObjectId(pojo,strMiaId);
                    lookForTheField(pojo,fieldId);
                }else if (isElementIdPicklist(pojo,strElementId)) {
                    CheckboxObject.isPicklist = true;
                    CheckboxObject.isMia = false;
                    CheckboxObject.isCheckbox = false;
                    CheckboxObject.isHro = false;
                    strPickListId = strElementId;
                    String fieldId = getFieldIdByObjectId(pojo,strPickListId);
                    lookForTheField(pojo,fieldId);
                    CheckboxObject.picklistOptionsCtr = 1;
                }
            }else{
                String text;
                if(CheckboxObject.isCheckbox){
                    String elem = isCheckboxMatrix? stringReplaceTwoValues(checkboxMatrixElementToBeClickedLocator,strCheckBoxId,value) : stringReplaceTwoValues(actionLocator,strCheckBoxId,value);
                    WebElement element = stringToWebElement(elem);
                    scrollElementIntoView(driver,element);
                    Reporter.log("Checkbox "+getFieldNameByElementId(pojo,strCheckBoxId)+" tickbox number: "+ value+" should be selected.");
                    Reporter.log(elem);
                    Assert.assertTrue(element.isSelected(),"Checkbox "+getFieldNameByElementId(pojo,strCheckBoxId)+ " failed because tickbox number: "+ value+" is not selected.");
                } else if (CheckboxObject.isHro) {
                    text = hro.getHroTextFromElementId(strHroId);
                    Reporter.log("Field name: "+getFieldNameByElementId(pojo,strHroId)+" Expected text: "+ value+" Actual text: "+text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                } else if (CheckboxObject.isMia) {
                    text = mia.getMiaTextFromElementId(strMiaId);
                    Reporter.log("Field name: "+getFieldNameByElementId(pojo,strMiaId)+" Expected text: "+ value+" Actual text: "+text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                } else if (CheckboxObject.isPicklist) {
                    text = mia.getPicklistSelectedOptionsName(strPickListId,CheckboxObject.picklistOptionsCtr);
                    Reporter.log("Field name: "+getFieldNameByElementId(pojo,strPickListId)+ " Expected text: "+ value+" Actual text: "+text);
                    Assert.assertTrue(text.equalsIgnoreCase(value),"Expected text: "+ value+" Actual text: "+text);
                    CheckboxObject.picklistOptionsCtr++;
                }
            }
        }
    }

    public boolean getFieldItemRules(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                if(fields.getResponses()!=null&& fields.getMandatory()){
                    CheckboxObject.mandatory = fields.getMandatory();
                    CheckboxObject.checkboxName = fields.getName();
                    if(fields.getResponses()!=null){
                        CheckboxObject.minimum = fields.getResponses().getMinimum();
                        CheckboxObject.maximum = fields.getResponses().getMaximum();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean getCheckboxRulesForMinimumInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
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

    public boolean getCheckboxRulesForMinimumAndMaximumInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
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
        int numberOfElements = numberOfElementsVisible(element);
        for(int x = 1; x <= numberOfElements; x++){
            element = stringReplaceTwoValues(actionLocator,strObjectElementId,Integer.toString(x));
            WebElement elem = stringToWebElement(element);
            if(elem.isSelected()){
                CheckboxObject.hasValueList.add(Integer.toString(x));
            }
        }
        return numberOfElements;
    }

    public boolean isFieldIdInRoutingRules(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        boolean result = false;
        loopBreak:
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
                ) {
                    if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                        if(conditions.getAction().equalsIgnoreCase("enable")||conditions.getAction().equalsIgnoreCase("disable")){
                            result = true;
                            break loopBreak;
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean isFieldIdInRoutingRulesWhenFieldDisable(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        boolean result = false;
        loopBreak:
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            if(routing.getConditions()!=null){
                for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
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

    public static boolean isCheckboxFieldNotChecked(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        int numberOfCheckbox = countCheckboxItems(elementId);
        String elem;
        WebElement element;
        boolean result = true;
        for(int x = 1; x<=numberOfCheckbox;x++){
             elem = stringReplaceTwoValues(checkboxElementToBeClickedLocator,elementId,Integer.toString(x));
             element = stringToWebElement(elem);
             if(element.isSelected()){
                 result = false;
                 break;
             }
        }
        return result;
    }

    public static String getFieldName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
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

    public int clickBeyondMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,int maxInput,String strFieldId,int elementCountInACheckbox){
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

    public int clickWithinMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,int maxInput,String strFieldId,int elementCountInACheckbox){
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

    public static String [] minMaximumGeneratedInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, int min, int max, int elementCount){
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
    public static String [] adjustInputIfAlreadySelected(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String[] gen){
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

    public static ArrayList<String> getHasValueToEnableFields(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        ArrayList<String> result = new ArrayList<>();
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
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

    public static int clickWithinMinimumMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, int minInput, int maxInput, String strObjectElementId, int elementCountInACheckbox){
        String[] gen = minMaximumGeneratedInputs(pojo,minInput,maxInput,elementCountInACheckbox);
        gen = adjustInputIfAlreadySelected(pojo,gen);
        recordInputsFromCheckbox(strObjectElementId,gen);
        System.out.println(CheckboxObject.checkboxInputs);
        clickElementHasValue(gen,strObjectElementId);
        return gen.length;
    }

    public static String getHasValueCheckboxThatHasRoutingRulesIfMax1(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId, int max){
        String hasValue="";
        if(max == 1){
            for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getConditions()!=null){
                    for (com.Formic.OF2.utils.Pojo.Condition condition : routing.getConditions()
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

    public static boolean isCheckboxThatHasRoutingRules(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        boolean hasValue=false;
            for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getConditions()!=null){
                    for (com.Formic.OF2.utils.Pojo.Condition condition : routing.getConditions()
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

    public void checkListOfConditions(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,Integer indexNumber) throws Exception {
        String whenFieldElementId;
        String fieldIdElementId;
        for (com.Formic.OF2.utils.Pojo.Condition conditions: pojo.data.project.getRouting().get(indexNumber).getConditions()
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

    public void checkListOfConditions(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
        String whenFieldElementId;
        String fieldIdElementId;
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
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
//                            anotherValidation(pojo);
                            break outerLoop;
                        }else{
                            uncheckWhenFieldIdThatDisablesFieldId(pojo,strFieldId);
                            testFindTheWhenFieldThatDisablesTheFieldId(pojo,whenField);
                        }
                    }
                }
            }
        }
    }

    public void checkListOfConditionsWithNoInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
        String whenFieldElementId;
        String fieldIdElementId;
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                    fieldIdElementId = getObjectIdFromFieldId(pojo,strFieldId);
                    if(fieldIdElementId!=null){
                        String whenField = conditions.getWhenField();
                        whenFieldElementId = getObjectIdFromFieldId(pojo,whenField);
                        Reporter.log("We check if the field: "+getFieldName(pojo, strFieldId) +" is disabled by another field: "+ getFieldName(pojo, whenField));
                        String typeName = getTypeNameByFieldId(pojo,strFieldId);
                        STACK.push(conditions.getAction());
                        STACK.push(typeName);
                        STACK.push(strFieldId);
                        STACK.push(conditions.getHasValue());
                        STACK.push(whenField);
                        if(whenFieldElementId!=null&&conditions.getAction().equalsIgnoreCase("enable")){
                            System.out.println(CheckboxObject.checkboxName+ " is enabled");
                            System.out.println("We now check if whenField: "+getFieldName(pojo, whenField)+ " is enabled");
                            testFindTheWhenFieldThatDisablesTheFieldId(pojo,whenField);
                            validateListOfFieldsStackedSubmitNoInputs(pojo);
                            AssertMandatoryFieldsWithoutInputs(pojo,strFieldId);
                            break outerLoop;
                        }else{
                            uncheckWhenFieldIdThatDisablesFieldId(pojo,strFieldId);
                            testFindTheWhenFieldThatDisablesTheFieldId(pojo,whenField);
                            validateListOfFieldsStackedSubmitNoInputs(pojo);
                            AssertMandatoryFieldsWithoutInputs(pojo,strFieldId);
                        }
                    }
                }
            }
        }
    }

    public String getTypeNameByFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String result=null;
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object objects: pages.getObjects()
            ) {
                if(objects.getTypename()!=null){
                    if(objects.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")||objects.getTypename().equalsIgnoreCase("ManualImageAreaText")||objects.getTypename().equalsIgnoreCase("PickList")){

                        if(objects.getFieldId().equalsIgnoreCase(strFieldId)){
                            result =  objects.getTypename();
                        }

                    }
                    else if(objects.getTypename().equalsIgnoreCase("TickboxGroup")){
                        if(objects.getSubQuestionFields()!=null)
                        {
                            for (com.Formic.OF2.utils.Pojo.SubQuestionField sub: objects.getSubQuestionFields()
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
    public void findTheWhenFieldThatDisablesTheFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strWhenField) {
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
            for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strWhenField)){
                    if(routing.getConditions()!= null){
                        for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
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

    public void testFindTheWhenFieldThatDisablesTheFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strWhenField) {
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
            for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strWhenField)){
                    if(routing.getConditions()!= null){
                        for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
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

    public void validateListOfFieldsStacked(com.Formic.OF2.utils.Pojo.FormContentPojo pojo) throws Exception {
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
    }

    public void validateListOfFieldsStackedSubmit(com.Formic.OF2.utils.Pojo.FormContentPojo pojo) throws Exception {
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
                    clickSpecificRadioButton(pojo,whenFieldId,hasValue);
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
    }

    public void validateListOfFieldsStackedSubmitNoInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo) throws Exception {
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
                    clickSpecificRadioButton(pojo,whenFieldId,hasValue);
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
    }

    public void hroInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId) throws ParseException {
        lookForTheField(pojo, strFieldId);
        if (CheckboxObject.strFormatRegex != null && CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)) {
            String email = emailAddressInputs();
            hro.setTextToHro(pojo, strFieldId, email);
        } else {

            String inputs = "";
            boolean flag = false;
            if (CheckboxObject.strFormatRegex != null) {
                inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
                if (!inputs.equalsIgnoreCase("")) {
                    hro.setTextToHro(pojo, strFieldId, inputs);
                    flag = true;
                }
            }

            if (CheckboxObject.strFormatMask != null && flag != true) {
                inputs = FormatMask.formatDateTime(CheckboxObject.strFormatMask);
                if (inputs != null) {
                    hro.setTextToHro(pojo, strFieldId, inputs);
                    flag = true;
                }
            }
            if(flag!=true&&CheckboxObject.strFormatRegex!=null){
                FormatRegex.RegexType regex = FormatRegex.getRegexType(CheckboxObject.strFormatRegex);
                if(regex.equals(FormatRegex.RegexType.ONLY_NUMBERS)){
                    inputs = hro.numericInputs(pojo,strFieldId, InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
                    hro.setTextToHro(pojo, strFieldId, inputs);
                } else if (regex.equals(FormatRegex.RegexType.ALPHANUMERIC)) {
                    inputs = hro.alphaNumericInputs(pojo,strFieldId,InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
                    hro.setTextToHro(pojo, strFieldId, inputs);
                }else if (regex.equals(FormatRegex.RegexType.ONLY_LETTERS)) {
                    inputs =  hro.alphaInputs(pojo,strFieldId,InputLimitExtractor.extractInputLimit(CheckboxObject.strFormatRegex));
                    hro.setTextToHro(pojo, strFieldId, inputs);
                }
            }else if(flag!=true){
                if(CheckboxObject.strDataTypeNew.equalsIgnoreCase("NUMERIC")){
                    inputs = hro.numericInputs(pojo,strFieldId,3);
                    hro.setTextToHro(pojo, strFieldId, inputs);
                } else if (CheckboxObject.strDataTypeNew.equalsIgnoreCase("ALPHA_NUMERIC")) {
                    inputs = hro.alphaNumericInputs(pojo,strFieldId,3);
                    hro.setTextToHro(pojo, strFieldId, inputs);
                }
            }
        }
    }

    public void miaInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId) throws ParseException {
        lookForTheField(pojo, strFieldId);
        if (CheckboxObject.strFormatRegex != null && CheckboxObject.strFormatRegex.equalsIgnoreCase(emailRegEx)) {
            String email = emailAddressInputs();
            mia.setTextToMia(pojo, strFieldId, email);
        } else {

            String inputs = "";
            boolean flag = false;
            if (CheckboxObject.strFormatRegex != null) {
                inputs = FormatRegex.generateFormattedString(CheckboxObject.strFormatRegex);
                if (!inputs.equalsIgnoreCase("")) {
                    mia.setTextToMia(pojo, strFieldId, inputs);
                    flag = true;
                }
            }

            if (CheckboxObject.strFormatMask != null && flag != true) {
                inputs = FormatMask.formatDateTime(CheckboxObject.strFormatMask);
                if (inputs != null) {
                    mia.setTextToMia(pojo, strFieldId, inputs);
                    flag = true;
                }
            }
            if(flag!=true){
                if(mia.miaDataType().equalsIgnoreCase("NUMERIC")){
                    mia.numericInputs(pojo,strFieldId);
                } else if (mia.miaDataType().equalsIgnoreCase("ALPHA_NUMERIC")) {
                    mia.alphaNumericInputs(pojo,strFieldId);
                }else if (mia.miaDataType().equalsIgnoreCase("ALPHA")) {
                    mia.alphaInputs(pojo,strFieldId);
                }
            }
        }
    }


    public void picklistInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String strFieldId) throws ParseException {
        mia.addWithinMinimumMaximumOptions(pojo,CheckboxObject.minimum,strFieldId);
        int numberOfOptionsSelected = mia.getNumberOfOptionsSelected(pojo,strFieldId);
        ArrayList<String> optionsName = new ArrayList<>();
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        for(int x = 0; x<numberOfOptionsSelected;x++){
            optionsName.add(mia.getPicklistSelectedOptionsName(elementId,x+1));
        }
        mia.recordInputsFromPicklist(elementId,optionsName);
    }

    public String getElementName(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        String result="";
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
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

    public int identifyHiddenCheckBox(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strWhenFieldId){
        int checkBoxNumber = 0;
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object objects: pages.getObjects()
            ) {
                if(objects.getSubQuestionFields()!=null){
                    for (com.Formic.OF2.utils.Pojo.SubQuestionField sub: objects.getSubQuestionFields()
                    ) {
                        if(sub.getGuidId()!=null){
                            if(strWhenFieldId.equalsIgnoreCase(sub.getGuidId())){
                                if(objects.getTickboxResponses()!=null){
                                    for (com.Formic.OF2.utils.Pojo.TickboxResponse tickBox: objects.getTickboxResponses()
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

    public void getRoutingItems(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            CheckboxObject.fieldId.add(routing.getFieldId());
        }
    }

    public void getCheckboxFieldItems(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        for (var pages : pojo.data.project.getPages()
             ) {
            for (var object : pages.getObjects()
                 ) {
               if(object.getTypename()!=null){
                   if (object.getTypename().equalsIgnoreCase("TickboxGroup")){
                       for (var sub : object.getSubQuestionFields()
                            ) {
                           if(!skipSingleHiddenCheckbox(pojo,sub.getGuidId())){
                               CheckboxObject.fieldId.add(sub.getGuidId());
                           }
                       }
                   }
               }
            }
        }
    }

    public void clickSpecificRadioButton(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId,String hasValue) {
        if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,strFieldId)){
            CheckboxMatrix.clickSpecificRadioButton(pojo,strFieldId,hasValue);
        }else{
            String elementId = getObjectIdFromFieldId(pojo,strFieldId);
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

    }

    public void unselectCheckboxThatDisableField(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, WebElement element, String elementId){
        do{
            String strFieldId = getFieldIdByObjectId(pojo,elementId);
            for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
                 ) {
                if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                    for (com.Formic.OF2.utils.Pojo.Condition condition : routing.getConditions()
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

    public void clickSpecificRadioButtonForEnableDisableFields(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String elementId,String hasValue) {
        String elem = stringReplaceTwoValues(actionLocator,elementId,hasValue);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        String name = getFieldNameByElementId(pojo,elementId);
        if(!element.isSelected()&&!hasValue.equalsIgnoreCase("0")){
            Reporter.log("Check if "+name+" is clickable.");
            String strFieldId = getFieldIdByObjectId(pojo,elementId);
            outerLoop:
            for(boolean x = false; x == false;){
                for (com.Formic.OF2.utils.Pojo.Routing routing : pojo.data.project.getRouting()
                ) {
                    if(!element.isEnabled()){
                        if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                            for (com.Formic.OF2.utils.Pojo.Condition condition : routing.getConditions()
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

    public void clickFieldNameInCompletionErrors(String strFieldName){
        String elem = stringReplace(completionErrorsFieldNameButton,strFieldName);
        WebElement element = stringToWebElement(elem);
        element.click();
    }

    //#region getFieldIds for mia hro and picklist
    public void getAllFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            CheckboxObject.fieldId.add(routing.getFieldId());
        }

        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
        ) {
            if(!skipSingleHiddenCheckbox(pojo,fields.getGuidId())){
                CheckboxObject.fieldId.add(fields.getGuidId());
            }
        }
        CheckboxObject.fieldId = removeDuplicates(CheckboxObject.fieldId);
    }
    public void getFieldIdHro(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")){
                        CheckboxObject.fieldId.add(object.getFieldId());
                    }
                }
            }
        }
    }

    public void getFieldIdMia(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("ManualImageAreaText")){
                        CheckboxObject.fieldId.add(object.getFieldId());
                    }
                }
            }
        }
    }

    public void getFieldIdPicklist(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("PickList")){
                        CheckboxObject.fieldId.add(object.getFieldId());
                    }
                }
            }
        }
    }
    //#endregion

    //#region Routing rules validation
    public void checkboxEnabledDisabledValidation(String objectId, String action,com.Formic.OF2.utils.Pojo.FormContentPojo pojo) {
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

    public void checkboxEnabledDisabledValidationForHandwritingRecognitionObject(String objectId, String action, com.Formic.OF2.utils.Pojo.FormContentPojo pojo) {
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

    public void checkboxEnabledDisabledValidationForMia(String objectId, String action, com.Formic.OF2.utils.Pojo.FormContentPojo pojo) {
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
    //#endregion

    //#region Asserts
    public void assertEnableDisableFields(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String typeName, String strElementId, String action){
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

    public static void AssertMandatoryFieldsWithInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
        lookForTheField(pojo,strFieldId);
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String element;
        if(mia.isFieldIdPickList(pojo,strFieldId)||mia.isFieldIdMia(pojo,strFieldId)){
            element = stringReplace(mandatoryFieldMessagePickListLocator,elementId);
        }else{
            element = stringReplace(mandatoryFieldMessageLocator,elementId);
        }
        WebElement validationMessageLocator = stringToWebElement(element);
        scrollElementIntoView(driver,validationMessageLocator);
        String fieldName = getFieldName(pojo,strFieldId);
        Assert.assertEquals(validationMessageLocator.getText(),"This field is mandatory.", fieldName+"The expected validation message for "+fieldName+" was: This field is mandatory. but the actual message was: "+validationMessageLocator.getText());
        Reporter.log("<b>Checkbox Name: <b/>"+fieldName+" <b>is mandatory. <b/>",true);
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void AssertMandatoryFieldsWithoutInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
        lookForTheField(pojo,strFieldId);
        if(mia.isFieldIdPickList(pojo,strFieldId)||mia.isFieldIdMia(pojo,strFieldId)){
            if(CheckboxObject.minimum==1||CheckboxObject.minimum==0){
                mia.assertMiaMandatoryField(pojo,strFieldId);
            }
        }else if (isFieldIdCheckBox(pojo,strFieldId)){
            if(CheckboxObject.minimum==1||CheckboxObject.minimum==0) {
                if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,strFieldId)){
                    if(CheckboxMatrix.isCheckboxMatrixFieldNotChecked(pojo,strFieldId)){
                        CheckboxMatrix.assertRequiredField(pojo,getObjectIdFromFieldId(pojo,strFieldId),getFieldName(pojo,strFieldId));
                    }else {
                        CheckboxMatrix.assertThisFieldIsMandatory(pojo,getObjectIdFromFieldId(pojo,strFieldId),getFieldName(pojo,strFieldId));
                    }
                }else{
                    if(isCheckboxFieldNotChecked(pojo,strFieldId)){
                        assertRequiredField(getObjectIdFromFieldId(pojo,strFieldId),getFieldName(pojo,strFieldId));
                    }else{
                        assertThisFieldIsMandatory(getObjectIdFromFieldId(pojo,strFieldId),strFieldId);
                    }
                }
            }
        } else if (hro.isFieldIdHro(pojo,strFieldId)) {
            if(CheckboxObject.minimum==1||CheckboxObject.minimum==0) {
                hro.assertHroMandatoryField(pojo, strFieldId);
            }
        }
        String fieldName = getFieldName(pojo,strFieldId);
        Reporter.log("<b>Checkbox Name: <b/>"+fieldName+" <b>is mandatory. <b/>",true);
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public void assertCheckboxMandatoryField(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        String fieldName = getFieldName(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(CheckboxObject.mandatory){
            if (isFieldIdCheckBox(pojo,strFieldId)){
                if(CheckboxObject.minimum==1||CheckboxObject.minimum==0) {
                    if(CheckboxMatrix.isFieldIdCheckBoxMatrix(pojo,strFieldId)){
                        if(CheckboxMatrix.isCheckboxMatrixFieldNotChecked(pojo,strFieldId)){
                            CheckboxMatrix.assertRequiredField(pojo,getObjectIdFromFieldId(pojo,strFieldId),getFieldName(pojo,strFieldId));
                        }else {
                            CheckboxMatrix.assertThisFieldIsMandatory(pojo,getObjectIdFromFieldId(pojo,strFieldId),getFieldName(pojo,strFieldId));
                        }
                    }else{
                        if(isCheckboxFieldNotChecked(pojo,strFieldId)){
                            assertRequiredField(getObjectIdFromFieldId(pojo,strFieldId),fieldName);
                        }else{
                            assertThisFieldIsMandatory(getObjectIdFromFieldId(pojo,strFieldId),strFieldId);
                        }
                    }
                }
            }
            Reporter.log("<b>Checkbox Name: <b/>"+fieldName+" <b>is mandatory. <b/>",true);
        }else {
            String elementForValidation = stringReplace(validationMessageLocator,elementId);
            Reporter.log("<b>No validation message should be displayed.</b>" );
            Assert.assertTrue(driver.findElements(By.xpath(elementForValidation)).size()==0,"No validation message should be displayed for "+ fieldName +".");
        }
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void assertWithinAcceptedInputs(String strName, String strObjectElementId){
        String elemValidationMessage = stringReplace(newValidationMessageUponSubmit,strObjectElementId);
        String elemCompilationErrors = stringReplace(validationMessageUponSubmitSideBar,strObjectElementId);
        Assert.assertFalse(isElementVisible(driver,elemValidationMessage),"The expected for : "+strName + " There shouldn't be any validation message displayed below the object.");
        Assert.assertFalse(isElementVisible(driver,elemCompilationErrors),"The expected for: "+strName + "Completion Errors isn't displayed. The actual is Completion errors is displayed.");
        Reporter.log("There shouldn't be any validation message displayed below the field: "+ strName);
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

    public static void assertRequiredField(String strObjectElementId, String name){
        comp.validateCompletionErrorMessage(name,"Required field.");
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(validationMessageLocator,strObjectElementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"Required field.","The expected value is : Required field. "+validationMessageUnderCheckbox.getText());
        CheckboxObject.checkboxObjectDefaultValue();
    }

    public static void assertThisFieldIsMandatory(String strObjectElementId, String name){
        WebElement validationMessageUnderCheckbox = stringReplaceAndConvertToWebElement(mandatoryFieldMessageLocator,strObjectElementId);
        scrollElementIntoView(driver,validationMessageUnderCheckbox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Assert.assertEquals(validationMessageUnderCheckbox.getText(),"This field is mandatory.","The expected value is for the field "+name+" : This field is mandatory. "+validationMessageUnderCheckbox.getText());
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

    public void assertLessThanMinimumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) {
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

    public void assertWithinMinimumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickWithinMinimumInput(CheckboxObject.minimum,elementId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFieldsWithInputs(pojo,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public static void assertWithinMinimumMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(hro.isFieldIdHro(pojo,strFieldId)){
            String inputs;
            if(Objects.requireNonNull(CheckboxObject.strDataTypeNew).equalsIgnoreCase("NUMERIC")){
                inputs = numericInputs(pojo,strFieldId,hro.identifyMaximumInputsByFieldId());
                hro.setTextToHro(pojo,strFieldId,inputs);
            } else if (Objects.requireNonNull(CheckboxObject.strDataTypeNew).equalsIgnoreCase("ALPHA_NUMERIC")) {
                inputs = alphaNumericInputs(pojo,strFieldId, hro.identifyMaximumInputsByFieldId());
                hro.setTextToHro(pojo,strFieldId,inputs);
            }else if (Objects.requireNonNull(CheckboxObject.strDataTypeNew).equalsIgnoreCase("ALPHA")) {
                inputs = alphaInputs(pojo,strFieldId, hro.identifyMaximumInputsByFieldId());
                hro.setTextToHro(pojo,strFieldId,inputs);
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
            AssertMandatoryFieldsWithInputs(pojo,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

//    public void assertBeyondMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
//        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
//        lookForTheField(pojo,strFieldId);
//        int numberOfItems = countCheckboxItems(elementId);
//        clickBeyondMaximumInput(pojo,CheckboxObject.maximum,strFieldId,numberOfItems);
//        if(CheckboxObject.mandatory){
//            AssertMandatoryFieldsWithInputs(pojo,strFieldId);
//        }else{
//            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
//        }
//    }

    public void assertWithinMaximumInput(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        int numberOfItems = countCheckboxItems(elementId);
        clickWithinMaximumInput(pojo,CheckboxObject.maximum,strFieldId,numberOfItems);
        if(CheckboxObject.mandatory){
            AssertMandatoryFieldsWithInputs(pojo,strFieldId);
        }else{
            assertWithinAcceptedInputs(CheckboxObject.checkboxName,elementId);
        }
    }

    public void assertMinimumConfig(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) {
        String elementId = getObjectIdFromFieldId(pojo,strFieldId);
        lookForTheField(pojo,strFieldId);
        if(CheckboxObject.minimum==1){
            assertRequiredField(elementId,CheckboxObject.checkboxName);
        }else {
            assertMinimumConfigNoInputs(elementId,CheckboxObject.checkboxName,CheckboxObject.minimum);
        }
    }
    //#endregion

    //#region Submit and save functions
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
            return "";
        }
    }

    public void clickContinueButton(){
        try{
            String continueBtn = "//button[text()='Continue']";
            WebElement element = stringToWebElement(continueBtn);
            click(element);
        }catch(Exception e){
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
        }
    }
    //#endregion
}
