package Objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CheckboxObject {

    //Checkbox is Mandatory
    public static boolean mandatory;
    public static int maximum;
    public static int minimum;
    //Checkbox has Maximum inputs
    public static boolean isMax;
    //Maximum inputs allowed
    public static int maxNum;
    //Checkbox has Minimum inputs
    public static boolean isMin;
    //Minimum inputs allowed
    public static int minNum;
    //checkbox caption
    public static String checkboxName;

    public static String completionErrorName;

    public static boolean isSubmitButtonClicked;

    public static int ruleCounter;

    public static int numberOfInputs;

    public static boolean multiResponse;

    public static String guidIdForRules;

    public static String guidIdForElements;

    public static boolean isCheckboxMatrix;

    public static boolean elementHidden;

    public static boolean withinMinimumInputs;

    public static boolean withinMaximumInputs;

    public static boolean lessThanMinimumInputs;

    public static boolean beyondMaximumInputs;

    public static boolean withinMinimumMaximumInputs;

    public static boolean minimumConfig;

    public static String strFieldId;

    public static ArrayList<String> fieldId = new ArrayList<>();
    public static ArrayList<String> checkboxInputs = new ArrayList<>();
    public static ArrayList<String> hasValueList = new ArrayList<>();
    public static boolean isMandatoryFieldTest;
    public  static String strFormatMask;
    public  static String strFormatRegex;
    public  static String strDataTypeNew;
    public static boolean isEnableDisabledFields;
    public static boolean isCheckbox;
    public static boolean isHro;
    public static boolean isMia;
    public static String singleFieldId;
    public static int testCaseId;
    public static byte[] encodeBytes;
    public static File screenShot;
    public static String scenarioName;
    public static String errorMessage;

    public static void checkboxObjectDefaultValue(){
        errorMessage="";
        scenarioName ="";
        screenShot = null;
        encodeBytes =null;
        testCaseId = 0;
        singleFieldId="";
        hasValueList.clear();
        maximum=0;
        minimum=0;
        isSubmitButtonClicked=false;
        minimumConfig = false;
        strFieldId="";
        isMandatoryFieldTest = false;
        beyondMaximumInputs = false;
        withinMaximumInputs = false;
        lessThanMinimumInputs = false;
        withinMinimumInputs = false;
        mandatory = false;
        isMax = false;
        maxNum = 0;
        isMin = false;
        minNum = 0;
        checkboxName = "";
        completionErrorName ="";
        ruleCounter = 0;
        numberOfInputs = 0;
        multiResponse=false;
        guidIdForRules="";
        isCheckboxMatrix=false;
        guidIdForElements="";
        elementHidden=false;
    }


}
