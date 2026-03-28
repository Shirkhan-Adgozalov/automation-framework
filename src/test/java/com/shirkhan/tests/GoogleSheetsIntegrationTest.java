package com.shirkhan.tests;

import com.shirkhan.utils.DataGenerator;
import com.shirkhan.utils.GoogleSheetsHelper;
import com.shirkhan.utils.LoggerHelper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class GoogleSheetsIntegrationTest {

    @Test
    public void testCreateSampleGoogleSheet() throws IOException, GeneralSecurityException {
        // Create sample test data in Google Sheets
        GoogleSheetsHelper.createSampleTestData();
        
        // Read data back
        List<List<String>> testData = GoogleSheetsHelper.readSheetData("TestCases!A:H");
        
        LoggerHelper.info("Total rows in Google Sheets: " + testData.size());
        
        // Print first few rows
        for (int i = 0; i < Math.min(5, testData.size()); i++) {
            LoggerHelper.info("Row " + i + ": " + testData.get(i));
        }
    }
    
    @Test
    public void testDynamicGoogleSheetsData() throws IOException, GeneralSecurityException {
        // Create new sheet for dynamic tests
        GoogleSheetsHelper.createSheet("DynamicTests");
        
        // Add dynamic test data
        for (int i = 1; i <= 5; i++) {
            List<String> testCaseData = List.of(
                "DT00" + i,
                DataGenerator.randomUsername(),
                DataGenerator.randomPassword(),
                DataGenerator.randomEmail(),
                "Success"
            );
            
            GoogleSheetsHelper.appendSheetData("DynamicTests!A:E", testCaseData);
            LoggerHelper.info("Added test case DT00" + i + " to Google Sheets");
        }
        
        // Read and verify
        List<List<String>> dynamicData = GoogleSheetsHelper.readSheetData("DynamicTests!A:E");
        LoggerHelper.info("Dynamic test data rows: " + dynamicData.size());
    }
    
    @Test
    public void testUpdateTestResults() throws IOException, GeneralSecurityException {
        // Read existing test cases
        List<List<String>> testData = GoogleSheetsHelper.readSheetData("TestCases!A:H");
        
        // Update status for first 3 test cases
        for (int i = 1; i <= Math.min(3, testData.size() - 1); i++) {
            String range = "TestCases!H" + (i + 1); // Status column
            String newStatus = "Passed";
            
            GoogleSheetsHelper.writeSheetData(range, List.of(List.of(newStatus)));
            LoggerHelper.info("Updated status for test case " + i + " to: " + newStatus);
        }
        
        // Verify updates
        List<List<String>> updatedData = GoogleSheetsHelper.readSheetData("TestCases!A:H");
        for (int i = 1; i <= Math.min(3, updatedData.size() - 1); i++) {
            LoggerHelper.info("Test case " + i + " status: " + updatedData.get(i).get(7));
        }
    }
}
