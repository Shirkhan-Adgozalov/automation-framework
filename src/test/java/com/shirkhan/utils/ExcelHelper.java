package com.shirkhan.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    // Read data from Excel file
    public static List<String> readExcelData(String filePath, String sheetName, int rowNum) throws IOException {
        List<String> rowData = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found");
            }
            
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                throw new RuntimeException("Row " + rowNum + " not found");
            }
            
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.add(String.valueOf((int) cell.getNumericCellValue()));
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    default:
                        rowData.add("");
                }
            }
        }
        
        return rowData;
    }

    // Get row count from sheet
    public static int getRowCount(String filePath, String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return 0;
            }
            return sheet.getLastRowNum() + 1;
        }
    }

    // Get column count from sheet
    public static int getColumnCount(String filePath, String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return 0;
            }
            Row firstRow = sheet.getRow(0);
            return firstRow != null ? firstRow.getLastCellNum() : 0;
        }
    }

    // Write data to Excel file
    public static void writeToExcel(String filePath, String sheetName, List<List<String>> data) throws IOException {
        Workbook workbook;
        Sheet sheet;
        
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
        } catch (IOException e) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet(sheetName);
        }
        
        // Clear existing data
        int lastRow = sheet.getLastRowNum();
        for (int i = lastRow; i >= 0; i--) {
            Row row = sheet.getRow(i);
            if (row != null) {
                sheet.removeRow(row);
            }
        }
        
        // Write new data
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i);
            List<String> rowData = data.get(i);
            for (int j = 0; j < rowData.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData.get(j));
            }
        }
        
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    // Create sample Excel file for testing
    public static void createSampleExcel(String filePath, String sheetName) throws IOException {
        List<List<String>> sampleData = new ArrayList<>();
        
        // Header row
        List<String> header = List.of("TestCase", "FirstName", "LastName", "Email", "Phone", "City", "ExpectedResult");
        sampleData.add(header);
        
        // Sample test data
        for (int i = 1; i <= 5; i++) {
            List<String> rowData = List.of(
                "TC00" + i,
                DataGenerator.randomFirstName(),
                DataGenerator.randomLastName(),
                DataGenerator.randomEmail(),
                DataGenerator.randomPhoneNumber(),
                DataGenerator.randomCity(),
                "Success"
            );
            sampleData.add(rowData);
        }
        
        writeToExcel(filePath, sheetName, sampleData);
        LoggerHelper.info("Sample Excel file created: " + filePath);
    }

    // Get specific cell data
    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return "";
            }
            
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return "";
            }
            
            Cell cell = row.getCell(colNum);
            if (cell == null) {
                return "";
            }
            
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf((int) cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                default:
                    return "";
            }
        }
    }
}
