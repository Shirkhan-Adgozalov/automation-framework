package com.shirkhan.tests;

import com.shirkhan.utils.DataGenerator;
import com.shirkhan.utils.ExcelHelper;
import com.shirkhan.utils.LoggerHelper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ExcelIntegrationTest {

    @Test
    public void testCreateSampleExcel() throws IOException {
        String filePath = "test-data/sample_test_data.xlsx";
        String sheetName = "RegistrationTests";
        
        // Create sample Excel file
        ExcelHelper.createSampleExcel(filePath, sheetName);
        
        // Read and verify data
        int rowCount = ExcelHelper.getRowCount(filePath, sheetName);
        LoggerHelper.info("Total rows in Excel: " + rowCount);
        
        // Read first test case
        List<String> firstRow = ExcelHelper.readExcelData(filePath, sheetName, 1);
        LoggerHelper.info("First test case data: " + firstRow);
        
        // Read specific cell
        String firstName = ExcelHelper.getCellData(filePath, sheetName, 1, 1);
        LoggerHelper.info("First name from Excel: " + firstName);
    }
    
    @Test
    public void testDynamicExcelData() throws IOException {
        String filePath = "test-data/dynamic_test_data.xlsx";
        String sheetName = "DynamicTests";
        
        // Create dynamic test data
        List<List<String>> testData = List.of(
            List.of("TestCase", "Username", "Password", "ExpectedResult"),
            List.of("DT001", DataGenerator.randomUsername(), DataGenerator.randomPassword(), "Success"),
            List.of("DT002", DataGenerator.randomUsername(), DataGenerator.randomPassword(), "Success"),
            List.of("DT003", "invalid_user", "invalid_pass", "Failure")
        );
        
        ExcelHelper.writeToExcel(filePath, sheetName, testData);
        LoggerHelper.info("Dynamic Excel file created: " + filePath);
        
        // Verify data
        for (int i = 1; i < testData.size(); i++) {
            List<String> row = ExcelHelper.readExcelData(filePath, sheetName, i);
            LoggerHelper.info("Test case " + i + ": " + row);
        }
    }
}
