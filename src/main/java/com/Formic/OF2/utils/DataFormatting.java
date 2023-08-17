package com.Formic.OF2.utils;

public class DataFormatting {

    public static String dataFormat(String formatMask){
        String result ="";
        if(formatMask.equalsIgnoreCase("A_N_[]NAA")){
            result = "PostCode";
        } else if (formatMask.equalsIgnoreCase("A*")) {
            result = "SurName";
        } else if (formatMask.equalsIgnoreCase("[0][ 0123456789]*")) {
            result = "Telephone";
        } else if (formatMask.equalsIgnoreCase("?*")) {
            result = "Address";
        } else if (formatMask.equalsIgnoreCase("NN[-]NN[-]NN")) {
            result = "BankSortCode";
        } else if (formatMask.equalsIgnoreCase("?*[@]?*[.]?*")) {
            result = "Email";
        } else if (formatMask.equalsIgnoreCase("NNNN[ ]NNNN[ ]NNNN[ ]NNNN")) {
            result = "CreditCardNumber";
        }
        return result;
    }
}
