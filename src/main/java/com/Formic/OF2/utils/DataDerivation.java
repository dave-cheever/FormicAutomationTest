package com.Formic.OF2.utils;

import com.Formic.OF2.test.BasePage;
import com.Formic.OF2.utils.Pojo.FormContentPojo;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataDerivation extends BasePage {
    public DataDerivation(WebDriver driver) {
        super(driver);
    }

    public static void getFieldIdDerivationByTypeNameAndOperator(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName, String operator){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        if(isFieldDerivationHasArithmeticOperator(pojo,object.getFieldId(),operator)){
                            CheckboxObject.fieldId.add(object.getFieldId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void getFieldIdDerivationByTypeNameAndNoOperator(com.Formic.OF2.utils.Pojo.FormContentPojo pojo,String typeName){
        for (com.Formic.OF2.utils.Pojo.Page pages: pojo.data.project.getPages()
        ) {
            for (com.Formic.OF2.utils.Pojo.Object object: pages.getObjects()
            ) {
                if(object.getGuidId()!=null){
                    if(object.getTypename().equalsIgnoreCase(typeName)){
                        if(FieldValidation.isFieldIdMia(pojo,object.getFieldId())){
                            if(isFieldDerivationHasNoArithmeticOperator(pojo,object.getFieldId())){
                                CheckboxObject.fieldId.add(object.getFieldId());
                                break;
                            }
                        }
                    }
                }
            }
        }


    }

    public static boolean isFieldDerivationHasNoArithmeticOperator(FormContentPojo pojo, String strFieldId){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String derivation = fields.getDerivation();
                if(derivation!=null&&!hasArithmeticExpression(derivation)){
                    CheckboxObject.fieldName = transformString(fields.getDerivation());
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasArithmeticExpression(String derivation) {
        // Define a regular expression to match arithmetic expressions
        String arithmeticExpressionRegex = ".*[+\\-*/].*";

        // Use Pattern and Matcher to check if the derivation matches the arithmetic expression regex
        Pattern pattern = Pattern.compile(arithmeticExpressionRegex);
        Matcher matcher = pattern.matcher(derivation);

        return matcher.matches();
    }

    public static boolean containsSpace(String input) {
        // Check if the input string contains a space character
        return input.contains(" ");
    }


    public static boolean isFieldDerivationHasArithmeticOperator(FormContentPojo pojo, String strFieldId,String operation){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String derivation = fields.getDerivation();
                if(derivation!=null){
                    if(containsSpace(derivation)){
                        String[] EqualsTo = derivation.split(" ");
                        if(EqualsTo[1].equalsIgnoreCase(operation)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param num Will only accept 0 for the first field and 2 for the second field.
     * @return the name of the field
     */
    public static String getDerivationFieldNameByNumber(FormContentPojo pojo, String strFieldId, int num){
        for (com.Formic.OF2.utils.Pojo.Field fields: pojo.data.project.getFields()) {
            if(fields.getGuidId().equalsIgnoreCase(strFieldId)){
                String derivation = fields.getDerivation();
                String[] EqualsTo = derivation.split(" ");
                String fieldName = EqualsTo[num];
                return transformString(fieldName);
            }
        }
        return "";
    }

    /**
     * Removes the first and second characters, as well as the 2nd to the last and last characters from the input string.
     *
     * @param input The input string to be transformed.
     * @return The transformed string with specified characters removed.
     */
    public static String transformString(String input){
        String removedFirstTwo = input.substring(2);
        // Remove 2nd to last and last characters
        input = removedFirstTwo.substring(0, removedFirstTwo.length() - 2);
        return input;
    }


}
