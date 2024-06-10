package com.Formic.OF2.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;

public class DataDrivenTest {

    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet1");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 1; i <= rowCount; i++) { // Start from 1 to skip header row
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                data[i - 1][j] = row.getCell(j).getStringCellValue();
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataEnable")
    public Object[][] testDataEnable() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet2");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataMinInputs")
    public Object[][] testDataMinInputs() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet3");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataMaxInputs")
    public Object[][] testDataMaxInputs() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet4");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataManualImageArea")
    public Object[][] testDataManualImageArea() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet5");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataHroNumeric")
    public Object[][] testDataHroNumeric() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet6");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataHroDataFormatting")
    public Object[][] testDataHroDataFormatting() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet9");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataHroDateTime")
    public Object[][] testDataHroDateTime() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("Sheet7");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataHroEmail")
    public Object[][] testDataHroEmail() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("Sheet8");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }

    @DataProvider(name = "testDataHroMandatory")
    public Object[][] testDataHroMandatory() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = "src/main/resources/Test_Data.xls";
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("Sheet10");
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        // Loop through rows and columns to read data
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                if (row.getCell(j) != null) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                } else {
                    data[i][j] = ""; // Handle null cells
                }
            }
        }
        workbook.close();
        System.out.println("Your excel file has been generated!");
        return data;
    }


    public static void writeToExcel(ArrayList<String> fieldIds) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet("sheet1");
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        int fieldSize = fieldIds.size()+1;
        for (int i = 1; i < fieldSize; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void createExcelTestDataFile() throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet1 = workbook.createSheet("sheet1");
        HSSFSheet sheet2 = workbook.createSheet("sheet2");
        HSSFSheet sheet3 = workbook.createSheet("sheet3");
        HSSFSheet sheet4 = workbook.createSheet("sheet4");
        HSSFSheet sheet5 = workbook.createSheet("sheet5");
        HSSFSheet sheet6 = workbook.createSheet("sheet6");
        HSSFSheet sheet7 = workbook.createSheet("sheet7");
        HSSFSheet sheet8 = workbook.createSheet("sheet8");
        HSSFSheet sheet9 = workbook.createSheet("sheet9");
        HSSFSheet sheet10 = workbook.createSheet("sheet10");

        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelEnableDisable(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("WhenFieldId");
        rowHead.createCell(1).setCellValue("hasValue");
        rowHead.createCell(2).setCellValue("fieldId");
        rowHead.createCell(3).setCellValue("action");
        for (int i = 1; i < fieldIds.size()+1; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(2).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(3).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelCheckboxMinimumInputs(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Minimum");
        rowHead.createCell(2).setCellValue("Mandatory");
        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(2).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelCheckboxMaximumInputs(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Maximum");
        rowHead.createCell(2).setCellValue("Mandatory");
        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(2).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelMiaAndHro(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("FormatMask");
        rowHead.createCell(4).setCellValue("FormatRegex");
        rowHead.createCell(5).setCellValue("DataType");
        rowHead.createCell(6).setCellValue("Derivation");
        rowHead.createCell(7).setCellValue("Validation");
        rowHead.createCell(8).setCellValue("IsMultiResponse");
        rowHead.createCell(9).setCellValue("Maximum");
        rowHead.createCell(10).setCellValue("Minimum");
        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(2).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(3).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(4).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(5).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(6).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(7).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(8).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(9).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(10).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelHroNumeric(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("Maximum");

        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(2).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(3).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelHroDataFormatting(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("FormatMask");

        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(2).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(3).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelHroMandatory(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");

        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelHroDateTime(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("FormatMask");

        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(2).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(3).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void writeToExcelHroEmail(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = "src/main/resources/Test_Data.xls";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");

        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(2).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    public static void deleteWorkbook() {
        // Delete the Excel file
        boolean deleted = new File("src/main/resources/Test_Data.xlsx").delete();
        if (deleted) {
            System.out.println("Workbook deleted successfully.");
        } else {
            System.out.println("Failed to delete workbook.");
        }
    }


}

