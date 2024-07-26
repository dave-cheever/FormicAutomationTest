package com.Formic.OF2.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.checkerframework.checker.formatter.qual.InvalidFormat;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {
//    private static String testDataFilePath = "$(Build.SourcesDirectory)/src/main/resources/Test_Data.xlsx";
    private static String testDataFilePath = "src/main/resources/Test_Data.xlsx";
    private static boolean isEnabled = Boolean.parseBoolean(ConfigLoader.getProperty("test.iEnabled"));

    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException, InvalidFormatException {
        // Path to your Excel file
        //        String excelFilePath = "src/main/resources/Test_Data.xls";
        String excelFilePath = testDataFilePath;
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
        String excelFilePath = testDataFilePath;
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
        String excelFilePath = testDataFilePath;
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
        String excelFilePath = testDataFilePath;
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

    @DataProvider(name = "testDataHroNumeric")
    public Object[][] testDataHroNumeric() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
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

    @DataProvider(name = "testDataHroDateTime")
    public Object[][] testDataHroDateTime() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("Sheet6");
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
        String excelFilePath = testDataFilePath;
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

    @DataProvider(name = "testDataHroDataFormatting")
    public Object[][] testDataHroDataFormatting() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet8");
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
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("Sheet9");
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

    @DataProvider(name = "testDataManualImageAreaEmail")
    public Object[][] testDataManualImageAreaEmail() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet10");
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

    @DataProvider(name = "testDataManualImageAreaDateTime")
    public Object[][] testDataManualImageAreaDateTime() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet11");
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

    @DataProvider(name = "testDataManualImageAreaNumeric")
    public Object[][] testDataManualImageAreaNumeric() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet12");
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

    @DataProvider(name = "testDataManualImageAreaDataFormatting")
    public Object[][] testDataManualImageAreaDataFormatting() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet13");
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

    @DataProvider(name = "testDataMiaMandatory")
    public Object[][] testDataMiaMandatory() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("Sheet14");
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

    @DataProvider(name = "testDataMiaPickList")
    public Object[][] testDataMiaPickList() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("Sheet15");
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

    @DataProvider(name = "testDataMatrixMandatory")
    public Object[][] testDataMatrixMandatory() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("Sheet16");
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

    @DataProvider(name = "testDataMatrixMinInputs")
    public Object[][] testDataMatrixMinInputs() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet17");
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

    @DataProvider(name = "testDataMatrixMaxInputs")
    public Object[][] testDataMatrixMaxInputs() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet18");
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

    @DataProvider(name = "testHroDataValidationPositiveEqualTo")
    public Object[][] testHroDataValidationPositiveEqualTo() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet19");
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

    @DataProvider(name = "testHroDataValidationPositiveNotEqualTo")
    public Object[][] testHroDataValidationPositiveNotEqualTo() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet20");
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

    @DataProvider(name = "testHroDataValidationPositiveGreaterThan")
    public Object[][] testHroDataValidationPositiveGreaterThan() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet21");
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

    @DataProvider(name = "testHroDataValidationPositiveGreaterThanEqualTo")
    public Object[][] testHroDataValidationPositiveGreaterThanEqualTo() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet22");
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

    @DataProvider(name = "testHroDataValidationPositiveLessThan")
    public Object[][] testHroDataValidationPositiveLessThan() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet23");
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

    @DataProvider(name = "testHroDataValidationPositiveLessThanEqualTo")
    public Object[][] testHroDataValidationPositiveLessThanEqualTo() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet24");
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

    @DataProvider(name = "testMiaDataValidationPositiveEqualTo")
    public Object[][] testMiaDataValidationPositiveEqualTo() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet25");
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

    @DataProvider(name = "testMiaDataValidationPositiveNotEqualTo")
    public Object[][] testMiaDataValidationPositiveNotEqualTo() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet26");
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

    @DataProvider(name = "testMiaDataValidationPositiveGreaterThan")
    public Object[][] testMiaDataValidationPositiveGreaterThan() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet27");
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

    @DataProvider(name = "testMiaDataValidationPositiveGreaterThanEqualTo")
    public Object[][] testMiaDataValidationPositiveGreaterThanEqualTo() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet28");
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

    @DataProvider(name = "testMiaDataValidationPositiveLessThan")
    public Object[][] testMiaDataValidationPositiveLessThan() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet29");
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

    @DataProvider(name = "testMiaDataValidationPositiveLessThanEqualTo")
    public Object[][] testMiaDataValidationPositiveLessThanEqualTo() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet30");
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

    @DataProvider(name = "testHroDataDerivationAdd")
    public Object[][] testHroDataDerivationAdd() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet31");
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

    @DataProvider(name = "testHroDataDerivationSubtract")
    public Object[][] testHroDataDerivationSubtract() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet32");
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

    @DataProvider(name = "testHroDataDerivationDivide")
    public Object[][] testHroDataDerivationDivide() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet33");
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

    @DataProvider(name = "testHroDataDerivationMultiply")
    public Object[][] testHroDataDerivationMultiply() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet34");
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

    @DataProvider(name = "testMiaDataDerivationAdd")
    public Object[][] testMiaDataDerivationAdd() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet35");
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

    @DataProvider(name = "testMiaDataDerivationSubtract")
    public Object[][] testMiaDataDerivationSubtract() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet36");
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

    @DataProvider(name = "testMiaDataDerivationDivide")
    public Object[][] testMiaDataDerivationDivide() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet37");
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

    @DataProvider(name = "testMiaDataDerivationMultiply")
    public Object[][] testMiaDataDerivationMultiply() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet38");
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

    @DataProvider(name = "testHroDataDerivationPropagation")
    public Object[][] testHroDataDerivationPropagation() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet39");
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

    @DataProvider(name = "testMiaDataDerivationPropagation")
    public Object[][] testMiaDataDerivationPropagation() throws IOException, InvalidFormatException {
        // Path to your Excel file
        String excelFilePath = testDataFilePath;
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet("sheet40");
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


    public static void writeToExcel(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        int fieldSize = fieldIds.size()+1;
        for (int i = 1; i < fieldSize; i++) {
            System.out.println("##[command] creating row: "+i);
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            System.out.println("##[command] value: "+fieldIds.get(0)+" set to row: "+ i);
            fieldIds.remove(0);
        }
        // Write workbook to a file
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
        System.out.println("##[command] workbook close write to excel");
    }

    public static void createExcelTestDataFile() throws IOException, InvalidFormatException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        HSSFWorkbook workbook = null;
        try {
            // Create a new workbook
            String excelFilePath = testDataFilePath;
//            fileInputStream = new FileInputStream(new File(excelFilePath));
//            workbook = new HSSFWorkbook(new POIFSFileSystem(fileInputStream));
            workbook = new HSSFWorkbook();
            for (int i = 1; i <= 40; i++) {
                HSSFSheet sheet = workbook.createSheet("sheet" + i);
                System.out.println("##[command] Sheet" + i + " created");
            }
            // Write workbook to a file
            System.out.println("##[command] Write workbook to a file");
            System.out.println("##[command] FileOutputStream outputStream = new FileOutputStream(excelFilePath);");

            File testFile = new File(excelFilePath);
            if (testFile.createNewFile()) {
                System.out.println("##[command] Test file created successfully at " + excelFilePath);
            } else {
                System.out.println("##[warning] Test file already exists at " + excelFilePath);
            }

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            System.out.println("##[command] Before workbook.write(outputStream);");
            workbook.write(outputStream);
            System.out.println("##[command] After workbook.write(outputStream);");

            System.out.println("##[command] Before outputStream.close();");
            outputStream.close();
            System.out.println("##[command] After outputStream.close();");

            System.out.println("##[command] Before workbook.close();");
            workbook.close();
            System.out.println("##[command] After workbook.close();");
            System.out.println("##[command] Workbook is now closed");
        } catch (IOException e) {
            System.out.println("##[error] IOException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("##[error] An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void outputStreamEnabler(HSSFWorkbook workbook, String filePath, Boolean isEnabled) throws IOException {
        // Write workbook to a file
        if(isEnabled){
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        }
    }

    public static void writeToExcelEnableDisable(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelCheckboxMinimumInputs(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelCheckboxMaximumInputs(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelHroDataValidation(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("NumberToValidate");
        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelHroDataDerivationPropagation(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("ObjectId");
        for (int i = 1;fieldIds.size()!=0; i++) {
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
            rowHead.createCell(1).setCellValue(fieldIds.get(0));
            fieldIds.remove(0);
        }
        // Write workbook to a file
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelHroDataDerivation(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelMiaEmail(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelHroNumeric(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelMiaNumeric(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("Maximum");
        rowHead.createCell(4).setCellValue("FormatMask");

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
        }
        // Write workbook to a file
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelMiaDataFormatting(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("FormatMask");
        rowHead.createCell(4).setCellValue("FormatRegEx");

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
        }
        // Write workbook to a file
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelHroDataFormatting(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("FormatRegEx");
        rowHead.createCell(4).setCellValue("FormatMask");

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
        }
        // Write workbook to a file
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelHroDataFormattingValid(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("FormatRegex");

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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelHroMandatory(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelMiaPickList(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        rowHead.createCell(1).setCellValue("Mandatory");
        rowHead.createCell(2).setCellValue("Name");
        rowHead.createCell(3).setCellValue("Min");
        rowHead.createCell(4).setCellValue("Max");

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
        }
        // Write workbook to a file
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelHroDateTime(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }


    public static void writeToExcelHroEmail(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
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
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
    }

    public static void writeToExcelMatrixMandatory(ArrayList<String> fieldIds,String sheetNumber) throws IOException, InvalidFormatException {
        // Create a new workbook
        String excelFilePath = testDataFilePath;
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet(sheetNumber);
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue("FieldId");
        int fieldSize = fieldIds.size()+1;
        for (int i = 1; i < fieldSize; i++) {
            System.out.println("##[command] creating row: "+i);
            rowHead = sheet.createRow(i);
            rowHead.createCell(0).setCellValue(fieldIds.get(0));
            System.out.println("##[command] value: "+fieldIds.get(0)+" set to row: "+ i);
            fieldIds.remove(0);
        }
        // Write workbook to a file
        outputStreamEnabler(workbook,excelFilePath,isEnabled);
        System.out.println("##[command] workbook close write to excel");
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

