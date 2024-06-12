package com.Formic.OF2.utils;

import com.Formic.OF2.pages.CheckboxMatrix;
import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import com.beust.ah.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldManager extends BasePage {
    public FieldManager(WebDriver driver) {
        super(driver);
    }

    static String fieldSetLocator = "//div[@data-object-id='$TEXT']/fieldset/input[1]";

    /**
     * Determines whether the specified field ID corresponds to a CheckboxMatrix element within the given form content.
     *
     * @param pojo The FormContentPojo object representing the form content to be analyzed.
     * @param strFieldId The field ID to be checked for being a CheckboxMatrix element.
     * @return {@code true} if the field ID corresponds to a CheckboxMatrix element, {@code false} otherwise.
     */
    public static boolean isFieldIdCheckBoxMatrix(FormContentPojo pojo, String strFieldId){
        boolean result = false;
        String strObjectId = getObjectIdFromFieldId(pojo,strFieldId);
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getSubQuestionFields()!=null){
                    if(object.getGuidId().equalsIgnoreCase(strObjectId)){
                        if(object.getSubQuestionFields().size()>1){
                            result = true;
                            break outerLoop;
                        }
                    }

                }
            }
        }
        return result;
    }

    /**
     * Retrieves the field IDs of checkboxes within the specified project/form,
     * excluding CheckboxMatrix elements and hidden checkboxes.
     *
     * @param pojo The FormContentPojo object representing the form content to be analyzed.
     * It contains the data structure representing the form's pages and objects.
     * Checkbox field IDs are stored in the 'fieldId' ArrayList of the CheckboxObject class.
     */
    public static ArrayList<String> getAllCheckboxFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = new ArrayList<>();
        for (var pages : pojo.data.project.getPages()
        ) {
            for (var object : pages.getObjects()
            ) {
                if(object.getTypename()!=null){
                    if (object.getTypename().equalsIgnoreCase("TickboxGroup")){
                        for (var sub : object.getSubQuestionFields()
                        ) {
                            if(!skipSingleHiddenCheckbox(pojo,sub.getGuidId())){
                                if(!isFieldIdCheckBoxMatrix(pojo,sub.getGuidId())){
                                    fieldIds.add(sub.getGuidId());
                                }
                            }
                        }
                    }
                }
            }
        }
        return fieldIds;
    }

    /**
     * Checks whether the specified field ID corresponds to a single hidden checkbox within the given form content.
     *
     * @param pojo The FormContentPojo object representing the form content to be analyzed.
     * @param fieldId The field ID to be checked for being a single hidden checkbox.
     * @return {@code true} if the field ID corresponds to a single hidden checkbox, {@code false} otherwise.
     */
    public static boolean skipSingleHiddenCheckbox(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
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

    /**
     * Retrieves the field IDs of checkboxes within the specified form content and populates the CheckboxObject with
     * field IDs that adhere to mandatory rules.
     *
     * @param pojo The FormContentPojo object representing the form content to be analyzed.
     */
    public static ArrayList<String> getAllCheckboxFieldIdWithMandatoryRules(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getAllCheckboxFieldId(pojo);
        ArrayList<String> mandatoryCheckBoxFieldIds = new ArrayList<>();
        for (String fields: fieldIds) {
            for(com.Formic.OF2.utils.Pojo.Field field: pojo.data.project.getFields()){
                if(fields.equalsIgnoreCase(field.getGuidId())){
                    if(field.getMandatory()){
                        mandatoryCheckBoxFieldIds.add(field.getGuidId());
                    }
                }
            }
        }
        return mandatoryCheckBoxFieldIds;
    }

    public static ArrayList<String> getCheckboxRulesForMinimumInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getAllCheckboxFieldIds(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)){
                    if(fields.getResponses()!=null&&fields.getResponses().getMinimum()!=0){
                        result.add(fieldId);
                        result.add(fields.getResponses().getMinimum().toString());
                        result.add(fields.getMandatory().toString());
                    }else{
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getCheckboxRulesForMaximumInputs(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getAllCheckboxFieldIds(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)){
                    if(fields.getResponses()!=null&&fields.getResponses().getMaximum()!=0){
                        result.add(fieldId);
                        result.add(fields.getResponses().getMaximum().toString());
                        result.add(fields.getMandatory().toString());
                    }else{
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getFieldIdMia(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> result = new ArrayList<>();
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("ManualImageAreaText")){
                        result.add(object.getFieldId());
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getFieldIdHro(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> result = new ArrayList<>();
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase("HandwritingRecognitionObject")){
                        result.add(object.getFieldId());
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getCheckboxRulesForManualImageArea(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getFieldIdMia(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)&&!DataValidation.isFieldIdPickList(pojo,fieldId)){
                    result.add(fieldId);
                    result.add(fields.getMandatory().toString());
                    result.add(fields.getName());
                    result.add(fields.getFormatMask());
                    result.add(fields.getFormatRegex());
                    result.add(fields.getDataTypeNew());
                    result.add(fields.getDerivation());
                    result.add(fields.getValidation());
                    if(fields.getResponses()!=null){
                        result.add(fields.getResponses().getIsMultiResponse().toString());
                        result.add(fields.getResponses().getMaximum().toString());
                        result.add(fields.getResponses().getMinimum().toString());

                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getCheckboxRulesForHandWritingRecognitionObject(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getFieldIdHro(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)&&!DataValidation.isFieldIdPickList(pojo,fieldId)){
                    result.add(fieldId);
                    result.add(fields.getMandatory().toString());
                    result.add(fields.getName());
                    result.add(fields.getFormatMask());
                    result.add(fields.getFormatRegex());
                    result.add(fields.getDataTypeNew());
                    result.add(fields.getDerivation());
                    result.add(fields.getValidation());
                    if(fields.getResponses()!=null){
                        result.add(fields.getResponses().getIsMultiResponse().toString());
                        result.add(fields.getResponses().getMaximum().toString());
                        result.add(fields.getResponses().getMinimum().toString());

                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getHandWritingRecognitionObjectRulesNumeric(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getFieldIdHro(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)) {
                    if (fields.getDataTypeNew().equalsIgnoreCase("NUMERIC")){
                        if (isNumericPatternViaFormatRegex(fields.getFormatRegex())) {
                            result.add(fieldId);
                            result.add(fields.getMandatory().toString());
                            result.add(fields.getName());
                            result.add(Integer.toString(getMaxLengthViaFormatRegex(fields.getFormatRegex())));
                        }
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getHandWritingRecognitionObjectRulesDataFormatting(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getFieldIdHro(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)) {
                    if (fields.getDataTypeNew().equalsIgnoreCase("ALPHA_NUMERIC")){
                        if (fields.getFormatMask()!=null&&!fields.getFormatRegex().equalsIgnoreCase("^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$")) {
                            result.add(fieldId);
                            result.add(fields.getMandatory().toString());
                            result.add(fields.getName());
                            result.add(fields.getFormatMask());
                        }
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getHandWritingRecognitionObjectRulesDataFormattingValid(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getFieldIdHro(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)) {
                    if (fields.getDataTypeNew().equalsIgnoreCase("ALPHA_NUMERIC")){
                        if (fields.getFormatMask()!=null&&!fields.getFormatRegex().equalsIgnoreCase("^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+$")) {
                            result.add(fieldId);
                            result.add(fields.getMandatory().toString());
                            result.add(fields.getName());
                            result.add(fields.getFormatRegex());
                        }
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getHandWritingRecognitionObjectRulesMandatory(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getFieldIdHro(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)) {
                    if (fields.getMandatory().booleanValue()==true){
                        result.add(fieldId);
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getHandWritingRecognitionObjectRulesDateTime(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getFieldIdHro(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)) {
                    if (fields.getDataTypeNew().equalsIgnoreCase("DATE_TIME")){
                        result.add(fieldId);
                        result.add(fields.getMandatory().toString());
                        result.add(fields.getName());
                        result.add(fields.getFormatMask());
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getHandWritingRecognitionObjectRulesEmail(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> fieldIds = getFieldIdHro(pojo);
        ArrayList<String> result = new ArrayList<>();
        for(String fieldId: fieldIds){
            for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()
            ) {
                if(fields.getGuidId().equalsIgnoreCase(fieldId)) {
                    if (fields.getDataTypeNew().equalsIgnoreCase("ALPHA_NUMERIC")){
                        if (fields.getFormatMask().equalsIgnoreCase("?*[@]?*[.]?*")) {
                            result.add(fieldId);
                            result.add(fields.getMandatory().toString());
                            result.add(fields.getName());
                        }
                    }
                }
            }
        }
        return result;
    }






    public static ArrayList<String> getRoutingFieldsEnableDisable(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> result = new ArrayList<>();
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for(com.Formic.OF2.utils.Pojo.Condition condition: routing.getConditions()){
                    result.add(condition.getWhenField());
                    result.add(condition.getHasValue());
                    result.add(routing.getFieldId());
                    result.add(condition.getAction());
            }
        }
        return result;
    }

    public static String getElementNameByFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId){
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

    public static boolean isMandatory(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        boolean result = false;
        for(com.Formic.OF2.utils.Pojo.Field field: pojo.data.project.getFields()){
            if(fieldId.equalsIgnoreCase(field.getGuidId())){
                result = field.getMandatory();
            }
        }
        return result;
    }

    /**
     * Retrieves the object ID corresponding to the given field ID within the specified form content.
     *
     * @param pojo The FormContentPojo object representing the form content to be analyzed.
     * @param strWhenFieldId The field ID for which the corresponding object ID is to be retrieved.
     * @return The object ID associated with the specified field ID, or null if not found.
     */
    public static String getObjectIdByFieldId(FormContentPojo pojo, String strWhenFieldId){
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

    /**
     * Retrieves the field ID associated with the given object ID within the specified form content.
     *
     * @param pojo The FormContentPojo object representing the form content to be analyzed.
     * @param strObjectId The object ID for which the corresponding field ID is to be retrieved.
     * @return The field ID associated with the specified object ID, or an empty string if not found.
     */
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

    public void getCheckboxObjectId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        getAllCheckboxFieldId(pojo);
        for (String fields: CheckboxObject.fieldId) {
            for(com.Formic.OF2.utils.Pojo.Field field: pojo.data.project.getFields()){
                if(fields.equalsIgnoreCase(field.getGuidId())){
                    if(field.getMandatory()){
                        CheckboxObject.CheckBoxFieldIdMandatory.add(field.getGuidId());
                    }
                }
            }
        }
    }

    public static int getCheckBoxResponseMinValue(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId){
        int result = 0;
        for (com.Formic.OF2.utils.Pojo.Field field: pojo.data.project.getFields()
        ) {
            if(field.getGuidId().equalsIgnoreCase(fieldId)){
                if(field.getResponses()!=null){
                    result = field.getResponses().getMinimum();
                }
            }
        }
        return result;
    }


    public static String getTypeNameByFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) {
        String result = null;
        for (com.Formic.OF2.utils.Pojo.Page page : pojo.data.project.getPages()) {
            for (com.Formic.OF2.utils.Pojo.Object object : page.getObjects()) {

                        if (object.getSubQuestionFields() != null) {
                            for (com.Formic.OF2.utils.Pojo.SubQuestionField sub : object.getSubQuestionFields()) {
                                if (sub.getGuidId().equalsIgnoreCase(strFieldId)) {
                                    result = object.getTypename();
                                    break;
                                }
                            }
                        } else if (object.getFieldId()!=null){
                            if(object.getFieldId().equalsIgnoreCase(strFieldId)){
                                result = object.getTypename();
                                break;
                            }
                        }
            }
        }
        return result;
    }



    public static ArrayList<String> findDisablingFieldIds(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId) throws Exception {
        ArrayList<String> disablingFieldIds = new ArrayList<>();
        String currentFieldId = fieldId;
        String lastFieldId = "";

        while (!lastFieldId.equalsIgnoreCase(currentFieldId)) {
            lastFieldId = currentFieldId;
            if (isFieldDisabled(pojo, currentFieldId)) {
                // Find the field that disables the current field
                disablingFieldIds = findEnablingFieldId(pojo, currentFieldId);
                currentFieldId = disablingFieldIds.get(disablingFieldIds.size() - 2);
            } else {
                break;
            }
        }

        return disablingFieldIds;
    }

    private static boolean isFieldDisabled(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String fieldId) {
        String objectId = getObjectIdByFieldId(pojo,fieldId);
        String elem = stringReplace(fieldSetLocator,objectId);
        WebElement element = stringToWebElement(elem);
        scrollElementIntoView(driver,element);
        return !element.isEnabled();
    }

    public static ArrayList<String>findEnablingFieldId(com.Formic.OF2.utils.Pojo.FormContentPojo pojo, String strFieldId) throws Exception {
        ArrayList<String> result = new ArrayList<>();
        String fieldIdElementId;
        outerLoop:
        for (com.Formic.OF2.utils.Pojo.Routing routing: pojo.data.project.getRouting()
        ) {
            for (com.Formic.OF2.utils.Pojo.Condition conditions: routing.getConditions()
            ) {
                if(routing.getFieldId().equalsIgnoreCase(strFieldId)){
                    fieldIdElementId = getObjectIdFromFieldId(pojo,strFieldId);
                    if(fieldIdElementId!=null&&conditions.getAction().equalsIgnoreCase("enable")){
                        result.add(conditions.getWhenField());
                        result.add(conditions.getHasValue());
                        break outerLoop;
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<String> getAllCheckboxFieldIds(com.Formic.OF2.utils.Pojo.FormContentPojo pojo){
        ArrayList<String> result = new ArrayList<>();
        for (var pages : pojo.data.project.getPages()
        ) {
            for (var object : pages.getObjects()
            ) {
                if(object.getTypename()!=null){
                    if (object.getTypename().equalsIgnoreCase("TickboxGroup")){
                        for (var sub : object.getSubQuestionFields()
                        ) {
                            if(!skipSingleHiddenCheckbox(pojo,sub.getGuidId())){
                                result.add(sub.getGuidId());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static boolean isNumericPatternViaFormatRegex(String pattern) {
        // Check if the pattern starts with ^, ends with $, and contains a valid numeric range
        if (pattern.startsWith("^[") && pattern.contains("]{") && pattern.endsWith("}$")) {
            // Extract the inner content between ^[ and ]
            String innerContent = pattern.substring(2, pattern.indexOf(']'));

            // Check if inner content is 0-9
            if (innerContent.equals("0-9")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAlphaNumericPatternViaFormatRegex(String pattern) {
        // Check if the pattern starts with ^, ends with $, and contains a valid numeric range
        if (pattern.startsWith("^[") && pattern.contains("]{") && pattern.endsWith("}$")) {
            // Extract the inner content between ^[ and ]
            String innerContent = pattern.substring(2, pattern.indexOf(']'));

            // Check if inner content is 0-9
            if (innerContent.equals("a-zA-Z0-9")) {
                return true;
            }
        }
        return false;
    }

    public static int getMaxLengthViaFormatRegex(String pattern) {
        // Extract the content between { and }
        int startIndex = pattern.indexOf('{') + 1;
        int endIndex = pattern.indexOf('}');

        if (startIndex > 0 && endIndex > startIndex) {
            // Get the content inside the curly braces
            String lengthConstraint = pattern.substring(startIndex, endIndex);

            // Split the length constraint to get the max length value
            String[] lengths = lengthConstraint.split(",");

            // Return the last value as the max length
            return Integer.parseInt(lengths[lengths.length - 1]);
        }

        // Return -1 if the pattern is not valid
        return -1;
    }

    public static boolean validatePatternMatch(String formatRegex, String input) {
        // Compile the provided format regex
        Pattern pattern = Pattern.compile(formatRegex);

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(input);

        // Return if the input string matches the format regex
        return matcher.matches();
    }
}
